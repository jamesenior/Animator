package cs3500.animator.model.shapes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import cs3500.animator.model.animations.ColorShift;
import cs3500.animator.model.animations.Mutation;
import cs3500.animator.model.animations.MutationType;
import cs3500.animator.model.animations.Resize;
import cs3500.animator.model.animations.Translation;
import cs3500.animator.model.tools.MyColor;
import cs3500.animator.model.tools.Posn;

/**
 * an abstract class for a Shape with width, height, x/y position, color.
 * a shape also contains a map of ticks where mutations start or end with their respective states.
 * the list of Mutations represents everything that will happen to it in an animation.
 * width and height both must be positive, non-zero ints.
 * pos can be negative (i.e. if an AbsShape should be outside the viewport).
 * color must be a valid {@link MyColor}.
 */
public abstract class AbsShape implements Shape {
  private final String name;
  private NavigableMap<Integer, ShapeState> states;
  private List<Mutation> mutations;


  /**
   * constructor for an AbsShape.
   * the map of states & list of mutations are initialized to empty, can be added to via add method.
   *
   * @param width  the initial width of this AbsShape
   * @param height the initial height of this AbsShape
   * @param pos    the initial Posn of this AbsShape
   * @param color  the initial color of this AbsShape
   */
  public AbsShape(String name, int width, int height, Posn pos, MyColor color) {
    if (name == null) {
      throw new IllegalArgumentException("Name cannot be null.");
    }
    this.name = name;
    states = new TreeMap<>();
    ShapeState initState = new ShapeStateImpl(width, height, pos, color);
    states.put(0, initState);
    mutations = new ArrayList<>();
  }

  @Override
  public void addMutation(Mutation m) {
    if (m == null) {
      throw new IllegalArgumentException("Mutation cannot be null.");
    }
    if (hasConflict(m)) {
      throw new IllegalArgumentException("Mutations of the same type cannot overlap.");
    }
    mutations.add(m);
    int startTick = m.getStartTick();
    int endTick = m.getEndTick();
    if (!states.containsKey(startTick)) {
      states.put(startTick, findStateAt(startTick));
    }
    ShapeState endState = findStateAt(endTick);
    MutationType type = m.getType();
    switch (type) {
      case TRANSLATION:
        Translation t = (Translation) m;
        endState.setPos(t.getPos());
        break;
      case COLOR_SHIFT:
        ColorShift cs = (ColorShift) m;
        endState.setColor(cs.getColor());
        break;
      case RESIZE:
        Resize r = (Resize) m;
        endState.setWidth(r.getWidth());
        endState.setHeight(r.getHeight());
        break;
      default:
        throw new IllegalStateException("this shouldn't happen");
    }
    states.put(endTick, endState);
    blendStates(type);
  }

  private void blendStates(MutationType type) {
    Stream<Mutation> mutationsOfTypeStream = mutations.stream().filter(m -> m.getType() == type);
    List<Mutation> mutationsOfType = mutationsOfTypeStream.collect(Collectors.toList());
    List<Integer> endTicks = new ArrayList<>();
    for (Mutation m : mutationsOfType) {
      int startTick = m.getStartTick();
      int endTick = m.getEndTick();
      endTicks.add(endTick);
      int currTick = states.higherKey(startTick);
      while (currTick < endTick) {
        ShapeState startState = states.get(startTick);
        ShapeState endState = states.get(endTick);
        ShapeState currState = states.get(currTick);
        switch (type) {
          case TRANSLATION:
            int x = findValueBetween(startTick, startState, endTick, endState, currTick, "x");
            int y = findValueBetween(startTick, startState, endTick, endState, currTick, "y");
            currState.setPos(new Posn(x, y));
            break;
          case RESIZE:
            int w = findValueBetween(startTick, startState, endTick, endState, currTick, "w");
            int h = findValueBetween(startTick, startState, endTick, endState, currTick, "h");
            currState.setWidth(w);
            currState.setHeight(h);
            break;
          case COLOR_SHIFT:
            int r = findValueBetween(startTick, startState, endTick, endState, currTick, "r");
            int g = findValueBetween(startTick, startState, endTick, endState, currTick, "g");
            int b = findValueBetween(startTick, startState, endTick, endState, currTick, "b");
            currState.setColor(new MyColor(r, g, b));
            break;
          default:
            throw new IllegalArgumentException("invalid type");
        }
        currTick = states.higherKey(currTick);
      }
    }
    fixStatesAfterLastTick(Collections.max(endTicks), type);
  }

  private void fixStatesAfterLastTick(int lastTick, MutationType type) {
    Integer nextTick = states.higherKey(lastTick);
    ShapeState lastState = states.get(lastTick);
    while (nextTick != null) {
      ShapeState nextState = states.get(nextTick);
      switch (type) {
        case TRANSLATION:
          Posn lastPos = lastState.getPos();
          nextState.setPos(lastPos);
          break;
        case RESIZE:
          int lastWidth = lastState.getWidth();
          int lastHeight = lastState.getHeight();
          nextState.setWidth(lastWidth);
          nextState.setHeight(lastHeight);
          break;
        case COLOR_SHIFT:
          MyColor lastColor = lastState.getColor();
          nextState.setColor(lastColor);
          break;
        default:
          throw new IllegalArgumentException("unsupported mutation.");
      }
      nextTick = states.higherKey(nextTick);
    }
  }

