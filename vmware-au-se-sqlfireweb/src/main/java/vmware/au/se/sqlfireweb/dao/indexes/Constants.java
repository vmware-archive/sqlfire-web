package vmware.au.se.sqlfireweb.dao.indexes;

public interface Constants 
{

	public static final String USER_INDEXES = 
			"select distinct index, \"UNIQUE\", \"TABLE\", \"INDEX_TYPE\", \"COLUMNS_AND_ORDER\", \"SCHEMA\" " +
			"from   sys.indexes " +
			"where  \"SCHEMA\" = ? " +
			"and index like ?";

//	public static final String USER_INDEXES = 
//			"select distinct indexname, \"UNIQUE\", tablename, indextype, COLUMNS_AND_ORDER, schemaname " +
//			"from   sys.indexes " +
//			"where  schemaname = ? " +
//			"and indexname like ?";

	
	public static String DROP_INDEX = "drop index %s.\"%s\"";
	
	public static String VIEW_TABLE_COLUMNS =
			"select c.columnname " + 
			"FROM SYS.SYSCOLUMNS c, sys.systables t " + 
			"where c.referenceid = t.tableid " +
			"and t.tableschemaname = ? " +
			"and t.tablename = ? " + 
			"order by c.columnnumber";
}
