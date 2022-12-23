package cs3500.animator.view;

import cs3500.animator.model.ReadOnlyAnimatorModel;

/**
 * an interface representing a cs3500.animator.view for an Animator.
 */
public interface AnimatorView {

  /**
   * render this animation visually at the given tick rate.
   * @param tickRate the rate of ticks per second
   */
  void render(double tickRate);

  /**
   * get the String for this View.
   */
  String getViewString();

  /**
   * get the tickRate of this view.
   */
  double getTickRate();

  /**
   * factory method that produces a view with the given model at the given tick rate.
   * @param type the type of view to instantiate
   * @param model the model to view
   * @param tickRate rate in ticks per second
   */
  static AnimatorView createView(String type, ReadOnlyAnimatorModel model, double tickRate)
          throws IllegalArgumentException {
    switch (type) {
      case "text":
        return new TextAnimatorView(model, tickRate);
      case "visual":
        return new SwingAnimatorView(model, tickRate);
      case "svg":
        return new SVGAnimatorView(model, tickRate);
      case "interactive":
        return new InteractiveView(model, tickRate);
      default:
        throw new IllegalArgumentException("invalid view type.");
    }
  }
}
