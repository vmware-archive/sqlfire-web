import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.vmware.sqlfire.jdbc.ClientDriver;

public class Test 
{

	static public Connection getNewConnection (String url) throws SQLException
	{
	   DriverManager.registerDriver(new ClientDriver());
	   Connection conn = DriverManager.getConnection(url);
	   conn.setAutoCommit(true);
	   return conn;
	}
	
	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException 
	{
		// TODO Auto-generated method stub
		Connection conn = getNewConnection("jdbc:sqlfire://localhost:1527/");
		System.out.println(conn);
		
		ResultSet rset = conn.getMetaData().getProcedureColumns(null, "APP", "GETREGIONNAME", "%");
		
		while (rset.next())
		{
			System.out.println(rset.getString("PARAMETER_ID"));
			System.out.println(rset.getString("COLUMN_NAME"));
			System.out.println(rset.getString("TYPE_NAME"));
		}
		
		System.out.println("all done.."); 
	}

}
