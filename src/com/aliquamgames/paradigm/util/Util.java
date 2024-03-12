package com.aliquamgames.paradigm.util;

import java.nio.ByteBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;

import com.aliquamgames.paradigm.Core;

/**
 * Recreation of java.awt.Graphics in LWJGL COPYRIGHT ALIQUAMGAMES (C)
 * With some extras
 */

public class Util {

	/**
	 * DRAW RECT METHODS
	 */

	public static void drawRect(float x, float y, float width, float height) {
		drawLine(x, y, x, y + height);
		drawLine(x, y + height, x + width, y + height);
		drawLine(x + width, y + height, x + width, y);
		drawLine(x + width, y, x, y);
	}

	public static void drawColoredRect(Color color, float x, float y, float width, float height) {
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glColor4f((float) color.getRed() / (float) 255, (float) color.getGreen() / (float) 255, (float) color.getBlue() / (float) 255, (float) color.getAlpha() / (float) 255);
		drawRect(x, y, width, height);
		GL11.glColor4f(255, 255, 255, 255);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
	}

	public static void drawColoredRect(Color color, float x, float y, float width, float height, boolean needsTransparency) {
		if (needsTransparency) GL11.glEnable(GL11.GL_BLEND);
		drawColoredRect(color, x, y, width, height);
		GL11.glDisable(GL11.GL_BLEND);
	}

	/**
	 * FILL RECT METHODS
	 */

	public static void fillRect(float x, float y, float width, float height) {
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2f(x, y);
		GL11.glVertex2f(x, y + height);
		GL11.glVertex2f(x + width, y + height);
		GL11.glVertex2f(x + width, y);
		GL11.glEnd();
	}

	public static void fillColoredRect(Color color, float x, float y, float width, float height) {
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glColor4f((float) color.getRed() / (float) 255, (float) color.getGreen() / (float) 255, (float) color.getBlue() / (float) 255, (float) color.getAlpha() / (float) 255);
		fillRect(x, y, width, height);
		GL11.glColor4f(255, 255, 255, 255);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
	}

	public static void fillColoredRect(Color color, float x, float y, float width, float height, boolean needsTransparency) {
		if (needsTransparency) GL11.glEnable(GL11.GL_BLEND);
		fillColoredRect(color, x, y, width, height);
		GL11.glDisable(GL11.GL_BLEND);
	}

	/**
	 * DRAW LINE METHODS
	 */

	public static void drawLine(float x1, float y1, float x2, float y2) {
		GL11.glBegin(GL11.GL_LINES);
		GL11.glVertex2f(x1, y1);
		GL11.glVertex2f(x2, y2);
		GL11.glEnd();
	}

	public static void drawColoredLine(Color color, float x1, float y1, float x2, float y2, int thickness, boolean needsTransparency) {
		if (needsTransparency) GL11.glEnable(GL11.GL_BLEND);
		drawColoredLine(color, x1, y1, x2, y2, thickness);
		GL11.glDisable(GL11.GL_BLEND);
	}

	public static void drawColoredLine(Color color, float x1, float y1, float x2, float y2) {
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glColor4f((float) color.getRed() / (float) 255, (float) color.getGreen() / (float) 255, (float) color.getBlue() / (float) 255, (float) color.getAlpha() / (float) 255);
		drawLine(x1, y1, x2, y2);
		GL11.glColor4f(255, 255, 255, 255);
		GL11.glEnable(GL11.GL_TEXTURE_2D);

	}

	public static void drawLine(float x1, float y1, float x2, float y2, int thickness) {
		if (y2 - y1 < x2 - x1) {
			fillRect(x1, y1, (x2 - x1), thickness);
		} else {
			for (int i = 0; i < thickness; i++) {
				drawLine(x1 + i, y1, x2 + i, y2);
			}
		}
	}

	public static void drawColoredLine(Color color, float x1, float y1, float x2, float y2, int thickness) {
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glColor4f((float) color.getRed() / (float) 255, (float) color.getGreen() / (float) 255, (float) color.getBlue() / (float) 255, (float) color.getAlpha() / (float) 255);
		drawLine(x1, y1, x2, y2, thickness);
		GL11.glColor4f(255, 255, 255, 255);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
	}

