import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.ReadOnlyAnimatorModel;
import cs3500.animator.model.SimpleAnimatorModel;
import cs3500.animator.model.animations.ColorShift;
import cs3500.animator.model.animations.Mutation;
import cs3500.animator.model.animations.Resize;
import cs3500.animator.model.animations.Translation;
import cs3500.animator.model.shapes.Oval;
import cs3500.animator.model.shapes.Rectangle;
import cs3500.animator.model.shapes.Shape;
import cs3500.animator.model.tools.MyColor;
import cs3500.animator.model.tools.Posn;

import static org.junit.Assert.assertEquals;

/**
 * a JUnit test class for the SimpleAnimatorModel class.
 */
public class SimpleAnimatorModelTest {
  private Shape rect1;
  private Shape rect1a;
  private Shape oval1;
  private Mutation move;
  private AnimatorModel model;
  private Mutation colorShift;

  @Before
  public void setup() {
    MyColor red = new MyColor(255, 0, 0);
    rect1 = new Rectangle("rect1", 20, 30, new Posn(10, 10), red);
    rect1a = new Rectangle("rect1", 70, 30, new Posn(10, 10), red);
    move = new Translation(new Posn(30, 30), 10, 30);
    Mutation resize = new Resize(40, 50, 20, 40);
    model = new SimpleAnimatorModel(500, 500);
    MyColor green = new MyColor(0, 255, 0);
    rect1 = new Rectangle("rect1", 20, 30, new Posn(10, 10), red);
    oval1 = new Oval("oval1", 40, 30, new Posn(100, 100), green);
    colorShift = new ColorShift(red, 70, 100);
    rect1.addMutation(move);
    rect1.addMutation(resize);
    model.addShape(rect1);
  }

  @Test
  public void testAddShape() {
    List<Shape> shapes = model.getShapes();
    assertEquals(1, shapes.size());
    assertEquals(rect1, shapes.get(0));
  }

  @Test (expected = IllegalArgumentException.class)
  public void testAddDupeName() {
    setup();
    model.addShape(rect1a);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testRemoveFakeShape() {
    setup();
    model.removeShape(rect1a);
  }

  @Test
  public void testNoShapesGetList() {
    ReadOnlyAnimatorModel model1 = new SimpleAnimatorModel(500, 500);
    assertEquals(new ArrayList<Shape>(), model1.getShapes());
  }

  @Test
  public void testRemoveShape() {
    setup();
    model.removeShape(rect1);
    assertEquals("\nFin.", model.toString());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testAddNullMutation() {
    model.addMutationTo(null, rect1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testAddToNullShape() {
    model.addMutationTo(move, null);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testAddToBadShape() {
    model.addMutationTo(move, rect1a);
  }

  @Test
  public void testToString() {
    setup();
    oval1.addMutation(colorShift);
    model.addShape(oval1);
    String expectedOutput = "Create rectangle rect1\n"
            + "Create oval oval1\n\n"
            + "Initial state of rect1:\n"
            + "Width: 20\n"
            + "Height: 30\n"
            + "Position: (10, 10)\n"
            + "Color: (255, 0, 0)\n"
            + "From tick 0 to tick 10, move rect1 to:\n"
            + "Width: 20\n"
            + "Height: 30\n"
            + "Position: (10, 10)\n"
            + "Color: (255, 0, 0)\n"
            + "From tick 10 to tick 20, move rect1 to:\n"
            + "Width: 20\n"
            + "Height: 30\n"
            + "Position: (20, 20)\n"
            + "Color: (255, 0, 0)\n"
            + "From tick 20 to tick 30, move rect1 to:\n"
            + "Width: 30\n"
            + "Height: 40\n"
            + "Position: (30, 30)\n"
            + "Color: (255, 0, 0)\n"
            + "From tick 30 to tick 40, move rect1 to:\n"
            + "Width: 40\n"
            + "Height: 50\n"
            + "Position: (30, 30)\n"
            + "Color: (255, 0, 0)\n"
            + "Initial state of oval1:\n"
            + "Width: 40\n"
            + "Height: 30\n"
            + "Position: (100, 100)\n"
            + "Color: (0, 255, 0)\n"
            + "From tick 0 to tick 70, move oval1 to:\n"
            + "Width: 40\n"
            + "Height: 30\n"
            + "Position: (100, 100)\n"
            + "Color: (0, 255, 0)\n"
            + "From tick 70 to tick 100, move oval1 to:\n"
            + "Width: 40\n"
            + "Height: 30\n"
            + "Position: (100, 100)\n"
            + "Color: (255, 0, 0)\n"
            + "Fin.";
    assertEquals(expectedOutput, model.toString());
  }
}