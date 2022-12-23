import org.junit.Test;

import cs3500.animator.model.AnimatorModel;
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
import cs3500.animator.view.AnimatorView;

import static org.junit.Assert.assertEquals;

/**
 * Class representing tests for the TextAnimatorView.
 */
public class TextAnimatorViewTest {

  @Test
  public void testRender() {
    AnimatorModel model = new SimpleAnimatorModel(500, 500);
    Appendable ap = new StringBuilder("");
    AnimatorView view = new MockTextAnimatorView(ap, model);
    assertEquals("", ap.toString());
    MyColor red = new MyColor(255, 0, 0);
    Shape rect = new Rectangle("rect", 20, 30, new Posn(50, 50), red);
    model.addShape(rect);
    MyColor green = new MyColor(0, 255, 0);
    Shape oval = new Oval("oval", 40, 30, new Posn(100, 100), green);
    Mutation move = new Translation(new Posn(40, 40), 50, 250);
    Mutation resize = new Resize(30, 20, 150, 350);
    Mutation turnBlue =
            new ColorShift(new MyColor(0, 0, 255), 0, 350);
    model.addMutationTo(move, rect);
    model.addMutationTo(resize, rect);
    String expectedOut1 = "canvas: 500x500\n"
            + "new rectangle rect\n"
            + "From t=0.0 to t=50.0, move rect to:\n"
            + "Width: 20\n"
            + "Height: 30\n"
            + "Position: (50, 50)\n"
            + "Color: (255, 0, 0)\n"
            + "From t=50.0 to t=150.0, move rect to:\n"
            + "Width: 20\n"
            + "Height: 30\n"
            + "Position: (45, 45)\n"
            + "Color: (255, 0, 0)\n"
            + "From t=150.0 to t=250.0, move rect to:\n"
            + "Width: 25\n"
            + "Height: 25\n"
            + "Position: (40, 40)\n"
            + "Color: (255, 0, 0)\n"
            + "From t=250.0 to t=350.0, move rect to:\n"
            + "Width: 30\n"
            + "Height: 20\n"
            + "Position: (40, 40)\n"
            + "Color: (255, 0, 0)";
    view.render(1);
    assertEquals(expectedOut1, ap.toString());
  }

  @Test
  public void testGetViewString() {
    AnimatorModel model = new SimpleAnimatorModel(500, 500);
    Appendable ap = new StringBuilder("");
    AnimatorView view = new MockTextAnimatorView(ap, model);
    assertEquals("", ap.toString());
    MyColor red = new MyColor(255, 0, 0);
    Shape rect = new Rectangle("rect", 20, 30, new Posn(50, 50), red);
    model.addShape(rect);
    MyColor green = new MyColor(0, 255, 0);
    Shape oval = new Oval("oval", 40, 30, new Posn(100, 100), green);
    Mutation move = new Translation(new Posn(40, 40), 50, 250);
    Mutation resize = new Resize(30, 20, 150, 350);
    Mutation turnBlue =
            new ColorShift(new MyColor(0, 0, 255), 0, 350);
    model.addMutationTo(move, rect);
    model.addMutationTo(resize, rect);
    String expectedOut1 = "canvas: 500x500\n"
            + "new rectangle rect\n"
            + "From t=0.0 to t=50.0, move rect to:\n"
            + "Width: 20\n"
            + "Height: 30\n"
            + "Position: (50, 50)\n"
            + "Color: (255, 0, 0)\n"
            + "From t=50.0 to t=150.0, move rect to:\n"
            + "Width: 20\n"
            + "Height: 30\n"
            + "Position: (45, 45)\n"
            + "Color: (255, 0, 0)\n"
            + "From t=150.0 to t=250.0, move rect to:\n"
            + "Width: 25\n"
            + "Height: 25\n"
            + "Position: (40, 40)\n"
            + "Color: (255, 0, 0)\n"
            + "From t=250.0 to t=350.0, move rect to:\n"
            + "Width: 30\n"
            + "Height: 20\n"
            + "Position: (40, 40)\n"
            + "Color: (255, 0, 0)";
    view.render(1);
    assertEquals(expectedOut1, view.getViewString());
  }

}