	public static void drawX(float x, float y, float width, float height, int thickness) {
		GL11.glBegin(GL11.GL_LINES);
		for (int i = 0; i < thickness; i++) {
			GL11.glVertex2f(x + i, y);
			GL11.glVertex2f(x + width - (thickness - i), y + height);
		}
		GL11.glEnd();

		GL11.glBegin(GL11.GL_LINES);
		for (int i = thickness; i >= 0; i--) {
			GL11.glVertex2f(x + width - i, y);
			GL11.glVertex2f(x + (thickness - i), y + height);
		}
		GL11.glEnd();
	}

	public static void drawColoredX(Color color, float x, float y, float width, float height, int thickness) {
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glColor4f((float) color.getRed() / (float) 255, (float) color.getGreen() / (float) 255, (float) color.getBlue() / (float) 255, (float) color.getAlpha() / (float) 255);
		drawX(x, y, width, height, thickness);
		GL11.glColor4f(255, 255, 255, 255);
		GL11.glEnable(GL11.GL_TEXTURE_2D);

	}

	/**
	 * DRAW SHAPE METHODS
	 */

	public static void drawShape(float centerX, float centerY, int radius, int numberOfSides) {
		drawCircle(centerX, centerY, radius, numberOfSides);
	}

	public static void drawColoredShape(Color color, float centerX, float centerY, int radius, int numberOfSides) {
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glColor4f((float) color.getRed() / (float) 255, (float) color.getGreen() / (float) 255, (float) color.getBlue() / (float) 255, (float) color.getAlpha() / (float) 255);
		drawShape(centerX, centerY, radius, numberOfSides);
		GL11.glColor4f(255, 255, 255, 255);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
	}

	/**
	 * FILL SHAPE METHODS
	 */
	public static void fillShape(float centerX, float centerY, int radius, int numberOfSides) {
		fillCircle(centerX, centerY, radius, numberOfSides);
	}

	public static void fillColoredShape(Color color, float centerX, float centerY, int radius, int numberOfSides) {
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glColor4f((float) color.getRed() / (float) 255, (float) color.getGreen() / (float) 255, (float) color.getBlue() / (float) 255, (float) color.getAlpha() / (float) 255);
		fillShape(centerX, centerY, radius, numberOfSides);
		GL11.glColor4f(255, 255, 255, 255);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
	}
	
	/** 
	 * DRAW TRIANGLE METHODS
	 */
	
	public void drawTriangle(int x, int y, int width, int height) {

	}
	
	/**
	 * FILL TRIANGLE METH
	 */
	
	public void fillTriangle(int x, int y, int width, int height) {
		
	}

	/**
	 * DRAW CIRCLE METHODS
	 */

	public static void drawCircle(float centerX, float centerY, int radius, int numberOfSegments) {
		float theta = (float) (2 * 3.1415926 / (float) (numberOfSegments));
		float c = (float) Math.cos(theta);
		float s = (float) Math.sin(theta);
		float t;

		float x = radius;
		float y = 0;

		GL11.glBegin(GL11.GL_LINE_LOOP);
		for (int ii = 0; ii < numberOfSegments; ii++) {
			GL11.glVertex2f(x + centerX, y + centerY);

			t = x;
			x = c * x - s * y;
			y = s * t + c * y;
		}
		GL11.glEnd();
	}

	public static void drawColoredCircle(Color color, float centerX, float centerY, int radius, int numberOfSegments) {
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glColor4f((float) color.getRed() / (float) 255, (float) color.getGreen() / (float) 255, (float) color.getBlue() / (float) 255, (float) color.getAlpha() / (float) 255);
		drawCircle(centerX, centerY, radius, numberOfSegments);
		GL11.glColor4f(255, 255, 255, 255);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
	}

	/**
	 * FILL CIRCLE METHODS
	 */

