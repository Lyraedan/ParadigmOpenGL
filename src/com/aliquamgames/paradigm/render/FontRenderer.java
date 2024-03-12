package com.aliquamgames.paradigm.render;

import java.awt.Font;
import java.io.IOException;
import java.io.InputStream;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.util.ResourceLoader;

import com.aliquamgames.paradigm.ImageLoader;

/**
 * 
 * @author Luke Rapkin
 */
public class FontRenderer {

	public static TrueTypeFont font;
	public static TrueTypeFont font2;

	private boolean bold = true;
	private int fontSize = 24;
	private float fontSizeFloat = 24f;
	private boolean antiAlias = true;
	private String loadFontFrom = "res/fonts/unifont.ttf";

	public FontRenderer() {
		initFont("Times New Roman", true, fontSize, antiAlias);
		initCustomFont(fontSize, true);
		initCustomFont(fontSize, true, loadFontFrom);
	}

	public void configFont(TrueTypeFont font, boolean bold, int fontSize, boolean antiAlias) {
		this.font = font;
		this.bold = bold;
		this.fontSize = fontSize;
		this.antiAlias = antiAlias;
	}

	public void configCustom(float fontSize, boolean antiAlias) {
		this.fontSizeFloat = fontSize;
		this.antiAlias = antiAlias;
	}
	
	public void configCustom(float fontSize, boolean antiAlias, String loadFontFrom) {
		this.fontSizeFloat = fontSize;
		this.antiAlias = antiAlias;
		this.loadFontFrom = loadFontFrom;
	}

	public void initFont(String font, boolean bold, int fontSize, boolean antiAlias) {
		// load a default java font
		Font awtFont = null;
		if (bold) {
			awtFont = new Font(font, Font.BOLD, fontSize);
		} else {
			awtFont = new Font(font, Font.PLAIN, fontSize);
		}

		this.font = new TrueTypeFont(awtFont, antiAlias);
	}

	public void initCustomFont(float fontSize, boolean antiAlias) {
		// load font from a .tff file //and now ;)
		try {
			InputStream inputStream = ResourceLoader.getResourceAsStream("res/fonts/unifont.ttf");

			Font awtFont2 = Font.createFont(Font.TRUETYPE_FONT, inputStream);
			awtFont2 = awtFont2.deriveFont(fontSize);
			font2 = new TrueTypeFont(awtFont2, antiAlias);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void initCustomFont(float fontSize, boolean antiAlias, String loadFontFrom) {
		// load font from a .tff file //and now ;)
		try {
			InputStream inputStream = ResourceLoader.getResourceAsStream(loadFontFrom);

			Font awtFont2 = Font.createFont(Font.TRUETYPE_FONT, inputStream);
			awtFont2 = awtFont2.deriveFont(fontSize);

			font2 = new TrueTypeFont(awtFont2, antiAlias);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getStringLength() {
//		int length = font.getFontMetrics(new Font(font, Font.BOLD, 20)).stringWidth(user);
		int finalLength = 0;
		return finalLength;
	}

}
