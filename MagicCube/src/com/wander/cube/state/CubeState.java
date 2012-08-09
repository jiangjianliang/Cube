package com.wander.cube.state;

import android.hardware.Camera;

public abstract class CubeState {
	
	protected Camera camera;
	
	public void setCamera(Camera camera) {
		this.camera = camera;
	}
	public abstract void handlePush(CubeContext context);
	
}
