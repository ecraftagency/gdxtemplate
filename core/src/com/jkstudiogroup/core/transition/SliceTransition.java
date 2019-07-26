package com.jkstudiogroup.core.transition;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.jkstudiogroup.core.util.JLGame;

public class SliceTransition extends JLTransition{
  private static final SliceTransition instance = new SliceTransition();
  private Vector2 target;
  private Vector2 origin;
  private Interpolation easing;
  private int width;
  private int height;

  public static SliceTransition init(float duration, Vector2 sliceDirection, Interpolation easing) {
    instance.duration = instance.timeToTarget = duration;
    instance.target = new Vector2(0,0);
    instance.origin = new Vector2(sliceDirection.x* JLGame.SW, sliceDirection.y*JLGame.SH);
    instance.easing = easing;
    instance.width = (int)JLGame.SW;
    instance.height = (int)JLGame.SH;
    return instance;
  }

  @Override
  public boolean render(Batch batch, Texture currentFbo, Texture nextFbo, float delta) {
    if (timeToTarget > 0) {
      timeToTarget -= delta;
      float mProgress = timeToTarget < 0 ? 1 : 1f - timeToTarget/duration;
      float toX = easing.apply(origin.x, target.x, mProgress);
      float toY = easing.apply(origin.y, target.y, mProgress);

      batch.begin();
      batch.draw(nextFbo, toX, toY, 0f, 0f, width, height, 1f, 1f,0f, 0, 0, width, height, false, true);
      batch.end();

      return mProgress != 1;
    }
    return false;
  }
}