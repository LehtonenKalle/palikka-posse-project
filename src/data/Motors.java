package data;

import java.util.ArrayList;
import java.util.List;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.utility.Delay;
import lejos.utility.Stopwatch;

//Outside the Thread
public class Motors implements Runnable{
	// Alustetaan nopeus atribuutti.
	private int speed;
	// DataExchange objekti luodaan.
	private DataExchange DEObj = new DataExchange();
	List<Movement> recordedMovements = new ArrayList<>();
	boolean obstacleAvoided = false;
	Stopwatch stopwatch = new Stopwatch();
	
	// Luo kaksi moottori objektia.
	// A Vasen.
    UnregulatedMotor motorA = new UnregulatedMotor(MotorPort.A);
    // B Oikea.
    UnregulatedMotor motorB = new UnregulatedMotor(MotorPort.B);
   
    //getter
	public int getSpeed() {
		return speed;
	}
	//setter
	public void setSpeed(int speed) {
	        this.speed = speed;
	}
	//constructori
	public Motors(DataExchange DE,int speed) {
		super();
		this.speed = speed;
		DEObj = DE;
	}
	// Inside the Thread
	@Override
	public void run() {
		stopwatch.reset();
		
		if(recordedMovements.size() == 0) {
			recordedMovements.add(new Movement(0,0,0));
		}
		
				
		
    	do {
    	    // Tarkistetaan onko nappia painettu käynnistyksen jälkeen.
    	    if (Button.getButtons() != 0) {
    	        // Moottorit pysäytetään.
    	        motorA.stop();
    	        motorB.stop();
    	        // Moottoreiden resurssit vapautetaan.
    		    motorA.close();
    		    motorB.close();
    		    // Loop loppuu.
    		    break;
    	    }
    	    if (DEObj.getObstacleDetected() && obstacleAvoided == false) {
    	    	
		    	// A Vasen.
		        // B Oikea.
//		        	180 astetta.
//		        	motorA.setPower(50);
//		        	motorB.setPower(-50);
//		        	Delay.msDelay(1000);
		    		// 90 astetta.Käännös.
    	    		recordedMovements.add(new Movement(motorA.getPower(), motorB.getPower(), stopwatch.elapsed()));
	        		motorA.setPower(50);
	        		motorB.setPower(-50);
	        		Delay.msDelay(550);
	        		recordedMovements.add(new Movement(motorA.getPower(), motorB.getPower(), stopwatch.elapsed()));
		        	// Jatketaan suoraan hetki.
		            motorA.setPower(50);
		            motorB.setPower(50);
		            Delay.msDelay(1000);
		            recordedMovements.add(new Movement(motorA.getPower(), motorB.getPower(), stopwatch.elapsed()));
		            // 90 astetta.Käännös.
		        	motorA.setPower(-50);
		        	motorB.setPower(50);
		        	Delay.msDelay(500);
		        	recordedMovements.add(new Movement(motorA.getPower(), motorB.getPower(), stopwatch.elapsed()));
		        	// Jatketaan suoraan hetki.
		            motorA.setPower(50);
		            motorB.setPower(50);
		            Delay.msDelay(1800);
		            recordedMovements.add(new Movement(motorA.getPower(), motorB.getPower(), stopwatch.elapsed()));
		            // 90 astetta.Käännös.
		        	motorA.setPower(-50);
		        	motorB.setPower(50);
		        	Delay.msDelay(400);
		        	recordedMovements.add(new Movement(motorA.getPower(), motorB.getPower(), stopwatch.elapsed()));
		        	// Jatketaan suoraan hetki.
		        	do {
    		            motorA.setPower(25);
    		            motorB.setPower(25);
		        	} while (!DEObj.getLineDetected());
		        	recordedMovements.add(new Movement(motorA.getPower(), motorB.getPower(), stopwatch.elapsed()));
		       
//	            	motorA.stop();
//	            	motorB.stop();
		        	do {
		        		motorA.setPower(50);
		        		motorB.setPower(-10);
		        	} while (DEObj.getLineDetected());
		        	recordedMovements.add(new Movement(motorA.getPower(), motorB.getPower(), stopwatch.elapsed()));
		        	obstacleAvoided = true;
		            
	        		
		    }
    	    	// LINJA TRUE, Linja näkyvissä. KÄÄNNÖS 1. Rajat asetettu Lightsensor luokassa.
    		    if (DEObj.getLineDetected()) {
    		    	
    		    	// A Vasen.
    		        // B Oikea.
    		        	motorA.setPower(50);
    		        	motorB.setPower(15);        
    		        	// Tietoa color sensorin arvoista.
    		        	//System.out.println("color value: " + colorValue);
    		        	//Tietoa ultra sensorin arvoista
    		        	//System.out.println("distance: " + distanceValue);
    		    }
    		    // LINJA FALSE, Linja näkyvissä. KÄÄNNÖS 2. Rajat asetettu Lightsensor luokassa.
    		    if (!DEObj.getLineDetected()) {
    		    	
    		    	// A Vasen.
    		        // B Oikea.
    		        	motorA.setPower(15);
    		        	motorB.setPower(50);        
    		        	// Tietoa color sensorin arvoista.
    		        	//System.out.println("color value: " + colorValue);
    		        	//Tietoa ultra sensorin arvoista
    		        	//System.out.println("distance: " + distanceValue);
    		    }
    		    // ESTE TRUE, kun este on näkyvissä.Korjausliikkeitä.
    		    Movement lastRecordedMovement = recordedMovements.get(recordedMovements.size() - 1);
    		    if(lastRecordedMovement.getPowerA() != motorA.getPower() && lastRecordedMovement.getPowerB() != motorB.getPower()) {
    		    	recordedMovements.add(new Movement(motorA.getPower(), motorB.getPower(), stopwatch.elapsed()));
    		    }
    		    	
    		   
    		    
    		    if(DEObj.getObstacleDetected()== true && obstacleAvoided == true) {
    		    	
    		    	
    		    	recordedMovements.add(new Movement(motorA.getPower(), motorB.getPower(), stopwatch.elapsed()));
    		    	motorA.setPower(0);
    		    	motorB.setPower(0);
    		    	
    		    	long elapsedTime = stopwatch.elapsed();
    	            long seconds = elapsedTime / 1000;
    	            long minutes = seconds / 60;
    	            seconds %= 60;

    	            LCD.drawString(String.format("%02d:%02d", minutes, seconds), 0, 0);
    		    	
    		    	Delay.msDelay(5000);
    		    	
    				//recordedMovements.add(new Movement(50,25,0)); //1
    				//recordedMovements.add(new Movement(25,50,1000));//2
    				//recordedMovements.add(new Movement(50,25,1500));//3
    				//recordedMovements.add(new Movement(25,50,2200));//4
    				//recordedMovements.add(new Movement(0,0,2500));//5
    				
    				List<Movement> reversedMovements = new ArrayList<>();
    				
    				//otetaan viimeinen aika ylös
    				Movement lastMovement = recordedMovements.get(recordedMovements.size()-1);
    				int lastTimestamp = lastMovement.getTimestamp();
    				
    				//käydään läpi jokainen muuttuja listassa
    				for (int i = recordedMovements.size() - 1; i >= 0; i--) {
    		            Movement movement = recordedMovements.get(i);  
    		            int time = lastTimestamp - movement.getTimestamp();
    		            Movement reversedMovement = new Movement(-movement.getPowerA(), -movement.getPowerB(), time);
    		            lastTimestamp -= time;
    		            reversedMovements.add(reversedMovement);
    		            
    		        }
    				
    				//Tähän takaperinajo looppiin
    				for (Movement movement : reversedMovements) {
    					motorA.setPower(movement.getPowerA());
    					motorB.setPower(movement.getPowerB());
    					Delay.msDelay(movement.getTimestamp() + 10);
    		            //System.out.println("Power A: " + movement.getPowerA() + ", Power B: " + movement.getPowerB() + ", Timestamp: " + movement.getTimestamp());
    		        }
    			}
    		
    		    	
    		    
    	    
    	} while (Button.getButtons() == 0);//Loop jatkuu niin kauan kunnes nappia painetaan.
	}//End of inside the thread.
}//End of class.
