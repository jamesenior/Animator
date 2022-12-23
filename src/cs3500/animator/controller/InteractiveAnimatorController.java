package cs3500.animator.controller;

import cs3500.animator.model.ReadOnlyAnimatorModel;
import cs3500.animator.view.AnimationSpeed;
import cs3500.animator.view.InteractiveAnimatorView;
import cs3500.animator.view.InteractiveView;

/**
 * an AnimatorController that supports an InteractiveAnimatorView and handles the following inputs:.
 *   p: pause/play.
 *   r: restart.
 *   l: toggle looping.
 *   q: slow animation to half its original speed.
 *   w: set animation to its original speed.
 *   e: speed up animation to double its original speed.
 */
public class InteractiveAnimatorController implements AnimatorController {
  private ReadOnlyAnimatorModel model;
  private InteractiveAnimatorView view;

  /**
   * Constructor for an InteractiveAnimatorController.
   * links this controller to the given model.
   * @param model the model to create a controller for
   */
  public InteractiveAnimatorController(ReadOnlyAnimatorModel model) {
    if (model == null) {
      throw new IllegalArgumentException("model cannot be null.");
    }
    this.model = model;
  }

  @Override
  public void startAnimation(double tickRate) {
    view = new InteractiveView(model, tickRate);
    view.addListener(new AnimatorKeyAdapter(this));
    view.makeVisible();
  }

  @Override
  public void handleKey(char key) {
    switch (key) {
      case 'p':
        view.togglePause();
        view.refreshOnce();
        break;
      case 'r':
        view.restart();
        break;
      case 'l':
        view.toggleLoop();
        view.refreshOnce();
        break;
      case 'q':
        view.changeTickRate(view.getTickRate() * 0.5);
        view.setSpeed(AnimationSpeed.SLOW);
        view.refreshOnce();
        break;
      case 'w':
        view.changeTickRate(view.getTickRate());
        view.setSpeed(AnimationSpeed.NORMAL);
        view.refreshOnce();
        break;
      case 'e':
        view.changeTickRate(view.getTickRate() * 2.0);
        view.setSpeed(AnimationSpeed.FAST);
        view.refreshOnce();
        break;
      default:
        break;
    }
  }
}
