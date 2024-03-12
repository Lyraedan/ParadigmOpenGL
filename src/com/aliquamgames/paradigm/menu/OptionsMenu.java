package com.aliquamgames.paradigm.menu;

import javax.swing.JOptionPane;

import org.lwjgl.util.Rectangle;
import org.newdawn.slick.Color;

import com.aliquamgames.paradigm.Core;
import com.aliquamgames.paradigm.Core.State;
import com.aliquamgames.paradigm.menu.PauseMenu.PausedState;
import com.aliquamgames.paradigm.sound.SoundPlayer;
import com.aliquamgames.paradigm.util.Util;

public class OptionsMenu extends Menu {

	// the state that the optionsMenu is in
	public static enum OptionsState {
		OPTIONS, TEXTUREPACKS
	}

	// the instance of the OptionsState enum
	public static OptionsState optionsState = OptionsState.OPTIONS;

	// the space between the buttons
	private int buttonSpace = 60;
	// the first button's Y
	private int firstButtonY = 50;
	// the number of buttons
	private int numberOfButtons = 7;
	// the space between the graphics quality buttons
	private int buttonXSpace = 10;
	// the brightness of the screen, sent to Core
	private int brightness = 100;
	// the middle of the screen x
	private int x = Core.getGameWidth() / 2;

	// the array of rectangles that contains the buttons
	private Rectangle[] buttonRects = new Rectangle[numberOfButtons];
	// the array of rectangles that contains the graphics quality buttons
	private Rectangle[] graphicsRects = new Rectangle[3];

	// the array of colors that holds the colors for the strings in the buttons
	private Color[] colors = new Color[numberOfButtons];
	// the array of colors that holds the colors for the graphics quality buttons
	private Color[] graphicsColors = new Color[3];

	// the string array that holds the graphics quality strings
	private String[] graphicsStrings = { "Low", "Medium", "High" };
	// the options array that holds the explanations to the box
	private String[] optionStrings = { "Music", "Sound FX", "Brightness", "GFX Quality", "Resolution", "Texture Pack", "" };
	// the buttons array that holds the strings that are put in the buttons
	private String[] buttonStrings = { "Mute Music", "Mute Sound FX", brightness + "%", "", Core.getGameWidth() + "x" + Core.getGameHeight(), "Normal", "Back" };

	// the x's for the graphics buttons
	private int[] graphicsXs = new int[3];
	// the options strings x's
	private int[] optionsStringXs = new int[numberOfButtons];
	// the options strings y's
	private int[] optionsStringYs = { firstButtonY - 5, firstButtonY - 5, firstButtonY + buttonHeight + buttonSpace - 5, firstButtonY + buttonHeight + buttonSpace - 5, firstButtonY + buttonHeight * 2 + buttonSpace * 2 - 5, firstButtonY + buttonHeight * 2 + buttonSpace * 2 - 5, Core.getGameHeight() - buttonHeight - 30 + 50 };
	// the button strings x's
	private int[] buttonStringXs = new int[numberOfButtons];
	// the button strings y's
	private int[] buttonStringYs = { firstButtonY + 50, firstButtonY + 50, firstButtonY + buttonHeight + buttonSpace + 50, firstButtonY + buttonHeight + buttonSpace + 50, firstButtonY + buttonHeight * 2 + buttonSpace * 2 + 50, firstButtonY + buttonHeight * 2 + buttonSpace * 2 + 50, Core.getGameHeight() - buttonHeight - 30 + 60 };
	// the button x's
	private int[] buttonXs = { x - mediumButtonWidth - buttonXSpace, x + buttonXSpace, x - mediumButtonWidth - buttonXSpace, x + buttonXSpace, x - mediumButtonWidth - buttonXSpace, x + buttonXSpace, x - mediumButtonWidth / 2 };
	// the button y's
	private int[] buttonYs = { firstButtonY, firstButtonY, firstButtonY + buttonHeight + buttonSpace, firstButtonY + buttonHeight + buttonSpace, firstButtonY + buttonHeight * 2 + buttonSpace * 2, firstButtonY + buttonHeight * 2 + buttonSpace * 2, Core.getGameHeight() - buttonHeight - 20 };