	public static void fillCircle(float centerX, float centerY, int radius, int numberOfSegments) {
		for (int i = radius; i > 0; i--) {
			drawCircle(centerX, centerY, i, numberOfSegments);
		}
	}

	public static void fillColoredCircle(Color color, float centerX, float centerY, int radius, int numberOfSegments) {
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glColor4f((float) color.getRed() / (float) 255, (float) color.getGreen() / (float) 255, (float) color.getBlue() / (float) 255, (float) color.getAlpha() / (float) 255);
		fillCircle(centerX, centerY, radius, numberOfSegments);
		GL11.glColor4f(255, 255, 255, 255);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
	}

	/**
	 * DRAW ARC METHODS
	 */

	public static void drawArc(float centerX, float centerY, int radius, float startAngle, float angleBetweenEachLine, int numberOfSegments) {
		float theta = angleBetweenEachLine / (float) (numberOfSegments - 1);
		float tangetial_factor = (float) Math.tan(theta);
		float radial_factor = (float) Math.cos(theta);

		float x = radius * (float) Math.cos(startAngle);
		float y = radius * (float) Math.sin(startAngle);

		GL11.glBegin(GL11.GL_LINE_STRIP);
		for (int ii = 0; ii < numberOfSegments; ii++) {
			GL11.glVertex2f(x + centerX, y + centerY);

			float tx = -y;
			float ty = x;

			x += tx * tangetial_factor;
			y += ty * tangetial_factor;

			x *= radial_factor;
			y *= radial_factor;
		}
		GL11.glEnd();
	}

	public static void drawColoredArc(Color color, float centerX, float centerY, int radius, float startAngle, float angleBetweenEachLine, int numberOfSegments) {
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glColor4f((float) color.getRed() / (float) 255, (float) color.getGreen() / (float) 255, (float) color.getBlue() / (float) 255, (float) color.getAlpha() / (float) 255);
		drawArc(centerX, centerY, radius, startAngle, angleBetweenEachLine, numberOfSegments);
		GL11.glColor4f(255, 255, 255, 255);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
	}

	/**
	 * FILL ARC METHODS
	 */

	public static void fillArc(float centerX, float centerY, int radius, float startAngle, float angleBetweenEachLine, int numberOfSegments) {
		for (int i = radius; i > 0; i--) {
			drawArc(centerX, centerY, i, startAngle + i, angleBetweenEachLine - i, numberOfSegments);
		}
	}

	public static void fillColoredArc(Color color, float centerX, float centerY, int radius, float startAngle, float angleBetweenEachLine, int numberOfSegments) {
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glColor4f((float) color.getRed() / (float) 255, (float) color.getGreen() / (float) 255, (float) color.getBlue() / (float) 255, (float) color.getAlpha() / (float) 255);
		fillArc(centerX, centerY, radius, startAngle, angleBetweenEachLine, numberOfSegments);
		GL11.glColor4f(255, 255, 255, 255);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
	}

	/**
	 * DRAW STRING METHODS
	 */

	@SuppressWarnings("static-access")
	public static void drawString(String text, int x, int y, TrueTypeFont font, boolean bold, int fontSize, boolean antiAlias) {
		Core.fontRenderer.configFont(font, bold, fontSize, antiAlias);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		Core.fontRenderer.font.drawString(y, x, text);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glColor4f(255, 255, 255, 255);
	}

	@SuppressWarnings("static-access")
	public static void drawString(String text, int x, int y, TrueTypeFont font, boolean bold, int fontSize, boolean antiAlias, Color color) {
		Core.fontRenderer.configFont(font, bold, fontSize, antiAlias);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		Core.fontRenderer.font.drawString(x, y, text, color);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glColor4f(255, 255, 255, 255);
	}

	@SuppressWarnings("static-access")
	public static void drawStringWithShadow(String text, int x, int y, TrueTypeFont font, boolean bold, int fontSize, boolean antiAlias, Color color) {
		Core.fontRenderer.configFont(font, bold, fontSize, antiAlias);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		Core.fontRenderer.font.drawString(x + 2, y + 2, text, Color.black);
		Core.fontRenderer.font.drawString(x, y, text, color);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glColor4f(255, 255, 255, 255);
	}

