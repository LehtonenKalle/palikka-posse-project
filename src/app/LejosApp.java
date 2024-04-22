//This is comment...updated by Joni

package app;

import java.io.File;
import data.*;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

/**
 * The main class for the Lejos application.
 *
 * This application was developed by a team consisting of:
 * - Niko Laaksonen
 * - Joni Mitronen
 * - Kalle Lehtonen
 * - Arttu Liitti
 * 
 * This application was collaboratively developed by a team where all members contributed
 * extensively across all aspects of the project. Although each member had their primary areas
 * of focus, the team worked closely together, sharing ideas, providing feedback, and supporting
 * each other throughout the development process.
 */

public class LejosApp {
	
    private static ObstacleDetector od;
    private static LightSensor ls;
    private static DataExchange de;
    private static Motors urm;
    private static SoundPlayer sp;

    
    /**
     * The main method of the Lejos application.
     * Initializes necessary objects and starts threads for different functionalities.
     * @param args The command line arguments.
     */
    
    public static void main(String[] args) {
    	de = new DataExchange();
    	urm = new Motors(de,100);
    	od = new ObstacleDetector(de);
    	ls = new LightSensor(de);
    	sp = new SoundPlayer(de);
    	
    	Thread thread1 = new Thread(od);
    	
    	Thread thread2 = new Thread(urm);
    	
    	Thread thread3 = new Thread(ls);
    	
    	Thread thread4 = new Thread(sp);
    	

    	
    	thread1.setDaemon(true);
    	thread1.start();
    	
    	thread2.setDaemon(true);
    	thread2.start();
    	
    	thread3.setDaemon(true);
    	thread3.start();
    	
    	thread4.setDaemon(true);
    	thread4.start();
    	
    	Button.LEDPattern(4);
        Button.waitForAnyPress();
    }
}