  private boolean hasConflict(Mutation m) {
    int startTick = m.getStartTick();
    int endTick = m.getEndTick();
    for (Mutation other : mutations) {
      if (m.isSameTypeAs(other)) {
        int otherStart = other.getStartTick();
        int otherEnd = other.getEndTick();
        if ((startTick > otherStart && startTick < otherEnd)
                || (startTick < otherStart && endTick > otherStart)
                || startTick == otherStart) {
          return true;
        }
      }
    }
    return false;
  }

  @Override
  public ShapeState findStateAt(int tick) {
    if (tick == 0) {
      return states.get(0);
    }
    if (states.containsKey(tick)) {
      return states.get(tick);
    }
    Integer prevTick = states.lowerKey(tick);
    ShapeState prevState = new ShapeStateImpl(states.lowerEntry(tick).getValue());
    Integer nextTick = states.higherKey(tick);
    if (nextTick == null) {
      return prevState;
    }
    ShapeState nextState = new ShapeStateImpl(states.higherEntry(tick).getValue());
    if (nextState.equals(prevState)) {
      return prevState;
    }
    int w = findValueBetween(prevTick, prevState, nextTick, nextState, tick, "w");
    int h = findValueBetween(prevTick, prevState, nextTick, nextState, tick, "h");
    int x = findValueBetween(prevTick, prevState, nextTick, nextState, tick, "x");
    int y = findValueBetween(prevTick, prevState, nextTick, nextState, tick, "y");
    Posn pos = new Posn(x, y);
    int r = findValueBetween(prevTick, prevState, nextTick, nextState, tick, "r");
    int g = findValueBetween(prevTick, prevState, nextTick, nextState, tick, "g");
    int b = findValueBetween(prevTick, prevState, nextTick, nextState, tick, "b");
    MyColor color = new MyColor(r, g, b);
    return new ShapeStateImpl(w, h, pos, color);
  }

  private static int findValueBetween(int prevTick, ShapeState prevState,
                                      int nextTick, ShapeState nextState,
                                      int tick, String val) {
    int prevVal;
    int nextVal;
    switch (val) {
      case "w":
        prevVal = prevState.getWidth();
        nextVal = nextState.getWidth();
        break;
      case "h":
        prevVal = prevState.getHeight();
        nextVal = nextState.getHeight();
        break;
      case "x":
        prevVal = prevState.getPos().getX();
        nextVal = nextState.getPos().getX();
        break;
      case "y":
        prevVal = prevState.getPos().getY();
        nextVal = nextState.getPos().getX();
        break;
      case "r":
        prevVal = prevState.getColor().getRed();
        nextVal = nextState.getColor().getRed();
        break;
      case "g":
        prevVal = prevState.getColor().getGreen();
        nextVal = nextState.getColor().getGreen();
        break;
      case "b":
        prevVal = prevState.getColor().getBlue();
        nextVal = nextState.getColor().getBlue();
        break;
      default:
        throw new IllegalArgumentException("Val must be a string containing one of:"
                + "w, h, x, y, r, g, or b.");
    }
    if (prevVal == nextVal) {
      return prevVal;
    }
    double valRange = nextVal - prevVal;
    double tickRange = nextTick - prevTick;
    double increment = valRange / tickRange;
    double result = (tick - prevTick) * increment + prevVal;
    return (int) result;
  }

  @Override
  public void removeMutation() {
    Mutation m = mutations.get(mutations.size() - 1);
    mutations.remove(m);
    int startTick = m.getStartTick();
    int endTick = m.getEndTick();
    ShapeState startState = states.get(startTick);
    ShapeState endState = states.get(endTick);
    switch (m.getType()) {
      case TRANSLATION:
        Posn startPos = startState.getPos();
        endState.setPos(startPos);
        states.put(endTick, endState);
        break;
      case RESIZE:
        int startWidth = startState.getWidth();
        int startHeight = startState.getHeight();
        endState.setWidth(startWidth);
        endState.setHeight(startHeight);
        states.put(endTick, endState);
        break;
      default:
        break;
    }
  }

  @Override
  public NavigableMap<Integer, ShapeState> getStates() {
    return new TreeMap<>(states);
  }

  @Override
  public List<Mutation> getMutations() {
    return new ArrayList<>(mutations);
  }

  @Override
  public abstract String getType();

  @Override
  public String getName() {
    return name;
  }