	@SuppressWarnings("static-access")
	public static void drawStringWithShadow(String text, int x, int y, TrueTypeFont font, boolean bold, int fontSize, boolean antiAlias) {
		Core.fontRenderer.configFont(font, bold, fontSize, antiAlias);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		Core.fontRenderer.font.drawString(x + 2, y + 2, text, Color.black);
		Core.fontRenderer.font.drawString(x, y, text, Color.white);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glColor4f(255, 255, 255, 255);
	}

	@SuppressWarnings("static-access")
	public static void drawStringWithShadow(String text, int x, int y, float fontSize, boolean antiAlias) {
		Core.fontRenderer.configCustom(fontSize, antiAlias);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		Core.fontRenderer.font2.drawString(x + 2, y + 2, text, Color.black);
		Core.fontRenderer.font2.drawString(x, y, text);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glColor4f(255, 255, 255, 255);
	}

	@SuppressWarnings("static-access")
	public static void drawStringWithShadow(String text, int x, int y, float fontSize, boolean antiAlias, Color color) {
		Core.fontRenderer.configCustom(fontSize, antiAlias);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		Core.fontRenderer.font2.drawString(x + 2, y + 2, text, Color.black);
		Core.fontRenderer.font2.drawString(x, y, text, color);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glColor4f(255, 255, 255, 255);
	}

	@SuppressWarnings("static-access")
	public static void drawString(String text, int x, int y, float fontSize, boolean antiAlias) {
		Core.fontRenderer.configCustom(fontSize, antiAlias);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		Core.fontRenderer.font2.drawString(x, y, text);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glColor4f(255, 255, 255, 255);
	}

	@SuppressWarnings("static-access")
	public static void drawString(String text, int x, int y, float fontSize, boolean antiAlias, String loadFontFrom) {
		Core.fontRenderer.configCustom(fontSize, antiAlias, loadFontFrom);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		Core.fontRenderer.font2.drawString(x, y, text);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glColor4f(255, 255, 255, 255);
	}

	@SuppressWarnings("static-access")
	public static void drawString(String text, int x, int y, float fontSize, boolean antiAlias, Color color) {
		Core.fontRenderer.configCustom(fontSize, antiAlias);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		Core.fontRenderer.font2.drawString(x, y, text, color);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glColor4f(255, 255, 255, 255);
	}

	@SuppressWarnings("static-access")
	public static void drawString(String text, int x, int y, float fontSize, boolean antiAlias, String loadFontFrom, Color color) {
		Core.fontRenderer.configCustom(fontSize, antiAlias, loadFontFrom);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		Core.fontRenderer.font2.drawString(x, y, text, color);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glColor4f(255, 255, 255, 255);
	}

	public static int getStringLength(String string, int fontSize) {
		return string.length() * (fontSize / 2);
	}
	
	public static void rotate(int x, int y, double rotate, float translatefX, float translatefY) {
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glPushMatrix();
		GL11.glTranslated(x, y, 0);
		GL11.glTranslatef(translatefX, translatefY, -0.0f); // back to previous position
		GL11.glRotated(rotate, 0.0f, 0.0f, -1.0f); // rotate
		GL11.glTranslatef(-translatefX, -translatefY, 0.0f); // to the origin
		GL11.glTranslated(-x, -y, 0);
		// GL11.glPopMatrix();
	}

	public static void rotate(int x, int y, double rotate) {
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glPushMatrix();
		GL11.glTranslated(x, y, 0);
		GL11.glTranslatef(10.0f, 10.5f, -0.0f); // back to previous position
		GL11.glRotated(rotate, 0.0f, 0.0f, -1.0f); // rotate
		GL11.glTranslatef(-10.0f, -10.5f, 0.0f); // to the origin
		GL11.glTranslated(-x, -y, 0);
		// GL11.glPopMatrix();
	}

