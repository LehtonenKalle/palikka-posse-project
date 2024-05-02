package data;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import com.google.gson.Gson;

public class DataSender implements Runnable {
	private DataToDatabase DTD = new DataToDatabase();
	private static final int sendIntervalMS = 1000; //1 second interval
	
	public DataSender(DataToDatabase dtd) {
		DTD = dtd;
	}
	
	public void run() {
		try {
            Thread.sleep(1000); // Wait for 1 seconds at start
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		
		
		while(true) {
			
		
	        try {
	            String jsonData = convertToJson(DTD);
	            // Create URL and connection
	            URL url = new URL("http://192.168.1.187:8080/rest/lego/sendstatistics");
	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	            conn.setRequestMethod("POST"); // Use POST method
	            conn.setRequestProperty("Content-Type", "application/json");
	            conn.setDoOutput(true);

	            
	            OutputStream os = conn.getOutputStream();
	            OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.UTF_8);
	            osw.write(jsonData);
	            osw.flush();

	            // Check response code
	            int responseCode = conn.getResponseCode();
	            if (responseCode == HttpURLConnection.HTTP_OK) {
	                System.out.println("Data sent successfully");
	            } else {
	                System.out.println("Failed to send data. Response code: " + responseCode);
	            }

	            // Close connections
	            osw.close();
	            os.close();
	            conn.disconnect();
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println("Some problem!");
	        }
	        try {
	            Thread.sleep(sendIntervalMS); // Adjust as needed
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	
	private static String convertToJson(DataToDatabase data) {
	    Gson gson = new Gson();
	    return gson.toJson(data);
	}
		
		
		
		
		
		
	}
	

