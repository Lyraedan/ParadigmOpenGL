package com.aliquamgames.paradigm.playing;

import org.newdawn.slick.Color;

import com.aliquamgames.paradigm.Core;
import com.aliquamgames.paradigm.inventory.Inventory;
import com.aliquamgames.paradigm.render.FontRenderer;
import com.aliquamgames.paradigm.util.Util;

public class Achievements {
	public String[] list = { "Chopping Wood", "Time To Mine", "Crafting!", "Open Inventory" };
	public int numberOfAchievements = list.length;// <--- for the console print
	public String numberOfAchievementsEarned;
	public int numberOfAchievementsEarnedInt = 0 /*Integer.parseInt(numberOfAchievementsEarned)*/;
	public int updateData;
	public Achievements[] tree = new Achievements[list.length];// for the tree itself
	boolean earned = false, canEarn = true, remove = false;
	public static boolean treeOpen = false;
	int waitTimer = 0;

	public Achievements() {
		System.out.println("Number Of Achievements: " + numberOfAchievements);
		for (int i = 0; i < tree.length; i++) {
			tree[i] = new Achievements(list);
		}
	}

	public Achievements(String[] list) {
		this.list = list;
	}

	public void tick() {
		tree();
	}

	public void tree() {
		int[] sid = Inventory.invBar[Inventory.selected].id;
		int[] sid2 = Inventory.holdingID;

		// EARNING CODE
		for (int i = 0; i < tree.length; i++) {
			if (tree[i].canEarn) {
				// if(sid == Tile.log) {
				// tree[0].earned = true;
				// tree[0].canEarn = false;
				// }
				if (tree[0].earned) {
					if (sid2 == Tile.craftingTable) {
						tree[2].earned = true;
						tree[2].canEarn = false;
					}
				}
				if (Inventory.isOpen) {
					tree[3].earned = true;
					tree[3].canEarn = false;
				}
				if (tree[2].earned) {
					// if(sid == Tile.woodenPickaxe) {
					// tree[1].earned = true;
					// tree[1].canEarn = false;
					// }
					canEarn = true;
				}
			}
		}
	}

	// int xPos = Core.size.width - 115;
	int xPos = 0;
	int yPos = 0;

	int xPos2 = 0;
	int yPos2 = 0;

	int width = FontRenderer.font2.getWidth("Got Achievement: ") + 5;
	int height = FontRenderer.font2.getHeight() * 2 + 5;

	public void render() {

		for (int i = 0; i < tree.length; i++) {
			if (tree[i].earned && !tree[i].remove) {
				yPos += 1;
				Util.fillColoredRect(Color.black, (float) xPos, (float) yPos, width, height);
				Util.drawStringWithShadow("Got Achievement: ", xPos + 5, yPos + (5), 15, false, Color.yellow);
				Util.drawStringWithShadow(list[i], xPos + 5, yPos + (20 + 5), 15, false);
				if (tree[i].earned && yPos <= 1) {
					Core.achievementGot.setVolume(1.0f, 1.0f);
					Core.achievementGot.play("WAV", false, "SOUND");
				}
				if (yPos >= 30) {
					yPos = 30;
					waitTimer += 1;
				} else {
					tree[i].remove = false;
				}
				if (waitTimer >= 100) {
					tree[i].remove = true;
					yPos = 0;
					waitTimer = 0;
				}
			}

			if (treeOpen) {
				// for the Graphical achievement tree
				Util.fillColoredRect(Color.gray, (float) 0, (float) 0, (float) Core.getGameWidth(), (float) Core.getGameHeight());
				Util.fillColoredRect(Color.black, (float) 5, (float) 5, (float) Core.getGameWidth() - 5 * 3, (float) Core.getGameHeight() - 5 * 6);

				// drawing the icons
				// g.drawImage(Tile.workBenchIcon, xPos2 + Core.size.width / 2, yPos2 + Core.size.height / 2, Tile.tileSize, Tile.tileSize, null);

				// for the graphical achievement tree connections
				if (tree[0].earned) {
					Util.drawLine(0, 0, 10, 10);
				} else if (!tree[0].earned) {
					Util.drawLine(0, 0, 10, 10);
				}

				// if a achievement is not earned then shade it
				if (tree[0].earned) {
				} else if (!tree[0].earned) {
					Util.fillColoredRect(new Color(0, 0, 0, 180), (float) xPos2 + (float) Core.getGameWidth() / 2, (float) yPos2 + (float) Core.getGameHeight() / 2, (float) Tile.tileSize, (float) Tile.tileSize);
				}

			}
		}
	}
}
