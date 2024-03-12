package com.aliquamgames.paradigm.blocks;

import org.lwjgl.util.Rectangle;

import com.aliquamgames.paradigm.Core;
import com.aliquamgames.paradigm.playing.Tile;


public class Door extends Block {
	
	public static boolean isOpen = false;
	
	public Door(Rectangle size, int[] id, int strength, int spotX, int spotY, String name) {
		super(size, id, strength, spotX, spotY, name);
	}
	
	public void checkIfOpen(int x, int y) {
		if(isOpen) {
			if(Core.level.block[x][y].id == Tile.door && Core.level.block[x][y - 1].id == Tile.door) {
				Core.level.block[x][y].id = Tile.doorBottomOpen;
				Core.level.block[x][y - 1].id = Tile.doorTopOpen;
			} else if(Core.level.block[x][y].id == Tile.doorBottomOpen && Core.level.block[x][y - 1].id == Tile.doorTopOpen) {
				Core.level.block[x][y].id = Tile.door;
				Core.level.block[x][y - 1].id = Tile.door;
			}
		}
	}
	
	public void tick() {
		super.tick();
		checkIfOpen(spotX, spotY);
	}
	
	public void render() {
		super.render();
	}

}
