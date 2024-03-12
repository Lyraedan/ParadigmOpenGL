package com.aliquamgames.paradigm.minimap;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Rectangle;
import org.newdawn.slick.Color;

import com.aliquamgames.paradigm.Core;
import com.aliquamgames.paradigm.playing.Sky;
import com.aliquamgames.paradigm.playing.Tile;
import com.aliquamgames.paradigm.util.Util;

public class Minimap {

	// the width of the map in pixels
	private int mapWidth;
	// the height of the map in pixels
	private int mapHeight;
	// the width of a map blip
	private int blipWidth = 4;
	// the height of the map blip
	private int blipHeight = 4;
	// the x offset from the right side
	private int screenXOffset = 15;
	// the y offset from the top
	private int screenYOffset = 10;
	// the x for setting blips
	private int x = 0;
	// the y for setting blips
	private int y = 0;
	// this is the starting x
	private int startX = 0;
	// this is the starting y
	private int startY = 0;

	// controls if the variables startX and startY have been set
	private boolean set = false;

	// the MinimapBlip mutlidimensional array
	private MinimapBlip[][] blips = new MinimapBlip[30][30];

	public Minimap() {
		for (int x = 0; x < blips.length; x++) {
			for (int y = 0; y < blips[0].length; y++) {
				blips[x][y] = new MinimapBlip(new Rectangle(Core.getGameWidth() - screenXOffset - (blipWidth * (blips.length - 1)) + (x * blipWidth), (y * blipHeight + screenYOffset), blipWidth, blipHeight), Tile.air);
			}
		}

		mapWidth = blipWidth * (blips.length - 1);
		mapHeight = blipHeight * (blips[0].length - 1);
	}

	@SuppressWarnings("static-access")
	public void assignColorToObjects(int camX, int camY, int renW, int renH) {
		if (!set) {
			startX = camX / Tile.tileSize - blips.length / 2 / 2 + 1;
			startY = camY / Tile.tileSize - blips[0].length / 2 / 2 + 3;
			set = true;
		}

		while (set) {
			try {
				if (startX + x >= 0 && startY + y >= 0 && startX + x < Core.level.worldW && startY + y < Core.level.worldH) {
					blips[x][y].id = Core.level.block[startX + x][startY + y].id;
					if (Core.level.block[startX + x][startY + y].discovered) blips[x][y].darkness = 0;
					else blips[x][y].darkness = Core.level.block[startX + x][startY + y].darkness;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			x++;
			if (x >= blips.length) {
				y++;
				x = 0;
			}
			if (y >= blips[0].length) {
				y = 0;
				x = 0;
				set = false;
			}
		}
	}

	public void addWayPoint(String name, int x, int y) {
		System.out.println("Added waypoint for " + name + " @ X:" + x + " Y: " + y);
	}

	public void tick(int camX, int camY, int renW, int renH) {
		assignColorToObjects(camX, camY, renW, renH);
	}

	public void render() {
		GL11.glDisable(GL11.GL_BLEND);
		Util.fillColoredRect(Color.black, Core.getGameWidth() - screenXOffset - mapWidth - 4, screenYOffset - 5, mapWidth + 15, mapHeight + 15);
		Util.fillColoredRect(new Color(Sky.r, Sky.g, Sky.b, 255), Core.getGameWidth() - screenXOffset - mapWidth, screenYOffset, mapWidth + 4, mapHeight + 4);

		for (int x = 0; x < blips.length; x++) {
			for (int y = 0; y < blips[0].length; y++) {
				blips[x][y].render();
			}
		}
		// draw player dot
		int x = Core.getGameWidth() - screenXOffset - (blipWidth * (blips.length - 1)) / 2 - (blipWidth / 2);
		int y = (blipHeight * (blips[0].length - 1)) / 2 - blipHeight - 5;

		Util.fillColoredRect(Color.red, x, y, blipWidth, blipHeight);

	}
}
