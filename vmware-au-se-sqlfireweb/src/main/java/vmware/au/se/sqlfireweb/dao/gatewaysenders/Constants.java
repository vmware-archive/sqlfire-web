package vmware.au.se.sqlfireweb.dao.gatewaysenders;

public interface Constants 
{
	public static final String USER_GATEWAY_SENDERS = 
			"select sender_id, remote_ds_id, server_groups, is_started " +
			"from sys.GATEWAYSENDERS " +
			"where sender_id like ? " +
			"order by 1";
	
	public static String START_GATEWAY_SENDER = "CALL SYS.START_GATEWAYSENDER ('%s')";
	
	public static String STOP_GATEWAY_SENDER = "CALL SYS.STOP_GATEWAYSENDER ('%s')";
	
	public static String DROP_GATEWAY_SENDER = "DROP GATEWAYSENDER \"%s\"";

	public static final String VIEW_ALL_USER_GATEWAY_SENDER_INFO = 
			"select * " +
			"from sys.GATEWAYSENDERS " +
			"where sender_id = '%s'";
}
