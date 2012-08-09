package com.wander.cube.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MagicCubeActivity extends Activity {
    private Button scanBtn;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initConponent();
    }
    
    private void initConponent(){
    	scanBtn = (Button)findViewById(R.id.scan);
    	scanBtn.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent intent = new Intent(getBaseContext(), ScanActivity.class);
				startActivity(intent);
			}
		});
    }
    
}