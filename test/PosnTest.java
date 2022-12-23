import org.junit.Before;
import org.junit.Test;

import cs3500.animator.model.tools.Posn;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * tests for our Posn class.
 */
public class PosnTest {
  private Posn pos1;

  @Before
  public void setup() {
    pos1 = new Posn(1, 2);
  }

  @Test
  public void testGetters() {
    assertEquals(1, pos1.getX());
    assertEquals(2, pos1.getY());
  }

  @Test
  public void testSet() {
    pos1.setPos(new Posn(3, 4));
    assertEquals(new Posn(3, 4), pos1);
  }

  @Test
  public void testEquals() {
    Posn pos2 = new Posn(1, 2);
    Posn pos3 = new Posn(2, 2);
    assertEquals(pos1, pos2);
    assertFalse(pos1.equals(pos3));
  }

  @Test
  public void testHashCode() {
    assertEquals(pos1.hashCode(), new Posn(1, 2).hashCode());
    assertFalse(pos1.hashCode() == new Posn(2, 2).hashCode());
  }

}
