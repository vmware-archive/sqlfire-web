package vmware.au.se.sqlfireweb.dao.triggers;

public class Constants 
{
	public static final String USER_TRIGGERS = 
		"select t.triggerid, s.schemaname, t.triggername, t.creationtimestamp, t.event, " + 
		       "t.firingtime, t.type, t.state, t.triggerdefinition " + 
	    "from sys.systriggers t, sys.sysschemas s " +
	    "where s.schemaname = ? " +
	    "and t.schemaid = s.schemaid " + 
	    "and   t.triggername like ?";
	
	public static String DROP_TRIGGER = "drop trigger %s.\"%s\"";
	
	public static final String VIEW_ALL_USER_TRIGGER_COLUMNS = 
		"select * " +
		"from   sys.systriggers " +
		"where  triggerid = '%s'";
	
}
