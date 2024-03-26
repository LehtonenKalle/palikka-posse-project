package app;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class LejosApp {

    private static EV3UltrasonicSensor us = new EV3UltrasonicSensor(SensorPort.S4);
    private static EV3ColorSensor cs = new EV3ColorSensor(SensorPort.S3);


    public static void main(String[] args) {

        final SampleProvider distanceSP = us.getDistanceMode();
        final SampleProvider colorSP = cs.getRedMode();
        
        int distanceValue = 0;
        UnregulatedMotor motorA = new UnregulatedMotor(MotorPort.A);
        UnregulatedMotor motorB = new UnregulatedMotor(MotorPort.B);
        
        
        Button.LEDPattern(4);
        
        Sound.beepSequenceUp();
        
        Button.waitForAnyPress();
        
        // Ultrasensor loop
//        while(true) {
//            float [] sample = new float[distanceSP.sampleSize()];
//            distanceSP.fetchSample(sample, 0);
//            distanceValue = (int)(sample[0]*100);
//
//            System.out.println("Distance: " + distanceValue);
//            Delay.msDelay(500);
//            
//            if(Button.getButtons()!=0) {
//                break;
//            }
//            
//        }
        

      System.out.println("Distance: " + distanceValue);
       
        // --------------------------------------------------------------
        
        // Lightsensor loop
        while(true) {
            cs.setFloodlight(true);
  
            // colorsensor 
            float[] colorSample = new float[colorSP.sampleSize()];
            colorSP.fetchSample(colorSample, 0);
            double colorValue = colorSample[0];
            
            // ultrasensor
            float [] distanceSample = new float[distanceSP.sampleSize()];
            distanceSP.fetchSample(distanceSample, 0);
            distanceValue = (int)(distanceSample[0]*100);
           
            
            /*if (colorValue <= 0.12 && colorValue >= 0.08) {
            	motorA.setPower(100);
            	motorB.setPower(100);
            	
            	System.out.println(colorValue);
            }*/
            
            if (distanceValue < 7) {
            	// take a turn
            	motorA.setPower(50);
            	motorB.setPower(-50);
            	Delay.msDelay(500);
            	do {
                	// go straight again
                	motorA.setPower(30);
                	motorB.setPower(50);
            	} while(colorValue > 0.1);

            	motorA.setPower(-50);
            	motorB.setPower(50);
            	Delay.msDelay(300);
            }
             
            if (colorValue < 0.1) {
            	// Left
            	motorA.setPower(50);
            	// Right
            	motorB.setPower(25);        
            	
            	//System.out.println("color value: " + colorValue);
            	System.out.println("distance: " + distanceValue);
            }
          
            if (colorValue >= 0.1) {
            	motorA.setPower(25);
            	motorB.setPower(50);   
            	//System.out.println("color value: " + colorValue);
            	System.out.println("distance: " + distanceValue);
            }
         
            
           
            if (Button.getButtons()!= 0) {
                motorA.close();
                motorB.close();
                break;
            }
        }
        
        // --------------------------------------------------------------

        

        

    }

}
