package com.aliquamgames.paradigm.weather;

import java.util.ArrayList;

import com.aliquamgames.paradigm.Core;
import com.aliquamgames.paradigm.level.InfiniteLevel.BiomeEnum;
import com.aliquamgames.paradigm.playing.Tile;

public class LightningStormWeather extends Weather {

	public ArrayList<Lightning> lightning = new ArrayList<Lightning>();
	private int maxLightning = 2;
	private BiomeEnum[] biomesThatLightning = { BiomeEnum.FOREST, BiomeEnum.JUNGLE, BiomeEnum.MOUNTAINS, BiomeEnum.PLAINS };
	private long currentTime = getTime();
	private long lastTime = getTime();
	private byte waitTime = 75;

	public LightningStormWeather() {

	}

	@SuppressWarnings("static-access")
	@Override
	public void tick(int camX, int camY, int renW, int renH) {
		if (active) {
			stopSoundsIfBiomeIsWrong();
			currentTime = getTime();
			if (currentTime > lastTime + waitTime) {
				lastTime = getTime();
				if (lightning.toArray().length <= maxLightning) {
					int startX = 0;
					int endX = 0;
					for (int i = Core.level.getIndexOfBiomePlayerIsIn() - 1; i < Core.level.getIndexOfBiomePlayerIsIn() + 2; i++) {
						if (i < 0) continue;
						if (i >= Core.level.generatedChunks) break;
						for (int b = 0; b < biomesThatLightning.length; b++) {
							if (Core.level.biomeArray[i].equals(biomesThatLightning[b])) {
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

		for (int i = 0; i < lightning.toArray().length; i++) {
			lightning.get(i).tick();
			if (lightning.get(i).remove) lightning.remove(i);
		}
		if (!playSound) {
			// TODO FIX WHEN THERE IS SOUNDS
			// Core.rain2.stop();
		}
	}

	public void render(int camX, int camY) {
		for (int i = 0; i < lightning.toArray().length; i++) {
			lightning.get(i).render(camX, camY);
		}
	}

	@Override
	public void stopSoundsIfBiomeIsWrong() {
		boolean[] booleans = new boolean[biomesThatLightning.length];
		for (int i = 0; i < biomesThatLightning.length; i++) {
			booleans[i] = true;
			if (!Core.level.getBiomePlayerIsIn().equals(biomesThatLightning[i])) {
				booleans[i] = false;
			}
		}
		// TODO this needs to be changed once more biomes with rain is add
		if (!booleans[0] && !booleans[1] && !booleans[2] && !booleans[3]) {
			playSound = false;
		}
	}

	@Override
	public void add(int startX, int endX, int camX) {
		lightning.add(new Lightning(startX, endX, camX));
	}

}
