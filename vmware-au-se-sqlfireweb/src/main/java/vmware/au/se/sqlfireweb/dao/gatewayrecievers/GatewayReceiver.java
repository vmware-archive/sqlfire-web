/*
  	Copyright (c) 2013 GoPivotal, Inc. All Rights Reserved.

	This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; only version 2 of the License, and no
    later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

	The full text of the GPL is provided in the COPYING file.
*/
package vmware.au.se.sqlfireweb.dao.gatewayrecievers;

public class GatewayReceiver 
{
	private String id;
	private String runningPort;
	private String serverGroups;
	private String bindAddress;
	
	public GatewayReceiver(String id, String runningPort, String serverGroups,
			String bindAddress) {
		super();
		this.id = id;
		this.runningPort = runningPort;
		this.serverGroups = serverGroups;
		this.bindAddress = bindAddress;
	}

	public GatewayReceiver() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRunningPort() {
		return runningPort;
	}

	public void setRunningPort(String runningPort) {
		this.runningPort = runningPort;
	}

	public String getServerGroups() {
		return serverGroups;
	}

	public void setServerGroups(String serverGroups) {
		this.serverGroups = serverGroups;
	}

	public String getBindAddress() {
		return bindAddress;
	}

	public void setBindAddress(String bindAddress) {
		this.bindAddress = bindAddress;
	}

	@Override
	public String toString() {
		return "GatewayReceiver [id=" + id + ", runningPort=" + runningPort
				+ ", serverGroups=" + serverGroups + ", bindAddress="
				+ bindAddress + "]";
	}
	
}
