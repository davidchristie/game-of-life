package gameoflife;

import java.awt.Point;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class InfiniteCellGrid implements CellGrid {

  private class Cell {
    
    private Point point;

    public Cell(int x, int y) {
      point = new Point(x, y);
    }

    public int getX() {
      return point.x;
    }

    public int getY() {
      return point.y;
    }

    @Override
    public boolean equals(Object object) {
      if (object instanceof Cell) {
        Cell cell = (Cell) object;
        return point.equals(cell.point);
      }
      return false;
    }

    @Override
    public int hashCode() {
      return point.hashCode();
    }

  }

  private Set<Cell> livingCells = new HashSet<>();

  @Override
  public boolean isCellAlive(int x, int y) {
    Cell cell = new Cell(x, y);
    return livingCells.contains(cell);
  }

  @Override
  public void setCellAlive(int x, int y, boolean alive) {
    Cell cell = new Cell(x, y);
    if (alive) {
      livingCells.add(cell);
    } else {
      livingCells.remove(cell);
    }
  }

  @Override
  public void nextIteration() {
    Set<Cell> newLivingCells = new HashSet<>();
    getLivingNeighbourMap().entrySet().stream().forEach(entry -> {
      Cell cell = entry.getKey();
      int livingNeighbourCount = entry.getValue();
      boolean isCurrentlyAlive = livingCells.contains(cell);
      if (isCellAliveNextIteration(isCurrentlyAlive, livingNeighbourCount)) {
        newLivingCells.add(cell);
      }
    });
    livingCells = newLivingCells;
  }

  private Map<Cell, Integer> getLivingNeighbourMap() {
    Map<Cell, Integer> livingNeighbourMap = new HashMap<>();
    livingCells.stream().forEach(livingCell -> {
      getNeighbouringCells(livingCell).stream().forEach(neighbour -> {
        if (livingNeighbourMap.containsKey(neighbour)) {
          livingNeighbourMap.put(
            neighbour,
            livingNeighbourMap.get(neighbour) + 1
          );
        } else {
          livingNeighbourMap.put(neighbour, 1);
        }
      });
    });
    return livingNeighbourMap;
  }

  private List<Cell> getNeighbouringCells(Cell cell) {
    List<Cell> neighbours = new LinkedList<>();
    for (int i = -1; i <= 1; i++) {
      for (int j = -1; j <= 1; j++) {
        if (!(i == 0 && j == 0)) {
          neighbours.add(new Cell(cell.getX() + i, cell.getY() + j));
        }
      }
    }
    return neighbours;
  }

  private boolean isCellAliveNextIteration(
    boolean isCurrentlyAlive,
    int livingNeighbourCount
  ) {
    if (isCurrentlyAlive) {
      if (livingNeighbourCount >= 2 && livingNeighbourCount <= 3) {
        return true;
      }
    } else {
      if (livingNeighbourCount == 3) {
        return true;
      }
    }
    return false;
  }

}
