package com.wander.cube.activity;

import java.util.List;

import lejos.nxt.Motor;

import com.wander.cube.state.CubeContext;
import com.wander.cube.state.CubeState;
import com.wander.cube.state.CubeStateFactory;
import com.wander.cube.util.BlueTooth;
import com.wander.cube.util.Robot;

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
    
	//TODO test Robot
	int face = 0;
	int count = 1;
	
    
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
				//BlueTooth.connectAndPrepare();
				if(!running){
					running = true;
					CubeState nextState = CubeStateFactory.getState(CubeStateFactory.STATE_A);
					nextState.setCamera(getCamera());
					setState(nextState);
					push();
				}
				
				/*
				if(!running){
					BlueTooth.connectAndPrepare();
					running = true;
				}
				String sInput;
				//sInput = "FU LF RU BL DF DR DB DL UL BU FR BR FRU FUL BLU BUR DRF DFL DLB DBR";
				sInput = "BU FU UR LU DF DR DB DL LF BR LB RF UBL FRU ULF BUR DRF DFL DLB DBR";
				System.out.println(com.wander.cube.util.CubeSolver.GetResult(sInput));
				List<Integer> face = com.wander.cube.util.CubeSolver.getMoveList();
				List<Integer> count = com.wander.cube.util.CubeSolver.getMoveAmountList();
				
				System.out.println("total steps is " + face.size());
				for(int i =0; i < face.size(); i++){
					Robot.run(face.get(i), count.get(i));
				}
				
				
				Robot.run(0, count);
				count++;
				if(count == 4){
					face = (face + 1) % 6;
					count = 1;
				}*/
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