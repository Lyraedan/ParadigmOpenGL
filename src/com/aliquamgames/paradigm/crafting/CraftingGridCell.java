package com.aliquamgames.paradigm.crafting;

import org.lwjgl.util.Rectangle;
import org.newdawn.slick.Color;

import com.aliquamgames.paradigm.playing.Tile;
import com.aliquamgames.paradigm.util.Util;

public class CraftingGridCell {

	public int[] id = Tile.air;
	private Rectangle rect;
	public boolean hasItem = false;

	public CraftingGridCell(Rectangle rect) {
		this.rect = rect;
	}

	public void render() {
		Util.drawColoredRect(new Color(0, 0, 0, 255), rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
		if (id != Tile.air) {
			Util.drawImageNearestNeighbor(Tile.terrain, rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight(), id[0] * Tile.tileSize, id[1] * Tile.tileSize, Tile.tileSize, Tile.tileSize, true);
			if (!hasItem) Util.fillColoredRect(new Color(0, 0, 0, 175), rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight(), true);
		}
	}

}
