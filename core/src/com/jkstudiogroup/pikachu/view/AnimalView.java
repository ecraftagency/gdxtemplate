package com.jkstudiogroup.pikachu.view;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.Align;
import com.jkstudiogroup.core.action.ArcAction;
import com.jkstudiogroup.core.util.JLGroup;
import com.jkstudiogroup.core.util.JLImage;
import com.jkstudiogroup.core.util.JLStage;
import com.jkstudiogroup.pikachu.interfaces.IAnimalObserver;

public class AnimalView extends JLGroup implements IAnimalObserver {
  private JLImage tile;
  private JLImage ani;
  private BoardView board;

  public AnimalView(JLStage stage, float x, float y, int id) {
    super(stage);
    tile = new JLImage(stage, "cucxilau", 0, 0, Align.bottomLeft);
    ani = new JLImage(stage, "" + id, 5, 22, Align.bottomLeft);
    this.addActor(tile);
    this.addActor(ani);
    this.board = (BoardView)stage;
    setPosition(x, y);
  }

  @Override
  public void destroy() {
    //this.getListeners().removeIndex(0);
    //this.setVisible(false);
    this.stage.getActors().removeValue(this, true);
  }

  @Override
  public void select(boolean active) {
    float scale = (active) ? 1.1f : 1f;
    setScale(scale);
  }

  @Override
  public void changePosition(int row, int col, int dr, int dc, Runnable onComplete) {
    float toX = board.x + col*BoardView.WIDTH;
    float toY = board.y + row*BoardView.HEIGHT;
    int z = getZIndex();
    int dz = dr*10 + dc;
    //addAction(ArcAction.add(this,toX, toY, 2f, Interpolation.slowFast));
    //stage.action(this, Actions.moveTo(toX, toY, 0.2f, Interpolation.slowFast), () -> {

    //});
  }

  @Override
  public void hint() {

  }

  @Override
  public void changeTexture(int id) {

  }

  @Override
  public void show(int row, int col) {

  }

  @Override
  public String toString() {
    return "z index: " + getZIndex();
  }
}