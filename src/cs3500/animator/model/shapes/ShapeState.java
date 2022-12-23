package cs3500.animator.model.shapes;

import cs3500.animator.model.tools.MyColor;
import cs3500.animator.model.tools.Posn;

/**
 * class representing the state of a Shape.
 * a ShapeState stores a width, height, {@link Posn}, and {@link MyColor}.
 */
public interface ShapeState {

  /**
   * get the width of this Shape.
   */
  int getWidth();

  /**
   * get the height of this Shape.
   */
  int getHeight();

  /**
   * get the x/y position of this Shape.
   */
  Posn getPos();

  /**
   * get the color of this Shape.
   */
  MyColor getColor();

  /**
   * set the width of this Shape to the given int.
   *
   * @param w the new width of this Shape
   */
  void setWidth(int w);

  /**
   * set the height of this Shape to the given int.
   *
   * @param h the new height of this Shape
   */
  void setHeight(int h);

  /**
   * set the position of this Shape to the given Posn.
   *
   * @param p the new Posn of this Shape
   */
  void setPos(Posn p);

  /**
   * set the color of this shape to the given MyColor.
   *
   * @param c the new color of this Shape
   */
  void setColor(MyColor c);
}
