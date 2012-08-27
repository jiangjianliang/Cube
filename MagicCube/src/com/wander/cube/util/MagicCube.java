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

	private static int[] coreColorList = {4, 13, 22, 31, 40, 49};

	private static int[] colorAmountList = { 0, 0, 0, 0, 0, 0 };

	private static char[] charList = { 'U', 'R', 'D', 'L', 'F', 'B' };

	// URDLFB 0-53(4,... all center block index exclusive), 54 means " "
	// UF UR UB UL DF DR DB DL FR FL BR BL UFR URB UBL ULF DRF DFL DLB DBR
	private static int[] list = { 7, 37, 54, 5, 12, 54, 1, 52, 54, 3, 32, 54,
			25, 43, 54, 21, 14, 54, 19, 46, 54, 23, 30, 54, 41, 16, 54, 39, 34,
			54, 50, 10, 54, 48, 28, 54, 8, 38, 15, 54, 2, 9, 53, 54, 0, 51, 29,
			54, 6, 35, 36, 54, 24, 17, 44, 54, 26, 42, 33, 54, 20, 27, 45, 54,
			18, 47, 11 };
	
	private static String cubeStr = "";
	
	public static void addColor(SimpleColor simpleColor) {
		colorList.add(simpleColor);
	}

	public static String getCubeString() {
		
		constructCubeString();
		//test
		test();
		
		if(!checkConstruction())
			return null;
		
		formatCubeString();
		
		return cubeStr;
	}
	
	private static void constructCubeString(){
		for(int i = 0; i < 6; i++){
			colorAmountList[i] = 0;
		}
		
		for(int i = 0; i < list.length; i++){
			colorAmountList[colorList.get(i).getColor()-'0']++;
			if(list[i] == 54)
				cubeStr += " ";
			else
				cubeStr += colorList.get(list[i]).getColor();
		}
		
	}
	
	private static boolean checkConstruction(){
		for(int i = 0; i < 6; i++){
			if(colorAmountList.length != 8){
				return false;
			}
		}
		return true;
	}
	
	private static void formatCubeString(){

		for(int i = 0; i < coreColorList.length; i++){
			String expr = String.valueOf(colorList.get(coreColorList[i]));
			String sub = String.valueOf(charList[i]);
			cubeStr = cubeStr.replaceAll(expr, sub);
		}
	}
	
	private static void test(){
		String data = "";
		for(int i = 0; i < colorList.size(); i++){
			data += colorList.get(i).getColor()+",";
			if( i %9 == 8)
				data += "\r\n";
		}
		
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
