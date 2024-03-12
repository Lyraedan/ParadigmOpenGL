package com.aliquamgames.paradigm.menu;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;

import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.colorchooser.AbstractColorChooserPanel;

import org.lwjgl.util.Rectangle;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import com.aliquamgames.paradigm.Core;
import com.aliquamgames.paradigm.Core.State;
import com.aliquamgames.paradigm.ImageLoader;
import com.aliquamgames.paradigm.util.Util;

public class CreateACharacterMenu extends Menu {

	// TODO MAKE IT SO THAT IF TWO OF THE BUTTONS ON THE RIGHT SIDE ARE THE SAME COLOR (SO THE IMAGE HAS TWO OF THE SAME COLOR)
	// THAT WILL MAKE A CHANGE AFFECT BOTH THE TWO BECAUSE THEY ARE THE SAME COLOR.. SO MAKE IT SO THAT THIS DOESNT HAPPEN
	// POSSIBLY BY MAKING ONE OF THEM HAVE ONE LESS R VALUE

	// this is the background image
	private Texture background;
	// the player image. this is the larger one made exclusively for this class
	private Texture playerImage;

	// the array that contains the rectangles around the buttons
	private Rectangle[] rects = new Rectangle[10];
	// the array that contains the rectangles around the color rects
	private Rectangle[] colorRects = new Rectangle[4];

	// controls if the player is a boy or a girl
	private boolean isBoy = true;

	// the direction the player is facing
	private int dir = 1;
	// the width of the player
	private int width = 230;
	// the height of the player
	private int height = 330;
	// the width of the playerImage
	private int imageWidth = width * 2;
	// the height of the playerImage
	private int imageHeight = 330;
	// the x in the image where the male player starts
	private int maleX = 0;
	// the x in the image where the female player starts
	private int femaleX = 231;
	// the x for the player rendering
	private int x = Core.getGameWidth() / 2 - width / 2;
	// the y for the player rendering
	private int y = Core.getGameHeight() / 2 - height / 2;
	// the offset on the x for the player rendering
	private int xOffset = -5;
	// the offset on the y for the player rendering
	private int yOffset = -50;
	// the width of the buttons on the left
	private int slotWidth = 140;
	// the height of the buttons on the left
	private int slotHeight = 34;
	// the width of the buttons on the right
	private int squareWidth = 57;
	// the height of the buttons on the right
	private int squareHeight = 40;

	// the ARGB for the skin color on the image
	private static int skinColorOnImage = shifter(255, 255, 255, 255);
	// the ARGB for the hair color on the image
	private static int hairColorOnImage = shifter(76, 76, 76, 255);
	// the ARGB for the shirt color on the image
	private static int shirtColorOnImage = shifter(87, 87, 87, 255);
	// the ARGB for the shoe color on the image
	private static int shoeColorOnImage = shifter(15, 15, 15, 255);
	// the ARGB for the belt color on the image
	private static int beltColorOnImage = shifter(29, 29, 29, 255);
	// the ARGB for the back hair color on the image
	private static int backHairColorOnImage = shifter(151, 151, 151, 255);
	// the ARGB for the back face color on the image
	private static int backFaceColorOnImage = shifter(198, 198, 198, 255);
	// the ARGB for the bottom face color on the image
	private static int bottomFaceColorOnImage = shifter(171, 171, 171, 255);
	// the ARGB for the eye and mouth color on the image
	private static int eyeColorOnImage = shifter(36, 36, 36, 255);
	// the ARGB for the eye and mouth color on the image
	private static int mouthColorOnImage = shifter(36, 36, 36, 255);

	// the ARGB for the skin color to be set to the image
	private static int skinColor = shifter(255, 178, 127, 255);
	// the ARGB for the hair color to be set to the image
	private static int hairColor = shifter(127, 51, 0, 255);
	// the ARGB for the shirt color to be set to the image
	private static int shirtColor = shifter(0, 38, 255, 255);
	// the ARGB for the shoe color to be set to the image
	private static int shoeColor = shifter(15, 15, 15, 255);
	// the ARGB for the belt color to be set to the image
	private static int beltColor = shifter(29, 29, 29, 255);
	// the ARGB for the back hair color to be set to the image
	private static int backHairColor = shifter(175, 70, 0, 255);
	// the ARGB for the back face color to be set to the image
	private static int backFaceColor = shifter(226, 156, 113, 255);
	// the ARGB for the bottom face color to be set to the image
	private static int bottomFaceColor = shifter(226, 156, 113, 255);
	// the ARGB for the eye and mouth color on the image
	private static int eyeColor = shifter(36, 36, 36, 255);
	// the ARGB for the eye and mouth color on the image
	private static int mouthColor = shifter(36, 36, 36, 255);

