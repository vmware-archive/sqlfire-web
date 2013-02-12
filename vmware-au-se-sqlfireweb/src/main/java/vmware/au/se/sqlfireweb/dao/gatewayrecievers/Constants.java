package vmware.au.se.sqlfireweb.dao.gatewayrecievers;

public interface Constants 
{
	public static final String USER_GATEWAY_RECEIVERS = 
			"select id, running_port, server_groups, bind_address " +
			"from sys.GATEWAYRECEIVERS " +
			"where id like ? " +
			"order by 1";
	
	public static String DROP_GATEWAY_RECEIVER = "DROP GATEWAYRECEIVER \"%s\"";

	public static final String VIEW_ALL_GATEWAY_RECEIVERS_COLUMNS = 
			"select * " +
			"from sys.GATEWAYRECEIVERS " +
			"where id = '%s' " +
			"order by 1";
}
