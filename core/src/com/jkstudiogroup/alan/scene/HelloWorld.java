package com.jkstudiogroup.alan.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Align;
import com.jkstudiogroup.core.transition.FadeTransition;
import com.jkstudiogroup.core.transition.SliceTransition;
import com.jkstudiogroup.core.util.JLImage;
import com.jkstudiogroup.core.util.JLScreen;
import com.jkstudiogroup.core.util.JLStage;

public class HelloWorld extends JLScreen {
  private JLStage uiStage;
  public HelloWorld() {
    super("hello.atlas");
  }

  @Override
  public void show() {
    super.show();
    uiStage = addStage();
    uiStage.addActor(new JLImage(uiStage, "bg", 0, 0, Align.bottomLeft));

    JLImage btn = new JLImage(uiStage, "cucxilau", 100, 100, Align.bottomLeft);
    uiStage.addActor(btn);
    btn.setTouchable(Touchable.enabled);
    btn.onClick = this::click;
  }

  private void click(Actor actor) {
    game.setScreen(new AlanScreen(), FadeTransition.init(1f));
  }
}
