package com.aliquamgames.paradigm.playing;

import java.awt.image.BufferedImage;

import org.newdawn.slick.opengl.Texture;

import com.aliquamgames.paradigm.ImageLoader;

public class Tile {
	
	public static final int tileSize = 48;

	public static final byte invLength = 8;
	public static final byte invHeight = 4;
	public static final byte invCellSize = 60;
	public static final byte invCellSpace = 4;
	public static final byte invBorderSpace = 4;
	public static final byte invItemBorder = 3;

	public static final int[] air = { -1 - 1 };
	public static final int[] solidair = { 9, 12 };

	// blocks
	// row 1
	public static final int[] fire = { 0, 0 };
	public static final int[] fire2 = { 1, 0 };
	public static final int[] fire3 = { 2, 0 };
	public static final int[] fire4 = { 3, 0 };
	public static final int[] fire5 = { 4, 0 };
	public static final int[] dirt = { 5, 0 };
	public static final int[] dirtWithGrass = { 6, 0 };
	public static final int[] sandstone = { 7, 0 };
	public static final int[] sandstone2 = { 8, 0 };
	public static final int[] sandstone3 = { 9, 0 };
	public static final int[] vines = { 10, 0 };
	public static final int[] sandstoneSmallDecoration = { 11, 0 };
	public static final int[] sandstoneSmallDecoration2 = { 12, 0 };
	public static final int[] sandstonePillar = { 13, 0 };
	public static final int[] gravel = { 14, 0 };

	// row 2
	public static final int[] fire6 = { 0, 1 };
	public static final int[] fire7 = { 1, 1 };
	public static final int[] fire8 = { 2, 1 };
	public static final int[] fire9 = { 3, 1 };
	public static final int[] fire10 = { 4, 1 };
	public static final int[] sand = { 5, 1 };
	public static final int[] obsidian = { 6, 1 };
	public static final int[] obsidian2 = { 7, 1 };
	public static final int[] obsidian3 = { 8, 1 };
	public static final int[] grassDecoration = { 9, 1 };
	public static final int[] oakWoodPlanks = { 10, 1 };
	public static final int[] chest = { 11, 1 };
	public static final int[] copperInSandstone = { 12, 1 };
	public static final int[] copperInSandstone2 = { 13, 1 };
	public static final int[] copperInSandstone3 = { 14, 1 };

	// row 3
	public static final int[] fire11 = { 0, 2 };
	public static final int[] fireWithBase = { 1, 2 }; // fire frame 12
	public static final int[] fireWithBase2 = { 2, 2 };
	public static final int[] fireWithBase3 = { 3, 2 };
	public static final int[] fireWithBase4 = { 4, 2 };
	public static final int[] grassOverlay = { 5, 2 };
	public static final int[] mossOverlay = { 6, 2 };
	public static final int[] mossOverlay2 = { 7, 2 };
	public static final int[] mossOverlay3 = { 8, 2 };
	public static final int[] mossOverlay4 = { 9, 2 };
	public static final int[] chiseledSandstonePillar = { 10, 2 };
	public static final int[] chiseledSandstone = { 11, 2 };
	public static final int[] diamondInSandstone = { 12, 2 };
	public static final int[] diamondInSandstone2 = { 13, 2 };
	public static final int[] diamondInSandstone3 = { 14, 2 };

	// row 4
	public static final int[] fireWithBase5 = { 0, 3 };
	public static final int[] fireWithBase6 = { 1, 3 };
	public static final int[] fireWithBase7 = { 2, 3 };
	public static final int[] fireWithBase8 = { 3, 3 };
	public static final int[] fireWithBase9 = { 4, 3 };
	public static final int[] breaking = { 5, 3 };
	public static final int[] breaking2 = { 6, 3 };
	public static final int[] breaking3 = { 7, 3 };
	public static final int[] breaking4 = { 8, 3 };
	public static final int[] breaking5 = { 9, 3 };
	public static final int[] breaking6 = { 10, 3 };
	public static final int[] breaking7 = { 11, 3 };
	public static final int[] breaking8 = { 12, 3 };
	public static final int[] breaking9 = { 13, 3 };
	public static final int[] breaking10 = { 14, 3 };

