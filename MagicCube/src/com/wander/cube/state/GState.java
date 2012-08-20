package com.wander.cube.state;

import java.util.List;

import com.wander.cube.util.CubeSolver;
import com.wander.cube.util.MagicCube;
import com.wander.cube.util.Robot;

import android.hardware.Camera;
import android.util.Log;

public class GState extends CubeState {

	private static final String TAG = "GState";

	@Override
	public void handlePush(CubeContext context) {
		//TODO 获取前面得到的颜色数组,进行验证
		
		Log.i(TAG, "begin solving");
		String cubeString = MagicCube.constructCubeString();
		if (cubeString == null  || cubeString.equals("")){
			Log.e(TAG, "fail to get cube string");
		}
		CubeSolver.GetResult(cubeString);
		List<Integer> face = CubeSolver.getMoveList();
		List<Integer> count = CubeSolver.getMoveAmountList();
		
		Log.i(TAG, "total steps is " + face.size());
		for(int i =0; i < face.size(); i++){
			Robot.run(face.get(i), count.get(i));
		}
		//set next state
		CubeState nextState = CubeStateFactory.getState(CubeStateFactory.STATE_H);
		nextState.setCamera(context.getCamera());
		context.setState(nextState);
		context.push();
	}

}
