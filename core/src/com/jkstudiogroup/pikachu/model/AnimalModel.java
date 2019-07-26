package com.jkstudiogroup.pikachu.model;

import com.jkstudiogroup.pikachu.interfaces.IAnimalObserver;

public class AnimalModel {
  private int id;
  private int row;
  private int col;
  private IAnimalObserver observer;

  AnimalModel(int id, int row, int col, IAnimalObserver observer) {
    this.id = id;
    this.col = col;
    this.row = row;
    this.observer = observer;
  }

  void select(boolean active) {
    this.observer.select(active);
  }

  void updatePosition(int dr, int dc) {
    row += dr;
    col += dc;
    observer.changePosition(row, col, dr, dc, null);
  }

  void destroy() {
    this.observer.destroy();
  }

  public void addObserver(IAnimalObserver observer) {
    this.observer = observer;
  }

  public int getId() {
    return id;
  }

  int getRow() {
    return row;
  }

  int getCol() {
    return col;
  }
}