package cs3500.animator.algoanimations;

import java.util.ArrayList;
import java.util.List;

import cs3500.animator.model.shapes.Rectangle;
import cs3500.animator.model.shapes.Shape;
import cs3500.animator.model.tools.MyColor;
import cs3500.animator.model.tools.Posn;

/**
 * class representing an abstract animation.
 * Has fields for the array of heights given, a List representing the initial
 * array, the total number of ticks, A list of Strings representing moves,
 * A list of Rectangles being moved, and the final output string.
 */
public abstract class AbsAlgoAnimation implements AlgoAnimation {
  protected final List<Integer> initArray;
  protected final int[] heights;
  protected int numTicks;
  protected final List<String> moveList;
  protected final List<Shape> rectangles;
  protected String finalString;

  /**
   * Constructor for an abstract Algo Animation.  The constructor takes in
   * an array of heights for the rectangle heights, and then calls each method to set each
   * field.
   *
   * @param heights the rectangle heights being used.
   */
  public AbsAlgoAnimation(int[] heights) {
    this.heights = heights;
    initArray = new ArrayList<>();
    for (int i : heights) {
      initArray.add(i);
    }
    numTicks = 1;
    moveList = new ArrayList<>();
    rectangles = new ArrayList<>();
    finalString = "";
    // initialize rectangles
    int n = heights.length;
    for (int i = 0; i < n; i++) {
      String name = "disk" + (i + 1);
      int width = 10;
      int height = heights[i] * 10;
      Shape r = new Rectangle(name, width, height, new Posn(100 * (i + 1), 400),
              new MyColor(0, 0, 0));
      rectangles.add(r);
    }
    // sort through heights and store all their movements
    sort(heights);
    // sets the final String for the animation
    finalString += toString();
  }

  @Override
  public abstract void sort(int[] arrInp);

  @Override
  public String moveString(String shapeName, double prevX, double prevY,
                           double nextX, double nextY, int startTick, int endTick) {
    return "move name " + shapeName + " moveto " + prevX + " " + prevY
            + " " + nextX + " " + nextY + " from " + startTick + " to " + endTick + "\n";
  }

  @Override
  public String toString() {
    String result = "";
    result += "canvas 800 800\n";
    int n = heights.length;
    // iterate through rectangles, creating initial statement for each
    for (int i = 0; i < n; i++) {
      String rectString = "rectangle name disk" + (i + 1)
              + " min-x " + 100 * (i + 1) + " min-y " + 400
              + " width " + 10 + " height " + initArray.get(i) * 10
              + " color " + 0 + " " + 0 + " " + 0
              + " from 1 to " + numTicks + "\n";
      result += rectString;
    }
    // iterate through the moveList,
    for (String move : moveList) {
      result += move;
    }
    return result;
  }

  @Override
  public String getFinalString() {
    return finalString;
  }
}
