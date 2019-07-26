package com.jkstudiogroup.domino.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;
import com.jkstudiogroup.core.action.ArcAction;
import com.jkstudiogroup.core.util.JLImage;
import com.jkstudiogroup.core.util.JLScreen;
import com.jkstudiogroup.core.util.JLStage;

public class Play extends JLScreen {
  JLStage uiStage;
  public Play() {
    super("game.atlas");
  }

  @Override
  public void show() {
    super.show();
    uiStage = addStage();
    JLImage btn = new JLImage(uiStage, "cucxilau", 500, 500, Align.bottomLeft);
    uiStage.addActor(new JLImage(uiStage, "bg", 0, 0, Align.bottomLeft));
    uiStage.addActor(btn);
    btn.onClick = this::click;
  }

  private void click(Actor actor) {
    actor.addAction(ArcAction.add(new Vector2(0,0), 360, 1.5f, Interpolation.swingIn));
    //btn.addAction(ParabolAction.add(btn, 400, 400, new Vector2(1,0), 0.5f, Interpolation.smoother));
    //actor.addAction(SimpleAction.add(this::handler));
  }

  private boolean handler(Actor actor, float delta) {
    Gdx.app.log("hello: ", "lambda");
    return false;
  }
}