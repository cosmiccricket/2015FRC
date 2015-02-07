package org.usfirst.frc.team2517.robot;
import java.lang.Math;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANJaguar;

/* *
 * We want the swerve module class to
 * 1. Initialize 2 CANJaguars as parameters
 * 2. Directly receive direction and magnitude variables through a function and move the 
 * motorTurn jaguar until it has reached its goal
 * 3. Look nice and have comments
 * 
 * You will need to basically rip code from last year's math code and convert it to java
 * 
 * */

public class SwerveModule {
	public CANJaguar turnJag, moveJag;
	public AnalogInput encoder;
	private double[] Cor = new double[2]; //Cor = center of rotation
	double turnSpeed;
	private static double minVoltage = 0.2;
	public SwerveModule(int tJagID, int mJagID, int eID)
	{
		turnJag = new CANJaguar(tJagID);
		moveJag = new CANJaguar(mJagID);
		encoder = new AnalogInput(eID);
	}
	public void update(double targetAngle, double magnitude)
	{
		moveJag.set(magnitude);
		double currentAngle = encoder.getValue();
		if (Utils.deadband(targetAngle - currentAngle, Math.PI / 90) == 0){
			turnJag.set(0); //applied deadband
		}
		else if (Math.abs(targetAngle - currentAngle) > 2.5 && targetAngle > currentAngle){
			turnJag.set(-(currentAngle + (5-targetAngle))/2.5 * (1 - minVoltage) - minVoltage);
		}
		else if (Math.abs(targetAngle - currentAngle) > 2.5 && targetAngle < currentAngle){
			turnJag.set((currentAngle + (5-targetAngle)) / 2.5 * (1-minVoltage) + minVoltage);
		}
		else if (Math.abs(targetAngle - currentAngle) < 2.5 && targetAngle > currentAngle){
			turnJag.set((targetAngle - currentAngle) / 2.5 * (1-minVoltage) + minVoltage);
		}
		else if (Math.abs(targetAngle-currentAngle) < 2.5 && targetAngle < currentAngle)
		{
			turnJag.set(-(targetAngle - currentAngle ) / 2.5 *(1-minVoltage) - minVoltage);
		}
		//passing in voltage into motor control. Turn direction calculated based on the 
	}
	public void setCOR(double centerX, double centerY){
		Cor[0] = centerX;
		Cor[1] = centerY;
	}
	public double[] COR(){
		return Cor;
	}
}
