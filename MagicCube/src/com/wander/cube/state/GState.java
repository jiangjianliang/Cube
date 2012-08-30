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
		
		Log.i(TAG, "begin solving");
		String cubeString = MagicCube.getCubeString();
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
		
		setNextStateAndPush(CubeStateFactory.STATE_G, context);
	}
	
}
