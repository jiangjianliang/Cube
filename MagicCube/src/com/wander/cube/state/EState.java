package com.wander.cube.state;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;

import com.wander.cube.robot.Robot;
import com.wander.cube.util.BitmapHandler;

public class EState extends CubeState {

	@Override
	public void handlePush(final CubeContext context) {
		camera.takePicture(null, null, new Camera.PictureCallback() {

			public void onPictureTaken(byte[] data, Camera camera) {

				Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0,
						data.length);
				BitmapHandler.handleBitmap(bitmap);

				storeImage(data, "/sdcard/AAA/eState.jpg");

				camera.startPreview();
				// 转动马达
				Robot.rotatePaw();
				Robot.rotatePaw();
				Robot.rotateBottom(2);
				
				setNextStateAndPush(CubeStateFactory.STATE_F, context);

			}
		});
	}

}
