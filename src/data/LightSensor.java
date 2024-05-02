package data;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Stopwatch;

/**
 * The LightSensor class gets the light value from the sensor and sends it to DataExchange 
 */
public class LightSensor implements Runnable {
	// Initializing DataExchange and EV3LightSensor
	private DataExchange DEObj = new DataExchange();
    private static EV3ColorSensor cs = new EV3ColorSensor(SensorPort.S3);
    
    private SharedData SDO = new SharedData();
    private DataToDatabase DTDO = new DataToDatabase();
    
    private boolean isBelowTreshold;
    
    //Test
    public LightSensor(DataExchange DE, SharedData SD, DataToDatabase DTD) {
    	DEObj = DE;
    	SDO = SD;
    	DTDO = DTD;
    }
    

	/**
     * @param DE the DateExchange object transfers data between threads
     */
    public LightSensor(DataExchange DE) {
		DEObj = DE;
	}
    
    /**
     * if color values are over 0,1 or under 0,1 send data to DataEchange
     */
    public void run() {
    	Stopwatch stopwatch = new Stopwatch();
        while (true) {
        	// Using getColor()-method to get the light value
            double colorValue = getColor();
            if (colorValue < (SDO.getColorTresHold() + 0.02)) {
            	if(!isBelowTreshold) {
            		stopwatch.reset();
            		isBelowTreshold = true;
            	} 
            	//DTDO.setOn_line_time(stopwatch.elapsed());
            	//System.out.println(DTDO.getOn_line_time());
            } else if (colorValue > SDO.getColorTresHold() + 0.02) {
            	isBelowTreshold = false;
            }
            if (colorValue < SDO.getColorTresHold()) {
                // Send data to dataExchange
            	DEObj.setLineDetected(true);
            	
            }
            // half black half white = 0.1
            else if (colorValue > SDO.getColorTresHold()){
                // Send data to dataExchange
            	DEObj.setLineDetected(false);

            }
            //System.out.println(SDO.getColorTresHold());
        }
    }

    /**
     * Turns on the floodlight
     * Retrieves the color value from the sensor and places it into an array
     * @return the color value obtained from the sensor
     */
    public double getColor() {
    	// Initializing sample provider
        final SampleProvider colorSP = cs.getRedMode();
        cs.setFloodlight(true);
        // array 
        float[] colorSample = new float[colorSP.sampleSize()];
        // Fetching the sample to the array
        colorSP.fetchSample(colorSample, 0);
        // Setting the color value as the value found from the 0th index
        double colorValue = colorSample[0];
        return colorValue;
    }
}


