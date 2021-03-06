/**
* COPYRIGHT 2012-2017 Pluribus Networks Inc.
*
* All rights reserved. This copyright notice is Copyright Management
* Information under 17 USC 1202 and is included to protect this work and
* deter copyright infringement.  Removal or alteration of this Copyright
* Management Information without the express written permission from
* Pluribus Networks Inc is prohibited, and any such unauthorized removal
* or alteration will be a violation of federal law.
*/
package com.pluribus.vcf.helper;

import com.jcabi.log.Logger;
import com.jcabi.ssh.SSHByPassword;
import com.jcabi.ssh.Shell;
import java.io.IOException;
import java.net.UnknownHostException;

public class SwitchMethods {
	private String switchIp;
	private String switchUser = "root";
	private String switchPwd = "test123";
	
	public SwitchMethods(String switchName) {
		this.switchIp = switchName;
	}

	//Workaround for bug 15007
	public void restartTomcat() {
		Shell session = getSwitchSession();
		String out1 = null;
		try {
			out1 = new Shell.Plain(session).exec("cli --no-login-prompt --quiet -c \"admin-service-modify if mgmt no-web\"");
			Thread.sleep(20000);
			out1 = new Shell.Plain(session).exec("cli --no-login-prompt --quiet -c \"admin-service-modify if data no-web\"");
			Thread.sleep(20000);
			out1 = new Shell.Plain(session).exec("cli --no-login-prompt --quiet -c \"admin-service-modify if mgmt web\"");
			Thread.sleep(20000); //Sleeping as tomcat takes 1 min to start
		} catch (Exception e) {
			Logger.error(e, "Tomcat restart failed");
		}
	}
	public Shell getSwitchSession() {
		Shell serverSession = null;
		try {
				serverSession = new Shell.Verbose(
					new SSHByPassword(
							switchIp,
							22,
							switchUser,
							switchPwd
						)
				);
		} catch (UnknownHostException e) {
			System.out.println(e.toString());
		}
		return serverSession;
	}
	
	public void assignFabric() {
		Shell session = getSwitchSession();
		String out1;
		try {
			out1 = new Shell.Plain(session).exec("cli --no-login-prompt --quiet -c fabric-node-show");
			if(out1.contains("fabric-node-show: Not joined to fabric")) {
				out1 = new Shell.Plain(session).exec("cli --no-login-prompt --quiet -c \"fabric-create name vcfc-smoke\"");
			}
		} catch (IOException e) {
			System.out.println(e.toString());
		}
	}
	
	public void clearSessions() {
		Shell session = getSwitchSession();
		String out1;
		try {
			out1 = new Shell.Plain(session).exec("cli --no-login-prompt --quiet -c connection-clear");
			out1 = new Shell.Plain(session).exec("cli --no-login-prompt --quiet -c connection-clear-history");
		} catch (IOException e) {
			System.out.println(e.toString());
		}
	}
	
	public int getConnectionCount(String destIp) {
		int connCount = 0;
		Shell session = getSwitchSession();
		String out1 = null;
		try {
			out1 = new Shell.Plain(session).exec("cli --no-login-prompt --quiet -c \"connection-show dst-ip "+destIp+"\"");
		} catch(IOException e) {
			System.out.println(e.toString());
		}
		String[] connLines = out1.split("\n");
		for(int x =0; x < connLines.length; x++) {
			if(connLines[x].contains(destIp)) {
				connCount += 1;
			}
		}
		return connCount;
	}
}
