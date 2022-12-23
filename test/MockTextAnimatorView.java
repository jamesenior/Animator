import java.io.IOException;
import java.util.List;
import java.util.NavigableMap;

import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.shapes.Shape;
import cs3500.animator.model.shapes.ShapeState;
import cs3500.animator.view.AbsAnimatorView;

/**
 * Class representing a MockTextAnimatorView for testing. Almost identical to real class.
 */
public class MockTextAnimatorView extends AbsAnimatorView {
  private Appendable log;
  private String viewString;

  /**
   * Constructor for Mock View.
   *
   * @param ap    given appendable.
   * @param model given model.
   */
  public MockTextAnimatorView(Appendable ap, AnimatorModel model) {
    super(model);
    log = ap;
    viewString = "";
  }

  @Override
  public void render(double tickRate) {
    String result = "canvas: " + model.getCanvasW() + "x" + model.getCanvasH();
    List<Shape> shapes = model.getShapes();
    for (Shape s : shapes) {
      result += "\nnew " + s.getType() + " " + s.getName();
    }
    for (Shape s : shapes) {
      NavigableMap<Integer, ShapeState> states = s.getStates();
      int thisTick = 0;
      while (states.higherKey(thisTick) != null) {
        int nextTick = states.higherKey(thisTick);
        ShapeState nextState = states.get(nextTick);
        result += "\nFrom t=" + (thisTick * tickRate) + " to t=" + (nextTick * tickRate)
                + ", move " + s.getName() + " to:\n" + nextState;
        thisTick = nextTick;
      }
    }
    viewString = result;
    try {
      log.append(result);
    } catch (IOException ioe) {
      throw new IllegalArgumentException("shouldn't happen...");
    }
  }

  @Override
  public String getViewString() {
    return viewString;
  }
}
