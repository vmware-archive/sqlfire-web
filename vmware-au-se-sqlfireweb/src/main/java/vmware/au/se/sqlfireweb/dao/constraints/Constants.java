package vmware.au.se.sqlfireweb.dao.constraints;

public interface Constants 
{
	public static final String USER_CONSTRAINTS = 
			"select s.schemaname, t.tablename, c.CONSTRAINTNAME, c.TYPE, c.state " +
			"from sys.systables t, sys.sysconstraints c, sys.sysschemas s " +
			"where  s.schemaname = ? " +
			"and    s.schemaid = t.schemaid " +
			"and    t.tableid = c.tableid " +
			"and    c.constraintname like ?";
	
	public static String DROP_CONSTRAINT = "alter table %s.\"%s\" drop constraint \"%s\"";
}
