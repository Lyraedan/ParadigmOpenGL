package com.aliquamgames.paradigm.blocks;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Rectangle;

import com.aliquamgames.paradigm.Core;
import com.aliquamgames.paradigm.playing.Tile;
import com.aliquamgames.paradigm.util.Util;

/**
 * 
 * @author Luke Rapkin
 */
public class Block {

	/** PIPE STUFF */
	public boolean rightOutput = false;
	public boolean leftOutput = false;
	public boolean upOutput = false;
	public boolean downOutput = false;
	public boolean rightInput = false;
	public boolean leftInput = false;
	public boolean upInput = false;
	public boolean downInput = false;
	public boolean hasRunningWater = false;
	/** PIPE STUFF */
	
	public boolean isChestOpen = false;

	public Rectangle rect;

	public int[] id = { -1, -1 };
	public int[][] id2 = { id };
	public int[][][] id3 = { id2 };
	// either way they return originals id
	public int strength;
	public int darkness = 0;
	public String name = "Unnamed";

	public boolean tntTriggered = false;
	public boolean remove = false;
	public boolean discovered = false;

	public static enum Rotation {
		ROTATE90, ROTATE180, ROTATE270, NONE
	}

	public Rotation rotation = Rotation.NONE;

	public int spotX = 0;
	public int spotY = 0;

	public Block(Rectangle size, int[] id, int strength, int spotX, int spotY, String name) {
		this.rect = size;
		this.id = id;
		this.strength = strength;
		this.spotX = spotX;
		this.spotY = spotY;
		this.name = name;
	}

	public Block() {

	}

