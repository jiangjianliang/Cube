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

	private static int[] coreIndexList = { 4, 13, 22, 31, 40, 49 };

	private static int[] colorAmountList = new int[6];

	private static char[] charList = { 'U', 'R', 'D', 'L', 'F', 'B' };

	private static int BLANK = 54;
	// URDLFB 0-53(4,... all center block index exclusive), 54 means " "
	// UF UR UB UL DF DR DB DL FR FL BR BL UFR URB UBL ULF DRF DFL DLB DBR
	private static int[] list = { 7, 37, BLANK, 5, 12, BLANK, 1, 52, BLANK, 3,
			32, BLANK, 25, 43, BLANK, 21, 14, BLANK, 19, 46, BLANK, 23, 30,
			BLANK, 41, 16, BLANK, 39, 34, BLANK, 50, 10, BLANK, 48, 28, BLANK,
			8, 38, 15, BLANK, 2, 9, 53, BLANK, 0, 51, 29, BLANK, 6, 35, 36,
			BLANK, 24, 17, 44, BLANK, 26, 42, 33, BLANK, 20, 27, 45, BLANK, 18,
			47, 11 };

	private static String cubeStr = "";

	public static void addColor(SimpleColor simpleColor) {
		colorList.add(simpleColor);
	}

	public static String getCubeString() {

		constructCubeString();
		// test
		test();
		if (!checkConstruction()) {
			System.out.println(colorAmountList[0] + ";;" + colorAmountList[1]
					+ ";;" + colorAmountList[2] + ";;" + colorAmountList[3]
					+ ";;" + colorAmountList[4] + ";;" + colorAmountList[5]);
			return null;
		}

		formatCubeString();

		return cubeStr;
	}

	private static void constructCubeString() {
		for (int i = 0; i < 6; i++) {
			colorAmountList[i] = 0;
		}

		for (int i = 0; i < list.length; i++) {
			int squareIndex = list[i];
			if (squareIndex == BLANK)
				cubeStr += " ";
			else {
				// System.out.println(colorList.get(squareIndex).getColor());
				colorAmountList[colorList.get(squareIndex).getColor()]++;
				cubeStr += colorList.get(squareIndex).getColor();
			}
		}

	}

	private static boolean checkConstruction() {
		for (int i = 0; i < 6; i++) {
			if (colorAmountList[i] != 8) {
				return false;
			}
		}
		return true;
	}

	private static void formatCubeString() {

		for (int i = 0; i < coreIndexList.length; i++) {
			String expr = String.valueOf(colorList.get(coreIndexList[i])
					.getColor());
			String sub = String.valueOf(charList[i]);
			cubeStr = cubeStr.replaceAll(expr, sub);
		}
	}

	private static void test() {
		String data = "";
		for (int i = 0; i < colorList.size(); i++) {
			if (i % 9 == 0)
				data += "\r\n" + i / 9;
			data += colorList.get(i).toString() + "\r\n";

		}

		data += "\r\n\r\n" + colorAmountList[0] + ";;" + colorAmountList[1]
				+ ";;" + colorAmountList[2] + ";;" + colorAmountList[3] + ";;"
				+ colorAmountList[4] + ";;" + colorAmountList[5];
		try {
			File logFile = new File("/sdcard/AAA/hello.txt");
			FileOutputStream outStream = new FileOutputStream(logFile);
			ObjectOutputStream objStream = new ObjectOutputStream(outStream);
			objStream.writeObject(cubeStr);
			objStream.writeObject(data);
			objStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
