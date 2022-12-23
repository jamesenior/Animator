package cs3500.animator.controller;

/**
 * interface for a controller for an AnimatorModel.
 * can start an animation by creating a view and handle key inputs if it is interactive.
 */
public interface AnimatorController {
  /**
   * begins the animation by creating a view and rendering it at the given tickRate.
   * @param tickRate the initial tickRate to render the animation at
   */
  void startAnimation(double tickRate);

  /**
   * handles key inputs if necessary.
   * if this is not a controller that supports an interactive view, this method may be left blank.
   * @param key the character associated with the key input
   */
  void handleKey(char key);
}
