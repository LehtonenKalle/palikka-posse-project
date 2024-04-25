package data;

import java.util.concurrent.*;

public class SharedData {
	private volatile double colorTresHold;
	private volatile int motorAValue;
	private volatile int motorBValue;

    public synchronized void setColorTresHold(double data) {
        this.colorTresHold = data;
    }

    public synchronized double getColorTresHold() {
        return this.colorTresHold;
    }

	public synchronized int getMotorAValue() {
		return motorAValue;
	}

	public synchronized void setMotorAValue(int motorAValue) {
		this.motorAValue = motorAValue;
	}

	public synchronized int getMotorBValue() {
		return motorBValue;
	}

	public synchronized void setMotorBValue(int motorBValue) {
		this.motorBValue = motorBValue;
	}
    
    
}
