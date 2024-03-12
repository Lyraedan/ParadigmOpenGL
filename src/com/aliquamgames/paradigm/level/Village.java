package com.aliquamgames.paradigm.level;

import java.util.Random;

import com.aliquamgames.paradigm.Core;
import com.aliquamgames.paradigm.blocks.Block.Rotation;
import com.aliquamgames.paradigm.playing.Tile;

/**
 * 
 * @author Luke Rapkin
 */
public class Village extends Biome {
	@SuppressWarnings("static-access")
	public Village() {
		switch (Core.level.biome) {
		case PLAINS:
			break;
		case MOUNTAINS:
			break;
		case SNOW:
			break;
		case DESERT:
			break;
		case FOREST:
			break;
		case TAINT:
			break;
		case JUNGLE:
			break;
		case OCEAN:
			break;
		}
	}

	@SuppressWarnings("static-access")
	@Override
	public void generate(int startX, int endX, int height) {
		switch (Core.level.biome) {
		case PLAINS:
			break;
		case MOUNTAINS:
			break;
		case SNOW:
			break;
		case DESERT:
			for (int x = startX; x < startX + 8; x++) {
				for (int y = 1; y < Core.level.worldH - 1; y++) {
					if (Core.level.block[x][y + 1].id == Tile.sand && Core.level.block[x][y - 1].id == Tile.air) {
						if (y < 30) {
							if (new Random().nextInt(500) > 0) {
								try {
									int addX = 0;

									addX += generateHouse(x + addX, y, Tile.sand, Tile.sandStoneSlab);
									addX += 1;

									int wellAddX = addX;
									addX += generateWell(x + addX, y, Tile.stone, Tile.oakWoodSlab);
									addX += 1;

									addX += generateChurch(x + addX, y, Tile.oakWoodStair, Tile.oakWoodSlab);
									addX += 1;

									addX += generateGraves(x + addX, y, 3);
									addX += 1;

									addX += generateBlackSmith(x + addX, y, Tile.sandstone, Tile.sandStoneSlab);

									flattenLand(x, y, endX, Tile.sand);

									generateWell(x + wellAddX, y, Tile.stone, Tile.oakWoodSlab);
									return;
								} catch (NullPointerException e) {
								} catch (ArrayIndexOutOfBoundsException e) {
								}
							}
						}
					}
				}
			}

			break;
		case FOREST:
			break;
		case TAINT:
			break;
		case JUNGLE:
			break;
		case OCEAN:
			break;
		}
	}

