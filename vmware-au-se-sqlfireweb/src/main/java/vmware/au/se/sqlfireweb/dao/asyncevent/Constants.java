package vmware.au.se.sqlfireweb.dao.asyncevent;

public interface Constants 
{
	public static final String USER_ASYNCEVENTS = 
			"select id, listener_class, server_group, is_started " +
			"from   sys.ASYNCEVENTLISTENERS " +
			"where  id like ?";
	
	public static String START_ASYNC = "call SYS.START_ASYNC_EVENT_LISTENER('%s')";
	
	public static String STOP_ASYNC = "call SYS.STOP_ASYNC_EVENT_LISTENER('%s')";
	
	public static String DROP_ASYNC = "DROP ASYNCEVENTLISTENER \"%s\"";

	public static final String VIEW_ALL_USER_ASYNCEVENT_INFO = 
			"select * " +
			"from   sys.ASYNCEVENTLISTENERS " +
			"where  id = '%s'";
}
