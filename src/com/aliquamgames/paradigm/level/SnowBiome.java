package com.aliquamgames.paradigm.level;

import java.util.Random;

import com.aliquamgames.paradigm.Core;
import com.aliquamgames.paradigm.playing.Tile;
import com.aliquamgames.paradigm.weather.BlizzardWeather;
import com.aliquamgames.paradigm.weather.HailWeather;
import com.aliquamgames.paradigm.weather.SnowWeather;
import com.aliquamgames.paradigm.weather.Weather;

public class SnowBiome extends Biome {

	public SnowBiome() {
		weather = new Weather[3];
		weather[0] = new SnowWeather();
		weather[1] = new HailWeather();
		weather[2] = new BlizzardWeather();
	}

	@SuppressWarnings("static-access")
	@Override
	public void generate(int startX, int endX, int height) {
		for (int x = startX; x < endX; x++) {
			for (int y = 0; y < Core.level.worldH; y++) {
				if (y > Core.level.worldH >> 4) {
					if (new Random().nextInt(100) > 20) {
						try {
							if (Core.level.block[x - 1][y - 1].id == Tile.dirt) {
								Core.level.block[x][y].id = Tile.dirt;
							}
						} catch (Exception e) {
						}
					}
					if (new Random().nextInt(100) > 30) {
						try {
							if (Core.level.block[x + 1][y - 1].id == Tile.dirt) {
								Core.level.block[x][y].id = Tile.dirt;
							}
						} catch (Exception e) {
						}
					}
					try {
						if (Core.level.block[x][y - 1].id == Tile.dirt) {
							Core.level.block[x][y].id = Tile.dirt;
						}

					} catch (Exception e) {
					}
					try {
						if (new Random().nextInt(100) < 15) {
							Core.level.block[x][y].id = Tile.dirt;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		for (int x = startX; x < endX; x++) {
			for (int y = 0; y < Core.level.worldH; y++) {
				if (Core.level.block[x][y].id == Tile.dirt && Core.level.block[x][y - 1].id == Tile.air) {
					Core.level.block[x][y].id = Tile.dirtLightWithSnow;
				}
			}
		}
		// TODO FIX
		// Core.level.getCol(0xffffff);
	}

}
