package com.aliquamgames.paradigm.weather;

import java.util.Random;

import org.newdawn.slick.Color;

import com.aliquamgames.paradigm.Core;
import com.aliquamgames.paradigm.util.Util;

public class Hail {

	public int x;
	public int y;
	private int movementSpeed = 20;
	private int width = 2;

	public boolean remove = false;

	private Color color = Color.cyan;

	public Hail(int startX, int endX, int camX) {
		x = new Random().nextInt(endX + 1 - startX) + startX;
		y = (int) (Core.player.getYPosition() - Core.getGameHeight() / 2 - 10);

		width = (10) * 3;
	}

	public void tick() {
		y += movementSpeed;
		if (y > (Core.player.getYPosition() + Core.getGameHeight())) remove = true;
	}

	public void render(int camX, int camY) {
		if (y > Core.player.getYPosition() - Core.getGameHeight() / 2 - 30 && x > Core.player.getXPosition() - Core.getGameWidth() / 2 && x < Core.player.getXPosition() + Core.getGameWidth() / 2) {
			Util.fillColoredRect(color, (float) (x - camX), (float) (y - camY), width, width);
		}
	}
}
