package com.aliquamgames.paradigm.blocks;

import org.lwjgl.Sys;
import org.lwjgl.util.Rectangle;

public class Crop extends Block {
	
	private int wheatMatureTime = 60 * 1000 * 12; //12 minutes
	private int carrotMatureTime = 60 * 1000 * 10; //10 minutes
	private int potatoMatureTime = 60 * 1000 * 11; //11 minutes
	private long startTime = getTime();
	private long currentTime = getTime();
	protected boolean[] crop = new boolean[3];
	
	public Crop(Rectangle size, int[] id, int strength, int spotX, int spotY, String name) {
		super(size, id, strength, spotX, spotY, name);
		currentTime = getTime();

	}
	
	public void rotationWheat() {
		startTime = getTime();
		if(currentTime < startTime + wheatMatureTime) {
			currentTime = getTime();
		}
	}
	
	public void rotationCarrot() {
		startTime = getTime();
		if(currentTime < startTime + carrotMatureTime) {
			currentTime = getTime();
		}
	}

	public void rotationPotato() {
		startTime = getTime();
		if(currentTime < startTime + potatoMatureTime) {
			currentTime = getTime();
		}
	}

	public void tick() {
		super.tick();
		if(crop[0]) {
			rotationWheat();
		} else if(crop[1]) {
			rotationCarrot();
		} else if(crop[2]) {
			rotationPotato();
		}
	}

	public void render() {
		super.render();

	}
	
	
	public long getTime() {
		return Sys.getTime() * 1000 / Sys.getTimerResolution();
	}
	
}
