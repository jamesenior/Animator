import org.junit.Before;
import org.junit.Test;

import cs3500.animator.controller.AnimatorController;

import static org.junit.Assert.assertEquals;

/**
 * A JUnit test class for the interactive controller.
 * uses a controller with a mock view to log what is happening when the controller is given keys.
 */
public class InteractiveControllerTest {
  private AnimatorController controller;
  private Appendable log;

  @Before
  public void setup() {
    AnimatorModel model = new SimpleAnimatorModel(100, 100);
    log = new StringBuilder();
    controller = new MockInteractiveController(log);
  }

  @Test
  public void testInvalidKeyInputs() {
    setup();
    controller.startAnimation(100);
    controller.handleKey('d');
    controller.handleKey('a');
    String expectedString = "added key listener\n"
            + "made visible\n";
    assertEquals(expectedString, log.toString());
  }

  @Test
  public void testValidKeyInputsInOrder() {
    setup();
    controller.startAnimation(100);
    controller.handleKey('p');
    controller.handleKey('l');
    controller.handleKey('r');
    controller.handleKey('q');
    controller.handleKey('w');
    controller.handleKey('e');
    String expectedString = "added key listener\n"
            + "made visible\n"
            + "pause toggled\n"
            + "refreshed once\n"
            + "loop toggled\n"
            + "refreshed once\n"
            + "restarted\n"
            + "tick rate changed to 50.0\n"
            + "speed set to SLOW\n"
            + "refreshed once\n"
            + "tick rate changed to 100.0\n"
            + "speed set to NORMAL\n"
            + "refreshed once\n"
            + "tick rate changed to 200.0\n"
            + "speed set to FAST\n"
            + "refreshed once\n";
    assertEquals(expectedString, log.toString());
  }
}