	// the texturePackMenu instance
	private static TexturePackMenu texturePacksMenu;

	public OptionsMenu() {
		optionsStringXs[0] = x - mediumButtonWidth - buttonXSpace + 90;
		optionsStringXs[1] = x + buttonXSpace + 65;
		optionsStringXs[2] = x - mediumButtonWidth - buttonXSpace + 55;
		optionsStringXs[3] = x + buttonXSpace + 45;
		optionsStringXs[4] = x - mediumButtonWidth - buttonXSpace + 50;
		optionsStringXs[5] = x + buttonXSpace + 30;
		optionsStringXs[6] = x - mediumButtonWidth / 2;

		buttonStringXs[0] = x - mediumButtonWidth - buttonXSpace + 50;
		buttonStringXs[1] = x + buttonXSpace + 20;
		buttonStringXs[2] = x - mediumButtonWidth - buttonXSpace + 170;
		buttonStringXs[3] = x + buttonXSpace + 70;
		buttonStringXs[4] = x - mediumButtonWidth - buttonXSpace + 80;
		buttonStringXs[5] = x + buttonXSpace + 90;
		buttonStringXs[6] = x - mediumButtonWidth / 2 + 105;

		for (int i = 0; i < graphicsRects.length; i++) {
			graphicsRects[i] = new Rectangle(buttonXs[3] + 15 + (mediumButtonWidth / 3 - 15 + 10) * i, buttonYs[3] + 5, mediumButtonWidth / 3 - 15, buttonHeight - 10);
			graphicsColors[i] = Color.white;
		}
		graphicsColors[2] = Color.gray;

		graphicsXs[0] = graphicsRects[0].getX() + 24;
		graphicsXs[1] = graphicsRects[1].getX() + 8;
		graphicsXs[2] = graphicsRects[2].getX() + 22;

		for (int i = 0; i < numberOfButtons; i++) {
			buttonRects[i] = new Rectangle(buttonXs[i], buttonYs[i], mediumButtonWidth, buttonHeight);
			colors[i] = Color.white;
		}
	}

	public void tick() {
		if (texturePacksMenu == null) texturePacksMenu = new TexturePackMenu();
		switch (optionsState) {
		case OPTIONS:
			mouseMove();
			break;
		case TEXTUREPACKS:
			texturePacksMenu.tick();
			break;
		}
	}

	public void mouseMove() {
		if (Core.isMouseLeft) {
			if (buttonRects[2].contains(Core.mouseX, Core.mouseY)) {
				brightness = Core.mouseX - buttonXs[2] - 25;
				if (brightness >= 100) brightness = 100;
				if (brightness <= 0) brightness = 0;
				Core.brightness = 100 - brightness;
				buttonStrings[2] = Integer.toString(brightness) + "%";
			}
		}
	}

	public void render() {
		switch (optionsState) {
		case OPTIONS:
			optionsMenuRender();
			break;
		case TEXTUREPACKS:
			texturePacksMenu.render();
			break;
		}
	}

	public void clicked(int x, int y) {
		switch (optionsState) {
		case OPTIONS:
			optionsMenuClicked(x, y);
			break;
		case TEXTUREPACKS:
			texturePacksMenu.clicked(x, y);
			break;
		}
	}

