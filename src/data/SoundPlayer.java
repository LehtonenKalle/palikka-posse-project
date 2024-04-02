package data;

import java.io.File;

import lejos.hardware.Sound;

public class SoundPlayer implements Runnable {
	private DataExchange DEObj = new DataExchange();
	
	public SoundPlayer(DataExchange DE) {
		// Setting up DataExchange object that is sent to this constructor as an argument
		DEObj = DE;
	}
	
	public void run() {
		Sound.playSample(new File("Tokyo-Drift-22050Hz.wav"), 0);
	}
}
