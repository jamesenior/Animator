package cs3500.animator.model.animations;

/**
 * an abstract class representing some change for a shape that occurs over some number of ticks.
 * startTick is the tick on which this mutation begins (must be >= 0).
 * endTick is the tick on which this mutation finishes.
 */
public abstract class Mutation {
  private int startTick;
  private int endTick;
  private MutationType type;

  /**
   * constructor for a Mutation.
   * @param startTick the start tick of this Mutation
   * @param endTick the end tick of this Mutation
   * @param type the type of Mutation, as a {@link MutationType}
   */
  public Mutation(int startTick, int endTick, MutationType type) {
    if (startTick < 0) {
      throw new IllegalArgumentException("startTick must be >= 0");
    }
    if (startTick >= endTick) {
      throw new IllegalArgumentException("startTick must be before than endTick");
    }
    if (type == null) {
      throw new IllegalArgumentException("Mutation type cannot be null.");
    }
    this.startTick = startTick;
    this.endTick = endTick;
    this.type = type;
  }

  /**
   * returns the start tick of this mutation.
   * @return the start tick of this mutation
   */
  public int getStartTick() {
    return startTick;
  }

  /**
   * returns the end tick of this mutation.
   * @return the end tick of this mutation
   */
  public int getEndTick() {
    return endTick;
  }

  /**
   * return the type of this mutation.
   * @return this mutation's type
   */
  public MutationType getType() {
    return type;
  }

  /**
   * is this Mutation the same type of mutation as the one provided?.
   * @param m the provided mutation
   * @return whether this mutation is the same type as m
   */
  public boolean isSameTypeAs(Mutation m) {
    return type == m.getType();
  }
}