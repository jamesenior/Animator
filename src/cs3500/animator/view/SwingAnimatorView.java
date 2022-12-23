package cs3500.animator.view;

import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import cs3500.animator.model.ReadOnlyAnimatorModel;

/**
 * visual cs3500.animator.view of an animator cs3500.animator.model using Java Swing.
 */
public class SwingAnimatorView extends AbsAnimatorView {
  protected JFrame frame;

  /**
   * Constructor for a Swing Animator View. Initializes and Renders the view.
   * @param model the model to make the view for
   * @param tickRate the given tickRate
   */
  public SwingAnimatorView(ReadOnlyAnimatorModel model, double tickRate) {
    super(model);
    frame = new JFrame();
    initFrame();
    this.tickRate = tickRate;
    render(tickRate);
  }

  protected void initFrame() {
    frame.setTitle("Animation");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(model.getCanvasW(), model.getCanvasH());
    frame.setLocationRelativeTo(null);
  }

  @Override
  public void render(double tickRate) {
    AnimationPanel panel = new AnimationPanel(model);
    JScrollPane scrollPane = new JScrollPane(panel);
    frame.add(scrollPane);
    double freq = (1 / tickRate) * 1000;
    Timer timer = new Timer((int) freq, new Refresh(frame, panel));
    frame.setVisible(true);
    timer.start();
  }

  @Override
  public String getViewString() {
    throw new UnsupportedOperationException("No String for Swing Class.");
  }
}
