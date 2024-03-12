package com.aliquamgames.paradigm.playing;

import java.awt.image.BufferedImage;

import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import com.aliquamgames.paradigm.Core;
import com.aliquamgames.paradigm.util.Util;

public class GUI {
	// the number of hearts
	public int numberOfHearts = 10;
	// the size of the hearts
	private int heartSize = Tile.tileSize;
	// the x to start at
	private int x = 17;
	// this is the starting x for the second row of hearts
	private int x2 = 17;
	// the y for the hearts
	private int y = Core.getGameHeight() - heartSize - 2;
	// the number of food
	public int numberOfFood = 10;
	// the size of the food
	public int foodSize = heartSize;
	// the number of armor
	public int numberOfArmor = 10;
	// the size of the armor
	public int armorSize = heartSize;
	// the length of the XP bar
	private int lengthOfXPBar = 675;

	// the Texture array containing the armor images
	public Texture[] armorImages = new Texture[numberOfArmor];
	// the Texture array containing the food images
	public Texture[] foodImages = new Texture[numberOfFood];
	// the Texture array containing the heart images
	public Texture[] heartImages = new Texture[numberOfHearts];
	
	public BufferedImage halfHeart;
	// the half hunger image
	public BufferedImage halfHunger;
	// the half armor image
	public BufferedImage halfArmor;

	public GUI() {
		x += heartSize * (numberOfHearts / 2 - 1);
		x2 = 17;
		// TODO FIX THIS WHEN WE FIGURE OUT A METHOD TO CREATE IMAGES
//		 createHalfHeartImage();
//		 createHalfHungerImage();
//		 createHalfArmorImage();
		for (int i = 0; i < heartImages.length; i++) {
			heartImages[i] = Tile.heart;
		}
		for (int i = 0; i < foodImages.length; i++) {
			foodImages[i] = Tile.hunger;
		}
		for (int i = 0; i < armorImages.length; i++) {
			armorImages[i] = Tile.armor;
		}
	}

	@SuppressWarnings("static-access")
	public void tick() {

		for (int i = 0; i < heartImages.length; i++) {
			if (Core.player.maxHealth / numberOfHearts * i + 10 > Core.player.health && Core.player.maxHealth / numberOfHearts * i + 5 < Core.player.health) {
				heartImages[numberOfHearts - 1 - i] = Tile.half_heart;
			} else if (Core.player.maxHealth / numberOfHearts * i + 5 > Core.player.health && Core.player.maxHealth / numberOfHearts * i < Core.player.health) {
				heartImages[numberOfHearts - 1 - i] = Tile.heart_damaged;
			} else if (heartImages[numberOfHearts - 1 - i] != Tile.half_heart && heartImages[numberOfHearts - 1 - i] != Tile.heart_damaged) {
				heartImages[numberOfHearts - 1 - i] = Tile.heart;
			}
		}

		for (int i = 0; i < foodImages.length; i++) {
			if (Core.player.maxHunger / numberOfFood * i < Core.player.hunger && Core.player.maxHunger / numberOfFood * i + 0.5 >= Core.player.hunger) {
				foodImages[numberOfFood - 1 - i] = Tile.hunger_damage;
			} else if (Core.player.maxHunger / numberOfFood * i + 0.5 < Core.player.hunger && Core.player.maxHunger / numberOfFood * i + 1 >= Core.player.hunger) {
				foodImages[numberOfFood - 1 - i] = Tile.half_hunger;
			} else if (foodImages[numberOfFood - 1 - i] != Tile.half_hunger && foodImages[numberOfFood - 1 - i] != Tile.hunger_damage) {
				foodImages[numberOfFood - 1 - i] = Tile.hunger;
			}
		}

		for (int i = 0; i < armorImages.length; i++) {
			if (Core.player.maxArmor / numberOfArmor * i < Core.player.armor && Core.player.maxArmor / numberOfArmor * i + 0.5 >= Core.player.armor) {
				armorImages[numberOfArmor - 1 - i] = Tile.armor_damage;
			} else if (Core.player.maxArmor / numberOfArmor * i + 0.5 < Core.player.armor && Core.player.maxArmor / numberOfArmor * i + 1 >= Core.player.armor) {
				armorImages[numberOfArmor - 1 - i] = Tile.half_armor;
			} else if (armorImages[numberOfArmor - 1 - i] != Tile.half_armor && armorImages[numberOfArmor - 1 - i] != Tile.armor_damage) {
				armorImages[numberOfArmor - 1 - i] = Tile.armor;
			}
		}
	}

