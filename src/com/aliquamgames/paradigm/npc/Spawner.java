package com.aliquamgames.paradigm.npc;

import java.util.Random;

import com.aliquamgames.paradigm.Core;
import com.aliquamgames.paradigm.playing.Tile;

public class Spawner {
	// this is the number of mob classes we have, its used in the random to determine what kind of mob to spawn
	private int numberOfMobClasses = 2;
	// the is the number of mobs currently in the game
	private int numberOfCurrentMobs = 0;
	// this is the max amount of mobs on the screen
	private int maxAmountOfMobs = 5;

	// this is the current time of the system in milliseconds
	private long currentTime = System.currentTimeMillis();
	// this is the past time of the system in milliseconds
	private long pastTime = currentTime;
	// this is the amount of time between mob spawns in milliseconds
	private int spawnTime = 10000;

	public void addMob(Mob mob) {
		EntityTicker.npcs.add(mob);
		numberOfCurrentMobs++;
	}

	public void removeMob(Mob mob) {
		EntityTicker.npcs.remove(mob);
		numberOfCurrentMobs--;
	}

	public void removeMob(int index) {
		EntityTicker.npcs.remove(index);
		numberOfCurrentMobs--;
	}

	int ranX;
	int count = 0;
	// int y = (count / 20) + (12 * 5) - (4 * 10 + 10) + (5 * 2 + 3) * 20;// more completely useless spawn math but works
	int y = 0;

	@SuppressWarnings("static-access")
	public void tick() {
		ranX = new Random().nextInt(((Core.level.getIndexOfBiomePlayerIsIn() + 1) * Tile.tileSize * Core.level.CHUNKW) - (Core.level.getIndexOfBiomePlayerIsIn() * Tile.tileSize * Core.level.CHUNKW));
		if (numberOfCurrentMobs <= 0) {
			numberOfCurrentMobs = 0; // good one XD
		}
		currentTime = System.currentTimeMillis();
		if (numberOfCurrentMobs < maxAmountOfMobs) {
			if (currentTime > pastTime + spawnTime) {
				pastTime = System.currentTimeMillis();
				spawnTime = (new Random().nextInt(1) + 1) * 1000;
				int rand = new Random().nextInt(numberOfMobClasses);
				switch (rand) {
				case 0:
					addMob(new Zombie(ranX, y));
					addMob(new Skeleton(ranX, y));
					break;
				case 1:
					break;
				case 2:
					break;
				case 3:
					break;
				}
			}
		}
	}
}
