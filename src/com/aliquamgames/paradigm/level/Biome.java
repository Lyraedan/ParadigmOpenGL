package com.aliquamgames.paradigm.level;

import com.aliquamgames.paradigm.weather.Weather;

public abstract class Biome {

	protected Weather[] weather;
	private int currentWeather = 0;
	private boolean setCurrentWeather = false;

	public Biome() {

	}

	public abstract void generate(int startX, int endX, int height);

	@SuppressWarnings("static-access")
	public void tick(int camX, int camY, int renW, int renH) {
		weather[currentWeather].tick(camX, camY, renW, renH);
		if (!weather[currentWeather].active && !setCurrentWeather) {
			currentWeather++;
			if (currentWeather >= weather.length) currentWeather = 0;
			setCurrentWeather = true;
		}
		if (weather[currentWeather].active) {
			setCurrentWeather = false;
		}
	}

	public void render(int camX, int camY) {
		weather[currentWeather].render(camX, camY);

	}
}