	// the method to handle the shifting
	private static int shifter(int r, int g, int b, int a) {
		return (a << 24 | r << 16 | g << 8 | b);
	}

	// these are the colors that will be set to the player in the game
	public static int[] colors = { skinColor, hairColor, shirtColor, shoeColor, beltColor, backHairColor, backFaceColor, bottomFaceColor, eyeColor, mouthColor };
	// the default colors
	private int[] defaultColors = colors;
	// these are the colors on the player image
	private static int[] colorsOnImage = { skinColorOnImage, hairColorOnImage, shirtColorOnImage, shoeColorOnImage, beltColorOnImage, backHairColorOnImage, backFaceColorOnImage, bottomFaceColorOnImage, eyeColorOnImage, mouthColorOnImage };
	// the default colors on the image
	private int[] defaultColorsOnImage = colorsOnImage;

	// the name of the player
	public static String name = "Player";
	// the race of the player
	public static String race = "Human";
	// the gender of the player
	public static String gender = "Boy";

	// the Color for the box on the right for the skin
	private Color skinRectColor = new Color(255, 178, 127, 255);
	// the Color for the box on the right for the hair
	private Color hairRectColor = new Color(127, 51, 0, 255);
	// the Color for the box on the right for the shirt
	private Color shirtRectColor = new Color(0, 38, 255, 255);
	// the Color for the box on the right for the shoe
	private Color shoeRectColor = new Color(15, 15, 15, 255);

	// the colors for the rects on the right
	private Color[] colorsForColorRects = { skinRectColor, hairRectColor, shirtRectColor, shoeRectColor };
	// the default colors for the rects on the right
	private Color[] defaultColorsForColorRects = colorsForColorRects;

	private JColorChooser cc = new JColorChooser();
	AbstractColorChooserPanel[] panels;

	public CreateACharacterMenu() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, IOException {

		playerImage = ImageLoader.loadSlick("PNG", "/GUI/createACharacterMenu/player.png");

		// playerImage = createNewImageWithColor();

		background = ImageLoader.loadSlick("PNG", "/GUI/createACharacterMenu/background.png");

		cc.setPreviewPanel(null);
		AbstractColorChooserPanel[] colorPanels = cc.getChooserPanels();
		for (int i = 1; i < colorPanels.length; i++) {
			AbstractColorChooserPanel cp = colorPanels[i];

			Field f = cp.getClass().getDeclaredField("panel");
			f.setAccessible(true);

			Object colorPanel = f.get(cp);
			Field f2 = colorPanel.getClass().getDeclaredField("spinners");
			f2.setAccessible(true);
			Object spinners = f2.get(colorPanel);

			Object transpSlispinner = Array.get(spinners, 3);
			if (i == colorPanels.length - 1) {
				transpSlispinner = Array.get(spinners, 4);
			}
			Field f3 = transpSlispinner.getClass().getDeclaredField("slider");
			f3.setAccessible(true);
			JSlider slider = (JSlider) f3.get(transpSlispinner);
			slider.setEnabled(false);
			slider.setVisible(false);
			Field f4 = transpSlispinner.getClass().getDeclaredField("spinner");
			f4.setAccessible(true);
			JSpinner spinner = (JSpinner) f4.get(transpSlispinner);
			spinner.setEnabled(false);
			spinner.setVisible(false);

			Field f5 = transpSlispinner.getClass().getDeclaredField("label");
			f5.setAccessible(true);
			JLabel label = (JLabel) f5.get(transpSlispinner);
			label.setVisible(false);
		}
		panels = cc.getChooserPanels();
		;

		rects[0] = new Rectangle(88, 111, slotWidth, slotHeight);
		rects[1] = new Rectangle(88, 216, slotWidth, slotHeight);
		rects[2] = new Rectangle(88, 345, slotWidth, slotHeight);
		rects[3] = new Rectangle(611, 108, squareWidth, squareHeight);
		rects[4] = new Rectangle(611, 210, squareWidth, squareHeight);
		rects[5] = new Rectangle(611, 319, squareWidth, squareHeight);
		rects[6] = new Rectangle(611, 420, squareWidth, squareHeight);
		rects[7] = new Rectangle(76, 531, 159, 44);
		rects[8] = new Rectangle(560, 535, 159, 44);
		rects[9] = new Rectangle(318, 505, 160, 44);

		colorRects[0] = rects[3];
		colorRects[1] = rects[4];
		colorRects[2] = rects[5];
		colorRects[3] = rects[6];

	}

