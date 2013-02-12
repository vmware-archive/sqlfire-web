import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.vmware.sqlfire.jdbc.ClientDriver;


public class ConnectionTest {

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
		DatabaseMetaData meta = conn.getMetaData ();

		// gets driver info:

		System.out.println("=============\nDatabase Product Name is ... " +
		meta.getDatabaseProductName());
		System.out.println("Database Product Version is  " +
		meta.getDatabaseProductVersion());
		System.out.println("=============\nJDBC Driver Name is ........ " +
		meta.getDriverName());
		System.out.println("JDBC Driver Version is ..... " +
		meta.getDriverVersion());
		System.out.println("JDBC Driver Major Version is " +
		meta.getDriverMajorVersion());
		System.out.println("JDBC Driver Minor Version is " +
		meta.getDriverMinorVersion());
		System.out.println("============="); 
	}

}
