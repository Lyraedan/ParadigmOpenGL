package com.aliquamgames.paradigm.crafting;

import org.lwjgl.util.Rectangle;
import org.newdawn.slick.opengl.Texture;

import com.aliquamgames.paradigm.playing.Tile;
import com.aliquamgames.paradigm.util.Util;

public class CategoryCell {

	public Texture texture;
	public Rectangle rect;
	public boolean selected = false;

	public CategoryCell(Rectangle rect) {
		this.rect = rect;
	}

	public void render() {
		Util.drawImage(texture, rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight(), true);

		Util.drawImage(Tile.tile_cell, rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight(), true);

		if (selected) {
			Util.drawImage(Tile.tile_select, rect.getX() - 1, rect.getY() - 1, rect.getWidth() + 2, rect.getHeight() + 2, true);
		}
	}

}
