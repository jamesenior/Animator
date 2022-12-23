package cs3500.animator.model.animations;

import cs3500.animator.model.tools.MyColor;

/**
 * a Mutation that changes the RGB color of a shape.
 * r is the destination red component (int 0-255).
 * g is the destination green component (int 0-255).
 * b is the destination blue component (int 0-255).
 */
public class ColorShift extends Mutation {
  private MyColor color;

  /**
   * constructor for a ColorShift.
   * @param color the destination color
   * @param startTick the start tick of this mutation
   * @param endTick the end tick of this mutation
   */
  public ColorShift(MyColor color, int startTick, int endTick) {
    super(startTick, endTick, MutationType.COLOR_SHIFT);
    this.color = color;
  }

  /**
   * get the destination color as a MyColor.
   * @return the destination color
   */
  public MyColor getColor() {
    return new MyColor(color);
  }

  /**
   * return the destination red component (int 0-255).
   * @return destination red component
   */
  public int getR() {
    return color.getRed();
  }

  /**
   * return the shift in green component (int 0-255).
   * @return destination green component
   */
  public int getG() {
    return color.getGreen();
  }

  /**
   * return the shift in blue component (int 0-255).
   * @return destination blue component
   */
  public int getB() {
    return color.getBlue();
  }
}
