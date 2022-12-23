package cs3500.animator.model;

import java.util.List;

import cs3500.animator.model.shapes.Shape;

/**
 * A read-only animator model that only supports getting information about the state of the model.
 * consists of Shapes that contain Mutations.
 * can produce a string representing all shapes and all mutations happening to each shape.
 */
public interface ReadOnlyAnimatorModel {
  /**
   * get the list of shapes that are in this animation.
   */
  List<Shape> getShapes();

  /**
   * creates a text Output of this animation.
   */
  String toString();

  /**
   * get the width of the canvas.
   */
  int getCanvasW();

  /**
   * get the height of the canvas.
   */
  int getCanvasH();

  /**
   * get the final tick of this animation.
   */
  int getLastTick();
}
