package app;

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

public class LejosApp {
	
	//private static ColorSensor cs;
    private static ObstacleDetector od;
    private static LightSensor ls;
    private static DataExchange de;
    private static Motors urm;

    public static void main(String[] args) {
    	de = new DataExchange();
    	urm = new Motors(de,100);
    	od = new ObstacleDetector(de);
    	ls = new LightSensor(de);
    	
    	Thread thread1 = new Thread(od);
    	
    	Thread thread2 = new Thread(urm);
    	
    	Thread thread3 = new Thread(ls);

    	
    	thread1.setDaemon(true);
    	thread1.start();
    	
    	thread2.setDaemon(true);
    	thread2.start();
    	
    	thread3.setDaemon(true);
    	thread3.start();
    	
    	Button.LEDPattern(4);
        Button.waitForAnyPress();
    }
}