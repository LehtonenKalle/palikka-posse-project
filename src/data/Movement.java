package data;

public class Movement {
	private int powerA;
    private int powerB;
    private int timestamp;

    public Movement(int powerA, int powerB, int timestamp) {
        this.powerA = powerA;
        this.powerB = powerB;
        this.timestamp = timestamp;
    }
    public Movement(int powerA, int powerB) {
    	this.powerA = powerA;
        this.powerB = powerB;
    }
    
    public void setTimestamp(int timestamp) {
    	this.timestamp = timestamp;
    }
 // Getter methods for powerA, powerB, and timestamp
    public int getPowerA() {
        return powerA;
    }

    public int getPowerB() {
        return powerB;
    }

    public int getTimestamp() {
        return timestamp;
    }
}
