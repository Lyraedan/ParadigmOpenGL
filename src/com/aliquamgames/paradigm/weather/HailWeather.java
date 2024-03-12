package com.aliquamgames.paradigm.weather;

import java.util.ArrayList;

import com.aliquamgames.paradigm.Core;
import com.aliquamgames.paradigm.level.InfiniteLevel.BiomeEnum;
import com.aliquamgames.paradigm.playing.Tile;

public class HailWeather extends Weather {

	private BiomeEnum[] biomesThatHail = { BiomeEnum.SNOW };

	private int maxHail = 250;
	private ArrayList<Hail> hail = new ArrayList<Hail>();
	private long currentTime = getTime();
	private long lastTime = getTime();
	private byte waitTime = 75;

	public HailWeather() {

	}

	@SuppressWarnings("static-access")
	@Override
	public void tick(int camX, int camY, int renW, int renH) {
		if (active) {
			stopSoundsIfBiomeIsWrong();
			currentTime = getTime();
			if (currentTime > lastTime + waitTime) {
				lastTime = getTime();
				if (hail.toArray().length <= maxHail) {
					int startX = 0;
					int endX = 0;
					for (int i = Core.level.getIndexOfBiomePlayerIsIn() - 1; i < Core.level.getIndexOfBiomePlayerIsIn() + 2; i++) {
						if (i < 0) continue;
						if (i >= Core.level.generatedChunks) break;
						for (int b = 0; b < biomesThatHail.length; b++) {
							if (Core.level.biomeArray[i].equals(biomesThatHail[b])) {
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
		for (int i = 0; i < hail.toArray().length; i++) {
			hail.get(i).tick();
			if (hail.get(i).remove) hail.remove(i);
		}
		if (!playSound) {
			// TODO FIX WHEN THERE IS SOUNDS
			// Core.rain2.stop();
		}
	}

	public void render(int camX, int camY) {
		for (int i = 0; i < hail.toArray().length; i++) {
			hail.get(i).render(camX, camY);
		}

	}

	@Override
	public void stopSoundsIfBiomeIsWrong() {
		boolean[] booleans = new boolean[biomesThatHail.length];
		for (int i = 0; i < biomesThatHail.length; i++) {
			booleans[i] = true;
			if (!Core.level.getBiomePlayerIsIn().equals(biomesThatHail[i])) {
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
		hail.add(new Hail(startX, endX, camX));
	}

}