	// Torus is a donut like thing
	/** What happens when luke gets bored... */
	// why the hell did i write this?
	public static void drawTorus(float r, float R, int nsides, int rings) {
		float ringDelta = 2.0f * (float) Math.PI / rings;
		float sideDelta = 2.0f * (float) Math.PI / nsides;
		float theta = 0.0f, cosTheta = 1.0f, sinTheta = 0.0f;
		for (int i = rings - 1; i >= 0; i--) {
			float theta1 = theta + ringDelta;
			float cosTheta1 = (float) Math.cos(theta1);
			float sinTheta1 = (float) Math.sin(theta1);
			GL11.glBegin(GL11.GL_QUAD_STRIP);
			float phi = 0.0f;
			for (int j = nsides; j >= 0; j++) {
				phi += sideDelta;
				float cosPhi = (float) Math.cos(phi);
				float sinPhi = (float) Math.sin(phi);
				float dist = R + r * cosPhi;
				// this was when i realised i was doing something i didnt need too XD
				GL11.glNormal3f(cosTheta1 * cosPhi, -sinTheta1 * cosPhi, sinPhi);
				GL11.glVertex3f(cosTheta1 * dist, -sinTheta1 * dist, r * sinPhi);
				GL11.glNormal3f(cosTheta * cosPhi, -sinTheta * cosPhi, sinPhi);
				GL11.glVertex3f(cosTheta * dist, -sinTheta * dist, r * sinPhi);
			}
			GL11.glEnd();
			theta = theta1;
			cosTheta = cosTheta1;
			sinTheta = sinTheta1;
		}
	}

	/**
	 * DRAW IMAGE METHODS
	 */

	/**
	 * @param texture
	 *            - the image to be binded
	 * @param x
	 *            - the x to draw the rectangle at
	 * @param y
	 *            - the y to draw the rectangle at
	 * @param width
	 *            - the width of the rectangle
	 * @param height
	 *            - the height of the rectangle
	 * @param startingXOnTexture
	 *            - the starting x on the texture
	 * @param startingYOnTexture
	 *            - the starting y on the texture
	 * @param textureWidth
	 *            - the number of pixels on the width that you want it to go to.. startingXOnTexture + textureWidth
	 * @param textureHeight
	 *            - the number of pixels on the height that you want it to go to.. startingYOnTexture + textureHeight
	 * @param needsTransparency
	 *            - controls if it needs to enable transparency
	 */
	public static void fillTexturedRect(Texture texture, float x, float y, float width, float height, float startingXOnTexture, float startingYOnTexture, float textureWidth, float textureHeight, boolean needsTransparency) {
		texture.bind();
		if (needsTransparency) {
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		} else {
			GL11.glDisable(GL11.GL_BLEND);
		}
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glTexCoord2f((float) startingXOnTexture / (float) texture.getTextureWidth(), (float) startingYOnTexture / (float) texture.getTextureHeight());
		GL11.glVertex2f(x, y);
		GL11.glTexCoord2f((float) startingXOnTexture / (float) texture.getTextureWidth(), (float) (startingYOnTexture + textureHeight) / (float) texture.getTextureHeight());
		GL11.glVertex2f(x, y + height);
		GL11.glTexCoord2f((float) (startingXOnTexture + textureWidth) / (float) texture.getTextureWidth(), (float) (startingYOnTexture + textureHeight) / (float) texture.getTextureHeight());
		GL11.glVertex2f(x + width, y + height);
		GL11.glTexCoord2f((float) (startingXOnTexture + textureWidth) / (float) texture.getTextureWidth(), (float) startingYOnTexture / (float) texture.getTextureHeight());
		GL11.glVertex2f(x + width, y);
		GL11.glEnd();

		GL11.glDisable(GL11.GL_BLEND);
		GL11.glColor4f(255, 255, 255, 255);
	}

	public static void drawImage(Texture texture, float x, float y, float width, float height, float startingXOnTexture, float startingYOnTexture, boolean needsTransparency) {
		fillTexturedRect(texture, x, y, width, height, startingXOnTexture, startingYOnTexture, texture.getImageWidth(), texture.getImageHeight(), needsTransparency);
	}

