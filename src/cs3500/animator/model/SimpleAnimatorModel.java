package cs3500.animator.model;


import java.util.ArrayList;

import java.util.Collections;
import java.util.List;


import cs3500.animator.model.animations.Mutation;
import cs3500.animator.model.shapes.Shape;


/**
 * class a cs3500.animator.model of an animation.
 * animation contains a list of Shapes, each of which has a number of mutations that happen to it.
 * initializes with no shapes.
 * supports changing x/y coordinate, width, height, and color.
 */
public class SimpleAnimatorModel implements AnimatorModel {
  private List<Shape> shapeList;
  private int canvasW;
  private int canvasH;

  /**
   * Constructor for a SimpleAnimatorModel that starts with no shapes.
   * specify canvas height and width in constructor.
   */
  public SimpleAnimatorModel(int canvasW, int canvasH) {
    if (canvasW < 1 || canvasH < 1) {
      throw new IllegalArgumentException("canvas dimensions must be greater than zero.");
    }
    shapeList = new ArrayList<>();
    this.canvasH = canvasH;
    this.canvasW = canvasW;
  }


  @Override
  public void addShape(Shape shape) {
    if (shape == null) {
      throw new IllegalArgumentException("Cannot add a null shape.");
    }
    String name = shape.getName();
    for (Shape other : shapeList) {
      if (name.equals(other.getName())) {
        throw new IllegalArgumentException("There is already a shape named '"
                + name + "' in the list. Please use distinct names.");
      }
    }
    shapeList.add(shape);
  }

  @Override
  public void removeShape(Shape shape) {
    if (shapeList.contains(shape)) {
      shapeList.remove(shape);
    } else {
      throw new IllegalArgumentException("Shape was not found.");
    }
  }

  @Override
  public List<Shape> getShapes() {
    return new ArrayList<>(shapeList);
  }

  @Override
  public void addMutationTo(Mutation m, Shape s) {
    if (!shapeList.contains(s) || s == null) {
      throw new IllegalArgumentException("Invalid shape.");
    }
    if (m == null) {
      throw new IllegalArgumentException("Mutation cannot be null.");
    }
    s.addMutation(m);
  }

  @Override
  public void removeMutationFrom(Shape s) {
    s.removeMutation();
  }


  /**
   * toString begins by listing each shape's type and name.
   * then, go through each shape and describe where it ends up at each tick in the map of states.
   */
  @Override
  public String toString() {
    String result = "";
    for (Shape s : shapeList) {
      result += "Create " + s.getType()  + " " + s.getName() + "\n";
    }
    for (Shape s : shapeList) {
      result += s;
    }
    result += "\nFin.";
    return result;
  }

  @Override
  public int getCanvasW() {
    return canvasW;
  }

  @Override
  public int getCanvasH() {
    return canvasH;
  }

  @Override
  public int getLastTick() {
    List<Integer> lastTicks = new ArrayList<>();
    for (Shape s : shapeList) {
      lastTicks.add(s.getStates().lastKey());
    }
    return Collections.max(lastTicks);
  }
}
