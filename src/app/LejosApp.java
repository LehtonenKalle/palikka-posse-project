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
    private static UltrasonicSensor us;
    private static DataExchange de;

    public static void main(String[] args) {
    	de = new DataExchange();
    	
    	UltrasonicSensor us = new UltrasonicSensor(de);
    	
    	
    	Thread thread1 = new Thread(us);
    	
    	
    	thread1.setDaemon(true);
    	thread1.start();
    	
    	
    }
}