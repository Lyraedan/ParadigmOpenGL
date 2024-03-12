package com.aliquamgames.paradigm.weather;

import java.util.Random;

import org.newdawn.slick.Color;

import com.aliquamgames.paradigm.Core;
import com.aliquamgames.paradigm.util.Util;

public class BloodRain {
	// the x for the rain
	public int x;
	// the y for the rain
	public int y;
	// the movement speed
	private int movementSpeed = 18;
	// the length of the rain
	private int length = 3;

	// controls if it needs to be removed or not
	public boolean remove = false;

	// the color of the rain
	private static Color color = Color.red;

	public BloodRain(int startX, int endX, int camX) {
		x = new Random().nextInt(endX + 1 - startX) + startX;
		y = (int) (Core.player.getYPosition() - Core.getGameHeight() / 2 - 10);

		color = Color.red;
		length = (new Random().nextInt(5) + 3) * 3;
	}

	public void tick() {
		y += movementSpeed;
		if (y > (Core.player.getYPosition() + Core.getGameHeight() / 2)) remove = true;
	}

	public void render(int camX, int camY) {
		if (y > Core.player.getYPosition() - Core.getGameHeight() / 2 - 30 && x > Core.player.getXPosition() - Core.getGameWidth() / 2 && x < Core.player.getXPosition() + Core.getGameWidth() / 2) {
			Util.drawColoredLine(color, (float) (x - camX), (float) (y - camY), (float) (x - camX), (float) (y - camY + length), 3);
		}
	}
}
