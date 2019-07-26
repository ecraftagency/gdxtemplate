package com.jkstudiogroup.domino.scene;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Align;
import com.jkstudiogroup.core.transition.SliceTransition;
import com.jkstudiogroup.core.util.JLImage;
import com.jkstudiogroup.core.util.JLScreen;
import com.jkstudiogroup.core.util.JLStage;

public class Title extends JLScreen {
  JLStage uiStage;

  public Title() {
    super("start.atlas");
  }

  @Override
  public void show() {
    uiStage = this.addStage();
    uiStage.addActor(new JLImage(uiStage, "bg", 0, 0, Align.bottomLeft));
    JLImage btn = new JLImage(uiStage, "cucxilau", 200, 200, Align.bottomLeft);
    uiStage.addActor(btn);
    btn.setTouchable(Touchable.enabled);
    btn.onClick = (a) -> game.setScreen(new Play(), SliceTransition.init(0.5f, new Vector2(1,1), Interpolation.smooth2));
    super.show();
  }
}
