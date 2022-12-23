package cs3500.animator.view;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;

/**
 * an extension of the Refresh ActionListener that supports looping playback.
 */
public class LoopRefresh extends Refresh {
  private InteractiveAnimatorView view;

  /**
   * constructor for a LoopRefresh that links it to a view with a JFrame.
   * @param jf    the given jFrame
   * @param panel the AnimatorPanel
   */
  public LoopRefresh(JFrame jf, AnimationPanel panel, InteractiveAnimatorView view) {
    super(jf, panel);
    if (view == null) {
      throw new IllegalArgumentException("view cannot be null.");
    }
    this.view = view;
  }

  @Override
  public void actionPerformed(ActionEvent ae) {
    frame.repaint();
    if (view.isLooping() && panel.getCurrentTick() >= view.getLastTick()) {
      panel.setCurrentTick(0);
    } else {
      panel.setCurrentTick(panel.getCurrentTick() + 1);
    }
  }
}
