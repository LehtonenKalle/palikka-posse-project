package data;

import java.util.concurrent.*;

/**
 * The SharedData class provides a thread-safe container for storing shared data among different components.
 * It encapsulates variables representing color threshold, motor A value, and motor B value.
 */
public class SharedData {
    
    /** The color threshold value used in some application logic. */
    private volatile double colorTresHold;
    
    /** The value representing the power or intensity of motor A. */
    private volatile int motorAValue;
    
    /** The value representing the power or intensity of motor B. */
    private volatile int motorBValue;

    /**
     * Sets the color threshold value.
     * @param data The color threshold value to set.
     */
    public synchronized void setColorTresHold(double data) {
        this.colorTresHold = data;
    }

    /**
     * Retrieves the color threshold value.
     * @return The current color threshold value.
     */
    public synchronized double getColorTresHold() {
        return this.colorTresHold;
    }

    /**
     * Retrieves the value representing the power or intensity of motor A.
     * @return The current value of motor A.
     */
    public synchronized int getMotorAValue() {
        return motorAValue;
    }

    /**
     * Sets the value representing the power or intensity of motor A.
     * @param motorAValue The value to set for motor A.
     */
    public synchronized void setMotorAValue(int motorAValue) {
        this.motorAValue = motorAValue;
    }

    /**
     * Retrieves the value representing the power or intensity of motor B.
     * @return The current value of motor B.
     */
    public synchronized int getMotorBValue() {
        return motorBValue;
    }

    /**
     * Sets the value representing the power or intensity of motor B.
     * @param motorBValue The value to set for motor B.
     */
    public synchronized void setMotorBValue(int motorBValue) {
        this.motorBValue = motorBValue;
    }
}

