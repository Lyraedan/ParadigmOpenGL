package com.aliquamgames.paradigm.blocks;

import org.lwjgl.util.Rectangle;

public class Dirt extends Block {

	public Dirt(Rectangle size, int[] id, int strength, int x, int y, String name) {
		super(size, id, strength, x, y, name);
//		this.setBounds(size);
	}

	public void tick() {
		super.tick();

	}

	public void render() {
		super.render();
	}
}
