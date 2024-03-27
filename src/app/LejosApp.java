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
    private static DataExchange de;

    public static void main(String[] args) {
    	de = new DataExchange();
    	
    	od = new ObstacleDetector(de);
    	
    	Thread thread1 = new Thread(od);
    	
    	
    	thread1.setDaemon(true);
    	thread1.start();
    	
        Button.waitForAnyPress();
    }
}