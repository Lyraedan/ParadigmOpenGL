package com.aliquamgames.paradigm.inventory;

import org.lwjgl.util.Point;
import org.lwjgl.util.Rectangle;
import org.newdawn.slick.Color;

import com.aliquamgames.paradigm.Core;
import com.aliquamgames.paradigm.playing.Tile;
import com.aliquamgames.paradigm.render.FontRenderer;
import com.aliquamgames.paradigm.util.Util;

public class Inventory {
	public static Cell[] invBar = new Cell[Tile.invLength];
	public static Cell[] invBag = new Cell[Tile.invLength * Tile.invHeight];

	public static boolean isOpen = false;
	public static boolean isHolding = false;
	public static boolean throwBlock = false;

	public static int maxStack = 64;
	private int fontSize = 8;

	public static int selected = 0;
	public static int[] holdingID = Tile.air;
	public static int holdingIDStack = 1;
	public static String holdingIDName = "Unnamed";

	private static String blockName = "Unnamed";

	public Inventory() {
		for (int i = 0; i < invBar.length; i++) {
			invBar[i] = new Cell(new Rectangle((Core.getGameWidth() >> 1) - ((Tile.invLength * (Tile.invCellSize + Tile.invCellSpace)) >> 1) + (i * (Tile.invCellSize + Tile.invCellSpace)), Tile.invBorderSpace, Tile.invCellSize, Tile.invCellSize), Tile.air, 1, "Air");
		}

		int x = 0, y = 0;
		for (int i = 0; i < invBag.length; i++) {
			invBag[i] = new Cell(new Rectangle((Core.getGameWidth() >> 1) - ((Tile.invLength * (Tile.invCellSize + Tile.invCellSpace)) >> 1) + (x * (Tile.invCellSize + Tile.invCellSpace)), (Tile.invCellSize * 2 + Tile.invBorderSpace) + (y * (Tile.invCellSize + Tile.invCellSpace) - Tile.invCellSize + Tile.invCellSpace), Tile.invCellSize, Tile.invCellSize), Tile.air, 1, "Air");

			x++;
			if (x == Tile.invLength) {
				x = 0;
				y++;
			}
		}

		// Loads the inventory bar and sets the default blocks in the bar
		invBar[0].id = Tile.sand;
		invBar[1].id = Tile.dirtWithGrass;
		invBar[2].id = Tile.egg;
		invBar[3].id = Tile.stone;
		invBar[4].id = Tile.obsidian;
		invBar[5].id = Tile.water;
		invBar[6].id = Tile.chest;
		invBar[7].id = Tile.craftingTable;

		// sets the default stack number
		invBar[0].stack = 63;
		invBar[1].stack = 1;
		invBar[2].stack = 2;
		invBar[3].stack = 3;
		invBar[4].stack = 10;
		invBar[5].stack = 5;
		invBar[6].stack = 6;
		invBar[7].stack = 7;

		// Loads the inventory bag with the blocks
		invBag[0].id = Tile.wall;
		invBag[1].id = Tile.vines;
		invBag[2].id = Tile.door;
		invBag[3].id = Tile.glass;
		invBag[4].id = Tile.pipe;
		invBag[5].id = Tile.chest;

		invBag[0].stack = 1;
		invBag[1].stack = 1;
		invBag[2].stack = 1;
		invBag[3].stack = 1;
		invBag[4].stack = 10;
		invBag[5].stack = 10;

		// sets the default stack size in the invBag
		for (int i = 0; i < invBag.length; i++) {
			if (invBag[i].id == Tile.air) {
				invBag[i].stack = 0;
			}
		}
	}

