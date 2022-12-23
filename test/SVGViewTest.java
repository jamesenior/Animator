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
 * class representing tests for the SVG cs3500.animator.view.
 */
public class SVGViewTest {

  @Test(expected = IllegalArgumentException.class)
  public void testRenderInvalidTickRate() {
    AnimatorModel model = new SimpleAnimatorModel(500, 500);
    Appendable ap = new StringBuilder("");
    AnimatorView view = new MockSVGAnimatorView(ap, model);
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
    view.render(-4);
  }

  @Test
  public void testRender() {
    AnimatorModel model = new SimpleAnimatorModel(500, 500);
    Appendable ap = new StringBuilder("");
    AnimatorView view = new MockSVGAnimatorView(ap, model);
    assertEquals("", ap.toString());
    MyColor red = new MyColor(255, 0, 0);
    MyColor blue = new MyColor(0, 0, 255);
    Shape rect = new Rectangle("R", 20, 30, new Posn(50, 50), red);
    Shape oval = new Oval("O", 10, 20, new Posn(100, 100), blue);
    model.addShape(rect);
    model.addShape(oval);
    Mutation move = new Translation(new Posn(40, 40), 8, 15);
    Mutation resize = new Resize(30, 20, 5, 10);
    Mutation move2 = new Translation(new Posn(80, 80), 5, 10);
    Mutation reColor = new ColorShift(
            new MyColor(0, 255, 0), 12, 15);
    model.addMutationTo(move, rect);
    model.addMutationTo(resize, rect);
    model.addMutationTo(reColor, oval);
    model.addMutationTo(move2, oval);
    String expectedOut1 = "<svg width=\"500\" height=\"500\" version=\"1.1\"" +
            " xmlns=\"http://www.w3.org/2000/svg\">\n" +
            "<rect id=\"R\" x=\"40\" y=\"35\" width=\"20\" height=\"30\"" +
            " fill=\"rgb(255,0,0)\" visibility=\"visible\" >\n" +
            "<animate attributeType=\"xml\" begin=\"4000.0ms\" dur=\"3500.0ms\"" +
            " attributeName=\"x\" from=\"37.0\" to=\"25.0\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"4000.0ms\" dur=\"3500.0ms\"" +
            " attributeName=\"y\" from=\"38.0\" to=\"30.0\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"2500.0ms\" dur=\"2500.0ms\"" +
            " attributeName=\"width\" from=\"20.0\" to=\"30.0\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"2500.0ms\" dur=\"2500.0ms\"" +
            " attributeName=\"height\" from=\"30.0\" to=\"20.0\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"4000.0ms\" dur=\"1ms\" " +
            "attributeName=\"x\" to=\"37.0\" fill=\"remove\" />\n" +
            "<animate attributeType=\"xml\" begin=\"4000.0ms\" dur=\"1ms\" " +
            "attributeName=\"y\" to=\"38.0\" fill=\"remove\" />\n" +
            "<animate attributeType=\"xml\" begin=\"2500.0ms\" dur=\"1ms\" " +
            "attributeName=\"width\" to=\"20.0\" fill=\"remove\" />\n" +
            "<animate attributeType=\"xml\" begin=\"2500.0ms\" dur=\"1ms\" " +
            "attributeName=\"height\" to=\"30.0\" fill=\"remove\" /></rect>\n" +
            "<ellipse id=\"O\" cx=\"100\" cy=\"100\" rx=\"10\" ry=\"5\" fill=\"" +
            "rgb(0,0,255)\" visibility=\"visible\" >\n" +
            "<animate attributeType=\"xml\" begin=\"6000.0ms\" dur=\"1500.0ms\" " +
            "attributeName=\"fill\" from=\"rgb(0,0,255)\" to=\"rgb(0,255,0)\"" +
            " fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"2500.0ms\" dur=\"2500.0ms\" " +
            "attributeName=\"cx\" from=\"100.0\" to=\"80.0\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"2500.0ms\" dur=\"2500.0ms\" " +
            "attributeName=\"cy\" from=\"100.0\" to=\"80.0\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"6000.0ms\" dur=\"1ms\" " +
            "attributeName=\"fill\" to=\"rgb(0,0,255)\" fill=\"remove\" />\n" +
            "<animate attributeType=\"xml\" begin=\"2500.0ms\" dur=\"1ms\" " +
            "attributeName=\"cx\" to=\"100.0\" fill=\"remove\" />\n" +
            "<animate attributeType=\"xml\" begin=\"2500.0ms\" dur=\"1ms\" " +
            "attributeName=\"cy\" to=\"100.0\" fill=\"remove\" /></ellipse>\n" +
            "</svg>";
    view.render(2);
    assertEquals(expectedOut1, ap.toString());
  }

