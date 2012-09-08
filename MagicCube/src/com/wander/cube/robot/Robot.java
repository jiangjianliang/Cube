package com.wander.cube.robot;

import lejos.nxt.Motor;
import lejos.nxt.remote.RemoteMotor;

public class Robot {

	private static final int BaseQuarter = -270;

	private static final int PawHoldPosition = 0;
	private static final int PawBeforeTurnOverPosition = -115;
	private static final int PawAfterTurnOverPosition = -180;
	private static int BaseRotateFix = -50;

	private static RemoteMotor paw = Motor.A;
	private static RemoteMotor bottom = Motor.B;
	//private static RemoteMotor camera = Motor.C;

	/**
	 * 
	 * @param face
	 *            0-5,FBRLUD
	 */
	public static void run(int face, int count) {
		count = count % 4;
		switch (face) {
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
	 * F -> B'PPPS'PB FF -> B'PPPS'S'PB FFF -> B'PPPSPB
	 */
	public static void slideF(int count) {
		rotateBottom(-1);
		rotatePaw();
		rotatePaw();
		rotatePaw();

		slideBottom(count);

		rotatePaw();
		rotateBottom(1);
	}

	private static void slideBottom(int count) {
		switch (count) {
		case 1:
			rotateBottomSide(-1);
			break;
		case 2:
			rotateBottomSide(-2);
			break;
		case 3:
			rotateBottomSide(1);
			break;
		default:
			;
		}
	}

	/**
	 * B -> BPPPxPB'
	 */
	public static void slideB(int count) {
		rotateBottom(1);
		rotatePaw();
		rotatePaw();
		rotatePaw();

		slideBottom(count);

		rotatePaw();
		rotateBottom(-1);
	}

	/**
	 * R -> PPPxP
	 */
	public static void slideR(int count) {
		rotatePaw();
		rotatePaw();
		rotatePaw();

		slideBottom(count);

		rotatePaw();
	}

	/**
	 * L -> PxPPP
	 */
	public static void slideL(int count) {
		rotatePaw();

		slideBottom(count);

		rotatePaw();
		rotatePaw();
		rotatePaw();
	}

	/**
	 * U -> PPxPP
	 */
	public static void slideU(int count) {
		rotatePaw();
		rotatePaw();

		slideBottom(count);

		rotatePaw();
		rotatePaw();
	}

	/**
	 * D -> x
	 */
	public static void slideD(int count) {

		slideBottom(count);

	}

	public static void rotatePaw() {
		paw.setSpeed(400);
		paw.rotateTo(PawBeforeTurnOverPosition);
		// paw.setSpeed(400);
		paw.rotateTo(PawAfterTurnOverPosition);
		paw.rotateTo(PawBeforeTurnOverPosition);
		paw.rotateTo(PawHoldPosition);
	}

	public static void rotateBottom(int quarter) {
		bottom.rotate(quarter * BaseQuarter);
	}

	/**
	 * quarter > 0 clockwise < 0 anti clockwise
	 */
	public static void rotateBottomSide(int quarter) {
		if (quarter == 0)
			return;
		paw.setSpeed(400);
		paw.rotateTo(PawBeforeTurnOverPosition);

		int fixAngle = BaseRotateFix * ((quarter > 0) ? 1 : -1);
		bottom.rotate(quarter * BaseQuarter + fixAngle);
		bottom.rotate(-fixAngle);

		paw.rotateTo(PawHoldPosition);
	}

	@Deprecated
	public static void moveCameraForward() {

	}

	@Deprecated
	public static void moveCameraBackward() {

	}
}
