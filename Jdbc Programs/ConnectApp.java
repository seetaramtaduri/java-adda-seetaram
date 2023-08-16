import java.sql.*;
import java.util.*;

class ConnectApp {
 public static void main(String args[]) {
  try{
   Class.forName("ids.sql.IDSDriver");
   String url="jdbc:ids://cx122974-a.cv1.sdca.home.com:80/";
   url+="conn?dbtype=odbc&dsn='IDSExamples'";
   Connection connection=DriverManager.getConnection(url);
   DatabaseMetaData meta=connection.getMetaData();
   System.out.print("Database: "+meta.getDatabaseProductName());
   System.out.println(" version "+meta.getDatabaseProductVersion());
   System.out.println("User name: "+meta.getUserName());
   connection.close();
  }catch(Exception ex){
   System.out.println(ex);
   System.exit(0);
  }
 }
}
