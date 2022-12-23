package cs3500.animator.view;

import cs3500.animator.model.ReadOnlyAnimatorModel;

/**
 * an abstract class representing a cs3500.animator.view with a tick rate in ticks per second.
 */
public abstract class AbsAnimatorView implements AnimatorView {
  protected ReadOnlyAnimatorModel model;
  protected double tickRate;

  /**
   * constructor for an AbsAnimatorView.
   * passes in the cs3500.animator.model to be animated.
   * @param model the cs3500.animator.model to be passed to this cs3500.animator.view
   */
  public AbsAnimatorView(ReadOnlyAnimatorModel model) {
    if (model == null) {
      throw new IllegalArgumentException("cs3500.animator.model cannot be null");
    }
    this.model = model;
  }

  /**
   * in subclasses, override and call super.render and fully implement render.
   * @param tickRate the rate of ticks per second
   */
  @Override
  public void render(double tickRate) {
    this.tickRate = tickRate;
  }

  @Override
  public abstract String getViewString();

  @Override
  public double getTickRate() {
    return tickRate;
  }
}
