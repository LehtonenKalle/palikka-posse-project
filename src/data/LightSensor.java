package data;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;

public class LightSensor implements Runnable {
	// Initializing DataExchange and EV3LightSensor
	private DataExchange DEObj = new DataExchange();
    private static EV3ColorSensor cs = new EV3ColorSensor(SensorPort.S3);

    public LightSensor(DataExchange DE) {
		DEObj = DE;
	}
    
    public void run() {
        while (true) {
        	// Using getColor()-method to get the light value
            double colorValue = getColor();
            //System.out.println("color value:" + colorValue);            //System.out.println(colorValue);
            if (colorValue < 0.1) {

                // Send data to dataExchange
            	DEObj.setLineDetected(true);
            	
            }
            // black = 0.1
            else if (colorValue > 0.1){
                // Send data to dataExchange
            	DEObj.setLineDetected(false);
            }
        }
    }

    public double getColor() {
    	// Initializing sample provider
        final SampleProvider colorSP = cs.getRedMode();
        cs.setFloodlight(true);
        // array 
        float[] colorSample = new float[colorSP.sampleSize()];
        // Fetching the sample to the array
        colorSP.fetchSample(colorSample, 0);
        // Setting the DistanceValue as the value found from the 0th index
        double colorValue = colorSample[0];
        return colorValue;
    }
}


