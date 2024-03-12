package com.aliquamgames.paradigm.playing;


import org.newdawn.slick.Color;

import com.aliquamgames.paradigm.Core;
import com.aliquamgames.paradigm.util.Util;

public class Skills {

	// TODO FINISH THIS CLASS ONCE ATTACKING IS COMPLETE

	// the attack skill
	public int attack = 1;
	// the defense skill
	public int defense = 1;
	// the magic skill
	public int magic = 1;
	// the mining skill
	public int mining = 1;
	// the farming skill
	public int farming = 1;
	// the speed skill
	public int speed = 1;
	// the health skill
	public int health = 1;
	// the xp level
	public int xp = 0;
	// the level of the player
	public int level = 0;
	// the max skill level
	public int maxSkillLevel = 99;

	// the array that contains how many kills they have to get to increase the attack skill
	public int[] attackKills = new int[maxSkillLevel];
	// the array that contains how many times they have to get hit wearing armor to increase the defense skill
	public int[] defenseHits = new int[maxSkillLevel];
	// the array that contains how many magic kills they have to get to increase the magic skill
	public int[] magicKills = new int[maxSkillLevel];
	// the array that contains how many blocks they have to mine to increase the mining skill
	public int[] miningBlocks = new int[maxSkillLevel];
	// the array that contains how many blocks they have to farm to increase the farming skill
	public int[] farmingBlocks = new int[maxSkillLevel];
	// the array that contains how many blocks they have to move to increase the speed skill
	public int[] speedMoves = new int[maxSkillLevel];
	// the array that contains how many times they have to get hit without armor to get to increase the health skill
	public int[] healthHits = new int[maxSkillLevel];
	// the array that contains how much xp is needed to level up
	public int[] xpNeeded = new int[maxSkillLevel];
	// the levels 1-99
	public int[] levels = new int[maxSkillLevel];

	// controls if a skill has been changed
	public boolean changed = false;
	// controls if the skills menu is open
	public boolean open = false;

	// the skill strings
	public String[] skillStrings = { "Attack", "Defense", "Magic", "Mining", "Farming", "Speed", "Health", "Level" };
	// the integer array of the perks
	public int[] skills = { attack, defense, magic, mining, farming, speed, health, level };

	public Skills() {
		setAttackKillsArray();
		setDefenseHitsArray();
		setMagicKillsArray();
		setMiningBlocksArray();
		setFarmingBlocksArray();
		setSpeedMovesArray();
		setHealthHitsArray();
		setXPNeededArray();

		for (int i = 1; i <= maxSkillLevel; i++) {
			levels[i - 1] = i;
		}

	}

