package com.aliquamgames.paradigm.blocks;

import org.lwjgl.util.Rectangle;

import com.aliquamgames.paradigm.Core;
import com.aliquamgames.paradigm.playing.Tile;

public class Pipe extends Block {

	public Pipe(Rectangle size, int[] id, int strength, int spotX, int spotY, String name) {
		super(size, id, strength, spotX, spotY, name);
	}

	@SuppressWarnings("static-access")
	public void tick() {
		super.tick();

		if (!hasRunningWater) {
			if (rightInput) {
				if (Core.level.block[spotX + 1][spotY].id == Tile.water) hasRunningWater = true;
			}
			if (leftInput) {
				if (Core.level.block[spotX - 1][spotY].id == Tile.water) hasRunningWater = true;
			}
			if (upInput) {
				if (Core.level.block[spotX][spotY - 1].id == Tile.water) hasRunningWater = true;
			}
			if (downInput) {
				if (Core.level.block[spotX][spotY + 1].id == Tile.water) hasRunningWater = true;
			}
		} else {
			if (rightInput) {
				if (Core.level.block[spotX + 1][spotY].id != Tile.water) hasRunningWater = false;
			}
			if (leftInput) {
				if (Core.level.block[spotX - 1][spotY].id != Tile.water) hasRunningWater = false;
			}
			if (upInput) {
				if (Core.level.block[spotX][spotY - 1].id != Tile.water) hasRunningWater = false;
			}
			if (downInput) {
				if (Core.level.block[spotX][spotY + 1].id != Tile.water) hasRunningWater = false;
			}

			if (rightOutput) {
				if (Core.level.block[spotX + 1][spotY].id == Tile.air) Core.level.block[spotX + 1][spotY].id = Tile.water;
			}
			if (leftOutput) {
				if (Core.level.block[spotX - 1][spotY].id == Tile.air) Core.level.block[spotX - 1][spotY].id = Tile.water;
			}
			if (upOutput) {
				if (Core.level.block[spotX][spotY - 1].id == Tile.air) Core.level.block[spotX][spotY - 1].id = Tile.water;
			}
			if (downOutput) {
				if (Core.level.block[spotX][spotY + 1].id == Tile.air) Core.level.block[spotX][spotY + 1].id = Tile.water;
				else if (Core.level.block[spotX][spotY + 1].id == Tile.pipe) Core.level.block[spotX][spotY + 1].hasRunningWater = true;
			}
		}

	}

	public void render() {
		super.render();
	}
}
