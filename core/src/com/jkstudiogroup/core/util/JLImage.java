package com.jkstudiogroup.core.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class JLImage extends Image {
  protected JLStage stage;
  protected TextureAtlas textureAtlas;
  public ClickInterface onClick = null;

  public JLImage(JLStage stage, String key, float x, float y, int align) {
    super(stage.textureAtlas.findRegion(key));
    this.stage = stage;
    this.textureAtlas = stage.getTextureAtlas();
    setPosition(x, y, align);
    setOrigin(align);
    setTouchable(Touchable.disabled);
    addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        if (onClick != null)
          JLImage.this.onClick.handler(JLImage.this);
      }
    });
  }

  public void setTexture(String key) {
    if (textureAtlas != null) {
      TextureAtlas.AtlasRegion region = textureAtlas.findRegion(key);
      setDrawable(new TextureRegionDrawable(textureAtlas.findRegion(key)));
      setWidth(region.getRegionWidth());
      setHeight(region.getRegionHeight());
    }
    else
      Gdx.app.log(this.toString(), "fail to load texture");
  }

  @FunctionalInterface
  public interface ClickInterface {
    void handler(Actor actor);
  }
}