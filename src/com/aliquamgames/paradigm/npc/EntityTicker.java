package com.aliquamgames.paradigm.npc;

import java.util.ArrayList;

public class EntityTicker {

	public static ArrayList<NPC> npcs = new ArrayList<NPC>();

	public EntityTicker() {

	}

	public void tick(int camX, int camY) {
		for (int i = 0; i < npcs.toArray().length; i++) {
			npcs.get(i).tick(camX, camY, i);
			if (npcs.get(i).remove) {
				npcs.remove(i);
				continue;
			}
		}
	}

	public void render(int camX, int camY) {
		for (int i = 0; i < npcs.toArray().length; i++) {
			npcs.get(i).render(camX, camY, i);
		}
	}

}
