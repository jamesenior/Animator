package cs3500.animator.algoanimations;

/**
 * Interface representing an animation of an algorithm.
 * The animation will take in an array of ints
 * representing a set of rectangles, and then sort them according to their heights.
 */
public interface AlgoAnimation {

  /**
   * Does the main work of the animation.
   * Iterates through the Animation's array of integers and sorts them,
   * as well as adding each move to the list of moves as it iterates.
   */
  void sort(int[] arrInp);

  /**
   * Creates a String for the given move.
   *
   * @param shapeName the Name of the Shape
   * @param prevX     the previous X position of the shape
   * @param prevY     the previous Y position of the shape
   * @param nextX     the next X position of the shape
   * @param nextY     the next Y position of the shape
   * @param startTick the start Tick of the move
   * @param endTick   the end Tick of the move
   * @return the concatenated move String
   */
  String moveString(String shapeName, double prevX, double prevY,
                    double nextX, double nextY, int startTick, int endTick);


  /**
   * Gets the final full String for the animation.
   *
   * @return The full concatenated String
   */
  String getFinalString();
}
