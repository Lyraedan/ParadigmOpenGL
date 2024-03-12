package com.aliquamgames.paradigm.level;

import java.util.ArrayList;
import java.util.Random;

import org.lwjgl.Sys;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Point;
import org.lwjgl.util.Rectangle;
import org.newdawn.slick.Color;

import com.aliquamgames.paradigm.Core;
import com.aliquamgames.paradigm.blocks.Block;
import com.aliquamgames.paradigm.blocks.Block.Rotation;
import com.aliquamgames.paradigm.blocks.BlockDrop;
import com.aliquamgames.paradigm.crafting.Crafting;
import com.aliquamgames.paradigm.inventory.Chest;
import com.aliquamgames.paradigm.inventory.Inventory;
import com.aliquamgames.paradigm.npc.EntityTicker;
import com.aliquamgames.paradigm.playing.Particle;
import com.aliquamgames.paradigm.playing.Tile;
import com.aliquamgames.paradigm.util.Util;
import com.aliquamgames.paradigm.weather.Weather;

public class InfiniteLevel implements Runnable {

	// this is the size of the block drops
	public int blockDropSize = 20;

	// this is the beginning world width, changes as the level expands
	public static int worldW = 45;
	// the beginning world height
	public static int worldH = 250;// 250
	// this is the default world width, what is starts out as, does not change
	public final int DEFAULTWORLDW = worldW;
	// this is the default world height, what it starts out as, does not change
	public final int DEFAULTWORLDH = worldH;
	// this is the chunk width, how many blocks are added on the x when the world is expanding
	public static final int CHUNKW = 45;
	// this is the chunk height, how many blocks are added on the y when the world is expanding
	public final int CHUNKH = worldH;
	// TODO IDK WHAT THIS DOES
	int biomes;

	// this is the width of the block array list
	public static int blockArrayWidth = (int) (Math.floor(50000 / CHUNKW) * CHUNKW);
	// this is the number of chunks that can be made
	// the width is the integer of 50000/CHUNKW (45) = 1111
	public static int maxChunks = blockArrayWidth / CHUNKW;
	// this is the number of generated chunks
	public int generatedChunks = 0;

	// the current time of the System
	public long currentTime = getTime();
	public String direction = "right";

	// this is the multidimensional block array that contains the blocks at each position
	public static Block[][] block = new Block[blockArrayWidth][worldH];
	// public static BlockOverLayer[][] blockOverLayer = new BlockOverLayer[blockArrayWidth][worldH];
	// public static Particle[][] particle = new Particle[blockArrayWidth][worldH];

	public ArrayList<BlockDrop> blockDrops = new ArrayList<BlockDrop>();
	public ArrayList<Particle> particles = new ArrayList<Particle>();

	// this is the Biome enum, controls what biome it is in
	public static enum BiomeEnum {
		PLAINS, MOUNTAINS, SNOW, DESERT, FOREST, TAINT, JUNGLE, OCEAN
	}

	// the Biome enum instance
	public static BiomeEnum biome = BiomeEnum.PLAINS;
	// the BiomeEnum array that contains the biome creations
	public static BiomeEnum[] biomeArray = new BiomeEnum[maxChunks];
	// the Chunk array that contains the chunks
	public static Chunk[] chunks = new Chunk[maxChunks];
	// the randomizer for the biomes
	public Random biomeChecker = new Random();

	public Thread generationThread = new Thread(this, "Generation");

	// the arraylist containing all the chests that are placed down
	public ArrayList<Chest> chests = new ArrayList<Chest>();
	public boolean chestOpen = false;

	public InfiniteLevel() {
		GL11.glEnable(GL11.GL_COLOR_MATERIAL);
		changeBiome();
		for (int x = 0; x < DEFAULTWORLDW; x++) {
			for (int y = 0; y < worldH; y++) {
				block[x][y] = new Block(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.air, 0, x, y, "Air");
			}
		}

		/** DO NOT CHANGE FROM LEFT */
		createBlocks(0, DEFAULTWORLDW);
		changeBiomeArray("left");
		changeChunkArray("left");
		generate(0, DEFAULTWORLDW);

		for (int i = 0; i < 5; i++) {
			createLevel("right");
		}

		for (int x = 0; x < worldW; x++) {
			for (int y = 0; y < worldH; y++) {
				Core.physics.checkBooleans(block[x][y].id);
			}
		}
	}

