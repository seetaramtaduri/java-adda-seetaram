
import java.sql.*;

public class Jdbc1{
     public static void main(String []s)throws Exception{

//register the driver

DriverManager.registerDriver(new sun.jdbc.odbc.JdbcOdbcDriver());

//open session to db
Connection con=DriverManager.getConnection("jdbc:odbc:mydatasource","scott","tiger");


//get the Statement
 Statement st=con.createStatement();

//execute the query
   st.executeUpdate("create table adj_tab(accno number,sal number(8,2))");
  
System.out.println("query executed and table created");

  }//main
}//class