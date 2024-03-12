package com.aliquamgames.paradigm.listeners;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;

import com.aliquamgames.paradigm.Core;
import com.aliquamgames.paradigm.Core.State;
import com.aliquamgames.paradigm.crafting.Crafting;
import com.aliquamgames.paradigm.inventory.Inventory;
import com.aliquamgames.paradigm.playing.Chat;
import com.aliquamgames.paradigm.playing.Tile;

public class KeyboardListener {

	private boolean chatOpen = false;
	private int chatLength = 60;

	public KeyboardListener() {
		try {
			Keyboard.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		Keyboard.enableRepeatEvents(true);
	}

	@SuppressWarnings("static-access")
	public void input() {
		currentTime = getTime();
		if (Keyboard.isCreated()) {
			/**
			 * NON TIME DELAYED EVENTS
			 */
			if (!chatOpen) {
				if ((Keyboard.isKeyDown(Keyboard.KEY_A) || Keyboard.isKeyDown(Keyboard.KEY_LEFT))) Core.isLeft = true;
				else Core.isLeft = false;
				if ((Keyboard.isKeyDown(Keyboard.KEY_D) || Keyboard.isKeyDown(Keyboard.KEY_RIGHT))) Core.isRight = true;
				else Core.isRight = false;
				if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) Core.spaceBarDown = true;
				else Core.spaceBarDown = false;
				if (Keyboard.isKeyDown(Keyboard.KEY_J)) Core.player.hurt(1);
				if (Keyboard.isKeyDown(Keyboard.KEY_F3)) Core.showDebug = true;
				else Core.showDebug = false;// TODO make stay open till f3 is pressed again
			} else {

			}

			/**
			 * HERE IS THE TIME DELAYED EVENTS
			 */

			if (currentTime > lastTime + delayTime) {
				if (chatOpen) {
					delayTime = 75;
					if (Keyboard.isKeyDown(Keyboard.KEY_BACK)) {
						if (Chat.message.length() > 0) {
							String[] chars = new String[Chat.message.length()];
							for (int i = 0; i < Chat.message.length(); i++) {
								char character = Chat.message.charAt(i);
								chars[i] = Character.toString(character);
							}
							chars[Chat.message.length() - 1] = "";
							Chat.message = "";
							for (int i = 0; i < chars.length; i++) {
								Chat.message = Chat.message + chars[i];
							}
						}
					}

					if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) || Keyboard.isKeyDown(Keyboard.KEY_RETURN)) {
						chatOpen = false;
						Chat.isOpen = false;
						Chat.sent = true;
						return;
					}

					// TODO FIX WHEN CONFIG IS MADE
					// if (Core.config.prop.getProperty("playerName").length() + Chat.message.length() < chatLength) {
					if (Chat.message.length() < chatLength) {
						while (Keyboard.next()) {
							if (Keyboard.getEventKeyState() && Keyboard.isKeyDown(Keyboard.getEventKey())) {
								if (Keyboard.getEventCharacter() != '') Core.chat.message = Core.chat.message + Keyboard.getEventCharacter();
							}
						}

						Core.chat.checkChat();
					}

				} else {
					delayTime = 125;
					if (Keyboard.isKeyDown(Keyboard.KEY_C)) {
						if (Crafting.isOpen) Crafting.isOpen = false;
						else {
							Crafting.checkedRecipes = false;
							Crafting.isOpen = true;
						}
					}
					if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {
						Inventory.selected--;
						if (Inventory.selected < 0) Inventory.selected = Tile.invLength - 1;
					}
					if (Keyboard.isKeyDown(Keyboard.KEY_E)) {
						Inventory.selected++;
						if (Inventory.selected >= Tile.invLength) Inventory.selected = 0;
					}
					if (Keyboard.isKeyDown(Keyboard.KEY_F)) {
						if (Inventory.isOpen) Inventory.isOpen = false;
						else Inventory.isOpen = true;
						for (int i = 0; i < Core.level.chests.toArray().length; i++) {
							if (Core.level.chests.get(i).isOpen) {
								Core.level.chests.get(i).isOpen = false;
								Inventory.isOpen = false;
								Core.level.chestOpen = false;
							}
						}
					}
					if (Keyboard.isKeyDown(Keyboard.KEY_Z)) {
						Inventory.throwBlock = true;
					}
					if (Keyboard.isKeyDown(Keyboard.KEY_T)) {
						chatOpen = true;
						Core.chat.isOpen = true;
					}
					if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
						if (Core.state == State.PAUSED) Core.state = State.PLAYING;
						else Core.state = State.PAUSED;
					}
				}
				lastTime = getTime();
			}
		}
	}

	private long currentTime = getTime();
	private long lastTime = getTime();
	private short delayTime = 125;

	private long getTime() {
		return Sys.getTime() * 1000 / Sys.getTimerResolution();
	}

}
