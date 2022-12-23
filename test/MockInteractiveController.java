
import cs3500.animator.controller.AnimatorController;
import cs3500.animator.controller.AnimatorKeyAdapter;
import cs3500.animator.view.AnimationSpeed;
import cs3500.animator.view.InteractiveAnimatorView;

/**
 * Mock controller to test the InteractiveAnimatorController class.
 * only difference is this class creates a MockInteractiveView rather than a real view.
 */
public class MockInteractiveController implements AnimatorController {
  private InteractiveAnimatorView view;
  private Appendable log;

  /**
   * constructor for a MockInteractiveController.
   * takes an Appendable to create and keep track of the mock view's log.
   * @param ap the appendable log to be added to by the mock view
   */
  public MockInteractiveController(Appendable ap) {
    if (ap == null) {
      throw new IllegalArgumentException("appendable cannot be null");
    }
    log = ap;
  }

  @Override
  public void startAnimation(double tickRate) {
    view = new MockInteractiveView(tickRate, log);
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
