package com.aliquamgames.paradigm.crafting;

import org.lwjgl.util.Rectangle;
import org.newdawn.slick.Color;

import com.aliquamgames.paradigm.Core;
import com.aliquamgames.paradigm.ImageLoader;
import com.aliquamgames.paradigm.inventory.Inventory;
import com.aliquamgames.paradigm.playing.Tile;
import com.aliquamgames.paradigm.util.Util;

public class Crafting {

	private int arrowSection = 0;

	// predefine the recipes, the last one is the product
	private int[][] dirt = { Tile.dirt, Tile.dirt, Tile.dirt, Tile.dirt, Tile.dirt, Tile.dirt, Tile.dirt, Tile.dirt, Tile.dirt, Tile.dirt };
	private int[][] stone = { Tile.stone, Tile.stone, Tile.stone, Tile.stone, Tile.stone, Tile.stone, Tile.stone, Tile.stone, Tile.stone, Tile.stone };
	private int[][] air = { Tile.air, Tile.air, Tile.air, Tile.air, Tile.air, Tile.air, Tile.air, Tile.air, Tile.air, Tile.air };

	// the arrays that contain the recipes, the amounts, and the strings for the product
	private int[][][] tools = { dirt, stone };
	private int[] toolAmounts = { 2, 1 };
	private String[] toolStrings = { "Dirt", "Stone" };

	private int[][][] food = {};
	private int[] foodAmounts = { 1, 1 };
	private String[] foodStrings = { "", "" };

	private int[][][] structures = {};
	private int[] structureAmounts = { 1, 1 };
	private String[] structureStrings = { "", "" };

	private int[][][] scenery = {};
	private int[] sceneryAmounts = { 1, 1 };
	private String[] sceneryStrings = { "", "" };

	private int[][][] decoration = {};
	private int[] decorationAmounts = { 1, 1 };
	private String[] decorationStrings = { "", "" };

	private int[][][] misc = {};
	private int[] miscAmounts = { 1, 1 };
	private String[] miscStrings = { "", "" };

	// this contains the tile that will be used
	private int[][] tiles = dirt;
	// this contains the category's tiles
	private int[][][] categoryTiles = tools;

	private enum Categories {
		TOOLS, FOOD, STRUCTURE, SCENERY, DECORATION, MISC
	}

	private Categories category = Categories.TOOLS;

	public static boolean isOpen = false;
	private int width = 500;
	private int height = 350;
	public static boolean checkedRecipes = false;

	private int iconWidth = Tile.tileSize;
	private int iconHeight = Tile.tileSize;
	private int spaceBetweenIcons = 13;

	private int gridIconWidth = 35;
	private int gridIconHeight = 35;

	private int inventoryIconWidth = 30;
	private int inventoryIconHeight = 30;
	private int spaceBetweenInventoryIcons = 8;

	private int x = 250;
	private int y = 300;
	private int yOffset = 50;
	private int numberOfCategories = 6;

	// the category cells
	private CategoryCell[] categoryCells = new CategoryCell[6];
	// the cells that actually contain the product
	private CraftingCell[] craftingCells = new CraftingCell[8];
	// for the 3x3 grid
	private CraftingGridCell[] craftingGridCells = new CraftingGridCell[9];
	// the inventory cells
	private InventoryCell[] inventoryCells = new InventoryCell[Tile.invHeight * Tile.invLength + Tile.invLength];

