package data;

import lejos.hardware.Button;
import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.utility.Delay;

public class MotorsV2 implements Runnable {
	private DataExchange DEObj = new DataExchange();
    SharedData sd = new SharedData();
    boolean obstacleAvoided = false;
    
    UnregulatedMotor motorA = new UnregulatedMotor(MotorPort.A); // LEFT
    UnregulatedMotor motorB = new UnregulatedMotor(MotorPort.B); // RIGHT
	
	public MotorsV2(DataExchange de, SharedData sd) {
		this.DEObj = de;
		this.sd = sd;
	}
	
	public void run() {
		while (true) {
			
		
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
			    	
		        	motorA.setPower(50); //LEFT
		       		motorB.setPower(-50); //RIGHT
		       		Delay.msDelay(500);
			       	// Continue straight for a while.
			        motorA.setPower(50); //LEFT
			        motorB.setPower(50); //RIGHT
			        Delay.msDelay(1000);
			        // 90 degrees turn.
			       	motorA.setPower(-50); //LEFT
			      	motorB.setPower(50); //RIGHT
			       	Delay.msDelay(500);
			       	// Continue straight for a while.
			        motorA.setPower(50); //LEFT
			        motorB.setPower(50); //RIGHT
			        Delay.msDelay(1800);
			        // 90 degrees turn.
			      	motorA.setPower(-50); //LEFT
			      	motorB.setPower(50); //RIGHT
			      	Delay.msDelay(400);
			      		// Continue straight for as longs as the line is found slowly.
			       		do {
			       			motorA.setPower(25); //LEFT
			       			motorB.setPower(25); //RIGHT
			       		} while (!DEObj.getLineDetected());
			        	// Turn more in line with the track after the line is found,
			        	// Hopefully continues straight,
			        	// The flag for obstacleAvoided is turned to true for (2. LAP) when we put on reverse gear.
			        	do {
			        		motorA.setPower(50); //LEFT
			        		motorB.setPower(-10); //RIGHT
			        	} while (DEObj.getLineDetected());
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
		}while(DEObj.isManualMode() == false);
		do {
			 
				//System.out.println("MotorA value: " + sd.getMotorAValue());
				//System.out.println("MotorB value: " + sd.getMotorBValue());
				motorA.setPower(sd.getMotorAValue());
				motorB.setPower(sd.getMotorBValue());
				
				Delay.msDelay(1000);
			
		}while(DEObj.isManualMode() == true);
		}
		}
}
	
	
		
		
