package com.wander.cube.util;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.Color;

public class BitmapHandler {
	public static int WIDTH = 120;
	public static int SMALL_WIDTH = WIDTH/6;
	public static int BLOCK_WIDTH = 8;
	public static int HALF_BLOCK_WIDTH = BLOCK_WIDTH /2;
	
	
	private static List<Point> pointList = new ArrayList<Point>();
	static{
		
		pointList.add(new Point(SMALL_WIDTH - HALF_BLOCK_WIDTH, SMALL_WIDTH - HALF_BLOCK_WIDTH));
		pointList.add(new Point(SMALL_WIDTH*3 - HALF_BLOCK_WIDTH, SMALL_WIDTH - HALF_BLOCK_WIDTH));
		pointList.add(new Point(SMALL_WIDTH*5 - HALF_BLOCK_WIDTH, SMALL_WIDTH - HALF_BLOCK_WIDTH));
		pointList.add(new Point(SMALL_WIDTH - HALF_BLOCK_WIDTH, SMALL_WIDTH*3 - HALF_BLOCK_WIDTH));
		pointList.add(new Point(SMALL_WIDTH*3 - HALF_BLOCK_WIDTH, SMALL_WIDTH*3 - HALF_BLOCK_WIDTH));
		pointList.add(new Point(SMALL_WIDTH*5 - HALF_BLOCK_WIDTH, SMALL_WIDTH*3 - HALF_BLOCK_WIDTH));
		pointList.add(new Point(SMALL_WIDTH - HALF_BLOCK_WIDTH, SMALL_WIDTH*5 - HALF_BLOCK_WIDTH));
		pointList.add(new Point(SMALL_WIDTH*3 - HALF_BLOCK_WIDTH, SMALL_WIDTH*5 - HALF_BLOCK_WIDTH));
		pointList.add(new Point(SMALL_WIDTH*5 - HALF_BLOCK_WIDTH, SMALL_WIDTH*5 - HALF_BLOCK_WIDTH));
	}
	
	/*private static List<Integer> rotateList = new ArrayList<Integer>();
	static{
		rotateList.add(2);
		rotateList.add(5);
		rotateList.add(8);
		rotateList.add(1);
		rotateList.add(4);
		rotateList.add(7);
		rotateList.add(0);
		rotateList.add(3);
		rotateList.add(6);
	}
	*/
	
	private static int START_X = 0;
	private static int START_Y = 0;
	
	public static void handleBitmap(Bitmap bitmap, int count){
		//process bitmap and get the color
		setStartPoint(bitmap);
		block(bitmap, count);
	}
	
	private static void setStartPoint(Bitmap bitmap){
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		//assume width/2,height/2 is center of the cube
		//set START_X,START_Y
		START_X = width/2- WIDTH/2;
		START_Y = height/2-WIDTH/2;
	}
	
	private static void block(Bitmap bitmap, int count){
		int[] pixels = new int[BLOCK_WIDTH * BLOCK_WIDTH];
		//List<SimpleColor> originalColorList= new ArrayList<SimpleColor>();
		for(int i = 0; i < 9; i++){
			bitmap.getPixels(pixels, 0, bitmap.getWidth(), START_X, START_Y, BLOCK_WIDTH, BLOCK_WIDTH);
			//process pixels array
			int red = 0;
			int green = 0;
			int blue = 0;
			for(int j = 0; i < pixels.length; i++){
				red += Color.red(pixels[j]);
				green += Color.green(pixels[j]);
				blue += Color.blue(pixels[j]);
			}
			
			SimpleColor simpleColor = new SimpleColor(red/pixels.length, green/pixels.length, blue/pixels.length);
			//store color in the arraylist
			//TODO originalColorList.add(simpleColor);
			MagicCube.addColor(simpleColor);
		}
	}
	
}
