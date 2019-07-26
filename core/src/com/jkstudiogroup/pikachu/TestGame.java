package com.jkstudiogroup.pikachu;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.jkstudiogroup.core.util.JLGame;
import com.jkstudiogroup.pikachu.scene.Title;

public class TestGame extends JLGame {
  public TestGame() {
    super(1280, 720);
  }

  @Override
  public void create() {
    super.create();
    setScreen(new Title());
  }

  @Override
  public void loadAssets() {
    assetManager.load("menu.atlas", TextureAtlas.class);
    assetManager.load("play.atlas", TextureAtlas.class);
  }
}