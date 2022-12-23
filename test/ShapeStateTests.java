import org.junit.Before;
import org.junit.Test;

import cs3500.animator.model.shapes.ShapeState;
import cs3500.animator.model.shapes.ShapeStateImpl;
import cs3500.animator.model.tools.MyColor;
import cs3500.animator.model.tools.Posn;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * test for our shape class and its methods.
 */
public class ShapeStateTests {
  private Posn pos1;
  private MyColor red;
  private MyColor blue;
  private ShapeState state1;
  private ShapeState state2;
  private ShapeState state3;

  @Before
  public void setup() {
    pos1 = new Posn(1, 2);
    red = new MyColor(255, 0, 0);
    blue = new MyColor(0, 0, 255);
    state1 = new ShapeStateImpl(4, 5, pos1, red);
    state2 = new ShapeStateImpl(4, 5, pos1, red);
    state3 = new ShapeStateImpl(5, 4, pos1, blue);
  }

  @Test
  public void testGetters() {
    assertEquals(red, state1.getColor());
    assertEquals(4, state1.getWidth());
    assertEquals(5, state1.getHeight());
    assertEquals(pos1, state1.getPos());
  }

  @Test
  public void testSetters() {
    state1.setColor(blue);
    assertEquals(blue, state1.getColor());
    state1.setHeight(6);
    assertEquals(6, state1.getHeight());
    state1.setWidth(3);
    assertEquals(3, state1.getWidth());
    Posn pos2 = new Posn(6, 6);
    state1.setPos(pos2);
    assertEquals(pos2, state1.getPos());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidDimensionsWidth() {
    ShapeState state1 = new ShapeStateImpl(-4, 5, pos1, red);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidDimensionsHeight() {
    ShapeState state1 = new ShapeStateImpl(4, -5, pos1, red);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidDimensionsSetWidth() {
    state1.setWidth(-4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidDimensionsSetHeight() {
    state1.setHeight(-3);
  }

  @Test
  public void testToString() {
    String expectedOutput = "Width: 4\n"
            + "Height: 5\n"
            + "Position: (1, 2)\n"
            + "Color: (255, 0, 0)";
    assertEquals(expectedOutput, state1.toString());
  }

  @Test
  public void testEquals() {
    assertEquals(state1, state2);
    assertFalse(state1.equals(state3));
  }

  @Test
  public void testHashCode() {
    assertEquals(state1.hashCode(), state2.hashCode());
    assertFalse(state1.hashCode() == state3.hashCode());
  }
}
