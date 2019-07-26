package com.jkstudiogroup.core.action;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;

public class ParabolAction extends TemporalAction {
  private float x0,y0,x1,y1;
  Vector2 side;
  public static ParabolAction add(Actor target, float toX, float toY, Vector2 side, float duration, Interpolation easing) {
    ParabolAction action = (ParabolAction) Actions.action(ParabolAction.class);
    action.x0 = target.getX();
    action.y0 = target.getY();
    action.x1 = toX;
    action.y1 = toY;
    action.setDuration(duration);
    action.setInterpolation(easing);
    action.side = side;

    return action;
  }

  @Override
  protected void update(float percent) {
    float expX = (side.x == 1) ? percent*percent : percent;
    float expY = (side.y == 1) ? percent*percent : percent;

    target.setPosition(x0 + (x1-x0)*expX, y0 + (y1-y0)*expY);
  }
}