package com.aliquamgames.paradigm.playing;

import com.aliquamgames.paradigm.Core;

public class Commands {

	public Commands() {

	}

	@SuppressWarnings("static-access")
	public void checkCommands(String message) {
		message = message.toLowerCase();
		message = message.replace("/", "");
		String[] string = new String[4];
		int stringIndex = 0;
		try {
			for (int i = 0; i < message.length(); i++) {
				if (message.charAt(i) != ' ') {
					string[stringIndex] = string[stringIndex] + message.charAt(i);
				} else {
					stringIndex++;
				}
			}
		} catch (Exception e) {
			Core.chat.sendCommandNotRecognized();
			Core.chat.sent = true;
		}

		for (int i = 0; i < string.length; i++) {
			if (string[i] != null) {
				if (string[i].contains("null")) {
					string[i] = string[i].replace("null", "");
				}
			}
		}

		System.out.println("COMMAND: " + string[0] + " " + string[1] + " " + string[2] + " " + string[3]);

		try {
			if (string[0].equals("heal")) heal(string);
			else if (string[0].equals("give")) give(string);
			else if (string[0].equals("teleport")) teleport(string);
			else if (string[0].equals("removeall")) removeAll(string);
			else {
				Core.chat.sendCommandNotRecognized();
				Core.chat.sent = true;
			}
		} catch (Exception e) {
			Core.chat.sendCommandNotRecognized();
			Core.chat.sent = true;
		}
	}

	@SuppressWarnings("static-access")
	public void removeAll(String[] string) {
		if (string[0] != null) {
			for (int i = Core.level.blockDrops.toArray().length - 1; i >= 0; i--) {
				Core.level.blockDrops.remove(i);
			}
			for (int i = Core.entityTicker.npcs.toArray().length - 1; i >= 0; i--) {
				Core.spawner.removeMob(i);
			}
		}
	}

	public void teleport(String[] string) {
		if (string[3] != null) {
			System.out.println("SHOULD TELEPORT THE SPECIFIED PLAYER TO THE SPECIFIED X AND Y");
		} else if (string[1] != null && string[2] != null) {
			int x = Integer.parseInt(string[1]) * Tile.tileSize;
			int y = Integer.parseInt(string[2]) * Tile.tileSize;
			Core.sX = x;
			Core.sY = y;
		}
	}

	@SuppressWarnings("static-access")
	public void heal(String[] string) {
		if (string[1] != null) {
			System.out.println("THIS SHOULD HEAL THE SPECIFIED PLAYER: " + string[1]);
		} else {
			Core.player.health = Core.player.maxHealth;
			Core.player.hunger = Core.player.maxHunger;
			for (int i = 0; i < Core.gui.heartImages.length; i++) {
				Core.gui.heartImages[i] = Tile.heart;
			}
			for (int i = 0; i < Core.gui.foodImages.length; i++) {
				Core.gui.foodImages[i] = Tile.hunger;
			}
		}
	}

	@SuppressWarnings("static-access")
	public void give(String[] string) {
		if (string[3] != null) {
			System.out.println("SHOULD GIVE THE SPECIFIED NUMBER OF ITEMS TO THE SPECIFIED PLAYER");
			Core.level.addItem(getTileID(string[2]), Core.inventory.maxStack, string[2]);
		} else if (string[2] != null) {
			System.out.println("SHOULD GIVE THE MAXSTACK TO THE SPECIFIED PLAYER");
			Core.level.addItem(getTileID(string[2]), Core.inventory.maxStack, string[2]);
		} else {
			Core.level.addItem(getTileID(string[1]), Core.inventory.maxStack, string[2]);
		}
	}

	public int[] getTileID(String string) {
		if (string != null) {//i thought it would work at first but then i realised wait...... its coming back
			if (string.equals("dirt")) return Tile.total[2];
			return Tile.dirt;
		}
		return Tile.air;
	}
}
