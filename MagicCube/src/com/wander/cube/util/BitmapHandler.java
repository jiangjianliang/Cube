package com.wander.cube.util;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;

public class BitmapHandler {
	private static final String TAG = "BitmapHandler";
	
	public static int WIDTH = 750;
	public static int SMALL_WIDTH = WIDTH/6;
	public static int BLOCK_WIDTH = 4;
	public static int HALF_BLOCK_WIDTH = BLOCK_WIDTH /2;
	
	public static int DELTA_WIDTH  = 50;
	
	private static List<Point> pointList = new ArrayList<Point>();
	static{
		
		pointList.add(new Point(170, 100));
		pointList.add(new Point(470, 100));
		pointList.add(new Point(750, 100));
		pointList.add(new Point(170, 370));
		pointList.add(new Point(470, 370));
		pointList.add(new Point(750, 370));
		pointList.add(new Point(170, 650));
		pointList.add(new Point(470, 650));
		pointList.add(new Point(750, 650));
	}
	
	private static int START_X = 0;
	private static int START_Y = 0;
	
	public static void handleBitmap(Bitmap bitmap){
		//process bitmap and get the color
		setStartPoint(bitmap);
		block(bitmap);
	}
	
	private static void setStartPoint(Bitmap bitmap){
		
		//set START_X,START_Y
		START_X = 0;
		
		//START_X = 0;
		START_Y = 0;
	}
	
	private static void block(Bitmap bitmap){
		for(int i = 0; i < 9; i++){
			Point scanPoint = pointList.get(i);
			int x = scanPoint.getX() + START_X;
			int y = scanPoint.getY() + START_Y;
			
			int red = 0;
			int green = 0;
			int blue = 0;
			
			for(int j = 0; j < BLOCK_WIDTH; j++){
				int colorInt = bitmap.getPixel(x+j, y+j);
				red += Color.red(colorInt);
				green += Color.green(colorInt);
				blue += Color.blue(colorInt);
				Log.i(TAG, Color.red(colorInt) + ";;" + Color.green(colorInt) + ";;" +Color.blue(colorInt));
			}
			
			SimpleColor simpleColor = new SimpleColor(red/BLOCK_WIDTH, green/BLOCK_WIDTH, blue/BLOCK_WIDTH);
			String str = "(" + red/BLOCK_WIDTH + "," + green/BLOCK_WIDTH + "," +blue/BLOCK_WIDTH + ")";
			MagicCube.addColor(simpleColor, str);
			Log.i(TAG, "in block");
		}
	}
	
}
