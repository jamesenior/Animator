package cs3500.animator.view;

import cs3500.animator.model.ReadOnlyAnimatorModel;
import cs3500.animator.model.shapes.Shape;


/**
 * A class representing the SVG compatible cs3500.animator.view for the Animator Model.
 */
public class SVGAnimatorView extends AbsAnimatorView {
  private String viewString;

  /**
   * constructor for an AbsAnimatorView.
   * passes in the cs3500.animator.model to be animated.
   *
   * @param model the cs3500.animator.model to be passed to this cs3500.animator.view
   */
  public SVGAnimatorView(ReadOnlyAnimatorModel model, double tickRate) {
    super(model);
    render(tickRate);
  }

  @Override
  public void render(double tickRate) {
    if (tickRate <= 0) {
      throw new IllegalArgumentException("tick rate must be positive.");
    }
    StringBuilder result = new StringBuilder();
    //starter statement
    result.append("<svg width=\"" + model.getCanvasW()
            + "\" height=\"" + model.getCanvasH() + "\" version=\"1.1\" " +
            "xmlns=\"http://www.w3.org/2000/svg\">");
    //iterate through shapes
    for (Shape s : model.getShapes()) {
      result.append(s.getSVGTag(tickRate));
    }
    result.append("\n</svg>");
    viewString = result.toString();
  }

  @Override
  public String getViewString() {
    return viewString;
  }
}
