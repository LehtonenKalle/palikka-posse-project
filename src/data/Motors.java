//Package
package data;

//Imports
import java.util.ArrayList;
import java.util.List;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.utility.Delay;
import lejos.utility.Stopwatch;
/**
 * Represents a Motors class that controls the movement of motors.
 * This class utilizes the DataExchange object for transferring information between threads.
 */
//Outside the Thread - Section
public class Motors implements Runnable{
	// Attributes documentation
	// Attribute speed is initialized. This is the speed of the motors
	private int speed;
	// Attribute obstacleAvoided is initialized.Indicates if obstacle has been avoided
	boolean obstacleAvoided = false;
	// DataExchange object is created for transfering information between threads.
	private DataExchange DEObj = new DataExchange();
	// Array Movement is created.
	List<Movement> recordedMovements = new ArrayList<>();
	// Stopwatch object is created for utilizing timing.
	Stopwatch stopwatch = new Stopwatch();
	// Two unregulated motor objects are created.
    UnregulatedMotor motorA = new UnregulatedMotor(MotorPort.A); // LEFT
    UnregulatedMotor motorB = new UnregulatedMotor(MotorPort.B); // RIGHT
    
    SharedData sd = new SharedData();
   
    // Constructor documentation
    /**
     * Constructs a new Motors object with the specified DataExchange and speed.
     * @param DE The DataExchange object for transferring information between threads.
     * @param speed The initial speed of the motors.
     */
	public Motors(DataExchange DE,int speed) {
		super();
		this.speed = speed;
		DEObj = DE;
	}
	
	
	/**
	 * Executes the main logic of the motors in a separate thread.
	 * This method controls the movement of the motors based on sensor inputs.
	 */
	// Inside the Thread - Section
	@Override
	public void run() {
		
		motorA.setPower(sd.getMotorAValue());
		motorB.setPower(sd.getMotorBValue());
		System.out.println("MotorA value: " + sd.getMotorAValue());
		System.out.println("MotorB value: " + sd.getMotorBValue());
		// timer is reseted
		stopwatch.reset();
		// Initialize the recordedMovements list with a default movement if it's empty
		if(recordedMovements.size() == 0) {
			recordedMovements.add(new Movement(0,0,0));
		}
    	do {
    		//STOPPING SCENARIO.
    	    // Check if any button is pressed and released after start.
    	    if (Button.getButtons() != 0) {
    	        // Motors stop.
    	        motorA.stop(); //LEFT
    	        motorB.stop(); //RIGHT
    	        // Motors resources are freed.
    		    motorA.close(); //LEFT
    		    motorB.close(); //RIGHT
    		    // Loop END.
    		    break;
    	    }
    	    // LAP 1 SCENARIO.
    	    // Check if obstacle is detected (true) and obstacleAvoided flag is false.
    	    if (DEObj.getObstacleDetected() && obstacleAvoided == false) {
		    	// 90 degrees turn.
    	    	recordedMovements.add(new Movement(motorA.getPower(), motorB.getPower(), stopwatch.elapsed()));
	        	motorA.setPower(50); //LEFT
	       		motorB.setPower(-50); //RIGHT
	       		Delay.msDelay(500);
	       		recordedMovements.add(new Movement(motorA.getPower(), motorB.getPower(), stopwatch.elapsed()));
		       	// Continue straight for a while.
		        motorA.setPower(50); //LEFT
		        motorB.setPower(50); //RIGHT
		        Delay.msDelay(1000);
		        recordedMovements.add(new Movement(motorA.getPower(), motorB.getPower(), stopwatch.elapsed()));
		        // 90 degrees turn.
		       	motorA.setPower(-50); //LEFT
		      	motorB.setPower(50); //RIGHT
		       	Delay.msDelay(500);
		       	recordedMovements.add(new Movement(motorA.getPower(), motorB.getPower(), stopwatch.elapsed()));
		       	// Continue straight for a while.
		        motorA.setPower(50); //LEFT
		        motorB.setPower(50); //RIGHT
		        Delay.msDelay(1800);
		        recordedMovements.add(new Movement(motorA.getPower(), motorB.getPower(), stopwatch.elapsed()));
		        // 90 degrees turn.
		      	motorA.setPower(-50); //LEFT
		      	motorB.setPower(50); //RIGHT
		      	Delay.msDelay(400);
		      	recordedMovements.add(new Movement(motorA.getPower(), motorB.getPower(), stopwatch.elapsed()));
		      		// Continue straight for as longs as the line is found slowly.
		       		do {
		       			motorA.setPower(25); //LEFT
		       			motorB.setPower(25); //RIGHT
		       		} while (!DEObj.getLineDetected());
		        recordedMovements.add(new Movement(motorA.getPower(), motorB.getPower(), stopwatch.elapsed()));
		        	// Turn more in line with the track after the line is found,
		        	// Hopefully continues straight,
		        	// The flag for obstacleAvoided is turned to true for (2. LAP) when we put on reverse gear.
		        	do {
		        		motorA.setPower(50); //LEFT
		        		motorB.setPower(-10); //RIGHT
		        	} while (DEObj.getLineDetected());
		        recordedMovements.add(new Movement(motorA.getPower(), motorB.getPower(), stopwatch.elapsed()));
		        obstacleAvoided = true;
		    }
    	    // These two codes (TRUE/FALSE) are for following the line. Basically the robot goes side to side like a lizard.
    	   	// WHEN LINE FLAG = TRUE , we do this action to get to the opposite direction.
    	   	// Boundary for this flag is set on LightSensor Class.
    		if (DEObj.getLineDetected()) {
    		    motorA.setPower(50); //LEFT
    		    motorB.setPower(15); //RIGHT  
    		    
    		}
    		// WHEN LINE FLAG = FALSE , we do this action to get to the opposite direction.
    	    // Boundary for this flag is set on LightSensor Class.
    		if (!DEObj.getLineDetected()) {
    		    motorA.setPower(15); //LEFT
    		    motorB.setPower(50); //RIGHT    
    	    }
    			// Record movement if there's any change in motor power
    		    Movement lastRecordedMovement = recordedMovements.get(recordedMovements.size() - 1);
    		if(lastRecordedMovement.getPowerA() != motorA.getPower() && lastRecordedMovement.getPowerB() != motorB.getPower()) {
    		    	recordedMovements.add(new Movement(motorA.getPower(), motorB.getPower(), stopwatch.elapsed()));
    	    }
    		    // LAP 2. SCNEARIO.
    		    // Check if obstacle is detected (true) and obstacleAvoided flag is true.
    		if(DEObj.getObstacleDetected()== true && obstacleAvoided == true) {
    		    recordedMovements.add(new Movement(motorA.getPower(), motorB.getPower(), stopwatch.elapsed()));
    		   	motorA.setPower(0); //LEFT
    		   	motorB.setPower(0); //RIGHT
    		   	// Timer attributes initialized.
    		   	long elapsedTime = stopwatch.elapsed();
    	        long seconds = elapsedTime / 1000;
                long minutes = seconds / 60;
   	            seconds %= 60;
   	            // Print the time to the LCD screen.
    	        LCD.drawString(String.format("%02d:%02d", minutes, seconds), 0, 0);
    	        // Wait for a short moment, for reading time and getting ready to go in reverse gear.
    		   	Delay.msDelay(5000);
    		   	// Creates new array for storing the moves in new 'reversed' order, from the original array.
    			List<Movement> reversedMovements = new ArrayList<>();
    			// Takes a note from the last time.
    			Movement lastMovement = recordedMovements.get(recordedMovements.size()-1);
   				int lastTimestamp = lastMovement.getTimestamp();
   					// Goes through every variable in original recorded array in order to copy them to new array in reversed order.
   					for (int i = recordedMovements.size() - 1; i >= 0; i--) {
   						Movement movement = recordedMovements.get(i);  
   						int time = lastTimestamp - movement.getTimestamp();
   						Movement reversedMovement = new Movement(-movement.getPowerA(), -movement.getPowerB(), time);
   						lastTimestamp -= time;
   						reversedMovements.add(reversedMovement); 
    		        }
    				// Loop for going through the reversed array and setting power to the motors according to that.
    				for (Movement movement : reversedMovements) {
    					motorA.setPower(movement.getPowerA()); //LEFT
    					motorB.setPower(movement.getPowerB()); //RIGHT
    					Delay.msDelay(movement.getTimestamp() + 10);
    		        }
    			}
    	} while (Button.getButtons() == 0);//Loop continues until any button is pressed.
	}//End of inside the thread - Section
	
    // Getter for attribute speed
    /**
     * Retrieves the speed of the motors.
     * @return The current speed of the motors.
     */
	public int getSpeed() {
		return speed;
	}
	// Setter for attribute speed
	/**
	 * Sets the speed of the motors.
	 * @param speed The speed to set for the motors.
	 */
	public void setSpeed(int speed) {
	        this.speed = speed;
	}
}//End of class and outside the thread - Section
