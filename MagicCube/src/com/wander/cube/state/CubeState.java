package com.wander.cube.state;

import java.io.File;
import java.io.FileOutputStream;

import android.hardware.Camera;

public abstract class CubeState {

	protected Camera camera;

	public void setCamera(Camera camera) {
		this.camera = camera;
	}

	public void setNextStateAndPush(int next, CubeContext context){
		CubeState nextState = CubeStateFactory
				.getState(next);
		nextState.setCamera(context.getCamera());
		context.setState(nextState);
		context.push();
	}
	
	public void storeImage(byte[] data, String filePath) {
		try {
			File jpgFile = new File(filePath);
			FileOutputStream outStream = new FileOutputStream(jpgFile);
			outStream.write(data);
			outStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	};

	public abstract void handlePush(CubeContext context);

}
