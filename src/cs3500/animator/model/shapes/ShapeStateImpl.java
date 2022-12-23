package cs3500.animator.model.shapes;

import java.util.Objects;

import cs3500.animator.model.tools.MyColor;
import cs3500.animator.model.tools.Posn;

/**
 * class representing the State of a {@link Shape}.
 * contains width, height, x/y position, and color.
 */
public class ShapeStateImpl implements ShapeState {

  private int width;
  private int height;
  private Posn pos;
  private MyColor color;

  /**
   * constructor for a ShapeStateImpl.
   *
   * @param width  the initial width (must be > 0)
   * @param height the initial height (must be > 0)
   * @param pos    the initial position
   * @param color  the initial color
   */
  public ShapeStateImpl(int width, int height, Posn pos, MyColor color) {
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Invalid dimensions given.");
    }
    if (pos == null) {
      throw new IllegalArgumentException("Position cannot be null.");
    }
    if (color == null) {
      throw new IllegalArgumentException("Color cannot be null.");
    }
    this.width = width;
    this.height = height;
    this.pos = pos;
    this.color = color;
  }

  /**
   * secondary constructor for a ShapeStateImpl.
   * creates a deep copy of the given ShapeState.
   *
   * @param state the ShapeState to copy
   */
  public ShapeStateImpl(ShapeState state) {
    if (state == null) {
      throw new IllegalArgumentException("State cannot be null.");
    }
    width = state.getWidth();
    height = state.getHeight();
    pos = state.getPos();
    color = state.getColor();
  }

  @Override
  public int getWidth() {
    return width;
  }

  @Override
  public int getHeight() {
    return height;
  }

  @Override
  public Posn getPos() {
    return pos;
  }

  @Override
  public MyColor getColor() {
    return color;
  }

  @Override
  public void setWidth(int w) {
    if (w <= 0) {
      throw new IllegalArgumentException("Invalid dimensions given.");
    }
    width = w;
  }

  @Override
  public void setHeight(int h) {
    if (h <= 0) {
      throw new IllegalArgumentException("Invalid dimensions given.");
    }
    height = h;
  }

  @Override
  public void setPos(Posn p) {
    pos = p;
  }

  @Override
  public void setColor(MyColor c) {
    color = c;
  }

  /**
   * Format:            .
   * "Width: w          .
   * Height: h         .
   * Position: (x, y)  .
   * Color: (r, g, b)" .
   */
  @Override
  public String toString() {
    return "Width: " + width + "\nHeight: " + height + "\n"
            + "Position: (" + pos.getX() + ", " + pos.getY() + ")\n"
            + "Color: (" + color.getRed() + ", " + color.getGreen() + ", " + color.getBlue() + ")";
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof ShapeStateImpl)) {
      return false;
    }
    ShapeStateImpl other = (ShapeStateImpl) obj;
    if (this == obj) {
      return true;
    }
    return width == other.getWidth()
            && height == other.getHeight()
            && pos.equals(other.getPos())
            && color.equals(other.getColor());
  }

  @Override
  public int hashCode() {
    return Objects.hash(width, height, pos, color);
  }
}