	public Crafting() {
		x = Core.getGameWidth() / 2 - width / 2 + spaceBetweenIcons;
		y = Core.getGameHeight() / 2 - height / 2 + spaceBetweenIcons - yOffset + iconHeight;

		// create the category cells
		int length = numberOfCategories * (iconWidth + spaceBetweenIcons);
		int tempX = Core.getGameWidth() / 2;
		for (int i = 0; i < numberOfCategories; i++) {
			categoryCells[i] = new CategoryCell(new Rectangle(tempX - length / 2 + i * (iconWidth + spaceBetweenIcons), y - iconHeight, iconWidth, iconHeight));
		}
		try {
			categoryCells[0].texture = ImageLoader.loadSlick("PNG", "/crafting/tools.png");
			categoryCells[1].texture = ImageLoader.loadSlick("PNG", "/crafting/food.png");
			categoryCells[2].texture = ImageLoader.loadSlick("PNG", "/crafting/structures.png");
			categoryCells[3].texture = ImageLoader.loadSlick("PNG", "/crafting/scenery.png");
			categoryCells[4].texture = ImageLoader.loadSlick("PNG", "/crafting/decoration.png");
			categoryCells[5].texture = ImageLoader.loadSlick("PNG", "/crafting/misc.png");
		} catch (Exception e) {
			e.printStackTrace();
		}

		// create the crafting cells
		for (int i = 0; i < (int) (width / (iconWidth + spaceBetweenIcons)); i++) {
			craftingCells[i] = new CraftingCell(new Rectangle(x + (i * (iconWidth + spaceBetweenIcons)), y + spaceBetweenIcons, iconWidth, iconHeight));
		}

		// create the grid cells
		int tempY = 0;
		int addY = 0;
		int addX = 0;
		int startY = Core.getGameHeight() / 2 + height / 2 - yOffset - (3 * (iconHeight + spaceBetweenIcons) - spaceBetweenIcons + 5);
		for (int i = 0; i < 9; i++) {
			addX = ((i % 3) * (gridIconWidth + spaceBetweenIcons));
			if (i != 0) if (i % 3 == 0) tempY++;

			addY = tempY * (gridIconHeight + spaceBetweenIcons);
			craftingGridCells[i] = new CraftingGridCell(new Rectangle(x + addX, startY + addY, gridIconWidth, gridIconHeight));
		}

		// create inventory cells
		tempY = 0;
		startY = Core.getGameHeight() / 2 - yOffset - spaceBetweenIcons * 2;
		int startX = x + (3 * (gridIconWidth + spaceBetweenIcons) + spaceBetweenIcons);
		addY = 0;
		addX = 0;
		for (int i = 0; i < inventoryCells.length; i++) {
			addX = ((i % Tile.invLength) * (inventoryIconWidth + spaceBetweenInventoryIcons));
			if (i != 0) if (i % Tile.invLength == 0) tempY++;

			addY = tempY * (inventoryIconHeight + spaceBetweenInventoryIcons);
			inventoryCells[i] = new InventoryCell(new Rectangle(startX + addX, startY + addY, inventoryIconWidth, inventoryIconHeight));

		}

		for (int i = 0; i < Tile.invLength; i++) {
			inventoryCells[i].id = Inventory.invBar[i].id;
			inventoryCells[i].stack = Inventory.invBar[i].stack;
		}

		for (int i = Tile.invLength; i < Tile.invHeight * Tile.invLength; i++) {
			inventoryCells[i].id = Inventory.invBag[(i - Tile.invLength)].id;
			inventoryCells[i].stack = Inventory.invBag[(i - Tile.invLength)].stack;
		}

		checkRecipes();

	}

	public void checkRecipes() {
		switch (category) {
		case TOOLS:
			categoryTiles = tools;
			craftingCells[0].canBeMade = recipe(dirt, Tile.dirt, 4);
			craftingCells[0].stack = toolAmounts[0];
			craftingCells[1].canBeMade = recipe(stone, Tile.stone, 4);
			craftingCells[1].stack = toolAmounts[1];
			break;
		case FOOD:
			categoryTiles = food;
			break;
		case STRUCTURE:
			categoryTiles = structures;
			break;
		case SCENERY:
			categoryTiles = scenery;
			break;
		case DECORATION:
			categoryTiles = decoration;
			break;
		case MISC:
			categoryTiles = misc;
			break;
		}
		for (int i = 0; i < craftingCells.length; i++) {
			if (i >= categoryTiles.length) craftingCells[i].id = Tile.air;
			else craftingCells[i].id = categoryTiles[i][0];
		}

	}

