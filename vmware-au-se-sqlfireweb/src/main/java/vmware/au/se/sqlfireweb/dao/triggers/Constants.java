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
package vmware.au.se.sqlfireweb.dao.triggers;

public class Constants 
{
	public static final String USER_TRIGGERS = 
		"select t.triggerid, s.schemaname, t.triggername, t.creationtimestamp, t.event, " + 
		       "t.firingtime, t.type, t.state, t.triggerdefinition " + 
	    "from sys.systriggers t, sys.sysschemas s " +
	    "where s.schemaname = ? " +
	    "and t.schemaid = s.schemaid " + 
	    "and   t.triggername like ?";
	
	public static String DROP_TRIGGER = "drop trigger %s.\"%s\"";
	
	public static final String VIEW_ALL_USER_TRIGGER_COLUMNS = 
		"select * " +
		"from   sys.systriggers " +
		"where  triggerid = '%s'";
	
}