	// row 5
	public static final int[] fireWithBase10 = { 0, 4 };
	public static final int[] fireWithBase11 = { 1, 4 };
	public static final int[] fireWithBase12 = { 2, 4 };
	public static final int[] fireWithBase13 = { 3, 4 };
	public static final int[] smoke = { 4, 4 };
	public static final int[] door = { 5, 4 };
	public static final int[] doorTopOpen = { 6, 4 };
	public static final int[] doorBottomOpen = { 7, 4 };
	public static final int[] peat = { 8, 4 };
	public static final int[] coalInSandstone = { 9, 4 };
	public static final int[] coalInSandstone2 = { 10, 4 };
	public static final int[] coalInSandstone3 = { 11, 4 };
	public static final int[] ironInSandstone = { 12, 4 };
	public static final int[] ironInSandstone2 = { 13, 4 };
	public static final int[] ironInSandstone3 = { 14, 4 };

	// row 6
	public static final int[] smoke2 = { 0, 5 };
	public static final int[] smoke3 = { 1, 5 };
	public static final int[] smoke4 = { 2, 5 };
	public static final int[] smoke5 = { 3, 5 };
	public static final int[] smoke6 = { 4, 5 };
	public static final int[] star = { 5, 5 };
	public static final int[] goldInSandstone = { 6, 5 };
	public static final int[] goldInSandstone2 = { 7, 5 };
	public static final int[] goldInSandstone3 = { 8, 5 };
	public static final int[] arythimilInSandstone = { 9, 5 };
	public static final int[] arythimilInSandstone2 = { 10, 5 };
	public static final int[] arythimilInSandstone3 = { 11, 5 };
	public static final int[] emeraldInSandstone = { 12, 5 };
	public static final int[] emeraldInSandstone2 = { 13, 5 };
	public static final int[] emeraldInSandstone3 = { 14, 5 };

	// row 7
	public static final int[] rubyInSandstone = { 0, 6 };
	public static final int[] rubyInSandstone2 = { 1, 6 };
	public static final int[] rubyInSandstone3 = { 2, 6 };
	public static final int[] cobaltInSandstone = { 3, 6 };
	public static final int[] cobaltInSandstone2 = { 4, 6 };
	public static final int[] cobaltInSandstone3 = { 5, 6 };
	public static final int[] craftingTable = { 6, 6 };
	public static final int[] furnaceOff = { 7, 6 };
	public static final int[] furnaceOn = { 8, 6 };
	public static final int[] topHalfOfBed = { 9, 6 };
	public static final int[] bottomHalfOfBed = { 10, 6 };
	public static final int[] topHalfOfMessyBed = { 11, 6 };
	public static final int[] bottomHalfOfMessyBed = { 12, 6 };
	public static final int[] oakWoodStair = { 13, 6 };
	public static final int[] oakWoodSlab = { 14, 6 };

	// row 8
	public static final int[] roses = { 0, 7 };
	public static final int[] lilies = { 1, 7 };
	public static final int[] roses2 = { 2, 7 };
	public static final int[] lilies2 = { 3, 7 };
	public static final int[] woodenPole = { 4, 7 };
	public static final int[] woodenPoleWithBlockOnTop = { 5, 7 };
	public static final int[] woodenPole2 = { 6, 7 };
	public static final int[] woodenPoleWithBlockOnTop2 = { 7, 7 };
	public static final int[] smoothStoneWall = { 8, 7 };
	public static final int[] smoothStoneWall2 = { 9, 7 };
	public static final int[] smoothStoneWallChiseledTop = { 10, 7 };
	public static final int[] smoothStoneWallChiseledBottom = { 11, 7 };
	public static final int[] smoothStoneWallChiseledLeft = { 12, 7 };
	public static final int[] smoothStoneWallChiseledMiddle = { 13, 7 };
	public static final int[] smoothStoneWallChiseledRight = { 14, 7 };

	// row 9
	public static final int[] pipeCorner = { 0, 8 };
	public static final int[] pipe = { 1, 8 };
	public static final int[] pipeInlet = { 2, 8 };
	public static final int[] pipeAfterInlet = { 3, 8 };
	public static final int[] stone = { 4, 8 };
	public static final int[] stone2 = { 5, 8 };
	public static final int[] stone3 = { 6, 8 };
	public static final int[] copperOverlay = { 7, 8 };
	public static final int[] copperOverlay2 = { 8, 8 };
	public static final int[] copperOverlay3 = { 9, 8 };
	public static final int[] diamondOverlay = { 10, 8 };
	public static final int[] diamondOverlay2 = { 11, 8 };
	public static final int[] diamondOverlay3 = { 12, 8 };
	public static final int[] logPillar = { 13, 8 };
	public static final int[] logPillar2 = { 14, 8 };

