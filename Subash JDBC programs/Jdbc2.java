/* write an application where we make use of type-2 drivers , and i wanted to execute a select query */

import java.sql.*;

public class Jdbc2{
     public static void main(String []s)throws Exception{

     Connection con=DriverManager.getConnection("jdbc:oracle:oci8:@server","scott","tiger");

 Statement st=con.createStatement();

ResultSet rs=st.executeQuery("select * from mytable");

while(rs.next()){
  System.out.println(rs.getInt(1) +"\t"+ rs.getDouble(2));
   }//while
 }//main
}//class