	@SuppressWarnings("static-access")
	public void syncBlockClasses(int[] id, int x, int y) {
		if (id == Tile.anvil) Core.level.block[x][y] = (new Anvil(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.anvil, 600, x, y, "Anvil"));
		else if (id == Tile.arythimilInSandstone || id == Tile.arythimilInSandstone2 || id == Tile.arythimilInSandstone3) Core.level.block[x][y] = (new ArythimiInSandStone(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.arythimilInSandstone, 600, x, y, "Arythimil Ore"));
		else if (id == Tile.arythimilOverlay || id == Tile.arythimilOverlay2 || id == Tile.arythimilOverlay3) Core.level.block[x][y] = (new ArythimiOverlay(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.arythimilOverlay, 600, x, y, "Arythimil Ore"));
		else if (id == Tile.bottomHalfOfBed || id == Tile.topHalfOfBed || id == Tile.bottomHalfOfMessyBed || id == Tile.topHalfOfMessyBed) Core.level.block[x][y] = (new Bed(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.bottomHalfOfBed, 50, x, y, "Bed"));
		else if (id == Tile.books) Core.level.block[x][y] = (new Books(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.books, 200, x, y, "Book"));
		else if (id == Tile.bookShelf) Core.level.block[x][y] = (new BookShelf(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.bookShelf, 300, x, y, "Bookshelf"));
		else if (id == Tile.bridge) Core.level.block[x][y] = (new Bridge(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.bridge, 300, x, y, "Bridge"));
		else if (id == Tile.bridgeHolderUnderlay) Core.level.block[x][y] = (new BridgeHolderUnderlay(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.bridgeHolderUnderlay, 200, x, y, "Bridge Holder Underlay"));
		else if (id == Tile.chest) Core.level.block[x][y] = (new Chest(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.chest, 200, x, y, "Chest"));
		else if (id == Tile.chiseledSandstone) Core.level.block[x][y] = (new ChiseledSandStone(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.chiseledSandstone, 600, x, y, "Chiseled Sandstone"));
		else if (id == Tile.chiseledSandstonePillar) Core.level.block[x][y] = (new ChiseledSandStonePillar(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.chiseledSandstonePillar, 600, x, y, "Chiseled Sandstone Pillar"));
		else if (id == Tile.chiseledStoneBlock) Core.level.block[x][y] = (new ChiseledStoneBlock(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.chiseledStoneBlock, 600, x, y, "Chiseled Sandstone Block"));
		else if (id == Tile.coalInSandstone || id == Tile.coalInSandstone2 || id == Tile.coalInSandstone3) Core.level.block[x][y] = (new CoalInSandStone(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.coalInSandstone, 600, x, y, "Coal Ore"));
		else if (id == Tile.coalOverlay || id == Tile.coalOverlay2 || id == Tile.coalOverlay3) Core.level.block[x][y] = (new CoalOverlay(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.coalOverlay, 600, x, y, "Coal Ore"));
		else if (id == Tile.cobaltInSandstone || id == Tile.cobaltInSandstone2 || id == Tile.cobaltInSandstone3) Core.level.block[x][y] = (new CobaltInSandStone(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.cobaltInSandstone, 600, x, y, "Cobalt In Sandstone"));
		else if (id == Tile.cobaltOverlay || id == Tile.cobaltOverlay2 || id == Tile.cobaltOverlay3) Core.level.block[x][y] = (new CobaltOverlay(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.cobaltOverlay, 600, x, y, "Cobalt Ore"));
		else if (id == Tile.copperInSandstone || id == Tile.copperInSandstone2 || id == Tile.copperInSandstone3) Core.level.block[x][y] = (new CopperInSandStone(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.copperInSandstone, 600, x, y, "Copper In Sandstone"));
		else if (id == Tile.copperOverlay || id == Tile.copperOverlay2 || id == Tile.copperOverlay3) Core.level.block[x][y] = (new CopperOverlay(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.copperOverlay, 600, x, y, "Copper Ore"));
		else if (id == Tile.craftingTable) Core.level.block[x][y] = (new CraftingTable(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.craftingTable, 200, x, y, "Crafting Table"));
		else if (id == Tile.diamondInSandstone || id == Tile.diamondInSandstone2 || id == Tile.diamondInSandstone3) Core.level.block[x][y] = (new DiamondInSandStone(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.diamondInSandstone, 600, x, y, "Diamond In Sandstone"));
		else if (id == Tile.diamondOverlay || id == Tile.diamondOverlay2 || id == Tile.diamondOverlay3) Core.level.block[x][y] = (new DiamondOverlay(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.diamondOverlay, 600, x, y, "Diamond Ore"));
		else if (id == Tile.dirt) Core.level.block[x][y] = (new Dirt(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.dirt, 100, x, y, "Dirt"));
		else if (id == Tile.dirtLight) Core.level.block[x][y] = (new DirtLight(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.dirtLight, 100, x, y, "Dirt"));
		else if (id == Tile.dirtLightWithSnow) Core.level.block[x][y] = (new DirtLightWithSnow(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.dirtLightWithSnow, 100, x, y, "Snowy Grass"));
		else if (id == Tile.door || id == Tile.doorBottomOpen || id == Tile.doorTopOpen) Core.level.block[x][y] = (new Door(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.door, 125, x, y, "Door"));
		else if (id == Tile.emeraldInSandstone || id == Tile.emeraldInSandstone2 || id == Tile.emeraldInSandstone3) Core.level.block[x][y] = (new EmeraldInSandStone(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.emeraldInSandstone, 600, x, y, "Emerald In Sandstone"));
		else if (id == Tile.emeraldOverlay || id == Tile.emeraldOverlay2 || id == Tile.emeraldOverlay3) Core.level.block[x][y] = (new EmeraldOverlay(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.emeraldOverlay, 600, x, y, "Emerald Ore"));
		else if (id == Tile.roses || id == Tile.roses2 || id == Tile.lilies || id == Tile.lilies2) Core.level.block[x][y] = (new Flowers(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.roses, 10, x, y, "Rose"));
		else if (id == Tile.furnaceOn || id == Tile.furnaceOff) Core.level.block[x][y] = (new Furnace(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.furnaceOff, 250, x, y, "Furnace"));
		else if (id == Tile.glass) Core.level.block[x][y] = (new Glass(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.glass, 175, x, y, "Glass"));
		else if (id == Tile.goldInSandstone || id == Tile.goldInSandstone2 || id == Tile.goldInSandstone3) Core.level.block[x][y] = (new GoldInSandStone(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.goldInSandstone, 600, x, y, "Gold In Sandstone"));
		else if (id == Tile.goldOverlay || id == Tile.goldOverlay2 || id == Tile.goldOverlay3) Core.level.block[x][y] = (new GoldOverlay(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.goldOverlay, 600, x, y, "Gold Ore"));
		else if (id == Tile.dirtWithGrass) Core.level.block[x][y] = (new Grass(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.dirtWithGrass, 100, x, y, "Grass"));
		else if (id == Tile.grassDecoration) Core.level.block[x][y] = (new GrassDecoration(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.grassDecoration, 10, x, y, "Grass"));
		else if (id == Tile.grassOverlay) Core.level.block[x][y] = (new GrassOverlay(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.grassOverlay, 100, x, y, "Grass"));
		else if (id == Tile.gravel) Core.level.block[x][y] = (new Gravel(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.gravel, 90, x, y, "Gravel"));
		else if (id == Tile.graveStone) Core.level.block[x][y] = (new GraveStone(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.graveStone, 200, x, y, "Grave Stone"));
		else if (id == Tile.hellStone) Core.level.block[x][y] = (new HellStone(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.hellStone, 600, x, y, "Hell Stone"));
		else if (id == Tile.hellStoneBlockChiseled) Core.level.block[x][y] = (new HellStoneBlockChiseled(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.hellStoneBlockChiseled, 600, x, y, "Hell Stone Block Chiseled"));
		else if (id == Tile.hellStonePillar) Core.level.block[x][y] = (new HellStonePillar(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.hellStonePillar, 600, x, y, "Hell Stone Pillar"));
		else if (id == Tile.hellStonePillarChiseled) Core.level.block[x][y] = (new HellStonePillarChiseled(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.hellStonePillarChiseled, 600, x, y, "Hell Stone Pillar Chiseled"));
		else if (id == Tile.hellStonePole || id == Tile.hellStonePole2) Core.level.block[x][y] = (new HellStonePole(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.hellStonePole, 225, x, y, "Hell Stone Pole"));
		else if (id == Tile.hellStonePoleWithBlockOnTop || id == Tile.hellStonePoleWithBlock2) Core.level.block[x][y] = (new HellStonePoleWithBlockOnTop(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.hellStonePoleWithBlockOnTop, 225, x, y, "Hell Stone Pole"));
		else if (id == Tile.hellStoneSlab) Core.level.block[x][y] = (new HellStoneSlab(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.hellStoneSlab, 400, x, y, "Hell Stone Slab"));
		else if (id == Tile.hellStoneSmallDecoration || id == Tile.hellStoneSmallDecoration2) Core.level.block[x][y] = (new HellStoneSmallDecoration(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.hellStoneSmallDecoration, 600, x, y, "Hell Stone Small Decoration"));
		else if (id == Tile.hellStoneStairs) Core.level.block[x][y] = (new HellStoneStairs(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.hellStoneStairs, 300, x, y, "Hell Stone Stairs"));
		else if (id == Tile.ironInSandstone || id == Tile.ironInSandstone2 || id == Tile.ironInSandstone3) Core.level.block[x][y] = (new IronInSandStone(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.ironInSandstone, 600, x, y, "Iron In Sandstone"));
		else if (id == Tile.ironOverlay || id == Tile.ironOverlay2 || id == Tile.ironOverlay3) Core.level.block[x][y] = (new IronOverlay(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.ironOverlay, 600, x, y, "Iron Ore"));
		else if (id == Tile.logPillar || id == Tile.logPillar2) Core.level.block[x][y] = (new LogPillar(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.logPillar, 300, x, y, "Log Pillar"));
		else if (id == Tile.mossOverlay || id == Tile.mossOverlay2 || id == Tile.mossOverlay3 || id == Tile.mossOverlay4) Core.level.block[x][y] = (new MossOverlay(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.mossOverlay, 100, x, y, "Moss"));
		else if (id == Tile.oakWoodPlanks) Core.level.block[x][y] = (new OakWoodPlanks(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.oakWoodPlanks, 300, x, y, "Oak Wood Planks"));
		else if (id == Tile.oakWoodSlab) Core.level.block[x][y] = (new OakWoodSlab(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.oakWoodSlab, 400, x, y, "Oak Wood Slab"));
		else if (id == Tile.oakWoodStair) Core.level.block[x][y] = (new OakWoodStair(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.oakWoodStair, 300, x, y, "Oak Wood Stair"));
		else if (id == Tile.obsidian || id == Tile.obsidian2 || id == Tile.obsidian3) Core.level.block[x][y] = (new Obsidian(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.obsidian, 1000, x, y, "Obsidian"));
		else if (id == Tile.peat) Core.level.block[x][y] = (new Peat(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.peat, 400, x, y, "Peat"));
		else if (id == Tile.pillarGrassOverlay) Core.level.block[x][y] = (new PillarGrassOverlay(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.pillarGrassOverlay, 100, x, y, "Grass on Pillar"));
		else if (id == Tile.pipe || id == Tile.pipeAfterInlet || id == Tile.pipeCorner || id == Tile.pipeInlet) {
			Core.level.block[x][y] = (new Pipe(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.pipe, 600, x, y, "Pipe"));
			Core.level.block[x][y].upInput = true;
			Core.level.block[x][y].downOutput = true;
		} else if (id == Tile.rubyInSandstone || id == Tile.rubyInSandstone2 || id == Tile.rubyInSandstone3) Core.level.block[x][y] = (new RubyInSandStone(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.rubyInSandstone, 600, x, y, "Ruby"));
		else if (id == Tile.rubyOverlay || id == Tile.rubyOverlay2 || id == Tile.rubyOverlay3) Core.level.block[x][y] = (new RubyOverlay(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.rubyOverlay, 600, x, y, "Ruby"));
		else if (id == Tile.sand) Core.level.block[x][y] = (new Sand(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.sand, 90, x, y, "Sand"));
		else if (id == Tile.sandstone || id == Tile.sandstone2 || id == Tile.sandstone3) Core.level.block[x][y] = (new SandStone(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.sandstone, 590, x, y, "Sandstone"));
		else if (id == Tile.sandstoneBrick) Core.level.block[x][y] = (new SandStoneBrick(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.sandstoneBrick, 590, x, y, "Sandstone Brick"));
		else if (id == Tile.sandstoneSmallDecoration || id == Tile.sandstoneSmallDecoration2) Core.level.block[x][y] = (new StoneSmallDecoration(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.sandstoneSmallDecoration, 590, x, y, "Sandstone Decoration"));
		else if (id == Tile.smoothStonePillar) Core.level.block[x][y] = (new SmoothStonePillar(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.smoothStonePillar, 590, x, y, "Smooth Stone Pillar"));
		else if (id == Tile.smoothStoneWall || id == Tile.smoothStoneWall2) Core.level.block[x][y] = (new SmoothStoneWall(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.smoothStoneWall, 590, x, y, "Smooth Stone Wall"));
		else if (id == Tile.smoothStoneWallChiseledBottom || id == Tile.smoothStoneWallChiseledLeft || id == Tile.smoothStoneWallChiseledRight || id == Tile.smoothStoneWallChiseledMiddle || id == Tile.smoothStoneWallChiseledTop) Core.level.block[x][y] = (new SmoothStoneWallChiseled(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.smoothStoneWallChiseledBottom, 590, x, y, "Chiseled Smooth Stone Wall"));
		else if (id == Tile.star) Core.level.block[x][y] = (new Star(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.star, 0, x, y, "Star"));
		else if (id == Tile.stone || id == Tile.stone2 || id == Tile.stone3) Core.level.block[x][y] = (new Stone(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.stone, 590, x, y, "Stone"));
		else if (id == Tile.stoneSmallDecoration || id == Tile.stoneSmallDecoration2) Core.level.block[x][y] = (new StoneSmallDecoration(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.stoneSmallDecoration, 590, x, y, "Stone Decoration"));
		else if (id == Tile.tnt) Core.level.block[x][y] = (new TNT(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.tnt, 50, x, y, "TNT"));
		else if (id == Tile.wall || id == Tile.wall2 || id == Tile.wall3 || id == Tile.wall4 || id == Tile.wall5) Core.level.block[x][y] = (new Wall(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.wall, 590, x, y, "Wall"));
		else if (id == Tile.woodenPole || id == Tile.woodenPole2) Core.level.block[x][y] = (new WoodenPole(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.woodenPole, 225, x, y, "Wooden Pole"));
		else if (id == Tile.woodenPoleWithBlockOnTop || id == Tile.woodenPoleWithBlockOnTop2) Core.level.block[x][y] = (new WoodenPoleWithBlockOnTop(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.woodenPoleWithBlockOnTop, 225, x, y, "Wooden Pole"));
		else if (id == Tile.water) Core.level.block[x][y] = (new Water(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.water, 225, x, y, "Water"));
		else if (id == Tile.sandStoneSlab) Core.level.block[x][y] = (new SandStoneSlab(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.sandStoneSlab, 225, x, y, "SandStone Slab"));
		// TODO ADD CROPS
		else System.err.println("BLOCK.SYNCBLOCKCLASSES: THE ID GIVEN DOES NOT HAVE A CLASS ASSOCIATED WITH IT");
	}

