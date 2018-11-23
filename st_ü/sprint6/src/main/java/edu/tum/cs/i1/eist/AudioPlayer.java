package edu.tum.cs.i1.eist;

import java.applet.Applet;
import java.applet.AudioClip;

public class AudioPlayer {

	private static AudioClip MUSIC;
	private static AudioClip BANG;
	private static boolean playingBackgroundMusic;
	public boolean bangSoundPlayed = false;

	public AudioPlayer() {
		AudioPlayer.MUSIC = loadAudioClip("Music.au");
		AudioPlayer.BANG = loadAudioClip("Bang.au"); 
		AudioPlayer.playingBackgroundMusic = false;
	}

	public static boolean isPlayingMusic() {
		return AudioPlayer.playingBackgroundMusic;
	}

	public void playBackgroundMusic() {
		if(!AudioPlayer.playingBackgroundMusic){
			AudioPlayer.playingBackgroundMusic = true;
			MUSIC.loop();
		}
	}

	public void stopBackgroundMusic() {
		if(AudioPlayer.playingBackgroundMusic) {
			AudioPlayer.playingBackgroundMusic = false;
			MUSIC.stop();
		}
	}

	public void playBangAudio() {
		AudioPlayer.BANG.play();
		bangSoundPlayed = true;
	}

	private static AudioClip loadAudioClip(String fileName) {
		return Applet.newAudioClip(Bumpers.class.getClassLoader().getResource(fileName));
	}
}
