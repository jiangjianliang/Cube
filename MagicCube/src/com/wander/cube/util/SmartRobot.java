package com.wander.cube.util;

import java.util.ArrayList;
import java.util.List;

import lejos.nxt.Motor;
import lejos.nxt.remote.RemoteMotor;

public class SmartRobot {
	private static final int BaseQuarter = -270;

	private static final int PawHoldPosition = 0;
	private static final int PawBeforeTurnOverPosition = -115;
	private static final int PawAfterTurnOverPosition = -185;

	private static int BaseRotateFix = -50;

	private static RemoteMotor paw = Motor.A;
	static {
		paw.setSpeed(400);
	}
	private static RemoteMotor bottom = Motor.B;
	private static RemoteMotor camera = Motor.C;

	private static final int FaceF = 0;
	private static final int FaceB = 1;
	private static final int FaceR = 2;
	private static final int FaceL = 3;
	private static final int FaceU = 4;
	private static final int FaceD = 5;

	private static final int ActionP = 8;
	private static final int ActionB = 16;
	private static final int ActionS = 32;

	private static List<Integer> actionList;
	private static List<Integer> timesList;
	private static boolean[] actionFlag;

	public static void addMoveStep(List<Integer> faces, List<Integer> moves) {

		boolean[] faceFlag = new boolean[faces.size()];
		for (int i = 0; i < faceFlag.length; i++) {
			faceFlag[i] = true;
		}
		// sum up the same adjacent face's moves
		for (int i = 0; i < faces.size() - 1; i++) {
			if (faces.get(i) == faces.get(i + 1)) {
				faceFlag[i] = false;
				int sum = moves.get(i) + moves.get(i + 1);
				moves.set(i + 1, sum);
			}
		}
		
		actionList = new ArrayList<Integer>();
		timesList = new ArrayList<Integer>();
		
		// convert FBRLUD to PBS
		for (int i = 0; i < faceFlag.length; i++) {
			if (faceFlag[i] == false)
				continue;
			int amount = moves.get(i) % 4;
			//
			if (amount == 0)
				continue;

			switch (faces.get(i)) {
			case FaceF:
				convFaceF(amount);
				break;
			case FaceB:
				convFaceB(amount);
				break;
			case FaceR:
				convFaceR(amount);
				break;
			case FaceL:
				convFaceL(amount);
				break;
			case FaceU:
				convFaceU(amount);
				break;
			case FaceD:
				convFaceD(amount);
				break;
			default:
				;
			}
		}

		// sum up the same adjacent action's amount
		actionFlag = new boolean[actionList.size()];
		for (int i = 0; i < actionFlag.length; i++) {
			actionFlag[i] = true;
		}

		for (int i = 0; i < actionList.size() - 1; i++) {
			if (actionList.get(i) == actionList.get(i + 1)) {
				actionFlag[i] = false;
				int sum = timesList.get(i) + timesList.get(i + 1);
				timesList.set(i + 1, sum);
			}
		}
	}

	private static void convFaceF(int amount) {
		actionList.add(ActionB);
		timesList.add(-1);

		actionList.add(ActionP);
		timesList.add(3);

		convActionS(amount);

		actionList.add(ActionP);
		timesList.add(1);

		actionList.add(ActionB);
		timesList.add(1);
	}

	private static void convActionS(int amount) {
		actionList.add(ActionS);
		if (amount == 3)
			amount = 1;
		else
			amount = -amount;
		timesList.add(amount);
	}

	private static void convFaceB(int amount) {
		actionList.add(ActionB);
		timesList.add(1);

		actionList.add(ActionP);
		timesList.add(3);

		convActionS(amount);

		actionList.add(ActionP);
		timesList.add(1);

		actionList.add(ActionB);
		timesList.add(-1);
	}

	private static void convFaceR(int amount) {
		actionList.add(ActionP);
		timesList.add(3);

		convActionS(amount);

		actionList.add(ActionP);
		timesList.add(1);
	}

	private static void convFaceL(int amount) {
		actionList.add(ActionP);
		timesList.add(1);

		convActionS(amount);

		actionList.add(ActionP);
		timesList.add(3);
	}

	private static void convFaceU(int amount) {
		actionList.add(ActionP);
		timesList.add(2);

		convActionS(amount);

		actionList.add(ActionP);
		timesList.add(2);
	}

	private static void convFaceD(int amount) {
		convActionS(amount);
	}

	public static void run() {
		
		actionList.add(0, ActionB);
		actionList.add(ActionB);
		
		for (int i = 1; i < actionFlag.length-1; i++) {
			if (actionFlag[i-1] == false)
				continue;
			runPBS(actionList.get(i-1), actionList.get(i+1), actionList.get(i), timesList.get(i));
		}
	}

	/**
	 * 
	 * @param previous
	 *            , previous operation
	 * @param next
	 *            , next operation
	 * @param current
	 *            , current operation
	 * @param times
	 *            , current operation times
	 */
	private static void runPBS(int previous, int next, int current, int times) {
		// 90*4 == 0
		times = times % 4;
		if (times == 0)
			return;

		switch (current) {
		case ActionP:
			runP(previous, next, times);
			break;
		case ActionB:
			runB(times);
			break;
		case ActionS:
			runS(previous, next, times);
			break;
		default:
			;
		}
	}

	private static void runP(int previous, int next, int times) {
		if (previous == ActionB) {
			paw.rotateTo(PawBeforeTurnOverPosition);
		}

		for (int i = 0; i < times; i++) {
			paw.rotateTo(PawAfterTurnOverPosition);
			paw.rotateTo(PawBeforeTurnOverPosition);
		}

		if (next == ActionB) {
			paw.rotateTo(PawHoldPosition);
		}

	}

	private static void runB(int times) {
		times = shiftTimes(times);
		bottom.rotate(times * BaseQuarter);
	}

	private static int shiftTimes(int times) {
		// clockwise 270 = anti-clockwise 90
		if (times == 3) {
			return -1;
		} else if (times == -3) {
			return 1;
		}
		return times;
	}

	private static void runS(int previous, int next, int times) {
		if (previous == ActionB) {
			paw.rotateTo(PawBeforeTurnOverPosition);
		}

		times = shiftTimes(times);

		int fixAngle = BaseRotateFix * ((times > 0) ? 1 : -1);
		bottom.rotate(times * BaseQuarter + fixAngle);
		bottom.rotate(-fixAngle);

		if (next == ActionB) {
			paw.rotateTo(PawHoldPosition);
		}
	}

	public static void rotatePaw() {
		// paw.setSpeed(400);
		paw.rotateTo(PawBeforeTurnOverPosition);
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
	@Deprecated
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
}
