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
