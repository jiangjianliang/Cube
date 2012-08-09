package com.wander.cube.state;

import android.hardware.Camera;

public interface CubeContext {
	
	public void setState(CubeState state);
	public void push();
	public Camera getCamera();
	
}
