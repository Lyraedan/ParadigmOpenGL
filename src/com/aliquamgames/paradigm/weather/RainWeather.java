package com.aliquamgames.paradigm.weather;

import java.util.ArrayList;

import com.aliquamgames.paradigm.Core;
import com.aliquamgames.paradigm.level.InfiniteLevel.BiomeEnum;
import com.aliquamgames.paradigm.playing.Tile;

public class RainWeather extends Weather {

	public ArrayList<Rain> rain = new ArrayList<Rain>();
	private int maxRain = 150;
	private BiomeEnum[] biomesThatRain = { BiomeEnum.FOREST, BiomeEnum.JUNGLE, BiomeEnum.MOUNTAINS, BiomeEnum.OCEAN, BiomeEnum.PLAINS };
	private long currentTime = 0;
	private long lastTime = 0;
	private byte waitTime = 75;

	public RainWeather() {

	}

	@SuppressWarnings("static-access")
	@Override
	public void tick(int camX, int camY, int renW, int renH) {
		if (active) {
			Core.rain.setVolume(0.5f, 0.5f);
			Core.rain.loop("WAV", false, "SOUND");
			currentTime = getTime();
			if (currentTime > lastTime + waitTime) {
				lastTime = getTime();
				stopSoundsIfBiomeIsWrong();
				if (rain.toArray().length <= maxRain) {
					int startX = 0;
					int endX = 0;
					for (int i = Core.level.getIndexOfBiomePlayerIsIn() - 1; i < Core.level.getIndexOfBiomePlayerIsIn() + 2; i++) {
						if (i < 0) continue;
						if (i >= Core.level.generatedChunks) break;
						for (int b = 0; b < biomesThatRain.length; b++) {
							if (Core.level.biomeArray[i].equals(biomesThatRain[b])) {
								startX = i * Tile.tileSize * Core.level.CHUNKW;
								endX = (i + 1) * Tile.tileSize * Core.level.CHUNKW;
								add(startX, endX, camX);
							}
						}
					}
				}
			}
		} else {
			Core.rain.stop("WAV", false);
		}
		// for (int x = (camX / Tile.tileSize); x < (camX / Tile.tileSize) + renW; x++) {
		// for (int y = (camY / Tile.tileSize); y < (camY / Tile.tileSize) + renH; y++) {
		// // for (int y = 0; y < (Core.player.getYPosition() / Tile.tileSize) + renH / 2; y++) {
		// if (x >= 0 && x < Core.level.worldW && y >= 0 && y < Core.level.worldH) {
		// if (Core.level.block[x][y].id != Tile.air) {
		// for (int i = 0; i < rain.toArray().length; i++) {
		// if (rain.get(i).rect.intersects(Core.level.block[x][y])) {
		// rain.remove(i);
		// }
		// }
		// }
		// }
		// }
		// }
		for (int i = 0; i < rain.toArray().length; i++) {
			rain.get(i).tick();
			if (rain.get(i).remove) rain.remove(i);
		}
	}

	@Override
	public void render(int camX, int camY) {
		for (int i = 0; i < rain.toArray().length; i++) {
			rain.get(i).render(camX, camY);
		}
	}

	@Override
	public void stopSoundsIfBiomeIsWrong() {
		boolean[] booleans = new boolean[biomesThatRain.length];
		for (int i = 0; i < biomesThatRain.length; i++) {
			booleans[i] = true;
			if (!Core.level.getBiomePlayerIsIn().equals(biomesThatRain[i])) {
				booleans[i] = false;
			}
		}
		// TODO this needs to be changed once more biomes with rain is add
		if (!booleans[0] && !booleans[1] && !booleans[2] && !booleans[3] && !booleans[4]) {
			playSound = false;
		}
	}

	@Override
	public void add(int startX, int endX, int camX) {
		rain.add(new Rain(startX, endX, camX));
	}
}
