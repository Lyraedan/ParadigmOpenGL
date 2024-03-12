package com.aliquamgames.paradigm.blocks;

import org.lwjgl.util.Point;
import org.lwjgl.util.Rectangle;

import com.aliquamgames.paradigm.Core;
import com.aliquamgames.paradigm.playing.Tile;
import com.aliquamgames.paradigm.util.Util;

public class BlockDrop {

	public Rectangle rect = new Rectangle();

	// the ID for the block drop
	public int[] id = Tile.air;

	// 5 minutes of time before it disappears
	private static int blockDropTime = 60 * 1000 * 5;
	private static int fallingSpeed = 5;

	public int amount = 1;

	// the time that the block drop was created
	private long startTime;

	public String name;

	// controls if the block drop needs to be removed
	public boolean remove = false;

	public BlockDrop(Rectangle size, int[] id, String name, long startTime) {
		rect.setBounds(size);
		this.id = id;
		this.name = name;
		this.startTime = startTime;
	}

	public void tick(long currentTime) {
		if (currentTime > startTime + blockDropTime) {
			remove = true;
		}

		if (!isCollidingWithBlock(new Point((int) (rect.getX() + 2), (int) (rect.getY() + rect.getHeight() + fallingSpeed)), new Point((int) (rect.getX() + rect.getWidth() - 3), (int) (rect.getY() + rect.getHeight() + fallingSpeed)))) {
			rect.setY(rect.getY() + fallingSpeed);
		} else if (isCollidingWithBlock(new Point(rect.getX() + 2, rect.getY()), new Point(rect.getX() + rect.getWidth() - 2, rect.getY() + rect.getHeight() - 1))) {
			rect.setY(rect.getY() - fallingSpeed);
		}
	}

	public void render(int camX, int camY) {
		if (id != Tile.air) {
			if (rect.getX() > Core.player.getXPosition() - Core.getGameWidth() / 2 && rect.getX() < Core.player.getXPosition() + Core.getGameWidth() / 2 && rect.getY() > Core.player.getYPosition() - Core.getGameHeight() / 2 && rect.getY() < Core.player.getYPosition() + Core.getGameHeight() / 2) {
				Util.drawImage(Tile.terrain, rect.getX() - camX, rect.getY() - camY, rect.getX() + rect.getWidth() - camX, rect.getY() + rect.getHeight() - camY, id[0] * Tile.tileSize, id[1] * Tile.tileSize, id[0] * Tile.tileSize + Tile.tileSize, id[1] * Tile.tileSize + Tile.tileSize, true);
			}
		}
	}

	@SuppressWarnings("static-access")
	public boolean isCollidingWithBlock(Point pt1, Point pt2) {
		for (int x = (int) (rect.getX() / Tile.tileSize); x < (int) (rect.getX() / Tile.tileSize + 3); x++) {
			for (int y = (int) (rect.getY() / Tile.tileSize); y < (int) (rect.getY() / Tile.tileSize + 3); y++) {
				if (x >= 0 && y >= 0 && x < Core.level.generatedChunks * Core.level.CHUNKW && y < Core.level.CHUNKH) {
					if (Core.level.block[x][y].id != Tile.air && Core.level.block[x][y].id != Tile.roses && Core.level.block[x][y].id != Tile.roses2 && Core.level.block[x][y].id != Tile.lilies && Core.level.block[x][y].id != Tile.lilies2) {
						if (Core.level.block[x][y].rect.contains(pt1) || (Core.level.block[x][y].rect.contains(pt2))) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
}
