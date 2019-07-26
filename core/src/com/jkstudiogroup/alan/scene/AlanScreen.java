package com.jkstudiogroup.alan.scene;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Align;
import com.jkstudiogroup.core.action.SimpleAction;
import com.jkstudiogroup.core.util.JLImage;
import com.jkstudiogroup.core.util.JLScreen;
import com.jkstudiogroup.core.util.JLStage;

public class AlanScreen extends JLScreen {
  private JLStage uiStage;

  private Vector2 vtgai = new Vector2(400, 400);
  private Vector2 vBob = new Vector2(15, 215);

  private float cuongdoVantoc = 60;
  private Vector2 huongtuBobtoiGai = vtgai.sub(vBob).nor().scl(60);
  //.scl(cuongdoVantoc);




  private float cuongdocvantocGio = 30;
  private Vector2 vantocGio = new Vector2(1,-1).scl(cuongdocvantocGio);

  public AlanScreen() {
    super("alan.atlas");
  }

  @Override
  public void show() {
    super.show();
    uiStage = addStage();
    uiStage.addActor(new JLImage(uiStage, "bg", 0, 0, Align.bottomLeft));

    JLImage btn = new JLImage(uiStage, "cucxilau", 15, 215, Align.bottomLeft);
    btn.setTouchable(Touchable.enabled);
    uiStage.addActor(btn);

    btn.onClick = this::handler;
  }

  private void handler(Actor actor) {
    actor.addAction(SimpleAction.add((a, dt)-> {
      //Vector2 vantocCuoiCung = new Vector2(vantocBanDau).add(vantocGio);
      float x = actor.getX();
      float y = actor.getY();

      x += huongtuBobtoiGai.x*dt;
      y += huongtuBobtoiGai.y*dt;

      actor.setPosition(x, y);
      return false;
    }));
  }
}
