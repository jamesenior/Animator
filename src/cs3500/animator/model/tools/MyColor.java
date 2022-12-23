package cs3500.animator.model.tools;

import java.util.Objects;

/**
 * class representing our version of a color.
 * uses r/g/b values between 0-255.
 */
public class MyColor {
  private int red;
  private int green;
  private int blue;


  /**
   * a constructor for the Color.
   * red, green, and blue values must be between 0-255.
   */
  public MyColor(int red, int green, int blue) {
    validColor(red, green, blue);
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  /**
   * Extra constructor for MyColor that sets it to another.
   * @param color the given color
   */
  public MyColor(MyColor color) {
    if (color == null) {
      throw new IllegalArgumentException("color cannot be null");
    }
    red = color.getRed();
    green = color.getGreen();
    blue = color.getBlue();
  }

  private void validColor(int r, int g, int b) {
    if (r < 0 || r > 255 || g < 0 || g > 255 || b < 0 || b > 255) {
      throw new IllegalArgumentException("Red, green, and blue values must be between 0 and 225.");
    }
  }

  /**
   * gets the red value of this MyColor.
   */
  public int getRed() {
    return red;
  }

  /**
   * gets the green value of this MyColor.
   */
  public int getGreen() {
    return green;
  }

  /**
   * gets the blue value of this MyColor.
   */
  public int getBlue() {
    return blue;
  }

  /**
   * sets this MyColor to another MyColor.
   */
  public void setColor(MyColor that) {
    red = that.getRed();
    green = that.getGreen();
    blue = that.getBlue();
  }

  /**
   * returns the Color as a String used in the SVG format.
   *
   * @return the color as a String
   */
  public String getSVGColor() {
    return "\"rgb(" + red + "," + green + "," + blue + ")\"";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MyColor myColor = (MyColor) o;
    return red == myColor.getRed() && green == myColor.getGreen() && blue == myColor.getBlue();
  }

  @Override
  public int hashCode() {
    return Objects.hash(red, green, blue);
  }
}
