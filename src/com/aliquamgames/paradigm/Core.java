package com.aliquamgames.paradigm;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Random;

import org.gamejolt.GameJoltAPI;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import com.aliquamgames.paradigm.blocks.Block;
import com.aliquamgames.paradigm.crafting.Crafting;
import com.aliquamgames.paradigm.inventory.Inventory;
import com.aliquamgames.paradigm.level.InfiniteLevel;
import com.aliquamgames.paradigm.listeners.Animation;
import com.aliquamgames.paradigm.listeners.KeyboardListener;
import com.aliquamgames.paradigm.menu.Menu;
import com.aliquamgames.paradigm.menu.PauseMenu;
import com.aliquamgames.paradigm.menu.SplashScreen;
import com.aliquamgames.paradigm.npc.EntityTicker;
import com.aliquamgames.paradigm.npc.Spawner;
import com.aliquamgames.paradigm.playing.Achievements;
import com.aliquamgames.paradigm.playing.Chat;
import com.aliquamgames.paradigm.playing.Commands;
import com.aliquamgames.paradigm.playing.GUI;
import com.aliquamgames.paradigm.playing.Physics;
import com.aliquamgames.paradigm.playing.Player;
import com.aliquamgames.paradigm.playing.Skills;
import com.aliquamgames.paradigm.playing.Sky;
import com.aliquamgames.paradigm.playing.Tile;
import com.aliquamgames.paradigm.render.FontRenderer;
import com.aliquamgames.paradigm.render.Shader;
import com.aliquamgames.paradigm.sound.SoundPlayer;
import com.aliquamgames.paradigm.util.SaveLoad;
import com.aliquamgames.paradigm.util.Util;

public class Core {

	// DONT CHANGE FROM 30, THIS IS THE UPDATE rate, not FRAMERATE
	public static final int UPDATE_RATE = 30;

	private static int width = 800;
	private static int height = 600;
	private static boolean fullscreen = false;
	// the x of the mouse in the window
	public static int mouseX = 0;
	// the y of the mouse in the window
	public static int mouseY = 0;

	public static boolean isMouseRight = false;
	public static boolean isMouseLeft = false;
	public static boolean spaceBarDown = false;
	public static boolean isLeft = false;
	public static boolean isRight = false;

	public static boolean showDebug = false; // TODO THE X, Y, BIOME HANDLER

	// the title of the game
	public static String title = "Paradigm";
	// the state the game is in
	public static String state2 = "OpenGL";
	// the version the game is in
	public static String version = "";
	// the version the game is in
	public static String dev = "Infdev";
	// the full title of the game
	public static String randomMessage = randomMessage();
	public static String randomMessage2 = randomMessage();
	private static String fullTitle = title + " " + state2 + " " + dev + " " + version + ": " + " | " + randomMessage2;
	// the quality of the graphics
	public static String graphicsQuality = "High";

	private static final double moveFromBoarder = 0;
	public static double sX = moveFromBoarder, sY = moveFromBoarder;
	public static int dir = 0;

	public static int brightness = 100;

	// the enum that controls what state the game is in
	public static enum State {
		SPLASHSCREEN, INITIALIZED, MENU, PLAYING, PAUSED, GAMEOVER
	}

	// the instance of the State enum
	public static State state = State.SPLASHSCREEN;

	// for gamejolt
	private int gameId;
	private String privateKey;
	private String username = "Team_Aliquam";
	private String userToken;
	public GameJoltAPI gamejoltAPI;

	public SplashScreen splash;
	public Menu menu;
	public static FontRenderer fontRenderer;
	public static InfiniteLevel level;
	public static Sky sky;
	public static Block block;
	public static Player player;
	public static Inventory inventory;
	public static ImageLoader imageLoader;
	public static KeyboardListener keyboardListener;
	public static Skills skills;
	public static Chat chat;
	public static Commands commands;
	public static GUI gui;
	public static EntityTicker entityTicker;
	public static Spawner spawner;
	public static Achievements achievements;
	public static Animation animation;
	public static PauseMenu pauseMenu;
	public static Crafting crafting;
	public static Shader shader;
	public static Physics physics;
	public static SaveLoad saveLoad;