	public void optionsMenuClicked(int x, int y) {
		if (buttonRects[0].contains(x, y)) {
			if (SoundPlayer.muted) {
				buttonStrings[0] = "Music Muted";
				SoundPlayer.muted = false;
			} else {
				buttonStrings[0] = "Mute Music";
				SoundPlayer.muted = true;
			}
		} else if (buttonRects[1].contains(x, y)) {
			if (SoundPlayer.muted2) {
				buttonStrings[1] = "Mute SoundFX";
				SoundPlayer.muted2 = false;
			} else {
				buttonStrings[1] = "SoundFX Muted";
				SoundPlayer.muted2 = true;
			}
		} else if (buttonRects[2].contains(x, y)) {

		} else if (buttonRects[3].contains(x, y)) {
			if (graphicsRects[0].contains(x, y)) {
				for (int i = 0; i < graphicsColors.length; i++) {
					graphicsColors[i] = Color.white;
				}
				graphicsColors[0] = Color.gray;
				Core.graphicsQuality = "Low";
			} else if (graphicsRects[1].contains(x, y)) {
				for (int i = 0; i < graphicsColors.length; i++) {
					graphicsColors[i] = Color.white;
				}
				graphicsColors[1] = Color.gray;
				Core.graphicsQuality = "Medium";
			} else if (graphicsRects[2].contains(x, y)) {
				for (int i = 0; i < graphicsColors.length; i++) {
					graphicsColors[i] = Color.white;
				}
				graphicsColors[2] = Color.gray;
				Core.graphicsQuality = "High";
			}
		} else if (buttonRects[4].contains(x, y)) {
			// Core.nextWidth = askForInput("width");
			// Core.nextHeight = askForInput("height");
		} else if (buttonRects[5].contains(x, y)) {
			optionsState = OptionsState.TEXTUREPACKS;
		} else if (buttonRects[6].contains(x, y)) {
			if (Core.state == State.MENU) menuState = MenuState.MENU;
			else if (Core.state == State.PAUSED) PauseMenu.pausedState = PausedState.PAUSED;
		}
	}

	public void optionsMenuRender() {
		Util.fillColoredRect(new Color(0, 0, 0, 90), 0, 0, Core.getGameWidth(), Core.getGameHeight(), true);

		for (int i = 0; i < numberOfButtons; i++) {
			Util.drawImage(mediumButton, buttonXs[i], buttonYs[i], mediumButtonWidth, buttonHeight, false);
			Util.drawStringWithShadow(buttonStrings[i], buttonStringXs[i], buttonStringYs[i], 33, true, colors[i]);
			Util.drawStringWithShadow(optionStrings[i], optionsStringXs[i], optionsStringYs[i], 33, true, Color.white);
		}
		// brightness slider
		Util.drawColoredLine(Color.white, buttonXs[2] + 25, buttonYs[2] + 40, buttonXs[2] + 25 + (int) (brightness * 1.2), buttonYs[2] + 40, 13);
		Util.fillCircle(buttonXs[2] + (int) (brightness * 1.2), buttonYs[2] + 20, 20, 20);

		for (int i = 0; i < graphicsRects.length; i++) {
			Util.fillColoredRect(graphicsColors[i], (float) graphicsRects[i].getX(), (float) graphicsRects[i].getY(), (float) graphicsRects[i].getWidth(), (float) graphicsRects[i].getHeight());
			Util.drawStringWithShadow(graphicsStrings[i], graphicsXs[i], graphicsRects[i].getY() + 40, 16, false, Color.black);
		}
	}

	public void mouseHover() {
		switch (optionsState) {
		case OPTIONS:
			optionsMenuMouseHover();
			break;
		case TEXTUREPACKS:
			texturePacksMenu.mouseHover();
			break;
		}
	}

	public void optionsMenuMouseHover() {
		for (int i = 0; i < numberOfButtons; i++) {
			if (buttonRects[i].contains(Core.mouseX, Core.mouseY)) {
				colors[i] = Color.yellow;
			} else {
				colors[i] = Color.white;
			}
		}
	}

	public int askForInput(String item) {
		String response = null;
		int intReturn = -1;
		do {
			try {
				if (item == "width") response = (JOptionPane.showInputDialog(null, "Enter a new width.", "Change Width Resolution", JOptionPane.PLAIN_MESSAGE));
				if (item == "height") response = (JOptionPane.showInputDialog(null, "Enter a new height.", "Change Height Resolution", JOptionPane.PLAIN_MESSAGE));
				if (response == null) break;
				intReturn = Integer.parseInt(response);
			} catch (NumberFormatException e) {

			}
		} while (intReturn <= 0);
		return intReturn;
	}

}
