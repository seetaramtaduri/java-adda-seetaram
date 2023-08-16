/* write an example using PreparedStatement */

import java.sql.*;

public class Jdbc4{
    public static void main(String []s)throws Exception{

System.setProperty("jdbc.drivers","oracle.jdbc.driver.OracleDriver");

Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:server","scott","tiger");

PreparedStatement ps=con.prepareStatement("insert into adj_tab values(?,?)");

ps.setInt(1,501);
ps.setDouble(2,5000);

//execute the query

   int i=ps.executeUpdate();

/* to get the info how many no. of rows are affected */
 System.out.println("rows affected are::"+i);
   }//main
}//class