package cs3500.animator.view;





import java.awt.Graphics;

import java.awt.Graphics2D;

import java.awt.Color;

import javax.swing.JPanel;

import cs3500.animator.model.ReadOnlyAnimatorModel;
import cs3500.animator.model.shapes.Shape;
import cs3500.animator.model.shapes.ShapeState;
import cs3500.animator.model.tools.MyColor;
import cs3500.animator.model.tools.Posn;

/**
 * Class representing a JPanel that draws a ReadOnlyAnimatorModel.
 */
public class AnimationPanel extends JPanel {
  private ReadOnlyAnimatorModel model;
  private int currentTick;

  /**
   * Constructor for an animation panel that sets the
   * model to m and the current tick to 0.
   * @param m the given model
   */
  public AnimationPanel(ReadOnlyAnimatorModel m) {
    if (m == null) {
      throw new IllegalArgumentException("shape cannot be null.");
    }
    model = m;
    currentTick = 0;
  }

  /**
   * set the current tick to the given integer.
   */
  public void setCurrentTick(int tick) {
    currentTick = tick;
  }

  /**
   * get the current tick.
   */
  public int getCurrentTick() {
    return currentTick;
  }

  @Override
  public void paintComponent(Graphics g0) {
    super.paintComponent(g0);
    Graphics2D g = (Graphics2D) g0;
    for (Shape shape : model.getShapes()) {
      ShapeState state = shape.findStateAt(currentTick);
      Posn pos = state.getPos();
      int w = state.getWidth();
      int h = state.getHeight();
      int x = pos.getX();
      int y = pos.getY();
      MyColor c = state.getColor();
      Color color = new Color(c.getRed(), c.getGreen(), c.getBlue());
      g.setColor(color);
      switch (shape.getType()) {
        case "oval":
          g.fillOval(x, y, w, h);
          break;
        case "rectangle":
          g.fillRect(x, y, w, h);
          break;
        default:
          throw new IllegalStateException("shape type not supported.");
      }
    }
  }
}
