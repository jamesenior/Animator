package cs3500.animator.algoanimations;


import java.util.Collections;

/**
 * Class representing a selection sort animation.
 */
public class SelectionSortAlgoAnimation extends AbsAlgoAnimation {

  public SelectionSortAlgoAnimation(int[] heights) {
    super(heights);
  }

  @Override
  public void sort(int[] arrInp) {
    int[] arr = arrInp;
    int n = arr.length;
    int temp = 0;
    // One by one move boundary of unsorted subarray
    for (int i = 0; i < n - 1; i++) {
      // Find the minimum element in unsorted array
      int min_idx = i;
      for (int j = i + 1; j < n; j++) {
        if (arr[j] < arr[min_idx]) {
          min_idx = j;
        }
      }
      int prevX = 100 * (i + 1);
      int nextX = 100 * (min_idx + 1);
      int y = 400;
      // stores moves in List
      if (min_idx != i) {
        //moves larger rectangle forward
        moveList.add(moveString(rectangles.get(i).getName(), prevX, y, nextX,
                y, numTicks, numTicks + 10));
        //moves smaller rectangle back
        moveList.add(moveString(rectangles.get(min_idx).getName(), nextX, y, prevX,
                y, numTicks, numTicks + 10));
      }
      // swap rectangles in list of rectangles
      Collections.swap(rectangles, i, min_idx);
      // Swap the found minimum element with the first
      // element
      temp = arr[min_idx];
      arr[min_idx] = arr[i];
      arr[i] = temp;
      // increment number of ticks
      numTicks += 10;
    }
  }
}
