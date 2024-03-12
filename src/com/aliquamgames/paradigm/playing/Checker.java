package com.aliquamgames.paradigm.playing;

import com.aliquamgames.paradigm.Core;

public class Checker {
	
	public void fixInventory(int x, int y) {
		for(int i = 0; i < Tile.numberOfBlocks; i++) {
			if(Core.level.block[x][y].id[0] == Tile.getBlock(i)[0] && Core.level.block[x][y].id[1] == Tile.getBlock(i)[1]) {
				Core.level.block[x][y].id = Tile.getBlock(i);
			}
		}	}

}
