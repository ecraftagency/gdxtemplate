package com.jkstudiogroup.core.util;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.jkstudiogroup.core.transition.JLTransition;

public abstract class JLGame extends Game {
  public static float SH;
  public static float SW;
  protected static AssetManager assetManager;

  private boolean useTransition = false;
  private JLTransition transition;

  private FrameBuffer currentFbo;
  private FrameBuffer nextFbo;
  private SpriteBatch batch;

  public JLGame(float SW, float SH) {
    JLGame.SH = SH;
    JLGame.SW = SW;
    assetManager = new AssetManager();
  }

  float getHeight() {
    return SH;
  }

  float getWidth() {
    return SW;
  }

  @Override
  public void create() {
    SH = SW* Gdx.graphics.getHeight()/Gdx.graphics.getWidth();
    loadAssets();
    assetManager.finishLoading();
    batch = new SpriteBatch();
  }

  @Override
  public void setScreen(Screen screen) {
    JLScreen jlScreen = (JLScreen)screen;
    jlScreen.initAssets();
    jlScreen.setGameInstance(this);
    super.setScreen(screen);
  }

  public void setScreen(Screen screen, JLTransition transition) {
    System.gc();
    if (getScreen() != null) {
      this.useTransition = true;
      this.transition = transition;

      currentFbo = new FrameBuffer(Pixmap.Format.RGB565, (int) SW, (int) SH, false);
      nextFbo = new FrameBuffer(Pixmap.Format.RGB565, (int) SW, (int) SH, false);
      currentFbo.begin();
      getScreen().render(0.0f);
      currentFbo.end();
    }
    setScreen(screen);
  }

  @Override
  public void render() {
    if (useTransition) {
      nextFbo.begin();
      getScreen().render(Gdx.graphics.getDeltaTime());
      nextFbo.end();
      useTransition = transition.render(batch, currentFbo.getColorBufferTexture(), nextFbo.getColorBufferTexture(), Gdx.graphics.getDeltaTime());
    }
    else {
      super.render();
    }
  }

  public abstract void loadAssets();
}