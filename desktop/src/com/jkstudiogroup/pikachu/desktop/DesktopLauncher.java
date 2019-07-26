package com.jkstudiogroup.pikachu.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.jkstudiogroup.alan.Alan;
import com.jkstudiogroup.domino.Domino;
import com.jkstudiogroup.pikachu.Pikachu;
import com.jkstudiogroup.pikachu.TestGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = 720;
		config.width = 1280;
		new LwjglApplication(new Alan(), config);
	}
}
