package vmware.au.se.sqlfireweb.dao.asyncevent;

public class Asyncevent 
{
	public String name;
	public String listenerClass;
	public String serverGroup;
	private String isStarted;
	
	public Asyncevent()
	{	  
	}

	public Asyncevent(String name, String listenerClass, String serverGroup, String isStarted) 
	{
		super();
		this.name = name;
		this.listenerClass = listenerClass;
		this.serverGroup = serverGroup;
		this.isStarted = isStarted;
	}


	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getListenerClass() {
		return listenerClass;
	}
	
	public void setListenerClass(String listenerClass) {
		this.listenerClass = listenerClass;
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
		return "Asyncevent [name=" + name + ", listenerClass=" + listenerClass
				+ ", serverGroup=" + serverGroup + ", isStarted=" + isStarted
				+ "]";
	}
  
	
  
}
