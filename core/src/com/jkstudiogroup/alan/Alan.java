package com.jkstudiogroup.alan;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.jkstudiogroup.alan.scene.AlanScreen;
import com.jkstudiogroup.alan.scene.HelloWorld;
import com.jkstudiogroup.core.util.JLGame;

public class Alan extends JLGame {
  public Alan() {
    super(1280, 720);

  }

  @Override
  public void create() {
    super.create();
    setScreen(new AlanScreen());
  }

  @Override
  public void loadAssets() {
    assetManager.load("hello.atlas", TextureAtlas.class);
    assetManager.load("alan.atlas", TextureAtlas.class);
  }
}
