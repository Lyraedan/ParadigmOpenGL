package com.aliquamgames.paradigm.crafting;

import org.lwjgl.util.Rectangle;
import org.newdawn.slick.Color;

import com.aliquamgames.paradigm.playing.Tile;
import com.aliquamgames.paradigm.util.Util;

public class CraftingCell {

	public Rectangle rect;
	public int[] id;
	public boolean selected = false;
	public boolean canBeMade = true;
	public int stack = 1;

	public CraftingCell(Rectangle rect) {
		this.rect = rect;
	}

	public void render() {
		if (id != Tile.air) {
			Util.drawImageNearestNeighbor(Tile.terrain, rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight(), id[0] * Tile.tileSize, id[1] * Tile.tileSize, Tile.tileSize, Tile.tileSize, true);
			if (!canBeMade) Util.fillColoredRect(new Color(0, 0, 0, 150), rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight(), true);

		}

		Util.drawImage(Tile.tile_cell, rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight(), true);

		if (selected) {
			Util.drawImage(Tile.tile_select, rect.getX() - 1, rect.getY() - 1, rect.getWidth() + 2, rect.getHeight() + 2, true);
		}

		if (id != Tile.air) Util.drawStringWithShadow("" + stack, rect.getX(), rect.getY() + rect.getHeight() - 15, 8, true);
	}
}
