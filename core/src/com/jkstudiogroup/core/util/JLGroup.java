package com.jkstudiogroup.core.util;

import com.badlogic.gdx.scenes.scene2d.Group;

public class JLGroup extends Group {
  protected JLStage stage;

  public JLGroup(JLStage stage) {
    this.stage = stage;
    //stage.addActor(this);
  }
}