package com.aliquamgames.paradigm.blocks;

import org.lwjgl.util.Rectangle;
import org.newdawn.slick.Color;

import com.aliquamgames.paradigm.Core;
import com.aliquamgames.paradigm.level.InfiniteLevel.BiomeEnum;
import com.aliquamgames.paradigm.playing.Tile;
import com.aliquamgames.paradigm.util.Util;

public class GrassOverlay extends Block {
	
	private final Color plains = new Color(0, 0, 0, 90);
	private final Color forest = new Color(0, 0, 0, 255);
	
	private Color color;
	private Color[] grassColor = {plains, forest};

	public GrassOverlay(Rectangle size, int[] id, int strength, int spotX, int spotY, String name) {
		super(size, id, strength, spotX, spotY, name);
	}
	
	public void addOverlayer() {
		if(Core.level.getBiomeBlockIsIn(spotX, spotY).equals(BiomeEnum.PLAINS)) {
			color = grassColor[0];
		}
		Util.fillColoredRect(color, spotX, spotY, Tile.tileSize, 6);
	}
	
	public void render() {
		addOverlayer();
		super.render();
	}

}
