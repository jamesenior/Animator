package cs3500.animator.algoanimations;

import java.util.Collections;

/**
 * class representing an animation for Bubble Sort. Does so programmatically.
 * Bubble sort moves through each adjacent pair and if the earlier rectangle
 * is greater (in my example, higher), then the two items are swapped
 */
public class BubbleSortAlgoAnimation extends AbsAlgoAnimation {

  /**
   * constructor for a BubbleSortAnimation.  This animation is created by
   *
   * @param heights the set of rectangle heights for the animation.
   */
  public BubbleSortAlgoAnimation(int[] heights) {
    super(heights);
  }

  @Override
  public void sort(int[] arrInp) {
    int[] arr = arrInp;
    int n = arr.length;
    int temp = 0;
    for (int i = 0; i < n - 1; i++) {
      for (int j = 0; j < n - i - 1; j++) {
        if (arr[j] > arr[j + 1]) {
          int prevX = 100 * (j + 1);
          int nextX = 100 * (j + 2);
          int y = 400;
          // store moves in list
          //move bigger rectangle forward in animation
          moveList.add(moveString(rectangles.get(j).getName(), prevX, y, nextX, y,
                  numTicks, numTicks + 10));
          //move smaller rectangle back in animation
          moveList.add(moveString(rectangles.get(j + 1).getName(), nextX, y, prevX, y,
                  numTicks, numTicks + 10));
          // swap rectangles in list of rectangles
          Collections.swap(rectangles, j, j + 1);
          // swap arr[j+1] and arr[j]
          temp = arr[j];
          arr[j] = arr[j + 1];
          arr[j + 1] = temp;
          // increment number of moves and number of ticks
          numTicks += 10;
        }
      }
    }
  }
}
