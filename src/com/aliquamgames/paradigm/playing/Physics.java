package com.aliquamgames.paradigm.playing;

import java.util.Random;

import org.lwjgl.Sys;

import com.aliquamgames.paradigm.Core;

public class Physics {

	private long currentTime = getTime();

	private long lastWaterSpread = getTime();
	private long waterSpreadTime = 100;

	private long lastLavaSpread = getTime();
	private long lavaSpreadTime = 750;

	private long lastFireBurnOut = getTime();
	private long fireBurnOutTime = 7500;

	private long lastFireSpread = getTime();
	private long fireSpreadTime = 3500;

	private long lastGrassSpread = getTime();
	private long grassSpreadTime = 10000;

	public boolean checkWater = false;
	public boolean checkLava = false;
	public boolean checkFire = false;
	public boolean checkGrass = false;

	public Physics() {

	}

	public void checkBooleans(int[] id) {
		if (id == Tile.water) checkWater = true;
		// TODO NEEDS TO BE A LAVA TILE
		else if (id == Tile.lilies) checkLava = true;
		else if (id == Tile.fire) checkFire = true;
		else if (id == Tile.dirtWithGrass) checkGrass = true;
	}

	@SuppressWarnings("static-access")
	public void tick(int camX, int camY, int renW, int renH) {
		currentTime = getTime();
		try {
			for (int x = (camX / Tile.tileSize); x < (camX / Tile.tileSize) + renW; x++) {
				for (int y = (camY / Tile.tileSize); y < (camY / Tile.tileSize) + renH; y++) {
					if (x < 0 || y < 0 || x >= Core.level.worldW || y >= Core.level.worldH) continue;
					if (checkWater && currentTime > lastWaterSpread + waterSpreadTime) waterSpread(x, y);
					if (checkLava && currentTime > lastLavaSpread + lavaSpreadTime) lavaSpread(x, y);
					if (checkFire && currentTime > lastFireBurnOut + fireBurnOutTime) fireBurnOut(x, y);
					if (checkFire && currentTime > lastFireSpread + fireSpreadTime) fireSpread(x, y);
					if (checkGrass && currentTime > lastGrassSpread + grassSpreadTime) grassSpread(x, y);

					// if (emitsLight(x, y)) {
					// if (Core.level.block[x][y].id != Tile.air) {
					// Core.shader.glLight(Core.level.block[x][y].rect.getX(), Core.level.block[x][y].rect.getY(), Core.level.block[x][y].rect.getWidth(), Core.level.block[x][y].rect.getHeight(), Core.level.block[x][y].rect.getX(),
					// Core.level.block[x][y].rect.getY());
					// GL11.glDisable(GL11.GL_LIGHTING);
					// }
					// }
				}
			}

		} catch (Exception e) {
			// e.printStackTrace();
		}
	}

	@SuppressWarnings("static-access")
	public void waterSpread(int x, int y) {
		lastWaterSpread = getTime();
		if (Core.level.block[x][y].id == Tile.water && Core.level.block[x + 1][y].id == Tile.air) {
			Core.level.block[x + 1][y].trim(1); // need to think this though and finish trim
			Core.level.block[x + 1][y].id = Tile.water;
		}

	}

	public void lavaSpread(int x, int y) {
		lastLavaSpread = getTime();
	}

	public void fireBurnOut(int x, int y) {
		lastFireBurnOut = getTime();
	}

	public void fireSpread(int x, int y) {
		lastFireSpread = getTime();
	}

	public boolean emitsLight(int x, int y) {
		for (int fire = 0; fire < Tile.fireAnimationSwap.length; fire++) {
			if (Core.level.block[x][y].id == Tile.fireAnimationSwap[fire]) return true;
			if (Core.level.block[x][y].id == Tile.dirtWithGrass) return true; // testing
		}
		return false;
	}

	@SuppressWarnings("static-access")
	public void grassSpread(int x, int y) throws ArrayIndexOutOfBoundsException {
		if (Core.level.block[x][y].id == Tile.dirtWithGrass || Core.level.block[x][y].id == Tile.dirtLightWithSnow) {
			int rand = new Random().nextInt(2);
			if (rand == 1) return;
			if (Core.level.block[x + 1][y].id == Tile.dirt && Core.level.block[x + 1][y - 1].id == Tile.air) {
				Core.level.block[x + 1][y].id = Tile.dirtWithGrass;
				lastGrassSpread = getTime();
			} else if (Core.level.block[x - 1][y].id == Tile.dirt && Core.level.block[x - 1][y - 1].id == Tile.air) {
				Core.level.block[x - 1][y].id = Tile.dirtWithGrass;
				lastGrassSpread = getTime();
			} else if (Core.level.block[x + 1][y + 1].id == Tile.dirt && Core.level.block[x + 1][y].id == Tile.air) {
				Core.level.block[x + 1][y + 1].id = Tile.dirtWithGrass;
				lastGrassSpread = getTime();
			} else if (Core.level.block[x + 1][y - 1].id == Tile.dirt && Core.level.block[x + 1][y - 2].id == Tile.air) {
				Core.level.block[x + 1][y - 1].id = Tile.dirtWithGrass;
				lastGrassSpread = getTime();
			} else if (Core.level.block[x - 1][y + 1].id == Tile.dirt && Core.level.block[x - 1][y].id == Tile.air) {
				Core.level.block[x - 1][y + 1].id = Tile.dirtWithGrass;
				lastGrassSpread = getTime();
			} else if (Core.level.block[x - 1][y - 1].id == Tile.dirt && Core.level.block[x - 1][y - 2].id == Tile.air) {
				Core.level.block[x - 1][y - 1].id = Tile.dirtWithGrass;
				lastGrassSpread = getTime();
			}
		}
	}

	public void addGrassOverlay(int x, int y) {

	}

	public long getTime() {
		return Sys.getTime() * 1000 / Sys.getTimerResolution();
	}

}
