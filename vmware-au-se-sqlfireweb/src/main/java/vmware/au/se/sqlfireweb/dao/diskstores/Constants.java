package vmware.au.se.sqlfireweb.dao.diskstores;

public interface Constants {

	public static final String USER_DISKSTORES = 
			"select name, dir_path_size \"Directory\" " +
			"from sys.sysdiskstores " +
			"where name like ? " +
			"order by 1";

	public static final String USER_DISKSTORES_FOR_CREATE = 
			"select name, dir_path_size \"Directory\" " +
			"from sys.sysdiskstores " +
			"where name not in ('SQLF-DD-DISKSTORE', 'SQLF-DEFAULT-DISKSTORE') " +
			"order by 1";
	
	public static final String DROP_DISKSTORE =
			"drop diskstore %s";
}
