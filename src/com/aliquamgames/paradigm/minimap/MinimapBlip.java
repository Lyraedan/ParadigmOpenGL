package com.aliquamgames.paradigm.minimap;

import org.lwjgl.util.Rectangle;
import org.newdawn.slick.Color;

import com.aliquamgames.paradigm.playing.Tile;
import com.aliquamgames.paradigm.util.Util;

public class MinimapBlip {

	public int[] id = Tile.air;
	public int darkness = 0;
	public Rectangle rect = new Rectangle();

	public MinimapBlip(Rectangle size, int[] id) {
		rect.setBounds(size);
		this.id = id;
	}

	public void render() {
		Color col = getColor();

		if (id != Tile.air) {
			//Util.drawImage(Tile.terrain, rect.getX(), rect.getY(), rect.getX() + rect.getWidth(), rect.getY() + rect.getHeight(), id[0] * Tile.tileSize, id[1] * Tile.tileSize, id[0] * Tile.tileSize + Tile.tileSize, id[1] * Tile.tileSize, true);
			// Util.drawImage(Tile.terrain, id[0] * Tile.tileSize, id[1] * Tile.tileSize, Tile.tileSize, Tile.tileSize, true);
		}

		if (darkness != 0) Util.fillColoredRect(new Color(col.getRed(), col.getGreen(), col.getBlue(), darkness), rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight(), true);
		else if (id == Tile.air) Util.fillColoredRect(new Color(255, 255, 255, 0), rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight(), true);
		else Util.fillColoredRect(new Color(col.getRed(), col.getGreen(), col.getBlue(), 255), rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());

	}

