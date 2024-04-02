package data;

import java.util.ArrayList;
import java.util.List;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.utility.Delay;

//Outside the Thread
public class Motors implements Runnable{
	// Alustetaan nopeus atribuutti.
	private int speed;
	// DataExchange objekti luodaan.
	private DataExchange DEObj = new DataExchange();
	
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
		motorA.setPower(this.speed);
    	motorB.setPower(this.speed);
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
    	    if (DEObj.getObstacleDetected()) {
		    	// A Vasen.
		        // B Oikea.
//		        	180 astetta.
//		        	motorA.setPower(50);
//		        	motorB.setPower(-50);
//		        	Delay.msDelay(1000);
		    		// 90 astetta.Käännös.
	        		motorA.setPower(50);
	        		motorB.setPower(-50);
	        		Delay.msDelay(550);
		        	// Jatketaan suoraan hetki.
		            motorA.setPower(50);
		            motorB.setPower(50);
		            Delay.msDelay(1000);
		            // 90 astetta.Käännös.
		        	motorA.setPower(-50);
		        	motorB.setPower(50);
		        	Delay.msDelay(500);
		        	// Jatketaan suoraan hetki.
		            motorA.setPower(50);
		            motorB.setPower(50);
		            Delay.msDelay(1800);
		            // 90 astetta.Käännös.
		        	motorA.setPower(-50);
		        	motorB.setPower(50);
		        	Delay.msDelay(400);
		        	// Jatketaan suoraan hetki.
		        	do {
    		            motorA.setPower(25);
    		            motorB.setPower(25);
		        	} while (!DEObj.getLineDetected());
		       
//	            	motorA.stop();
//	            	motorB.stop();
		        	do {
		        		motorA.setPower(50);
		        		motorB.setPower(-10);
		        	} while (DEObj.getLineDetected());
		            
	        		
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
    		    
    		    if(DEObj.getObstacleDetected()== true && jotain muuta) {
    		    	List<Movement> recordedMovements = new ArrayList<>();
    				recordedMovements.add(new Movement(50,25,0)); //1
    				recordedMovements.add(new Movement(25,50,1000));//2
    				recordedMovements.add(new Movement(50,25,1500));//3
    				recordedMovements.add(new Movement(25,50,2200));//4
    				recordedMovements.add(new Movement(0,0,2500));//5
    				
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
    		            System.out.println(lastTimestamp);
    		        }
    					
    				System.out.println("1");
    				for (Movement movement : reversedMovements) {
    		            System.out.println("Power A: " + movement.getPowerA() + ", Power B: " + movement.getPowerB() + ", Timestamp: " + movement.getTimestamp());
    		        }
    			}
    		}
    		    }
    		    
    	    
    	} while (Button.getButtons() == 0);//Loop jatkuu niin kauan kunnes nappia painetaan.
	}//End of inside the thread.
}//End of class.
