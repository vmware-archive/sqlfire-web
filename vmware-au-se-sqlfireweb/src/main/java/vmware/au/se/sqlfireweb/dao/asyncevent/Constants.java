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
package vmware.au.se.sqlfireweb.dao.asyncevent;

public interface Constants 
{
	// sqlfire 1.1.1 = server_groups
	public static final String USER_ASYNCEVENTS = 
			"select id, listener_class, server_group, is_started " +
			"from   sys.ASYNCEVENTLISTENERS " +
			"where  id like ?";
	
	public static String START_ASYNC = "call SYS.START_ASYNC_EVENT_LISTENER('%s')";
	
	public static String STOP_ASYNC = "call SYS.STOP_ASYNC_EVENT_LISTENER('%s')";
	
	public static String DROP_ASYNC = "DROP ASYNCEVENTLISTENER \"%s\"";

	public static final String VIEW_ALL_USER_ASYNCEVENT_INFO = 
			"select * " +
			"from   sys.ASYNCEVENTLISTENERS " +
			"where  id = '%s'";
}
