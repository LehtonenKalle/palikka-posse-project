package data;

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
    	    	// LINJA TRUE, Linja näkyvissä. KÄÄNNÖS 1. Rajat asetettu Lightsensor luokassa.
    		    if (DEObj.getLineDetected()) {
    		    	// A Vasen.
    		        // B Oikea.
    		        	motorA.setPower(50);
    		        	motorB.setPower(25);        
    		        	// Tietoa color sensorin arvoista.
    		        	//System.out.println("color value: " + colorValue);
    		        	//Tietoa ultra sensorin arvoista
    		        	//System.out.println("distance: " + distanceValue);
    		    }
    		    // LINJA FALSE, Linja näkyvissä. KÄÄNNÖS 2. Rajat asetettu Lightsensor luokassa.
    		    if (!DEObj.getLineDetected()) {
    		    	// A Vasen.
    		        // B Oikea.
    		        	motorA.setPower(25);
    		        	motorB.setPower(50);        
    		        	// Tietoa color sensorin arvoista.
    		        	//System.out.println("color value: " + colorValue);
    		        	//Tietoa ultra sensorin arvoista
    		        	//System.out.println("distance: " + distanceValue);
    		    }
    		    // ESTE TRUE, kun este on näkyvissä.Korjausliikkeitä.
    		    if (DEObj.getObstacleDetected()) {
    		    	// A Vasen.
    		        // B Oikea.
//    		        	180 astetta.
//    		        	motorA.setPower(50);
//    		        	motorB.setPower(-50);
//    		        	Delay.msDelay(1000);
    		    		// 90 astetta.Käännös.
		        		motorA.setPower(50);
		        		motorB.setPower(-50);
		        		Delay.msDelay(500);
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
    		            Delay.msDelay(1700);
    		            // 90 astetta.Käännös.
    		        	motorA.setPower(-50);
    		        	motorB.setPower(50);
    		        	Delay.msDelay(500);
    		        	// Jatketaan suoraan hetki.
    		            motorA.setPower(50);
    		            motorB.setPower(50);
    		            Delay.msDelay(1000);
    		            if(DEObj.getLineDetected()) {
    		            	// 90 astetta.Käännös.
    		            	motorA.stop();
    		            	motorB.stop();
//    		        		motorA.setPower(50);
//    		        		motorB.setPower(-50);
//    		        		Delay.msDelay(500);
    		            }
		        		
    		    }
    	    
    	} while (Button.getButtons() == 0);//Loop jatkuu niin kauan kunnes nappia painetaan.
	}//End of inside the thread.
}//End of class.
