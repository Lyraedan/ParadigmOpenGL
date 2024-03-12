package com.aliquamgames.paradigm.playing;

import org.newdawn.slick.Color;

import com.aliquamgames.paradigm.Core;
import com.aliquamgames.paradigm.inventory.Inventory;
import com.aliquamgames.paradigm.util.Util;

public class Crafting {

	public static boolean isOpen = true;
	private int width = 500;
	private int height = 315;
	private boolean checkedRecipes = false;

	private int iconWidth = Tile.tileSize;
	private int iconHeight = Tile.tileSize;
	private int spaceBetweenIcons = 13;

	private int[][] tiles = { Tile.stone, Tile.water, Tile.anvil, Tile.bookShelf, Tile.chest, Tile.craftingTable, Tile.wall, Tile.vines, Tile.fire, Tile.tnt, Tile.star, Tile.smoke, Tile.rubyOverlay3, Tile.coalOverlay3, Tile.arythimilInSandstone, Tile.roses, Tile.woodenPole, Tile.bedrock, Tile.chiseledStoneBlock, Tile.copperInSandstone, Tile.arythimilOverlay, Tile.bedrock, Tile.fireWithBase, Tile.emeraldInSandstone, Tile.hellStone, Tile.lilies, Tile.logPillar, Tile.sandstone, Tile.sandstonePillar, Tile.smoke, Tile.smoothStonePillar, Tile.smoothStoneWallChiseledMiddle, Tile.diamondInSandstone, Tile.dirtLight, Tile.dirtLightWithSnow, Tile.dirtWithGrass, Tile.furnaceOn, Tile.glass, Tile.goldInSandstone, Tile.goldOverlay };
	private boolean[] canBeMade = { false, false, true, true, true, false, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true };

	private int x = 250;
	private int y = 300;
	private int yOffset = 50;

	public Crafting() {
		initialize();
	}

	public void initialize() {
		canBeMade[0] = recipe(Tile.dirt, Tile.dirt, Tile.dirt, Tile.dirt, Tile.dirt, Tile.dirt, Tile.dirt, Tile.dirt, Tile.dirt, Tile.stone, 4);
		canBeMade[1] = recipe(Tile.dirt, Tile.dirt, Tile.dirt, Tile.dirt, Tile.dirt, Tile.dirt, Tile.dirt, Tile.glass, Tile.dirt, Tile.water, 4);

		x = Core.getGameWidth() / 2 - width / 2 + spaceBetweenIcons;
		y = Core.getGameHeight() / 2 - height / 2 + spaceBetweenIcons - yOffset;
	}

	public void tick() {
		if (isOpen) {

		}
	}

	public void render() {
		// done in render cuz this has to be done in order to know what to render
		if (!checkedRecipes) {
			initialize();
			checkedRecipes = true;
		}

		if (isOpen) {
			Util.fillColoredRect(new Color(0, 0, 0, 150), Core.getGameWidth() / 2 - width / 2, Core.getGameHeight() / 2 - height / 2 - yOffset, width, height, true);
			int tempY = 0;
			for (int i = 0; i < tiles.length; i++) {
				int[] tile = tiles[i];

				int addX = ((i % 8) * (iconWidth + spaceBetweenIcons));
				int addY = 0;
				if (i != 0) if (i % 8 == 0) tempY++;
				addY = tempY * (iconHeight + spaceBetweenIcons);
				if (canBeMade[i]) {
					Util.fillTexturedRect(Tile.terrain, x + addX, y + addY, iconWidth, iconHeight, tile[0] * Tile.tileSize, tile[1] * Tile.tileSize, Tile.tileSize, Tile.tileSize, true);
				} else {
					Util.fillTexturedRect(Tile.terrain, x + addX, y + addY, iconWidth, iconHeight, tile[0] * Tile.tileSize, tile[1] * Tile.tileSize, Tile.tileSize, Tile.tileSize, true);
					Util.drawColoredX(new Color(255, 0, 0, 255), x + addX, y + addY, iconWidth, iconHeight, 5);
				}
			}
		}
	}

