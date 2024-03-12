package com.aliquamgames.paradigm.weather;

import java.util.Random;

import org.newdawn.slick.Color;

import com.aliquamgames.paradigm.Core;
import com.aliquamgames.paradigm.util.Util;

public class Sandstorm {

	// the x for the sandstorm
	public int x;
	// the y for the sandstorm
	public int y;
	// the movement speed
	private int movementSpeed = 9;
	// the width of the sandstorm
	private int width = 3;

	// controls if it needs to be removed or not
	public boolean remove = false;

	// the color of the sandstorm
	private static Color color = Color.yellow;

	public Sandstorm(int startX, int endX, int camX) {
		x = new Random().nextInt(endX + 1 - startX) + startX;
		y = (int) (Core.player.getYPosition() - Core.getGameHeight() / 2 - 10);

		width = 5 * 3;
	}

	public void tick() {
		y += movementSpeed;
		if (y > (Core.player.getYPosition() + Core.getGameHeight())) remove = true;
	}

	public void render(int camX, int camY) {
		if (y > Core.player.getYPosition() - Core.getGameHeight() / 2 - 30 && x > Core.player.getXPosition() - Core.getGameWidth() / 2 - 30 && x < Core.player.getXPosition() + Core.getGameWidth() / 2 + 30) {
			Util.fillColoredRect(color, (float) (x - camX), (float) (y - camY), width, width);
		}
	}
}
