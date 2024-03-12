package com.aliquamgames.paradigm.npc;

import org.lwjgl.util.Point;
import org.lwjgl.util.Rectangle;
import org.newdawn.slick.opengl.Texture;

import com.aliquamgames.paradigm.Core;
import com.aliquamgames.paradigm.playing.Tile;
import com.aliquamgames.paradigm.util.Util;

public class NPC {

	// the movement speed of the player
	protected double movementSpeed = 1;
	// the x of the player (overrides Rectangle.x)
	public double x = 0;
	// the y of the player (overrides Rectangle.y)
	public double y = 0;
	// controls the direction of the NPC
	protected double dir = movementSpeed;
	// the falling speed
	protected double fallingSpeed = 2;
	// the speed of the jumping
	protected double jumpingSpeed = 2;
	// this is the max falling speed that it can have
	protected double maxFallingSpeed = 10;
	// this is how much the fallingSpeed changes by every tick
	protected double fallingSpeedChange = 0.1;
	// this is the default falling speed for resetting the falling speed
	protected double defaultFallingSpeed = fallingSpeed;

	public Rectangle rect = new Rectangle();

	// the AI instance for the NPC
	protected AI ai;

	// the image for the NPC
	public Texture image;

	// controls if the NPC needs to be removed
	public boolean remove = false;
	// controls if the NPC is dead or not
	public boolean isDead = false;
	// controls if the character is jumping or not
	public boolean isJumping = false;
	// this controls if the NPC is falling
	public boolean isFalling = false;
	// this controls if the NPC can move or not
	private boolean canMove = false;
	// controls if the NPC is hostile
	public boolean hostile = false;
	// controls if the NPC is moving
	public boolean isMoving = false;

	// the amount of damage the NPC does
	public int damage = 10;
	// the current animation column
	protected int animationFrameColumn = 0;
	// the starting X for the animation
	protected int animationStartX = 0;
	// the amount of time for the animation to occur
	protected int animationTime = 500;
	// the timer that counts up to the animationTime
	protected int animationTimer = 0;
	// the width of the NPC
	protected int width = 48;
	// the height of the NPC
	protected int height = 48;
	// the number of animations for the NPC
	protected int animations = 4;
	// the health of the NPC
	protected int health = 100;
	// the max health of the NPC
	protected int maxHealth = health;
	// the jumping height
	protected int jumpingHeight = 20;
	// the count of the jumping
	private int jumpingCount = 0;
	// timer to remove the entity via death or despawn
	private int despawnTimer = 60 * 1000 * 5;
	// timer to begin despawn sequance
	private int despawnStartTime;
	// the width of each animation on the image
	private int widthOnImage = 20;
	// the height of each animation on the image
	private int heightOnImage = 20;

	public NPC(double x, double y) {
		this.x = x;
		this.y = y;
		rect.setBounds((int) x, (int) y, width, height);
		despawnStartTime = (int) System.currentTimeMillis();
	}

	public void tick(int camX, int camY, int index) {
		ai.tick(x, y, camX, camY, index);
		dir = ai.dir;
		animations();
		move();

		// remove(despawnStartTime); //TODO REWRITE CAUSE 100 FPS DROP
	}

	public void render(int camX, int camY, int index) {
		if (dir == -getMovementSpeed()) {
			Util.drawImage(image, (int) x - camX, (int) y - camY, (int) x + width - camX, (int) y + height - camY, animationStartX + animationFrameColumn * widthOnImage, 0, animationStartX + animationFrameColumn * widthOnImage + widthOnImage, heightOnImage, true);
		} else if (dir == getMovementSpeed()) {
			Util.drawImage(image, (int) x - camX, (int) y - camY, (int) x + width - camX, (int) y + height - camY, animationStartX + animationFrameColumn * widthOnImage + widthOnImage, 0, animationStartX + animationFrameColumn * widthOnImage, heightOnImage, true);
		} else {
			Util.drawImage(image, (int) x - camX, (int) y - camY, (int) x + width - camX, (int) y + height - camY, animationStartX + animationFrameColumn * widthOnImage + widthOnImage, 0, animationStartX + animationFrameColumn * widthOnImage, heightOnImage, true);
		}
	}

	public void move() {
		fall();
		jump();
		walk();
	}

	public void fall() {
		if (!isJumping) {
			if (!isCollidingWithBlock(new Point((int) (x + 2), (int) (y + height + 1 + fallingSpeed - defaultFallingSpeed)), new Point((int) (x + width - 2), (int) (y + height + 1 + fallingSpeed - defaultFallingSpeed)))) {
				isFalling = true;
				y += fallingSpeed;
				fallingSpeed += fallingSpeedChange;
				if (fallingSpeed >= maxFallingSpeed) {
					fallingSpeed = maxFallingSpeed;
				}
			} else {
				isFalling = false;
				fallingSpeed = defaultFallingSpeed;
				hasHitTheGround = true;
			}
		}
	}

