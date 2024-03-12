package com.aliquamgames.paradigm.level;

import java.util.Random;

import com.aliquamgames.paradigm.Core;
import com.aliquamgames.paradigm.playing.Tile;
import com.aliquamgames.paradigm.weather.Weather;
import com.aliquamgames.paradigm.weather.SandstormWeather;

public class DesertBiome extends Biome {

	public DesertBiome() {
		weather = new Weather[1];
		weather[0] = new SandstormWeather();
	}

	@SuppressWarnings("static-access")
	@Override
	public void generate(int startX, int endX, int height) {
		for (int x = startX; x < endX; x++) {
			for (int y = 0; y < Core.level.worldH; y++) {
				if (y > Core.level.worldH >> 4) { // ... / 2) - old
					try {
						if (new Random().nextInt(10) > 20) {
							if (Core.level.block[x - 1][y - 1].id == Tile.sand) {
								Core.level.block[x][y].id = Tile.sand;
								// Core.level.blockUnderlayer[x][y].id = Tile.sand;
							}
						}
						if (new Random().nextInt(10) > 30) {
							if (Core.level.block[x + 1][y - 1].id == Tile.sand) {
								Core.level.block[x][y].id = Tile.sand;
								// Core.level.blockUnderlayer[x][y].id = Tile.sand;
							}
						}
						if (Core.level.block[x][y - 1].id == Tile.sand) {
							Core.level.block[x][y].id = Tile.sand;
							Core.level.block[x][y + 1].id = Tile.sand;
							Core.level.block[x - 1][y].id = Tile.sand;
							Core.level.block[x + 1][y].id = Tile.sand;
							Core.level.block[x + 2][y].id = Tile.sand;
							Core.level.block[x + 2][y + 2].id = Tile.sand;
							// Core.level.blockUnderlayer[x][y].id = Tile.sand;
							// Core.level.blockUnderlayer[x][y+1].id = Tile.sand;
							// Core.level.blockUnderlayer[x-1][y].id = Tile.sand;
							// Core.level.blockUnderlayer[x+1][y].id = Tile.sand;
							// Core.level.blockUnderlayer[x+2][y].id = Tile.sand;
							// Core.level.blockUnderlayer[x+2][y+2].id = Tile.sand;
						}
					} catch (Exception e) {

					}
					if (new Random().nextInt(100) < 2) {
						try {
							Core.level.block[x][y].id = Tile.sand;
							// Core.level.blockUnderlayer[x][y].id = Tile.sand;
						} catch (ArrayIndexOutOfBoundsException e) {
							continue;
						}
					}
				}
			}
		}
		for (int x = startX; x < endX; x++) {
			for (int y = 0; y > Core.level.worldH; y++) {
				if (Core.level.block[x][y].id == Tile.dirt && Core.level.block[x][y - 1].id == Tile.air) {
					Core.level.block[x][y].id = Tile.sand;
				}
			}
		}
	}

}
