package com.wander.cube.state;

import android.hardware.Camera;

public class CState extends CubeState {

	@Override
	public void handlePush(CubeContext context) {
		// TODO Auto-generated method stub
		camera.takePicture(null, null, new Camera.PictureCallback() {
			
			public void onPictureTaken(byte[] data, Camera camera) {
				// TODO Auto-generated method stub
				
			}
		});
	}

}
