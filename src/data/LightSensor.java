package data;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;

public class LightSensor implements Runnable {
	private DataExchange DEObj = new DataExchange();
    private static EV3ColorSensor cs = new EV3ColorSensor(SensorPort.S3);

    public LightSensor(DataExchange DE) {
		DEObj = DE;
	}
    
    public void run() {
        while (true) {
            double colorValue = getColor();            //System.out.println(colorValue);
            if (colorValue < 0.1) {
            	//colorValue = 
                // Send data to motors
            	DEObj.setLineDetected(true);
            	
            }
            else if (colorValue > 0.1){
                // Send data to motors
            	DEObj.setLineDetected(false);
            }
        }
    }

    public double getColor() {
        final SampleProvider colorSP = cs.getRedMode();
        cs.setFloodlight(true);
        float[] colorSample = new float[colorSP.sampleSize()];
        colorSP.fetchSample(colorSample, 0);
        double colorValue = colorSample[0];
        return colorValue;
    }
}


