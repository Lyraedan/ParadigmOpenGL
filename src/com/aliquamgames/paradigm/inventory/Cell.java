package com.aliquamgames.paradigm.inventory;

import org.lwjgl.util.Point;
import org.lwjgl.util.Rectangle;
import org.newdawn.slick.Color;

import com.aliquamgames.paradigm.Core;
import com.aliquamgames.paradigm.Core.State;
import com.aliquamgames.paradigm.playing.Tile;
import com.aliquamgames.paradigm.util.Util;

public class Cell {
	public int[] id = { 0, 0 };
	public int[][] id2 = { id };
	public int[][][] id3 = { id2 };
	public int stack = 0;
	public String name;
	public Rectangle rect = new Rectangle();

	public Cell(Rectangle size, int[] id, int stack, String name) {
		rect.setBounds(size);
		this.id = id;
		this.stack = stack;
		this.name = name;
	}

	public void render(boolean isSelected) {
		if (Core.state == State.PLAYING) {
			if (Inventory.isOpen && rect.contains(new Point(Core.mouseX, Core.mouseY))) {
				Util.fillColoredRect(new Color(255, 255, 255, 130), rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
			}
		}

		if (stack < 1) {
			id = Tile.air;
			name = "Air";
		}

		if (id != Tile.air) {
			Util.drawImageNearestNeighbor(Tile.terrain, rect.getX() + Tile.invItemBorder, rect.getY() + Tile.invItemBorder, rect.getWidth() - Tile.invItemBorder * 2, rect.getHeight() - Tile.invItemBorder * 2, id[0] * Tile.tileSize, id[1] * Tile.tileSize, Tile.tileSize, Tile.tileSize, true);

		} else {
			if (stack > 0) {
				stack = 0;
			}
		}

		Util.drawImage(Tile.tile_cell, rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight(), true);

		if (isSelected && !Inventory.isOpen) {
			Util.drawImage(Tile.tile_select, rect.getX() - 1, rect.getY() - 1, rect.getWidth() + 2, rect.getHeight() + 2, true);

		}

		// TODO FIX THIS WHEN TEXT WORKS
		if (stack > 1) {
			// g.setColor(Color.RED);
			// g.setFont(new Font(ImageLoader.font, Font.BOLD, 35));
			// g.drawString("" + stack, rect.getX(), rect.getY() + Tile.invCellSize);
			Util.drawStringWithShadow("" + stack, rect.getX() + 10, rect.getY() + Tile.invCellSize - 30, 35, false);
		}
	}
}
