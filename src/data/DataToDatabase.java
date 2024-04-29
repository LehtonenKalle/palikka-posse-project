package data;

import lejos.hardware.Battery;

/*
 * This class needs to be "copy" of server side "LegoStatistics" class
 * Meaning same variables with same names for jpa to work
 */

public class DataToDatabase {
	private volatile int on_line_time;
	private volatile double battery_voltage;
	
	public DataToDatabase() {
	}

	public int getOn_line_time() {
		return on_line_time;
	}

	public void setOn_line_time(int on_line_time) {
		int seconds = on_line_time/1000;
		this.on_line_time += seconds;
	}

	public double getBattery_voltage() {
		battery_voltage = Battery.getVoltage();
		return battery_voltage;
	}

	public void setBattery_voltage(double battery_voltage) {
		this.battery_voltage = battery_voltage;
	}
	
	


	
	
}
