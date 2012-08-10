package com.wander.cube.activity;

import com.wander.cube.state.CubeContext;
import com.wander.cube.state.CubeState;
import com.wander.cube.state.CubeStateFactory;
import com.wander.cube.util.BlueTooth;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MagicCubeActivity extends Activity implements CubeContext{
    private Button scanBtn;
    private Preview preview;
    
    private CubeState state;
	private Camera camera;
	
	private boolean running = false;
    
    
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initConponent();
    }
    
    private void initConponent(){
    	preview = (Preview)findViewById(R.id.preview);
    	scanBtn = (Button)findViewById(R.id.scan);
    	scanBtn.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				scanBtn.setEnabled(false);
				//scanBtn.setVisibility(View.INVISIBLE);
				camera = preview.mCamera;
				BlueTooth.connectAndPrepare();
				if(!running){
					running = true;
					CubeState nextState = CubeStateFactory.getState(CubeStateFactory.STATE_A);
					nextState.setCamera(getCamera());
					setState(nextState);
					push();
				}
			}
		});
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