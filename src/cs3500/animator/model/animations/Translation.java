package cs3500.animator.model.animations;

import cs3500.animator.model.tools.Posn;

/**
 * a cs3500.animator.model.animations.Mutation that moves a shape in the x/y plane.
 * x is the x coordinate of the destination point.
 * y is the y coordinate of the destination point.
 */
public class Translation extends Mutation {
  private Posn pos;

  /**
   * constructor for a Translation.
   * @param pos the destination position
   * @param startTick the start tick of this mutation
   * @param endTick the end tick of this mutation
   */
  public Translation(Posn pos, int startTick, int endTick) {
    super(startTick, endTick, MutationType.TRANSLATION);
    this.pos = pos;
  }

  /**
   * get the destination position of this Translation.
   * @return the destination position
   */
  public Posn getPos() {
    return new Posn(pos);
  }

  /**
   * return the destination x coordinate.
   * @return final x position
   */
  public int getX() {
    return pos.getX();
  }

  /**
   * return the destination y coordinate.
   * @return final y position
   */
  public int getY() {
    return pos.getY();
  }
}
