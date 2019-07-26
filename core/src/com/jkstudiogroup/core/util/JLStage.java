package com.jkstudiogroup.core.util;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.utils.viewport.Viewport;

public class JLStage extends Stage {
  private float timeToCameraZoomTarget, cameraZoomTarget, cameraZoomOrigin, cameraZoomDuration;
  private float timeToCameraMoveTarget, cameraMoveDuration;
  private Vector2 cameraMoveTarget, cameraMoveOrigin;

  private Runnable moveComplete, zoomComplete;

  private Interpolation zoomStyle;
  private Interpolation moveStyle;
  private OrthographicCamera camera;
  protected JLScreen screen;
  protected TextureAtlas textureAtlas = null;

  public JLStage(JLScreen screen) {
    super();
    this.screen = screen;
    this.textureAtlas = screen.getTextureAtlas();

    timeToCameraZoomTarget = -1;
    cameraZoomTarget = 0;
    cameraZoomOrigin = 0;
    cameraZoomDuration = 0;
    zoomStyle = Interpolation.linear;

    timeToCameraMoveTarget = -1;
    moveStyle = Interpolation.linear;

    moveComplete = null;
    zoomComplete = null;
    camera = (OrthographicCamera) getCamera();
  }

  public TextureAtlas getTextureAtlas() {
    return this.textureAtlas;
  }

  @Override
  public void setViewport(Viewport viewport) {
    super.setViewport(viewport);
    camera = (OrthographicCamera) getCamera();
  }

  public void move(Vector2 delta, float duration, Interpolation moveStyle, Runnable onComplete) {
    this.moveStyle = moveStyle;
    moveComplete = onComplete;
    cameraMoveTarget = delta;
    timeToCameraMoveTarget = cameraMoveDuration = duration;
    cameraMoveOrigin = new Vector2(camera.position.x, camera.position.y);
  }

  public void zoom(float zoomTo, float duration, Interpolation zoomStyle, Runnable onComplete) {
    this.zoomStyle = zoomStyle;
    zoomComplete = onComplete;
    cameraZoomTarget = zoomTo;
    timeToCameraZoomTarget = cameraZoomDuration = duration;
    cameraZoomOrigin = camera.zoom;
  }

  @Override
  public void act(float delta) {
    if (timeToCameraMoveTarget >= 0) {
      timeToCameraMoveTarget -= delta;
      float mProgress = timeToCameraMoveTarget < 0 ? 1 : 1f - timeToCameraMoveTarget / cameraMoveDuration;
      camera.position.x = moveStyle.apply(cameraMoveOrigin.x, cameraMoveTarget.x, mProgress);
      camera.position.y = moveStyle.apply(cameraMoveOrigin.y, cameraMoveTarget.y, mProgress);
      if (mProgress == 1 && moveComplete != null) {
        moveComplete.run();
        moveComplete = null;
      }
    }

    if (timeToCameraZoomTarget >= 0) {
      timeToCameraZoomTarget -= delta;
      float progress = timeToCameraZoomTarget < 0 ? 1 : 1f - timeToCameraZoomTarget / cameraZoomDuration;
      camera.zoom = zoomStyle.apply(cameraZoomOrigin, cameraZoomTarget, progress);
      if (progress == 1 && zoomComplete != null) {
        zoomComplete.run();
        zoomComplete = null;
      }
    }
    super.act(delta);
  }

  public <T extends Actor> void palActions(T target, Runnable onComplete, Action...as) {
    SequenceAction seq = new SequenceAction();
    ParallelAction pal = new ParallelAction();
    for (Action a : as) {
      pal.addAction(a);
    }

    seq.addAction(pal);
    if (onComplete != null) {
      seq.addAction(Actions.run(onComplete));
    }

    target.addAction(seq);
  }

  public <T extends Actor> void seqActions(T target, Runnable onComplete, Action ...as) {
    SequenceAction seq = new SequenceAction();
    for (Action a : as) {
      seq.addAction(a);
    }

    if (onComplete != null)
      seq.addAction(Actions.run(onComplete));

    target.addAction(seq);
  }

  public <T extends Actor> void action(T target, Action action, Runnable onComplete){
    SequenceAction seq = new SequenceAction();
    seq.addAction(action);
    if (onComplete != null)
      seq.addAction(Actions.run(onComplete));
    target.addAction(seq);
  }

  public void setTimeout(float timeout, Runnable callback) {
    this.addAction(Actions.sequence(
            Actions.delay(timeout),
            Actions.run(callback)
    ));
  }
}
