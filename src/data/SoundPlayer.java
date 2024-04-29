package data;

import java.io.File;
import lejos.hardware.Sound;

public class SoundPlayer implements Runnable {
    private String saveDir;
    private String fileName; // Variable to hold the filename

    public SoundPlayer(String saveDir) {
        this.saveDir = saveDir;
    }

    // Method to set the filename
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void run() {
        while (true) {
            try {
                // Construct the file path for the downloaded WAV file
                String filePath = saveDir + fileName;

                // Play the downloaded WAV file
                Sound.playSample(new File(filePath), 200);

                // Sleep for some time before playing the next file
                Thread.sleep(10000); // Adjust this interval as needed

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

