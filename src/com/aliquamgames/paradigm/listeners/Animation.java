package com.aliquamgames.paradigm.listeners;

import com.aliquamgames.paradigm.playing.Tile;

/**
 * 
 * @author Luke Rapkin
 * 
 */

public class Animation {
	
	// TODO REOPTIMIZE CLASS, the fire method in reality gets called mutliple times, the static might help a bit
	// TODO Im really not sure...

	public void tick() {
		fire();
		smoke();
		portal();
	}
	
	public int[] portal() {
		return Tile.air;
	}
	
	private int fireDelayTime = 0;
	private int fireIncreaseTime = 0; //20 VVVVVV
	private final int fireFrameSkip = 60; // 60 is good for 14 frames

	public int[] fire() {
		fireDelayTime += 1;
		if (fireDelayTime >= fireFrameSkip) {
			fireIncreaseTime += 1;
			fireDelayTime = 0;
		}
		if (fireIncreaseTime >= Tile.fireAnimationSwap.length) {
			fireIncreaseTime = 0;
		}
		return Tile.fireAnimationSwap[fireIncreaseTime];
	}
	
	private int smokeDelayTime = 0;
	private final int smokeFrameSkip = 80;
	private int smokeIncreaseTime = 0;

	public int[] smoke() {
		smokeDelayTime += 1;
		if(smokeDelayTime >= smokeFrameSkip) {
			smokeIncreaseTime += 1;
			smokeDelayTime = 0;
		}
		if(smokeIncreaseTime >= Tile.smokeAnimationSwap.length) {
			smokeIncreaseTime = 0;
		}
		return Tile.smokeAnimationSwap[smokeIncreaseTime];
	}
}
