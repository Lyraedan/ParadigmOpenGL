package com.aliquamgames.paradigm.level;

import java.util.Random;

import org.lwjgl.opengl.GL11;

public class Chunk {

	public Biome biome;
	private int startX;
	private int endX;

	public Chunk(Biome biome, int startX, int endX, int height) {
		this.biome = biome;
		this.startX = startX;
		this.endX = endX;
	}

	public void generate(int startX, int endX, int height) {
		biome.generate(startX, endX, height);
		if (new Random().nextInt(500) > 0) {
			new Village().generate(startX, endX, height);
		}
	}

	public void tick(int camX, int camY, int renW, int renH) {
		biome.tick(camX, camY, renW, renH);
	}

	public void render(int camX, int camY) {
		// TODO FIX THIS BAD BOY

		GL11.glDisable(GL11.GL_BLEND);
		biome.render(camX, camY);

		// if (camX / Tile.tileSize > startX && camX / Tile.tileSize + Core.level.CHUNKW < endX) {

		// }

		// if (camX / Tile.tileSize + 12 >= startX && camX / Tile.tileSize + Core.level.CHUNKW - 12 <= endX) {

		// }
	}
}
