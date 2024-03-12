package com.aliquamgames.paradigm.crafting;

import org.lwjgl.util.Rectangle;

import com.aliquamgames.paradigm.playing.Tile;
import com.aliquamgames.paradigm.util.Util;

public class InventoryCell {

	public Rectangle rect;
	public int[] id = Tile.air;
	public int stack = 0;

	public InventoryCell(Rectangle rect) {
		this.rect = rect;
	}

	public void render() {
		if (id != Tile.air) {
			Util.drawImageNearestNeighbor(Tile.terrain, rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight(), id[0] * Tile.tileSize, id[1] * Tile.tileSize, Tile.tileSize, Tile.tileSize, true);
		}
		Util.drawImage(Tile.tile_cell, rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight(), true);

		if (stack > 1) {
			Util.drawStringWithShadow("" + stack, rect.getX(), rect.getY() + rect.getHeight() - 15, 8, true);
		}
	}

}
