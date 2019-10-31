package gameoflife;

public interface CellGrid {

  public boolean isCellAlive(int x, int y);

  public void setCellAlive(int x, int y, boolean alive);

  public void nextIteration();

}
