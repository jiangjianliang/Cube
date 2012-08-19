package com.wander.cube.util;

public class SimpleColor {
	private int red;
	private int green;
	private int blue;

	public SimpleColor(int red, int green, int blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	public int distanceTo(SimpleColor color) {
		return Math.abs(red - color.red) + Math.abs(green - color.green)
				+ Math.abs(blue - color.blue);
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
