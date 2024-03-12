package com.aliquamgames.paradigm.blocks;

import java.awt.image.BufferedImage;

import org.lwjgl.util.Rectangle;
import org.newdawn.slick.opengl.Texture;

import com.aliquamgames.paradigm.Core;
import com.aliquamgames.paradigm.level.InfiniteLevel.BiomeEnum;
import com.aliquamgames.paradigm.playing.Tile;
import com.aliquamgames.paradigm.util.Util;

public class Water extends Block {

	public Water(Rectangle size, int[] id, int strength, int spotX, int spotY, String name) {
		super(size, id, strength, spotX, spotY, name);
	}
	
	public void tick() {
		super.tick();

	}

	private final int waterTrans = 93;

	// get hex codes at www.color-hex.com
	private final int plains = shifter(73, 95, 255, waterTrans);
	private final int jungle = shifter(38, 114, 255, waterTrans);
	private final int taint = shifter(166, 22, 255, waterTrans);
	private final int snow = shifter(109, 213, 255, waterTrans);
	private final int desert = shifter(61, 251, 255, waterTrans);
	private final int ocean = shifter(0, 12, 255, waterTrans);

	public int[] getColors = { plains, jungle, taint, snow, desert };
	public int[] colors = { plains, jungle, taint, snow, desert };

	int imageWidth = Tile.tileSize;
	int imageHeight = imageWidth;

	public BufferedImage color() {
		BufferedImage water = ((BufferedImage)Tile.terrain).getSubimage(Tile.tileSize, Tile.tileSize * 13, imageWidth, imageHeight);
		for (int x = 0; x < imageWidth; x++) {
			for (int y = 0; y < imageHeight; y++) {
				for (int i = 0; i < getColors.length; i++) {
					if (water.getRGB(x, y) != getColors[i]) {
						if (Core.level.getBiomeBlockIsIn(spotX, spotY).equals(BiomeEnum.PLAINS)) {
							water.setRGB(x, y, colors[0]);
						} else if (Core.level.getBiomeBlockIsIn(spotX, spotY).equals(BiomeEnum.JUNGLE)) {
							water.setRGB(x, y, colors[1]);
						} else if (Core.level.getBiomeBlockIsIn(spotX, spotY).equals(BiomeEnum.TAINT)) {
							water.setRGB(x, y, colors[2]);
						} else if (Core.level.getBiomeBlockIsIn(spotX, spotY).equals(BiomeEnum.SNOW)) {
							water.setRGB(x, y, colors[3]);
						} else if (Core.level.getBiomeBlockIsIn(spotX, spotY).equals(BiomeEnum.DESERT)) {
							water.setRGB(x, y, colors[4]);
						}
					}
				}
			}
		}
	
		return water = (BufferedImage)Util.convertBufferedImageToTexture(water);
	}

	private static int shifter(int r, int g, int b, int a) {
		return (a << 24 | r << 16 | g << 8 | b);
	}

	private BufferedImage image;
	private Texture image2;

	public void render() {
		super.render();
//		image = color();
//		image2 = Util.convertBufferedImageToTexture(image);
//		Util.drawImage(image2, rect.getX() - (int) Core.sX, rect.getY() - (int) Core.sY, rect.getX() + rect.getHeight() - (int) Core.sX, rect.getY() + rect.getHeight() - (int) Core.sY, 0, 0, image.getWidth(), image.getHeight(), true);

	}
}
