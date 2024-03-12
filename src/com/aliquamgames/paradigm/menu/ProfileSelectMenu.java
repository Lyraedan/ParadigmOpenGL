package com.aliquamgames.paradigm.menu;

import org.lwjgl.util.Rectangle;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import com.aliquamgames.paradigm.Core;
import com.aliquamgames.paradigm.Core.State;
import com.aliquamgames.paradigm.util.Util;

public class ProfileSelectMenu extends Menu {

	private int selectedProfile = -1;

	private Texture[] buttons = { button, button, button, button, mediumButton, mediumButton };
	private int x = Core.getGameWidth() / 2 - buttonWidth / 2;
	private int space = 40;
	private int firstButtonX = 25;
	private int[] buttonXs = { x, x, x, x, 0, Core.getGameWidth() - mediumButtonWidth };
	private int[] buttonYs = { firstButtonX, firstButtonX + buttonHeight + space, firstButtonX + buttonHeight * 2 + space * 2, firstButtonX + buttonHeight * 3 + space * 3, Core.getGameHeight() - buttonHeight - 10, Core.getGameHeight() - buttonHeight - 10 };
	private int[] buttonWidths = { buttonWidth, buttonWidth, buttonWidth, buttonWidth, mediumButtonWidth, mediumButtonWidth };
	private Color[] colors = { Color.white, Color.white, Color.white, Color.white, Color.white, Color.white };
	private String[] strings;
	private int[] stringXs = { x + 110, x + 110, x + 25, x + 25, 90, Core.getGameWidth() - mediumButtonWidth + 105 };
	private int[] stringYs;
	private int[] fontSizes = { 40, 40, 30, 30, 40, 40 };
	private Rectangle[] rects = new Rectangle[buttons.length];
	public static String worldFolder = "World ";
	private boolean[] created = new boolean[4];

	public ProfileSelectMenu() {
		strings = new String[buttons.length];
		if (created[0]) {// TODO RESTORE !
			strings[0] = "Empty";
		} else {
			strings[0] = worldFolder + "1";
		}
		if (created[1]) {// TODO RESTORE !
			strings[1] = "Empty";
		} else {
			strings[1] = worldFolder + "2";
		}
		if (!created[2]) {
			strings[2] = "Empty";
		} else {
			strings[2] = worldFolder + "3";
		}
		if (!created[3]) {
			strings[3] = "Empty";
		} else {
			strings[3] = worldFolder + "4";
		}
		strings[4] = "Back";
		strings[5] = "Play";

		stringYs = new int[buttons.length];
		for (int i = 0; i < stringYs.length; i++) {
			stringYs[i] = buttonYs[i] + 50;
		}

		for (int i = 0; i < rects.length; i++) {
			rects[i] = new Rectangle(buttonXs[i], buttonYs[i], buttonWidths[i], buttonHeight);
		}

	}

	public void tick() {

	}

	public void render() {
		for (int i = 0; i < buttons.length; i++) {
			Util.drawImage(buttons[i], buttonXs[i], buttonYs[i], mediumButtonWidth, buttonHeight, false);
			Util.drawStringWithShadow(strings[i], stringXs[i], stringYs[i], fontSizes[i], true, colors[i]);
		}
	}

	public void setColors(int index) {
		for (int i = 0; i < colors.length; i++) {
			colors[i] = Color.white;
		}
		colors[index] = Color.yellow;
	}

	@SuppressWarnings("static-access")
	public void clicked(int x, int y) {
		if (rects[0].contains(x, y)) {
			selectedProfile = 1;
			setColors(0);
			if (strings[0] == "Empty") menuState = MenuState.CREATEACHARACTER;
		} else if (rects[1].contains(x, y)) {
			selectedProfile = 2;
			setColors(1);
			if (strings[1] == "Empty") menuState = MenuState.CREATEACHARACTER;
		} else if (rects[2].contains(x, y)) {
			selectedProfile = 3;
			setColors(2);
			if (strings[2] == "Empty") menuState = MenuState.CREATEACHARACTER;
		} else if (rects[3].contains(x, y)) {
			selectedProfile = 4;
			setColors(3);
			if (strings[3] == "Empty") menuState = MenuState.CREATEACHARACTER;
		} else if (rects[4].contains(x, y)) {
			menuState = menuState.MENU;
		} else if (rects[5].contains(x, y)) {
			if (selectedProfile != -1) {
				System.out.println("NEEDS TO LOAD A CERTAIN PROFILE");
				Core.state = State.PLAYING;
			}
		}
	}

	public void mouseHover() {
		// for (int i = 0; i < rects.length; i++) {
		// if (rects[i].contains(Core.mouseX, Core.mouseY)) {
		// colors[i] = Color.YELLOW;
		// } else {
		// if (selectedProfile >= 1) {
		// if (colors[selectedProfile - 1] != Color.YELLOW) {
		// colors[i] = Color.white;
		// }
		// }
		// }
		// }
	}
}
