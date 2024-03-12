package com.aliquamgames.paradigm.playing;

import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import com.aliquamgames.paradigm.Core;
import com.aliquamgames.paradigm.ImageLoader;
import com.aliquamgames.paradigm.util.Util;

public class Sky {

	public static int day = 0;
	public static int night = 1;
	public static int time = day;
	public static int r1 = 187, g1 = 255, b1 = 253; // day r1 = 187 g1 = 255 b1 = 253 //r1 = 35, g1 = 208, b1 = 224;
	public static int r2 = 15; // night
	public static boolean isNight = false;
	public static boolean isDay = true;
	private Texture[] sun_moon = new Texture[2];
	private Texture[] background = new Texture[4];

	public int gg2 = 15;
	public int b2 = 80;
	public static int r = r1, g = g1, b = b1;
	public static int a = 255;

	public int dayFrame = 0, dayTime = 60 * 1000 * 10; //25000 * 2
	public int changeFrame = 0, changeTime = 4;

	public Sky() {
		try {
			sun_moon[0] = ImageLoader.loadSlick("PNG", "/scenery/sun.png");
			sun_moon[1] = ImageLoader.loadSlick("PNG", "/scenery/moon.png");
			
			background[0] = ImageLoader.loadSlick("PNG", "/scenery/backgrounds/Mountain_Loop1.png");
			background[1] = ImageLoader.loadSlick("PNG", "/scenery/backgrounds/Mountain_Loop2.png");
			background[2] = ImageLoader.loadSlick("PNG", "/scenery/backgrounds/Mountain_Snow_Loop1.png");
			background[3] = ImageLoader.loadSlick("PNG", "/scenery/backgrounds/Mountain_Snow_Loop2.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (time == day) {
			r = r1;
			g = g1;
			b = b1;

		} else if (time == night) {
			r = r2;
			g = gg2;
			b = b2;

		}
	}

	public void tick() {
		if (dayFrame >= dayTime) {
			if (time == day) {
				time = night;
			} else if (time == night) {
				time = day;
			}

			dayFrame = 0;
		} else {
			dayFrame += 1;
		}

		if (changeFrame >= changeTime) {
			if (time == day) {

				if (r < r1) {
					r += 1;
				}
				if (g < g1) {
					g += 1;
				}
				if (b < b1) {
					b += 1;
				}

			} else if (time == night) {

				if (r > r2) {
					r -= 1;
				}
				if (g > gg2) {
					g -= 1;
				}
				if (b > b2) {
					b -= 1;
				}
			}
			changeFrame = 0;
		} else {
			changeFrame += 1;
		}
	}
	
	private int SUN_MOON_X = Core.getGameWidth() / 2;
	private int SUN_MOON_Y = 0;

	public void render() {
		Util.fillColoredRect(new Color(r, g, b, a), 0, 0, Core.getGameWidth(), Core.getGameHeight());
		GL11.glColor4f(255, 255, 255, 255);
//		switch(Core.level.getBiomePlayerIsIn()) {
//		case PLAINS:
//			Util.drawImage(background[0], 0, 0, Core.getGameWidth(), Core.getGameHeight(), true);
//			break;
//		case MOUNTAINS:
//			Util.drawImage(background[0], 0, 0, Core.getGameWidth(), Core.getGameHeight(), true);
//			break;
//		case SNOW:
//			Util.drawImage(background[2], 0, 0, Core.getGameWidth(), Core.getGameHeight(), true);
//			break;
//		case DESERT:
//			break;
//		case FOREST:
//			Util.drawImage(background[0], 0, 0, Core.getGameWidth(), Core.getGameHeight(), true);
//			break;
//		case TAINT:
//			break;
//		case JUNGLE:
//			Util.drawImage(background[0], 0, 0, Core.getGameWidth(), Core.getGameHeight(), true);
//			break;
//		case OCEAN:
//			break;
//		} //TODO ^^^^^^^^ Don't do it! it lags the fuck out
		Util.drawImage(background[0], 0, 0, Core.getGameWidth(), Core.getGameHeight(), true);
		Util.rotate(SUN_MOON_X, SUN_MOON_Y, rotation(false), /* */ 400.0f / 2, 300.0f / 2);
		//curse this location	im gonna be here forever		 ^^^^^^^^^^^^^^^^^^^^^^^^
		if(time == day) {
			Util.drawImage(sun_moon[0], SUN_MOON_X, SUN_MOON_Y, 76, 76, true);
		} else if(time == night) {
			Util.drawImage(sun_moon[1], SUN_MOON_X, SUN_MOON_Y, 76, 76, true);
		}
		GL11.glPopMatrix();

	}
	
	private double angle = 0;
	public double rotation(boolean positive) {
		double rotationSpeed = 0.1;
		if(positive) {
		angle += rotationSpeed;
		} else {
		angle -= rotationSpeed;
		}
//		if(angle >= 0) {
//			angle = 360;
//		}
//		if(angle <= 360) {
//			angle = 0;
//		}
		return angle;
	}

}
