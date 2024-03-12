package com.aliquamgames.paradigm.sound;

import java.io.IOException;

import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.openal.SoundStore;
import org.newdawn.slick.util.ResourceLoader;

/**
 * 
 * @author Luke Rapkin
 * @made_on 16th July 2014
 * @last_modified 16th July 2014
 * 
 */

public class SoundPlayer {
	/** The ogg sound effect */
	private Audio oggEffect;
	/** The wav sound effect */
	private Audio wavEffect;
	/** The aif sound effect */
	private Audio aifEffect;
	/** The ogg stream thats been loaded */
	private Audio oggStream;
	/** The mod stream thats been loaded */
	private Audio modStream;

	public static boolean muted = false; // MUSIC
	public static boolean muted2 = false; // SOUND
	private boolean isMusic = false;

	public float pitch = 1.0f;
	public float gain = 1.0f;

	// help | http://slick.ninjacave.com/javadoc-util/org/newdawn/slick/openal/Audio.html

	public SoundPlayer(String format, String location, boolean stream) {
		format = format.toUpperCase();
		location = "res" + location;
		try {
			if (format.equalsIgnoreCase("wav")) {
				wavEffect = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream(location));
			} else if (format.equalsIgnoreCase("ogg")) {
				oggEffect = AudioLoader.getAudio("OGG", ResourceLoader.getResourceAsStream(location));
			} else if (format.equalsIgnoreCase("ogg") && stream) {
				oggStream = AudioLoader.getStreamingAudio("OGG", ResourceLoader.getResource(location));
			} else if (format.equalsIgnoreCase("mod") && stream) {
				modStream = AudioLoader.getStreamingAudio("MOD", ResourceLoader.getResource(location));
			} else if (format.equalsIgnoreCase("aif")) {
				aifEffect = AudioLoader.getAudio("AIF", ResourceLoader.getResourceAsStream(location));
			}
		} catch (IOException e) {

		}
	}

	// works just like the old one did
	public void play(String format, boolean stream, String soundType) {
		format = format.toUpperCase();
		if (soundType.equalsIgnoreCase("sound") || !muted2) {
			isMusic = false;
			if (format.equalsIgnoreCase("wav")) {
				wavEffect.playAsSoundEffect(pitch, gain, false);
			} else if (format.equalsIgnoreCase("ogg")) {
				oggEffect.playAsSoundEffect(pitch, gain, false);
			} else if (format.equalsIgnoreCase("ogg") && stream) {
				oggStream.playAsSoundEffect(pitch, gain, false);
			} else if (format.equalsIgnoreCase("mod") && stream) {
				modStream.playAsSoundEffect(pitch, gain, false);
			} else if (format.equalsIgnoreCase("aif")) {
				aifEffect.playAsSoundEffect(pitch, gain, false);
			}
		} else if (soundType.equalsIgnoreCase("music") && !muted) {
			isMusic = true;
			if (format.equalsIgnoreCase("wav")) {
				wavEffect.playAsMusic(pitch, gain, false);
				System.out.println("Sound Position: " + wavEffect.getPosition());
			} else if (format.equalsIgnoreCase("ogg")) {
				oggEffect.playAsMusic(pitch, gain, false);
			} else if (format.equalsIgnoreCase("ogg") && stream) {
				oggStream.playAsMusic(pitch, gain, false);
			} else if (format.equalsIgnoreCase("mod") && stream) {
				modStream.playAsMusic(pitch, gain, false);
			} else if (format.equalsIgnoreCase("aif")) {
				aifEffect.playAsMusic(pitch, gain, false);
			}
		}
		// polling is required to allow streaming to get a chance to queue buffers
		SoundStore.get().poll(0);
		isPlaying(format, stream, soundType);

		if (muted && isMusic) {
			stop(format, stream);
		}
		if (muted2 && !isMusic) {
			stop(format, stream);
		}
	}

	public void stop(String format, boolean stream) {
		format = format.toUpperCase();
		if (format.equalsIgnoreCase("wav")) {
			wavEffect.stop();
		} else if (format.equalsIgnoreCase("ogg")) {
			oggEffect.stop();
		} else if (format.equalsIgnoreCase("ogg") && stream) {
			oggStream.stop();
		} else if (format.equalsIgnoreCase("mod") && stream) {
			modStream.stop();
		} else if (format.equalsIgnoreCase("aif")) {
			aifEffect.stop();
		}
	}

	public void loop(String format, boolean stream, String soundType) {
		format = format.toUpperCase();
		if (soundType.equalsIgnoreCase("sound") && !muted2) {
			isMusic = false;
			if (format.equalsIgnoreCase("wav")) {
				if (!wavEffect.isPlaying()) {// these are looping but this line here is a sound fix just in case
					wavEffect.playAsSoundEffect(pitch, gain, true);
				}
			} else if (format.equalsIgnoreCase("ogg")) {
				oggEffect.playAsSoundEffect(pitch, gain, true);
			} else if (format.equalsIgnoreCase("ogg") && stream) {
				if (!oggStream.isPlaying()) {
					oggStream.playAsSoundEffect(pitch, gain, true);
				}
			} else if (format.equalsIgnoreCase("mod") && stream) {
				if (!modStream.isPlaying()) {
					modStream.playAsSoundEffect(pitch, gain, true);
				}
			} else if (format.equalsIgnoreCase("aif")) {
				if (!aifEffect.isPlaying()) {
					aifEffect.playAsSoundEffect(pitch, gain, true);
				}
			}
		} else if (soundType.equalsIgnoreCase("music") && !muted) {
			if (!wavEffect.isPlaying()) {// these are looping but this line here is a sound fix just in case
				wavEffect.playAsMusic(pitch, gain, true);
			}
		} else if (format.equalsIgnoreCase("ogg")) {
			oggEffect.playAsMusic(pitch, gain, true);
		} else if (format.equalsIgnoreCase("ogg") && stream) {
			if (!oggStream.isPlaying()) {
				oggStream.playAsMusic(pitch, gain, true);
			}
		} else if (format.equalsIgnoreCase("mod") && stream) {
			if (!modStream.isPlaying()) {
				modStream.playAsMusic(pitch, gain, true);
			}
		} else if (format.equalsIgnoreCase("aif")) {
			if (!aifEffect.isPlaying()) {
				aifEffect.playAsMusic(pitch, gain, true);
			}
		}
		// polling is required to allow streaming to get a chance to queue buffers
		SoundStore.get().poll(0);
		
		isPlaying(format, stream, soundType);

		if (muted && isMusic) {
			stop(format, stream);
		}
		if (muted2 && !isMusic) {
			stop(format, stream);
		}
	}

	/** WIP */
	public void pause(String format, boolean stream, String soundType) {
		// we will need to write our own pause method
		format = format.toUpperCase();
		if (soundType.equalsIgnoreCase("sound") && !muted2) {
			if (format.equalsIgnoreCase("wav")) {
				if (wavEffect.isPlaying()) {
				}
			} else if (format.equalsIgnoreCase("ogg")) {
				if (oggEffect.isPlaying()) {

				}
			} else if (format.equalsIgnoreCase("ogg") && stream) {
				if (oggStream.isPlaying()) {

				}
			} else if (format.equalsIgnoreCase("mod") && stream) {
				if (modStream.isPlaying()) {

				}
			} else if (format.equalsIgnoreCase("aif")) {
				if (aifEffect.isPlaying()) {

				}
			}
		} else if (soundType.equalsIgnoreCase("music") && !muted) {
			if (format.equalsIgnoreCase("wav")) {
				if (wavEffect.isPlaying()) {
				}
			} else if (format.equalsIgnoreCase("ogg")) {
				if (oggEffect.isPlaying()) {

				}
			} else if (format.equalsIgnoreCase("ogg") && stream) {
				if (oggStream.isPlaying()) {

				}
			} else if (format.equalsIgnoreCase("mod") && stream) {
				if (modStream.isPlaying()) {

				}
			} else if (format.equalsIgnoreCase("aif")) {
				if (aifEffect.isPlaying()) {

				}
			}
		}
	}

	// till you add volume
	public void setVolume(float pitch, float gain) {
		if (pitch <= 0) {
			pitch = 0;
		}
		if (gain <= 0) {
			gain = 0;
		}
		this.pitch = pitch;
		this.gain = gain;
	}

	public boolean isPlaying(String format, boolean stream, String soundType) {
		format = format.toUpperCase();
		if (soundType.equalsIgnoreCase("sound") && !muted2) {
			if (format.equalsIgnoreCase("wav")) {
				return wavEffect.isPlaying();
			} else if (format.equalsIgnoreCase("ogg")) {
				return oggEffect.isPlaying();
			} else if (format.equalsIgnoreCase("ogg") && stream) {
				return oggStream.isPlaying();
			} else if (format.equalsIgnoreCase("mod") && stream) {
				return modStream.isPlaying();
			} else if (format.equalsIgnoreCase("aif")) {
				return aifEffect.isPlaying();
			}
		} else if (soundType.equalsIgnoreCase("music") && !muted) {
			if (format.equalsIgnoreCase("wav")) {
				return wavEffect.isPlaying();
			} else if (format.equalsIgnoreCase("ogg")) {
				return oggEffect.isPlaying();
			} else if (format.equalsIgnoreCase("ogg") && stream) {
				return oggStream.isPlaying();
			} else if (format.equalsIgnoreCase("mod") && stream) {
				return modStream.isPlaying();
			} else if (format.equalsIgnoreCase("aif")) {
				return aifEffect.isPlaying();
			}
		}
		return false;
	}

}
