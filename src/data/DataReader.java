package data;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


/**
 * The DataReader class retrieves data from a remote RESTful API endpoint.
 * It parses the response and extracts specific key-value pairs, updating
 * a shared data object accordingly.
 * Implements the Runnable interface to enable execution in a separate thread.
 */
public class DataReader implements Runnable{
	/** The shared data object for storing the retrieved values. */
	private SharedData SDO = new SharedData();
	/** The data exchange object for communication between components. */
	private DataExchange DEObj = new DataExchange();
    /**
     * Constructs a DataReader object with the specified shared data and data exchange objects.
     * @param sd The SharedData object to store the retrieved values.
     * @param de The DataExchange object for communication.
     */
	public DataReader(SharedData sd, DataExchange de) {
		SDO = sd;
		DEObj = de;
	}	
    /**
     * Runs the data retrieval process in a separate thread.
     * Continuously retrieves data from the specified RESTful API endpoint,
     * parses the response, and updates the shared data object.
     */
	public void run() {
		
		
		while (true){
										
			URL url = null;
			HttpURLConnection conn = null;
			InputStreamReader isr = null;
			BufferedReader br=null;

			String s=null;
			try {
				// Open connection to the RESTful API endpoint
				url = new URL("http://192.168.1.187:8080/rest/lego/getvalues");
				conn = (HttpURLConnection)url.openConnection();				
				InputStream is=null;
				try {
					is=conn.getInputStream();
				}
				catch (Exception e) {
			// Handle connection error
		  			System.out.println("Exception conn.getInputSteam()");
		  			e.printStackTrace();
		            System.out.println("Cannot get InputStream!");
				}
				isr = new InputStreamReader(is);
		  		br=new BufferedReader(isr);
		  	// Read the response from the API endpoint
		  		s=br.readLine();
		  		
			}
				catch(Exception e) {
			// Handle general exceptions
					e.printStackTrace();
		        System.out.println("Some problem!");
				}
		
		/*
		 * Next section is taking string from the inputstream, splitting it
		 * and making keyvalue pairs from it
		 */
		
		// Remove curly braces and split by comma to get key-value pairs
	    String[] keyValuePairs = s.replaceAll("[{}\"]", "").split(",");
	    
	    // Create a map to store key-value pairs
	    Map<String, String> map = new HashMap<>();
	    
	    for (String pair : keyValuePairs) {
	        // Split each key-value pair by colon
	        String[] keyValue = pair.split(":");
	        
	        // Trim any leading or trailing whitespace
	        String key = keyValue[0].trim();
	        String value = keyValue[1].trim();
	        
	        // Remove any leading or trailing quotes
	        key = key.replaceAll("^\"|\"$", "");
	        value = value.replaceAll("^\"|\"$", "");
	        
	        // Put key-value pair into the map
	        map.put(key, value);
	    }
	    
	    /*
	     * Next we get values we want from the map that has keyvalue pairs ready
	     */
	    
	    // Example: Get value associated with "colorvalue"
	    String colorvalue = map.get("colorvalue");
	    double d = Double.parseDouble(colorvalue);
	    SDO.setColorTresHold(d);
	    
	    String motorAPower = map.get("motorapower");
	    int powerIntA = Integer.parseInt(motorAPower);
	    SDO.setMotorAValue(powerIntA);
	    
	    String motorBPower = map.get("motorbpower");
	    int powerIntB = Integer.parseInt(motorBPower);
	    SDO.setMotorBValue(powerIntB);
	    
	    String manualModeStr = map.get("manualmode");
        boolean manualMode = Boolean.parseBoolean(manualModeStr);
        DEObj.setManualMode(manualMode);
	    
	    try {
            Thread.sleep(50); // Adjust as needed
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		}
		
	}
}
	
	
