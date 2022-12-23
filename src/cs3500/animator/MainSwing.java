package cs3500.animator;

import cs3500.animator.controller.AnimatorController;
import cs3500.animator.controller.InteractiveAnimatorController;
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

/**
 * an alternate main class used to visually test the interactive view and controller.
 * this is not our main main class.
 */
public class MainSwing {
  /**
   * run this main testing class.
   */
  public static void main(String[] args) {
    MyColor red = new MyColor(255, 0, 0);
    Shape rect1 = new Rectangle("rect1", 20, 30, new Posn(10, 10), red);
    Mutation move = new Translation(new Posn(30, 30), 10, 50);
    Mutation resize = new Resize(40, 50, 30, 100);
    AnimatorModel model = new SimpleAnimatorModel(301, 200);
    MyColor green = new MyColor(0, 255, 0);
    Shape oval1 = new Oval("oval1", 40, 30, new Posn(100, 100), green);
    Mutation colorShift = new ColorShift(new MyColor(0, 0, 255), 70, 100);
    rect1.addMutation(move);
    rect1.addMutation(resize);
    model.addShape(rect1);
    model.addShape(oval1);
    model.addMutationTo(colorShift, oval1);
    AnimatorController controller = new InteractiveAnimatorController(model);
    controller.startAnimation(50);
  }
}