	// init sounds
	public static final SoundPlayer forestAmbient = new SoundPlayer("WAV", "/sound/In-Game/Forest_Ambient.wav", false);
	public static final SoundPlayer achievementGot = new SoundPlayer("WAV", "/sound/In-Game/Game_Achievement_Reached.wav", false);
	public static final SoundPlayer rain = new SoundPlayer("WAV", "/sound/In-Game/Rain.wav", false);
	public static final SoundPlayer rain2 = new SoundPlayer("WAV", "/sound/In-Game/Rain_2.wav", false);
	public static final SoundPlayer fallScream = new SoundPlayer("WAV", "/sound/In-Game/Fall_Scream.wav", false);

	public static final SoundPlayer grass = new SoundPlayer("WAV", "/sound/Walking/grass.wav", false);
	public static final SoundPlayer gravel = new SoundPlayer("WAV", "/sound/Walking/dirt.wav", false);
	public static final SoundPlayer stone = new SoundPlayer("WAV", "/sound/Walking/stone.wav", false);
	public static final SoundPlayer snow = new SoundPlayer("WAV", "/sound/Walking/snow.wav", false);
	public static final SoundPlayer cloth = new SoundPlayer("WAV", "/sound/Walking/cloth.wav", false);

	public static boolean running = false;

