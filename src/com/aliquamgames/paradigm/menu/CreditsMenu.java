package com.aliquamgames.paradigm.menu;

import java.io.IOException;

import org.lwjgl.Sys;
import org.lwjgl.util.Rectangle;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import com.aliquamgames.paradigm.Core;
import com.aliquamgames.paradigm.ImageLoader;
import com.aliquamgames.paradigm.util.Util;

/**
 * 
 * @author Luke Rapkin
 * 
 */

public class CreditsMenu extends Menu {

	private final String[] developers = new String[3];
	private final String[] cause = new String[developers.length];
	private final Texture[] icons = new Texture[developers.length];
	private Rectangle[] iconRect = new Rectangle[developers.length];
	private Texture twitter, button;
	private Color[] colors = new Color[2];
	private Color[] colors2 = new Color[developers.length];
	private Texture[] buttons = new Texture[developers.length];
	private final int xPos = 60;
	private final int yPos = 100;
	private final int buttonX = Core.getGameWidth() / 2;
	private final int buttonY = Core.getGameHeight() - 100;
	private final int buttonWidth = 390;
	private final int buttonHeight = 80;

	// max length 26 letters including spaces
	public String[] aboutDevelopers = { "About Aliquam Games:",//
			"AbcdefthijklmnopqrstuvwxyZ",//
			"",//
			"",//
			"",//
			"",//
			"",//
			"",//
			"",//
			"",//
			"",//
			"",//
			"",//
			"" };//
	public String[] aboutParadigm = { "About Paradigm:",//
			"Paradigm is a 2D block building survival game, where you",//
			"can build a house to survive the night; against the demons",//
			"that arise to attack you. The worlds are infinite so there",//
			"is nothing, to hold you back from releasing your creativity.",//
			"",//
			"Go forth, and make your own fate what you want it to be." };//

	public CreditsMenu() {

		try {
			twitter = ImageLoader.loadSlick("PNG", "/logos/twitter.png");
			button = ImageLoader.loadSlick("PNG", "/GUI/buttons/button_background.png");
		} catch (IOException e) {
			e.printStackTrace();
		}

		colors[0] = Color.white;
		colors[1] = Color.white;

		cause[0] = "Code";
		cause[1] = "Code";
		cause[2] = "GFX";

		developers[0] = "Luke Rapkin" + " ( " + cause[0] + " ) ";
		developers[1] = "Matthew Meacham" + " ( " + cause[1] + " ) ";
		developers[2] = "Ross MacPhee" + " ( " + cause[2] + " ) ";

		icons[0] = twitter;
		icons[1] = twitter;
		icons[2] = twitter;

		buttons[0] = button;
		buttons[1] = button;
		buttons[2] = button;

		back = new Rectangle(buttonX - (buttonWidth / 2), buttonY, buttonWidth, buttonHeight);
		for (int i = 0; i < developers.length; i++) {
			iconRect[i] = new Rectangle(xPos - 50, yPos + i * 60, 50, 50);
		}
	}

	public void tick() {

	}

	private Rectangle back;

	public void render() {
		for (int i = 0; i < developers.length; i++) {
			Util.drawStringWithShadow(developers[i], xPos, yPos + i * 60 + 10, 15, false);
		}
		for (int i = 0; i < developers.length; i++) {
			Util.drawImage(buttons[i], xPos - 55, yPos + i * 60, 50, 50, false);
		}
		for (int i = 0; i < icons.length; i++) {
			Util.fillColoredRect(colors2[i], xPos - 50, yPos + i * 60 + 5, 50 - 10, 50 - 10);
			Util.drawImage(icons[i], xPos - 50, yPos + i * 60 + 5, 50 - 10, 50 - 10, true);
		}
		for (int i = 0; i < 1; i++) {
			Util.drawImage(button, buttonX - (buttonWidth / 2), buttonY, buttonWidth, buttonHeight, false);
			Util.drawStringWithShadow("Back", buttonX - (15 * 2), buttonY + (15 * 2), 15, false, colors[i]);
		}

		Util.drawString("Follow us on twitter!", xPos, yPos - 30, 15, false);
		Util.drawString("VVV", xPos - 55, yPos - 30, 15, false);
		Util.fillColoredRect(new Color(0, 0, 0, 90), 415, 15, 378, 300, true);
		Util.fillColoredRect(new Color(0, 0, 0, 90), 15, Core.getGameHeight() - 270, Core.getGameWidth() - 32, (170 - 15), true);
		for (int i = 0; i < aboutDevelopers.length; i++) {
			Util.drawStringWithShadow(aboutDevelopers[i], 415, 10 + i * 20 + 10, 15, false);
		}
		for (int i = 0; i < aboutParadigm.length; i++) {
			Util.drawStringWithShadow(aboutParadigm[i], 20, Core.getGameHeight() - 275 + i * 20 + 10, 15, false);
		}
	}

	public void clicked(int x, int y) {
		if (back.contains(x, y)) menuState = MenuState.MENU;
		if (iconRect[0].contains(x, y)) Sys.openURL("https://www.twitter.com/DRAGONMASTER412");
		if (iconRect[1].contains(x, y)) Sys.openURL("https://twitter.com/MrWayFarOut");
		if (iconRect[2].contains(x, y)) Sys.openURL("https://twitter.com/_MrCrazyCat");
	}

	public void mouseHover() {
		for (int i = 0; i < 1; i++) {
			if (back.contains(Core.mouseX, Core.mouseY)) {
				colors[i] = Color.yellow;
			} else {
				colors[i] = Color.white;
			}
		}
		for (int i = 0; i < developers.length; i++) {
			if (iconRect[i].contains(Core.mouseX, Core.mouseY)) {
				colors2[i] = Color.yellow;
			} else {
				colors2[i] = Color.white;
			}
		}
	}

}