	@SuppressWarnings("static-access")
	public int generateHouse(int x, int y, int[] mainTile, int[] slabTile) {
		Core.level.block[x][y].id = slabTile;
		Core.level.block[x + 1][y].id = mainTile;
		Core.level.block[x + 2][y].id = mainTile;
		Core.level.block[x + 3][y].id = mainTile;
		Core.level.block[x + 4][y].id = mainTile;
		Core.level.block[x + 5][y].id = mainTile;
		Core.level.block[x + 6][y].id = mainTile;
		Core.level.block[x + 7][y].id = mainTile;
		Core.level.block[x + 8][y].id = mainTile;
		Core.level.block[x + 9][y].id = slabTile;

		Core.level.block[x][y - 1].id = Tile.air;
		Core.level.block[x + 1][y - 1].id = Tile.door;
		Core.level.block[x + 2][y - 1].id = Tile.air;
		Core.level.block[x + 3][y - 1].id = Tile.air;
		Core.level.block[x + 4][y - 1].id = Tile.craftingTable;
		Core.level.block[x + 5][y - 1].id = Tile.topHalfOfBed;
		Core.level.block[x + 6][y - 1].id = Tile.bottomHalfOfBed;
		Core.level.block[x + 7][y - 1].id = Tile.air;
		Core.level.block[x + 8][y - 1].id = Tile.door;
		Core.level.block[x + 9][y - 1].id = Tile.air;

		Core.level.block[x][y - 2].id = Tile.air;
		Core.level.block[x + 1][y - 2].id = Tile.door;
		Core.level.block[x + 2][y - 2].id = Tile.air;
		Core.level.block[x + 3][y - 2].id = Tile.air;
		Core.level.block[x + 4][y - 2].id = Tile.air;
		Core.level.block[x + 5][y - 2].id = Tile.air;
		Core.level.block[x + 6][y - 2].id = Tile.air;
		Core.level.block[x + 7][y - 2].id = Tile.air;
		Core.level.block[x + 8][y - 2].id = Tile.door;
		Core.level.block[x + 9][y - 2].id = Tile.air;

		Core.level.block[x][y - 3].id = Tile.air;
		Core.level.block[x + 1][y - 3].id = Tile.sandstone;
		Core.level.block[x + 2][y - 3].id = Tile.oakWoodSlab;
		Core.level.block[x + 2][y - 3].rotation = Rotation.ROTATE180;
		Core.level.block[x + 3][y - 3].id = Tile.air;
		Core.level.block[x + 4][y - 3].id = Tile.bookShelf;
		Core.level.block[x + 5][y - 3].id = Tile.bookShelf;
		Core.level.block[x + 6][y - 3].id = Tile.air;
		Core.level.block[x + 7][y - 3].id = Tile.oakWoodSlab;
		Core.level.block[x + 7][y - 3].rotation = Rotation.ROTATE180;
		Core.level.block[x + 8][y - 3].id = Tile.sandstone;
		Core.level.block[x + 9][y - 3].id = Tile.air;

		Core.level.block[x][y - 4].id = Tile.air;
		Core.level.block[x + 1][y - 4].id = Tile.oakWoodSlab;
		Core.level.block[x + 2][y - 4].id = Tile.sandstone;
		Core.level.block[x + 3][y - 4].id = Tile.air;
		Core.level.block[x + 4][y - 4].id = Tile.books;
		Core.level.block[x + 5][y - 4].id = Tile.books;
		Core.level.block[x + 6][y - 4].id = Tile.air;
		Core.level.block[x + 7][y - 4].id = Tile.sandstone;
		Core.level.block[x + 8][y - 4].id = Tile.oakWoodSlab;
		Core.level.block[x + 9][y - 4].id = Tile.air;

		Core.level.block[x][y - 5].id = Tile.air;
		Core.level.block[x + 1][y - 5].id = Tile.air;
		Core.level.block[x + 2][y - 5].id = Tile.oakWoodSlab;
		Core.level.block[x + 3][y - 5].id = Tile.sandstone;
		Core.level.block[x + 4][y - 5].id = Tile.sandstone;
		Core.level.block[x + 5][y - 5].id = Tile.sandstone;
		Core.level.block[x + 6][y - 5].id = Tile.sandstone;
		Core.level.block[x + 7][y - 5].id = Tile.oakWoodSlab;
		Core.level.block[x + 8][y - 5].id = Tile.air;
		Core.level.block[x + 9][y - 5].id = Tile.air;

		return 10;
	}

