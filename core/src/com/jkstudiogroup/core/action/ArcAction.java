package com.jkstudiogroup.core.action;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;

public class ArcAction extends TemporalAction {
  private float degree,arc,radius;
  private Vector2 cp;

  public static ArcAction add(Vector2 centrePoint, float arc, float duration, Interpolation easing) {
    ArcAction action =(ArcAction) Actions.action(ArcAction.class);
    action.setDuration(duration);
    action.setInterpolation(easing);
    action.arc = arc;
    action.cp = centrePoint;
    return action;
  }

  @Override
  protected void begin() {
    float sx = target.getX();
    float sy = target.getY();
    Vector2 v = new Vector2(sx, sy);
    radius = cp.dst(v);
    Vector2 d = v.sub(cp);
    degree = MathUtils.atan2(d.y, d.x)*180/MathUtils.PI;
  }

  @Override
  protected void update(float percent) {
    float d = degree + arc*percent;
    float dx = MathUtils.cosDeg(d)*radius;
    float dy = MathUtils.sinDeg(d)*radius;
    target.setPosition(cp.x + dx, cp.y + dy);
  }
}