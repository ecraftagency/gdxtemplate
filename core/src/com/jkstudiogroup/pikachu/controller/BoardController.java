package com.jkstudiogroup.pikachu.controller;

import com.badlogic.gdx.Gdx;
import com.jkstudiogroup.core.util.JLGame;
import com.jkstudiogroup.core.util.JLScreen;
import com.jkstudiogroup.pikachu.interfaces.IBoardEvent;
import com.jkstudiogroup.pikachu.model.AnimalModel;
import com.jkstudiogroup.pikachu.model.BoardModel;
import com.jkstudiogroup.pikachu.view.BoardView;

public class BoardController implements IBoardEvent {
  BoardModel bModel = null;
  BoardView bView = null;

  public BoardController(JLScreen screen) {
    this.bView = new BoardView(screen, (JLGame.SW - BoardView.WIDTH*10)/2, (JLGame.SH - 60 - BoardView.HEIGHT*6)/2, this);
    screen.addStage(this.bView);
    screen.setInput(this.bView);
    bModel = BoardModel.init(6, 10, 1, this.bView);
  }

  @Override
  public void AnimalSelect(AnimalModel animal) {
    bModel.animalSelected(animal);
    Gdx.app.log("selected", "" + animal.getId());
  }
}