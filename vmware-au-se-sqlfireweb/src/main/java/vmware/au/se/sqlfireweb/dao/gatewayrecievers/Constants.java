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
