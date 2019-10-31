package gameoflife;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.util.Objects;
import java.awt.event.MouseAdapter;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class CellGridPanel extends JPanel {

  private static final int CELL_SIZE = 10;

  private static final long serialVersionUID = 1L;
  
  private CellGrid cellGrid;

  public CellGridPanel(CellGrid cellGrid) {
    Objects.requireNonNull(cellGrid);
    this.cellGrid = cellGrid;
    setPreferredSize(new Dimension(500, 500));
    addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent event) {
        if (event.getButton() == MouseEvent.BUTTON1) {
          handleMouseClick(event.getX(), event.getY());
        }
      }
    });
    addComponentListener(new ComponentAdapter() {
      @Override
      public void componentResized(ComponentEvent event) {
        repaint();
      }
    });
  }

  public void nextIteration() {
    cellGrid.nextIteration();
    repaint();
  }

  protected void paintComponent(Graphics graphics) {
    super.paintComponent(graphics);
    renderCellGrid(graphics);
  }

  private void handleMouseClick(int x, int y) {
    toggleCell(x / CELL_SIZE, y / CELL_SIZE);
    repaint();
  }

  private void renderCellGrid(Graphics graphics) {
    Dimension panelSize = this.getSize();
    for (int x = 0; x * CELL_SIZE < panelSize.getWidth() ; x++) {
      for (int y = 0; y * CELL_SIZE < panelSize.getHeight(); y++) {
        final boolean isCellAlive = cellGrid.isCellAlive(x, y);
        graphics.setColor(isCellAlive ? Color.BLACK : Color.WHITE);
        graphics.fillRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
        graphics.setColor(Color.LIGHT_GRAY);
        graphics.drawRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
      }
    }
  }

  private void toggleCell(int x, int y) {
    cellGrid.setCellAlive(x, y, !cellGrid.isCellAlive(x, y));
  }
}
