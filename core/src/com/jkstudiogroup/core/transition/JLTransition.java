package com.jkstudiogroup.core.transition;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public abstract class JLTransition {
  float duration;
  float timeToTarget;
  public abstract boolean render(Batch batch, Texture currentFbo, Texture nextFbo, float delta);
}