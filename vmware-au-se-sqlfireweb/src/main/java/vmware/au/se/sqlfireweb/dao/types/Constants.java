package vmware.au.se.sqlfireweb.dao.types;

public interface Constants 
{
	public static final String USER_TYPES = 
			"select aliasschemaname, alias, javaclassname " +
			"FROM   sys.sysaliases  " +
			"WHERE  aliasschemaname = ? " +
			"and    aliastype = 'A' " +
			"and    alias like ? " +
			"order  by 2";
	
	public static String DROP_TYPE = "drop type %s.\"%s\" RESTRICT";
}