	// row 10
	public static final int[] coalOverlay = { 0, 9 };
	public static final int[] coalOverlay2 = { 1, 9 };
	public static final int[] coalOverlay3 = { 2, 9 };
	public static final int[] ironOverlay = { 3, 9 };
	public static final int[] ironOverlay2 = { 4, 9 };
	public static final int[] ironOverlay3 = { 5, 9 };
	public static final int[] goldOverlay = { 6, 9 };
	public static final int[] goldOverlay2 = { 7, 9 };
	public static final int[] goldOverlay3 = { 8, 9 };
	public static final int[] arythimilOverlay = { 9, 9 };
	public static final int[] arythimilOverlay2 = { 10, 9 };
	public static final int[] arythimilOverlay3 = { 11, 9 };
	public static final int[] emeraldOverlay = { 12, 9 };
	public static final int[] emeraldOverlay2 = { 13, 9 };
	public static final int[] emeraldOverlay3 = { 14, 9 };

	// row 11
	public static final int[] rubyOverlay = { 0, 10 };
	public static final int[] rubyOverlay2 = { 1, 10 };
	public static final int[] rubyOverlay3 = { 2, 10 };
	public static final int[] cobaltOverlay = { 3, 10 };
	public static final int[] cobaltOverlay2 = { 4, 10 };
	public static final int[] cobaltOverlay3 = { 5, 10 };
	public static final int[] pillarGrassOverlay = { 6, 10 };
	public static final int[] bridgeHolderUnderlay = { 7, 10 };
	public static final int[] bridge = { 8, 10 };
	public static final int[] graveStone = { 9, 10 };
	public static final int[] chiseledStoneBlock = { 10, 10 };
	public static final int[] stoneSmallDecoration = { 11, 10 };
	public static final int[] stoneSmallDecoration2 = { 12, 10 };
	public static final int[] smoothStonePillar = { 13, 10 };
	public static final int[] wall = { 14, 10 };

	// row 12
	public static final int[] wall2 = { 0, 11 };
	public static final int[] wall3 = { 1, 11 };
	public static final int[] wall4 = { 2, 11 };
	public static final int[] wall5 = { 3, 11 };
	public static final int[] sandstoneBrick = { 4, 11 };
	public static final int[] glass = { 5, 11 };
	public static final int[] hellStone = { 6, 11 };
	public static final int[] hellStone2 = { 7, 11 };
	public static final int[] hellStone3 = { 8, 11 };
	public static final int[] hellStoneSmallDecoration = { 9, 11 };
	public static final int[] hellStoneSmallDecoration2 = { 10, 11 };
	public static final int[] hellStonePillar = { 11, 11 };
	public static final int[] hellStonePillarChiseled = { 12, 11 };
	public static final int[] hellStoneBlockChiseled = { 13, 11 };
	public static final int[] hellStoneStairs = { 14, 11 };

	// row 13
	public static final int[] hellStoneSlab = { 0, 12 };
	public static final int[] hellStonePole = { 1, 12 };
	public static final int[] hellStonePoleWithBlockOnTop = { 2, 12 };
	public static final int[] hellStonePole2 = { 3, 12 };
	public static final int[] hellStonePoleWithBlock2 = { 4, 12 };
	public static final int[] anvil = { 5, 12 };
	public static final int[] dirtLight = { 6, 12 };
	public static final int[] dirtLightWithSnow = { 7, 12 };
	public static final int[] books = { 8, 12 };
	public static final int[] bookShelf = { 9, 12 };//	HERE
	public static final int[] bedrock = { 10, 12 };
	public static final int[] bedrock2 = { 11, 12 };
	public static final int[] bedrock3 = { 12, 12 };
	public static final int[] tnt = { 13, 12 };
	public static final int[] sandStoneSlab = { 14, 12 };

	// row 14
	public static final int[] water = { 1, 13 };

	// items
	public static final int[] egg = { 9, 0 };

