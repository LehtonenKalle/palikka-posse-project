package data;

import lejos.hardware.Battery;

/*
 * This class needs to be "copy" of server side "LegoStatistics" class
 * Meaning same variables with same names for jpa to work
 */

public class DataToDatabase {
	/** The amount of time the device has been online (in seconds). */
    private volatile int on_line_time;
    
    /** The current battery voltage of the device. */
    private volatile double battery_voltage;
    
    /** The distance to the nearest obstacle (in some unit of measurement). */
    private volatile int obstacle_distance;
    
    /**
     * Constructs a new DataToDatabase object with default values.
     */
    public DataToDatabase() {
    }
    /**
     * Retrieves the online time of the device.
     * @return The online time in seconds.
     */
    public int getOn_line_time() {
        return on_line_time;
    }
    /**
     * Sets the online time of the device.
     * @param line_time The online time to set in seconds.
     */
    public void setOn_line_time(int line_time) {
        this.on_line_time = line_time;
    }
    /**
     * Retrieves the battery voltage of the device.
     * @return The battery voltage in volts.
     */
    public double getBattery_voltage() {
        // Retrieve the current battery voltage
        battery_voltage = Battery.getBatteryCurrent();
        return battery_voltage;
    }
    /**
     * Sets the battery voltage of the device.
     * @param battery_voltage The battery voltage to set in volts.
     */
    public void setBattery_voltage(double battery_voltage) {
        this.battery_voltage = battery_voltage;
    }
    /**
     * Retrieves the obstacle distance from the device.
     * @return The obstacle distance in some unit of measurement.
     */
    public int getObstacle_distance() {
        return obstacle_distance;
    }
    /**
     * Sets the obstacle distance for the device.
     * @param obstacle_distance The obstacle distance to set in some unit of measurement.
     */
    public void setObstacle_distance(int obstacle_distance) {
        this.obstacle_distance = obstacle_distance;
    }
	
	
}