	@SuppressWarnings("static-access")
	public int generateWell(int x, int y, int[] mainTile, int[] slabTile) {
		Core.level.block[x + 1][y].id = mainTile;
		Core.level.block[x + 2][y].id = Tile.water;
		Core.level.block[x + 3][y].id = mainTile;

		Core.level.block[x + 1][y + 1].id = mainTile;
		Core.level.block[x + 1][y + 2].id = mainTile;
		Core.level.block[x + 1][y + 3].id = mainTile;
		Core.level.block[x + 1][y + 4].id = mainTile;
		Core.level.block[x + 1][y + 5].id = mainTile;
		Core.level.block[x + 2][y + 1].id = Tile.water;
		Core.level.block[x + 2][y + 2].id = Tile.water;
		Core.level.block[x + 2][y + 3].id = Tile.water;
		Core.level.block[x + 2][y + 4].id = Tile.water;
		Core.level.block[x + 2][y + 5].id = Tile.water;
		Core.level.block[x + 3][y + 1].id = mainTile;
		Core.level.block[x + 3][y + 2].id = mainTile;
		Core.level.block[x + 3][y + 3].id = mainTile;
		Core.level.block[x + 3][y + 4].id = mainTile;
		Core.level.block[x + 3][y + 5].id = mainTile;
		Core.level.block[x + 1][y + 6].id = mainTile;
		Core.level.block[x + 2][y + 6].id = mainTile;
		Core.level.block[x + 3][y + 6].id = mainTile;

		Core.level.block[x + 1][y - 1].id = Tile.woodenPole;
		Core.level.block[x + 2][y - 1].id = Tile.air;
		Core.level.block[x + 3][y - 1].id = Tile.woodenPole;

		Core.level.block[x + 1][y - 2].id = Tile.woodenPole;
		Core.level.block[x + 2][y - 2].id = Tile.woodenPoleWithBlockOnTop;
		Core.level.block[x + 2][y - 2].rotation = Rotation.ROTATE180;
		Core.level.block[x + 3][y - 2].id = Tile.woodenPole;

		Core.level.block[x + 1][y - 3].id = Tile.woodenPole;
		Core.level.block[x + 2][y - 3].id = Tile.woodenPole;
		Core.level.block[x + 3][y - 3].id = Tile.woodenPole;

		Core.level.block[x + 1][y - 4].id = slabTile;
		// TODO NEEDS TO BE A DOUBLE SLAB
		Core.level.block[x + 2][y - 4].id = slabTile;
		Core.level.block[x + 3][y - 4].id = slabTile;

		return 3;
	}

	@SuppressWarnings("static-access")
	public int generateGraves(int x, int y, int numberOfGraves) {
		for (int i = 0; i < numberOfGraves; i++) {
			Core.level.block[x + i][y].id = Tile.graveStone;
			for (int y2 = y; y2 > 0; y2--) {
				if (Core.level.block[x][y2].id != Tile.graveStone) {
					Core.level.block[x + i][y2].id = Tile.air;
				}
			}
		}
		return numberOfGraves;
	}

