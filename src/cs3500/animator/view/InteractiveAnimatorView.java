package cs3500.animator.view;

import java.awt.event.KeyListener;

/**
 * an Interface representing an AnimatorView that supports user interaction during playback.
 * View can be paused, restarted, set to loop, slowed down, or sped up.
 */
public interface InteractiveAnimatorView extends AnimatorView {
  /**
   * add a key listener to this view to link it to a controller.
   * @param listener the listener to add
   */
  void addListener(KeyListener listener);

  /**
   * pauses or plays this view based on which state it is in.
   */
  void togglePause();

  /**
   * restart this view from tick 0.
   */
  void restart();

  /**
   * turn looping off and on.
   * when looping is on, the animation begins again at 0 when it reaches the end.
   */
  void toggleLoop();

  /**
   * set the tickRate of this view to the given tickRate.
   * @param newTickRate the new tick rate
   */
  void changeTickRate(double newTickRate);

  /**
   * set the AnimationSpeed of this view.
   * @param speed the AnimationSpeed to set this view to
   */
  void setSpeed(AnimationSpeed speed);

  /**
   * make this view visible.
   */
  void makeVisible();

  /**
   * return whether this view is currently playing an animation.
   */
  boolean isPlaying();

  /**
   * return whether this view is currently set to loop back.
   */
  boolean isLooping();

  /**
   * get the current AnimationSpeed of this view.
   * @return the current AnimationSpeed
   */
  AnimationSpeed getSpeed();

  /**
   * get the final tick of this animation.
   * @return the final tick
   */
  int getLastTick();

  /**
   * refresh this view by one tick.
   */
  void refreshOnce();
}
