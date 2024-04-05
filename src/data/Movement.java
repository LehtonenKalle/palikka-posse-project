package data;

/**
 * This class is used for the reverse functionality in Motors-class.
 * Motors.java calls this class for each unique movement
 */
public class Movement {
	private int powerA;
    private int powerB;
    private int timestamp;

    /**
     * @param powerA current power value of the motorA
     * @param powerB current power value of the motorB
     * @param timestamp integer value of the current time
     */
    public Movement(int powerA, int powerB, int timestamp) {
        this.powerA = powerA;
        this.powerB = powerB;
        this.timestamp = timestamp;
    }
    /**
     * @param powerA current power value of the motorA
     * @param powerB current power value of the motorB
     */
    public Movement(int powerA, int powerB) {
    	this.powerA = powerA;
        this.powerB = powerB;
    }
    
    /**
     * sets the timestamp value 
     * @param integer value of the current time in ms
     */
    public void setTimestamp(int timestamp) {
    	this.timestamp = timestamp;
    }
    
    
 // Getter methods for powerA, powerB, and timestamp
    /**
     * @return returns the current value of motorA
     */
    public int getPowerA() {
        return powerA;
    }
    /**
     * @return returns the current value of motorB
     */
    public int getPowerB() {
        return powerB;
    }

    /**
     * @return returns the current timestamp
     */
    public int getTimestamp() {
        return timestamp;
    }
}
