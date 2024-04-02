package data;

public class DataExchange {
	private boolean	obstacleDetected = false;
	private boolean	lineDetected = false;
	public DataExchange() {
		
	}	
	public void setObstacleDetected(boolean status) {
		obstacleDetected = status;
	}
	public boolean getObstacleDetected() {
		return obstacleDetected;
	}
	public void setLineDetected(boolean status) {
		lineDetected = status;
	}
	public boolean getLineDetected() {
		return lineDetected;
	}
}
