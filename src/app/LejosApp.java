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
    //private static Motors urm;
    private static MotorsManual urm;
    private static SoundPlayer sp;
    private static SharedData sd;
    private static DataSender ds;
    private static DataToDatabase dtd;
    private static MotorsV2 mv2;
    
    
    private static DataReader dr;

    
    /**
     * The main method of the Lejos application.
     * Initializes necessary objects and starts threads for different functionalities.
     * @param args The command line arguments.
     */
    
    public static void main(String[] args) {
    	de = new DataExchange();
    	sd = new SharedData();
    	dtd = new DataToDatabase();
    	
    	dr = new DataReader(sd, de);
    	ds = new DataSender(dtd);
    	
    	//urm = new Motors(de,100);
    	//urm = new Motors(de,100);
    	//urm = new MotorsManual(de, sd);
    	od = new ObstacleDetector(de, dtd);
    	ls = new LightSensor(de,sd,dtd);
    	sp = new SoundPlayer(de);
    	mv2 = new MotorsV2(de, sd);
    	
    	Thread thread1 = new Thread(od);
    	
    	//Thread thread2 = new Thread(urm);
    	//Thread thread2 = new Thread(urm);
    	
    	Thread thread3 = new Thread(ls);
    	
    	Thread thread4 = new Thread(sp);
    	
    	Thread thread5 = new Thread(dr);
    	
    	Thread thread6 = new Thread(ds);
    	
    	Thread thread7 = new Thread(mv2);
    	

    	
    	thread1.setDaemon(true);
    	thread1.start();
    	
    	//thread2.setDaemon(true);
    	//thread2.start();
    	
    	thread3.setDaemon(true);
    	thread3.start();
    	
    	thread4.setDaemon(true);
    	thread4.start();
    	
    	thread5.setDaemon(true);
    	thread5.start();
    	
    	thread6.setDaemon(true);
    	thread6.start();
    	
    	thread7.setDaemon(true);
    	thread7.start();
    	
    	Button.LEDPattern(4);
        Button.waitForAnyPress();
    }
}