	public Core() {
		try {
			Mouse.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		splash = new SplashScreen();
		// for multiplayer login VVVVVVVVVVVVVVVVV
		// gamejoltAPI = new GameJoltAPI(gameId, privateKey, username, userToken);
		fontRenderer = new FontRenderer();// this needs calling here
		new Tile();
		menu = new Menu();
	}

	// controls if the game objects have been loaded
	public static boolean loaded = false;

	private void loadObjects() {
		System.out.println("LOADING OBJECTS");
		imageLoader = new ImageLoader();
		keyboardListener = new KeyboardListener();
		// Thread t = new Thread(new Runnable() {
		// public void run() {
		// while (running) {
		// keyboardListener.input();
		// }
		// }
		// });
		// t.start();

		shader = new Shader();
		sky = new Sky();
		physics = new Physics();
		level = new InfiniteLevel();
		animation = new Animation();
		sX = 40;
		sY = -10;
		block = new Block();
		player = new Player();
		inventory = new Inventory();
		skills = new Skills();
		chat = new Chat();
		commands = new Commands();
		gui = new GUI();
		menu.loadObjects();
		entityTicker = new EntityTicker();
		spawner = new Spawner();
		achievements = new Achievements();
		pauseMenu = new PauseMenu();
		crafting = new Crafting();
		saveLoad = new SaveLoad();

		forestAmbient.setVolume(1.0f, 1.0f);
		forestAmbient.loop("WAV", false, "MUSIC");
	}

	public void render() {
		fps++;
		currentTime = getTime();
		if (currentTime > lastTime + 1000) {
			Display.setTitle(fullTitle + " | FPS: " + fps);
			fps = 0;
			lastTime = getTime();
		}
		try {
			clearScreen();
			Util.fillColoredRect(Color.white, 0, 0, getGameWidth(), getGameHeight()); // TODO POINT OF INTEREST

			switch (state) {
			case SPLASHSCREEN:
				splash.render();
				break;
			case INITIALIZED:
				GL11.glDisable(GL11.GL_BLEND);
				break;
			case MENU:
				GL11.glDisable(GL11.GL_BLEND);
				menu.render();
				break;
			case PLAYING:
				GL11.glDisable(GL11.GL_BLEND);
				renderEverything();
				break;
			case PAUSED:
				renderEverything();
				pauseMenu.render();
				break;
			case GAMEOVER:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void renderEverything() {
		sky.render();
		level.render((int) sX, (int) sY, (getGameWidth() / Tile.tileSize) + 2, (getGameHeight() / Tile.tileSize) + 2);
		player.render();
		entityTicker.render((int) sX, (int) sY);
		inventory.render();
		skills.render();
		gui.render();
		chat.render();
		achievements.render();
		drawName();
		crafting.render();
		for (int i = 0; i < level.chests.toArray().length; i++) {
			level.chests.get(i).render();
		}

		if (showDebug) {
			Util.drawStringWithShadow("FPS: ", 0, 0, 15, false);
			if (currentTime > lastTime + 1000) {
				Util.drawStringWithShadow("     " + fps, 0, 0, 15, false);
				fps = 0;
				lastTime = getTime();
			}
			Util.drawStringWithShadow("X: " + (Math.round(player.trueX / (Tile.tileSize))), 0, (10 * 3), 10 * 3, false);
			Util.drawStringWithShadow("Y: " + (Math.round(player.y / (Tile.tileSize))), 0, (20 * 3), 10 * 3, false);
			Util.drawStringWithShadow("Biome: " + level.getBiomePlayerIsIn(), 0, (30 * 3), 10 * 3, false);
		}

	}

	public void drawName() {
		// Util.fillColoredRect(new Color(0, 0, 0, 90), Core.getGameWidth() / 2 - Util.getStringLength(username, 15) - 5, Core.getGameHeight() / 2 - 45, Core.getGameWidth() / 2 - Util.getStringLength(username, 15) + 5, 15, true);
		Util.drawStringWithShadow(username, Core.getGameWidth() / 2 - Util.getStringLength(username, 15), Core.getGameHeight() / 2 - 45, 15, false, checkStatus());
	}

	private boolean isDeveloper() {
		if (username.equals("DRAGONMASTER412")) return true; // Luke
		if (username.equals("Mrwayfarout")) return true; // Matthew
		if (username.equals("_TheCrazyCat")) return true; // Ross
		if (username.equals("Team_Aliquam")) return true; // All

		return false;
	}

	private Color checkStatus() {
		if (isDeveloper()) {
			return Color.yellow;
		} else {
			return Color.white;
		}
	}

	public void tick() {
		mouseX = Mouse.getX();
		mouseY = height - Mouse.getY() - 1;
		if (Mouse.isButtonDown(0)) isMouseLeft = true;
		else isMouseLeft = false;
		if (Mouse.isButtonDown(1)) isMouseRight = true;
		else isMouseRight = false;
		if (isMouseRight || isMouseLeft) clicked();
		if (keyboardListener != null) keyboardListener.input();

		switch (state) {
		case SPLASHSCREEN:
			splash.tick();
			if (splash.waiting && !loaded) {
				loaded = true;
				loadObjects();
			}
			if (splash.finished) {
				state = State.MENU;
			}
			break;
		case INITIALIZED:
			state = State.MENU;
			break;
		case MENU:
			menu.tick();
			player.fallAtMenu();
			break;
		case PLAYING:
			sky.tick();
			level.tick((int) sX, (int) sY, getGameWidth() / Tile.tileSize + 2, getGameHeight() / Tile.tileSize + 2);
			animation.tick();
			inventory.tick();
			player.tick();
			skills.tick();
			gui.tick();
			entityTicker.tick((int) sX, (int) sY);
			spawner.tick();
			achievements.tick();
			crafting.tick();
			for (int i = 0; i < level.chests.toArray().length; i++) {
				level.chests.get(i).tick();
			}
			physics.tick((int) sX, (int) sY, getGameWidth() / Tile.tileSize + 2, getGameHeight() / Tile.tileSize + 2);
			break;
		case PAUSED:
			pauseMenu.tick();
			break;
		case GAMEOVER:
			break;
		}
	}

	public long lastMouseClick = getTime();
	public byte clickTime = 75;

	@SuppressWarnings("static-access")
	public void clicked() {
		if (lastMouseClick + clickTime < currentTime) {
			lastMouseClick = getTime();
			switch (state) {
			case MENU:
				menu.clicked(mouseX, mouseY);
				break;
			case PLAYING:
				if (inventory.isOpen) {
					if (isMouseRight) inventory.click(0);
					if (isMouseLeft) inventory.click(1);
				}
				if (crafting.isOpen) {
					if (isMouseLeft) crafting.clicked(mouseX, mouseY);
				}
				for (int i = 0; i < level.chests.toArray().length; i++) {
					if (level.chests.get(i).isOpen) {
						if (isMouseRight) level.chests.get(i).click(0);
						if (isMouseLeft) level.chests.get(i).click(1);
					}
				}
				break;
			case PAUSED:
				pauseMenu.clicked(mouseX, mouseY);
				break;
			}
		}
	}

	public static void glBeginRendering() {
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glCullFace(GL11.GL_BACK);

		// could look at that source | http://lwjgl.org/wiki/index.php?title=Slick-Util_Library_-_Part_3_-_TrueType_Fonts_for_LWJGL
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		// GL11.glShadeModel(GL11.GL_SMOOTH);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDisable(GL11.GL_LIGHTING);

		// GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		// GL11.glClearDepth(1);
		// GL11.glClearAccum(0.0f, 0.0f, 0.0f, 0.0f);

		// enable transparency
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		GL11.glViewport(0, 0, getGameWidth(), getGameHeight());
		GL11.glMatrixMode(GL11.GL_MODELVIEW);

		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, getGameWidth(), getGameHeight(), 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);

		// GL11.glDrawBuffer(GL11.GL_FRONT_AND_BACK);// proberbly bad idea to call causes lag <---- CAUSES HUGE LAG
		// GL11.glDrawBuffer(GL11.GL_BACK);

	}

	public static void clearScreen() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
	}

	public static void cleanUp(int val) {
		Display.destroy();
		System.exit(val);
	}

	public static void main(String[] args) {
		// final String userDir = System.getProperty("user.dir");
		// final String nativeLibDir = userDir + File.separator + "libs" + File.separator + "native";
		// System.setProperty("org.lwjgl.librarypath", nativeLibDir);
		Display.setTitle(fullTitle);
		try {
			if (!fullscreen) {
				Display.setDisplayMode(new DisplayMode(width, height));
			} else {
				Display.setFullscreen(fullscreen);
			}
			Display.setResizable(true);
			Display.create();
			// initIcon();
			// Display.setVSyncEnabled(true);
			clearScreen();
			glBeginRendering();
		} catch (LWJGLException e) {

		}

		Core core = new Core();
		running = true;
		while (!Display.isCloseRequested()) {

			core.render();

			long currentTime = core.getTime();
			core.delta += (currentTime - core.previousTime) / core.upsTime;
			core.previousTime = currentTime;

			if (core.delta >= 1) {
				core.tick();
				core.delta = 0;
			}

			// Display.sync(UPDATE_RATE);

			Display.update();
			// Display.setParent(AWT canvas);
			// System.out.println("Resized: " + Display.wasResized());
			// try {
			// if (Display.wasResized()) {
			// Display.setDisplayMode(new DisplayMode(getGameWidth(), getGameHeight()));
			// }
			// } catch (LWJGLException e) {
			// e.printStackTrace();
			// }
		}
		running = false;

		cleanUp(0);

	}
	
	private double upsTime = 1000.0 / UPDATE_RATE;
	private double delta = 0;
	private long previousTime = getTime();

	public static void initIcon() {
		// Texture texture = TextureLoader.getTexture("BMP",new FileInputStream("res/logos/Paradigm_Favicon.bmp"));
		Texture texture1;
		try {
			texture1 = TextureLoader.getTexture("PNG", new FileInputStream("res/logos/Paradigm_Favicon.png"));
			ByteBuffer[] icons = new ByteBuffer[3];

			// icons[0] = createIcon(16, 16, true, false, texture);
			icons[0] = createIcon(16, 16, true, false, texture1);
			icons[1] = createIcon(32, 32, false, false, texture1);
			icons[2] = createIcon(128, 128, true, true, texture1);
			if (icons != null) Display.setIcon(icons);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static ByteBuffer createIcon(int width, int height, boolean fixAlphas, boolean makeBlackTransparent, Texture texture) {
		// Save original draw buffer
		int drawBuffer = GL11.glGetInteger(GL11.GL_DRAW_BUFFER);

		// Draw & stretch a width by height icon onto the back buffer
		GL11.glDrawBuffer(GL11.GL_BACK);
		texture.bind();
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex2f(0, 0);
		GL11.glTexCoord2f(1, 0);
		GL11.glVertex2f(width, 0);
		GL11.glTexCoord2f(1, 1);
		GL11.glVertex2f(width, height);
		GL11.glTexCoord2f(0, 1);
		GL11.glVertex2f(0, height);
		GL11.glEnd();

		// Read the back buffer into the byte buffer icon
		GL11.glReadBuffer(GL11.GL_BACK);
		ByteBuffer icon = BufferUtils.createByteBuffer(width * height * 4);
		GL11.glReadPixels(0, 0, width, height, GL11.GL_RGBA, GL11.GL_BYTE, icon);

		// fixAlphas: In case of OpenGL blending and/or bitmap problems
		// makeBlackTransparent: Cycle through and set black to be transparent
		if (fixAlphas || makeBlackTransparent) {
			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					int color = y * 4 * width + x * 4;
					int red = icon.get(color);
					int green = icon.get(color + 1);
					int blue = icon.get(color + 2);

					if (makeBlackTransparent && red == 0 && green == 0 && blue == 0) {
						icon.put(color + 3, (byte) 0);
					} else if (fixAlphas) {
						icon.put(color + 3, (byte) 255);
					}
				}
				// System.out.println();
			}
		}

		// Set back to original draw buffer
		GL11.glDrawBuffer(drawBuffer);

		return (icon);
	}

	public static int getGameWidth() {
		// return width;
		return Display.getWidth();
	}

	public static int getGameHeight() {
		// return height;
		return Display.getHeight();
	}

	private int fps = 0;
	private long currentTime = getTime();
	private long lastTime = getTime();

	public long getTime() {
		return Sys.getTime() * 1000 / Sys.getTimerResolution();
	}

	public static String randomMessage() {
		int choice = new Random().nextInt(31);
//		int choice = 30;

		switch (choice) {
		case 0:
			return "Also Check Out Steam";
		case 1:
			return "Maybe No Dragons";
		case 2:
			return "Infinite worlds!";
		case 3:
			return "See Allergy Advice";
		case 4:
			return "Contains No Nuts!";
		case 5:
			return "95% pure";
		case 6:
			return "Dr. Who?";
		case 7:
			return "Rambo Never Dies!";
		case 8:
			return "You Got Terminated!";
		case 9:
			return "Good choice";
		case 10:
			return "Also Check Out TDF";
		case 11:
			return "#Code";
		case 12:
			return "The Art of Art!";
		case 13:
			return "Also Check Out Fez";
		case 14:
			return "Do You Like Waffles?";
		case 15:
			return "310800!";
		case 16:
			return "100% Plastic Food";
		case 17:
			return "Carpe Diem";
		case 18:
			return "Let's Kick Some Ice!";
		case 19:
			return "I Ain't Got Time To Bleed!";
		case 20:
			return "Get To Da Choppa!";
		case 21:
			return "Dude Where's My Car?";
		case 22:
			return "A113!";
		case 23:
			return "Nien, Nien, Nien, Nien, Nien!";
		case 24:
			return "#Selfie!";
		case 25:
			return "Remember that time?";
		case 26:
			return "Made just for you <3";
		case 27:
			return state2;
		case 28:
			return "Multi-threaded";
		case 29:
			return "OpenGL!";
		case 30:
			try {
				return "Thanks For Playing " + InetAddress.getLocalHost().getHostName();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
//		case 31:
//			com.sun.security.auth.module.NTSystem NTSystem = new
//          com.sun.security.auth.module.NTSystem();
//			System.out.println(NTSystem.getName());
//			break;
		}
		return "MissingNo";

	}

}
