package com.wander.cube.state;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.hardware.Camera;
import android.os.Environment;

public class AState extends CubeState {
	

	@Override
	public void handlePush(final CubeContext context) {
		camera.takePicture(null, null, new Camera.PictureCallback() {
			public void onPictureTaken(byte[] data, Camera camera) {
				//TODO 这里对照片进行处理，获取颜色
				//作为测试，这里先采用将图片写入到文件的作法
				try {
					File jpgFile = new File(Environment.getExternalStorageDirectory()+"AAA/", "aState.jpg");
					FileOutputStream outStream = new FileOutputStream(jpgFile);
					outStream.write(data);
					outStream.close();
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				camera.startPreview();
				//TODO 转动马达
				
				//set next state
				CubeState nextState = CubeStateFactory.getState(CubeStateFactory.STATE_B);
				nextState.setCamera(context.getCamera());
				context.setState(nextState);
				context.push();
			}
		});
		
	}

}
