package com.aliquamgames.paradigm.weather;

import java.util.ArrayList;

import com.aliquamgames.paradigm.Core;
import com.aliquamgames.paradigm.level.InfiniteLevel.BiomeEnum;
import com.aliquamgames.paradigm.playing.Tile;

public class SnowWeather extends Weather {

	public ArrayList<Snow> snow = new ArrayList<Snow>();
	private int maxSnow = 250;
	private BiomeEnum[] biomesThatSnow = { BiomeEnum.SNOW };
	private long currentTime = getTime();
	private long lastTime = getTime();
	private byte waitTime = 125;

	public SnowWeather() {

	}

	@SuppressWarnings("static-access")
	@Override
	public void tick(int camX, int camY, int renW, int renH) {
		if (active) {
			stopSoundsIfBiomeIsWrong();
			currentTime = getTime();
			if (currentTime > lastTime + waitTime) {
				lastTime = getTime();
				if (snow.toArray().length <= maxSnow) {
					int startX = 0;
					int endX = 0;
					for (int i = Core.level.getIndexOfBiomePlayerIsIn() - 1; i < Core.level.getIndexOfBiomePlayerIsIn() + 2; i++) {
						if (i < 0) continue;
						if (i >= Core.level.generatedChunks) break;
						for (int b = 0; b < biomesThatSnow.length; b++) {
							if (Core.level.biomeArray[i].equals(biomesThatSnow[b])) {
								startX = i * Tile.tileSize * Core.level.CHUNKW;
								endX = (i + 1) * Tile.tileSize * Core.level.CHUNKW;
								add(startX, endX, camX);
							}
						}
					}
				}
			}
		}

		for (int i = 0; i < snow.toArray().length; i++) {
			snow.get(i).tick();
			if (snow.get(i).remove) snow.remove(i);
		}
	}

	@Override
	public void render(int camX, int camY) {
		for (int i = 0; i < snow.toArray().length; i++) {
			snow.get(i).render(camX, camY);
		}
	}

	@Override
	public void stopSoundsIfBiomeIsWrong() {
		boolean[] booleans = new boolean[biomesThatSnow.length];
		for (int i = 0; i < biomesThatSnow.length; i++) {
			booleans[i] = true;
			if (!Core.level.getBiomePlayerIsIn().equals(biomesThatSnow[i])) {
				booleans[i] = false;
			}
		}
		// TODO this needs to be changed once more biomes with snow is add
		if (!booleans[0]) {
			playSound = false;
		}
	}

	@Override
	public void add(int startX, int endX, int camX) {
		snow.add(new Snow(startX, endX, camX));
	}

}