	public boolean recipe(int[] tile1, int[] tile2, int[] tile3, int[] tile4, int[] tile5, int[] tile6, int[] tile7, int[] tile8, int[] tile9, int[] product, int amount) {
		boolean[] booleans = new boolean[9];
		for (int i = 0; i < booleans.length; i++) {
			booleans[i] = false;
		}

		if (tile1 == null) booleans[0] = true;
		if (tile2 == null) booleans[1] = true;
		if (tile3 == null) booleans[2] = true;
		if (tile4 == null) booleans[3] = true;
		if (tile5 == null) booleans[4] = true;
		if (tile6 == null) booleans[5] = true;
		if (tile7 == null) booleans[6] = true;
		if (tile8 == null) booleans[7] = true;
		if (tile9 == null) booleans[8] = true;

		for (int i = 0; i < Inventory.invBar.length; i++) {
			int stack = Inventory.invBar[i].stack;
			if (Inventory.invBar[i].id == tile1 && stack - 1 >= 0) {
				booleans[0] = true;
				stack--;
			}
			if (Inventory.invBar[i].id == tile2 && stack - 1 >= 0) {
				booleans[1] = true;
				stack--;
			}
			if (Inventory.invBar[i].id == tile3 && stack - 1 >= 0) {
				booleans[2] = true;
				stack--;
			}
			if (Inventory.invBar[i].id == tile4 && stack - 1 >= 0) {
				booleans[3] = true;
				stack--;
			}
			if (Inventory.invBar[i].id == tile5 && stack - 1 >= 0) {
				booleans[4] = true;
				stack--;
			}
			if (Inventory.invBar[i].id == tile6 && stack - 1 >= 0) {
				booleans[5] = true;
				stack--;
			}
			if (Inventory.invBar[i].id == tile7 && stack - 1 >= 0) {
				booleans[6] = true;
				stack--;
			}
			if (Inventory.invBar[i].id == tile8 && stack - 1 >= 0) {
				booleans[7] = true;
				stack--;
			}
			if (Inventory.invBar[i].id == tile9 && stack - 1 >= 0) {
				booleans[8] = true;
				stack--;
			}
		}

		if (booleans[0] && booleans[1] && booleans[2] && booleans[3] && booleans[4] && booleans[5] && booleans[6] && booleans[7] && booleans[8]) return true;

		for (int i = 0; i < Inventory.invBag.length; i++) {
			int stack = Inventory.invBag[i].stack;
			if (Inventory.invBag[i].id == tile1 && stack - 1 >= 0) {
				booleans[0] = true;
				stack--;
			}
			if (Inventory.invBag[i].id == tile2 && stack - 1 >= 0) {
				booleans[1] = true;
				stack--;
			}
			if (Inventory.invBag[i].id == tile3 && stack - 1 >= 0) {
				booleans[2] = true;
				stack--;
			}
			if (Inventory.invBag[i].id == tile4 && stack - 1 >= 0) {
				booleans[3] = true;
				stack--;
			}
			if (Inventory.invBag[i].id == tile5 && stack - 1 >= 0) {
				booleans[4] = true;
				stack--;
			}
			if (Inventory.invBag[i].id == tile6 && stack - 1 >= 0) {
				booleans[5] = true;
				stack--;
			}
			if (Inventory.invBag[i].id == tile7 && stack - 1 >= 0) {
				booleans[6] = true;
				stack--;
			}
			if (Inventory.invBag[i].id == tile8 && stack - 1 >= 0) {
				booleans[7] = true;
				stack--;
			}
			if (Inventory.invBag[i].id == tile9 && stack - 1 >= 0) {
				booleans[8] = true;
				stack--;
			}
		}

		if (booleans[0] && booleans[1] && booleans[2] && booleans[3] && booleans[4] && booleans[5] && booleans[6] && booleans[7] && booleans[8]) return true;

		return false;
	}
}