	// private Texture createNewImageWithColor() {
	// for (int i = 0; i < colors.length; i++) {
	// if (i >= colorsForColorRects.length) break;
	// colors[i] = shifter(colorsForColorRects[i].getRed(), colorsForColorRects[i].getGreen(), colorsForColorRects[i].getBlue(), colorsForColorRects[i].getAlpha());
	// }
	// colors[5] = shifter(colorsForColorRects[1].getRed() + 38, colorsForColorRects[1].getGreen() + 19, colorsForColorRects[1].getBlue(), colorsForColorRects[1].getAlpha());
	// colors[6] = shifter(colorsForColorRects[0].getRed() - 29, colorsForColorRects[0].getGreen() - 22, colorsForColorRects[0].getBlue() - 14, colorsForColorRects[0].getAlpha());
	// colors[7] = shifter(colorsForColorRects[0].getRed() - 29, colorsForColorRects[0].getGreen() - 22, colorsForColorRects[0].getBlue() - 14, colorsForColorRects[0].getAlpha());
	//
	// Texture newImage = new Texture(imageWidth, imageHeight, BufferedImage.TYPE_4BYTE_ABGR);
	// for (int x = 0; x < imageWidth; x++) {
	// for (int y = 0; y < imageHeight; y++) {
	// for (int i = 0; i < colorsOnImage.length; i++) {
	// if (playerImage.getRGB(x, y) == colorsOnImage[i]) {
	// newImage.setRGB(x, y, colors[i]);
	// }
	// }
	// }
	// }
	// for (int i = 0; i < colorsOnImage.length; i++) {
	// colorsOnImage[i] = colors[i];
	// }
	// return newImage;
	// }

	public void tick() {
		colorsForColorRects[0] = skinRectColor;
		colorsForColorRects[1] = hairRectColor;
		colorsForColorRects[2] = shirtRectColor;
		colorsForColorRects[3] = shoeRectColor;

		if (Core.isLeft) dir = -1;
		if (Core.isRight) dir = 1;
		if (gender == "Boy") isBoy = true;
		else isBoy = false;
	}

	public void render() {

		Util.drawImage(background, 0, 0, Core.getGameWidth(), Core.getGameHeight(), 0, 0, background.getWidth(), background.getHeight(), true);
		renderCharacter();
		for (int i = 0; i < colorsForColorRects.length; i++) {
			Util.fillColoredRect(colorsForColorRects[i], colorRects[i].getX(), colorRects[i].getY(), colorRects[i].getWidth(), colorRects[i].getHeight());
		}

		Util.drawStringWithShadow(name, 95, 135, 14, true, Color.white);
		Util.drawStringWithShadow(race, 130, 240, 14, true, Color.white);
		Util.drawStringWithShadow(gender, 140, 370, 14, true, Color.white);
	}

