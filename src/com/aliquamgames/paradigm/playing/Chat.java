package com.aliquamgames.paradigm.playing;

import org.lwjgl.Sys;
import org.newdawn.slick.Color;

import com.aliquamgames.paradigm.Core;
import com.aliquamgames.paradigm.render.FontRenderer;
import com.aliquamgames.paradigm.util.Util;

public class Chat {
	// controls if the message has been sent
	public static boolean sent = false;
	// controls if the chat is open
	public static boolean isOpen = false;

	// the string containing the message
	public static String message;

	// the size of the fone
	private final byte fontSize = 24;

	// the x of the chat box
	private int x;
	// the y of the chat box
	private int y = Core.getGameHeight() - fontSize - 150;
	// the maximum number of lines the chat will be
	private int maxNumberOfLines = 6;
	// the amount of time the message stays on the screen before disappearing
	private int hangTime = 10000;

	// the string array containing the messages
	private String[] lines = new String[maxNumberOfLines];
	// the color array containing the colors of the text
	private Color[] colors = new Color[maxNumberOfLines];
	// the long array containing the time that the message was sent
	private long[] startTime = new long[maxNumberOfLines];

	// the current time of the system in milliseconds
	private long currentTime = getTime();
	// the past time of the system in milliseconds
	private long pastTime = currentTime;

	// the time between color changes for the underscore at the end of the text
	private short changeTime = 250;

	// the color of the underscore at the end of the text
	private Color underscoreColor = Color.white;

	public Chat() {
		message = "";
		for (int i = 0; i < colors.length; i++) {
			colors[i] = Color.white;
		}
	}

	public void checkChat() {

	}

	public void sendCommandNotRecognized() {
		message = "Command not recognized";
		for (int i = lines.length - 1; i >= 0; i--) {
			if (i + 1 < lines.length) {
				lines[i + 1] = lines[i];
				startTime[i + 1] = startTime[i];
			}
		}

		startTime[0] = getTime();
		lines[0] = message;
		message = "";
		sent = false;
	}

	public void sendMessage() {
		if (message == null) {
			sent = false;
			return;
		}

		if (message.startsWith("/")) {
			Core.commands.checkCommands(message);
			message = "";
			sent = false;
			return;
		}

		if (message.equals("")) {
			sent = false;
			return;
		}

		// TODO FIX WHEN CONFIG IS MADE
		// message = Core.config.prop.getProperty("playerName") + ": " + message;
		lines[0] = message;
		startTime[0] = getTime();

		for (int i = lines.length - 1; i >= 0; i--) {
			if (i + 1 < lines.length) {
				lines[i + 1] = lines[i];
				startTime[i + 1] = startTime[i];
			}
		}
		message = "";
		sent = false;
	}

	int height = FontRenderer.font2.getHeight();

	public void render() {
		currentTime = getTime();
		for (int i = 0; i < startTime.length; i++) {
			if (currentTime > startTime[i] + hangTime) {
				lines[i] = "";
			}
		}

		if (isOpen) {
			Util.fillColoredRect(new Color(0, 0, 0, 110), x, y - lines.length * height + height + 5, Core.getGameWidth(), lines.length * height, true);
			if (currentTime > pastTime + changeTime) {
				pastTime = getTime();
				if (underscoreColor == Color.white) underscoreColor = Color.black;
				else underscoreColor = Color.white;
			}

			int underScoreX = FontRenderer.font2.getWidth(message);
			Util.drawColoredLine(underscoreColor, x + underScoreX, y + height - 5, x + underScoreX + 15, y + height - 5, 3);
		}

		if (sent) {
			sendMessage();
		}

		lines[0] = message;

		for (int i = 0; i < lines.length; i++) {
			if (lines[i] != null) {
				Util.drawStringWithShadow("" + lines[i], x, y - fontSize * i - 3, 10, false);
			}
		}
	}

	public long getTime() {
		return Sys.getTime() * 1000 / Sys.getTimerResolution();
	}
}
