import org.junit.Test;

import cs3500.animator.model.animations.Mutation;
import cs3500.animator.model.animations.Resize;
import cs3500.animator.model.animations.Translation;
import cs3500.animator.model.tools.Posn;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * a JUnit test class for the Mutation class.
 */
public class MutationTest {
  private Mutation t1 = new Translation(new Posn(30, 80), 0, 1);
  private Mutation t2 = new Translation(new Posn(30, 80), 0, 2);
  private Mutation r1 = new Resize(1, 1, 1, 2);

  @Test
  public void testSameTypeAs() {
    assertTrue(t1.isSameTypeAs(t2));
    assertFalse(t1.isSameTypeAs(r1));
  }
}