	public void animate() {

	}

	public void trim(int trimBy) {

		// Im not sure this new Physics thing is actually any better than the old one :/
		// I had it planned and its gone D:

		if (trimBy > Tile.tileSize) {
			try {
				throw new Exception("Can't trim over " + Tile.tileSize);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (trimBy < 0) {
			try {
				throw new Exception("Can not be trim a negative number");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void tick() {
		// animate();
	}

	@SuppressWarnings("static-access")
	public void loadLight(int camX, int camY, int renW, int renH) {
		for (int x = (camX / Tile.tileSize); x < (camX / Tile.tileSize) + renW; x++) {
			for (int y = (camY / Tile.tileSize); y < (camY / Tile.tileSize) + renH; y++) {
				Core.shader.glLight(Core.level.block[spotX][spotY].rect.getX(), Core.level.block[spotX][spotY].rect.getY(), Core.level.block[spotX][spotY].rect.getWidth(), Core.level.block[spotX][spotY].rect.getHeight(), x, y);
			}
		}
	}
	
	public String getName() {
		return this.name;
	}

	@SuppressWarnings("static-access")
	public void render() {
		try {
			if (id != Tile.air) {
				// DO NOT REMOVE THE if(id != Tile.air) or lighting will not work correctly
				Core.shader.glLight(Core.level.block[spotX][spotY].rect.getX(), Core.level.block[spotX][spotY].rect.getY(), Core.level.block[spotX][spotY].rect.getWidth(), Core.level.block[spotX][spotY].rect.getHeight(), 400, 300);
				// Core.shader.glLight(1000, 1000, Core.level.block[spotX][spotY].rect.getWidth(), Core.level.block[spotX][spotY].rect.getHeight(), 400, 300);
				// loadLight((int)Core.sX, (int)Core.sY, (Core.getGameWidth() / Tile.tileSize) + 2, (Core.getGameHeight() / Tile.tileSize) + 2);
				if (Core.physics.emitsLight(spotX, spotY)) {
					// Core.shader.glLight(Core.level.block[spotX][spotY].rect.getX(), Core.level.block[spotX][spotY].rect.getY(), Core.level.block[spotX][spotY].rect.getWidth(), Core.level.block[spotX][spotY].rect.getHeight(),
					// Core.level.block[spotX][spotY].rect.getX(), 100);
				}
				switch (rotation) {
				case ROTATE90:
					GL11.glPushMatrix();
					Util.fillTexturedRect(Tile.terrain, rect.getX() - (int) Core.sX, rect.getY() - (int) Core.sY, Tile.tileSize, Tile.tileSize, id[0] * Tile.tileSize, id[1] * Tile.tileSize, Tile.tileSize, Tile.tileSize, true);
					GL11.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
					GL11.glPopMatrix();
					break;
				case ROTATE180:
					Util.drawImage(Tile.terrain, rect.getX() - (int) Core.sX, rect.getY() - (int) Core.sY, rect.getX() - (int) Core.sX + Tile.tileSize, rect.getY() - (int) Core.sY + Tile.tileSize, id[0] * Tile.tileSize + Tile.tileSize, id[1] * Tile.tileSize + Tile.tileSize, id[0] * Tile.tileSize, id[1] * Tile.tileSize, true);
					break;
				case ROTATE270:
					GL11.glPushMatrix();
					Util.fillTexturedRect(Tile.terrain, rect.getX() - (int) Core.sX, rect.getY() - (int) Core.sY, Tile.tileSize, Tile.tileSize, id[0] * Tile.tileSize, id[1] * Tile.tileSize, Tile.tileSize, Tile.tileSize, true);
					GL11.glRotatef(270.0f, 1.0f, 0.0f, 0.0f);
					GL11.glPopMatrix();
					break;
				case NONE:
				default:
					Util.fillTexturedRect(Tile.terrain, rect.getX() - (int) Core.sX, rect.getY() - (int) Core.sY, Tile.tileSize, Tile.tileSize, id[0] * Tile.tileSize, id[1] * Tile.tileSize, Tile.tileSize, Tile.tileSize, true);
					break;
				}

				GL11.glDisable(GL11.GL_LIGHTING);
			}
		} catch (Exception e) {

		}
	}
}
