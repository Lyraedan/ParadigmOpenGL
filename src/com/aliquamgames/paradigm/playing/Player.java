package com.aliquamgames.paradigm.playing;

import java.awt.image.BufferedImage;

import org.lwjgl.util.Point;
import org.lwjgl.util.Rectangle;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import com.aliquamgames.paradigm.Core;
import com.aliquamgames.paradigm.ImageLoader;
import com.aliquamgames.paradigm.crafting.Crafting;
import com.aliquamgames.paradigm.inventory.Inventory;
import com.aliquamgames.paradigm.sound.SoundPlayer;
import com.aliquamgames.paradigm.util.Util;

public class Player {

	// the RGBA for the skin color on the image
	private static int skinColorOnImage = shifter(255, 255, 255, 255);
	// the RGBA for the hair color on the image
	private static int hairColorOnImage = shifter(76, 76, 76, 255);
	// the RGBA for the shirt color on the image
	private static int shirtColorOnImage = shifter(87, 87, 87, 255);
	// the RGBA for the shoe color on the image
	private static int shoeColorOnImage = shifter(15, 15, 15, 255);
	// the RGBA for the belt color on the image
	private static int beltColorOnImage = shifter(29, 29, 29, 255);
	// the RGBA for the back hair color on the image
	private static int backHairColorOnImage = shifter(151, 151, 151, 255);
	// the RGBA for the back face color on the image
	private static int backFaceColorOnImage = shifter(198, 198, 198, 255);
	// the RGBA for the bottom face color on the image
	private static int bottomFaceColorOnImage = shifter(171, 171, 171, 255);
	// the RGBA for the eye and mouth color on the image
	private static int eyeColorOnImage = shifter(36, 36, 36, 255);
	// the RGBA for the eye and mouth color on the image
	private static int mouthColorOnImage = shifter(36, 36, 36, 255);

	// the RGBA for the skin color to be set to the image
	private static int skinColor = shifter(255, 178, 127, 255);
	// the RGBA for the hair color to be set to the image
	private static int hairColor = shifter(127, 51, 0, 255);
	// the RGBA for the shirt color to be set to the image
	private static int shirtColor = shifter(0, 38, 255, 255);
	// the RGBA for the shoe color to be set to the image
	private static int shoeColor = shifter(15, 15, 15, 255);
	// the RGBA for the belt color to be set to the image
	private static int beltColor = shifter(29, 29, 29, 255);
	// the RGBA for the back hair color to be set to the image
	private static int backHairColor = shifter(175, 70, 0, 255);
	// the RGBA for the back face color to be set to the image
	private static int backFaceColor = shifter(226, 156, 113, 255);
	// the RGBA for the bottom face color to be set to the image
	private static int bottomFaceColor = shifter(226, 156, 113, 255);
	// the RGBA for the eye and mouth color on the image
	private static int eyeColor = shifter(36, 36, 36, 255);
	// the RGBA for the eye and mouth color on the image
	private static int mouthColor = shifter(36, 36, 36, 255);

	// the method to handle the shifting
	/**
	 * @param the
	 *            R value, G value, B value, and A value of the color
	 * @return the ARGB integer value of the color
	 */
	private static int shifter(int r, int g, int b, int a) {
		return (a << 24 | r << 16 | g << 8 | b);
	}

	// these are the colors on the player image
	private static int[] colorsOnImage = { skinColorOnImage, hairColorOnImage, shirtColorOnImage, shoeColorOnImage, beltColorOnImage, backHairColorOnImage, backFaceColorOnImage, bottomFaceColorOnImage, eyeColorOnImage, mouthColorOnImage };
	// these are the colors that will be set to the player in the game
	public static int[] colors = { skinColor, hairColor, shirtColor, shoeColor, beltColor, backHairColor, backFaceColor, bottomFaceColor, eyeColor, mouthColor };

	// the speed of the player
	public double movementSpeed = 9;
	// the falling speed
	private double fallingSpeed = 6;
	// the speed of the jumping
	private double jumpingSpeed = 4;
	// this is the max falling speed that it can have
	private double maxFallingSpeed = 15;
	// this is how much the fallingSpeed changes by every tick
	private double fallingSpeedChange = 0.12;
	// this is the default falling speed for resetting the falling speed
	private double defaultFallingSpeed = fallingSpeed;
	// the direction the player is facing
	public double dir = movementSpeed;
	// the starvation/food for the player
	public double hunger = 10.5;
	// the max hunger for the player
	public double maxHunger = hunger;
	// the armor for the player
	public double armor = 0;
	// the max armor for the player
	public double maxArmor = armor;

