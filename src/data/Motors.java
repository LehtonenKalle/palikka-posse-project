package data;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.utility.Delay;

public class Motors implements Runnable{
	//------------------------------------------------------------------------------
	// Outside the Thread
	//------------------------------------------------------------------------------
	// Alustetaan nopeus atribuutti.
	private int speed;
	
	// Luo kaksi moottori objektia.
	// A Vasen.
    UnregulatedMotor motorA = new UnregulatedMotor(MotorPort.A);
    // B Oikea.
    UnregulatedMotor motorB = new UnregulatedMotor(MotorPort.B);
    
	public int getSpeed() {
		return speed;
	}
	
	public void setSpeed(int speed) {
	    // Onko nopeus enemmän kuin 100? Rajoita siihen.
	    if (speed > 100) {
	        this.speed = 100;
	    }
	    // Onko nopeus vähemmän kuin -100? Rajoita siihen.
	    else if (speed < -100) {
	        this.speed = -100;
	    }
	    // Jos nopeus on hyväksytyissä rajoissa, asetetaan se ja jatketaan eteenpäin.
	    else {
	        this.speed = speed;
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
        	    } else {
        	    	// LINJA TRUE, Linja näkyvissä. KÄÄNNÖS 1. Rajat asetettu Lightsensor luokassa.
        		    if (DataExchange.getLineDetected()) {
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
        		    if (!DataExchange.getLineDetected()) {
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
        		    if (DataExchange.getObstacleDetected()) {
        		    	// A Vasen.
        		        // B Oikea.
        		        	// Nopea suunnan muutos pois päin esteestä. 90 astetta.
        		        	motorA.setPower(50);
        		        	motorB.setPower(-50);
        		        	Delay.msDelay(1000);
        		        	// Jatketaan suoraan hetki.
        		            motorA.setPower(50);
        		            motorB.setPower(50);
        		            Delay.msDelay(1000);
        		        	// Nopea suunnan muutos takaisin viivalle. 90 astetta.
        		        	motorA.setPower(-50);
        		        	motorB.setPower(50);
        		        	Delay.msDelay(1000);
        		    }
        	    }
        	} while (Button.getButtons() == 0);//Loop jatkuu niin kauan kunnes nappia painetaan.
	    }  
    }//End of setter speed.
	   
	@Override
	public void run() {
		//------------------------------------------------------------------------------
		// Inside the Thread
		//------------------------------------------------------------------------------
		
	}
}//End of class.
