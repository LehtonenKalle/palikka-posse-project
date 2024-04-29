package data;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;



public class DataReader implements Runnable{
	
	private SharedData SDO = new SharedData();
	
	public DataReader(SharedData sd) {
		SDO = sd;
	}	
	
	public void run() {
		
		
		while (true){
										
			URL url = null;
			HttpURLConnection conn = null;
			InputStreamReader isr = null;
			BufferedReader br=null;

			String s=null;
			try {
				url = new URL("http://192.168.56.1:8080/rest/lego/getvalues");
				conn = (HttpURLConnection)url.openConnection();
					System.out.println(conn.toString()); 
				InputStream is=null;
				try {
					is=conn.getInputStream();
				}
				catch (Exception e) {
		  			System.out.println("Exception conn.getInputSteam()");
		  			e.printStackTrace();
		            System.out.println("Cannot get InputStream!");
				}
				isr = new InputStreamReader(is);
		  		br=new BufferedReader(isr);
		  		
		  		s=br.readLine();
		  		
	//			while ((s=br.readLine())!=null){
	//				System.out.println(s);
	//			}
			}
				catch(Exception e) {
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
	    
	    try {
            Thread.sleep(50); // Adjust as needed
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		}
		
	}
}
	
	
