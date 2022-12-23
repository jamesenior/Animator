package cs3500.animator.view;

import java.util.List;
import java.util.NavigableMap;

import cs3500.animator.model.ReadOnlyAnimatorModel;
import cs3500.animator.model.shapes.Shape;
import cs3500.animator.model.shapes.ShapeState;


/**
 * Class representing the Text Animator View.
 */
public class TextAnimatorView extends AbsAnimatorView {
  private String viewString;

  /**
   * Constructor.  Initializes and renders the view.
   * @param model the given model
   * @param tickRate the given tickRate
   */
  public TextAnimatorView(ReadOnlyAnimatorModel model, double tickRate) {
    super(model);
    render(tickRate);
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
  }

  @Override
  public String getViewString() {
    return viewString;
  }
}
