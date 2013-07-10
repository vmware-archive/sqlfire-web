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
package vmware.au.se.sqlfireweb.dao.views;

public interface Constants 
{
	public static final String USER_VIEWS = 
			"select t.tableschemaname, t.tablename, v.viewdefinition, t.datapolicy " +
			"from SYS.SYSVIEWS v, SYS.SYSSCHEMAS s, sys.systables t " +
			"where s.schemaid = v.compilationschemaid  " +
			"and   t.tableid = v.tableid " +
			"and   s.schemaname = ? " +
			"and   t.tablename like ? " +
			"order by 1, 2";
	
	public static String DROP_VIEW = "drop view %s.\"%s\"";

}
