package com.aliquamgames.paradigm.weather;

import java.util.ArrayList;

import com.aliquamgames.paradigm.Core;
import com.aliquamgames.paradigm.level.InfiniteLevel.BiomeEnum;
import com.aliquamgames.paradigm.playing.Tile;

public class BlizzardWeather extends Weather {

	public ArrayList<Hail> hail = new ArrayList<Hail>();
	public ArrayList<Snow> snow = new ArrayList<Snow>();
	private int maxSnow = 150;
	private int maxHail = 150;
	private BiomeEnum[] biomesThatBlizzard = { BiomeEnum.SNOW };
	private long currentTime = getTime();
	private long lastTime = getTime();
	private byte waitTime = 100;

	public BlizzardWeather() {

	}

	@SuppressWarnings("static-access")
	@Override
	public void tick(int camX, int camY, int renW, int renH) {
		if (active) {
			currentTime = getTime();
			if (currentTime > lastTime + waitTime) {
				lastTime = getTime();
				stopSoundsIfBiomeIsWrong();
				int startX = 0;
				int endX = 0;
				for (int i = Core.level.getIndexOfBiomePlayerIsIn() - 1; i < Core.level.getIndexOfBiomePlayerIsIn() + 2; i++) {
					if (i < 0) continue;
					if (i >= Core.level.generatedChunks) break;
					for (int b = 0; b < biomesThatBlizzard.length; b++) {
						if (Core.level.biomeArray[i].equals(biomesThatBlizzard[b])) {
							startX = i * Tile.tileSize * Core.level.CHUNKW;
							endX = (i + 1) * Tile.tileSize * Core.level.CHUNKW;
						}
					}
				}
				if (hail.toArray().length <= maxHail) {
					addHail(startX, endX, camX);
				}
				if (snow.toArray().length <= maxSnow) {
					addSnow(startX, endX, camX);
				}
			}
		} else {
			playSound = false;
		}
		for (int i = 0; i < hail.toArray().length; i++) {
			hail.get(i).tick();
			if (hail.get(i).remove) hail.remove(i);
		}
		for (int i = 0; i < snow.toArray().length; i++) {
			snow.get(i).tick();
			if (snow.get(i).remove) snow.remove(i);
		}
		if (!playSound) {
			// TODO FIX WHEN SOUNDS ARE IMPLEMENTED
			// Core.rain2.stop();
		}
	}

	public void render(int camX, int camY) {
		for (int i = 0; i < hail.toArray().length; i++) {
			hail.get(i).render(camX, camY);
		}
		for (int i = 0; i < snow.toArray().length; i++) {
			snow.get(i).render(camX, camY);
		}
	}

	@Override
	public void stopSoundsIfBiomeIsWrong() {
		boolean[] booleans = new boolean[biomesThatBlizzard.length];
		for (int i = 0; i < biomesThatBlizzard.length; i++) {
			booleans[i] = true;
			if (!Core.level.getBiomePlayerIsIn().equals(biomesThatBlizzard[i])) {
				booleans[i] = false;
			}
		}
		// TODO this needs to be changed once more biomes with rain is add
		if (!booleans[0]) {
			playSound = false;
		}
	}

	private void addHail(int startX, int endX, int camX) {
		hail.add(new Hail(startX, endX, camX));
	}

	private void addSnow(int startX, int endX, int camX) {
		snow.add(new Snow(startX, endX, camX));
	}

	@Override
	public void add(int startX, int endX, int camX) {

	}
}
