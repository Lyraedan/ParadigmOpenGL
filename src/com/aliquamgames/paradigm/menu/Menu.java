package com.aliquamgames.paradigm.menu;

import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Rectangle;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import com.aliquamgames.paradigm.Core;
import com.aliquamgames.paradigm.Core.State;
import com.aliquamgames.paradigm.ImageLoader;
import com.aliquamgames.paradigm.render.FontRenderer;
import com.aliquamgames.paradigm.util.Util;

public class Menu {
	// the year the game was created
	private String yearCreated = "2014";
	// the unicode value for the copyright symbol
	private String copyright = "\u00a9";
	// the splash String
	// private String splash = Core.randomMessage();

	// the background image
	protected Texture backgroundImage;
	// the title image
	protected Texture titleImage;
	// the small button image
	protected Texture smallButton;
	// the medium button image
	protected Texture mediumButton;
	// the button image
	protected Texture button;

	// the BufferedImage array that contains the button images
	private Texture[] buttons = new Texture[4];

	// the x for the buttons on the menu
	protected int x;
	// the width of the button, default is 390
	protected int buttonWidth = 390;
	// the width of the medium button, default is 147
	protected int mediumButtonWidth = 147;
	// the width of the small button, default is 84
	protected int smallButtonWidth = 84;
	// the height of the buttons, default is 80
	protected int buttonHeight = 80;
	// the space between the buttons
	protected int buttonSpace = 10;
	// the first button's Y
	private int firstButtonY = 180;
	// the size of the splash font
	private int splashFontSize = 20;
	// the minimum size of the splash font
	private int splashMinimum = splashFontSize;
	// the maximum size of the splash font
	private int splashCap = 40;

	// the array that contains the buttons x's
	private int[] buttonXs = new int[4];
	// the array that contains the buttons y's
	private int[] buttonYs = new int[4];
	// the array that contains the strings x's
	private int[] stringXs = new int[4];
	// the array that contains the strings y's
	private int[] stringYs = new int[4];

	// controls the double size of the splash
	private double splashSize = 20;

	// controls if the splash size is growing
	private boolean up = true;

	// the enum that controls what state the Menu is in
	public static enum MenuState {
		MENU, PROFILESELECT, CREATEACHARACTER, SINGLEPLAYER, MULTIPLAYER, OPTIONS, CREDITS
	}

	// the MenuState instance
	public static MenuState menuState = MenuState.MENU;

	// the color array that holds the colors for the text within the buttons
	private Color[] colors = new Color[4];

	// the string array that contains the strings within the button
	private String[] strings = new String[4];

	// the rectangle array that holds the rectangles around the buttons
	private Rectangle[] rects = new Rectangle[4];

	// the ProfileSelectMenu instance
	private ProfileSelectMenu profileSelectMenu;
	// the CreateACharacterMenu
	private CreateACharacterMenu createACharacterMenu;
	// the MultiplayerMenu instance
	private MultiplayerMenu multiplayerMenu;
	// the OptionsMenu instance
	public static OptionsMenu optionsMenu;
	// the CreditsMenu instance
	private CreditsMenu creditsMenu;

