package cs3500.animator.algoanimations;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;


/**
 * class for the main method of an animation.
 */
public class MainAnim {
  private static int[] inpArray;
  private static String outputFileName;
  private static String type;
  private static boolean arrProvided;
  private static boolean outProvided;
  private static boolean typeProvided;

  /**
   * Main method. Creates the animation and writes it to a given file.
   * The main method takes an input array, an output file, and a type.
   * example: -array 2 1 3 -type bubble -out bubsort.txt.
   *
   * @param args the given commands
   */
  public static void main(String[] args) {
    List<Integer> listInt = new ArrayList<Integer>();
    //handles arguments
    for (int argIdx = 0; argIdx < args.length; argIdx += 2) {
      switch (args[argIdx]) {
        case "-array":
          if (arrProvided) {
            JOptionPane.showMessageDialog(null,
                    "array can only be provided once.");
            break;
          }
          // iterate through given array
          int nextIdx = argIdx + 1;
          while (isInt(args[nextIdx])) {
            listInt.add(Integer.parseInt(args[nextIdx]));
            nextIdx++;
          }
          arrProvided = true;
          int[] arr = listInt.stream().mapToInt(i -> i).toArray();
          inpArray = arr;
          break;
        case "-type":
          if (typeProvided) {
            JOptionPane.showMessageDialog(null,
                    "type can only be provided once.");
            break;
          }
          if (!args[argIdx + 1].equals("selection") && !args[argIdx + 1].equals("bubble")) {
            JOptionPane.showMessageDialog(null,
                    "incorrect types.");
            break;
          }
          type = args[argIdx + 1];
          typeProvided = true;
          break;
        case "-out":
          if (outProvided) {
            JOptionPane.showMessageDialog(null,
                    "output can only be provided once.");
            break;
          }
          outputFileName = args[argIdx + 1];
          outProvided = true;
          break;
        default:
          JOptionPane.showMessageDialog(null, "invalid command given.");
      }
    }
    if (!arrProvided || !outProvided || !typeProvided) {
      JOptionPane.showMessageDialog(null,
              "Missing inputs.");
    }
    // creates animation
    AlgoAnimation anim;
    if (type.equals("selection")) {
      anim = new SelectionSortAlgoAnimation(inpArray);
    } else {
      anim = new BubbleSortAlgoAnimation(inpArray);
    }
    // write to file
    Path fileName = Path.of(outputFileName);
    try {
      Files.writeString(fileName, anim.getFinalString());
    } catch (IOException e) {
      JOptionPane.showMessageDialog(null,
              "Could not find file to write to.");
    }
  }

  /**
   * Checks to see if the given String argument is an integer.
   *
   * @param arg the Argument being given
   * @return true if the argument is an integer
   */
  private static boolean isInt(String arg) {
    try {
      Integer.parseInt(arg);
      return true;
    } catch (NumberFormatException nfe) {
      return false;
    }
  }
}
