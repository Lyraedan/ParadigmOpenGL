package com.aliquamgames.paradigm.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import org.lwjgl.Sys;

import com.aliquamgames.paradigm.Core;
import com.aliquamgames.paradigm.Core.State;

public class SaveLoad implements Runnable {
	private Thread thread = new Thread(this, "SaveLoad");

	private long currentTime = getTime();
	private long lastSaveTime = getTime();
	private short saveTime = 10 * 1000;

	public SaveLoad() {
		// thread.start(); //<-- causes lag
	}

	@SuppressWarnings("static-access")
	public void save() {
		try {
			FileOutputStream fileOut = new FileOutputStream("World.sav");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);

			out.writeObject(Core.level.block);
			out.writeObject(Core.level.biomeArray);
			out.writeObject(Core.level.chunks);
			out.writeByte(Core.player.health);
			out.writeObject(Core.inventory.invBar);
			out.writeObject(Core.inventory.invBag);
			out.writeDouble(Core.sX);
			out.writeDouble(Core.sY);
			out.writeDouble(Core.player.x);
			out.writeDouble(Core.player.y);
			out.writeDouble(Core.player.trueX);
			for (int i = 0; i < Core.entityTicker.npcs.toArray().length; i++) {
				out.writeObject(Core.entityTicker.npcs.get(i).image);
				out.writeDouble(Core.entityTicker.npcs.get(i).x);
				out.writeDouble(Core.entityTicker.npcs.get(i).y);
				out.writeDouble(Core.entityTicker.npcs.get(i).damage);
				out.writeObject(Core.entityTicker.npcs.get(i).rect);
			}

			out.close();
			fileOut.close();

		} catch (FileNotFoundException e) {
			System.err.println("File not Found");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("IO Exception");
			e.printStackTrace();
		}
	}

	public void load() {

	}

	@Override
	public void run() {
		while (Core.running) {
			if (Core.state == State.PLAYING || Core.state == State.PAUSED) {
				if (currentTime > lastSaveTime + saveTime) {
					lastSaveTime = getTime();
					// save();
				}
			}
		}

		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public long getTime() {
		return Sys.getTime() * 1000 / Sys.getTimerResolution();
	}

}