	public void jump() {
		if (hasHitTheGround) {
			if (isCollidingWithBlock(new Point((int) (x - 2), (int) (y + height / 2 + 1 + fallingSpeed - defaultFallingSpeed)), new Point((int) (x + width + 2), (int) (y + height / 2 + 1 + fallingSpeed - defaultFallingSpeed)))) {
				isJumping = true;
			}

			if (isJumping) {
				if (!isCollidingWithBlock(new Point((int) (x + 2), (int) (y + 1 + fallingSpeed - defaultFallingSpeed)), new Point((int) (x + width - 2), (int) (y + 1 + fallingSpeed - defaultFallingSpeed)))) {
					y -= jumpingSpeed;
					jumpingCount++;
					if (jumpingCount > jumpingHeight) {
						isJumping = false;
						hasHitTheGround = false;
						jumpingCount = 0;
					}

				} else {
					isJumping = false;

				}
			}
		}
	}

	public boolean hasHitTheGround = false;

	public void animations() {
		animationTimer++;
		if (animationTimer >= animationTime) {
			animationFrameColumn++;
			if (animationFrameColumn > animations - 1) {
				animationFrameColumn = 0;
			}
		}
	}

	public void hurt(int amount) {
		if (!isDead) {
			health -= amount;
			if (health <= 0) {
				isDead = true;
			}
		} else {
			isDead = false;
		}
	}

	public void walk() {
		if (isMoving) {
			if (dir == getMovementSpeed()) {
				canMove = isCollidingWithBlock(new Point((int) (x + width), (int) (y + fallingSpeed - defaultFallingSpeed)), new Point((int) (x + width), (int) (y + (height - 2) + fallingSpeed - defaultFallingSpeed)));
			} else if (dir == -getMovementSpeed()) {
				canMove = isCollidingWithBlock(new Point((int) x - 1, (int) (y + fallingSpeed - defaultFallingSpeed)), new Point((int) x - 1, (int) (y + (height - 2) + fallingSpeed - defaultFallingSpeed)));
			}
			if (!canMove) {
				if (dir == getMovementSpeed()) {
					x += getMovementSpeed();
				}
				if (dir == -getMovementSpeed()) {
					x -= getMovementSpeed();
				}
			}
		}
	}

	@SuppressWarnings("static-access")
	public boolean isCollidingWithBlock(Point pt1, Point pt2) {
		for (int x = (int) (this.x / Tile.tileSize); x < (int) (this.x / Tile.tileSize + 3); x++) {
			for (int y = (int) (this.y / Tile.tileSize); y < (int) (this.y / Tile.tileSize + 3); y++) {
				if (x >= 0 && y >= 0 && x < Core.level.generatedChunks * Core.level.CHUNKW && y < Core.level.CHUNKH) {
					if (Core.level.block[x][y].id != Tile.air && Core.level.block[x][y].id != Tile.roses && Core.level.block[x][y].id != Tile.roses2 && Core.level.block[x][y].id != Tile.lilies && Core.level.block[x][y].id != Tile.lilies2) {
						if (Core.level.block[x][y].rect.contains(pt1) || (Core.level.block[x][y].rect.contains(pt2))) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public void remove(long currentTime) {
		if (EntityTicker.npcs.equals(new Zombie(x, y))) {
			// zombie removal code
			if (currentTime >= despawnStartTime + despawnTimer) {
				remove = true;
			}
			if (EntityTicker.npcs.equals(new Zombie(x, y).isDead)) {
				remove = true;
			}
		}
		if (EntityTicker.npcs.equals(new Skeleton(x, y))) {
			// skeleton removal code
			if (currentTime >= despawnStartTime + despawnTimer) {
				remove = true;
			}
			if (EntityTicker.npcs.equals(new Skeleton(x, y).isDead)) {
				remove = true;
			}
		}
	}

	public boolean hostile() {
		return hostile();
	}

	public double getMovementSpeed() {
		return movementSpeed;
	}

	public int getDamage() {
		return damage;
	}

	public double getHealth() {
		return health;
	}

	public double getFallingSpeed() {
		return fallingSpeed;
	}

	public double getJumpingSpeed() {
		return jumpingSpeed;
	}

	public double getXPosition() {
		return x;
	}

	public double getYPosition() {
		return y;
	}

}
