package data;

import java.io.File;
import lejos.hardware.Sound;

/**
 * The SoundPlayer class is responsible for playing sounds on the robot.
 * It implements the Runnable interface to enable concurrent execution in a separate thread.
 */
public class SoundPlayer implements Runnable {
	private DataExchange DEObj = new DataExchange();
	
	/**
     * Constructs a new SoundPlayer object.
     * 
     * @param DE The DataExchange object used for communication between components.
     */
	public SoundPlayer(DataExchange DE) {
		DEObj = DE;
	}
	
	/**
     * The run method of the SoundPlayer thread.
     * It plays a sound file.
     */
	public void run() {
		Sound.playSample(new File("Tokyo-Drift-22050Hz.wav"), 200);
	}
}