	public void click(int e) {
		if (e == 1) {
			if (isOpen) {
				for (int i = 0; i < invBar.length; i++) {
					if (invBar[i].rect.contains(new Point(Core.mouseX, Core.mouseY))) {
						blockName = invBar[i].name;
						if (invBar[i].id != Tile.air && !isHolding) {
							holdingIDStack = invBar[i].stack;
							holdingID = invBar[i].id;
							holdingIDName = invBar[i].name;
							invBar[i].id = Tile.air;
							invBar[i].stack = 0;
							invBar[i].name = "Air";

							isHolding = true;
						} else if (isHolding && invBar[i].id == Tile.air) {
							invBar[i].stack = holdingIDStack;
							invBar[i].id = holdingID;
							invBar[i].name = holdingIDName;

							isHolding = false;
						} else if (isHolding && invBar[i].id != Tile.air) {
							int scon = invBar[i].stack;
							int[] con = invBar[i].id;
							String ncon = invBar[i].name;

							if (holdingID == con && holdingIDStack + scon <= maxStack) {
								invBar[i].stack = holdingIDStack + scon;
								invBar[i].id = holdingID;
								invBar[i].name = holdingIDName;
								holdingID = Tile.air;
								holdingIDName = "Air";
								holdingIDStack = 0;
							} else if (holdingID == con && holdingIDStack + scon >= maxStack && scon != 64) {
								for (int u = scon; u > 0; u--) {
									if (u + holdingIDStack == maxStack) {
										invBar[i].stack = scon + (holdingIDStack - u + 1);
										holdingIDStack = holdingIDStack - (holdingIDStack - u + 1);
										break;
									}
								}
							} else {

								invBar[i].stack = holdingIDStack;
								invBar[i].id = holdingID;
								invBar[i].name = holdingIDName;

								holdingIDStack = scon;
								holdingID = con;
								holdingIDName = ncon;
							}
						}
					}
				}

				for (int i = 0; i < invBag.length; i++) {
					if (invBag[i].rect.contains(new Point(Core.mouseX, Core.mouseY))) {
						blockName = invBag[i].name;
						if (invBag[i].id != Tile.air && !isHolding) {
							holdingIDStack = invBag[i].stack;
							holdingID = invBag[i].id;
							holdingIDName = invBag[i].name;
							invBag[i].id = Tile.air;
							invBag[i].stack = 0;
							invBag[i].name = "Air";

							isHolding = true;
						} else if (isHolding && invBag[i].id == Tile.air) {
							invBag[i].stack = holdingIDStack;
							invBag[i].id = holdingID;
							invBag[i].name = holdingIDName;

							isHolding = false;
						} else if (isHolding && invBag[i].id != Tile.air) {
							int scon = invBag[i].stack;
							int[] con = invBag[i].id;
							String ncon = invBag[i].name;

							if (holdingID == con && holdingIDStack + scon <= maxStack) {
								invBag[i].stack = holdingIDStack + scon;
								invBag[i].id = holdingID;
								invBag[i].name = holdingIDName;
								holdingID = Tile.air;
								holdingIDName = "Air";
								holdingIDStack = 0;
							} else if (holdingID == con && holdingIDStack + scon >= maxStack && scon != 64) {
								for (int u = scon; u > 0; u--) {
									if (u + holdingIDStack == maxStack) {
										invBag[i].stack = scon + (holdingIDStack - u + 1);
										holdingIDStack = holdingIDStack - (holdingIDStack - u + 1);
										break;
									}
								}
							} else {

								invBag[i].stack = holdingIDStack;
								invBag[i].id = holdingID;
								invBag[i].name = holdingIDName;

								holdingIDStack = scon;
								holdingID = con;
								holdingIDName = ncon;
							}
						}
					}
				}
			}
		} else if (e == 0) {
			if (isOpen) {
				for (int i = 0; i < invBar.length; i++) {
					if (invBar[i].rect.contains(new Point(Core.mouseX, Core.mouseY))) {
						if (isHolding && holdingID != Tile.air) {
							if (invBar[i].id == Tile.air) {
								invBar[i].id = holdingID;
								invBar[i].stack++;
								invBar[i].name = holdingIDName;
								holdingIDStack--;
							} else if (invBar[i].id == holdingID && holdingIDStack + invBar[i].stack <= maxStack) {
								invBar[i].stack++;
								invBar[i].name = holdingIDName;
								holdingIDStack--;
							}
						}
					}
				}

				for (int i = 0; i < invBag.length; i++) {
					if (invBag[i].rect.contains(new Point(Core.mouseX, Core.mouseY))) {
						if (isHolding && holdingID != Tile.air) {
							if (invBag[i].id == Tile.air) {
								invBag[i].id = holdingID;
								invBag[i].name = holdingIDName;
								invBag[i].stack++;
								holdingIDStack--;
							} else if (invBag[i].id == holdingID && holdingIDStack + invBag[i].stack <= maxStack) {
								invBag[i].stack++;
								invBag[i].name = holdingIDName;
								holdingIDStack--;
							}
						}
					}
				}
			}
		}
	}

