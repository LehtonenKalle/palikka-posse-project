package data;

/**
 * The DataExchange class facilitates communication between different components of the robot
 * by providing methods to set and retrieve information about obstacle and line detection,
 * as well as the manual mode status.
 */
public class DataExchange {
    
    /** Indicates whether an obstacle has been detected. */
    private boolean obstacleDetected = false;
    
    /** Indicates whether a line has been detected. */
    private boolean lineDetected = false;
    
    /** Indicates whether the robot is operating in manual mode. */
    private boolean manualMode;

    /**
     * Constructs a new DataExchange object.
     */
    public DataExchange() {
    }

    /**
     * Sets the status of obstacle detection.
     * @param status The status of obstacle detection (true if obstacle is detected, false otherwise).
     */
    public void setObstacleDetected(boolean status) {
        obstacleDetected = status;
    }

    /**
     * Retrieves the status of obstacle detection.
     * @return true if an obstacle is detected, false otherwise.
     */
    public boolean getObstacleDetected() {
        return obstacleDetected;
    }

    /**
     * Sets the status of line detection.
     * @param status The status of line detection (true if a line is detected, false otherwise).
     */
    public void setLineDetected(boolean status) {
        lineDetected = status;
    }

    /**
     * Retrieves the status of line detection.
     * @return true if a line is detected, false otherwise.
     */
    public boolean getLineDetected() {
        return lineDetected;
    }

    /**
     * Checks if the robot is in manual mode.
     * @return true if the robot is in manual mode, false otherwise.
     */
    public boolean isManualMode() {
        return manualMode;
    }

    /**
     * Sets the manual mode status of the robot.
     * @param manualMode true if the robot is in manual mode, false otherwise.
     */
    public void setManualMode(boolean manualMode) {
        this.manualMode = manualMode;
    }
}

