package com.jkstudiogroup.pikachu.scene;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Align;
import com.jkstudiogroup.core.util.JLImage;
import com.jkstudiogroup.core.util.JLScreen;
import com.jkstudiogroup.core.util.JLStage;
import com.jkstudiogroup.pikachu.controller.BoardController;

public class Title extends JLScreen {
  private JLStage uiStage;

  public Title() {
    super("menu.atlas");
  }

  @Override
  public void show() {
    super.show();
    uiStage = addStage();
    uiStage.addActor(new JLImage(uiStage, "bg", 0, 0, Align.bottomLeft));
    BoardController controller = new BoardController(this);
    JLImage b = new JLImage(uiStage, "", 0,0,Align.bottomLeft);
  }
}