	public void tick() {
		if (isOpen) {
			checkForInventoryChange();
		}
	}

	private void checkForInventoryChange() {

		for (int i = 0; i < Tile.invLength; i++) {
			inventoryCells[i].id = Inventory.invBar[i].id;
			inventoryCells[i].stack = Inventory.invBar[i].stack;
		}

		for (int i = Tile.invLength; i < Tile.invHeight * Tile.invLength + Tile.invHeight; i++) {
			inventoryCells[i].id = Inventory.invBag[(i - Tile.invLength)].id;
			inventoryCells[i].stack = Inventory.invBag[(i - Tile.invLength)].stack;
		}
	}

	public void render() {
		// done in render cuz this has to be done in order to know what to render
		if (!checkedRecipes) {
			checkRecipes();
			for (int i = 0; i < craftingCells.length; i++) {
				craftingCells[i].selected = false;
			}
			category = Categories.TOOLS;
			for (int i = 0; i < categoryCells.length; i++) {
				categoryCells[i].selected = false;
			}
			categoryCells[0].selected = true;
			changeGrid(null);
			checkedRecipes = true;
		}

		if (isOpen) {
			Util.fillColoredRect(new Color(0, 0, 0, 150), Core.getGameWidth() / 2 - width / 2, Core.getGameHeight() / 2 - height / 2 - yOffset, width, height, true);

			// renders the categories
			for (int i = 0; i < categoryCells.length; i++) {
				categoryCells[i].render();
			}

			// renders the crafting cells
			for (int i = 0; i < craftingCells.length; i++) {
				craftingCells[i].render();
			}

			// renders the grid
			renderGrid();
			renderInventory();
		}
	}

	public void clicked(int x, int y) {
		if (isOpen) {
			for (int i = 0; i < categoryCells.length; i++) {
				if (categoryCells[i].rect.contains(x, y)) {
					for (int i2 = 0; i2 < categoryCells.length; i2++) {
						categoryCells[i2].selected = false;
					}
					categoryCells[i].selected = true;
					if (i == 0) category = Categories.TOOLS;
					else if (i == 1) category = Categories.FOOD;
					else if (i == 2) category = Categories.STRUCTURE;
					else if (i == 3) category = Categories.SCENERY;
					else if (i == 4) category = Categories.DECORATION;
					else if (i == 5) category = Categories.MISC;

					checkRecipes();
					changeGrid(Tile.air);
				}
			}

			for (int i = 0; i < craftingCells.length; i++) {
				if (craftingCells[i].rect.contains(x, y)) {
					if (craftingCells[i].selected && craftingCells[i].canBeMade) {
						removeItemsFromInventory();
						addItemToInventory(i);
						checkRecipes();
						changeGrid(craftingCells[i].id);
						return;
					}
					for (int i2 = 0; i2 < craftingCells.length; i2++) {
						craftingCells[i2].selected = false;
					}
					craftingCells[i].selected = true;
					changeGrid(craftingCells[i].id);
				}
			}
		}
	}

	private void removeItemsFromInventory() {
		for (int t = 0; t < tiles.length - 1; t++) {
			if (tiles[t] != Tile.air) {
				for (int i = 0; i < Inventory.invBar.length; i++) {
					if (tiles[t] == Inventory.invBar[i].id) {
						Inventory.invBar[i].stack--;
						break;
					}
				}
			}
		}

		for (int t = 0; t < tiles.length - 1; t++) {
			if (tiles[t] != Tile.air) {
				for (int i = 0; i < Inventory.invBag.length; i++) {
					if (tiles[t] == Inventory.invBag[i].id) {
						Inventory.invBag[i].stack--;
						break;
					}
				}
			}
		}
	}

