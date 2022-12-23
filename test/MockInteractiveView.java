
import java.awt.event.KeyListener;
import java.io.IOException;

import cs3500.animator.view.AnimationSpeed;
import cs3500.animator.view.InteractiveAnimatorView;

/**
 * a mock interactive view that takes in an Appendable log.
 * appends to the log when its methods are called.
 */
public class MockInteractiveView implements InteractiveAnimatorView {
  private Appendable log;
  private double tickRate;

  /**
   * a constructor for the MockInteractiveView class.
   * takes in an Appendable log and an initial tickRate.
   * @param tickRate initial tick rate
   * @param ap appendable to be used as the log
   */
  public MockInteractiveView(double tickRate, Appendable ap) {
    log = ap;
    this.tickRate = tickRate;
  }

  @Override
  public void addListener(KeyListener listener) {
    try {
      log.append("added key listener\n");
    } catch (IOException ioe) {
      throw new IllegalArgumentException("this shouldn't happen.");
    }
  }

  @Override
  public void makeVisible() {
    try {
      log.append("made visible\n");
    } catch (IOException ioe) {
      throw new IllegalArgumentException("this shouldn't happen.");
    }
  }

  @Override
  public void render(double tickRate) {
    try {
      log.append("rendered\n");
    } catch (IOException ioe) {
      throw new IllegalArgumentException("this shouldn't happen.");
    }
  }

  @Override
  public String getViewString() {
    return null;
  }

  @Override
  public double getTickRate() {
    return tickRate;
  }

  @Override
  public boolean isPlaying() {
    return false;
  }

  @Override
  public boolean isLooping() {
    return false;
  }

  @Override
  public AnimationSpeed getSpeed() {
    return AnimationSpeed.NORMAL;
  }

  @Override
  public int getLastTick() {
    return 0;
  }

  @Override
  public void refreshOnce() {
    try {
      log.append("refreshed once\n");
    } catch (IOException ioe) {
      throw new IllegalArgumentException("this shouldn't happen.");
    }
  }

  @Override
  public void togglePause() {
    try {
      log.append("pause toggled\n");
    } catch (IOException ioe) {
      throw new IllegalArgumentException("this shouldn't happen.");
    }
  }

  @Override
  public void restart() {
    try {
      log.append("restarted\n");
    } catch (IOException ioe) {
      throw new IllegalArgumentException("this shouldn't happen.");
    }
  }

  @Override
  public void toggleLoop() {
    try {
      log.append("loop toggled\n");
    } catch (IOException ioe) {
      throw new IllegalArgumentException("this shouldn't happen.");
    }
  }

  @Override
  public void changeTickRate(double newTickRate) {
    try {
      log.append("tick rate changed to " + newTickRate + "\n");
    } catch (IOException ioe) {
      throw new IllegalArgumentException("this shouldn't happen.");
    }
  }

  @Override
  public void setSpeed(AnimationSpeed speed) {
    try {
      log.append("speed set to " + speed + "\n");
    } catch (IOException ioe) {
      throw new IllegalArgumentException("this shouldn't happen.");
    }
  }
}
