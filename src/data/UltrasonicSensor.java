package data;

import lejos.hardware.Button;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;


public class UltrasonicSensor implements Runnable {
	
	private DataExchange DEObj = new DataExchange();
	private static EV3UltrasonicSensor us = new EV3UltrasonicSensor(SensorPort.S3);
	
	public UltrasonicSensor(DataExchange DE) {
		DEObj = DE;
	}
	
	public void run() {
		if (getDistance() < 7) {
			DEObj.setObstacleDetected(true);
		}
	}
	
	public int getDistance() {
        final SampleProvider sp = us.getDistanceMode();
        int distanceValue = 0;
        
        float [] sample = new float[sp.sampleSize()];
        sp.fetchSample(sample, 0);
        distanceValue = (int)(sample[0]*100);
        
        return distanceValue;
	}
}