	@SuppressWarnings("static-access")
	private void addItemToInventory(int index) {
		int amount = 1;
		String name = "Air";
		switch (category) {
		case TOOLS:
			// TODO THIS NEEDS TO B CHANGED WHEN I ALLOW ARROWS
			amount = toolAmounts[index + (arrowSection * 8)];
			name = toolStrings[index + (arrowSection * 8)];
			break;
		case FOOD:
			break;
		case STRUCTURE:
			break;
		case SCENERY:
			break;
		case DECORATION:
			break;
		case MISC:
			break;
		}
		Core.level.addItem(tiles[tiles.length - 1], amount, name);
	}

	public void hasItem(int[][] tiles) {
		for (int i = 0; i < Inventory.invBar.length; i++) {
			int stack = Inventory.invBar[i].stack;
			for (int t = 0; t < tiles.length - 1; t++) {
				if (Inventory.invBar[i].id == tiles[t] && tiles[t] != Tile.air) {
					if (stack - 1 >= 0) {
						stack--;
						craftingGridCells[t].hasItem = true;
					}
				}
			}
		}

		for (int i = 0; i < Inventory.invBag.length; i++) {
			int stack = Inventory.invBag[i].stack;
			for (int t = 0; t < tiles.length - 1; t++) {
				if (Inventory.invBag[i].id == tiles[t] && tiles[t] != Tile.air) {
					if (stack - 1 >= 0) {
						stack--;
						craftingGridCells[t].hasItem = true;
					}
				}
			}
		}
	}

	public void renderGrid() {
		for (int i = 0; i < craftingGridCells.length; i++) {
			craftingGridCells[i].render();
		}
	}

	public void renderInventory() {
		for (int i = 0; i < inventoryCells.length; i++) {
			inventoryCells[i].render();
		}
	}

	public void changeGrid(int[] id) {
		if (id == Tile.dirt) tiles = dirt;
		else if (id == Tile.stone) tiles = stone;
		else tiles = air;

		for (int i = 0; i < craftingGridCells.length; i++) {
			craftingGridCells[i].id = tiles[i];
			craftingGridCells[i].hasItem = false;
		}

		hasItem(tiles);

	}

	public boolean recipe(int[] tile1, int[] tile2, int[] tile3, int[] tile4, int[] tile5, int[] tile6, int[] tile7, int[] tile8, int[] tile9, int[] product, int amount) {
		int[][] tiles = new int[9][0];
		tiles[0] = tile1;
		tiles[1] = tile2;
		tiles[2] = tile3;
		tiles[3] = tile4;
		tiles[4] = tile5;
		tiles[5] = tile6;
		tiles[6] = tile7;
		tiles[7] = tile8;
		tiles[8] = tile9;
		return recipe(tiles, product, amount);
	}

	public boolean recipe(int[][] tiles, int[] product, int amount) {
		boolean[] booleans = new boolean[9];
		for (int i = 0; i < booleans.length; i++) {
			booleans[i] = false;
		}

		for (int i = 0; i < tiles.length - 1; i++) {
			if (tiles[i] == null || tiles[i] == Tile.air) booleans[i] = true;
		}

		for (int i = 0; i < Inventory.invBar.length; i++) {
			int stack = Inventory.invBar[i].stack;
			for (int t = 0; t < tiles.length - 1; t++) {
				if (Inventory.invBar[i].id == tiles[t] && stack - 1 >= 0) {
					booleans[t] = true;
					stack--;
				}
			}
		}

		if (booleans[0] && booleans[1] && booleans[2] && booleans[3] && booleans[4] && booleans[5] && booleans[6] && booleans[7] && booleans[8]) return true;

		for (int i = 0; i < Inventory.invBag.length; i++) {
			int stack = Inventory.invBag[i].stack;
			for (int t = 0; t < tiles.length - 1; t++) {
				if (Inventory.invBag[i].id == tiles[t] && stack - 1 >= 0) {
					booleans[t] = true;
					stack--;
				}
			}
		}

		if (booleans[0] && booleans[1] && booleans[2] && booleans[3] && booleans[4] && booleans[5] && booleans[6] && booleans[7] && booleans[8]) return true;

		return false;
	}
}
