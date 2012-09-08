package com.wander.cube.state;

import java.util.List;

import android.util.Log;

import com.wander.cube.robot.Robot;
import com.wander.cube.util.BlueTooth;
import com.wander.cube.util.CubeSolver;
import com.wander.cube.util.MagicCube;

public class SolveState extends CubeState {

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
		
		//test
		//SmartRobot.addMoveStep(face, count);
		//SmartRobot.run();
		
		
		for(int i =0; i < face.size(); i++){
			Robot.run(face.get(i), count.get(i));
		}
		
		//BlueTooth.close();
		//setNextStateAndPush(CubeStateFactory.STATE_G, context);
	}
	
}
