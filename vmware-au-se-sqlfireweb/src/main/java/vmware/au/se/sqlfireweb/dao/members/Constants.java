package vmware.au.se.sqlfireweb.dao.members;

public interface Constants 
{
	public static final String ALL_MEMBERS =
			"select id, status, hostdata, iselder, host, pid, port, locator, servergroups " +
			"from sys.members";	
}
