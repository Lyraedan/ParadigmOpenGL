package com.aliquamgames.paradigm.level;

import java.util.Random;

import com.aliquamgames.paradigm.Core;
import com.aliquamgames.paradigm.playing.Tile;
import com.aliquamgames.paradigm.weather.LightningStormWeather;
import com.aliquamgames.paradigm.weather.RainWeather;
import com.aliquamgames.paradigm.weather.Weather;

public class OceanBiome extends Biome {

	public OceanBiome() {
		weather = new Weather[2];
		weather[0] = new RainWeather();
		weather[1] = new LightningStormWeather();
	}

	public int[] waterHeight = { 2, 4, 6, 16, 25 };
	private int oceanLevel = waterHeight[new Random().nextInt(waterHeight.length)];

	@SuppressWarnings("static-access")
	@Override
	public void generate(int startX, int endX, int height) {
		for (int x = startX; x < endX; x++) {
			for (int y = 0; y < Core.level.worldH; y++) {
				if (y > Core.level.worldH / oceanLevel) {
					Core.level.block[x][y].id = Tile.water;
				}

			}
		}
	}

}
