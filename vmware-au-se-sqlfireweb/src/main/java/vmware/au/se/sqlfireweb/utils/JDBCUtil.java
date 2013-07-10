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
package vmware.au.se.sqlfireweb.utils;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtil
{
  public static void close
  (ResultSet resultSet, CallableStatement statement, Connection connection) 
  {
    try 
    {
      if (resultSet != null)
          close(resultSet);
      if (statement != null)
          close(statement);
      if (connection != null)
          close(connection);
      }  
      catch (Exception e) 
      {
          e.printStackTrace();
      }
  }
  
  public static void close
  (ResultSet resultSet, Statement statement, Connection connection) 
  {
    try 
    {
      if (resultSet != null)
          close(resultSet);
      if (statement != null)
          close(statement);
      if (connection != null)
          close(connection);
      }  
      catch (Exception e) 
      {
          e.printStackTrace();
      }
  }

  public static void close
  (ResultSet resultSet, PreparedStatement statement, Connection connection) 
  {
    try 
    {
      if (resultSet != null)
          close(resultSet);
      if (statement != null)
          close(statement);
      if (connection != null)
          close(connection);
      }  
      catch (Exception e) 
      {
          e.printStackTrace();
      }
  }
  
  public static void close(ResultSet resultSet) 
  {
    try 
    {
        if (resultSet != null)
            resultSet.close();
    } 
    catch (SQLException ex) 
    {
      while (ex != null) 
      {
          System.out.println("SQLState: " + ex.getSQLState());
          System.out.println("Message: " + ex.getMessage());
          System.out.println("Vendor: " + ex.getErrorCode());
          ex.printStackTrace();
          ex = ex.getNextException();
          System.out.println("");
      }
    } 
    catch (Exception e) 
    {
        e.printStackTrace();
    }
  }
  
  public static void close(Statement statement) 
  {
      try 
      {
          if (statement != null)
              statement.close();
      } 
      catch (SQLException ex) 
      {
          while (ex != null) 
          {
              System.out.println("SQLState: " + ex.getSQLState());
              System.out.println("Message: " + ex.getMessage());
              System.out.println("Vendor: " + ex.getErrorCode());
              ex.printStackTrace();
              ex = ex.getNextException();
              System.out.println("");
          }
      } 
      catch (Exception e) 
      {
          e.printStackTrace();
      }
  }

  public static void close(PreparedStatement statement) 
  {
      try 
      {
          if (statement != null)
              statement.close();
      } 
      catch (SQLException ex) 
      {
          while (ex != null) 
          {
              System.out.println("SQLState: " + ex.getSQLState());
              System.out.println("Message: " + ex.getMessage());
              System.out.println("Vendor: " + ex.getErrorCode());
              ex.printStackTrace();
              ex = ex.getNextException();
              System.out.println("");
          }
      } 
      catch (Exception e) 
      {
          e.printStackTrace();
      }
  }

  public static void close(CallableStatement statement) 
  {
      try 
      {
          if (statement != null)
              statement.close();
      } 
      catch (SQLException ex) 
      {
          while (ex != null) 
          {
              System.out.println("SQLState: " + ex.getSQLState());
              System.out.println("Message: " + ex.getMessage());
              System.out.println("Vendor: " + ex.getErrorCode());
              ex.printStackTrace();
              ex = ex.getNextException();
              System.out.println("");
          }
      } 
      catch (Exception e) 
      {
          e.printStackTrace();
      }
  }
  
  public static void close(Connection connection) 
  {
    try 
    {
        if (connection != null)
            connection.close();
    } 
    catch (SQLException ex) 
    {
      while (ex != null) 
      {
          System.out.println("SQLState: " + ex.getSQLState());
          System.out.println("Message: " + ex.getMessage());
          System.out.println("Vendor: " + ex.getErrorCode());
          ex.printStackTrace();
          ex = ex.getNextException();
          System.out.println("");
      }
    } 
    catch (Exception e) 
    {
      e.printStackTrace();
    }
  }
}