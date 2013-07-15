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
package vmware.au.se.sqlfireweb.dao.members;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.jstl.sql.Result;

import org.apache.log4j.Logger;

import vmware.au.se.sqlfireweb.dao.members.Constants;
import vmware.au.se.sqlfireweb.main.SqlFireException;
import vmware.au.se.sqlfireweb.utils.AdminUtil;
import vmware.au.se.sqlfireweb.utils.JDBCUtil;
import vmware.au.se.sqlfireweb.utils.QueryUtil;

public class MemberDAOImpl implements MemberDAO 
{
	protected static Logger logger = Logger.getLogger("controller");
	
	@Override
	public List<Member> retrieveMembers(String userKey) throws SqlFireException 
	{
	    Connection        conn = null;
	    Statement 		  stmt = null;
	    ResultSet         rset = null;
	    List<Member>  	  members = null;
	    
	    try
	    {
	      conn = AdminUtil.getConnection(userKey);
	      stmt = conn.createStatement();
	      
	      rset = stmt.executeQuery(Constants.ALL_MEMBERS);

	      members = makeMemberListFromResultSet(rset);
	    }
	    catch (SQLException se)
	    {
	      logger.debug("Error retrieving all members");
	      throw new SqlFireException(se);
	    }
	    catch (Exception ex)
	    {
	      logger.debug("Error retrieving all members");
	      throw new SqlFireException(ex);      
	    }
	    finally
	    {
	      // close all resources
	      JDBCUtil.close(rset);
	      JDBCUtil.close(stmt);
	    }
	    
	    return members;
	}
	
	private List<Member> makeMemberListFromResultSet (ResultSet rset) throws SQLException
	{
		List<Member> members = new ArrayList<Member>();
		
		while (rset.next())
		{
			Member member = new Member(rset.getString(1),
				                	   rset.getString(2),
				                	   rset.getString(3),
				                	   rset.getString(4),
				                	   rset.getString(5),
				                	   rset.getString(6),
				                	   rset.getString(7),
				                	   rset.getString(8),
				                	   rset.getString(9));
			members.add(member);
		}
		
		return members;
		
	}

	@Override
	public Result getMemberInfo(String memberId, String userKey) throws SqlFireException 
	{
		Connection        conn = null;
		javax.servlet.jsp.jstl.sql.Result res = null;
		
		try 
		{
			conn = AdminUtil.getConnection(userKey);
			res = QueryUtil.runQuery
					(conn, String.format(Constants.VIEW_ALL_MEMBER_INFO, memberId), -1);
		} 
		catch (Exception e) 
		{
		      logger.debug("Error retrieving all member info for meber with id : " + memberId);
		      throw new SqlFireException(e); 
		}
		
		return res;
	}

}