	// blocks
	public static final int[][] total = { air, solidair, fire, fire2, fire3, fire4, fire5, dirt, dirtWithGrass, sandstone, sandstone2, sandstone3, vines, sandstoneSmallDecoration, sandstoneSmallDecoration2, sandstonePillar, gravel, fire6, fire7, fire8, fire9, fire10, sand, obsidian, obsidian2, obsidian3, grassDecoration, oakWoodPlanks, chest, copperInSandstone, copperInSandstone2, copperInSandstone3, fire11, fireWithBase, fireWithBase2, fireWithBase4, fireWithBase4, grassOverlay, mossOverlay, mossOverlay2, mossOverlay3, mossOverlay4, chiseledSandstonePillar, chiseledSandstone, diamondInSandstone, diamondInSandstone2, diamondInSandstone3, fireWithBase5, fireWithBase6, fireWithBase7, fireWithBase8, fireWithBase9, breaking, breaking2, breaking3, breaking4, breaking5, breaking6, breaking7, breaking8, breaking9, breaking10, fireWithBase10, fireWithBase11, fireWithBase12, fireWithBase13, smoke, door, doorTopOpen, doorBottomOpen, peat, coalInSandstone, coalInSandstone2, coalInSandstone3, ironInSandstone, ironInSandstone2, ironInSandstone3, smoke2, smoke3, smoke4, smoke5, smoke6, star, goldInSandstone, goldInSandstone2, goldInSandstone3, arythimilInSandstone, arythimilInSandstone2,
			arythimilInSandstone3, emeraldInSandstone, emeraldInSandstone2, emeraldInSandstone3, rubyInSandstone, rubyInSandstone2, rubyInSandstone3, cobaltInSandstone, cobaltInSandstone2, cobaltInSandstone3, craftingTable, furnaceOff, furnaceOn, topHalfOfBed, bottomHalfOfBed, topHalfOfMessyBed, bottomHalfOfMessyBed, oakWoodStair, oakWoodSlab, roses, lilies, roses2, lilies2, woodenPole, woodenPoleWithBlockOnTop, woodenPole2, woodenPoleWithBlockOnTop2, smoothStoneWall, smoothStoneWall2, smoothStoneWallChiseledTop, smoothStoneWallChiseledBottom, smoothStoneWallChiseledLeft, smoothStoneWallChiseledMiddle, smoothStoneWallChiseledRight, pipeCorner, pipe, pipeInlet, pipeAfterInlet, stone, stone2, stone3, copperOverlay, copperOverlay2, copperOverlay3, diamondOverlay, diamondOverlay2, diamondOverlay3, logPillar, logPillar2, coalOverlay, coalOverlay2, coalOverlay3, ironOverlay, ironOverlay2, ironOverlay3, goldOverlay, goldOverlay2, goldOverlay3, arythimilOverlay, arythimilOverlay2, arythimilOverlay3, emeraldOverlay, emeraldOverlay2, emeraldOverlay3, rubyOverlay, rubyOverlay2, rubyOverlay3, cobaltOverlay, cobaltOverlay2, cobaltOverlay3, pillarGrassOverlay, bridgeHolderUnderlay, bridge,
			graveStone, chiseledStoneBlock, stoneSmallDecoration, stoneSmallDecoration2, smoothStonePillar, wall, wall2, wall3, wall4, wall5, sandstoneBrick, glass, hellStone, hellStone2, hellStone3, hellStoneSmallDecoration, hellStoneSmallDecoration2, hellStonePillar, hellStonePillarChiseled, hellStoneBlockChiseled, hellStoneStairs, hellStoneSlab, hellStonePole, hellStonePoleWithBlockOnTop, hellStonePole2, hellStonePoleWithBlock2, anvil, dirtLight, dirtLightWithSnow, books, bookShelf, bedrock, bedrock2, bedrock3, tnt, water };
	// items
	public static final int[][] total2 = { Tile.egg, };

	public static final int numberOfBlocks = total.length;
	public static final int numberOfItems = total2.length;

	public static int[][] wheat = { Tile.dirt, Tile.stone, Tile.anvil, Tile.copperInSandstone2 };// testing

	// row 14
	// public static final int[] xxx = { 0, 13 };

	// row 15
	// public static final int[] xxx = { 0, 14 }

	public static final int[][] fireAnimationSwap = { fire, fire2, fire3, fire4, fire5, fire6, fire7, fire8, fire9, fire10, fire11, fireWithBase };
	public static final int[][] fireWithBaseAnimationSwap = { fireWithBase2, fireWithBase3, fireWithBase4, fireWithBase5, fireWithBase6, fireWithBase7, fireWithBase8, fireWithBase9, fireWithBase10, fireWithBase11, fireWithBase12, fireWithBase13 };
	public static final int[][] smokeAnimationSwap = { smoke, smoke2, smoke3, smoke4, smoke5, smoke6 };
	public static final int[][] portal1AnimationSwap = { smoke, smoke2, smoke3, smoke4, smoke5, smoke6 };

	public static final int[][][] crops = { wheat };
	// public static final int[][] crops = { wheat[0] };

