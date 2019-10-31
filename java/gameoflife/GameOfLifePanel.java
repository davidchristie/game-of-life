package gameoflife;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JPanel;

public class GameOfLifePanel extends JPanel {

  private static final int ITERATION_PERIOD = 100;
  private static final String BUTTON_START_TEXT = "Start";
  private static final String BUTTON_STOP_TEXT = "Stop";

	private static final long serialVersionUID = 1L;

  private JButton startStopButton = new JButton(BUTTON_START_TEXT);
  private CellGridPanel cellGridPanel = new CellGridPanel(new InfiniteCellGrid());
  private Timer timer = null;

  public GameOfLifePanel() {
    setLayout(new BorderLayout());
    add(BorderLayout.NORTH, startStopButton);
    add(BorderLayout.CENTER, cellGridPanel);
    startStopButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent event) {
        if (isRunning()) {
          stop();
          startStopButton.setText(BUTTON_START_TEXT);
        } else {
          start();
          startStopButton.setText(BUTTON_STOP_TEXT);
        }
      }
    });
  }

  private boolean isRunning() {
    return timer != null;
  }

  private void start() {
    timer = new Timer();
    timer.schedule(new TimerTask(){
      @Override
      public void run() {
        cellGridPanel.nextIteration();
      }
    }, 0, ITERATION_PERIOD);
  }

  private void stop() {
    if (isRunning()) {
      timer.cancel();
      timer = null;
    }
  }
}
