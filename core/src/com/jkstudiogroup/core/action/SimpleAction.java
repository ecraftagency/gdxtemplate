package com.jkstudiogroup.core.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class SimpleAction extends Action {
  private ActInterface accInterface;

  public static SimpleAction add(ActInterface accInterface) {
    SimpleAction action =(SimpleAction) Actions.action(SimpleAction.class);
    action.accInterface = accInterface;
    return action;
  }

  @Override
  public boolean act(float delta) {
    return this.accInterface.act(this.actor, delta);
  }

  @FunctionalInterface
  public interface ActInterface {
    public boolean act(Actor actor, float delta);
  }
}
