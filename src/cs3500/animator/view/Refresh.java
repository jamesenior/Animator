package cs3500.animator.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;



/**
 * Class that refreshes the JFrame. Stores the frame as field.
 */
public class Refresh implements ActionListener {
  protected JFrame frame;
  protected AnimationPanel panel;

  /**
   * Constructor for the class
   * that takes a JFrame snd stores it.
   * @param jf the given jFrame
   */
  public Refresh(JFrame jf, AnimationPanel panel) {
    if (jf == null) {
      throw new IllegalArgumentException("JFrame cannot be null.");
    }
    if (panel == null) {
      throw new IllegalArgumentException("Panel cannot be null.");
    }
    this.panel = panel;
    frame = jf;
  }

  @Override
  public void actionPerformed(ActionEvent ae) {
    frame.repaint();
    panel.setCurrentTick(panel.getCurrentTick() + 1);
  }
}
