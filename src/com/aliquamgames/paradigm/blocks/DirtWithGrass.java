package com.aliquamgames.paradigm.blocks;

import org.lwjgl.util.Rectangle;

public class DirtWithGrass extends Block {

	public DirtWithGrass(Rectangle size, int[] id, int strength,int spotX, int spotY, String name) {
		super(size, id, strength, spotX, spotY, name);
	}

	public void tick() {

	}

	public void render() {
		super.render();
	}
}
