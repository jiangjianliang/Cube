package com.wander.cube.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import android.util.Log;

public class MagicCube {

	private static final String TAG = "MagicCube";

	private static List<SimpleColor> colorList = new ArrayList<SimpleColor>();

	private static List<SimpleColor> coreList = new ArrayList<SimpleColor>();

	private static int[] colorAmountList = { 0, 0, 0, 0, 0, 0 };

	private static char[] charList = { 'U', 'R', 'D', 'L', 'F', 'B' };

	// URDLFB 0-53(4,... all center block index exclusive), 54 means " "
	// UF UR UB UL DF DR DB DL FR FL BR BL UFR URB UBL ULF DRF DFL DLB DBR
	private static int[] list = { 7, 37, 54, 5, 12, 54, 1, 52, 54, 3, 32, 54,
			25, 43, 54, 21, 14, 54, 19, 46, 54, 23, 30, 54, 41, 16, 54, 39, 34,
			54, 50, 10, 54, 48, 28, 54, 8, 38, 15, 54, 2, 9, 53, 54, 0, 51, 29,
			54, 6, 35, 36, 54, 24, 17, 44, 54, 26, 42, 33, 54, 20, 27, 45, 54,
			18, 47, 11 };
	// map cube block's index to URDLFB, which is in charList
	private static char[] map = new char[55];

	// private static final int THRESHOLD = 100;
	
	private static String colorDebug = "";
	
	public static void addColor(SimpleColor simpleColor, String colorString) {
		colorList.add(simpleColor);
		Log.i(TAG, "colorList size is " + colorList.size());
		colorDebug += colorString;
	}

	public static String constructCubeString() {
		String cubeStr = "";
		for(int i = 0; i < list.length; i++){
			if(list[i] == 54)
				cubeStr += " ";
			else
				cubeStr += colorList.get(list[i]).getColor();
		}
		String data = "";
		for(int i = 0; i < colorList.size(); i++){
			data += colorList.get(i).getColor()+",";
			if( i %9 == 8)
				data += "\r\n";
		}
		
		

		cubeStr = cubeStr.replaceAll(String.valueOf(colorList.get(4).getColor()), "U");
		cubeStr = cubeStr.replaceAll(String.valueOf(colorList.get(13).getColor()), "R");
		cubeStr = cubeStr.replaceAll(String.valueOf(colorList.get(22).getColor()), "D");
		cubeStr = cubeStr.replaceAll(String.valueOf(colorList.get(31).getColor()), "L");
		cubeStr = cubeStr.replaceAll(String.valueOf(colorList.get(40).getColor()), "F");
		cubeStr = cubeStr.replaceAll(String.valueOf(colorList.get(49).getColor()), "B");
		
		try {
			File logFile = new File("/sdcard/AAA/hello.txt");
			FileOutputStream outStream = new FileOutputStream(logFile);
			ObjectOutputStream objStream = new ObjectOutputStream(outStream);
			objStream.writeObject(cubeStr);
			objStream.writeObject(data);
			// outStream.write(data.toCharArray());
			objStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cubeStr;
		/*boolean flag = classify();
		if (!flag)
			return null;
		else {
			String cubeStr = "";
			for (int i = 0; i < list.length; i++) {
				cubeStr += map[list[i]];
			}
			return cubeStr;
		}*/
	}

	private static boolean classify() {
		prepareClassification();
		performClassification();
		return checkClassification();
	}

	private static void prepareClassification() {
		for (int i = 49; i > 0; i -= 9) {
			coreList.add(colorList.get(i));
			colorList.remove(i);
		}
	}

	private static void performClassification() {
		for (int i = 0; i < colorList.size(); i++) {
			int minDistance = -1;
			int minIndex = -1;
			SimpleColor color = colorList.get(i);
			for (int j = 0; j < coreList.size(); j++) {
				int distance = color.distanceTo(coreList.get(j));
				if (minDistance < distance) {
					minIndex = j;
					minDistance = distance;
				}
			}
			if (minIndex == -1) {
				Log.e(TAG, "error in classify");
			}
			colorAmountList[minIndex]++;
			map[i] = charList[minIndex];
		}
		map[54] = ' ';
		String data = "";
		for (int i = 0; i < 6; i++) {
			data += "(" + coreList.get(i).getRed() + ","
					+ coreList.get(i).getGreen() + ","
					+ coreList.get(i).getBlue() + ")--" + colorAmountList[i]
					+ ";;\r\n";
		}
		data += "\r\n";
		for(int i = 0; i < colorList.size(); i++){
			data += "(" + colorList.get(i).getRed() + ","
					+ colorList.get(i).getGreen() + ","
					+ colorList.get(i).getBlue() + "),";
			if( i %8 == 7)
				data += "\r\n";
		}
		Log.i(TAG, data);
		try {
			File logFile = new File("/sdcard/AAA/hello.txt");
			FileOutputStream outStream = new FileOutputStream(logFile);
			ObjectOutputStream objStream = new ObjectOutputStream(outStream);
			objStream.writeObject(data);
			// outStream.write(data.toCharArray());
			objStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static boolean checkClassification() {
		for (int i = 0; i < colorAmountList.length; i++) {
			if (colorAmountList[i] != 8)
				return false;
		}
		return true;
	}

}
