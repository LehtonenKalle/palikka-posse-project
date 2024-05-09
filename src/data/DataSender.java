package data;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import com.google.gson.Gson;

/**
 * The DataSender class sends data to a remote server via HTTP POST requests at regular intervals.
 * It converts data from a DataToDatabase object to JSON format and sends it to the specified endpoint.
 * Implements the Runnable interface to enable execution in a separate thread.
 */
public class DataSender implements Runnable {

    /** The interval (in milliseconds) at which data is sent to the server. */
    private static final int sendIntervalMS = 1000; // 1 second interval

    /** The DataToDatabase object containing the data to be sent. */
    private DataToDatabase DTD;

    /**
     * Constructs a DataSender object with the specified DataToDatabase object.
     * @param dtd The DataToDatabase object containing the data to be sent.
     */
    public DataSender(DataToDatabase dtd) {
        DTD = dtd;
    }

    /**
     * Runs the data sending process in a separate thread.
     * Continuously sends data to the remote server at regular intervals.
     */
    public void run() {
        try {
            Thread.sleep(1000); // Wait for 1 second at start
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (true) {
            try {
                // Convert DataToDatabase object to JSON format
                String jsonData = convertToJson(DTD);

                // Create URL connection
                URL url = new URL("http://192.168.1.187:8080/rest/lego/sendstatistics");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST"); // Use POST method
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setDoOutput(true);

                // Write JSON data to output stream
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

    /**
     * Converts a DataToDatabase object to JSON format using Gson library.
     * @param data The DataToDatabase object to be converted.
     * @return The JSON representation of the DataToDatabase object.
     */
    private static String convertToJson(DataToDatabase data) {
        Gson gson = new Gson();
        return gson.toJson(data);
    }
}


