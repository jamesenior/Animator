import java.io.IOException;

import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.shapes.Shape;
import cs3500.animator.view.AbsAnimatorView;

/**
 * class representing a mock cs3500.animator.view for the SVG cs3500.animator.view.
 */
public class MockSVGAnimatorView extends AbsAnimatorView {
  private Appendable log;
  private String viewString;

  /**
   * Constructor for mock view.
   *
   * @param ap    given appendable.
   * @param model given model.
   */
  public MockSVGAnimatorView(Appendable ap, AnimatorModel model) {
    super(model);
    log = ap;
    viewString = "";
  }


  @Override
  public void render(double tickRate) {
    if (tickRate <= 0) {
      throw new IllegalArgumentException("tick rate must be positive.");
    }
    StringBuilder result = new StringBuilder();
    //starter statement
    result.append("<svg width=\"" + model.getCanvasW()
            + "\" height=\"" + model.getCanvasH() + "\" version=\"1.1\" "
            + "xmlns=\"http://www.w3.org/2000/svg\">");
    //iterate through shapes
    for (Shape s : model.getShapes()) {
      result.append(s.getSVGTag(tickRate));
    }
    result.append("\n</svg>");
    viewString = result.toString();
    try {
      log.append(result.toString());
    } catch (IOException ioe) {
      throw new IllegalArgumentException("shouldn't happen...");
    }
  }

  @Override
  public String getViewString() {
    return viewString;
  }
}
