package com.peak.calldemoapp.util.webutil;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;

public class XmppTool {

	private static XMPPConnection con = null;
	private static String SERVER="123.57.22.77";

	private String HOST="localhost.localdomain";
	
	private static void openConnection(String server) {
		try {
			ConnectionConfiguration connConfig = new ConnectionConfiguration(server,
					5222);
			con = new XMPPConnection(connConfig);
			con.connect();
		} catch (XMPPException xe) {
			xe.printStackTrace();
		}
	}

	public static XMPPConnection getConnection() {
		if (con == null) {
			openConnection(SERVER);
		}
		return con;
	}

	public static void closeConnection() {
		con.disconnect();
		con = null;
	}
}
