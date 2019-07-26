package com.jkstudiogroup.core.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class JLScreen implements Screen {
  protected JLGame game;
  private Array<JLStage> stages;
  private InputMultiplexer inpMultiplex;
  protected TextureAtlas textureAtlas = null;
  private String atlasPath = null;

  public JLScreen(String atlasPath) {
    this.atlasPath = atlasPath;
    this.initAssets();
    this.stages = new Array<JLStage>();
    this.inpMultiplex = new InputMultiplexer();
    Gdx.input.setInputProcessor(inpMultiplex);
  }

  protected void setGameInstance(JLGame game) {
    this.game = game;
  }

  public void addStage(JLStage stage) {
    stages.add(stage);
  }

  public JLStage addStage() {
    JLStage stage = new JLStage(this);
    stage.setViewport(new ExtendViewport(game.getWidth(), game.getHeight(), new OrthographicCamera()));
    addStage(stage);
    inpMultiplex.addProcessor(stage);
    return stage;
  }

  public void setInput(JLStage stage) {
    inpMultiplex.addProcessor(stage);
  }
  public void removeInput(JLStage stage) {
    if (inpMultiplex.getProcessors().contains(stage, true)){
      inpMultiplex.removeProcessor(stage);
    }
  }

  public TextureAtlas getTextureAtlas() {
    return this.textureAtlas;
  }

  public void initAssets() {
    if (atlasPath != null && textureAtlas == null) {
      this.textureAtlas = JLGame.assetManager.get(atlasPath, TextureAtlas.class);
      for (Texture t : textureAtlas.getTextures())
        t.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }
  }

  @Override
  public void show() {

  }

  public void backgroundRefresh(float delta) {
    Gdx.gl.glClearColor(1/255f, 78/255f, 121/255f, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
  }

  @Override
  public void render(float delta) {
    backgroundRefresh(delta);
    for (JLStage stage : stages){
      stage.act(delta);
      stage.draw();
    }
  }

  @Override
  public void resize(int width, int height) {
    for (JLStage stage : stages)
      stage.getViewport().update(width, height, true);
  }

  @Override
  public void pause() {

  }

  @Override
  public void resume() {

  }

  @Override
  public void hide() {

  }

  @Override
  public void dispose() {

  }
}
