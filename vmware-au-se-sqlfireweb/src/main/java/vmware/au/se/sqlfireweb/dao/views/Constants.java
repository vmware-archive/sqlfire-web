package vmware.au.se.sqlfireweb.dao.views;

public interface Constants 
{
	public static final String USER_VIEWS = 
			"select t.tableschemaname, t.tablename, v.viewdefinition, t.datapolicy " +
			"from SYS.SYSVIEWS v, SYS.SYSSCHEMAS s, sys.systables t " +
			"where s.schemaid = v.compilationschemaid  " +
			"and   t.tableid = v.tableid " +
			"and   s.schemaname = ? " +
			"and   t.tablename like ? " +
			"order by 1, 2";
	
	public static String DROP_VIEW = "drop view %s.\"%s\"";

}
