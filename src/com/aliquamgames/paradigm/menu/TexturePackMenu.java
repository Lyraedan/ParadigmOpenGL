package com.aliquamgames.paradigm.menu;

import org.lwjgl.util.Rectangle;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import com.aliquamgames.paradigm.Core;
import com.aliquamgames.paradigm.util.Util;

public class TexturePackMenu extends OptionsMenu {

	// the BufferedImage array that holds the button images
	private Texture[] buttons = new Texture[2];

	// the Rectangle array that contains the rectangles around the buttons
	private Rectangle[] rects = new Rectangle[2];

	// the Color array that contains the colors for the text inside the button
	private Color[] colors = new Color[2];

	// the array that has the button x's
	private int[] buttonXs = new int[2];
	// the array that contains the button y's
	private int[] buttonYs = new int[2];
	// the array that holds the string x's
	private int[] stringXs = new int[2];
	// the array that contains the string y's
	private int[] stringYs = new int[2];

	// the String array that contains the strings that go inside the buttons
	private String[] strings = { "Back", "Set Textures" };

	public TexturePackMenu() {
		buttons[0] = mediumButton;
		buttons[1] = mediumButton;

		rects[0] = new Rectangle(50, Core.getGameHeight() - buttonHeight - 20, mediumButtonWidth, buttonHeight);
		rects[1] = new Rectangle(Core.getGameWidth() - mediumButtonWidth - 50, Core.getGameHeight() - buttonHeight - 20, mediumButtonWidth, buttonHeight);

		buttonXs[0] = 50;
		buttonXs[1] = Core.getGameWidth() - mediumButtonWidth - 50;

		buttonYs[0] = Core.getGameHeight() - buttonHeight - 20;
		buttonYs[1] = Core.getGameHeight() - buttonHeight - 20;

		stringXs[0] = 140;
		stringXs[1] = Core.getGameWidth() - mediumButtonWidth - 50 + 30;

		stringYs[0] = Core.getGameHeight() - buttonHeight - 20 + 55;
		stringYs[1] = Core.getGameHeight() - buttonHeight - 20 + 55;

		for (int i = 0; i < colors.length; i++) {
			colors[i] = Color.white;
		}
	}

	public void tick() {

	}

	public void render() {
		drawBox();
		for (int i = 0; i < buttons.length; i++) {
			Util.drawImage(buttons[i], buttonXs[i], buttonYs[i], mediumButtonWidth, buttonHeight, false);
			Util.drawStringWithShadow(strings[i], stringXs[i], stringYs[i], 40, true, colors[i]);
		}
	}

	public void drawBox() {
		Util.fillColoredRect(new Color(0, 0, 0, 90), 5, 5, Core.getGameWidth() - 10, Core.getGameHeight() - buttonHeight - 20 - 10, true);
	}

	public void clicked(int x, int y) {
		if (rects[0].contains(x, y)) {
			optionsState = OptionsState.OPTIONS;
		} else if (rects[1].contains(x, y)) {
			System.out.println("TEXTUREPACKMENU: SETTING NEW TEXTURE PACK, TO BE DONE");
		}
	}

	public void mouseHover() {
		for (int i = 0; i < rects.length; i++) {
			if (rects[i].contains(Core.mouseX, Core.mouseY)) {
				colors[i] = Color.yellow;
			} else {
				colors[i] = Color.white;
			}
		}
	}

}
