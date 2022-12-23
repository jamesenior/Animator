package cs3500.animator.model.shapes;

import java.util.ArrayList;
import java.util.List;
import java.util.NavigableMap;


import cs3500.animator.model.tools.MyColor;
import cs3500.animator.model.tools.Posn;

/**
 * class representing a Rectangle with an initial ShapeState.
 * can be animated by adding mutations.
 */
public class Rectangle extends AbsShape {
  /**
   * constructor for a Rectangle.
   * the map of states & list of mutations are initialized to empty, can be added to via add method.
   *
   * @param width  the initial width of this Rectangle
   * @param height the initial height of this Rectangle
   * @param pos    the initial Posn of this Rectangle
   * @param color  the initial color of this Rectangle
   */
  public Rectangle(String name, int width, int height, Posn pos, MyColor color) {
    super(name, width, height, pos, color);
  }

  @Override
  public String getType() {
    return "rectangle";
  }

  @Override
  public String getSVGName() {
    return "rect";
  }

  @Override
  public String getSVGDimensions() {
    NavigableMap<Integer, ShapeState> states = getStates();
    int xPos = states.get(0).getPos().getX()
            - (states.get(0).getWidth() / 2);
    int yPos = states.get(0).getPos().getY()
            - (states.get(0).getHeight() / 2);
    int height = getStates().get(0).getHeight();
    int width = getStates().get(0).getWidth();
    String result = String.format("x=\"%s\" y=\"%s\" width=\"%s\" height=\"%s\"",
            xPos, yPos, width, height);
    return result;
  }

  @Override
  public List<String> getDimensionLabels() {
    List<String> list = new ArrayList<>();
    list.add("x");
    list.add("y");
    list.add("width");
    list.add("height");
    return list;
  }


  @Override
  public List<Double> getSVGPosns(int startTick, int endTick,
                                  NavigableMap<Integer, ShapeState> stateList) {
    List<Double> list = new ArrayList<>();
    list.add(Double.valueOf(stateList.get(startTick).getPos().getX())
            - (stateList.get(startTick).getWidth() / 2));
    list.add(Double.valueOf(stateList.get(endTick).getPos().getX())
            - (stateList.get(endTick).getWidth() / 2));
    list.add(Double.valueOf(stateList.get(startTick).getPos().getY())
            - (stateList.get(startTick).getHeight() / 2));
    list.add(Double.valueOf(stateList.get(endTick).getPos().getY())
            - (stateList.get(endTick).getHeight() / 2));
    return list;
  }

  @Override
  public List<Double> getSVGDims(int startTick, int endTick,
                                 NavigableMap<Integer, ShapeState> stateList) {
    List<Double> list = new ArrayList<>();
    list.add(Double.valueOf(stateList.get(startTick).getWidth()));
    list.add(Double.valueOf(stateList.get(endTick).getWidth()));
    list.add(Double.valueOf(stateList.get(startTick).getHeight()));
    list.add(Double.valueOf(stateList.get(endTick).getHeight()));
    return list;
  }
}
