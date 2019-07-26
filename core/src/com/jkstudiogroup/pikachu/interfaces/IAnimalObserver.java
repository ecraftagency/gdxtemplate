package com.jkstudiogroup.pikachu.interfaces;

public interface IAnimalObserver {
  public void destroy();
  public void select(boolean active);
  public void changePosition(int row, int col, int dr, int dc, Runnable onComplete);
  public void hint();
  public void changeTexture(int id);
  public void show(int row, int col);
}