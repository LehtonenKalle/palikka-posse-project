package data;

/**
 * The DataExchange class facilitates communication between different components
 * of the robot by providing methods to set and retrieve information about obstacle
 * and line detection.
 */

public class DataExchange {
	private boolean	obstacleDetected = false;
	private boolean	lineDetected = false;
	
	/**
     * Constructs a new DataExchange object.
     */
	public DataExchange() {
		
	}
		
	/**
     * Sets the status of obstacle detection.
     * 
     * @param status The status of obstacle detection (true if obstacle is detected, false otherwise).
     */
	public void setObstacleDetected(boolean status) {
		obstacleDetected = status;
	}
	
	/**
     * Retrieves the status of obstacle detection.
     * 
     * @return true if obstacle is detected, false otherwise.
     */
	public boolean getObstacleDetected() {
		return obstacleDetected;
	}
	
	/**
     * Sets the status of line detection.
     * 
     * @param status The status of line detection (true if line is detected, false otherwise).
     */
	public void setLineDetected(boolean status) {
		lineDetected = status;
	}
	
	/**
     * Retrieves the status of line detection.
     * 
     * @return true if line is detected, false otherwise.
     */
	public boolean getLineDetected() {
		return lineDetected;
	}
	
	
}
