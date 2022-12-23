package cs3500.animator.model.shapes;

import java.util.ArrayList;
import java.util.List;
import java.util.NavigableMap;


import cs3500.animator.model.tools.MyColor;
import cs3500.animator.model.tools.Posn;

/**
 * class representing an Oval with an initial ShapeState.
 * can be animated by adding mutations.
 */
public class Oval extends AbsShape {
  /**
   * constructor for an Oval.
   * the map of states & list of mutations are initialized to empty, can be added to via add method.
   *
   * @param width  the initial width of this Oval
   * @param height the initial height of this Oval
   * @param pos    the initial Posn of this Oval
   * @param color  the initial color of this Oval
   */
  public Oval(String name, int width, int height, Posn pos, MyColor color) {
    super(name, width, height, pos, color);
  }

  @Override
  public String getType() {
    return "oval";
  }

  @Override
  public String getSVGName() {
    return "ellipse";
  }

  @Override
  public String getSVGDimensions() {
    int xPos = getStates().get(0).getPos().getX();
    int yPos = getStates().get(0).getPos().getY();
    int xRad = getStates().get(0).getHeight() / 2;
    int yRad = getStates().get(0).getWidth() / 2;
    String result = String.format("cx=\"%s\" cy=\"%s\" rx=\"%s\" ry=\"%s\"",
            xPos, yPos, xRad, yRad);
    return result;
  }

  @Override
  public List<String> getDimensionLabels() {
    List<String> list = new ArrayList<>();
    list.add("cx");
    list.add("cy");
    list.add("rx");
    list.add("ry");
    return list;
  }

  @Override
  public List<Double> getSVGPosns(int startTick, int endTick,
                                  NavigableMap<Integer, ShapeState> stateList) {
    List<Double> list = new ArrayList<>();
    list.add(Double.valueOf(stateList.get(startTick).getPos().getX()));
    list.add(Double.valueOf(stateList.get(endTick).getPos().getX()));
    list.add(Double.valueOf(stateList.get(startTick).getPos().getY()));
    list.add(Double.valueOf(stateList.get(endTick).getPos().getY()));
    return list;
  }

  @Override
  public List<Double> getSVGDims(int startTick, int endTick,
                                 NavigableMap<Integer, ShapeState> stateList) {
    List<Double> list = new ArrayList<>();
    list.add(Double.valueOf(stateList.get(startTick).getWidth() / 2));
    list.add(Double.valueOf(stateList.get(endTick).getWidth() / 2));
    list.add(Double.valueOf(stateList.get(startTick).getHeight() / 2));
    list.add(Double.valueOf(stateList.get(endTick).getHeight() / 2));
    return list;
  }
}
