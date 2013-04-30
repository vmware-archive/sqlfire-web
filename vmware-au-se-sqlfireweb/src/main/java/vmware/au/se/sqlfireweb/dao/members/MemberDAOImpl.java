package vmware.au.se.sqlfireweb.dao.members;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import vmware.au.se.sqlfireweb.dao.members.Constants;
import vmware.au.se.sqlfireweb.main.SqlFireException;
import vmware.au.se.sqlfireweb.utils.AdminUtil;
import vmware.au.se.sqlfireweb.utils.JDBCUtil;

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

}
