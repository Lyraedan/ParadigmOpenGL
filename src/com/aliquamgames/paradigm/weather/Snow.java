package com.aliquamgames.paradigm.weather;

import java.util.Random;

import org.newdawn.slick.Color;

import com.aliquamgames.paradigm.Core;
import com.aliquamgames.paradigm.util.Util;

public class Snow {

	// the x for the rain
	public int x;
	// the y for the rain
	public int y;
	// the movement speed
	public int movementSpeed = 3;
	// the width of the snow flake
	private int width = 3;

	// controls if it needs to be removed or not
	public boolean remove = false;

	// the color of the rain
	private static Color color = Color.white;

	public Snow(int startX, int endX, int camX) {
		x = new Random().nextInt(endX + 1 - startX) + startX;
		y = (int) (Core.player.getYPosition() - Core.getGameHeight() / 2 - 10);
		width = (new Random().nextInt(3) + 3) * 3;
	}

	public void tick() {
		y += movementSpeed;
		if (y > (Core.player.getYPosition() + Core.getGameHeight())) remove = true;
	}

	public void render(int camX, int camY) {
		if (y > Core.player.getYPosition() - Core.getGameHeight() / 2 - 30 && x > Core.player.getXPosition() - Core.getGameWidth() / 2 && x < Core.player.getXPosition() + Core.getGameWidth() / 2) {
			Util.fillColoredRect(color, (x - camX), (y - camY), width, width);
		}
	}
}
