package cs3500.animator.io;

import java.util.ArrayList;
import java.util.List;

import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.SimpleAnimatorModel;
import cs3500.animator.model.animations.ColorShift;
import cs3500.animator.model.animations.Resize;
import cs3500.animator.model.animations.Translation;
import cs3500.animator.model.shapes.Oval;
import cs3500.animator.model.shapes.Rectangle;
import cs3500.animator.model.shapes.Shape;
import cs3500.animator.model.tools.MyColor;
import cs3500.animator.model.tools.Posn;

/**
 * builder to translate an AnimationFileReader into a SimpleAnimatorModel.
 * canvas height and width default to 500px, can be changed via setBounds method.
 */
public class AnimationBuilder implements TweenModelBuilder<AnimatorModel> {
  private int canvasW;
  private int canvasH;
  private List<Shape> shapes;

  /**
   * constructor for the animation builder. sets Canvas size.
   */
  public AnimationBuilder() {
    canvasH = 500;
    canvasW = 500;
    shapes = new ArrayList<>();
  }

  @Override
  public TweenModelBuilder<AnimatorModel> setBounds(int width, int height) {
    canvasW = width;
    canvasH = height;
    return this;
  }

  @Override
  public TweenModelBuilder<AnimatorModel> addOval(String name, float cx, float cy, float xRadius,
                                                  float yRadius, float red, float green, float blue,
                                                  int startOfLife, int endOfLife) {
    int w = (int) xRadius * 2;
    int h = (int) yRadius * 2;
    int x = (int) cx;
    int y = (int) cy;
    int r = (int) red;
    int g = (int) green;
    int b = (int) blue;
    shapes.add(new Oval(name, w, h, new Posn(x, y), new MyColor(r, g, b)));
    return this;
  }

  @Override
  public TweenModelBuilder<AnimatorModel> addRectangle(String name, float lx, float ly, float width,
                                                       float height, float red, float green,
                                                       float blue, int startOfLife, int endOfLife) {
    int w = (int) width;
    int h = (int) height;
    int x = (int) lx + (w / 2);
    int y = (int) ly + (h / 2);
    int r = (int) red;
    int g = (int) green;
    int b = (int) blue;
    shapes.add(new Rectangle(name, w, h, new Posn(x, y), new MyColor(r, g, b)));
    return this;
  }

  @Override
  public TweenModelBuilder<AnimatorModel> addMove(String name, float moveFromX, float moveFromY,
                                                  float moveToX, float moveToY, int startTime,
                                                  int endTime) {
    for (Shape s : shapes) {
      if (s.getName().equals(name)) {
        int x = (int) moveToX;
        int y = (int) moveToY;
        s.addMutation(new Translation(new Posn(x, y), startTime, endTime));
        return this;
      }
    }
    throw new IllegalArgumentException("Shape " + name + " does not exist.");
  }

  @Override
  public TweenModelBuilder<AnimatorModel> addColorChange(String name, float oldR, float oldG,
                                                         float oldB, float newR, float newG,
                                                         float newB, int startTime, int endTime) {
    for (Shape s : shapes) {
      if (s.getName().equals(name)) {
        int r = (int) newR;
        int g = (int) newG;
        int b = (int) newB;
        s.addMutation(new ColorShift(new MyColor(r, g, b), startTime, endTime));
        return this;
      }
    }
    throw new IllegalArgumentException("Shape " + name + " does not exist.");
  }

  @Override
  public TweenModelBuilder<AnimatorModel> addScaleToChange(String name, float fromSx, float fromSy,
                                                           float toSx, float toSy, int startTime,
                                                           int endTime) {
    for (Shape s : shapes) {
      if (s.getName().equals(name)) {
        int w = (int) toSx;
        int h = (int) toSy;
        s.addMutation(new Resize(w, h, startTime, endTime));
        return this;
      }
    }
    throw new IllegalArgumentException("Shape " + name + " does not exist.");
  }

  @Override
  public AnimatorModel build() {
    AnimatorModel model = new SimpleAnimatorModel(canvasW, canvasH);
    for (Shape s : shapes) {
      model.addShape(s);
    }
    return model;
  }
}
