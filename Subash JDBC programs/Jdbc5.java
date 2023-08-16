import java.sql.*;
import java.util.*;
public class Jdbc5{
   public static void main(String []s)throws Exception{

//create Driver instance

oracle.jdbc.driver.OracleDriver od=new oracle.jdbc.driver.OracleDriver();

/* create the instance of java.util.Properties */

Properties p=new Properties();

p.put("user","scott");
p.put("password","tiger");

Connection con=od.connect("jdbc:oracle:thin:@localhost:1521:server",p);

PreparedStatement ps=con.prepareStatement("select * from adj_tab where accno=?");

ps.setInt(1,Integer.parseInt(s[0]));

ResultSet rs=ps.executeQuery();

while(rs.next()){
  System.out.println(rs.getInt(1) +"\t"+ rs.getDouble(2));
  }
 System.out.println("query executed ");
 ps.setInt(1,Integer.parseInt(s[1]));

  rs.close();
rs=ps.executeQuery();
 while(rs.next()){
 System.out.println(rs.getInt(1) +"\t"+ rs.getDouble(2));
  }//while
 }//main
}//class

/* 