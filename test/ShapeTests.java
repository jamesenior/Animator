import org.junit.Before;
import org.junit.Test;

import java.util.NavigableMap;

import cs3500.animator.model.animations.Mutation;
import cs3500.animator.model.animations.Resize;
import cs3500.animator.model.animations.Translation;
import cs3500.animator.model.shapes.Oval;
import cs3500.animator.model.shapes.Rectangle;
import cs3500.animator.model.shapes.Shape;
import cs3500.animator.model.shapes.ShapeState;
import cs3500.animator.model.shapes.ShapeStateImpl;
import cs3500.animator.model.tools.MyColor;
import cs3500.animator.model.tools.Posn;

import static org.junit.Assert.assertEquals;

/**
 * class representing tests for our Shape classes and its methods.
 */
public class ShapeTests {
  private Shape rect1;
  private Shape oval1;
  private MyColor red;
  private Mutation move;
  private Mutation resize;

  @Before
  public void setup() {
    red = new MyColor(255, 0, 0);
    MyColor green = new MyColor(0, 255, 0);
    rect1 = new Rectangle("rect1", 20, 30, new Posn(10, 10), red);
    oval1 = new Oval("oval1", 40, 30, new Posn(100, 100), green);
    move = new Translation(new Posn(30, 30), 10, 30);
    resize = new Resize(40, 50, 20, 40);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullName() {
    setup();
    new Oval(null, 1, 2, new Posn(0, 0), red);
  }

  @Test
  public void testConstruct() {
    setup();
    ShapeState initState = new ShapeStateImpl(20, 30, new Posn(10, 10), red);
    NavigableMap<Integer, ShapeState> stateMap = rect1.getStates();
    assertEquals(initState, stateMap.get(0));
  }

  @Test (expected = IllegalArgumentException.class)
  public void testAddNullMutation() {
    setup();
    rect1.addMutation(null);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testAddMutationsConflict() {
    setup();
    rect1.addMutation(move);
    Mutation move2 = new Translation(new Posn(200, 3), 20, 80);
    rect1.addMutation(move2);
  }

  @Test
  public void testAddMutations() {
    setup();
    rect1.addMutation(move);
    rect1.addMutation(resize);
    NavigableMap<Integer, ShapeState> stateMap = rect1.getStates();
    ShapeState expectedStateAt10 = new ShapeStateImpl(20, 30,
            new Posn(10, 10), red);
    ShapeState expectedStateAt20 = new ShapeStateImpl(20, 30,
            new Posn(20, 20), red);
    ShapeState expectedStateAt30 = new ShapeStateImpl(30, 40,
            new Posn(30, 30), red);
    ShapeState expectedStateAt40 = new ShapeStateImpl(40, 50,
            new Posn(30, 30), red);
    assertEquals(expectedStateAt10, stateMap.get(10));
    assertEquals(expectedStateAt20, stateMap.get(20));
    assertEquals(expectedStateAt30, stateMap.get(30));
    assertEquals(expectedStateAt40, stateMap.get(40));
  }

  @Test
  public void testGetShapeType() {
    assertEquals("oval", oval1.getType());
    assertEquals("rectangle", rect1.getType());
  }

  @Test
  public void testToString() {
    setup();
    rect1.addMutation(move);
    String expectedOutput = "\nInitial state of rect1:\n"
            + "Width: 20\n"
            + "Height: 30\n"
            + "Position: (10, 10)\n"
            + "Color: (255, 0, 0)\n"
            + "From tick 0 to tick 10, move rect1 to:\n"
            + "Width: 20\n"
            + "Height: 30\n"
            + "Position: (10, 10)\n"
            + "Color: (255, 0, 0)\n"
            + "From tick 10 to tick 30, move rect1 to:\n"
            + "Width: 20\n"
            + "Height: 30\n"
            + "Position: (30, 30)\n"
            + "Color: (255, 0, 0)";
    assertEquals(expectedOutput, rect1.toString());
  }
}