	// the xp for the player
	public int xp = 0;
	// the max xp variable
	public int maxXp = 250;
	// the players level
	public int level = 0;

	// TODO well... um.. hmmm what could you do im think like pipes for you but...
	// TODO or a item system? similar to blocks like i got a base down i think?

	// the current time of the system in milliseconds
	private long currentTime = System.currentTimeMillis();
	// the past time of the system in milliseconds
	private long pastTime = currentTime;

	// the amount of time between starving in milliseconds
	private int starveTime = 20 * 1000;
	// the width of the image containing the character
	private int imageWidth = 0;
	// the height of the image containing the character
	private int imageHeight = 0;
	// the width of the character
	public int width = 48;
	// the height of the character
	public int height = 48;
	// the animation timer
	private int animationTimer = 0;
	// the animation time
	private int animationTime = 5;
	// the jumping height
	private int jumpingHeight = 15;
	// the count of the jumping
	private int jumpingCount = 0;
	// the starting x of the foot in the image
	private int footX = 96;
	// the starting x of the arm in the image
	private int armX = 144;
	// the starting x of the further arm in the image
	@SuppressWarnings("unused")
	private int furtherArmX = 192;
	// the starting x of the body in the image
	private int bodyX = 240;
	// the starting x of the head in the image
	private int headFemaleX = 288;
	// the starting x of the normal mouth in the image
	private int mouthNormalX = 336;
	// the starting x of the open mouth in the image
	private int mouthOpenX = 384;
	// the starting x of the female hair in the image
	private int hairFemaleX = 432;
	// the starting x of the male hair in the image
	private int hairMaleX = 480;
	// the starting x of the male head in the image
	private int headMaleX = 528;
	// this is the starting pixel x (which is x) used to determine how many blocks they have walked
	private int startingPixelX = 0;
	// the lines x1, y, magic, farming, mining;1, x2, and y2 for drawing the falling lines
	private int lineX1, lineY1, lineX2, lineY2;

	/** SKILLS */
	// attack strength
	public int attackStrength = 1;
	// defense strength
	public int defenseStrength = 1;
	// magic strength
	public int magicStrength = 1;
	// mining speed
	public int miningSpeed = 1;
	// farming Speed
	public int farmingSpeed = 1;
	// speed
	public double speed = 1;
	// the health of the player
	public static int health = 100;
	// the max health of the player
	public int maxHealth = health;
	// the number of kills the player has got with a sword or bow
	public int attackKills = 1;
	// the number of times the player has got hit WITH ARMOR
	public int defenseHits = 1;
	// the number of kills the player has got with magic
	public int magicKills = 1;
	// the number of blocks the player has mined
	public int miningBlocks = 1;
	// the number of farming blocks the player has mined
	public int farmingBlocks = 1;
	// the number of BLOCKS the player has moved
	public int speedMoves = 1;
	// the number of times the player has been hit WITHOUT ARMOR
	public int healthHits = 1;
	/** SKILLS */

	// the amount that the left leg moves
	private byte leftLegAnimationMove = 0;
	// the amount that the right leg moves
	private byte rightLegAnimationMove = 0;

	// the x of the player
	public double x = 0;
	// the y of the player
	public double y = 0;

	// this controls if the player is a boy or not
	public static boolean isBoy = true;
	// controls if the player can move
	private boolean canMove = false;
	// controls if the player's mouth is open or not
	private boolean mouthOpen = true;
	// controls if the character is jumping or not
	public boolean isJumping = false;
	// controls if the player is falling
	private boolean isFalling = false;
	// controls if the player is dead
	public boolean isDead = false;
	// controls if the player can scream or not
	public boolean canScream = true;
	// controls if the walking sounds should play
	public boolean shouldPlay = false;
	// controls if the walking sounds are playing
	public boolean playing = false;
	// controls if it should trigger the air friction lines
	private boolean triggerAirFricton = false;
	// has the spawn been set?
	private boolean setSpawn = false;
	// the spawn X for beds
	private double spawnX;
	// the spawn Y for beds
	private double spawnY;
	// the players previous x for spawning
	private double prevX;
	// the players previous y for spawning
	private double prevY;
	// the negative supporting x cord
	public double trueX;

	// the image of the player
	public Texture playerImage;

	// the SoundFX instance of the walking on grass sound
	// TODO FIX THIS WHEN SOUNDS ARE IMPLEMENTED
	// public static SoundFX walkSound = Core.grass;

