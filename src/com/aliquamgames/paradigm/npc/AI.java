package com.aliquamgames.paradigm.npc;

import com.aliquamgames.paradigm.Core;

public class AI {

	// controls if the AI is hostile
	private boolean hostile;
	// controls if the AI is fleeing
	private boolean fleeing = false;

	// the direction of the AI
	public double dir = 0.5;

	public AI(boolean hostile) {
		this.hostile = hostile;
	}

	public void tick(double x, double y, int camX, int camY, int index) {
		if (hostile) {
			track((int) x, (int) y, camX, camY, index); // <----- tracks players position
			attackPlayerOnContact((int) x, (int) y, index); // <---- attacks on contact
		} else {

		}
	}

	public void flee(int x, int y, int index) {
		if (EntityTicker.npcs.get(index).getHealth() < EntityTicker.npcs.get(index).maxHealth / 5) {
			fleeing = true;
			if (EntityTicker.npcs.get(index).dir == EntityTicker.npcs.get(index).getMovementSpeed()) {
				EntityTicker.npcs.get(index).dir = -EntityTicker.npcs.get(index).getMovementSpeed();
			} else {
				EntityTicker.npcs.get(index).dir = EntityTicker.npcs.get(index).getMovementSpeed();
			}
		}
	}

	public void attackPlayerOnContact(int x, int y, int index) {
		if (x == Core.player.getXPosition() && x == Core.player.getYPosition()) {
			// Core.player.hurt(EntityTicker.npcs.get(index).getDamage()); //TODO UNDO ONCE HEALTH IS DONE
		}
	}

	public void track(int x, int y, int camX, int camY, int index) {
		if (!fleeing) {
			if (x - camX < Core.player.x - camX) {
				EntityTicker.npcs.get(index).isMoving = true;
				dir = EntityTicker.npcs.get(index).movementSpeed;
			} else {
				EntityTicker.npcs.get(index).isMoving = true;
				dir = -EntityTicker.npcs.get(index).movementSpeed;
			}
		}
	}

}
