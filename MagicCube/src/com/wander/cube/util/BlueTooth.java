package com.wander.cube.util;

import java.io.IOException;

import android.util.Log;
import lejos.nxt.Motor;
import lejos.nxt.remote.NXTCommand;
import lejos.pc.comm.NXTComm;
import lejos.pc.comm.NXTCommFactory;
import lejos.pc.comm.NXTCommLogListener;
import lejos.pc.comm.NXTCommandConnector;
import lejos.pc.comm.NXTConnector;

public class BlueTooth {

	public final static int A_CORNER = 270;
	public final static int B_CORNER = 270;

	private final static String TAG = "BlueTooth";

	private static NXTConnector connector;

	public static void connectAndPrepare() {
		connector = connectTo();
		if (connector != null) {
			prepare();
		}
		return;
	}

	private static NXTConnector connectTo() {
		Log.d(TAG, " about to add LEJOS listener ");

		NXTConnector conn = new NXTConnector();
		conn.setDebug(true);
		conn.addLogListener(new NXTCommLogListener() {
			public void logEvent(String arg0) {
				Log.i(TAG + " NXJ log:", arg0);
			}

			public void logEvent(Throwable arg0) {
				Log.i(TAG + " NXJ log:", arg0.getMessage(), arg0);
			}
		});

		// if (conn.connectTo("", "", NXTCommFactory.BLUETOOTH))
		if (conn.connectTo("btspp://"))
			// if(conn.connectTo("btspp://NXT", NXTComm.LCP))
			return conn;
		else
			return null;
	}

	private static void prepare() {
		NXTCommandConnector
				.setNXTCommand(new NXTCommand(connector.getNXTComm()));
	}
	
	public static void close(){
		try {
			connector.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//TODO delete these
	public static void motorARotate(int count) {
		Motor.A.rotate(count);
	}

	public static void motorBRotate(int count) {
		Motor.B.rotate(count);
	}

	public static void motorCRotate(int count) {
		Motor.C.rotate(count);
	}

}