	private boolean isUnderWater = false;

	public Rectangle rect = new Rectangle();

	public Player() {
		playerImage = ImageLoader.character;
		imageWidth = playerImage.getImageWidth();
		imageHeight = playerImage.getImageHeight();

		// playerImage = createNewImageWithColor(colors);

		rect.setBounds((Core.getGameWidth() >> 1) - (width >> 1), (Core.getGameHeight() >> 1) - (height >> 1), width, height);
		x = rect.getX();
		y = rect.getY();
		x += Core.sX;
		y += Core.sY;
		trueX = x;
	}

	public void fallAtMenu() {
		if (!isJumping && !isCollidingWithBlock(new Point((int) (x + 3), (int) (y + height + fallingSpeed - defaultFallingSpeed + feetOffGround)), new Point((int) (x + width - 3), (int) (y + height + fallingSpeed - defaultFallingSpeed + feetOffGround)))) {
			isFalling = true;
			fallingSpeed += fallingSpeedChange;
			if (fallingSpeed > maxFallingSpeed) {
				fallingSpeed = maxFallingSpeed;

			}
			y += fallingSpeed;
			Core.sY += fallingSpeed;
		} else {
			isFalling = false;
			fallingSpeed = defaultFallingSpeed;

		}
	}

	public BufferedImage createNewImageWithColor(int[] colors1) {
		BufferedImage newImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_4BYTE_ABGR);
		for (int x = 0; x < imageWidth; x++) {
			for (int y = 0; y < imageHeight; y++) {
				for (int i = 0; i < colorsOnImage.length; i++) {
					// TODO FIX THIS METHOD NO IDEA HOW!
					// if (playerImage.getRGB(x, y) == colorsOnImage[i]) {
					newImage.setRGB(x, y, colors1[i]);
					// }
				}
			}
		}
		for (int i = 0; i < colorsOnImage.length; i++) {
			colorsOnImage[i] = colors[i];
		}
		return newImage;
	}

	public void tick() {
		rect.setX((int) x);
		rect.setY((int) y);
		water();
		move();
		if (!Inventory.isOpen && !Crafting.isOpen) {
			animations();
		}
		sounds();
		starve();
		xp();
	}

	public void water() {
		if (isUnderwater(new Point((int) (x + 2), (int) (y + height)), new Point((int) (x + width - 2), (int) (y + height)))) {
			isUnderWater = true;
			if (Core.spaceBarDown) {
				Core.sY -= movementSpeed * 2;
				y -= movementSpeed * 2;
				fallingSpeed = 6;
			}
		} else {
			fallingSpeed = 6;
			isUnderWater = false;
		}
	}

	private void xp() {
		xp++;
		if (xp <= 0) {
			xp = 0;
		}
	}

	private void starve() {
		currentTime = System.currentTimeMillis();
		if (currentTime > pastTime + starveTime) {
			pastTime = System.currentTimeMillis();
			hunger -= 0.25;
			if (hunger <= 0) hunger = 0;
		}
	}

	public SoundPlayer grass = Core.grass;
	public SoundPlayer gravel = Core.gravel;
	public SoundPlayer stone = Core.stone;
	public SoundPlayer snow = Core.snow;
	public SoundPlayer cloth = Core.cloth;

	public final int[][] grassSound = { Tile.dirtWithGrass };
	public final int[][] gravelSound = { Tile.dirt, Tile.gravel };
	public final int[][] stoneSound = { Tile.stone, Tile.stone2, Tile.stone3, Tile.stoneSmallDecoration, Tile.stoneSmallDecoration2, Tile.bedrock, Tile.bedrock2, Tile.bedrock3, Tile.obsidian, Tile.obsidian2, Tile.obsidian3, Tile.sandstone, Tile.sandstone2, Tile.sandstone3, Tile.sandstoneBrick, Tile.sandstonePillar, Tile.sandstoneSmallDecoration, Tile.sandstoneSmallDecoration2 };
	public final int[][] snowSound = { Tile.dirtLightWithSnow };
	public final int[][] clothSound = { Tile.sand }; // for now

	private void sounds() {
		/** Todo rewrite */
		if (blockCollidingWith(new Point((int) (x + 3), (int) (y + height + fallingSpeed - defaultFallingSpeed + feetOffGround)), new Point((int) (x + width - 3), (int) (y + height + fallingSpeed - defaultFallingSpeed + feetOffGround))) == Tile.dirt) {

		}
	}

	// TODO FIX THIS METHOD WITH AN ARC METHOD FROM UTIL
	public void drawBubbles(int x, int y) {
		// we call this multiple times for multiple bubbles
		Util.drawColoredArc(Color.cyan, x, y, 8, 8, 0, 360);
		y += 1;
	}

	public void render() {
		// set line thickness

		if (isUnderWater) {
			drawBubbles((int) x - (int) Core.sX, (int) y - (int) Core.sY);
		}

		if (triggerAirFricton) {

			lineX1 = Core.getGameWidth() / 2;
			lineY1 = Core.getGameWidth() / 2 - 60;
			lineX2 = Core.getGameWidth() / 2;
			lineY2 = Core.getGameWidth() / 2 - 45;
			Util.drawColoredLine(new Color(255, 255, 255, 128), lineX1, lineY1, lineX2, lineY2);

			lineX1 = Core.getGameWidth() / 2 + 5;
			lineY1 = Core.getGameWidth() / 2 - 52;
			lineX2 = Core.getGameWidth() / 2 + 5;
			lineY2 = Core.getGameWidth() / 2 - 41;
			Util.drawColoredLine(new Color(255, 255, 255, 128), lineX1, lineY1, lineX2, lineY2);

			lineX1 = Core.getGameWidth() / 2 - 5;
			lineY1 = Core.getGameWidth() / 2 - 55;
			lineX2 = Core.getGameWidth() / 2 - 5;
			lineY2 = Core.getGameWidth() / 2 - 43;
			Util.drawColoredLine(new Color(255, 255, 255, 128), lineX1, lineY1, lineX2, lineY2);
		}
		renderCharacter();
	}

	public int feetOffGround = 3;
	private int widthOnImage = 25;
	private int heightOnImage = 24;
	private int animationStart = 15;
	private double widthResize = 1.92;
	private int heightResize = 2;

	public void renderCharacter() {

		if (isBoy) {
			if (dir == -movementSpeed) {
				Util.drawImage(playerImage, (int) (x - Core.sX), (int) (y - Core.sY), (int) (x - Core.sX + width), (int) (y - Core.sY + height + feetOffGround * heightResize + 1), (animationStart + animationFrame + 1) * widthOnImage, 0, (animationStart + animationFrame) * widthOnImage, heightOnImage, true);
			} else if (dir == movementSpeed) {
				Util.drawImage(playerImage, (int) (x - Core.sX), (int) (y - Core.sY), (int) (x - Core.sX + width), (int) (y - Core.sY + height + feetOffGround * heightResize + 1), (animationStart + animationFrame) * widthOnImage, 0, (animationStart + animationFrame + 1) * widthOnImage, heightOnImage, true);
			}
		} else {
			if (dir == -movementSpeed) {
				Util.drawImage(playerImage, (int) (x - Core.sX), (int) (y - Core.sY), (int) (x - Core.sX + width), (int) (y - Core.sY + height + feetOffGround * heightResize + 1), (animationStart + animationFrame + 1) * widthOnImage, 0, (animationStart + animationFrame) * widthOnImage, heightOnImage, true);
			} else if (dir == movementSpeed) {
				Util.drawImage(playerImage, (int) (x - Core.sX), (int) (y - Core.sY), (int) (x - Core.sX + width), (int) (y - Core.sY + height + feetOffGround * heightResize + 1), (animationStart + animationFrame) * widthOnImage, 0, (animationStart + animationFrame + 1) * widthOnImage, heightOnImage, true);
			}
		}
	}

	public void move() {
		if (!Inventory.isOpen && !Crafting.isOpen) {
			if (Core.isLeft) dir = -movementSpeed;
			else if (Core.isRight) dir = movementSpeed;
		}
		fall();
		if (!Inventory.isOpen && !Crafting.isOpen) {
			jump();
			walk();
		}
		// triggerBorders();
		if (!canScream) {
			if (isOnBlock()) {
				// TODO FIX THIS WHEN THERE IS SOUND
				Core.fallScream.stop("WAV", false);
				canScream = true;
			}
		}
	}

	public double getXPosition() {
		return x;
	}

	public double getYPosition() {
		return y;
	}

	public void fall() {
		if (!isJumping && !isCollidingWithBlock(new Point((int) (x + 12), (int) (y + height + fallingSpeed - defaultFallingSpeed + feetOffGround)), new Point((int) (x + width - 12), (int) (y + height + fallingSpeed - defaultFallingSpeed + feetOffGround)))) {
			isFalling = true;
			fallingSpeed += fallingSpeedChange;
			if (fallingSpeed > maxFallingSpeed) {
				fallingSpeed = maxFallingSpeed;
				triggerAirFricton = true;
				if (canScream) {
					// TODO FIX WHEN THERE IS SOUNDS
					Core.fallScream.play("WAV", false, "SOUND");
					canScream = false;
				}
			} else {
				triggerAirFricton = false;
			}
			y += fallingSpeed;
			Core.sY += fallingSpeed;
		} else {
			isFalling = false;
			fallingSpeed = defaultFallingSpeed;
			if (Core.spaceBarDown) {
				isJumping = true;
			}
		}
	}

	public void jump() {
		if (isJumping) {
			if (!isCollidingWithBlock(new Point((int) (x + 1), (int) y), new Point((int) (x + width - 1), (int) y))) {
				if (jumpingCount >= jumpingHeight) {
					isJumping = false;
					jumpingCount = 0;
				} else {
					y -= jumpingSpeed;
					Core.sY -= jumpingSpeed;

					jumpingCount += 1;
				}
			} else {
				isJumping = false;
				jumpingCount = 0;
			}
		}
	}

	public void walk() {
		if (Core.isLeft || Core.isRight) {
			if (startingPixelX + Tile.tileSize < x || startingPixelX - Tile.tileSize > x) {
				startingPixelX = (int) x;
				speedMoves++;
			}

			if (Core.isRight) {
				canMove = isCollidingWithBlock(new Point((int) (x + width - 7), (int) (y + 3)), new Point((int) (x + width - 7), (int) (y + (height - 2))));
			} else if (Core.isLeft) {
				canMove = isCollidingWithBlock(new Point((int) (x + 6), (int) (y + 3)), new Point((int) (x + 6), (int) (y + (height - 3))));
			}

			if (!canMove) {
				if (Core.isLeft) {
					Core.sX -= movementSpeed * (speed / 100 + 1);
					x -= movementSpeed * (speed / 100 + 1);
					trueX -= movementSpeed * (speed / 100 + 1);
				}
				if (Core.isRight) {
					Core.sX += movementSpeed * (speed / 100 + 1);
					x += movementSpeed * (speed / 100 + 1);
					trueX += movementSpeed * (speed / 100 + 1);
				}
			}
		}
	}

	public void hurt(int amount) {
		health -= amount;
		if (health <= 0) {
			isDead = true;
			prevX = this.x;
			prevY = this.y;
		}
		if (isDead) {
			if (trueX < 0) {
				respawn(trueX * Tile.tileSize, 0, false);
			} else {
				respawn(trueX / Tile.tileSize, 0, false);
			}
		}
	}

	public void heal(int amount) {
		health += amount;
		if (health > 0) {
			isDead = false;
		}
	}

	public void respawn(double x, double y, boolean spawnSet) {
		if (!spawnSet) {
			this.x = x * Tile.tileSize;
			this.y = y * Tile.tileSize;
			this.setSpawn = spawnSet;
			Core.sX = this.x;
			Core.sY = this.y;
			this.x += (Core.getGameWidth() >> 1) - (width >> 1);
			this.y += (Core.getGameHeight() >> 1) - (height >> 1);
		} else {
			this.x = x * Tile.tileSize;
			this.y = y * Tile.tileSize;
			this.setSpawn = spawnSet;
			Core.sX = this.x;
			Core.sY = this.y;
			this.x += (Core.getGameWidth() >> 1) - (width >> 1);
			this.y += (Core.getGameHeight() >> 1) - (height >> 1);
		}
		health = 100;
		armor = 0;
		hunger = 100;
		isDead = false;
		xp = 0;
		level = 0;
		for (int bar = 0; bar < Core.inventory.invBar.length; bar++) {
			int stack = Inventory.invBar[bar].stack;
			for (int s = 0; s < stack; s++) {
				Core.level.throwBlock(Inventory.invBar[bar].id, Inventory.invBar[bar].name);
			}
			Core.inventory.invBar[bar].id = Tile.air;
		}
		for (int bag = 0; bag < Core.inventory.invBag.length; bag++) {
			int stack = Inventory.invBag[bag].stack;
			for (int s = 0; s < stack; s++) {
				Core.level.throwBlock(Inventory.invBag[bag].id, Inventory.invBag[bag].name);
			}
			Core.inventory.invBag[bag].id = Tile.air;
		}
		for (int i = 0; i < Core.gui.heartImages.length; i++) {
			Core.gui.heartImages[i] = Tile.heart;
		}
	}

	public void setSpawnWithBed(double x, double y) {
		spawnX = x * Tile.tileSize;
		spawnY = y * Tile.tileSize;
		respawn(x, y, true);
	}

	public void setSpawnWithCommand(double x, double y) {
		this.x = x * Tile.tileSize;
		this.y = y * Tile.tileSize;
		respawn(x, y, false);
	}

	public boolean isOnBlock() {
		if (isCollidingWithBlock(new Point((int) x + 2, (int) (y + height + fallingSpeed - defaultFallingSpeed + feetOffGround)), new Point((int) (x + width - 2), (int) (y + height + fallingSpeed - defaultFallingSpeed + feetOffGround)))) {
			return true;
		}
		return false;
	}

	@SuppressWarnings("static-access")
	public int[] blockCollidingWith(Point pt1, Point pt2) {
		for (int x = (int) (this.x / Tile.tileSize); x < (int) (this.x / Tile.tileSize + 3); x++) {
			for (int y = (int) (this.y / Tile.tileSize); y < (int) (this.y / Tile.tileSize + 3); y++) {
				if (x >= 0 && y >= 0 && x < Core.level.worldW && y < Core.level.worldH) {
					if (Core.level.block[x][y].id != Tile.air) {
						if (Core.level.block[x][y].rect.contains(pt1) || Core.level.block[x][y].rect.contains(pt2)) {
							return Core.level.block[x][y].id;
						}
					}
				}
			}
		}
		return Tile.air;
	}

	@SuppressWarnings("static-access")
	public boolean isCollidingWithBlock(Point pt1, Point pt2) {
		for (int x = (int) (this.x / Tile.tileSize); x < (int) (this.x / Tile.tileSize + 3); x++) {
			for (int y = (int) (this.y / Tile.tileSize); y < (int) (this.y / Tile.tileSize + 3); y++) {
				if (x >= 0 && y >= 0 && x < Core.level.worldW && y < Core.level.worldH) {
					if (Core.level.block[x][y].id != Tile.air && Core.level.block[x][y].id != Tile.roses && Core.level.block[x][y].id != Tile.roses2 && Core.level.block[x][y].id != Tile.lilies && Core.level.block[x][y].id != Tile.lilies2 && Core.level.block[x][y].id != Tile.water) {
						if (Core.level.block[x][y].rect.contains(pt1) || (Core.level.block[x][y].rect.contains(pt2))) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	@SuppressWarnings("static-access")
	public boolean isUnderwater(Point pt1, Point pt2) {
		for (int x = (int) (this.x / Tile.tileSize); x < (int) (this.x / Tile.tileSize + 3); x++) {
			for (int y = (int) (this.y / Tile.tileSize); y < (int) (this.y / Tile.tileSize + 3); y++) {
				if (x >= 0 && y >= 0 && x < Core.level.worldW && y < Core.level.worldH) {
					if (Core.level.block[x][y].id == Tile.water) {
						if (Core.level.block[x][y].rect.contains(pt1) || (Core.level.block[x][y].rect.contains(pt2))) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	@SuppressWarnings("static-access")
	public boolean isCollidingWithBorder(Point pt1, Point pt2) {
		for (int x = (int) (this.x / Tile.tileSize); x < (int) (this.x / Tile.tileSize + 3); x++) {
			for (int y = (int) (this.y / Tile.tileSize); y < (int) (this.y / Tile.tileSize + 3); y++) {
				if (x >= 0 && y >= 0 && x < Core.level.worldW && y < Core.level.worldH) {
					if (Core.level.block[x][y].id == Tile.solidair) {
						if (Core.level.block[x][y].rect.contains(pt1) || (Core.level.block[x][y].rect.contains(pt2))) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	private int animationFrame = 0;
	private int maxAnimationFrame = 2;
	private boolean rightArmForward = true;

	public void animations() {
		if (!isFalling && (Core.isLeft || Core.isRight)) {
			animationTimer++;
			if (animationTimer > animationTime) {
				animationTimer = 0;
				if (rightArmForward) animationFrame++;
				else if (!rightArmForward) animationFrame += 2;
				if (animationFrame > 1 && rightArmForward) {
					animationFrame = 0;
					rightArmForward = false;
				} else if (animationFrame > maxAnimationFrame && !rightArmForward) {
					animationFrame = 0;
					rightArmForward = true;
				}
				if (animationFrame > maxAnimationFrame) {
					animationFrame = 0;
				}
			}
		} else {
			animationFrame = 0;
		}
	}
}