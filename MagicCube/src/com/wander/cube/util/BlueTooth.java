package com.wander.cube.util;

import android.util.Log;
import lejos.pc.comm.NXTComm;
import lejos.pc.comm.NXTCommFactory;
import lejos.pc.comm.NXTCommLogListener;
import lejos.pc.comm.NXTConnector;

public class BlueTooth {
	private final static String TAG = "LeJOS-Droid";

	public static NXTConnector connect() {
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

		//if (conn.connectTo("", "", NXTCommFactory.BLUETOOTH))
		if( conn.connectTo("btspp://"))
		//if(conn.connectTo("btspp://NXT", NXTComm.LCP))
			return conn;
		else
			return null;
	}
}