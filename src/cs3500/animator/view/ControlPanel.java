package cs3500.animator.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

/**
 * A JPanel with the controls for an InteractiveAnimatorView drawn onto it.
 * controls that can be turned on and off are green when on, red when off.
 */
public class ControlPanel extends JPanel {
  private final int w;
  private final int h;
  private final int textRow1;
  private final int textRow2;
  private InteractiveAnimatorView view;
  private static Color myGreen = new Color(0, 150, 0);

  /**
   * constructor for control panel that takes in the width and height of the panel.
   * also takes its associated view.
   * @param w width of the panel
   * @param h height of the panel
   * @param view the view associated with this control panel
   */
  public ControlPanel(int w, int h, InteractiveAnimatorView view) {
    this.w = w;
    this.h = h;
    textRow1 = h / 3;
    textRow2 = h / 3 * 2 + 7;
    this.view = view;
  }

  @Override
  public void paintComponent(Graphics g0) {
    super.paintComponent(g0);
    Graphics2D g = (Graphics2D) g0;
    g.setColor(Color.BLACK);
    g.drawRect(0, 0, w, h);
    g.setColor(getTextColor(view.isPlaying()));
    g.drawString("Play/Pause (P)", 15, textRow1);
    g.setColor(myGreen);
    g.drawString("Restart (R)", 115, textRow1);
    g.setColor(getTextColor(view.isLooping()));
    g.drawString("Loop (L)", 215, textRow1);
    g.setColor(getSpeedColor(AnimationSpeed.SLOW));
    g.drawString("Speed 0.5 (Q)", 15, textRow2);
    g.setColor(getSpeedColor(AnimationSpeed.NORMAL));
    g.drawString("Speed 1x (W)", 115, textRow2);
    g.setColor(getSpeedColor(AnimationSpeed.FAST));
    g.drawString("Speed 2x (E)", 215, textRow2);
  }

  private Color getTextColor(boolean b) {
    Color color = (b) ? myGreen : Color.RED;
    return color;
  }

  private Color getSpeedColor(AnimationSpeed speed) {
    Color color = (speed == view.getSpeed()) ? myGreen : Color.RED;
    return color;
  }
}