	public static Texture tile_cell;
	public static Texture tile_select;
	public static Texture terrain;
	public static Texture items;

	public static BufferedImage Bheart;
	public static BufferedImage Bheart_damaged;
	public static BufferedImage Bhalf_heart;
	public static BufferedImage Barmor;
	public static BufferedImage Barmor_damage;
	public static BufferedImage Bhalf_armor;
	public static BufferedImage Bhunger;
	public static BufferedImage Bhunger_damage;
	public static BufferedImage Bhalf_hunger;

	public static Texture heart;
	public static Texture heart_damaged;
	public static Texture half_heart;
	public static Texture armor;
	public static Texture armor_damage;
	public static Texture half_armor;
	public static Texture hunger;
	public static Texture hunger_damage;
	public static Texture half_hunger;

	public static Texture sun;
	public static Texture moon;
	public static Texture mountainLoop1;
	public static Texture mountainLoop2;
	public static Texture mountainSnowLoop1;
	public static Texture mountainSnowLoop2;

	public static Texture font;

	public Tile() {
		System.out.println("Number Of Blocks: " + numberOfBlocks);
		System.out.println("Number Of Items: " + numberOfItems);
		try {
			System.out.println("TILE: Loading Images...");
			Tile.terrain = ImageLoader.loadSlick("PNG", "/tilesets/tileset_terrain.png");
			Tile.items = ImageLoader.loadSlick("PNG", "/tilesets/tileset_items.png");
			Tile.tile_cell = ImageLoader.loadSlick("PNG", "/inventory/tile_cell.png");
			Tile.tile_select = ImageLoader.loadSlick("PNG", "/inventory/tile_select.png");
			// if(Bheart != null) Tile.Bheart = ImageLoader.loadJava("/GUI/icons/heart.png", false);
			// if(Bheart_damaged != null) Tile.Bheart_damaged = ImageLoader.loadJava("/GUI/icons/heart_damage.png", false);
			// if(Barmor != null) Tile.Barmor = ImageLoader.loadJava("/GUI/icons/armor.png", false);
			// if(Barmor_damage != null) Tile.Barmor_damage = ImageLoader.loadJava("/GUI/icons/armor_damage.png", false);
			// if(Bhunger != null) Tile.Bhunger = ImageLoader.loadJava("/GUI/icons/hunger.png", false);
			// if(Bhunger_damage != null) Tile.Bhunger_damage = ImageLoader.loadJava("/GUI/icons/hunger_damaged.png", false);
			Tile.heart = ImageLoader.loadSlick("PNG", "/GUI/icons/heart.png");
			Tile.heart_damaged = ImageLoader.loadSlick("PNG", "/GUI/icons/heart_damage.png");
			Tile.armor = ImageLoader.loadSlick("PNG", "/GUI/icons/armor.png");
			Tile.armor_damage = ImageLoader.loadSlick("PNG", "/GUI/icons/armor_damage.png");
			Tile.hunger = ImageLoader.loadSlick("PNG", "/GUI/icons/hunger.png");
			Tile.hunger_damage = ImageLoader.loadSlick("PNG", "/GUI/icons/hunger_damaged.png");
			Tile.sun = ImageLoader.loadSlick("PNG", "/scenery/sun.png");
			Tile.moon = ImageLoader.loadSlick("PNG", "/scenery/moon.png");
			Tile.mountainLoop1 = ImageLoader.loadSlick("PNG", "/scenery/backgrounds/Mountain_Loop1.png");
			Tile.mountainLoop2 = ImageLoader.loadSlick("PNG", "/scenery/backgrounds/Mountain_Loop2.png");
			Tile.mountainSnowLoop1 = ImageLoader.loadSlick("PNG", "/scenery/backgrounds/Mountain_Snow_Loop1.png");
			Tile.mountainSnowLoop2 = ImageLoader.loadSlick("PNG", "/scenery/backgrounds/Mountain_Snow_Loop2.png");
			Tile.font = ImageLoader.loadSlick("PNG", "/fonts/font.png");
			Tile.half_armor = ImageLoader.loadSlick("PNG", "/GUI/icons/half_armor.png");
			Tile.half_heart = ImageLoader.loadSlick("PNG", "/GUI/icons/half_heart.png");
			Tile.half_hunger = ImageLoader.loadSlick("PNG", "/GUI/icons/half_hunger.png");

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("TILE: Failed To Load Tile Images!");
		}
	}

	public static int[] getBlock(int number) {
		if (number < total.length) {
			return total[number];
		}
		return total[0];
	}

}
