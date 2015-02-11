package org.usfirst.frc.team2517.robot;

import org.usfirst.frc.team2517.robot.SwerveModule;
import java.util.ArrayList;
import java.lang.Math;

public class SwerveController {
	SwerveModule swerveFL; // Initialize one object for each module. Format is turnJag, moveJag, encID
	SwerveModule swerveFR;
	SwerveModule swerveBL;
	SwerveModule swerveBR;
	
	public SwerveController(int moveFL, int turnFL, int encFL,
							int moveFR, int turnFR, int encFR, 
							int moveBL, int turnBL, int encBL,
							int moveBR, int turnBR, int encBR){
		swerveFL = new SwerveModule(moveFL, turnFL, encFL, 0.7421, 0.6703);
		swerveFR = new SwerveModule(moveFR, turnFR, encFR, 0.6703, -0.7421);
		swerveBL = new SwerveModule(moveBL, turnBL, encBL, -0.6703, 0.7421);
		swerveBR = new SwerveModule(moveBR, turnBR, encBR, -0.7421, -0.6703);
	}
	
	public void swerve(double xVector, double yVector, double phi){
		swerveFL.x = (swerveFL.corX*phi)+xVector;
		swerveFL.y = (swerveFL.corY*phi)+yVector; // Someday outsource these into a SwerveModule function and run 4 times instead of 8
		swerveFR.x = (swerveFR.corX*phi)+xVector;
		swerveFR.y = (swerveFR.corY*phi)+yVector;
		swerveBL.x = (swerveBL.corX*phi)+xVector;
		swerveBL.y = (swerveBL.corY*phi)+yVector;
		swerveBR.x = (swerveBR.corX*phi)+xVector;
		swerveBR.y = (swerveBR.corY*phi)+yVector;
		
		// Find magnitudes through pythagorean therom
		swerveFL.mag = Math.sqrt(Math.pow(swerveFL.x,2) + Math.pow(swerveFL.y, 2));
		swerveFR.mag = Math.sqrt(Math.pow(swerveFR.x,2) + Math.pow(swerveFR.y, 2));
		swerveBL.mag = Math.sqrt(Math.pow(swerveBL.x,2) + Math.pow(swerveBL.y, 2));
		swerveBR.mag = Math.sqrt(Math.pow(swerveBR.x,2) + Math.pow(swerveBR.y, 2));
	}
}
