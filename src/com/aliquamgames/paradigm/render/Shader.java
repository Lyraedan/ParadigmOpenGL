package com.aliquamgames.paradigm.render;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

/**
 *
 * @author Luke Rapkin
 */
public class Shader {
	
	public Shader() {
//		GL11.glEnable(GL11.GL_COLOR_MATERIAL);
	}
	
	public void glLightf(float x, float y, float camX, float camY) {
//		GL11.glEnable(GL11.GL_COLOR_MATERIAL);
		FloatBuffer ambient = BufferUtils.createFloatBuffer(4);
		ambient.put(new float[] { 0.05f, 0.05f, 0.05f, 1f, }); //default
//		ambient.put(new float[] { x, y, camX, camY, }); //NO GO
//		ambient.put(new float[] { 1f, 5f, 1f, 0f, }); // very interesting....
		ambient.flip();

		FloatBuffer position = BufferUtils.createFloatBuffer(4);
		position.put(new float[] { x, y, camX, camY, });
//		position.put(new float[] { 0f, 0f, 0f, 1f, });
		position.flip();

		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_LIGHT0);
		GL11.glLightModel(GL11.GL_LIGHT_MODEL_AMBIENT, ambient);
		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, position);
	}
	
	public void glLighti(int x, int y, int camX, int camY) {
//		GL11.glEnable(GL11.GL_COLOR_MATERIAL);
		IntBuffer ambient = BufferUtils.createIntBuffer(4);
		ambient.put(new int[] { 0, 0, 0, 1, });
		ambient.flip();

		IntBuffer position = BufferUtils.createIntBuffer(4);
		position.put(new int[] { x, y, camX, camY, });
//		position.put(new int[] { 0, 0, 0, 1, });
		position.flip();

		GL11.glPushMatrix();
//		GL11.glTranslated(camX, camY, 0);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_LIGHT0);
		GL11.glLightModel(GL11.GL_LIGHT_MODEL_AMBIENT, ambient);
//		GL11.glTranslated(x, y, 0);
		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, position);
//		GL11.glTranslated(-x, -y, 0);
		GL11.glPopMatrix();
	}
	

	//USE THIS!!!		
	public void glLight(int x, int y, int blockWidth, int blockHeight, int translateX, int translateY, int radiusW, int radiusH) {
		GL11.glEnable(GL11.GL_COLOR_MATERIAL);
		IntBuffer ambient = BufferUtils.createIntBuffer(4);
		ambient.put(new int[] { 0, 0, 0, 1, }); //<- i think this is radius maybe intensity
		ambient.flip();

		IntBuffer position = BufferUtils.createIntBuffer(4);
		position.put(new int[] { x, y, blockWidth, blockHeight, });//<----- this may be radius im not 100% sure
		position.flip();

		GL11.glPushMatrix();
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_LIGHT0);
		GL11.glLightModel(GL11.GL_LIGHT_MODEL_AMBIENT, ambient);
		GL11.glTranslated(translateX, translateY, 0); //<---- sets postition
		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, position);
		GL11.glPopMatrix();
	}
	
	public void glLight(int x, int y, int camX, int camY, int translateX, int translateY) {
		GL11.glEnable(GL11.GL_COLOR_MATERIAL);
		IntBuffer ambient = BufferUtils.createIntBuffer(4);
		ambient.put(new int[] { 0, 0, 0, 1, }); //<- i think this is radius maybe intensity
		ambient.flip();

		IntBuffer position = BufferUtils.createIntBuffer(4);
		position.put(new int[] { x, y, camX, camY, });//<----- this may be radius im not 100% sure
		position.flip();

		GL11.glPushMatrix();
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_LIGHT0);
		GL11.glLightModel(GL11.GL_LIGHT_MODEL_AMBIENT, ambient);
		GL11.glTranslated(translateX, translateY, 0); //<---- sets postition
		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, position);
		GL11.glPopMatrix();
	}
	
	public void useShader(int x, int y, int camX, int camY, boolean GLSL) {
		if(!GLSL) {
			glLighti(x, y, camX, camY);
		} else {
			new GLSLLighting(x, y, camX, camY);
		}
	}

}