	public void generate(int startX, int endX) {
		if (startX == 0) {
			chunks[0].generate(startX, endX, CHUNKH);
		} else {
			chunks[generatedChunks].generate(startX, endX, CHUNKH);
		}

		for (int x = 0; x < worldW; x++) {
			for (int y = 0; y < worldH; y++) {
				setLightOnBlock(x, y);
			}
		}
		// plonk bedrock down
		for (int x = startX; x < endX; x++) {
			block[x][worldH - 1].id = Tile.bedrock;
		}

		generatedChunks++;
	}

	public void tick(int camX, int camY, int renW, int renH) {
		currentTime = getTime();
		for (int x = (camX / Tile.tileSize); x < (camX / Tile.tileSize) + renW; x++) {
			for (int y = (camY / Tile.tileSize); y < (camY / Tile.tileSize) + renH; y++) {
				if (x >= 0 && x < worldW && y >= 0 && y < worldH) {
					block[x][y].tick();
					if (block[x][y].darkness < 100) block[x][y].discovered = true;
					if (block[x][y].remove) block[x][y] = new Block(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.air, 0, x, y, "Air");
				}
			}
		}

		for (int i = 0; i < blockDrops.toArray().length; i++) {
			blockDrops.get(i).tick(currentTime);
			if (blockDrops.get(i).rect.intersects(Core.player.rect)) {
				int amount = addItem(blockDrops.get(i).id, blockDrops.get(i).amount, blockDrops.get(i).name);
				if (amount <= 0) {
					blockDrops.get(i).remove = true;
				} else {
					blockDrops.get(i).amount = amount;
				}
			}

			if (blockDrops.get(i).remove) {
				blockDrops.remove(i);
				break;
			}

			for (int b = 0; b < blockDrops.toArray().length; b++) {
				if (b != i) {
					if (blockDrops.get(i).id == blockDrops.get(b).id) {
						if (blockDrops.get(i).rect.intersects(blockDrops.get(b).rect)) {
							blockDrops.get(i).amount += blockDrops.get(b).amount;
							blockDrops.remove(b);
							break;
						}
					}
				}
			}
		}

		for (int i = 0; i < particles.toArray().length; i++) {
			particles.get(i).tick(currentTime);
			if (particles.get(i).remove) {
				particles.remove(i);
				break;
			}

			for (int p = 0; p < particles.toArray().length; p++) {
				if (p != i) {
					if (particles.get(i).id == particles.get(p).id) {
						if (particles.get(i).removeTime > particles.get(p).removedTime) {
							particles.remove(p);
							break;
						}
					}
				}
			}
		}

		if (!Inventory.isOpen && !Crafting.isOpen && !chestOpen) {
			building(camX, camY, renW, renH);
		}

		for (int i = getIndexOfBiomePlayerIsIn() - 1; i < getIndexOfBiomePlayerIsIn() + 2; i++) {
			if (i < 0) continue;
			if (i >= generatedChunks) break;
			chunks[i].tick(camX, camY, renW, renH);
		}

		createInfiniteTerrainAsNeeded(camX, camY);
		// this controls the weather
		Weather.controlWeather();
	}

	// private int radius = 96;

	public void render(int camX, int camY, int renW, int renH) {
		for (int i = 0; i < blockDrops.toArray().length; i++) {
			blockDrops.get(i).render(camX, camY);
		}

		for (int i = 0; i < particles.toArray().length; i++) {
			particles.get(i).render();
		}

		for (int i = getIndexOfBiomePlayerIsIn() - 1; i < getIndexOfBiomePlayerIsIn() + 2; i++) {
			if (i < 0) continue;
			if (i >= generatedChunks) break;
			chunks[i].render(camX, camY);
		}

		for (int x = (camX / Tile.tileSize); x < (camX / Tile.tileSize) + renW; x++) {
			for (int y = (camY / Tile.tileSize); y < (camY / Tile.tileSize) + renH; y++) {
				if (x >= 0 && x < worldW && y >= 0 && y < worldH) {
					block[x][y].render();
					// light(x, y, camX, camY);

					// if (block[x][y].rect.getX() - camX > Core.player.getXPosition() - camX - radius && block[x][y].rect.getX() - camX < Core.player.getXPosition() - camX + radius && block[x][y].rect.getY() - camY > Core.player.getYPosition() -
					// camY - radius && block[x][y].rect.getY() - camY < Core.player.getYPosition() - camY + radius) {
					// block[x][y].darkness -= 150;
					// if (block[x][y].darkness <= 0) block[x][y].darkness = 0;
					// } else {
					// setLightOnBlock(x, y);
					// }
				}
			}
		}
	}

