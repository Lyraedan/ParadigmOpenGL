package com.aliquamgames.paradigm;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class ImageLoader {
	
	public static Texture character;
	
	public ImageLoader() {
		try {
			character = loadSlick("PNG", "/character/character.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Texture loadSlick(String format, String location) throws IOException {
		format = format.toUpperCase();
		return TextureLoader.getTexture(format, ResourceLoader.getResourceAsStream("res" + location));
	}
	
	public static BufferedImage loadJava(String location, boolean resourceAsStream) throws IOException {
		// getClass() didn't work
		if(resourceAsStream) {
		return ImageIO.read(Core.class.getResourceAsStream("res" + location));
		} else {
		return ImageIO.read(Core.class.getResource("res" + location));
		}
	}

}
