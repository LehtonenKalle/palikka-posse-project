package data;

import lejos.hardware.Battery;

/*
 * This class needs to be "copy" of server side "LegoStatistics" class
 * Meaning same variables with same names for jpa to work
 */

public class DataToDatabase {
	private volatile int on_line_time;
	private volatile double battery_voltage;
	private volatile int obstacle_distance;
	
	public DataToDatabase() {
	}

	public int getOn_line_time() {
		return on_line_time;
	}

	public void setOn_line_time(int line_time) {
		int seconds = line_time/1000;
		this.on_line_time += seconds;
	}

	public double getBattery_voltage() {
		battery_voltage = Battery.getBatteryCurrent();
		return battery_voltage;
	}

	public void setBattery_voltage(double battery_voltage) {
		this.battery_voltage = battery_voltage;
	}

	public int getObstacle_distance() {
		return obstacle_distance;
	}

	public void setObstacle_distance(int obstacle_distance) {
		this.obstacle_distance = obstacle_distance;
	}
	
	


	
	
}