	@SuppressWarnings("static-access")
	public int generateChurch(int x, int y, int[] stairTile, int[] slabTile) {
		Core.level.block[x][y].id = stairTile;
		Core.level.block[x + 1][y].id = Tile.smoothStoneWallChiseledLeft;
		Core.level.block[x + 2][y].id = Tile.smoothStoneWallChiseledRight;
		Core.level.block[x + 3][y].id = Tile.smoothStoneWallChiseledLeft;
		Core.level.block[x + 4][y].id = Tile.smoothStoneWallChiseledRight;
		Core.level.block[x + 5][y].id = Tile.smoothStoneWallChiseledLeft;
		Core.level.block[x + 6][y].id = Tile.smoothStoneWallChiseledRight;
		Core.level.block[x + 7][y].id = stairTile;
		// TODO FIX THIS ROTATION, needs to be flipped horizontally
		Core.level.block[x + 7][y].rotation = Rotation.ROTATE180;

		Core.level.block[x][y - 1].id = Tile.air;
		Core.level.block[x + 1][y - 1].id = Tile.door;
		Core.level.block[x + 2][y - 1].id = Tile.air;
		// TODO LADDER
		Core.level.block[x + 3][y - 1].id = Tile.air;
		Core.level.block[x + 4][y - 1].id = Tile.chest;
		Core.level.block[x + 5][y - 1].id = Tile.air;
		Core.level.block[x + 6][y - 1].id = Tile.door;
		Core.level.block[x + 7][y - 1].id = Tile.air;

		Core.level.block[x][y - 2].id = Tile.air;
		Core.level.block[x + 1][y - 2].id = Tile.door;
		Core.level.block[x + 2][y - 2].id = Tile.air;
		// TODO LADDER
		Core.level.block[x + 3][y - 2].id = Tile.air;
		// TODO LIGHTER WOOD SLAB
		Core.level.block[x + 4][y - 2].id = Tile.oakWoodSlab;
		Core.level.block[x + 5][y - 2].id = Tile.air;
		Core.level.block[x + 6][y - 2].id = Tile.door;
		Core.level.block[x + 7][y - 2].id = Tile.air;

		Core.level.block[x][y - 3].id = Tile.air;
		Core.level.block[x + 1][y - 3].id = Tile.smoothStoneWallChiseledBottom;
		Core.level.block[x + 2][y - 3].id = Tile.air;
		// TODO LADDER
		Core.level.block[x + 3][y - 3].id = Tile.air;
		// TODO LIGHTER WOOD SLAB
		Core.level.block[x + 4][y - 3].id = Tile.oakWoodSlab;
		Core.level.block[x + 4][y - 3].rotation = Rotation.ROTATE180;
		Core.level.block[x + 5][y - 3].id = Tile.air;
		Core.level.block[x + 6][y - 3].id = Tile.smoothStoneWallChiseledBottom;
		Core.level.block[x + 7][y - 3].id = Tile.air;

		Core.level.block[x][y - 4].id = Tile.air;
		Core.level.block[x + 1][y - 4].id = Tile.smoothStoneWallChiseledMiddle;
		Core.level.block[x + 1][y - 4].rotation = Rotation.ROTATE90;
		Core.level.block[x + 2][y - 4].id = Tile.air;
		// TODO LADDER
		Core.level.block[x + 3][y - 4].id = Tile.air;
		Core.level.block[x + 4][y - 4].id = Tile.chest;
		Core.level.block[x + 5][y - 4].id = Tile.air;
		Core.level.block[x + 6][y - 4].id = Tile.smoothStoneWallChiseledMiddle;
		Core.level.block[x + 6][y - 4].rotation = Rotation.ROTATE90;
		Core.level.block[x + 7][y - 4].id = Tile.air;

		// TODO LIGHTER WOOD SLAB
		Core.level.block[x][y - 5].id = Tile.oakWoodSlab;
		Core.level.block[x][y - 5].rotation = Rotation.ROTATE180;
		Core.level.block[x + 1][y - 5].id = Tile.smoothStoneWallChiseledTop;
		// TODO LIGHTER WOOD SLAB
		Core.level.block[x + 2][y - 5].id = Tile.oakWoodSlab;
		Core.level.block[x + 2][y - 5].rotation = Rotation.ROTATE180;
		// TODO LADDER
		Core.level.block[x + 3][y - 5].id = Tile.air;
		Core.level.block[x + 4][y - 5].id = Tile.oakWoodSlab;
		Core.level.block[x + 4][y - 5].rotation = Rotation.ROTATE180;
		Core.level.block[x + 5][y - 5].id = Tile.oakWoodSlab;
		Core.level.block[x + 5][y - 5].rotation = Rotation.ROTATE180;
		Core.level.block[x + 6][y - 5].id = Tile.smoothStoneWallChiseledTop;
		Core.level.block[x + 7][y - 5].id = Tile.oakWoodSlab;
		Core.level.block[x + 7][y - 5].rotation = Rotation.ROTATE180;

		Core.level.block[x][y - 6].id = Tile.chest;
		Core.level.block[x + 1][y - 6].id = Tile.air;
		Core.level.block[x + 2][y - 6].id = Tile.door;
		Core.level.block[x + 3][y - 6].id = Tile.air;
		Core.level.block[x + 4][y - 6].id = Tile.air;
		Core.level.block[x + 5][y - 6].id = Tile.door;
		Core.level.block[x + 6][y - 6].id = Tile.air;
		Core.level.block[x + 7][y - 6].id = Tile.air;

		Core.level.block[x][y - 7].id = Tile.air;
		Core.level.block[x + 1][y - 7].id = Tile.air;
		Core.level.block[x + 2][y - 7].id = Tile.door;
		Core.level.block[x + 3][y - 7].id = Tile.air;
		Core.level.block[x + 4][y - 7].id = Tile.air;
		Core.level.block[x + 5][y - 7].id = Tile.door;
		Core.level.block[x + 6][y - 7].id = Tile.air;
		Core.level.block[x + 7][y - 7].id = Tile.air;

		Core.level.block[x][y - 8].id = Tile.air;
		Core.level.block[x + 1][y - 8].id = Tile.air;
		Core.level.block[x + 2][y - 8].id = Tile.smoothStoneWallChiseledTop;
		Core.level.block[x + 3][y - 8].id = Tile.air;
		Core.level.block[x + 4][y - 8].id = Tile.air;
		Core.level.block[x + 5][y - 8].id = Tile.smoothStoneWallChiseledTop;
		Core.level.block[x + 6][y - 8].id = Tile.air;
		Core.level.block[x + 7][y - 8].id = Tile.air;

		Core.level.block[x][y - 9].id = Tile.air;
		Core.level.block[x + 1][y - 9].id = Tile.air;
		Core.level.block[x + 2][y - 9].id = Tile.air;
		Core.level.block[x + 3][y - 9].id = Tile.smoothStoneWallChiseledLeft;
		Core.level.block[x + 4][y - 9].id = Tile.smoothStoneWallChiseledRight;
		Core.level.block[x + 5][y - 9].id = Tile.air;
		Core.level.block[x + 6][y - 9].id = Tile.air;
		Core.level.block[x + 7][y - 9].id = Tile.air;

		return 8;
	}