	public void drawXp(int x, int y) {
		Util.fillColoredRect(Color.gray, x, y, lengthOfXPBar + 2, 30);
		Util.fillColoredRect(Color.black, x + 1, y + 1, lengthOfXPBar, 24);

		if (Core.player.xp > 0) Util.fillColoredRect(Color.green, x + 1, y + 1, (int) (((double) Core.player.xp / (double) Core.player.maxXp) * lengthOfXPBar), 24);

		if (Core.player.level > 0) {
			// g.setColor(Color.BLACK);
			// g.setFont(new Font(ImageLoader.font, Font.BOLD, 40));
			// TODO FIX THIS WHEN TEXT WORKS

			Util.drawStringWithShadow("" + Core.player.level, Core.getGameWidth() / 2 - 4, y - 30, 10 * 3, false, Color.green);
		}
	}

	public void render() {
		drawXp(x - ((Core.getGameWidth() - lengthOfXPBar)) - 28, y - heartSize - 40);
		for (int i = 0; i < heartImages.length; i++) {
			if (i < numberOfHearts / 2) Util.drawImage(heartImages[i], (x - (heartSize * i)), y - heartSize, heartSize, heartSize, true);
			else Util.drawImage(heartImages[i], x2 + (heartSize * (numberOfHearts - i - 1)), y, heartSize, heartSize, true);
		}
		for (int i = 0; i < foodImages.length; i++) {
			if (i < numberOfFood / 2) Util.drawImage(foodImages[i], (Core.getGameWidth() - (foodSize * i) - 60), y - foodSize, foodSize, foodSize, true);
			else Util.drawImage(foodImages[i], (Core.getGameWidth() - (foodSize * (numberOfFood - i - 1)) - 60), y, foodSize, foodSize, true);
		}
		if(Core.player.armor > 0) {
		for (int i = 0; i < armorImages.length; i++) {
			if (i < numberOfArmor / 2) Util.drawImage(armorImages[i], (x - (armorSize * i) + 270), y - armorSize, armorSize, armorSize, true);
			else Util.drawImage(armorImages[i], x2 + (armorSize * (numberOfArmor - i - 1) + 270), y, armorSize, armorSize, true);
		}
	}
  }
	
	public void createHalfHeartImage() {
		halfHeart = new BufferedImage(Tile.Bheart.getWidth(), Tile.Bheart.getHeight(), BufferedImage.TYPE_INT_ARGB);
		for (int x = 0; x < halfHeart.getWidth() / 2; x++) {
			for (int y = 0; y < halfHeart.getHeight(); y++) {
				halfHeart.setRGB(x, y, Tile.Bheart.getRGB(x, y));
			}
		}
		for (int x = halfHeart.getWidth() / 2 - 1; x < halfHeart.getWidth(); x++) {
			for (int y = 0; y < halfHeart.getHeight(); y++) {
				halfHeart.setRGB(x, y, Tile.Bheart_damaged.getRGB(x, y));
			}
		}
		Util.BufferedImageToTexture(halfHeart);
	}

	public void createHalfHungerImage() {
		halfHunger = new BufferedImage(Tile.Bhunger.getWidth(), Tile.Bhunger.getHeight(), BufferedImage.TYPE_INT_ARGB);
		for (int x = 0; x < halfHunger.getWidth() / 2; x++) {
			for (int y = 0; y < halfHeart.getHeight(); y++) {
				halfHunger.setRGB(x, y, Tile.Bhunger.getRGB(x, y));
			}
		}

		for (int x = halfHunger.getWidth() / 2 - 1; x < halfHunger.getWidth(); x++) {
			for (int y = 0; y < halfHunger.getHeight(); y++) {
				halfHunger.setRGB(x, y, Tile.Bhunger_damage.getRGB(x, y));
			}
		}
		Util.BufferedImageToTexture(halfHunger);
	}

	public void createHalfArmorImage() {
		halfArmor = new BufferedImage(Tile.Barmor.getWidth(), Tile.Barmor.getHeight(), BufferedImage.TYPE_INT_ARGB);
		for (int x = 0; x < halfArmor.getWidth() / 2; x++) {
			for (int y = 0; y < halfArmor.getHeight(); y++) {
				halfArmor.setRGB(x, y, Tile.Barmor.getRGB(x, y));
			}
		}

		for (int x = halfArmor.getWidth() / 2 - 1; x < halfArmor.getWidth(); x++) {
			for (int y = 0; y < halfArmor.getHeight(); y++) {
				halfArmor.setRGB(x, y, Tile.Barmor_damage.getRGB(x, y));
			}
		}
		Util.BufferedImageToTexture(halfArmor);
	}
}
