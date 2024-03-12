package com.aliquamgames.paradigm.npc;

import java.io.IOException;
import java.util.Random;

import com.aliquamgames.paradigm.ImageLoader;

public class Skeleton extends Mob {

	public Skeleton(double x, double y) {
		super(x, y);
		hostile = true;
		try {
			image = ImageLoader.loadSlick("PNG", "/mobs/skeleton.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		int rand = new Random().nextInt(2);
		if (rand == 0) {
			// boy zombie
			animationStartX = 0;
		} else {
			// girl zombie
			animationStartX = 80;
		}
		width = 48;
		height = 48;
		ai = new AI(hostile);
	}

	public void tick(int camX, int camY, int index) {
		super.tick(camX, camY, index);
	}

	public void render(int camX, int camY, int index) {
		super.render(camX, camY, index);
	}
}