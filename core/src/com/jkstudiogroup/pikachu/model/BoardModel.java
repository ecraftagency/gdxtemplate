package com.jkstudiogroup.pikachu.model;

import com.badlogic.gdx.utils.Array;
import com.jkstudiogroup.pikachu.interfaces.IBoardObserver;
import com.jkstudiogroup.pikachu.model.util.D;
import com.jkstudiogroup.pikachu.model.util.Path;
import com.jkstudiogroup.pikachu.model.util.PathFinder;
import com.jkstudiogroup.pikachu.model.util.Point;
import com.jkstudiogroup.pikachu.model.util.Slice;
import com.jkstudiogroup.pikachu.model.util.Tuple;

import java.util.ArrayList;

public class BoardModel {
  private int row;
  private int col;
  private int numPair;
  private AnimalModel[][] amodels;
  private IBoardObserver observer;
  private AnimalModel selected = null;

  private static BoardModel instance = new BoardModel();

  public static BoardModel init(int row, int col, int numPair, IBoardObserver observer) {
    instance.row = row;
    instance.col = col;
    instance.amodels = new AnimalModel[row][col];
    instance.observer = observer;
    instance.numPair = numPair;
    instance.generateAnimals();
    instance.selected = null;
    return instance;
  }

  public static BoardModel load(String jsonSerialize) {
    return  null;
  }

  private void generateAnimals() {
    Array<Integer> indexList = initBoard();
    int num = 0;

    for (int i = row - 1; i >= 0; i--)
      for (int j = 0; j < col; j++) {
        amodels[i][j] = new AnimalModel(indexList.get(num++), i, j, null);
        observer.addAnimal(i, j, amodels[i][j]);
      }
  }

  public void animalSelected(AnimalModel animal) {
    if (selected == null) {
      selected = animal;
      animal.select(true);
    }
    else {
      if (selected.getId() == animal.getId() && (selected.getRow() != animal.getRow() || selected.getCol() != animal.getCol())) {
        ArrayList<Path> paths;
        if ( (paths = match(selected, animal)) != null) {
          int sr = selected.getRow();
          int sc = selected.getCol();
          int ar = animal.getRow();
          int ac = animal.getCol();

          amodels[selected.getRow()][selected.getCol()] = null;
          amodels[animal.getRow()][animal.getCol()] = null;
          selected.destroy();
          animal.destroy();
          selected = null;
          slice(sc, 1);
          slice(ac, 1);
        }
        else {
          selected.select(false);
          animal.select(true);
          selected = animal;
        }
      }
      else {
        selected.select(false);
        animal.select(true);
        selected = animal;
      }
    }
  }

  private ArrayList<Path> match(AnimalModel a1, AnimalModel a2) {
    PathFinder<AnimalModel> pf = new PathFinder<>(amodels, row, col);
    Point p1 = new Point(a1.getRow(), a1.getCol());
    Point p2 = new Point(a2.getRow(), a2.getCol());
    return (pf.findPath(p1, p2)) ? pf.path : null;
  }

  private void slice(int rc, int anchor) {
    ArrayList<Tuple<AnimalModel, D>> output = new ArrayList<>();
    Slice.nullSliceV(amodels, output, anchor, rc);
    for (int i = output.size() - 1; i >=0; i--) {
      AnimalModel a = output.get(i).obj;
      D d = output.get(i).delta;
      amodels[a.getRow()][a.getCol()] = null;
      a.updatePosition(d.dr, d.dc);
      amodels[a.getRow()][a.getCol()] = a;
    }
  }

  private Array<Integer> initBoard() {
    int size = col*row;
    int tmpIndex = 0;
    Array<Integer> indexList = new Array<Integer>();
    for(int i=0;i<size/2;i++){
      indexList.add(tmpIndex);
      indexList.add(tmpIndex);
      tmpIndex++;
      if(tmpIndex>=numPair)
        tmpIndex=0;
    }
    indexList.shuffle();
    return indexList;
  }
}