	public void building(int camX, int camY, int renW, int renH) {
		if (Core.isMouseLeft || Core.isMouseRight) {
			for (int x = (camX / Tile.tileSize); x < (camX / Tile.tileSize) + renW; x++) {
				for (int y = (camY / Tile.tileSize); y < (camY / Tile.tileSize) + renH; y++) {
					if (x - 1 >= 0 && y - 1 >= 0 && x + 1 < worldW && y + 1 < worldH) { // <----- causes interferance
						if (block[x][y].rect.contains(new Point((Core.mouseX) + (int) Core.sX, (Core.mouseY) + (int) Core.sY))) {
							if (Core.isMouseLeft) {
								if (isBreakable(x, y)) {
									if (block[x][y].id != Tile.tnt) {
										blockDrops.add(new BlockDrop(new Rectangle(x * Tile.tileSize + ((Tile.tileSize - blockDropSize) / 2), y * Tile.tileSize + ((Tile.tileSize - blockDropSize) / 2), blockDropSize, blockDropSize), block[x][y].id, block[x][y].name, currentTime));
										if (x - 1 >= 0 && y - 1 >= 0 && x + 1 < worldW && y + 1 < worldH) {
											// particles.add(new Particle(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, 2, 2), block[x][y].id));
										} else {
											particles.clear();
										}
										Core.player.miningBlocks++;
										block[x][y].id = Tile.air;
										boolean discovered = block[x][y].discovered;
										block[x][y] = new Block(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.air, 0, x, y, "Air");
										block[x][y].discovered = discovered;
									} else {
										block[x][y].tntTriggered = true;
									}
								}
							} else if (Core.isMouseRight) {
								int sid[] = Inventory.invBar[Inventory.selected].id;

								if (Inventory.invBar[Inventory.selected].stack <= 0) {
									sid = Tile.air;
									Inventory.invBar[Inventory.selected].id = Tile.air;
									Inventory.invBar[Inventory.selected].stack = 0;
								}
								if (x >= 0 && x < worldW && y >= 0 && y < worldH) {
									if (block[x][y].id == Tile.chest) {
										for (int i = 0; i < chests.toArray().length; i++) {
											if (chests.get(i).spotX == x && chests.get(i).spotY == y) {
												chests.get(i).isOpen = true;
												chestOpen = true;
											}
										}
									} else if (canPlaceHere(x, y)) {
										try {
											if (block[x][y].id != Tile.air || block[x - 1][y].id != Tile.air || block[x + 1][y].id != Tile.air || block[x][y - 1].id != Tile.air || block[x][y + 1].id != Tile.air) {
												if (sid != Tile.air) {
													Inventory.invBar[Inventory.selected].stack -= 1;
													block[x][y].id = sid;
													boolean discovered = block[x][y].discovered;
													Core.block.syncBlockClasses(block[x][y].id, x, y);
													if (sid == Tile.chest) {
														chests.add(new Chest(x, y));
													}
													block[x][y].discovered = discovered;
													Core.physics.checkBooleans(block[x][y].id);
													// Core.physics.particals(x, y);
												}
											}

										} catch (ArrayIndexOutOfBoundsException e) {
											// e.printStackTrace();
										}
									}
								}
								if (block[x][y].id != Tile.air && block[x][y + 1].id == Tile.dirtWithGrass) {
									block[x][y + 1].id = Tile.dirt;
								}
							}
						}
					}
				}
			}
		}
	}

	/**
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */

