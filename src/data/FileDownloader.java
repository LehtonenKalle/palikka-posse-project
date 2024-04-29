package data;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FileDownloader implements Runnable {
    private String saveDir;
    private String fileName; // Variable to hold the filename
    private SoundPlayer soundPlayer; // Reference to the SoundPlayer instance

    public FileDownloader(String saveDir, SoundPlayer soundPlayer) {
        this.saveDir = saveDir;
        this.soundPlayer = soundPlayer;
    }

    public String getFileName() {
        return fileName;
    }

    public void run() {
      
//            try {
//                String fileUrl = "http://192.168.56.1:8080/rest/downloadservice/downloadfile";
//
//                // Open connection to the URL
//                URL url = new URL(fileUrl);
//                HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
//                int responseCode = httpConn.getResponseCode();
//
//                // Check HTTP response code
//                if (responseCode == HttpURLConnection.HTTP_OK) {
//                    // Extract filename from content disposition header
//                    String contentDisposition = httpConn.getHeaderField("Content-Disposition");
//                    if (contentDisposition != null && contentDisposition.indexOf("=") != -1) {
//                        String[] parts = contentDisposition.split("=");
//                        fileName = parts[1].substring(parts[1].lastIndexOf("\\") + 1).replace("\"", "");
//
//                        // Set the new file name in SoundPlayer
//                        soundPlayer.setFileName(fileName);
//                    } else {
//                        // If filename couldn't be extracted, use a default name
//                        fileName = "unnamed.wav";
//                    }
//
//                    // Construct the path to save the file with its original name
//                    String saveFilePath = saveDir + fileName;
//
//                    // Open input stream from the HTTP connection
//                    InputStream inputStream = httpConn.getInputStream();
//
//                    // Open output stream to save the file
//                    FileOutputStream outputStream = new FileOutputStream(saveFilePath);
//
//                    // Read data from the input stream and write to the output stream
//                    int bytesRead;
//                    byte[] buffer = new byte[4096];
//                    while ((bytesRead = inputStream.read(buffer)) != -1) {
//                        outputStream.write(buffer, 0, bytesRead);
//                    }
//
//                    // Close streams
//                    outputStream.close();
//                    inputStream.close();
//
//                    System.out.println("File downloaded successfully: " + fileName);
//                } else {
//                    System.out.println("Server returned HTTP response code: " + responseCode);
//                }
//                httpConn.disconnect();
//
//                // Sleep for some time before checking for updates again
//                // Thread.sleep(5000); // Adjust this interval as needed
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
    	
    	try {
            String url = "http://192.168.56.1:8080/hello";
            
            // Open connection to the URL
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");

            // Get the response from the server
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Print the response on the LCD screen (replace this with your LCD screen handling code)
            System.out.println("Response from server: " + response.toString());

            // Close the connection
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    	
    	
        }
    }