	public Menu() {

		try {
			button = ImageLoader.loadSlick("PNG", "/GUI/buttons/button_background.png");
			buttonWidth = button.getImageWidth();
			buttonHeight = button.getImageHeight();
			smallButton = ImageLoader.loadSlick("PNG", "/GUI/buttons/button_background_small.png");
			smallButtonWidth = smallButton.getImageWidth();
			mediumButton = ImageLoader.loadSlick("PNG", "/GUI/buttons/button_background_med.png");
			mediumButtonWidth = mediumButton.getImageWidth();
			titleImage = ImageLoader.loadSlick("PNG", "/logos/Paradigm_Logo.png");
			aliquamLogo = ImageLoader.loadSlick("PNG", "/logos/AliquamGamesLogo.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		x = Core.getGameWidth() / 2 - buttonWidth / 2;
		colors[0] = Color.white;
		colors[1] = Color.white;
		colors[2] = Color.white;
		colors[3] = Color.white;

		colors2[0] = Color.white;
		colors2[1] = Color.white;

		buttons[0] = button;
		buttons[1] = button;
		buttons[2] = button;
		buttons[3] = button;

		strings[0] = "Single Player";
		strings[1] = "Mutliplayer";
		strings[2] = "Options";
		strings[3] = "Quit";

		buttonXs[0] = x;
		buttonXs[1] = x;
		buttonXs[2] = x;
		buttonXs[3] = x;

		buttonYs[0] = firstButtonY;
		buttonYs[1] = firstButtonY + buttonSpace + buttonHeight;
		buttonYs[2] = firstButtonY + buttonSpace * 2 + buttonHeight * 2;
		buttonYs[3] = firstButtonY + buttonSpace * 3 + buttonHeight * 3;

		stringXs[0] = buttonXs[0] + 110;
		stringXs[1] = buttonXs[1] + 120;
		stringXs[2] = buttonXs[2] + 145;
		stringXs[3] = buttonXs[3] + 165;

		stringYs[0] = buttonYs[0] + buttonHeight / 2 - 12;
		stringYs[1] = buttonYs[1] + buttonHeight / 2 - 12;
		stringYs[2] = buttonYs[2] + buttonHeight / 2 - 12;
		stringYs[3] = buttonYs[3] + buttonHeight / 2 - 12;

		rects[0] = new Rectangle(buttonXs[0], buttonYs[0], buttonWidth, buttonHeight);
		rects[1] = new Rectangle(buttonXs[1], buttonYs[1], buttonWidth, buttonHeight);
		rects[2] = new Rectangle(buttonXs[2], buttonYs[2], buttonWidth, buttonHeight);
		rects[3] = new Rectangle(buttonXs[3], buttonYs[3], buttonWidth, buttonHeight);
	}

	public void loadObjects() {
		profileSelectMenu = new ProfileSelectMenu();
		try {
			createACharacterMenu = new CreateACharacterMenu();
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException | IOException e) {
			e.printStackTrace();
		}
		multiplayerMenu = new MultiplayerMenu();
		optionsMenu = new OptionsMenu();
		creditsMenu = new CreditsMenu();
	}

	public void tick() {
		mouseHover();
		switch (menuState) {
		case MENU:
			menuTick();
			break;
		case PROFILESELECT:
			profileSelectMenu.tick();
			break;
		case CREATEACHARACTER:
			createACharacterMenu.tick();
			break;
		case MULTIPLAYER:
			multiplayerMenu.tick();
			break;
		case OPTIONS:
			optionsMenu.tick();
			break;
		case CREDITS:
			creditsMenu.tick();
			break;
		}
	}

	public void menuTick() {

	}

	public void render() {

		Util.fillColoredRect(new Color(115, 201, 185), 0, 0, Core.getGameWidth(), Core.getGameHeight());

		switch (menuState) {
		case MENU:
			menuRender();
			break;
		case PROFILESELECT:
			profileSelectMenu.render();
			break;
		case CREATEACHARACTER:
			createACharacterMenu.render();
			break;
		case MULTIPLAYER:
			multiplayerMenu.render();
			break;
		case OPTIONS:
			optionsMenu.render();
			break;
		case CREDITS:
			creditsMenu.render();
			break;
		}
	}

	public void menuRender() {
		Util.fillColoredRect(new Color(0, 0, 0, 255), 0, 15, Core.getGameWidth(), 150);
		Util.fillColoredRect(new Color(102, 161, 193, 190), 0, 20, Core.getGameWidth(), 140);

		Util.drawImage(titleImage, Core.getGameWidth() / 2 - (titleImage.getImageWidth()) / 2, 30, titleImage.getImageWidth(), titleImage.getImageHeight(), true);

		for (int i = 0; i < buttons.length; i++) {
			Util.drawImage(buttons[i], buttonXs[i], buttonYs[i], buttonWidth, buttonHeight, false);
			Util.drawStringWithShadow(strings[i], stringXs[i], stringYs[i], 40, false, colors[i]);
			addAliquamButton(Core.getGameWidth() - 50, Core.getGameHeight() - 100, 50, 50);
		}

		drawSplash(Core.getGameWidth() / 2 - splash.length() - Util.getStringLength(splash, 15) + 10, 115);

		int xOffset = FontRenderer.font2.getWidth(copyright + " " + yearCreated + " AliquamGames. All Rights Reserved");
		Util.drawStringWithShadow(Core.title, 5, Core.getGameHeight() - 50, 15, true);
		Util.drawStringWithShadow(Core.state2 + " " + Core.version, 5, Core.getGameHeight() - 30, 15, true);
		Util.drawStringWithShadow(copyright + " " + yearCreated + " AliquamGames. All Rights Reserved", Core.getGameWidth() - xOffset, Core.getGameHeight() - 30, 5, true);

	}

	String splash = Core.randomMessage;

	public void drawSplash(int x, int y) {
		if (up) {
			splashSize += 0.25;
			if (splashSize > splashCap) {
				up = false;
			}
		} else {
			splashSize -= 0.25;
			if (splashSize < splashMinimum) {
				up = true;
			}
		}
		splashFontSize = (int) splashSize;

		if (splash == Core.randomMessage2) {
			splash = Core.randomMessage();
		}

		 Util.rotate(x, y, 10);//20 maybe
		 Util.drawStringWithShadow(splash, x, y, splashFontSize, false, Color.yellow);
		 GL11.glPopMatrix();
	}

	public void clicked(int x, int y) {
		switch (menuState) {
		case MENU:
			menuClicked(x, y);
			break;
		case PROFILESELECT:
			profileSelectMenu.clicked(x, y);
			break;
		case CREATEACHARACTER:
			createACharacterMenu.clicked(x, y);
			break;
		case MULTIPLAYER:
			multiplayerMenu.clicked(x, y);
			break;
		case OPTIONS:
			optionsMenu.clicked(x, y);
			break;
		case CREDITS:
			creditsMenu.clicked(x, y);
			break;
		}
	}

	public void menuClicked(int x, int y) {
		if (rects[0].contains(x, y)) {
			Core.state = State.PLAYING;
			// menuState = MenuState.PROFILESELECT;
		}
		if (rects[1].contains(x, y)) menuState = MenuState.MULTIPLAYER;
		if (rects[2].contains(x, y)) menuState = MenuState.OPTIONS;
		if (rects[3].contains(x, y)) System.exit(0);
		if (aliquamRect.contains(x, y)) menuState = MenuState.CREDITS;
	}

	public void mouseHover() {
		switch (menuState) {
		case MENU:
			menuMouseHover();
			break;
		case PROFILESELECT:
			profileSelectMenu.mouseHover();
			break;
		case CREATEACHARACTER:
			createACharacterMenu.mouseHover();
			break;
		case MULTIPLAYER:
			multiplayerMenu.mouseHover();
			break;
		case OPTIONS:
			optionsMenu.mouseHover();
			break;
		case CREDITS:
			creditsMenu.mouseHover();
			break;
		}
	}

	public void menuMouseHover() {
		for (int i = 0; i < rects.length; i++) {
			if (rects[i].contains(Core.mouseX, Core.mouseY)) {
				colors[i] = Color.yellow;
			} else {
				colors[i] = Color.white;
			}
		}
		for (int i = 0; i < 1; i++) {
			if (aliquamRect.contains(Core.mouseX, Core.mouseY)) {
				colors2[i] = Color.yellow;
			} else {
				colors2[i] = Color.white;
			}
		}
	}

	// Credits button
	private Rectangle aliquamRect;
	private Color[] colors2 = new Color[2];
	public Texture aliquamLogo;

	public void addAliquamButton(int x, int y, int width, int height) {
		for (int i = 0; i < 1; i++) {
			aliquamRect = new Rectangle(x, y, width, height);
			Util.drawImage(button, x, y, width, height, false);
			Util.fillColoredRect(colors2[i], x + 5, y + 5, (width - 10), (height - 10));
			Util.drawImage(aliquamLogo, x + 5, y + 5, (width - 10), (height - 10), true);
		}
	}
}