  /**
   * Format:                                                       .
   * "Initial state of [name]:                                     .
   * [state of shape at tick 0]                                   .
   * From tick [current tick] to tick [next tick], move [name] to:.
   * [state of shape at next tick]"                               .
   * (repeat above two lines until finished)                      .
   */
  @Override
  public String toString() {
    int thisTick = 0;
    String result = "\nInitial state of " + name + ":\n" + states.get(thisTick);
    while (states.higherKey(thisTick) != null) {
      int nextTick = states.higherKey(thisTick);
      ShapeState nextState = states.get(nextTick);
      result += "\nFrom tick " + thisTick + " to tick " + nextTick
              + ", move " + name + " to:\n" + nextState;
      thisTick = nextTick;
    }
    return result;
  }

  @Override
  public abstract String getSVGName();

  @Override
  public abstract String getSVGDimensions();

  @Override
  public abstract List<String> getDimensionLabels();

  @Override
  public abstract List<Double> getSVGPosns(int startTick, int endTick,
                                           NavigableMap<Integer, ShapeState> stateList);

  @Override
  public abstract List<Double> getSVGDims(int startTick, int endTick,
                                          NavigableMap<Integer, ShapeState> stateList);

  @Override
  public String getBeginStatementSVG(double begin, double dur,
                                     String att, Double in1, Double in2) {
    return "\n<animate attributeType=\"xml\""
            + " begin=\"" + begin + "ms\" dur=\""
            + dur + "ms\" attributeName=\""
            + att + "\" from=\"" + in1
            + "\" to=\"" + in2 + "\" fill=\"freeze\" />";
  }

  @Override
  public String getEndStatementSVG(double begin,
                                   String att, Double in1) {
    return "\n<animate attributeType=\"xml\""
            + " begin=\"" + begin + "ms\" dur=\"1ms\" attributeName=\""
            + att + "\" to=\"" + in1
            + "\" fill=\"remove\" />";
  }


  @Override
  public String getSVGTag(double tickRate) {
    NavigableMap<Integer, ShapeState> states = getStates();
    StringBuilder result = new StringBuilder();
    //do initial  tag
    result.append(String.format("\n<" + getSVGName() + " id=\""
            + getName() + "\" " + getSVGDimensions()
            + " fill=" + states.get(0).getColor().getSVGColor()
            + " visibility=\"visible\" >"));
    List<String> beginCommands = new ArrayList<>();
    List<String> endCommands = new ArrayList<>();
    List<Mutation> mutations = getMutations();
    List<String> attLabels = getDimensionLabels();
    //iterate through mutations
    for (Mutation m : mutations) {
      String att1;
      String att2;
      int startTick = m.getStartTick();
      int endTick = m.getEndTick();
      double begin = (m.getStartTick() / tickRate) * 1000;
      double end = (m.getEndTick() / tickRate) * 1000;
      double duration = end - begin;
      List<Double> svgPosns = getSVGPosns(startTick, endTick, states);
      List<Double> svgDims = getSVGDims(startTick, endTick, states);
      switch (m.getType()) {
        case RESIZE:
          att1 = attLabels.get(2);
          att2 = attLabels.get(3);
          // add begin animations
          beginCommands.add(
                  getBeginStatementSVG(begin, duration, att1, svgDims.get(0), svgDims.get(1)));
          beginCommands.add(
                  getBeginStatementSVG(begin, duration, att2, svgDims.get(2), svgDims.get(3)));
          // add animation ends
          endCommands.add(
                  getEndStatementSVG(begin, att1, svgDims.get(0)));
          endCommands.add(
                  getEndStatementSVG(begin, att2, svgDims.get(2)));
          break;
        case TRANSLATION:
          att1 = attLabels.get(0);
          att2 = attLabels.get(1);
          beginCommands.add(
                  getBeginStatementSVG(begin, duration, att1, svgPosns.get(0), svgPosns.get(1)));
          beginCommands.add(
                  getBeginStatementSVG(begin, duration, att2, svgPosns.get(2), svgPosns.get(3)));
          // add animation ends
          endCommands.add(
                  getEndStatementSVG(begin, att1, svgPosns.get(0)));
          endCommands.add(
                  getEndStatementSVG(begin, att2, svgPosns.get(2)));
          break;
        case COLOR_SHIFT:
          att1 = "fill";
          beginCommands.add("\n<animate attributeType=\"xml\"" +
                  " begin=\"" + begin + "ms\" dur=\""
                  + duration + "ms\" attributeName=\""
                  + att1 + "\" from=" + states.get(startTick).getColor().getSVGColor()
                  + " to=" + states.get(endTick).getColor().getSVGColor() + " fill=\"freeze\" />");
          endCommands.add("\n<animate attributeType=\"xml\"" +
                  " begin=\"" + begin + "ms\" dur=\"1ms\" attributeName=\""
                  + att1 + "\" to=" + states.get(startTick).getColor().getSVGColor()
                  + " fill=\"remove\" />");
          break;
        default:
          break;
      }
    }
    for (String s : beginCommands) {
      result.append(s);
    }
    for (String s : endCommands) {
      result.append(s);
    }
    result.append("</" + getSVGName() + ">");
    return result.toString();
  }
}
