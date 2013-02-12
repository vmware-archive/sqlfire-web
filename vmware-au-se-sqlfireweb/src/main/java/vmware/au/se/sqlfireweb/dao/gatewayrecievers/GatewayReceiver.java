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
