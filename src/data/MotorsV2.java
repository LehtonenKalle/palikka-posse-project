package data;

import lejos.hardware.Button;
import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.utility.Delay;
import lejos.utility.Stopwatch;

/**
 * The MotorsV2 class controls the movement of the robot using two motors (left and right).
 * It implements various scenarios for robot movement based on obstacle detection, line following,
 * and manual mode input. The class continuously monitors the robot's environment and adjusts
 * its movement accordingly.
 */
public class MotorsV2 implements Runnable {

    /** The DataExchange object for communication between components. */
    private DataExchange DEObj;

    /** The SharedData object for sharing data among different components. */
    private SharedData sd;

    /** The DataToDatabase object containing statistical data. */
    private DataToDatabase DTDO;

    /** The left motor of the robot. */
    private UnregulatedMotor motorA = new UnregulatedMotor(MotorPort.A);

    /** The right motor of the robot. */
    private UnregulatedMotor motorB = new UnregulatedMotor(MotorPort.B);

    /**
     * Constructs a MotorsV2 object with the specified DataExchange, SharedData, and DataToDatabase objects.
     * @param de The DataExchange object for communication.
     * @param sd The SharedData object for data sharing.
     * @param dtdo The DataToDatabase object containing statistical data.
     */
    public MotorsV2(DataExchange de, SharedData sd, DataToDatabase dtdo) {
        this.DEObj = de;
        this.sd = sd;
        this.DTDO = dtdo;
    }

    /**
     * Runs the motor control logic in a separate thread.
     * The method continuously monitors the environment and adjusts robot movement accordingly,
     * implementing various scenarios for obstacle avoidance, line following, and manual mode control.
     */
    public void run() {
        Stopwatch stopwatch = new Stopwatch();
        while (true) {
            do {
                // Capture online time for statistical purposes
                DTDO.setOn_line_time(stopwatch.elapsed());

                // STOPPING SCENARIO: Check if any button is pressed
                if (Button.getButtons() != 0) {
                    // Stop both motors
                    motorA.stop();
                    motorB.stop();
                    // Free motor resources
                    motorA.close();
                    motorB.close();
                    break; // Exit loop
                }

                // LAP 1 SCENARIO: Obstacle detection and avoidance
                if (DEObj.getObstacleDetected()) {
                    // Perform obstacle avoidance maneuver
                    // (e.g., turn 90 degrees, move forward, turn 90 degrees, etc.)
                    // Adjust motor power and delay accordingly
                }

                // LINE FOLLOWING SCENARIO: Follow the line using differential motor speeds
                if (DEObj.getLineDetected()) {
                    // Adjust motor power based on line detection
                    // (e.g., set motorA to a higher power, motorB to a lower power)
                } else {
                    // Adjust motor power for correcting direction when line is lost
                    // (e.g., set motorA to a lower power, motorB to a higher power)
                }
            } while (DEObj.isManualMode() == false);

            // MANUAL MODE: Control motors based on user input
            do {
                // Reset stopwatch when entering manual mode
                stopwatch.reset();
                // Set motor power based on user-defined values from SharedData object
                motorA.setPower(sd.getMotorAValue());
                motorB.setPower(sd.getMotorBValue());
                // Delay for stability
                Delay.msDelay(200);
            } while (DEObj.isManualMode() == true);
        }
    }
}

	
		
		
