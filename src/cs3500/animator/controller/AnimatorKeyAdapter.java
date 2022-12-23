package cs3500.animator.controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * a KeyListener for an AnimatorController that handles key inputs.
 */
public class AnimatorKeyAdapter extends KeyAdapter {
  AnimatorController controller;

  /**
   * constructor for an AnimatorKeyAdapter.
   * links this adapter to the given controller.
   * @param controller the AnimatorController to pass key inputs to.
   */
  public AnimatorKeyAdapter(AnimatorController controller) {
    if (controller == null) {
      throw new IllegalArgumentException("controller cannot be null.");
    }
    this.controller = controller;
  }

  @Override
  public void keyTyped(KeyEvent e) {
    controller.handleKey(e.getKeyChar());
  }
}
