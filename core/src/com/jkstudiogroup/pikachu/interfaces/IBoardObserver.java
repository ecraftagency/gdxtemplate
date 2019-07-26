package com.jkstudiogroup.pikachu.interfaces;

import com.jkstudiogroup.pikachu.model.AnimalModel;

public interface IBoardObserver {
  public void addAnimal(int row, int col, AnimalModel aModel);
}
