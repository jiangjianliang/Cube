package com.wander.cube.util;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;

public class BitmapHandler {
	private static final String TAG = "BitmapHandler";

	private static int BLOCK_WIDTH = 1;

	private static int BLOCK_X1 = 170;
	private static int BLOCK_X2 = 470;
	private static int BLOCK_X3 = 750;
	private static int BLOCK_Y1 = 100;
	private static int BLOCK_Y2 = 370;
	private static int BLOCK_Y3 = 650;

	private static List<Point> pointList = new ArrayList<Point>();
	static {

		pointList.add(new Point(BLOCK_X1, BLOCK_Y1));
		pointList.add(new Point(BLOCK_X2, BLOCK_Y1));
		pointList.add(new Point(BLOCK_X3, BLOCK_Y1));
		pointList.add(new Point(BLOCK_X1, BLOCK_Y2));
		pointList.add(new Point(BLOCK_X2, BLOCK_Y2));
		pointList.add(new Point(BLOCK_X3, BLOCK_Y2));
		pointList.add(new Point(BLOCK_X1, BLOCK_Y3));
		pointList.add(new Point(BLOCK_X2, BLOCK_Y3));
		pointList.add(new Point(BLOCK_X3, BLOCK_Y3));
	}

	private static int START_X = 0;
	private static int START_Y = 0;

	public static void handleBitmap(Bitmap bitmap) {
		// process bitmap and get the color
		setStartPoint(bitmap);
		block(bitmap);
	}

	private static void setStartPoint(Bitmap bitmap) {
		// set START_X,START_Y
		START_X = -30;
		START_Y = 0;
	}

	private static void block(Bitmap bitmap) {
		for (int i = 0; i < 9; i++) {
			Point scanPoint = pointList.get(i);
			int x = scanPoint.getX() + START_X;
			int y = scanPoint.getY() + START_Y;

			int red = 0;
			int green = 0;
			int blue = 0;

			for (int j = 0; j < BLOCK_WIDTH; j++) {
				int colorInt = bitmap.getPixel(x + j, y + j);
				red += Color.red(colorInt);
				green += Color.green(colorInt);
				blue += Color.blue(colorInt);
				Log.i(TAG, Color.red(colorInt) + ";;" + Color.green(colorInt)
						+ ";;" + Color.blue(colorInt));
			}

			SimpleColor simpleColor = new SimpleColor(red / BLOCK_WIDTH, green
					/ BLOCK_WIDTH, blue / BLOCK_WIDTH);
			MagicCube.addColor(simpleColor);
		}
	}

}
