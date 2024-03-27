package data;

import lejos.hardware.Button;
import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.utility.Delay;

public class Motors implements Runnable{
	//------------------------------------------------------------------------------
	// Outside the Thread
	//------------------------------------------------------------------------------
//Huom. Sisälinjaan!
//	Huom. 0.22 kokonaan valkonen
//	Huom. 0.04 kokonaan musta
//	Huom. 0.1 mustan ja valkosen välissä
// 	Huom. A = Vasen JA B = Oikea	
//While looppi, jossa tarkistetaan
//	DataExchange.isLineDetected()
//	SEKÄ
//	DataExchange.isObjectDetected()
//		1. jos ei LINJAA ole niin tehdään korjausliikkeitä TRUE
//		2. jos LINJA kiikarissa jatketaan eteenpäin FALSE
	
	//Moottorit pysähtyy
    //motorA.stop();
    //motorB.stop();
	private int speed;
		

	// Luo kaksi moottori objektia.
	// Vasen.
    UnregulatedMotor motorA = new UnregulatedMotor(MotorPort.A);
    // Oikea.
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
	    // Jos nopeus on hyväksytyissä rajoissa, asetetaan se.
	    else {
	        this.speed = speed;
	    }
	    
	    // TRUE kun linja on näkyvissä.Korjausliikkeitä.
	    if (DataExchange.isLineDetected()) {
	        //KÄÄNNÖS 1.
	         
	        	motorA.setPower(50);
	        	motorB.setPower(25);        
	        	// Tietoa color sensorin arvoista.
	        	//System.out.println("color value: " + colorValue);
	        	//Tietoa ultra sensorin arvoista
	        	//System.out.println("distance: " + distanceValue);
	        
	    }
        //KÄÄNNÖS 2.
    	else {
    		motorA.setPower(25);
        	motorB.setPower(50);   
        	// Tietoa color sensorin arvoista.
        	//System.out.println("color value: " + colorValue);
        	//Tietoa ultra sensorin arvoista
        	//System.out.println("distance: " + distanceValue);
    	}
   
	    
	    
	    
	    
	    
	    
	    
	    // TRUE kun linja on näkyvissä.Täysillä eteenpäin.
	    while (DataExchange.isLineDetected()) {
	    	motorA.setPower(this.speed);
	        motorB.setPower(this.speed);
	    }
	    
	    // FALSE kun este ei ole näkyvissä.Täysillä eteenpäin.
	    while (!DataExchange.isObjectDetected()) {
	        motorA.setPower(this.speed);
	        motorB.setPower(this.speed);
	    }
	    
	    // TRUE kun este on näkyvissä.Korjausliikkeitä.
	    if (DataExchange.isObjectDetected()) {
//	    	VANHA-----------------------------------------
//		    if (distanceValue < 7) {
//	        	// take a turn
//	        	motorA.setPower(50);
//	        	motorB.setPower(-50);
//	        	Delay.msDelay(500);
//	        	do {
//	            	// go straight again
//	            	motorA.setPower(30);
//	            	motorB.setPower(50);
//	        	} while(colorValue > 0.1);
//
//	        	motorA.setPower(-50);
//	        	motorB.setPower(50);
//	        	Delay.msDelay(300);
//	        }
//	    	VANHA-----------------------------------------
	    }
     
	    // Pysähtyminen.
	    if (Button.getButtons()!= 0) {
//	    	VANHA-----------------------------------------
//		    motorA.close();
//          motorB.close();
//          break;
//	    	VANHA-----------------------------------------
	    }
    }//End of setter speed.
	    
	    
	    
	    
	
	@Override
	public void run() {
		//------------------------------------------------------------------------------
		// Inside the Thread
		//------------------------------------------------------------------------------b
		
	}
	
	
	

}
