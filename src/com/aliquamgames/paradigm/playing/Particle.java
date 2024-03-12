package com.aliquamgames.paradigm.playing;

import org.lwjgl.util.Rectangle;

import com.aliquamgames.paradigm.Core;
import com.aliquamgames.paradigm.util.Util;

public class Particle {
	
	public Rectangle rect;
	
	public int[] id = { -1, -1 };
	private final int particleWidth = 2;
	private final int particleHeight = particleWidth;
	
	public int removeTime = 0;
	public int removedTime = 3 * 1000;
	
	public boolean remove = false;

	public Particle(Rectangle size, int[] id) {
		this.rect = size;
		this.id = id;
	}
	
	public void tick(long currentTime) {
		int removeTimeDelay = 1 * 1000;
		removeTime += 1 / removeTimeDelay; // + 1 every second?
	}
	
	public void render() {
		Util.fillTexturedRect(Tile.terrain, rect.getX() - (int) Core.sX, rect.getY() - (int) Core.sY, Tile.tileSize, Tile.tileSize, id[0] * Tile.tileSize, id[1] * Tile.tileSize, particleWidth, particleHeight, true);
	}

}
