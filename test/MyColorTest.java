import org.junit.Before;
import org.junit.Test;

import cs3500.animator.model.tools.MyColor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * tests for our color class.
 */
public class MyColorTest {
  private MyColor red;

  @Before
  public void setup() {
    red = new MyColor(255, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLowRed() {
    new MyColor(-3, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testHiRed() {
    new MyColor(256, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLowGreen() {
    new MyColor(3, -4, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testHiGreen() {
    new MyColor(250, 263, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLowBlue() {
    new MyColor(3, 0, -2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testHiBlue() {
    new MyColor(25, 0, 300);
  }

  @Test
  public void testGetters() {
    MyColor r = new MyColor(255, 0, 5);
    assertEquals(255, r.getRed());
    assertEquals(0, r.getGreen());
    assertEquals(5, r.getBlue());
  }

  @Test
  public void testSetColor() {
    MyColor r = new MyColor(255, 0, 5);
    MyColor b = new MyColor(0, 0, 5);
    r.setColor(b);
    assertEquals(r, b);
  }


  @Test
  public void testEquals() {
    MyColor r = new MyColor(255, 0, 5);
    MyColor g = new MyColor(255, 0, 5);
    assertTrue(r.equals(g));
    assertFalse(r.equals(red));
  }

  @Test
  public void testHashCode() {
    assertEquals(red.hashCode(), new MyColor(255, 0, 0).hashCode());
    assertFalse(red.hashCode() == new MyColor(254, 0, 0).hashCode());
  }

}