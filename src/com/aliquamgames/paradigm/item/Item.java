package com.aliquamgames.paradigm.item;

import org.lwjgl.util.Rectangle;

import com.aliquamgames.paradigm.Core;
import com.aliquamgames.paradigm.playing.Tile;
import com.aliquamgames.paradigm.util.Util;

/**
 * 
 * @author Luke Rapkin
 */
public class Item {

	public int[] id = { -1, -1 };
	public Rectangle rect;
	public String name = "Unnamed";

	public Item(Rectangle size, int[] id, String name) {
		this.rect = size;
		this.id = id;
		this.name = name;
	}

	public void syncWithClasses() {

	}

	public void render() {
		try {
			if (id != Tile.air) {
				Util.fillTexturedRect(Tile.items, rect.getX() - (int) Core.sX, rect.getY() - (int) Core.sY, Tile.tileSize, Tile.tileSize, id[0] * Tile.tileSize, id[1] * Tile.tileSize, Tile.tileSize, Tile.tileSize, true);
			}
		} catch (Exception e) {

		}
	}

}
