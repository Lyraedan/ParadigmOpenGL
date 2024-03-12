package com.aliquamgames.paradigm.inventory;

import org.lwjgl.util.Rectangle;

import com.aliquamgames.paradigm.Core;
import com.aliquamgames.paradigm.playing.Tile;
import com.aliquamgames.paradigm.util.Util;

public class Chest {

	public int chestLength = 5;
	public int chestHeight = 4;

	public Cell[] chest = new Cell[chestLength * chestHeight];

	public boolean isOpen = false;
	public boolean isHolding = false;

	public static int[] holdingID = Tile.air;
	public static int holdingIDStack = 1;

	public int spotX = 0;
	public int spotY = 0;

	public Chest(int spotX, int spotY) {
		this.spotX = spotX;
		this.spotY = spotY;

		int x = 0, y = 0;
		for (int i = 0; i < chest.length; i++) {
			chest[i] = new Cell(new Rectangle((Core.getGameWidth() >> 1) - ((chestLength * (Tile.invCellSize + Tile.invCellSpace)) >> 1) + (x * (Tile.invCellSize + Tile.invCellSpace)), Core.getGameHeight() - (chestHeight * (Tile.invCellSize + Tile.invCellSpace)) + (y * (Tile.invCellSize + Tile.invCellSpace)), Tile.invCellSize, Tile.invCellSize), Tile.air, 1, "Air");
			x++;
			if (x == chestLength) {
				x = 0;
				y++;
			}
		}

		for (int i = 0; i < chest.length; i++) {
			chest[i].id = Tile.air;
			chest[i].stack = 0;
		}

	}

	public void render() {
		if (isOpen) {
			for (int i = 0; i < chest.length; i++) {
				chest[i].render(false);
			}
			for (int i = 0; i < Inventory.invBag.length; i++) {
				Inventory.invBag[i].render(false);
			}

			if (isHolding) {
				try {
					Util.drawImage(Tile.terrain, (Core.mouseX) - (Tile.invCellSize / 2) + Tile.invItemBorder, (Core.mouseY) - (Tile.invCellSize / 2) + Tile.invItemBorder, (Core.mouseX) - (Tile.invCellSize / 2) + Tile.invCellSize - Tile.invItemBorder, (Core.mouseY) - (Tile.invCellSize / 2) + Tile.invCellSize - Tile.invItemBorder, holdingID[0] * Tile.tileSize, holdingID[1] * Tile.tileSize, holdingID[0] * Tile.tileSize + Tile.tileSize, holdingID[1] * Tile.tileSize + Tile.tileSize, true);
				} catch (Exception e) {

				}
				if (holdingIDStack > 1) {
					Util.drawStringWithShadow("" + holdingIDStack, (Core.mouseX) - 20, (Core.mouseY) + 5, 25, true);
				}

				// drawBlockName();
			}
		}
	}

	public void tick() {
		if (isOpen) {
			if (holdingIDStack < 1) {
				holdingID = Tile.air;
				holdingIDStack = 0;
				isHolding = false;
			}
		}
	}

