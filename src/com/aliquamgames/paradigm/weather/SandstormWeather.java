package com.aliquamgames.paradigm.weather;

import java.util.ArrayList;

import com.aliquamgames.paradigm.Core;
import com.aliquamgames.paradigm.level.InfiniteLevel.BiomeEnum;
import com.aliquamgames.paradigm.playing.Tile;

public class SandstormWeather extends Weather {

	public ArrayList<Sandstorm> sandstorm = new ArrayList<Sandstorm>();
	private int maxSandstorm = 150;
	private BiomeEnum[] biomesThatSandstorm = { BiomeEnum.DESERT };
	private long currentTime = getTime();
	private long lastTime = getTime();
	private byte waitTime = 75;

	public SandstormWeather() {

	}

	@SuppressWarnings("static-access")
	@Override
	public void tick(int camX, int camY, int renW, int renH) {
		if (active) {
			stopSoundsIfBiomeIsWrong();
			currentTime = getTime();
			if (currentTime > lastTime + waitTime) {
				lastTime = getTime();
				if (sandstorm.toArray().length <= maxSandstorm) {
					int startX = 0;
					int endX = 0;
					for (int i = Core.level.getIndexOfBiomePlayerIsIn() - 1; i < Core.level.getIndexOfBiomePlayerIsIn() + 2; i++) {
						if (i < 0) continue;
						if (i >= Core.level.generatedChunks) break;
						for (int b = 0; b < biomesThatSandstorm.length; b++) {
							if (Core.level.biomeArray[i].equals(biomesThatSandstorm[b])) {
								startX = i * Tile.tileSize * Core.level.CHUNKW;
								endX = (i + 1) * Tile.tileSize * Core.level.CHUNKW;
								add(startX, endX, camX);
							}
						}
					}
				}
			}
		} else {
			playSound = false;
		}
		for (int i = 0; i < sandstorm.toArray().length; i++) {
			sandstorm.get(i).tick();
			if (sandstorm.get(i).remove) sandstorm.remove(i);
		}
		if (!playSound) {
			// TODO FIX WHEN THERE IS SOUNDS
			// Core.rain2.stop();
		}
	}

	public void render(int camX, int camY) {
		for (int i = 0; i < sandstorm.toArray().length; i++) {
			sandstorm.get(i).render(camX, camY);
		}
	}

	@Override
	public void stopSoundsIfBiomeIsWrong() {
		boolean[] booleans = new boolean[biomesThatSandstorm.length];
		for (int i = 0; i < biomesThatSandstorm.length; i++) {
			booleans[i] = true;
			if (!Core.level.getBiomePlayerIsIn().equals(biomesThatSandstorm[i])) {
				booleans[i] = false;
			}
		}
		// TODO this needs to be changed once more biomes with rain is add
		if (!booleans[0]) {
			playSound = false;
		}
	}

	@Override
	public void add(int startX, int endX, int camX) {
		sandstorm.add(new Sandstorm(startX, endX, camX));
	}
}
