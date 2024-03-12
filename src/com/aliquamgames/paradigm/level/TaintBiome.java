package com.aliquamgames.paradigm.level;

import java.util.Random;

import com.aliquamgames.paradigm.Core;
import com.aliquamgames.paradigm.playing.Tile;
import com.aliquamgames.paradigm.weather.BloodRainWeather;
import com.aliquamgames.paradigm.weather.Weather;

public class TaintBiome extends Biome {

	public TaintBiome() {
		weather = new Weather[1];
		weather[0] = new BloodRainWeather();
	}

	@SuppressWarnings("static-access")
	@Override
	public void generate(int startX, int endX, int height) {
		for (int x = startX; x < endX; x++) {
			for (int y = 0; y < Core.level.worldH; y++) {
				if (y > Core.level.worldW >> 4) {
					try {
						if (new Random().nextInt(150) > 50) {
							if (Core.level.block[x - 1][y - 1].id == Tile.dirt) {
								Core.level.block[x][y].id = Tile.dirt;
							}
						}
					} catch (Exception e) {
						if (new Random().nextInt(200) > 150) {
							if (Core.level.block[x][y].id == Tile.dirt) {
								Core.level.block[x - 1][y].id = Tile.dirt;
							}
						}
					}
					try {
						if (new Random().nextInt(50) > 25) {
							if (Core.level.block[x + 1][y - 1].id == Tile.dirt) {
								Core.level.block[x][y - 1].id = Tile.dirt;
							}
						}
					} catch (Exception e) {

					}
					try {
						if (new Random().nextInt(25) > 22.5) {
							if (Core.level.block[x + 1][y - 1].id == Tile.dirt) {
								Core.level.block[x][y].id = Tile.dirt;
							}
						}
					} catch (Exception e) {

					}
					try {
						if (new Random().nextInt(300) > 150) {
							if (Core.level.block[x + 1][y + 1].id == Tile.dirt) {
								Core.level.block[x + 1][y + 1].id = Tile.dirt;
							}
						}
					} catch (Exception e) {

					}
					try {
						if (new Random().nextInt(500) > 250) {
							if (Core.level.block[x - 1][y + 1].id == Tile.dirt) {
								Core.level.block[x - 1][y - 1].id = Tile.dirt;
							}
						}
					} catch (Exception e) {

					}
					try {
						if (new Random().nextInt(20) > 10) {
							if (Core.level.block[x][y].id != Tile.dirt) {
								Core.level.block[x][y].id = Tile.dirt;
							}
						}
					} catch (Exception e) {

					}
				}
			}
		}
		for (int x = startX; x < endX; x++) {
			for (int y = 0; y > Core.level.worldH; y++) {
				if (Core.level.block[x][y].id == Tile.dirt && Core.level.block[x][y - 1].id == Tile.air) {
					Core.level.block[x][y].id = Tile.hellStone;
				}
			}
		}
		// TODO FIX
		// Core.level.getCol(0xffffff);
	}

}