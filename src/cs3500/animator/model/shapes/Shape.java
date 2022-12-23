package cs3500.animator.model.shapes;

import java.util.List;
import java.util.NavigableMap;

import cs3500.animator.model.animations.Mutation;


/**
 * this interface represents a 2D geometric shape that can be animated based on a {@link Mutation}.
 * a Shape has a width, height, x/y position, and color.
 * a Shape contains a list of Mutations representing its animation.
 */
public interface Shape {

  /**
   * adds the given mutation to this shape's list of Mutations.
   * adds entries into the state map for the start and end of the Mutation.
   * if the Mutation is added around any other entries in the map, it updates those entries.
   *
   * @param m the Mutation to be added
   */
  public void addMutation(Mutation m);

  /**
   * removes the most recently added mutation from this shape's list of Mutations.
   */
  void removeMutation();

  /**
   * Method to retrieve a shape state at the given tick.
   *
   * @param tick the tick of the shape state
   * @return the Shape state at the given tick
   */
  public ShapeState findStateAt(int tick);


  /**
   * get the map of ticks and states for this Shape.
   */
  NavigableMap<Integer, ShapeState> getStates();

  /**
   * get the list of Mutations for this Shape.
   */
  List<Mutation> getMutations();

  /**
   * returns the type of shape this shape is as a String.
   */
  String getType();

  /**
   * returns the name of this Shape.
   */
  String getName();

  /**
   * gets the SVG tag for the whole shape. Iterates through its mutations to do so.
   */
  String getSVGTag(double tickRate);

  /**
   * gets the type of shape as a String for SVG shape names.
   */
  String getSVGName();

  /**
   * gets the SVG Dimensions for the given shape. Helps build the String for the initial
   * state of the shape.
   */
  String getSVGDimensions();

  /**
   * gets the labels for Dimensions of a Shape for SVG. Changes depending on shape.
   * (for example rx, ry vs width, height).
   *
   * @return a list of labels as Strings
   */
  List<String> getDimensionLabels();

  /**
   * gets the positions of a Shape State for SVG.
   *
   * @return a list of positions
   */
  List<Double> getSVGPosns(int startTick, int endTick,
                           NavigableMap<Integer, ShapeState> stateList);

  /**
   * gets the dimensions of a Shape State for SVG. Divides 2 for radius of ovals.
   *
   * @return a list of dimensions
   */
  List<Double> getSVGDims(int startTick, int endTick,
                          NavigableMap<Integer, ShapeState> stateList);


  /**
   * gets the String to begin an animation with a shape in SVG.
   *
   * @return a String to begin an animation
   */
  String getBeginStatementSVG(double begin, double dur,
                              String att, Double in1, Double in2);

  /**
   * gets the String to end an animation with a shape in SVG.
   *
   * @return a String to end an animation
   */
  String getEndStatementSVG(double begin,
                            String att, Double in1);
}
