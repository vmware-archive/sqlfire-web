package vmware.au.se.sqlfireweb.dao.stored;

public interface Constants 
{
	public static final String USER_STORED_CODE = 
			"select s.schemaname, alias, javaclassname " + 
		    "from sys.sysaliases a, sys.sysschemas s " +
		    "where aliastype = ? " + 
		    "and a.schemaid = s.schemaid " + 
		    "and s.schemaname = ? " +
		    "and alias like ?";
	
	public static String DROP_STORED_CODE = "drop %s %s.\"%s\"";
}
