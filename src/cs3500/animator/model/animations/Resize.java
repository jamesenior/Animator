package cs3500.animator.model.animations;

/**
 * a cs3500.animator.model.animations.Mutation that alters the dimensions of a shape.
 * width is the final width of the shape.
 * height is the final width of the shape.
 */
public class Resize extends Mutation {
  private int width;
  private int height;

  /**
   * constructor for a Resize.
   * @param width the destination width
   * @param height the destination height
   * @param startTick the start tick of this mutation
   * @param endTick the end tick of this mutation
   */
  public Resize(int width, int height, int startTick, int endTick) {
    super(startTick, endTick, MutationType.RESIZE);
    this.width = width;
    this.height = height;
  }

  /**
   * returns the final width of the shape after this cs3500.animator.model.animations.Dilation.
   * @return the final width of the shape
   */
  public int getWidth() {
    return width;
  }

  /**
   * returns the final height of the shape after this cs3500.animator.model.animations.Dilation.
   * @return the final height of the shape
   */
  public int getHeight() {
    return height;
  }
}
