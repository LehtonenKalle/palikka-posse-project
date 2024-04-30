package data;

import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.utility.Delay;

public class MotorsManual implements Runnable {
	private DataExchange DEObj = new DataExchange();
    SharedData sd = new SharedData();
    
    UnregulatedMotor motorA = new UnregulatedMotor(MotorPort.A); // LEFT
    UnregulatedMotor motorB = new UnregulatedMotor(MotorPort.B); // RIGHT
	
	public MotorsManual(DataExchange de, SharedData sd) {
		this.DEObj = de;
		this.sd = sd;
	}
	
	@Override
	public void run() {
		while (true) {
			//System.out.println("MotorA value: " + sd.getMotorAValue());
			//System.out.println("MotorB value: " + sd.getMotorBValue());
			motorA.setPower(sd.getMotorAValue());
			motorB.setPower(sd.getMotorBValue());
			
			Delay.msDelay(1000);
		}
		// TODO Auto-generated method stub


	}

}