  @Test
  public void testGetViewString() {
    AnimatorModel model = new SimpleAnimatorModel(500, 500);
    Appendable ap = new StringBuilder("");
    AnimatorView view = new MockSVGAnimatorView(ap, model);
    assertEquals("", ap.toString());
    MyColor red = new MyColor(255, 0, 0);
    MyColor blue = new MyColor(0, 0, 255);
    Shape rect = new Rectangle("R", 20, 30, new Posn(50, 50), red);
    Shape oval = new Oval("O", 10, 20, new Posn(100, 100), blue);
    model.addShape(rect);
    model.addShape(oval);
    Mutation move = new Translation(new Posn(40, 40), 8, 15);
    Mutation resize = new Resize(30, 20, 5, 10);
    Mutation move2 = new Translation(new Posn(80, 80), 5, 10);
    Mutation reColor = new ColorShift(
            new MyColor(0, 255, 0), 12, 15);
    model.addMutationTo(move, rect);
    model.addMutationTo(resize, rect);
    model.addMutationTo(reColor, oval);
    model.addMutationTo(move2, oval);
    view.render(2);
    String expectedOut1 = "<svg width=\"500\" height=\"500\" version=\"1.1\"" +
            " xmlns=\"http://www.w3.org/2000/svg\">\n" +
            "<rect id=\"R\" x=\"40\" y=\"35\" width=\"20\" height=\"30\"" +
            " fill=\"rgb(255,0,0)\" visibility=\"visible\" >\n" +
            "<animate attributeType=\"xml\" begin=\"4000.0ms\" dur=\"3500.0ms\"" +
            " attributeName=\"x\" from=\"37.0\" to=\"25.0\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"4000.0ms\" dur=\"3500.0ms\"" +
            " attributeName=\"y\" from=\"38.0\" to=\"30.0\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"2500.0ms\" dur=\"2500.0ms\"" +
            " attributeName=\"width\" from=\"20.0\" to=\"30.0\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"2500.0ms\" dur=\"2500.0ms\"" +
            " attributeName=\"height\" from=\"30.0\" to=\"20.0\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"4000.0ms\" dur=\"1ms\" " +
            "attributeName=\"x\" to=\"37.0\" fill=\"remove\" />\n" +
            "<animate attributeType=\"xml\" begin=\"4000.0ms\" dur=\"1ms\" " +
            "attributeName=\"y\" to=\"38.0\" fill=\"remove\" />\n" +
            "<animate attributeType=\"xml\" begin=\"2500.0ms\" dur=\"1ms\" " +
            "attributeName=\"width\" to=\"20.0\" fill=\"remove\" />\n" +
            "<animate attributeType=\"xml\" begin=\"2500.0ms\" dur=\"1ms\" " +
            "attributeName=\"height\" to=\"30.0\" fill=\"remove\" /></rect>\n" +
            "<ellipse id=\"O\" cx=\"100\" cy=\"100\" rx=\"10\" ry=\"5\" fill=\"" +
            "rgb(0,0,255)\" visibility=\"visible\" >\n" +
            "<animate attributeType=\"xml\" begin=\"6000.0ms\" dur=\"1500.0ms\" " +
            "attributeName=\"fill\" from=\"rgb(0,0,255)\" to=\"rgb(0,255,0)\"" +
            " fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"2500.0ms\" dur=\"2500.0ms\" " +
            "attributeName=\"cx\" from=\"100.0\" to=\"80.0\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"2500.0ms\" dur=\"2500.0ms\" " +
            "attributeName=\"cy\" from=\"100.0\" to=\"80.0\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"6000.0ms\" dur=\"1ms\" " +
            "attributeName=\"fill\" to=\"rgb(0,0,255)\" fill=\"remove\" />\n" +
            "<animate attributeType=\"xml\" begin=\"2500.0ms\" dur=\"1ms\" " +
            "attributeName=\"cx\" to=\"100.0\" fill=\"remove\" />\n" +
            "<animate attributeType=\"xml\" begin=\"2500.0ms\" dur=\"1ms\" " +
            "attributeName=\"cy\" to=\"100.0\" fill=\"remove\" /></ellipse>\n" +
            "</svg>";
    assertEquals(expectedOut1, view.getViewString());
  }
}
