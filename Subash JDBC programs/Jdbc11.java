import java.sql.*;
import java.util.*;
import java.io.*;

public class Jdbc11{
      public static void main(String []s)throws Exception{

Driver d=(Driver)Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();

Properties p=new Properties();

p.load(new FileInputStream("mypro.properties"));

Connection con=d.connect(p.getProperty("url"),p);

Statement st=con.createStatement();

ResultSet rs=st.executeQuery("select * from adj_tab");

//get the ResultSetMetaData

ResultSetMetaData rsm=rs.getMetaData();

/* get the ColumnCount , to get the ColumnNames */

int count=rsm.getColumnCount();

for(int i=1;i<=count;i++){
  
//get the ColumnName
System.out.println(rsm.getColumnName(i));
  }//for
 }//main
}//class