	@SuppressWarnings("static-access")
	public void tick() {
		for (int bar = 0; bar < invBar.length; bar++) {
			for (int bag = 0; bag < invBag.length; bag++) {
				// for(int x = 0; x < invBar[selected].) //fuck...
				invBar[bar].name = Core.level.block[selected][selected].getName();
				invBag[bag].name = Core.block.getName();
			}

		}
		if (throwBlock) {
			if (invBar[selected].id != Tile.air) {
				Core.level.throwBlock(invBar[selected].id, invBar[selected].name);
				invBar[selected].stack--;
			}
			throwBlock = false;
		}

		if (holdingIDStack < 1) {
			holdingID = Tile.air;
			holdingIDStack = 0;
			isHolding = false;
		}
		if (invBar[selected].stack < 0) {
			invBar[selected].stack = 0;
		}
	}

	public void fixAnimations() {
		for (int fire = 0; fire < Tile.fireAnimationSwap.length; fire++) {
			for (int bar = 0; bar < invBar.length; bar++) {
				if (invBar[bar].id == Tile.fireAnimationSwap[fire]) {
					invBar[bar].id = Core.animation.fire();
				}
			}
			for (int bag = 0; bag < invBag.length; bag++) {
				if (invBag[bag].id == Tile.fireAnimationSwap[fire]) {
					invBag[bag].id = Core.animation.fire();
				}
			}
		}
	}

	// TODO FIX THIS METHOD WHEN TEXT WORKS
	public void drawBlockName() {
		// draw the box the name will be in
		if (blockName != null) {
			int width = FontRenderer.font2.getWidth(blockName);
			int height = FontRenderer.font2.getHeight(blockName);
			Util.fillColoredRect(new Color(0, 0, 0, 180), Core.getGameWidth() / 2 - width / 2 - 5, (Tile.invHeight + 1) * (Tile.invCellSize + Tile.invCellSpace) + 10, width + 10, height + 2, true);
			Util.drawString(blockName, Core.getGameWidth() / 2 - width / 2 + 2, (Tile.invHeight + 1) * (Tile.invCellSize + Tile.invCellSpace) + 10, fontSize, true, Color.red);
		}
	}

	public void render() {

		// TODO TRY TO FIGURE OUT HOW TO DO NEAREST NEIGHBOR INTERPOLATION
		for (int i = 0; i < invBar.length; i++) {
			boolean isSelected = false;
			if (i == selected) {
				isSelected = true;
			}
			invBar[i].render(isSelected);
		}

		if (isOpen) {
			for (int i = 0; i < invBag.length; i++) {
				invBag[i].render(false);

			}
		}

		// TODO FIX THIS WHEN TEXT WORKS
		if (isHolding) {
			try {
				Util.drawImage(Tile.terrain, (Core.mouseX) - (Tile.invCellSize / 2) + Tile.invItemBorder, (Core.mouseY) - (Tile.invCellSize / 2) + Tile.invItemBorder, (Core.mouseX) - (Tile.invCellSize / 2) + Tile.invCellSize - Tile.invItemBorder, (Core.mouseY) - (Tile.invCellSize / 2) + Tile.invCellSize - Tile.invItemBorder, holdingID[0] * Tile.tileSize, holdingID[1] * Tile.tileSize, holdingID[0] * Tile.tileSize + Tile.tileSize, holdingID[1] * Tile.tileSize + Tile.tileSize, true);
			} catch (Exception e) {

			}
			if (holdingIDStack > 1) {
				Util.drawStringWithShadow("" + holdingIDStack, (Core.mouseX) - 20, (Core.mouseY) + 5, 25, true);
			}

			drawBlockName();
		}
	}
}
