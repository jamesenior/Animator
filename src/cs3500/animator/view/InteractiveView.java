package cs3500.animator.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.Timer;

import cs3500.animator.model.ReadOnlyAnimatorModel;

/**
 * an implementation of the InteractiveAnimatorView interface.
 * relies on a swing timer to refresh the animation and change speeds.
 */
public class InteractiveView extends SwingAnimatorView implements InteractiveAnimatorView {
  private Timer timer;
  private ActionListener refresh;
  private AnimationPanel panel;
  private static int cPanelHeight = 60;
  private static int cPanelWidth = 300;
  private boolean playing;
  private boolean looping;
  private boolean started;
  private AnimationSpeed speed = AnimationSpeed.NORMAL;

  /**
   * Constructor for a Swing Animator View. Initializes and Renders the view.
   *
   * @param model    the model to make the view for
   * @param tickRate the initial tickRate
   */
  public InteractiveView(ReadOnlyAnimatorModel model, double tickRate) {
    super(model, tickRate);
    playing = true;
    looping = true;
    started = true;

  }

  @Override
  protected void initFrame() {
    ControlPanel cPanel;
    super.initFrame();
    int canvasW = model.getCanvasW();
    int w = (canvasW <= cPanelWidth) ? cPanelWidth + 1 : canvasW;
    int h = model.getCanvasH() + cPanelHeight;
    frame.setSize(w, h);
    ControlPanel cPanel = new ControlPanel(cPanelWidth, cPanelHeight, this);
    panel = new AnimationPanel(model);
    frame.setLayout(new BorderLayout());
    frame.add(panel, BorderLayout.CENTER);
    cPanel.setPreferredSize(new Dimension(w, cPanelHeight));
    frame.add(cPanel, BorderLayout.SOUTH);
  }

  @Override
  public void addListener(KeyListener listener) {
    frame.addKeyListener(listener);
  }

  @Override
  public void makeVisible() {
    frame.setVisible(true);
  }

  @Override
  public void render(double tickRate) {
    double freq = (1 / tickRate) * 1000;
    refresh = new LoopRefresh(frame, panel, this);
    timer = new Timer((int) freq, refresh);
    if (!started || playing) {
      timer.start();
    }
  }

  @Override
  public boolean isPlaying() {
    return playing;
  }

  @Override
  public boolean isLooping() {
    return looping;
  }

  @Override
  public AnimationSpeed getSpeed() {
    return speed;
  }

  @Override
  public int getLastTick() {
    return model.getLastTick();
  }

  @Override
  public void refreshOnce() {
    refresh.actionPerformed(new ActionEvent(timer, 0, timer.getActionCommand()));
  }

  @Override
  public void togglePause() {
    if (playing) {
      timer.stop();
    } else {
      timer.start();
    }
    playing = !playing;
  }

  @Override
  public void restart() {
    panel.setCurrentTick(0);
  }

  @Override
  public void toggleLoop() {
    looping = !looping;
  }

  @Override
  public void changeTickRate(double newTickRate) {
    timer.stop();
    render(newTickRate);
  }

  @Override
  public void setSpeed(AnimationSpeed speed) {
    if (speed == null) {
      throw new IllegalArgumentException("speed cannot be null.");
    }
    this.speed = speed;
  }
}
