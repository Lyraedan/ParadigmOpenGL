package com.aliquamgames.paradigm.weather;

import java.util.Random;

import org.lwjgl.Sys;

public abstract class Weather {

	// controls if the weather is active
	public static boolean active = true;
	// controls if it should play the sound associated with the weather
	public static boolean playSound = false;
	// this is the current time of the system in milliseconds
	public static long currentTime = getTime();
	// the last time of the system in milliseconds
	public static long lastTime = currentTime;
	// the time it has to wait for another storm to occur, in milliseconds
	public static int waitTime = 45000;
	// this is the starting time of the storm in milliseconds
	public static long startTime = getTime();
	// how long the storm lasts in milliseconds
	public static long weatherTime = 25000;

	public Weather() {

	}

	public abstract void tick(int camX, int camY, int renW, int renH);

	public abstract void render(int camX, int camY);

	public abstract void stopSoundsIfBiomeIsWrong();

	public abstract void add(int startX, int endX, int camX);
	
	public static long getTime() {
		return Sys.getTime() * 1000 / Sys.getTimerResolution();
	}

	public static void controlWeather() {
		currentTime = getTime();
		if (currentTime > lastTime + waitTime && !active) {
			waitTime = (new Random().nextInt(60) + 60) * 1000;
			lastTime = getTime();
			startTime = getTime();
			active = true;
		}
		if (active) {
			if (currentTime > startTime + weatherTime) {
				weatherTime = (new Random().nextInt(50) + 25) * 1000;
				active = false;
			}
		}
	}

}
