package com.wander.cube.state;

import com.wander.cube.util.BitmapHandler;
import com.wander.cube.util.Robot;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;

public class AState extends CubeState {

	@Override
	public void handlePush(final CubeContext context) {
		camera.takePicture(null, null, new Camera.PictureCallback() {
			public void onPictureTaken(byte[] data, Camera camera) {
				
				Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0,
						data.length);
				BitmapHandler.handleBitmap(bitmap);
				storeImage(data, "/sdcard/AAA/aState.jpg");

				camera.startPreview();
				//
				Robot.rotatePaw();
				
				setNextStateAndPush(CubeStateFactory.STATE_B, context);
			}
		});

	}

}