	public void click(int e) {
		if (isOpen) {
			if (e == 1) {
				for (int i = 0; i < Inventory.invBag.length; i++) {
					if (!isHolding && Inventory.invBag[i].id != Tile.air) {
						if (Inventory.invBag[i].rect.contains(Core.mouseX, Core.mouseY)) {
							holdingID = Inventory.invBag[i].id;
							holdingIDStack = Inventory.invBag[i].stack;
							Inventory.invBag[i].id = Tile.air;
							Inventory.invBag[i].stack = 0;
							isHolding = true;
						}
					} else if (isHolding && Inventory.invBag[i].id == Tile.air) {
						if (Inventory.invBag[i].rect.contains(Core.mouseX, Core.mouseY)) {
							Inventory.invBag[i].id = holdingID;
							Inventory.invBag[i].stack = holdingIDStack;
							holdingID = Tile.air;
							holdingIDStack = 0;
							isHolding = false;
						}
					} else if (isHolding && Inventory.invBag[i].id != Tile.air) {
						if (Inventory.invBag[i].rect.contains(Core.mouseX, Core.mouseY)) {
							if (Inventory.invBag[i].id == holdingID) {
								if (Inventory.invBag[i].stack + holdingIDStack <= Inventory.maxStack) {
									Inventory.invBag[i].stack += holdingIDStack;
									holdingIDStack = 0;
									holdingID = Tile.air;
								} else {
									for (int s = holdingIDStack; s > 0; s--) {
										if (Inventory.invBag[i].stack + s == Inventory.maxStack) {
											Inventory.invBag[i].stack += s;
											holdingIDStack -= s;
											break;
										}
									}
								}
							} else if (Inventory.invBag[i].id != holdingID) {
								int[] con = Inventory.invBag[i].id;
								int scon = Inventory.invBag[i].stack;

								Inventory.invBag[i].id = holdingID;
								Inventory.invBag[i].stack = holdingIDStack;
								holdingID = con;
								holdingIDStack = scon;
							}
						}
					}
				}

				for (int i = 0; i < Inventory.invBar.length; i++) {
					if (!isHolding && Inventory.invBar[i].id != Tile.air) {
						if (Inventory.invBar[i].rect.contains(Core.mouseX, Core.mouseY)) {
							holdingID = Inventory.invBar[i].id;
							holdingIDStack = Inventory.invBar[i].stack;
							Inventory.invBar[i].id = Tile.air;
							Inventory.invBar[i].stack = 0;
							isHolding = true;
						}
					} else if (isHolding && Inventory.invBar[i].id == Tile.air) {
						if (Inventory.invBar[i].rect.contains(Core.mouseX, Core.mouseY)) {
							Inventory.invBar[i].id = holdingID;
							Inventory.invBar[i].stack = holdingIDStack;
							holdingID = Tile.air;
							holdingIDStack = 0;
							isHolding = false;
						}
					} else if (isHolding && Inventory.invBar[i].id != Tile.air) {
						if (Inventory.invBar[i].rect.contains(Core.mouseX, Core.mouseY)) {
							if (Inventory.invBar[i].id == holdingID) {
								if (Inventory.invBar[i].stack + holdingIDStack <= Inventory.maxStack) {
									Inventory.invBar[i].stack += holdingIDStack;
									holdingIDStack = 0;
									holdingID = Tile.air;
								} else {
									for (int s = holdingIDStack; s > 0; s--) {
										if (Inventory.invBar[i].stack + s == Inventory.maxStack) {
											Inventory.invBar[i].stack += s;
											holdingIDStack -= s;
											break;
										}
									}
								}
							} else if (Inventory.invBar[i].id != holdingID) {
								int[] con = Inventory.invBar[i].id;
								int scon = Inventory.invBar[i].stack;

								Inventory.invBar[i].id = holdingID;
								Inventory.invBar[i].stack = holdingIDStack;
								holdingID = con;
								holdingIDStack = scon;
							}
						}
					}
				}

				for (int i = 0; i < chest.length; i++) {
					if (!isHolding && chest[i].id != Tile.air) {
						if (chest[i].rect.contains(Core.mouseX, Core.mouseY)) {
							holdingID = chest[i].id;
							holdingIDStack = chest[i].stack;
							chest[i].id = Tile.air;
							chest[i].stack = 0;
							isHolding = true;
						}
					} else if (isHolding && chest[i].id == Tile.air) {
						if (chest[i].rect.contains(Core.mouseX, Core.mouseY)) {
							chest[i].id = holdingID;
							chest[i].stack = holdingIDStack;
							holdingID = Tile.air;
							holdingIDStack = 0;
							isHolding = false;
						}
					} else if (isHolding && chest[i].id != Tile.air) {
						if (chest[i].rect.contains(Core.mouseX, Core.mouseY)) {
							if (chest[i].id == holdingID) {
								if (chest[i].stack + holdingIDStack <= Inventory.maxStack) {
									chest[i].stack += holdingIDStack;
									holdingIDStack = 0;
									holdingID = Tile.air;
								} else {
									for (int s = holdingIDStack; s > 0; s--) {
										if (chest[i].stack + s == Inventory.maxStack) {
											chest[i].stack += s;
											holdingIDStack -= s;
											break;
										}
									}
								}
							} else if (chest[i].id != holdingID) {
								int[] con = chest[i].id;
								int scon = chest[i].stack;

								chest[i].id = holdingID;
								chest[i].stack = holdingIDStack;
								holdingID = con;
								holdingIDStack = scon;
							}
						}
					}
				}
			} else if (e == 0) {
				for (int i = 0; i < Inventory.invBag.length; i++) {
					if (!isHolding && Inventory.invBag[i].id != Tile.air) {
						if (Inventory.invBag[i].rect.contains(Core.mouseX, Core.mouseY)) {
							holdingID = Inventory.invBag[i].id;
							int newStack = (int) (Inventory.invBag[i].stack / 2);
							holdingIDStack = newStack;
							Inventory.invBag[i].stack -= newStack;
							isHolding = true;
						}
					} else if (isHolding && Inventory.invBag[i].id == holdingID) {
						if (Inventory.invBag[i].rect.contains(Core.mouseX, Core.mouseY)) {
							holdingIDStack--;
							Inventory.invBag[i].stack++;
						}
					} else if (isHolding && Inventory.invBag[i].id == Tile.air) {
						if (Inventory.invBag[i].rect.contains(Core.mouseX, Core.mouseY)) {
							Inventory.invBag[i].id = holdingID;
							holdingIDStack--;
							Inventory.invBag[i].stack++;
						}
					}
				}

				for (int i = 0; i < Inventory.invBar.length; i++) {
					if (!isHolding && Inventory.invBar[i].id != Tile.air) {
						if (Inventory.invBar[i].rect.contains(Core.mouseX, Core.mouseY)) {
							holdingID = Inventory.invBar[i].id;
							int newStack = (int) (Inventory.invBar[i].stack / 2);
							holdingIDStack = newStack;
							Inventory.invBar[i].stack -= newStack;
							isHolding = true;
						}
					} else if (isHolding && Inventory.invBar[i].id == holdingID) {
						if (Inventory.invBar[i].rect.contains(Core.mouseX, Core.mouseY)) {
							holdingIDStack--;
							Inventory.invBar[i].stack++;
						}
					} else if (isHolding && Inventory.invBar[i].id == Tile.air) {
						if (Inventory.invBar[i].rect.contains(Core.mouseX, Core.mouseY)) {
							Inventory.invBar[i].id = holdingID;
							holdingIDStack--;
							Inventory.invBar[i].stack++;
						}
					}
				}

				for (int i = 0; i < chest.length; i++) {
					if (!isHolding && chest[i].id != Tile.air) {
						if (chest[i].rect.contains(Core.mouseX, Core.mouseY)) {
							holdingID = chest[i].id;
							int newStack = (int) (chest[i].stack / 2);
							holdingIDStack = newStack;
							chest[i].stack -= newStack;
							isHolding = true;
						}
					} else if (isHolding && chest[i].id == holdingID) {
						if (chest[i].rect.contains(Core.mouseX, Core.mouseY)) {
							holdingIDStack--;
							chest[i].stack++;
						}
					} else if (isHolding && chest[i].id == Tile.air) {
						if (chest[i].rect.contains(Core.mouseX, Core.mouseY)) {
							chest[i].id = holdingID;
							holdingIDStack--;
							chest[i].stack++;
						}
					}
				}
			}
		}
	}

}
