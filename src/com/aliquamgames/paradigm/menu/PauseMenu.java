package com.aliquamgames.paradigm.menu;

import org.lwjgl.util.Rectangle;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import com.aliquamgames.paradigm.Core;
import com.aliquamgames.paradigm.Core.State;
import com.aliquamgames.paradigm.util.Util;

public class PauseMenu extends Menu {

	public int numberOfButtons = 3;

	public Texture[] images = { button, button, button };
	public int[] buttonXs = new int[numberOfButtons];
	public int[] buttonYs = new int[numberOfButtons];

	public String[] strings = { "Continue", "Options", "Save and Quit" };
	public int[] stringXs = new int[numberOfButtons];
	public int[] stringYs = new int[numberOfButtons];

	private Color[] colors = { Color.white, Color.white, Color.white };

	private Rectangle[] rects = new Rectangle[numberOfButtons];

	public enum PausedState {
		PAUSED, OPTIONS
	}

	public static PausedState pausedState = PausedState.PAUSED;

	public PauseMenu() {
		for (int i = 0; i < numberOfButtons; i++) {
			buttonXs[i] = x;
			buttonYs[i] = (150 + (buttonSpace + buttonHeight) * i);
			stringYs[i] = (150 + (buttonSpace + buttonHeight) * i + 25);
		}
		stringXs[0] = (x + 135);
		stringXs[1] = (x + 140);
		stringXs[2] = (x + 105);

		for (int i = 0; i < numberOfButtons; i++) {
			rects[i] = new Rectangle(buttonXs[i], buttonYs[i], buttonWidth, buttonHeight);
		}
	}

	public void tick() {
		mouseHover();
		switch (pausedState) {
		case PAUSED:
			pauseTick();
			break;
		case OPTIONS:
			optionsMenu.tick();
			break;
		}
	}

	public void pauseTick() {

	}

	public void render() {

		switch (pausedState) {
		case PAUSED:
			pauseRender();
			break;
		case OPTIONS:
			optionsMenu.render();
			break;
		}
	}

	public void pauseRender() {
		Util.fillColoredRect(new Color(0, 0, 0, 100), 0, 0, Core.getGameWidth(), Core.getGameHeight(), true);

		Util.drawStringWithShadow("Game Paused!", (x - 150), 35, 35, true, Color.white);

		for (int i = 0; i < numberOfButtons; i++) {
			Util.drawImage(images[i], buttonXs[i], buttonYs[i], buttonWidth, buttonHeight, false);
			Util.drawStringWithShadow(strings[i], stringXs[i], stringYs[i], 18, true, colors[i]);

		}
	}

	public void clicked(int x, int y) {
		switch (pausedState) {
		case PAUSED:
			pauseClicked(x, y);
			break;
		case OPTIONS:
			optionsMenu.clicked(x, y);
			break;
		}
	}

	public void pauseClicked(int x, int y) {
		if (rects[0].contains(x, y)) {
			Core.state = State.PLAYING;
		} else if (rects[1].contains(x, y)) {
			pausedState = PausedState.OPTIONS;
		} else if (rects[2].contains(x, y)) {
			System.out.println("PAUSEMENU: NEEDS TO SAVE");
			System.exit(0);
		}
	}

	public void mouseHover() {
		switch (pausedState) {
		case PAUSED:
			pauseMouseHover();
			break;
		case OPTIONS:
			optionsMenu.mouseHover();
			break;
		}
	}

	public void pauseMouseHover() {
		for (int i = 0; i < rects.length; i++) {
			if (rects[i].contains(Core.mouseX, Core.mouseY)) {
				colors[i] = Color.yellow;
			} else {
				colors[i] = Color.white;
			}
		}
	}

}