	private void createInfiniteTerrainAsNeeded(int camX, int camY) {
		if (Core.player.getXPosition() < 15 * Tile.tileSize) {
			if (worldW + CHUNKW <= blockArrayWidth) {
				for (int x = worldW; x < worldW + CHUNKW; x++) {
					for (int y = 0; y < CHUNKH; y++) {
						block[x][y] = new Block(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.air, 0, x, y, "Air");
					}
				}

				for (int x = 0; x < worldW; x++) {
					for (int y = 0; y < worldH; y++) {
						block[x][y].spotX += CHUNKW;
					}
				}

				// change blocks at the positions
				for (int x = worldW - 1; x >= 0; x--) {
					for (int y = 0; y < worldH; y++) {
						block[x + CHUNKW][y].id = block[x][y].id;
						block[x + CHUNKW][y].discovered = block[x][y].discovered;
						block[x + CHUNKW][y].rotation = block[x][y].rotation;
						block[x + CHUNKW][y].name = block[x][y].name;

						block[x][y].id = Tile.air;
						block[x][y].discovered = false;
						block[x][y].rotation = Rotation.NONE;
						block[x][y].name = "Air";

					}
				}

				// change positions of objects
				Core.sX += (Tile.tileSize * CHUNKW);
				Core.player.x += (Tile.tileSize * CHUNKW);
				for (int i = 0; i < blockDrops.toArray().length; i++) {
					blockDrops.get(i).rect.setX(blockDrops.get(i).rect.getX() + (Tile.tileSize * CHUNKW));
				}
				for (int i = 0; i < EntityTicker.npcs.toArray().length; i++) {
					EntityTicker.npcs.get(i).x += (Tile.tileSize * CHUNKW);
				}
			}
			createLevel("left");
		}

		if (Core.player.getXPosition() > worldW * Tile.tileSize - 15 * Tile.tileSize) {
			createLevel("right");
		}
	}

	public void createBlocks(int startX, int endX) {
		for (int x = startX; x < endX; x++) {
			for (int y = 0; y < worldH; y++) {
				block[x][y] = new Block(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.air, 0, x, y, "Air");
			}
		}
	}

