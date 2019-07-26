package com.jkstudiogroup.core.transition;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Interpolation;
import com.jkstudiogroup.core.util.JLGame;

public class FadeTransition extends JLTransition{
  private static FadeTransition instance = new FadeTransition();
  private int height = (int)JLGame.SH;
  private int width = (int)JLGame.SW;

  public static FadeTransition init(float duration) {
    instance.duration = instance.timeToTarget =  duration;
    return instance;
  }
  @Override
  public boolean render(Batch batch, Texture currentFbo, Texture nextFbo, float delta) {
    if (timeToTarget > 0) {
      timeToTarget -= delta;
      float mProgress = timeToTarget < 0 ? 1 : 1f - timeToTarget/duration;
      float alpha = Interpolation.fade.apply(0, 1, mProgress);

      batch.begin();
      batch.setColor(1.0F, 1.0F, 1.0F, 1);
      batch.draw(currentFbo, 0, 0, 0f, 0f, width, height, 1f, 1f,0f, 0, 0, width, height, false, true);
      batch.setColor(1.0F, 1.0F, 1.0F, alpha);
      batch.draw(nextFbo, 0, 0, 0f, 0f, width, height, 1f, 1f,0f, 0, 0, width, height, false, true);
      batch.end();

      return mProgress != 1;
    }
    return false;
  }
}
