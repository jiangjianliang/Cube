package com.wander.cube.activity;

import com.wander.cube.state.CubeContext;
import com.wander.cube.state.CubeState;
import com.wander.cube.state.CubeStateFactory;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;

public class ScanActivity extends Activity implements CubeContext{
	private CubeState state;
	private Camera camera;
	
	private boolean running = false;
	
	private Preview preview;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//System.out.println("aaaaaaaaaaaaaaa");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scan);
		//Intent intent = getIntent();
		//System.out.println("zzzzzzzzzzzzzzz");
		preview = (Preview)findViewById(R.id.preview);
		camera = preview.mCamera;
	}

	@Override
	protected void onResume() {
		
		super.onResume();
		if(!running){
			running = true;
			CubeState nextState = CubeStateFactory.getState(CubeStateFactory.STATE_A);
			nextState.setCamera(getCamera());
			setState(nextState);
			push();
		}
	}

	@Override
	public void setState(CubeState state) {
		this.state = state;
	}

	@Override
	public void push() {
		state.handlePush(this);
	}

	@Override
	public Camera getCamera() {
		return camera;
	}

}
