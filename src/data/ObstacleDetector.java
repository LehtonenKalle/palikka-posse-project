package data;

import lejos.hardware.Button;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;


public class ObstacleDetector implements Runnable {
	private DataExchange DEObj = new DataExchange();
	private static EV3UltrasonicSensor us = new EV3UltrasonicSensor(SensorPort.S4);
	
	public ObstacleDetector(DataExchange DE) {
		DEObj = DE;
	}
	
	public void run() {
		while (true) {
			System.out.println(getDistance());
			if (getDistance() < 7) {
				DEObj.setObstacleDetected(true);
			} else {
				DEObj.setObstacleDetected(false);
			}
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
