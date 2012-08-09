package com.wander.cube.state;

public class CubeStateFactory {

	public final static int STATE_A = 1;
	public final static int STATE_B = 2;
	public final static int STATE_C = 4;
	public final static int STATE_D = 8;
	public final static int STATE_E = 16;
	public final static int STATE_F = 32;
	public final static int STATE_G = 64;
	public final static int STATE_H = 128;
	public final static int STATE_I = 256;
	public final static int STATE_J = 512;

	public static CubeState getState(int option) {
		CubeState state = null;
		switch (option) {
		case STATE_A:
			state = new AState();
			break;
		case STATE_B:
			state = new BState();
			break;
		case STATE_C:
			state = new CState();
			break;
		case STATE_D:
			state = new DState();
			break;
		case STATE_E:
			state = new EState();
			break;
		case STATE_F:
			state = new FState();
			break;
		case STATE_G:
			state = new GState();
			break;
		case STATE_H:
			state = new HState();
			break;
		case STATE_I:
			state = new IState();
			break;
		case STATE_J:
			state = new JState();
			break;
		default:
		}
		return state;
	}
}
