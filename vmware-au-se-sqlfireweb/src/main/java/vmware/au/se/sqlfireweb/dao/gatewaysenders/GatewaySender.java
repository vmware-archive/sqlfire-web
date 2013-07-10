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
package vmware.au.se.sqlfireweb.dao.gatewaysenders;

public class GatewaySender 
{
	private String senderId;
	private String remoteDSId;
	private String serverGroup;
	private String isStarted;
	
	public GatewaySender(String senderId, String remoteDSId,
			String serverGroup, String isStarted) {
		super();
		this.senderId = senderId;
		this.remoteDSId = remoteDSId;
		this.serverGroup = serverGroup;
		this.isStarted = isStarted;
	}

	public GatewaySender() 
	{
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getRemoteDSId() {
		return remoteDSId;
	}

	public void setRemoteDSId(String remoteDSId) {
		this.remoteDSId = remoteDSId;
	}

	public String getServerGroup() {
		return serverGroup;
	}

	public void setServerGroup(String serverGroup) {
		this.serverGroup = serverGroup;
	}

	public String getIsStarted() {
		return isStarted;
	}

	public void setIsStarted(String isStarted) {
		this.isStarted = isStarted;
	}

	@Override
	public String toString() {
		return "GatewaySender [senderId=" + senderId + ", remoteDSId="
				+ remoteDSId + ", serverGroup=" + serverGroup + ", isStarted="
				+ isStarted + "]";
	}
  
	
  
}