	public Color getColor() {
		if (id == Tile.air) {
			return new Color(0, 0, 0);
		} else if (id == Tile.anvil) {
			return Color.gray;
		} else if (id == Tile.arythimilInSandstone2) {
			return Color.blue;
		} else if (id == Tile.arythimilInSandstone3) {
			return Color.blue;
		} else if (id == Tile.arythimilInSandstone) {
			return Color.blue;
		} else if (id == Tile.arythimilOverlay) {
			return Color.blue;
		} else if (id == Tile.arythimilOverlay2) {
			return Color.blue;
		} else if (id == Tile.arythimilOverlay3) {
			return Color.blue;
		} else if (id == Tile.bedrock) {
			return Color.gray;
		} else if (id == Tile.bedrock2) {
			return Color.gray;
		} else if (id == Tile.bedrock3) {
			return Color.gray;
		} else if (id == Tile.books) {
			return Color.red;
		} else if (id == Tile.bookShelf) {
			return new Color(127, 51, 0);
		} else if (id == Tile.bottomHalfOfBed) {
			return Color.white;
		} else if (id == Tile.bottomHalfOfMessyBed) {
			return Color.white;
		} else if (id == Tile.bridge) {
			return new Color(127, 51, 0);
		} else if (id == Tile.bridgeHolderUnderlay) {
			return new Color(127, 51, 0);
		} else if (id == Tile.chest) {
			return new Color(127, 51, 0);
		} else if (id == Tile.chiseledSandstone) {
			return Color.yellow;
		} else if (id == Tile.chiseledSandstonePillar) {
			return Color.yellow;
		} else if (id == Tile.chiseledStoneBlock) {
			return Color.gray;
		} else if (id == Tile.coalInSandstone) {
			return Color.black;
		} else if (id == Tile.coalInSandstone2) {
			return Color.black;
		} else if (id == Tile.coalInSandstone3) {
			return Color.black;
		} else if (id == Tile.coalOverlay) {
			return Color.black;
		} else if (id == Tile.coalOverlay2) {
			return Color.black;
		} else if (id == Tile.coalOverlay3) {
			return Color.black;
		} else if (id == Tile.cobaltInSandstone) {
			return Color.magenta;
		} else if (id == Tile.cobaltInSandstone2) {
			return Color.magenta;
		} else if (id == Tile.cobaltInSandstone3) {
			return Color.magenta;
		} else if (id == Tile.cobaltOverlay) {
			return Color.magenta;
		} else if (id == Tile.cobaltOverlay2) {
			return Color.magenta;
		} else if (id == Tile.cobaltOverlay3) {
			return Color.magenta;
		} else if (id == Tile.copperInSandstone) {
			return new Color(183, 73, 0);
		} else if (id == Tile.copperInSandstone2) {
			return new Color(183, 73, 0);
		} else if (id == Tile.copperInSandstone3) {
			return new Color(183, 73, 0);
		} else if (id == Tile.copperOverlay) {
			return new Color(183, 73, 0);
		} else if (id == Tile.copperOverlay2) {
			return new Color(183, 73, 0);
		} else if (id == Tile.copperOverlay3) {
			return new Color(183, 73, 0);
		} else if (id == Tile.craftingTable) {
			return new Color(127, 51, 0);
		} else if (id == Tile.diamondInSandstone) {
			return new Color(0, 255, 255);
		} else if (id == Tile.diamondInSandstone2) {
			return new Color(0, 255, 255);
		} else if (id == Tile.diamondInSandstone3) {
			return new Color(0, 255, 255);
		} else if (id == Tile.diamondOverlay) {
			return new Color(0, 255, 255);
		} else if (id == Tile.diamondOverlay2) {
			return new Color(0, 255, 255);
		} else if (id == Tile.diamondOverlay3) {
			return new Color(0, 255, 255);
		} else if (id == Tile.dirt) {
			return new Color(0, 0, 255);
		} else if (id == Tile.dirtLight) {
			return new Color(183, 51, 0);
		} else if (id == Tile.dirtLightWithSnow) {
			return Color.white;
		} else if (id == Tile.dirtWithGrass) {
			return Color.green;
		} else if (id == Tile.door) {
			return new Color(127, 51, 0);
		} else if (id == Tile.doorBottomOpen) {
			return new Color(127, 51, 0);
		} else if (id == Tile.doorTopOpen) {
			return new Color(127, 51, 0);
		} else if (id == Tile.emeraldInSandstone2) {
			return Color.green;
		} else if (id == Tile.emeraldInSandstone3) {
			return Color.green;
		} else if (id == Tile.emeraldInSandstone) {
			return Color.green;
		} else if (id == Tile.emeraldOverlay) {
			return Color.green;
		} else if (id == Tile.emeraldOverlay2) {
			return Color.green;
		} else if (id == Tile.emeraldOverlay3) {
			return Color.green;
		} else if (id == Tile.furnaceOff) {
			return Color.gray;
		} else if (id == Tile.furnaceOn) {
			return Color.gray;
		} else if (id == Tile.glass) {
			return new Color(0, 0, 0);
		} else if (id == Tile.goldInSandstone) {
			return Color.yellow;
		} else if (id == Tile.goldInSandstone2) {
			return Color.yellow;
		} else if (id == Tile.goldInSandstone3) {
			return Color.yellow;
		} else if (id == Tile.goldOverlay) {
			return Color.yellow;
		} else if (id == Tile.goldOverlay2) {
			return Color.yellow;
		} else if (id == Tile.goldOverlay3) {
			return Color.yellow;
		} else if (id == Tile.grassDecoration) {
			return Color.green;
		} else if (id == Tile.grassOverlay) {
			return Color.green;
		} else if (id == Tile.gravel) {
			return Color.gray;
		} else if (id == Tile.graveStone) {
			return Color.gray;
		} else if (id == Tile.hellStone) {
			return Color.red;
		} else if (id == Tile.hellStone2) {
			return Color.red;
		} else if (id == Tile.hellStone3) {
			return Color.red;
		} else if (id == Tile.hellStoneBlockChiseled) {
			return Color.red;
		} else if (id == Tile.hellStonePillar) {
			return Color.red;
		} else if (id == Tile.hellStonePillarChiseled) {
			return Color.red;
		} else if (id == Tile.hellStonePole) {
			return Color.red;
		} else if (id == Tile.hellStonePole2) {
			return Color.red;
		} else if (id == Tile.hellStonePoleWithBlock2) {
			return Color.red;
		} else if (id == Tile.hellStonePoleWithBlockOnTop) {
			return Color.red;
		} else if (id == Tile.hellStoneSlab) {
			return Color.red;
		} else if (id == Tile.hellStoneSmallDecoration) {
			return Color.red;
		} else if (id == Tile.hellStoneSmallDecoration2) {
			return Color.red;
		} else if (id == Tile.hellStoneStairs) {
			return Color.red;
		} else if (id == Tile.ironInSandstone) {
			return Color.gray;
		} else if (id == Tile.ironInSandstone2) {
			return Color.gray;
		} else if (id == Tile.ironInSandstone3) {
			return Color.gray;
		} else if (id == Tile.ironOverlay) {
			return Color.gray;
		} else if (id == Tile.ironOverlay2) {
			return Color.gray;
		} else if (id == Tile.ironOverlay3) {
			return Color.gray;
		} else if (id == Tile.lilies) {
			return Color.yellow;
		} else if (id == Tile.lilies2) {
			return Color.yellow;
		} else if (id == Tile.logPillar) {
			return new Color(127, 51, 0);
		} else if (id == Tile.logPillar2) {
			return new Color(127, 51, 0);
		} else if (id == Tile.mossOverlay) {
			return Color.green;
		} else if (id == Tile.mossOverlay2) {
			return Color.green;
		} else if (id == Tile.mossOverlay3) {
			return Color.green;
		} else if (id == Tile.mossOverlay4) {
			return Color.green;
		} else if (id == Tile.oakWoodPlanks) {
			return new Color(127, 51, 0);
		} else if (id == Tile.oakWoodSlab) {
			return new Color(127, 51, 0);
		} else if (id == Tile.oakWoodStair) {
			return new Color(127, 51, 0);
		} else if (id == Tile.obsidian) {
			return new Color(87, 0, 127);
		} else if (id == Tile.obsidian2) {
			return new Color(87, 0, 127);
		} else if (id == Tile.obsidian3) {
			return new Color(87, 0, 127);
		} else if (id == Tile.peat) {
			return new Color(127, 106, 0);
		} else if (id == Tile.pillarGrassOverlay) {
			return Color.green;
		} else if (id == Tile.pipe) {
			return Color.gray;
		} else if (id == Tile.pipeAfterInlet) {
			return Color.gray;
		} else if (id == Tile.pipeCorner) {
			return Color.gray;
		} else if (id == Tile.pipeInlet) {
			return Color.gray;
		} else if (id == Tile.roses) {
			return Color.red;
		} else if (id == Tile.roses2) {
			return Color.red;
		} else if (id == Tile.rubyInSandstone) {
			return Color.red;
		} else if (id == Tile.rubyInSandstone2) {
			return Color.red;
		} else if (id == Tile.rubyInSandstone3) {
			return Color.red;
		} else if (id == Tile.rubyOverlay) {
			return Color.red;
		} else if (id == Tile.rubyOverlay2) {
			return Color.red;
		} else if (id == Tile.rubyOverlay3) {
			return Color.red;
		} else if (id == Tile.sand) {
			return new Color(255, 216, 97);
		} else if (id == Tile.sandstone) {
			return new Color(255, 216, 97);
		} else if (id == Tile.sandstone2) {
			return new Color(255, 216, 97);
		} else if (id == Tile.sandstone3) {
			return new Color(255, 216, 97);
		} else if (id == Tile.sandstoneBrick) {
			return new Color(255, 216, 97);
		} else if (id == Tile.sandstonePillar) {
			return new Color(255, 216, 97);
		} else if (id == Tile.sandstoneSmallDecoration) {
			return new Color(255, 216, 97);
		} else if (id == Tile.sandstoneSmallDecoration2) {
			return new Color(255, 216, 97);
		} else if (id == Tile.smoothStonePillar) {
			return Color.gray;
		} else if (id == Tile.smoothStoneWall) {
			return Color.gray;
		} else if (id == Tile.smoothStoneWall2) {
			return Color.gray;
		} else if (id == Tile.smoothStoneWallChiseledBottom) {
			return Color.gray;
		} else if (id == Tile.smoothStoneWallChiseledLeft) {
			return Color.gray;
		} else if (id == Tile.smoothStoneWallChiseledMiddle) {
			return Color.gray;
		} else if (id == Tile.smoothStoneWallChiseledRight) {
			return Color.gray;
		} else if (id == Tile.smoothStoneWallChiseledTop) {
			return Color.gray;
		} else if (id == Tile.star) {
			return Color.yellow;
		} else if (id == Tile.stone) {
			return Color.gray;
		} else if (id == Tile.stone2) {
			return Color.gray;
		} else if (id == Tile.stone3) {
			return Color.gray;
		} else if (id == Tile.stoneSmallDecoration) {
			return Color.gray;
		} else if (id == Tile.stoneSmallDecoration2) {
			return Color.gray;
		} else if (id == Tile.tnt) {
			return Color.red;
		} else if (id == Tile.topHalfOfBed) {
			return Color.white;
		} else if (id == Tile.topHalfOfMessyBed) {
			return Color.red;
		} else if (id == Tile.vines) {
			return new Color(0, 127, 14);
		} else if (id == Tile.wall) {
			return Color.gray;
		} else if (id == Tile.wall2) {
			return Color.gray;
		} else if (id == Tile.wall3) {
			return Color.gray;
		} else if (id == Tile.wall4) {
			return Color.gray;
		} else if (id == Tile.wall5) {
			return Color.gray;
		} else if (id == Tile.water) {
			return Color.blue;
		} else if (id == Tile.woodenPole) {
			return new Color(127, 51, 0);
		} else if (id == Tile.woodenPole2) {
			return new Color(127, 51, 0);
		} else if (id == Tile.woodenPoleWithBlockOnTop) {
			return new Color(127, 51, 0);
		} else if (id == Tile.woodenPoleWithBlockOnTop2) {
			return new Color(127, 51, 0);
		}

		return new Color(0, 0, 0);
	}
}
