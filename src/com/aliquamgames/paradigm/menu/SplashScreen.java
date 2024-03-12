package com.aliquamgames.paradigm.menu;

import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import com.aliquamgames.paradigm.Core;
import com.aliquamgames.paradigm.ImageLoader;
import com.aliquamgames.paradigm.util.Util;

public class SplashScreen {
	// the splash screen image
	private Texture splash;

	// controls if the splash screen is waiting (holding the splash screen for a couple seconds, and also loads objects in Core)
	public boolean waiting = false;
	// controls if the splash screen is fading in
	public boolean fadingIn = true;
	// controls if the splash screen is fading out
	public boolean fadingOut = false;
	// controls if the splash screen is done with all its stuff, so that the Core can switch states
	public boolean finished = false;

	// the alpha channel for the splash screen image
	public float alpha = 0;
	// the change in the alpha each render
	public float alphaChange = 0.025f;

	// the amount of time the logo stays waiting and in full alpha after game objects have loaded in Core
	public int logoTime = 1000;
	// the timer that controls when it reaches the logoTime
	public int logoTimer = 0;
	// the time of the transition in seconds
	public double transitionTime = 0.5 / 2;

	public SplashScreen() {
		try {
			splash = ImageLoader.loadSlick("PNG", "/GUI/splash/splashScreen.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void tick() {
		if (fadingIn && !waiting) {
			alpha += alphaChange;
			if (alpha >= 1.0f) {
				alpha = 1.0f;
				fadingIn = false;
				waiting = true;
			}

		} else if (waiting) {
			if (logoTimer <= logoTime) {
				logoTimer += 10;
			} else {
				fadingOut = true;
				waiting = false;
			}
		} else if (fadingOut && !waiting) {
			alphaChange = (float) (1.0f / (transitionTime * 60));
			alpha -= alphaChange;
			if (alpha <= 0.0f) {
				alpha = 0.0f;
				fadingOut = false;
				finished = true;
			}
		}
	}

	public void render() {
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glColor4f(1.0f, 1.0f, 1.0f, alpha);
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2f(0, Core.getGameHeight());
		GL11.glVertex2f(0, 0);
		GL11.glVertex2f(Core.getGameWidth(), 0);
		GL11.glVertex2f(Core.getGameWidth(), Core.getGameHeight());
		GL11.glEnd();

		if (!finished) {
			Util.drawImage(splash, 0, 0, Core.getGameWidth(), Core.getGameHeight(), true);
		} else {
			splash.release();
		}
	}
}