	@SuppressWarnings("static-access")
	public void clicked(int x, int y) {
		if (rects[0].contains(x, y)) {
			name = JOptionPane.showInputDialog(null, "Set your name!", "Set your name!", JOptionPane.PLAIN_MESSAGE);
		} else if (rects[1].contains(x, y)) {

		} else if (rects[2].contains(x, y)) {
			if (gender == "Boy") gender = "Girl";
			else gender = "Boy";
		} else if (rects[3].contains(x, y)) {
			try {
				skinRectColor = createJChooserMenu("Skin");
			} catch (Exception e) {
				e.printStackTrace();
			}
			// playerImage = createNewImageWithColor();
			// playerImage = createNewImageWithColor();
		} else if (rects[4].contains(x, y)) {
			try {
				hairRectColor = createJChooserMenu("Hair");
			} catch (Exception e) {
				e.printStackTrace();
			}
			// playerImage = createNewImageWithColor();
			// playerImage = createNewImageWithColor();
		} else if (rects[5].contains(x, y)) {
			try {
				shirtRectColor = createJChooserMenu("Shirt");
			} catch (Exception e) {
				e.printStackTrace();
			}
			// playerImage = createNewImageWithColor();
			// playerImage = createNewImageWithColor();
		} else if (rects[6].contains(x, y)) {
			try {
				shoeRectColor = createJChooserMenu("Shoes");
			} catch (Exception e) {
				e.printStackTrace();
			}
			// playerImage = createNewImageWithColor();
			// playerImage = createNewImageWithColor();
		} else if (rects[7].contains(x, y)) {
			menuState = MenuState.PROFILESELECT;
		} else if (rects[8].contains(x, y)) {
			System.out.println("CREATEACHARACTERMENU: TODO: SET PLAYER ATTRIBUTES. SAVE THE FILE CONTAINING THIS STUFF");
			// Player.isBoy = isBoy;
			// System.out.println(Core.player.isBoy);
			// Core.player.playerImage = Core.player.createNewImageWithColor(colors);
			Core.state = State.PLAYING;
		} else if (rects[9].contains(x, y)) {
			reset();
		}
	}

	public void reset() {
		colorsOnImage = defaultColorsOnImage;
		colors = defaultColors;
		skinRectColor = defaultColorsForColorRects[0];
		hairRectColor = defaultColorsForColorRects[1];
		shirtRectColor = defaultColorsForColorRects[2];
		shoeRectColor = defaultColorsForColorRects[3];
		for (int i = 0; i < colorsForColorRects.length; i++) {
			colorsForColorRects[i] = defaultColorsForColorRects[i];
		}
		try {
			playerImage = ImageLoader.loadSlick("PNG", "/GUI/createACharacterMenu/player.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		// playerImage = createNewImageWithColor();
	}

	public void renderCharacter() {
		if (isBoy) {
			if (dir == 1) {
				Util.drawImage(playerImage, x + xOffset, y + yOffset, x + xOffset + width, y + yOffset + height, maleX + width, 0, maleX, height, true);
			} else if (dir == -1) {
				Util.drawImage(playerImage, x + xOffset, y + yOffset, x + xOffset + width, y + yOffset + height, maleX, 0, maleX + width, height, true);
			}
		} else {
			if (dir == 1) {
				Util.drawImage(playerImage, x, y + yOffset, x + width, y + yOffset + height, femaleX + width, 0, femaleX, height, true);
			} else if (dir == -1) {
				Util.drawImage(playerImage, x, y + yOffset, x + width, y + yOffset + height, femaleX, 0, femaleX + width, height, true);
			}
		}
	}

	private Color createJChooserMenu(String part) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		java.awt.Color col = null;

		if (part == "Hair") col = new java.awt.Color(hairRectColor.getRed(), hairRectColor.getGreen(), hairRectColor.getBlue(), hairRectColor.getAlpha());
		else if (part == "Skin") col = new java.awt.Color(skinRectColor.getRed(), skinRectColor.getGreen(), skinRectColor.getBlue(), skinRectColor.getAlpha());
		else if (part == "Shirt") col = new java.awt.Color(shirtRectColor.getRed(), shirtRectColor.getGreen(), shirtRectColor.getBlue(), shirtRectColor.getAlpha());
		else if (part == "Shoes") col = new java.awt.Color(shoeRectColor.getRed(), shoeRectColor.getGreen(), shoeRectColor.getBlue(), shoeRectColor.getAlpha());

		cc.setColor(col);

		for (AbstractColorChooserPanel accp : panels) {
			if (accp.getDisplayName().equals("RGB")) {
				JOptionPane.showMessageDialog(null, accp);
			}
		}

		return new Color(cc.getColor().getRed(), cc.getColor().getGreen(), cc.getColor().getBlue(), cc.getColor().getAlpha());
	}

	public void mouseHover() {

	}

}
