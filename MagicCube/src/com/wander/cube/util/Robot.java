package com.wander.cube.util;

import lejos.nxt.Motor;
import lejos.nxt.remote.RemoteMotor;

public class Robot {
	
	private static final int BaseQuarter = -270;

	private static final int PawHoldPosition = 0;
	private static final int PawTurnOverPosition = -100;
	
	private static int BaseRotateFix = -50;
	
	private static RemoteMotor paw=Motor.A;
	private static RemoteMotor bottom=Motor.B;
	
	//private static RemoteMotor monitor=Motor.C;
	/**
	 * 
	 * @param face 0-5,FBRLUD
	 */
	public static void run(int face, int count){
		count = count % 4;
		switch(face){
		case 0:
			slideF(count);
			break;
		case 1:
			slideB(count);
			break;
		case 2:
			slideR(count);
			break;
		case 3:
			slideL(count);
			break;
		case 4:
			slideU(count);
			break;
		case 5:
			slideD(count);
			break;
		default:
			;
		}
	}
	
	/**
	 * F -> B'PPPS'PB
	 * FF -> B'PPPS'S'PB
	 * FFF -> B'PPPSPB
	 */
	public static void slideF(int count){
		RotateBottom(-1);
		RotatePaw();
		RotatePaw();
		RotatePaw();
		
		slideBottom(count);
		
		RotatePaw();
		RotateBottom(1);
	}
	
	private static void slideBottom(int count){
		switch(count){
		case 1:
			RotateBottomSide(-1);
			break;
		case 2:
			RotateBottomSide(-2);
			break;
		case 3:
			RotateBottomSide(1);
			break;
		default:
				;
		}
	}
	/**
	 * B -> BPPPxPB'
	 */
	public static void slideB(int count){
		RotateBottom(1);
		RotatePaw();
		RotatePaw();
		RotatePaw();
		
		slideBottom(count);
		
		RotatePaw();
		RotateBottom(-1);
	}
	/**
	 * R -> PPPxP
	 */
	public static void slideR(int count){
		RotatePaw();
		RotatePaw();
		RotatePaw();
		
		slideBottom(count);
		
		RotatePaw();
	}
	/**
	 * L -> PxPPP
	 */
	public static void slideL(int count){
		RotatePaw();
		
		slideBottom(count);
		
		RotatePaw();
		RotatePaw();
		RotatePaw();
	}
	/**
	 * U -> PPxPP
	 */
	public static void slideU(int count){
		RotatePaw();
		RotatePaw();
		
		slideBottom(count);
		
		RotatePaw();
		RotatePaw();
	}
	
	/**
	 * D -> x
	 */
	public static void slideD(int count){
		
		slideBottom(count);
		
	}
	
	public static void RotatePaw(){
		paw.setSpeed(300);
		paw.rotateTo(PawHoldPosition);
		paw.setSpeed(400);
		paw.rotateTo(PawTurnOverPosition);
		//paw.setSpeed(400);
		paw.rotateTo(-180);
		paw.rotateTo(-90);
		paw.rotateTo(PawHoldPosition);
	}
	
	public static void RotateBottom(int quarter)
	{
		bottom.rotate(quarter * BaseQuarter);
	}	
	
	/**
	 * quarter > 0 clockwise
	 * < 0 anti clockwise
	 */
	public static void RotateBottomSide(int quarter){
		if(quarter == 0) 
			return;
		paw.setSpeed(400);
		paw.rotateTo(PawTurnOverPosition);
		
		int fixAngle = BaseRotateFix * ((quarter > 0)?1:-1);
		bottom.rotate(quarter * BaseQuarter + fixAngle);
		bottom.rotate(-fixAngle);
		
		paw.rotateTo(PawHoldPosition);
	}
	
}