	private synchronized void createLevel(final String direction) {
		changeBiome();
		this.direction = direction;
		generationThread.run();
		try {
			generationThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void changeChunkArray(String side) {
		if (side == "left") {
			for (int i = generatedChunks - 1; i >= 0; i--) {
				if (i + 1 < maxChunks) chunks[i + 1] = chunks[i];
			}
			switch (biome) {
			case PLAINS:
				chunks[0] = new Chunk(new PlainsBiome(), 0, CHUNKW, CHUNKH);
				break;
			case FOREST:
				chunks[0] = new Chunk(new ForestBiome(), 0, CHUNKW, CHUNKH);
				break;
			case DESERT:
				chunks[0] = new Chunk(new DesertBiome(), 0, CHUNKW, CHUNKH);
				break;
			case MOUNTAINS:
				chunks[0] = new Chunk(new MountainBiome(), 0, CHUNKW, CHUNKH);
				break;
			case SNOW:
				chunks[0] = new Chunk(new SnowBiome(), 0, CHUNKW, CHUNKH);
				break;
			case TAINT:
				chunks[0] = new Chunk(new TaintBiome(), 0, CHUNKW, CHUNKH);
				break;
			case JUNGLE:
				chunks[0] = new Chunk(new JungleBiome(), 0, CHUNKW, CHUNKH);
				break;
			case OCEAN:
				chunks[0] = new Chunk(new OceanBiome(), 0, CHUNKW, CHUNKH);
				break;
			}
		} else {
			switch (biome) {
			case PLAINS:
				chunks[generatedChunks] = new Chunk(new PlainsBiome(), worldW - CHUNKW, worldW, CHUNKH);
				break;
			case FOREST:
				chunks[generatedChunks] = new Chunk(new ForestBiome(), worldW - CHUNKW, worldW, CHUNKH);
				break;
			case DESERT:
				chunks[generatedChunks] = new Chunk(new DesertBiome(), worldW - CHUNKW, worldW, CHUNKH);
				break;
			case MOUNTAINS:
				chunks[generatedChunks] = new Chunk(new MountainBiome(), worldW - CHUNKW, worldW, CHUNKH);
				break;
			case SNOW:
				chunks[generatedChunks] = new Chunk(new SnowBiome(), worldW - CHUNKW, worldW, CHUNKH);
				break;
			case TAINT:
				chunks[generatedChunks] = new Chunk(new TaintBiome(), worldW - CHUNKW, worldW, CHUNKH);
				break;
			case JUNGLE:
				chunks[generatedChunks] = new Chunk(new JungleBiome(), worldW - CHUNKW, worldW, CHUNKH);
				break;
			case OCEAN:
				chunks[generatedChunks] = new Chunk(new OceanBiome(), worldW - CHUNKW, worldW, CHUNKH);
				break;
			}
		}
	}

	public void changeBiomeArray(String side) {
		System.out.println("GENERATED CHUNKS: " + generatedChunks);
		if (biome.equals(BiomeEnum.FOREST)) {
			if (side == "left") {
				for (int i = generatedChunks - 1; i >= 0; i--) {
					if (i + 1 < maxChunks) biomeArray[i + 1] = biomeArray[i];
				}
				biomeArray[0] = BiomeEnum.FOREST;
			} else {
				biomeArray[generatedChunks - 1] = BiomeEnum.FOREST;
			}
		} else if (biome.equals(BiomeEnum.DESERT)) {
			if (side == "left") {
				for (int i = generatedChunks - 1; i >= 0; i--) {
					if (i + 1 < biomeArray.length) biomeArray[i + 1] = biomeArray[i];
				}
				biomeArray[0] = BiomeEnum.DESERT;
			} else {
				biomeArray[generatedChunks - 1] = BiomeEnum.DESERT;
			}
		} else if (biome.equals(BiomeEnum.MOUNTAINS)) {
			if (side == "left") {
				for (int i = generatedChunks - 1; i >= 0; i--) {
					if (i + 1 < biomeArray.length) biomeArray[i + 1] = biomeArray[i];
				}
				biomeArray[0] = BiomeEnum.MOUNTAINS;
			} else {
				biomeArray[generatedChunks - 1] = BiomeEnum.MOUNTAINS;
			}
		} else if (biome.equals(BiomeEnum.PLAINS)) {
			if (side == "left") {
				for (int i = generatedChunks - 1; i >= 0; i--) {
					if (i + 1 < biomeArray.length) biomeArray[i + 1] = biomeArray[i];
				}
				biomeArray[0] = BiomeEnum.PLAINS;
			} else {
				biomeArray[generatedChunks - 1] = BiomeEnum.PLAINS;
			}
		} else if (biome.equals(BiomeEnum.SNOW)) {
			if (side == "left") {
				for (int i = generatedChunks - 1; i >= 0; i--) {
					if (i + 1 < biomeArray.length) biomeArray[i + 1] = biomeArray[i];
				}
				biomeArray[0] = BiomeEnum.SNOW;
			} else {
				biomeArray[generatedChunks - 1] = BiomeEnum.SNOW;
			}
		} else if (biome.equals(BiomeEnum.TAINT)) {
			if (side == "left") {
				for (int i = generatedChunks - 1; i >= 0; i--) {
					if (i + 1 < biomeArray.length) biomeArray[i + 1] = biomeArray[i];
				}
				biomeArray[0] = BiomeEnum.TAINT;
			} else {
				biomeArray[generatedChunks - 1] = BiomeEnum.TAINT;
			}
		} else if (biome.equals(BiomeEnum.JUNGLE)) {
			if (side == "left") {
				for (int i = generatedChunks - 1; i >= 0; i--) {
					if (i + 1 < biomeArray.length) biomeArray[i + 1] = biomeArray[i];
				}
				biomeArray[0] = BiomeEnum.JUNGLE;
			} else {
				biomeArray[generatedChunks - 1] = BiomeEnum.JUNGLE;
			}
		} else if (biome.equals(BiomeEnum.OCEAN)) {
			if (side == "left") {
				for (int i = generatedChunks - 1; i >= 0; i--) {
					if (i + 1 < biomeArray.length) biomeArray[i + 1] = biomeArray[i];
				}
				biomeArray[0] = BiomeEnum.OCEAN;
			} else {
				biomeArray[generatedChunks - 1] = BiomeEnum.OCEAN;
			}
		}
	}

	public void throwBlock(int[] id, String name) {
		if (Core.player.dir > 0) {
			blockDrops.add(new BlockDrop(new Rectangle((int) Core.player.x + Core.player.width + 10, (int) Core.player.y, blockDropSize, blockDropSize), id, name, currentTime));
		} else {
			blockDrops.add(new BlockDrop(new Rectangle((int) Core.player.x - blockDropSize - 10, (int) Core.player.y, blockDropSize, blockDropSize), id, name, currentTime));
		}
	}

	public int getBlocksAbove(int x, int y) {
		int count = 0;
		for (int u = 0; u < y; u++) {
			if (block[x][u].id != Tile.solidair && block[x][u].id != Tile.air && block[x][u].id != Tile.star) {
				count++;
			}
		}
		return count;
	}

	public void setLightOnBlock(int x, int y) {
		try {
			if (getBlocksAbove(x, y) == 1) {
				block[x][y].darkness = 60;
			} else {
				block[x][y].darkness = 60 + (getBlocksAbove(x, y) * 15);
				if (block[x][y].darkness >= 255) block[x][y].darkness = 255;
			}
		} catch (Exception e) {
			// e.printStackTrace();
		}
	}

	public long getTime() {
		return Sys.getTime() * 1000 / Sys.getTimerResolution();
	}

	/**
	 * @return the index of the biome they are in, in the biomeArray
	 */
	public int getIndexOfBiomePlayerIsIn() {
		return (int) ((Core.player.x / Tile.tileSize / CHUNKW));
	}

	/**
	 * @return gets the Biome of the biome that they are in
	 */
	public BiomeEnum getBiomePlayerIsIn() {
		return biomeArray[getIndexOfBiomePlayerIsIn()];
	}

	/**
	 * @return the index of the biome the block at x, y is in
	 */
	public int getIndexOfBiomeBlockIsIn(int x, int y) {
		return (int) (block[x][y].rect.getX() / Tile.tileSize / CHUNKW);
	}

	/**
	 * @return the biome the block at x, y is in
	 */

	public BiomeEnum getBiomeBlockIsIn(int x, int y) {
		return biomeArray[(int) (block[x][y].rect.getX() / Tile.tileSize / CHUNKW)];
	}

	public void changeBiome() {
		biomes = biomeChecker.nextInt(8);

		switch (biomes) {
		case 0:
			biome = BiomeEnum.SNOW;
			break;
		case 1:
			biome = BiomeEnum.MOUNTAINS;
			break;
		case 2:
			biome = BiomeEnum.PLAINS;
			break;
		case 3:
			biome = BiomeEnum.DESERT;
			break;
		case 4:
			biome = BiomeEnum.FOREST;
			break;
		case 5:
			biome = BiomeEnum.TAINT;
			break;
		case 6:
			biome = BiomeEnum.JUNGLE;
			break;
		case 7:
			biome = BiomeEnum.OCEAN;
			break;
		}
	}

	public void light(int x, int y, int camX, int camY) {
		Util.fillColoredRect(new Color(0, 0, 0, block[x][y].darkness), block[x][y].rect.getX() - camX, block[x][y].rect.getY() - camX, block[x][y].rect.getWidth(), block[x][y].rect.getHeight());
	}

	public boolean isBreakable(int x, int y) {
		if (block[x][y].id == Tile.solidair) return false;
		if (block[x][y].id == Tile.star) return false;
		if (block[x][y].id == Tile.air) return false;
		if (block[x][y].id == Tile.water) return false;
		if (block[x][y].id == Core.animation.smoke()) return false;
		if (block[x][y].id == Tile.bedrock) return false;

		return true;
	}

	public boolean canPlaceHere(int x, int y) {
		if (block[x][y].id == Tile.air) return true;
		for (int i = 0; i < Tile.fireAnimationSwap.length; i++) {
			if (block[x][y].id == Tile.fireAnimationSwap[i]) return true;
		}
		if (block[x][y].id == Tile.water) return true;

		return false;
	}

	public static int addItem(int[] tile, int stack, String name) {
		int amount = stack;
		for (int i = 0; i < Inventory.invBar.length; i++) {
			if (Inventory.invBar[i].id == tile && tile != Tile.air) {
				for (int s = amount; s > 0; s--) {
					if (Inventory.invBar[i].stack + s <= Inventory.maxStack) {
						Inventory.invBar[i].stack += s;
						amount -= s;
						Inventory.invBar[i].name = name;
						break;
					}
				}
			}
		}

		for (int i = 0; i < Inventory.invBar.length; i++) {
			if (Inventory.invBar[i].id == Tile.air) {
				for (int s = amount; s > 0; s--) {
					if (Inventory.invBar[i].stack + s <= Inventory.maxStack) {
						Inventory.invBar[i].id = tile;
						Inventory.invBar[i].stack += s;
						amount -= s;
						Inventory.invBar[i].name = name;
						break;
					}
				}
			}
		}

		for (int i = 0; i < Inventory.invBag.length; i++) {
			if (Inventory.invBag[i].id == tile && tile != Tile.air) {
				for (int s = amount; s > 0; s--) {
					if (Inventory.invBag[i].stack + s <= Inventory.maxStack) {
						Inventory.invBag[i].stack += s;
						amount -= s;
						Inventory.invBag[i].name = name;
						break;
					}
				}
			}
		}

		for (int i = 0; i < Inventory.invBag.length; i++) {
			if (Inventory.invBag[i].id == Tile.air) {
				for (int s = amount; s > 0; s--) {
					if (Inventory.invBag[i].stack + s <= Inventory.maxStack) {
						Inventory.invBag[i].id = tile;
						Inventory.invBag[i].stack += s;
						amount -= s;
						Inventory.invBag[i].name = name;
						break;
					}
				}
			}
		}
		return amount;
	}

	public void run() {
		if (worldW >= blockArrayWidth) {
			if (direction == "left") {
				editBlockArray("left");
				editChunkArray("left");
				editBiomeArray("left");
				editEntities("left");
			} else {
				editBlockArray("right");
				editChunkArray("right");
				editBiomeArray("right");
				editEntities("right");
			}
		} else {
			worldW += CHUNKW;
			if (direction == "left") {
				createBlocks(0, CHUNKW);
				changeChunkArray("left");
				generate(0, CHUNKW);
				changeBiomeArray("left");
			} else {
				createBlocks(worldW - CHUNKW, worldW);
				changeChunkArray("right");
				generate(worldW - CHUNKW, worldW);
				changeBiomeArray("right");
			}
		}
	}

	public void editEntities(String dir) {
		if (dir == "left") {
			Core.sX += (Tile.tileSize * (CHUNKW));
			Core.player.x += (Tile.tileSize * (CHUNKW));
			for (int i = 0; i < blockDrops.toArray().length; i++) {
				if (blockDrops.get(i).rect.getX() / Tile.tileSize / CHUNKW >= maxChunks - 1) {
					blockDrops.get(i).rect.setX(blockDrops.get(i).rect.getX() - ((maxChunks - 1) * Tile.tileSize * (CHUNKW)));
				} else {
					blockDrops.get(i).rect.setX(blockDrops.get(i).rect.getX() + (Tile.tileSize * (CHUNKW)));
				}
			}
			for (int i = 0; i < EntityTicker.npcs.toArray().length; i++) {
				if (EntityTicker.npcs.get(i).x / Tile.tileSize / CHUNKW >= maxChunks - 1) {
					EntityTicker.npcs.get(i).x -= ((maxChunks - 1) * Tile.tileSize * CHUNKW);
				} else {
					EntityTicker.npcs.get(i).x += (Tile.tileSize * (CHUNKW));
				}
			}
		} else {
			Core.sX -= (Tile.tileSize * CHUNKW);
			Core.player.x -= (Tile.tileSize * CHUNKW);
			for (int i = 0; i < blockDrops.toArray().length; i++) {
				if (blockDrops.get(i).rect.getX() / Tile.tileSize / CHUNKW < 1) {
					blockDrops.get(i).rect.setX(blockDrops.get(i).rect.getX() + ((maxChunks - 1) * Tile.tileSize * CHUNKW));
				} else {
					blockDrops.get(i).rect.setX(blockDrops.get(i).rect.getX() - (Tile.tileSize * CHUNKW));
				}
			}
			for (int i = 0; i < EntityTicker.npcs.toArray().length; i++) {
				if (EntityTicker.npcs.get(i).x / Tile.tileSize / CHUNKW < 1) {
					EntityTicker.npcs.get(i).x += ((maxChunks - 1) * Tile.tileSize * CHUNKW);
				} else {
					EntityTicker.npcs.get(i).x -= (Tile.tileSize * CHUNKW);
				}
			}
		}
	}

	public void editBlockArray(String dir) {
		Block[][] tempBlocks = new Block[CHUNKW][worldH];
		if (dir == "left") {
			for (int x = 0; x < tempBlocks.length; x++) {
				for (int y = 0; y < tempBlocks[0].length; y++) {
					tempBlocks[x][y] = new Block(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.air, 0, x, y, "Air");
				}
			}
			for (int x = blockArrayWidth - CHUNKW; x < blockArrayWidth; x++) {
				for (int y = 0; y < worldH; y++) {
					tempBlocks[x - (blockArrayWidth - CHUNKW)][y].id = block[x][y].id;
					tempBlocks[x - (blockArrayWidth - CHUNKW)][y].discovered = block[x][y].discovered;
					tempBlocks[x - (blockArrayWidth - CHUNKW)][y].name = block[x][y].name;
					tempBlocks[x - (blockArrayWidth - CHUNKW)][y].rotation = block[x][y].rotation;
				}
			}

			for (int x = blockArrayWidth - CHUNKW - 1; x >= 0; x--) {
				for (int y = 0; y < worldH; y++) {
					block[x + CHUNKW][y].id = block[x][y].id;
					block[x + CHUNKW][y].discovered = block[x][y].discovered;
					block[x + CHUNKW][y].name = block[x][y].name;
					block[x + CHUNKW][y].rotation = block[x][y].rotation;
				}
			}

			for (int x = 0; x < CHUNKW; x++) {
				for (int y = 0; y < worldH; y++) {
					block[x][y].id = tempBlocks[x][y].id;
					block[x][y].discovered = tempBlocks[x][y].discovered;
					block[x][y].name = tempBlocks[x][y].name;
					block[x][y].rotation = tempBlocks[x][y].rotation;

				}
			}
		} else {
			for (int x = 0; x < CHUNKW; x++) {
				for (int y = 0; y < worldH; y++) {
					tempBlocks[x][y] = new Block(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.air, 0, x, y, "Air");
					tempBlocks[x][y].id = block[x][y].id;
					tempBlocks[x][y].discovered = block[x][y].discovered;
					tempBlocks[x][y].name = block[x][y].name;
					tempBlocks[x][y].rotation = block[x][y].rotation;
				}
			}

			for (int x = CHUNKW; x < blockArrayWidth; x++) {
				for (int y = 0; y < worldH; y++) {
					block[x - CHUNKW][y].id = block[x][y].id;
					block[x - CHUNKW][y].discovered = block[x][y].discovered;
					block[x - CHUNKW][y].name = block[x][y].name;
					block[x - CHUNKW][y].rotation = block[x][y].rotation;

				}
			}

			for (int x = blockArrayWidth - CHUNKW; x < blockArrayWidth; x++) {
				for (int y = 0; y < worldH; y++) {
					block[x][y].id = tempBlocks[x - (blockArrayWidth - CHUNKW)][y].id;
					block[x][y].discovered = tempBlocks[x - (blockArrayWidth - CHUNKW)][y].discovered;
					block[x][y].name = tempBlocks[x - (blockArrayWidth - CHUNKW)][y].name;
					block[x][y].rotation = tempBlocks[x - blockArrayWidth - CHUNKW][y].rotation;
				}
			}
		}
	}

	public void editChunkArray(String dir) {
		Chunk tempChunk = null;
		if (dir == "left") {
			tempChunk = chunks[chunks.length - 1];
			for (int i = chunks.length - 2; i >= 0; i--) {
				chunks[i + 1] = chunks[i];
			}
			chunks[0] = tempChunk;
		} else {
			tempChunk = chunks[0];
			for (int i = 1; i < chunks.length - 1; i++) {
				chunks[i] = chunks[i + 1];
			}
			chunks[chunks.length - 1] = tempChunk;
		}
	}

	public void editBiomeArray(String dir) {
		BiomeEnum tempBiome = null;
		if (dir == "left") {
			tempBiome = biomeArray[biomeArray.length - 1];
			for (int i = biomeArray.length - 2; i >= 0; i--) {
				biomeArray[i + 1] = biomeArray[i];
			}
			biomeArray[0] = tempBiome;
		} else {
			tempBiome = biomeArray[0];
			for (int i = 0; i < biomeArray.length - 1; i++) {
				biomeArray[i] = biomeArray[i + 1];
			}
			biomeArray[biomeArray.length - 1] = tempBiome;
		}
	}
}
