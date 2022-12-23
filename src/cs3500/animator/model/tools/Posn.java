package cs3500.animator.model.tools;

import java.util.Objects;

/**
 * represents a position in the x/y coordinate plane.
 */
public class Posn {
  private int x;
  private int y;

  /**
   * constructor for a Posn.
   * @param x the x coordinate of this Posn
   * @param y the y coordinate of this Posn
   */
  public Posn(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * extra constructor for a Posn that sets it to given pos.
   * @param pos the given Posn to set to
   */
  public Posn(Posn pos) {
    if (pos == null) {
      throw new IllegalArgumentException("pos cannot be null.");
    }
    x = pos.getX();
    y = pos.getY();
  }

  /**
   * get the x position of this Posn.
   * @return this x position
   */
  public int getX() {
    return x;
  }

  /**
   * get the y position of this Posn.
   * @return this y position
   */
  public int getY() {
    return y;
  }

  /**
   * sets this position to another position.
   */
  public void setPos(Posn that) {
    this.x = that.getX();
    this.y = that.getY();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Posn posn = (Posn) o;
    return x == posn.getX() && y == posn.getY();
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }

  @Override
  public String toString() {
    return "(" + x + ", " + y + ")";
  }
}
