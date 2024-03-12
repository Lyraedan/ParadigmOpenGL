package com.aliquamgames.paradigm.weather;

import java.util.ArrayList;

import com.aliquamgames.paradigm.Core;
import com.aliquamgames.paradigm.level.InfiniteLevel.BiomeEnum;
import com.aliquamgames.paradigm.playing.Tile;

public class BloodRainWeather extends Weather {

	public ArrayList<BloodRain> bloodRain = new ArrayList<BloodRain>();
	private int maxBloodRain = 150;
	private BiomeEnum[] biomesThatBloodRain = { BiomeEnum.TAINT };
	private long currentTime = getTime();
	private long lastTime = getTime();
	private byte waitTime = 75;

	public BloodRainWeather() {

	}

	@SuppressWarnings("static-access")
	@Override
	public void tick(int camX, int camY, int renW, int renH) {
		if (active) {
			currentTime = getTime();
			if (currentTime > lastTime + waitTime) {
				lastTime = getTime();
				stopSoundsIfBiomeIsWrong();
				if (bloodRain.toArray().length <= maxBloodRain) {
					int startX = 0;
					int endX = 0;
					for (int i = Core.level.getIndexOfBiomePlayerIsIn() - 1; i < Core.level.getIndexOfBiomePlayerIsIn() + 2; i++) {
						if (i < 0) continue;
						if (i >= Core.level.generatedChunks) break;
						for (int b = 0; b < biomesThatBloodRain.length; b++) {
							if (Core.level.biomeArray[i].equals(biomesThatBloodRain[b])) {
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

		for (int i = 0; i < bloodRain.toArray().length; i++) {
			bloodRain.get(i).tick();
			if (bloodRain.get(i).remove) bloodRain.remove(i);
		}
		if (!playSound) {
			// TODO FIX WHEN THERE IS SOUNDS
			// Core.rain2.stop();
		}
	}

	@Override
	public void render(int camX, int camY) {
		for (int i = 0; i < bloodRain.toArray().length; i++) {
			bloodRain.get(i).render(camX, camY);
		}
	}

	@Override
	public void stopSoundsIfBiomeIsWrong() {
		boolean[] booleans = new boolean[biomesThatBloodRain.length];
		for (int i = 0; i < biomesThatBloodRain.length; i++) {
			booleans[i] = true;
			if (!Core.level.getBiomePlayerIsIn().equals(biomesThatBloodRain[i])) {
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
		bloodRain.add(new BloodRain(startX, endX, camX));
	}
}
