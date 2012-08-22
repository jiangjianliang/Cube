package com.wander.cube.util;

import android.util.Log;

public class SimpleColor {
	private int red;
	private int green;
	private int blue;
	private int color;
	
	private static final int Red = 0;
	private static final int Green = 1;
	private static final int Blue = 2;
	private static final int Orange = 3;
	private static final int Yellow = 4;
	private static final int White = 5;
	private static final String TAG = "SimpleColor";
	
	public SimpleColor(int red, int green, int blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
		convertColor();
	}

	private void convertColor(){
		int max = red;
		if(max < green)
			max = green;
		if(max < blue)
			max = blue;
		
		float redFactor = convToInt(red*(float)1.0/max);
		float greenFactor = convToInt(green*(float)1.0/max);
		float blueFactor = convToInt(blue*(float)1.0/max);
		if( redFactor == 0){
			if(blueFactor == 1)
				color = Blue;
			if(greenFactor == 1)
				color = Green;
		}
		else{
			if( greenFactor == 1){
				if( blueFactor == 0)
					color = Yellow;
				else
					color = White;
			}
			else if( greenFactor == 0){
				color = Red;
			}
			else{
				color = Orange;
			}
		}
		Log.i(TAG, "("+red + ","+ green +","+ blue + ")--("+redFactor + ","+ greenFactor +","+ blueFactor + ")--"+color);
	}
	
	private float convToInt(float num){
		if(num < 0.3)
			return 0;
		else if(num > 0.7)
			return 1;
		else
			return num;
	}
	
	public int distanceTo(SimpleColor color) {
		int diffRed = red - color.red;
		int diffGreen = green - color.green;
		int diffBlue = blue - color.blue;
		return diffRed * diffRed + diffGreen* diffGreen + diffBlue* diffBlue;
	}

	public int getColor(){
		return color;
	}
	
	public int getRed() {
		return red;
	}

	public void setRed(int red) {
		this.red = red;
	}

	public int getGreen() {
		return green;
	}

	public void setGreen(int green) {
		this.green = green;
	}

	public int getBlue() {
		return blue;
	}

	public void setBlue(int blue) {
		this.blue = blue;
	}
}
