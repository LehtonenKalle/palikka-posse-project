package data;

import lejos.hardware.Button;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

/**
 * This class sends information about detected obstacles to DataExchange utilizing UltrasonicSensor values.
 */
public class ObstacleDetector implements Runnable {
	// Initializing DataExchange and EV3UltrasonicSensor
	private DataExchange DEObj = new DataExchange();
	
	private static EV3UltrasonicSensor us = new EV3UltrasonicSensor(SensorPort.S4);
	/**
	 * Constructor for the class
	 * @param DE DataExchange object that is used for communication between components
	 */
	public ObstacleDetector(DataExchange DE) {
		// Setting up DataExchange object that is sent to this constructor as an argument
		DEObj = DE;
	}
	
	/**
	 * This is the run method that sends information about the obstacle detection to DE.
	 * This is done by measuring the distance.
	 */
	public void run() {
		// Looping 
		while (true) {
			//System.out.println(getDistance());
			// Using getDistance()-method to get the distance value
			// If distance to the object is under 7cm, send the information to DE
			if (getDistance() < 7) {
				DEObj.setObstacleDetected(true);
			}
			// If distance to the object is over 7cm, send the information to DE
			else {
				DEObj.setObstacleDetected(false);
			}
		}

	}
	
	/**
	 * Turns on the distance mode and gets the fetched distance value from SampleProvider
	 * @return returns the distance value in cm
	 */
	public int getDistance() {
		// Initializing sp
        final SampleProvider sp = us.getDistanceMode();
        int distanceValue = 0;
        
        // Making an array that is the size of the sample
        float [] sample = new float[sp.sampleSize()];
        // Fetching the sample to the array
        sp.fetchSample(sample, 0);
        // Setting the DistanceValue as the value found from the 0th index and multiplying it by 100 (to make the value as cm)
        distanceValue = (int)(sample[0]*100);
        
        return distanceValue;
	}
	
}