	public static void drawImage(Texture texture, float dx1, float dy1, float dx2, float dy2, float sx1, float sy1, float sx2, float sy2, boolean needsTransparency) {
		fillTexturedRect(texture, dx1, dy1, (dx2 - dx1), (dy2 - dy1), sx1, sy1, (sx2 - sx1), (sy2 - sy1), needsTransparency);
	}

	public static void drawImage(Texture texture, float x, float y, boolean needsTransparency) {
		fillTexturedRect(texture, x, y, x + texture.getImageWidth(), y + texture.getImageHeight(), 0, 0, texture.getImageWidth(), texture.getImageHeight(), needsTransparency);
	}

	public static void drawImage(Texture texture, float x, float y, float width, float height, boolean needsTransparency) {
		fillTexturedRect(texture, x, y, width, height, 0, 0, texture.getImageWidth(), texture.getImageHeight(), needsTransparency);
	}

	public static void drawImageNearestNeighbor(Texture texture, float x, float y, float width, float height, float startingXOnTexture, float startingYOnTexture, float textureWidth, float textureHeight, boolean needsTransparency) {
		texture.bind();
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
		if (needsTransparency) {
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		} else {
			GL11.glDisable(GL11.GL_BLEND);
		}
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glTexCoord2f((float) startingXOnTexture / (float) texture.getTextureWidth(), (float) startingYOnTexture / (float) texture.getTextureHeight());
		GL11.glVertex2f(x, y);
		GL11.glTexCoord2f((float) startingXOnTexture / (float) texture.getTextureWidth(), (float) (startingYOnTexture + textureHeight) / (float) texture.getTextureHeight());
		GL11.glVertex2f(x, y + height);
		GL11.glTexCoord2f((float) (startingXOnTexture + textureWidth) / (float) texture.getTextureWidth(), (float) (startingYOnTexture + textureHeight) / (float) texture.getTextureHeight());
		GL11.glVertex2f(x + width, y + height);
		GL11.glTexCoord2f((float) (startingXOnTexture + textureWidth) / (float) texture.getTextureWidth(), (float) startingYOnTexture / (float) texture.getTextureHeight());
		GL11.glVertex2f(x + width, y);
		GL11.glEnd();

		GL11.glDisable(GL11.GL_BLEND);
		GL11.glColor4f(255, 255, 255, 255);
	}

	private static final int BYTES_PER_PIXEL = 4;

	public static int BufferedImageToTexture(java.awt.image.BufferedImage image) {
		int[] pixels = new int[image.getWidth() * image.getHeight()];
		image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());

		ByteBuffer buffer = BufferUtils.createByteBuffer(image.getWidth() * image.getHeight() * BYTES_PER_PIXEL); // 4 for RGBA 3 for RGB

		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				int pixel = pixels[y * image.getWidth() + x];
				buffer.put((byte) ((pixel >> 16) & 0xFF)); // The Red Component
				buffer.put((byte) ((pixel >> 8) & 0xFF)); // The Green Component
				buffer.put((byte) (pixel & 0xFF)); // The Blue Component
				buffer.put((byte) ((pixel >> 24) & 0xFF)); // The Alpha Component - ONLY FOR RGBA
			}
		}

		/* We now have a bytebuffer filled with the color data for each pixel 
		 * now we just create the texture ID and bind it. and we can now load it into whatever
		 * OpenGL method we want
		 */

		buffer.flip();

		int textureID = GL11.glGenTextures(); // Generate texture ID
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID); // Bind texture ID

		// Setup wrap mode
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);

		// Setup texture scaling filtering
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);

		// Send texel data to OpenGL
		// explaination on texel http://whatis.techtarget.com/definition/texel-texture-element
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA8, image.getWidth(), image.getHeight(), 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer);

		// return the texture ID so we can bind it later on again
		return textureID;
	}

	public static Texture convertBufferedImageToTexture(java.awt.image.BufferedImage image) {
		return (Texture) image;
	}
}
