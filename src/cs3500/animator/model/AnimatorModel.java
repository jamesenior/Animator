package cs3500.animator.model;


import cs3500.animator.model.animations.Mutation;
import cs3500.animator.model.shapes.Shape;


/**
 * an Interface representing all the operations a user can perform on an Animation model.
 * can add and remove Shapes, can add and remove Mutations from the shapes.
 */
public interface AnimatorModel extends ReadOnlyAnimatorModel {

  /**
   * add a Shape to this animation.
   * @param shape the Shape to be added
   */
  void addShape(Shape shape);

  /**
   * remove the given Shape from this animation.
   * @param shape the Shape to be removed
   */
  void removeShape(Shape shape);

  /**
   * add the given Mutation to the given Shape.
   * accommodate the Mutation to the Shape's map of states.
   * @param m the Mutation to be added
   * @param s the Shape to add the Mutation to
   */
  void addMutationTo(Mutation m, Shape s);

  /**
   * remove the most recent Mutation from the given Shape.
   * @param s the Shape to remove the mutation from
   */
  void removeMutationFrom(Shape s);
}
