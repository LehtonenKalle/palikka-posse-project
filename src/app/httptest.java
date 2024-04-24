package app;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

//import javax.ws.rs.client.Client;
//import javax.ws.rs.client.ClientBuilder;
//import javax.ws.rs.client.Entity;
//import javax.ws.rs.client.Invocation.Builder;
//import javax.ws.rs.client.WebTarget;
//import javax.ws.rs.core.MediaType;

import lejos.hardware.Button;
import lejos.hardware.Sound;
public class httptest {

	public static void main(String[] args) {
		
        System.out.println("Read some text from URL\n");
        System.out.println("Press any key to start");
        
        Button.waitForAnyPress();

        URL url = null;
  		HttpURLConnection conn = null;
  		InputStreamReader isr = null;
  		BufferedReader br=null;

  		String s=null;
		try {
			url = new URL("http://192.168.1.11:8080/rest/lego/getvalues");
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
        
        // Example: Get value associated with "key2"
        String colorvalue = map.get("colorvalue");
        System.out.println("Value for colorvalue: " + colorvalue);
    

	
		
        System.out.println("Press any key to FINISH");
        Button.waitForAnyPress();
	}

}