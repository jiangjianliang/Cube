package com.wander.cube.util;

import java.util.ArrayList;
import java.util.List;

public class MagicCube {

	private static List<SimpleColor> colorList = new ArrayList<SimpleColor>();

	private static List<SimpleColor> coreList = new ArrayList<SimpleColor>();

	private static int[] colorAmountList = {0,0,0,0,0,0};
	
	private static char[] charList = {'U','R','D','L','F','B'};
	
	//URDLFB 0-53(4,... all center block index exclusive), 54 means " "
	//UF UR UB UL DF DR DB DL FR FL BR BL UFR URB UBL ULF DRF DFL DLB DBR
	private static int[] list = { 
			 7, 37, 54, 5, 12, 54, 1, 52, 54, 3, 32, 54,
			25, 43, 54, 21, 14, 54, 19, 46, 54, 23, 30, 54,
			41, 16, 54, 39, 34, 54, 50, 10, 54, 48, 28, 54,
			 8, 38, 15, 54, 2, 9, 53, 54, 0, 51, 29, 54, 6, 35, 36, 54,
			24, 17, 44, 54, 26, 42, 33, 54, 20, 27, 45, 54, 18, 47, 11 
			};
	// map cube block's index to URDLFB, which is in charList
	private static char[] map = new char[55];

	//private static final int THRESHOLD = 100;

	public static void addColor(SimpleColor simpleColor) {
		colorList.add(simpleColor);
	}


	public static String constructCubeString() {
		boolean flag = classify();
		if(!flag)
			return null;
		else{
			String cubeStr = "";
			for(int i =0; i < list.length; i++){
				cubeStr += map[list[i]];
			}
			return cubeStr;
		}
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
				System.out.println("error in classify");
			}
			colorAmountList[i]++;
			map[i] = charList[minIndex];
		}
		map[54] = ' ';
	}

	private static boolean checkClassification() {
		for (int i = 0; i < colorAmountList.length; i++) {
			if (colorAmountList[i] != 8)
				return false;
		}
		return true;
	}

}
