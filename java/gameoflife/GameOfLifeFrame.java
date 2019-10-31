package gameoflife;

import javax.swing.JFrame;

public class GameOfLifeFrame extends JFrame {

	private static final long serialVersionUID = 1L;

  public GameOfLifeFrame() {
    setTitle("Game of Life");
    setLocationRelativeTo(null);
    add(new GameOfLifePanel());
  }
}
