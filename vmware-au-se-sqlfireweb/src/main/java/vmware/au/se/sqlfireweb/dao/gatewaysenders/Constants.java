/*
  	Copyright (c) 2013 GoPivotal, Inc. All Rights Reserved.

	This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; only version 2 of the License, and no
    later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

	The full text of the GPL is provided in the COPYING file.
*/
package vmware.au.se.sqlfireweb.dao.gatewaysenders;

public interface Constants 
{
	// sqlfire 1.1.1 = server_groups
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
