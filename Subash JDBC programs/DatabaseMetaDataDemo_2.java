//program to Demo DatabaseMetaData
import java.sql.*;
import java.util.*;

class DatabaseMetaDataDemo {
 public static void main(String args[]) 
 {
    try
    {
       DriverManager.registerDriver(new sun.jdbc.odbc.JdbcOdbcDriver());
	   Connection con=DriverManager.getConnection("jdbc:odbc:oradsn","system","tiger");
       DatabaseMetaData meta=con.getMetaData();
       System.out.print("Database: "+meta.getDatabaseProductName());
       System.out.println(" version "+meta.getDatabaseProductVersion());
       System.out.println("User name: "+meta.getUserName());
       con.close();
      }
	  catch(Exception ex)
	  {
             System.out.println(ex);
             System.exit(0);
	  }
   }
}
