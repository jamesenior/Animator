package cs3500.animator;

import java.io.FileNotFoundException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import javax.swing.JOptionPane;

import cs3500.animator.controller.AnimatorController;
import cs3500.animator.controller.InteractiveAnimatorController;
import cs3500.animator.io.AnimationBuilder;
import cs3500.animator.io.AnimationFileReader;
import cs3500.animator.model.AnimatorModel;
import cs3500.animator.view.AnimatorView;

/**
 * main class for running an Animation.
 */
public class Main {
  private static AnimatorModel model;
  private static String viewType;
  private static int speed;
  private static String outputFile;
  private static boolean inProvided;
  private static boolean viewProvided;
  private static boolean outProvided;
  private static boolean speedProvided;

  /**
   * The main method to build different types of views.
   * recognized commands are: -in, -view, -out, and -speed.
   * The format for each is as follows:.
   * -in "name-of-animation-file".
   * -view "type-of-view".
   * -out "where-output-should-go" (must be .txt file for text view or a .svg file for svg view).
   * -speed "integer-ticks-per-second".
   * the -in and -view commands are required.
   * -out defaults to System.out if no output file is provided, and is ignored for visual views.
   * -speed defaults to 1 tick per second.
   *
   * @param args the commands passed to the main method
   */
  public static void main(String[] args) {
    // speed defaults to 1
    speed = 1;
    outputFile = null;
    inProvided = false;
    viewProvided = false;
    outProvided = false;
    speedProvided = false;
    for (int argIdx = 0; argIdx < args.length; argIdx += 2) {
      handleCommand(args[argIdx], args[argIdx + 1]);
    }
    if (!inProvided || !viewProvided) {
      JOptionPane.showMessageDialog(null,
              "must provide source file and view type.");
      return;
    }
    try {
      if (viewType.equals("interactive")) {
        AnimatorController c = new InteractiveAnimatorController(model);
        c.startAnimation(speed);
      } else {
        // creates view, will render for visual view
        AnimatorView view = AnimatorView.createView(viewType, model, speed);
        // runs on text or view files
        if (viewType.equals("text") || viewType.equals("svg")) {
          String outString = view.getViewString();
          if (!outProvided) {
            System.out.print(outString);
          } else if (!fileNameMatch(outputFile)) {
            JOptionPane.showMessageDialog(null,
                    "output file must be of matching type to view.");
            return;
          } else {
            // write to a file
            Path fileName = Path.of(outputFile);
            Files.writeString(fileName, outString);
          }
        }
      }
    } catch (IllegalArgumentException ia) {
      JOptionPane.showMessageDialog(null,
              "view type provided is invalid.");
      return;
    } catch (IOException e) {
      // when file can't be found
      JOptionPane.showMessageDialog(null,
              "Could not find file to write to.");
      return;
    }
  }

  /**
   * Handles the commands given individually, checks for any repeats.
   *
   * @param cmd   the Command type
   * @param input the command input
   */
  private static void handleCommand(String cmd, String input) {
    switch (cmd) {
      case "-in":
        if (inProvided) {
          JOptionPane.showMessageDialog(null,
                  "-in can only be called once.");
          break;
        }
        inProvided = true;
        try {
          model = new AnimationFileReader().readFile(input, new AnimationBuilder());
        } catch (FileNotFoundException fnf) {
          JOptionPane.showMessageDialog(null, "file " + input
                  + " not found.");
          break;
        }
        break;
      case "-view":
        if (viewProvided) {
          JOptionPane.showMessageDialog(null, "-view can only be called once.");
          break;
        }
        viewProvided = true;
        viewType = input;
        break;
      case "-out":
        if (outProvided) {
          JOptionPane.showMessageDialog(null,
                  "-out can only be called once.");
          break;
        }
        outProvided = true;
        outputFile = input;
        break;
      case "-speed":
        if (speedProvided) {
          JOptionPane.showMessageDialog(null,
                  "-speed can only be called once.");
          break;
        }
        speedProvided = true;
        try {
          speed = Integer.parseInt(input);
        } catch (NumberFormatException nfe) {
          JOptionPane.showMessageDialog(null,
                  "invalid speed: " + input
                          + ". speed must be an integer.");
          break;
        }
        break;
      default:
        JOptionPane.showMessageDialog(null, "invalid command: " + cmd);
    }
  }

  /**
   * checks if the fileName matches the type of the view.
   *
   * @param fileName the output fileName.
   * @return true if the fileName matches the current viewType.
   */
  private static boolean fileNameMatch(String fileName) {
    Objects.requireNonNull(fileName);
    boolean bool;
    switch (viewType) {
      case ("svg"):
        try {
          bool = fileName.endsWith(".svg");
        } catch (IndexOutOfBoundsException iob) {
          bool = false;
        }
        break;
      case ("text"):
        try {
          bool = fileName.endsWith(".txt");
        } catch (IndexOutOfBoundsException iob) {
          bool = false;
        }
        break;
      case ("visual"):
        bool = true;
        break;
      default:
        bool = false;
        break;
    }
    return bool;
  }
}
