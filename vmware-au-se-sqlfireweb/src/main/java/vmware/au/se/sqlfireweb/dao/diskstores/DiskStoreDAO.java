package vmware.au.se.sqlfireweb.dao.diskstores;

import java.util.List;

import vmware.au.se.sqlfireweb.main.Result;
import vmware.au.se.sqlfireweb.main.SqlFireException;

public interface DiskStoreDAO 
{
	public List<DiskStore> retrieveDiskStoreList(String schema, String search, String userKey) throws SqlFireException;
	
	public List<DiskStore> retrieveDiskStoreForCreateList(String userKey) throws SqlFireException;
	
	public Result simplediskStoreCommand (String diskStoreName, String type, String userKey) throws SqlFireException;

}
