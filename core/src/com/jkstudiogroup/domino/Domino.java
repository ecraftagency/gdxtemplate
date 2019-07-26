package com.jkstudiogroup.domino;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.jkstudiogroup.core.util.JLGame;
import com.jkstudiogroup.domino.scene.Play;
import com.jkstudiogroup.domino.scene.Title;

public class Domino extends JLGame {
  public Domino() {
    super(1280, 720);
  }

  @Override
  public void create() {
    super.create();
    setScreen(new Title());
  }

  @Override
  public void loadAssets() {
    assetManager.load("start.atlas", TextureAtlas.class);
    assetManager.load("game.atlas", TextureAtlas.class);

  }
}