	@SuppressWarnings("static-access")
	public int generateBlackSmith(int x, int y, int[] mainTile, int[] slabTile) {
		Core.level.block[x][y].id = Tile.sandStoneSlab;
		Core.level.block[x + 1][y].id = Tile.sandstone;
		Core.level.block[x + 2][y].id = Tile.sandstone;
		Core.level.block[x + 3][y].id = Tile.sandstone;
		Core.level.block[x + 4][y].id = Tile.sandstone;
		Core.level.block[x + 5][y].id = Tile.sandstone;
		Core.level.block[x + 6][y].id = Tile.sandstone;
		Core.level.block[x + 7][y].id = Tile.sandStoneSlab;

		Core.level.block[x][y - 1].id = Tile.air;
		Core.level.block[x + 1][y - 1].id = Tile.stone;
		Core.level.block[x + 2][y - 1].id = Tile.stone;
		Core.level.block[x + 3][y - 1].id = Tile.stone;
		Core.level.block[x + 4][y - 1].id = Tile.furnaceOff;
		Core.level.block[x + 5][y - 1].id = Tile.air;
		Core.level.block[x + 6][y - 1].id = Tile.door;
		Core.level.block[x + 7][y - 1].id = Tile.air;

		Core.level.block[x][y - 2].id = Tile.air;
		Core.level.block[x + 1][y - 2].id = Tile.woodenPole;
		Core.level.block[x + 2][y - 2].id = Tile.air;
		Core.level.block[x + 3][y - 2].id = Tile.stone;
		Core.level.block[x + 4][y - 2].id = Tile.craftingTable;
		Core.level.block[x + 5][y - 2].id = Tile.air;
		Core.level.block[x + 6][y - 2].id = Tile.door;
		Core.level.block[x + 7][y - 2].id = Tile.air;

		Core.level.block[x][y - 3].id = Tile.air;
		Core.level.block[x + 1][y - 3].id = Tile.woodenPole;
		Core.level.block[x + 2][y - 3].id = Tile.air;
		Core.level.block[x + 3][y - 3].id = Tile.air;
		Core.level.block[x + 4][y - 3].id = Tile.air;
		Core.level.block[x + 5][y - 3].id = Tile.air;
		Core.level.block[x + 6][y - 3].id = Tile.sandstone;
		Core.level.block[x + 7][y - 3].id = Tile.air;

		Core.level.block[x][y - 4].id = Tile.air;
		Core.level.block[x + 1][y - 4].id = Tile.oakWoodSlab;
		Core.level.block[x + 2][y - 4].id = Tile.oakWoodSlab;
		Core.level.block[x + 3][y - 4].id = Tile.sandstone;
		Core.level.block[x + 4][y - 4].id = Tile.sandstone;
		Core.level.block[x + 5][y - 4].id = Tile.sandstone;
		Core.level.block[x + 6][y - 4].id = Tile.oakWoodSlab;
		Core.level.block[x + 7][y - 4].id = Tile.air;

		return 8;
	}

	@SuppressWarnings("static-access")
	public void flattenLand(int x, int y, int endX, int[] mainTile) {
		for (int x2 = x; x2 < endX; x2++) {
			for (int y2 = y + 1; y2 < 25; y2++) {
				Core.level.block[x2][y2].id = mainTile;
			}
		}
	}

}
