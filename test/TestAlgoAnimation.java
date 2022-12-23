import org.junit.Test;

import cs3500.animator.algoanimations.AlgoAnimation;
import cs3500.animator.algoanimations.BubbleSortAlgoAnimation;
import cs3500.animator.algoanimations.SelectionSortAlgoAnimation;

import static org.junit.Assert.assertEquals;

/**
 * tests for both the AlgoAnimations I created.
 */
public class TestAlgoAnimation {

  @Test
  public void testBubbleSortAnimation() {
    int[] myArray = {5, 1, 3, 2, 4};
    assertEquals(5, myArray[0]);
    AlgoAnimation bubble1 = new BubbleSortAlgoAnimation(myArray);
    assertEquals("canvas 800 800\n"
                    + "rectangle name disk1 min-x 100 min-y 400 "
                    + "width 10 height 50 color 0 0 0 from 1 to 51\n"
                    + "rectangle name disk2 min-x 200 min-y 400 "
                    + "width 10 height 10 color 0 0 0 from 1 to 51\n"
                    + "rectangle name disk3 min-x 300 min-y 400 "
                    + "width 10 height 30 color 0 0 0 from 1 to 51\n"
                    + "rectangle name disk4 min-x 400 min-y 400 "
                    + "width 10 height 20 color 0 0 0 from 1 to 51\n"
                    + "rectangle name disk5 min-x 500 min-y 400 "
                    + "width 10 height 40 color 0 0 0 from 1 to 51\n"
                    + "move name disk1 moveto 100.0 400.0 200.0 400.0 from 1 to 11\n"
                    + "move name disk2 moveto 200.0 400.0 100.0 400.0 from 1 to 11\n"
                    + "move name disk1 moveto 200.0 400.0 300.0 400.0 from 11 to 21\n"
                    + "move name disk3 moveto 300.0 400.0 200.0 400.0 from 11 to 21\n"
                    + "move name disk1 moveto 300.0 400.0 400.0 400.0 from 21 to 31\n"
                    + "move name disk4 moveto 400.0 400.0 300.0 400.0 from 21 to 31\n"
                    + "move name disk1 moveto 400.0 400.0 500.0 400.0 from 31 to 41\n"
                    + "move name disk5 moveto 500.0 400.0 400.0 400.0 from 31 to 41\n"
                    + "move name disk3 moveto 200.0 400.0 300.0 400.0 from 41 to 51\n"
                    + "move name disk4 moveto 300.0 400.0 200.0 400.0 from 41 to 51\n",
            bubble1.getFinalString());
  }

  @Test
  public void testSelectionSortAnimation() {
    int[] myArray = {5, 1, 3, 2, 4};
    assertEquals(5, myArray[0]);
    AlgoAnimation select1 = new SelectionSortAlgoAnimation(myArray);
    assertEquals("canvas 800 800\n"
                    + "rectangle name disk1 min-x 100 min-y 400 "
                    + "width 10 height 50 color 0 0 0 from 1 to 41\n"
                    + "rectangle name disk2 min-x 200 min-y 400 "
                    + "width 10 height 10 color 0 0 0 from 1 to 41\n"
                    + "rectangle name disk3 min-x 300 min-y 400 "
                    + "width 10 height 30 color 0 0 0 from 1 to 41\n"
                    + "rectangle name disk4 min-x 400 min-y 400 "
                    + "width 10 height 20 color 0 0 0 from 1 to 41\n"
                    + "rectangle name disk5 min-x 500 min-y 400 "
                    + "width 10 height 40 color 0 0 0 from 1 to 41\n"
                    + "move name disk1 moveto 100.0 400.0 200.0 400.0 from 1 to 11\n"
                    + "move name disk2 moveto 200.0 400.0 100.0 400.0 from 1 to 11\n"
                    + "move name disk1 moveto 200.0 400.0 400.0 400.0 from 11 to 21\n"
                    + "move name disk4 moveto 400.0 400.0 200.0 400.0 from 11 to 21\n"
                    + "move name disk1 moveto 400.0 400.0 500.0 400.0 from 31 to 41\n"
                    + "move name disk5 moveto 500.0 400.0 400.0 400.0 from 31 to 41\n",
            select1.getFinalString());
  }


}