	@SuppressWarnings("static-access")
	public void tick() {

		for (int i = 0; i < maxSkillLevel; i++) {
			if (i + 1 < maxSkillLevel) {
				if (Core.player.attackKills > attackKills[i] && Core.player.attackKills < attackKills[i + 1] && attack != levels[i]) {
					attack++;
					changed = true;
				}
				if (Core.player.defenseHits > defenseHits[i] && Core.player.defenseHits < defenseHits[i + 1] && defense != levels[i]) {
					defense++;
					changed = true;
				}
				if (Core.player.magicKills > magicKills[i] && Core.player.magicKills < magicKills[i + 1] && magic != levels[i]) {
					magic++;
					changed = true;
				}
				if (Core.player.miningBlocks > miningBlocks[i] && Core.player.miningBlocks < miningBlocks[i + 1] && mining != levels[i]) {
					mining++;
					changed = true;
				}
				if (Core.player.farmingBlocks > farmingBlocks[i] && Core.player.farmingBlocks < farmingBlocks[i + 1] && farming != levels[i]) {
					farming++;
					changed = true;
				}
				if (Core.player.speedMoves > speedMoves[i] && Core.player.speedMoves < speedMoves[i + 1] && speed != levels[i]) {
					speed++;
					changed = true;
				}
				if (Core.player.healthHits > healthHits[i] && Core.player.healthHits < healthHits[i + 1] && health != levels[i]) {
					health++;
					Core.player.health = Core.player.maxHealth + health;
					Core.player.maxHealth = Core.player.maxHealth + health;
					changed = true;
				}
				if (level + 1 < maxSkillLevel) {
					if (Core.player.xp > xpNeeded[level] && Core.player.xp < xpNeeded[level + 1] && level != levels[i]) {
						level++;
						Core.player.xp = 0;
						if (level < maxSkillLevel) Core.player.maxXp = xpNeeded[level];
						Core.player.level = level;
						changed = true;
					}
				} else {
					if (Core.player.xp > xpNeeded[maxSkillLevel - 1] && level != levels[i]) {
						level++;
						Core.player.xp = 0;
						if (level < maxSkillLevel) Core.player.maxXp = xpNeeded[level];
						Core.player.level = level;
						changed = true;
					}
				}
			} else {
				if (Core.player.attackKills > attackKills[i] && attack != levels[i]) {
					attack++;
					changed = true;
				}
				if (Core.player.defenseHits > defenseHits[i] && defense != levels[i]) {
					defense++;
					changed = true;
				}
				if (Core.player.magicKills > magicKills[i] && magic != levels[i]) {
					magic++;
					changed = true;
				}
				if (Core.player.miningBlocks > miningBlocks[i] && mining != levels[i]) {
					mining++;
					changed = true;
				}
				if (Core.player.farmingBlocks > farmingBlocks[i] && farming != levels[i]) {
					farming++;
					changed = true;
				}
				if (Core.player.speedMoves > speedMoves[i] && speed != levels[i]) {
					speed++;
					changed = true;
				}
				if (Core.player.healthHits > healthHits[i] && health != levels[i]) {
					health++;
					Core.player.health = Core.player.maxHealth + skills[6];
					Core.player.maxHealth = Core.player.maxHealth + skills[6];
					changed = true;
				}
				if (Core.player.xp > xpNeeded[maxSkillLevel - 1] && level != levels[i]) {
					level++;
					Core.player.maxXp = xpNeeded[maxSkillLevel - 1];
					Core.player.xp = 0;
					Core.player.level = level;
					changed = true;
				}
			}
		}

		for (int i = 0; i < skills.length; i++) {
			if (skills[i] >= 99) skills[i] = 99;
		}

		if (attack >= 99) attack = 99;
		if (defense >= 99) defense = 99; 
		if (magic >= 99) magic = 99;
		if (mining >= 99) mining = 99;
		if (farming >= 99) farming = 99;
		if (speed >= 99) speed = 99;
		if (health >= 99) health = 99;
		if (level >= 99) level = 99;

		if (changed) {
			skills[0] = attack;
			skills[1] = defense;
			skills[2] = magic;
			skills[3] = mining;
			skills[4] = farming;
			skills[5] = speed;
			skills[6] = health;
			skills[7] = level;

			Core.player.attackStrength = attack;
			Core.player.defenseStrength = defense;
			Core.player.magicStrength = magic;
			Core.player.miningSpeed = mining;
			Core.player.farmingSpeed = farming;
			Core.player.speed = speed;

			changed = false;
		}
	}

	public void render() {
		if (open) {
			Util.fillColoredRect(new Color(0, 0, 0, 90), 0, 0, Core.getGameWidth(), Core.getGameHeight());
			for (int i = 0; i < skillStrings.length; i++) {
				Util.drawStringWithShadow(skillStrings[i] + ": " + skills[i] + "/" + maxSkillLevel, 40, 20 + (i * 20), 10 * 4, false);
			}
		}
	}

	public void setAttackKillsArray() {
		for (int i = 0; i < maxSkillLevel; i++) {
			attackKills[i] = i * 10;
		}
	}

	public void setDefenseHitsArray() {
		for (int i = 0; i < maxSkillLevel; i++) {
			defenseHits[i] = i * 25;
		}
	}

	public void setMagicKillsArray() {
		for (int i = 0; i < maxSkillLevel; i++) {
			magicKills[i] = i * 10;
		}
	}

	public void setMiningBlocksArray() {
		for (int i = 0; i < maxSkillLevel; i++) {
			miningBlocks[i] = i * 50;
		}
	}

	public void setFarmingBlocksArray() {
		for (int i = 0; i < maxSkillLevel; i++) {
			farmingBlocks[i] = i * 20;
		}
	}

	public void setSpeedMovesArray() {
		for (int i = 0; i < maxSkillLevel; i++) {
			speedMoves[i] = i * 200;
		}
	}

	public void setHealthHitsArray() {
		for (int i = 0; i < maxSkillLevel; i++) {
			healthHits[i] = i * 15;
		}
	}

	public void setXPNeededArray() {
		for (int i = 0; i < maxSkillLevel; i++) {
			xpNeeded[i] = i * 250 + 250;
		}
	}
}
