import java.sql.*;
import java.io.*;
import java.util.*;

public class Jdbc15{
      public static void main(String []s)throws Exception{

Driver d=(Driver)Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();

Properties p=new Properties();
p.load(new FileInputStream("mypro.properties"));

Connection con=d.connect(p.getProperty("url"),p);

Statement st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);

ResultSet rs=st.executeQuery("select accno,sal from adj_tab");

rs.moveToInsertRow();

rs.updateInt(1,Integer.parseInt(s[0]));
rs.updateDouble(2,Double.parseDouble(s[1]));

rs.insertRow();

System.out.println("row inserted");
  }//main
}//class