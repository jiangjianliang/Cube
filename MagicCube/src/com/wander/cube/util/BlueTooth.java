package com.wander.cube.util;

import java.io.IOException;

import lejos.nxt.remote.NXTCommand;
import lejos.pc.comm.NXTCommLogListener;
import lejos.pc.comm.NXTCommandConnector;
import lejos.pc.comm.NXTConnector;
import android.util.Log;

public class BlueTooth {

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
		// if(conn.connectTo("btspp://NXT", NXTComm.LCP))
		if (conn.connectTo("btspp://"))
			return conn;
		else
			return null;
	}

	private static void prepare() {
		NXTCommandConnector
				.setNXTCommand(new NXTCommand(connector.getNXTComm()));
	}

	public static void close() {
		try {
			connector.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}