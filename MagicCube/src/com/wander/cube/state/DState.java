package com.wander.cube.state;

import java.io.File;
import java.io.FileOutputStream;

import com.wander.cube.util.BlueTooth;
import com.wander.cube.util.Robot;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;

public class DState extends CubeState {

	@Override
	public void handlePush(final CubeContext context) {
		camera.takePicture(null, null, new Camera.PictureCallback() {
			
			public void onPictureTaken(byte[] data, Camera camera) {
				//TODO 这里对照片进行处理，获取颜色
				//作为测试，这里先采用将图片写入到文件的作法
				try {
					Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
					/*
					for(int i = 1; i < 100; i++){
						System.out.println(bitmap.getPixel(i, i));
					}
					*/
					File jpgFile = new File("/sdcard/AAA/dState.jpg");
					FileOutputStream outStream = new FileOutputStream(jpgFile);
					outStream.write(data);
					outStream.close();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				camera.startPreview();
				//转动马达
				Robot.rotateBottom(-1);
				Robot.rotatePaw();
				//set next state
				CubeState nextState = CubeStateFactory.getState(CubeStateFactory.STATE_E);
				nextState.setCamera(context.getCamera());
				context.setState(nextState);
				context.push();
				
			}
		});

	}

}
