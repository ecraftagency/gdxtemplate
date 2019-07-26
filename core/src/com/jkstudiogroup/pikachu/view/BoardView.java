package com.jkstudiogroup.pikachu.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.jkstudiogroup.core.util.JLScreen;
import com.jkstudiogroup.core.util.JLStage;
import com.jkstudiogroup.pikachu.interfaces.IBoardEvent;
import com.jkstudiogroup.pikachu.interfaces.IBoardObserver;
import com.jkstudiogroup.pikachu.model.AnimalModel;
import com.jkstudiogroup.pikachu.model.BoardModel;

public class BoardView extends JLStage implements IBoardObserver {
  float x;
  float y;
  public static final int WIDTH = 58;
  public static final int HEIGHT = 68;
  private IBoardEvent eventListener;
  public BoardView(JLScreen screen, float x, float y, IBoardEvent eventListener) {
    super(screen);
    this.eventListener = eventListener;
    this.x = x;
    this.y = y;
  }

  @Override
  public void addAnimal(int row, int col,final AnimalModel aModel) {
    float x = this.x + col*WIDTH;
    float y = this.y + row*HEIGHT;
    AnimalView animal = new AnimalView(this, x, y, aModel.getId());
    addActor(animal);
    aModel.addObserver(animal);
    animal.addListener(new ClickListener(){
      @Override
      public void clicked(InputEvent event, float x, float y) {
        BoardView.this.eventListener.AnimalSelect(aModel);
        super.clicked(event, x, y);
      }
    });
  }
}