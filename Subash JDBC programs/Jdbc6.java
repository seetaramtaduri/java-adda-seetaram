import java.sql.*;
import java.util.*;
import oracle.jdbc.driver.*;

public class Jdbc6{
    public static void main(String []s)throws Exception{
 
OracleDriver od=new OracleDriver();

Properties p=new Properties();
p.put("user","scott");
p.put("password","tiger");

Connection con=od.connect("jdbc:oracle:thin:@localhost:1521:server",p);

//get the CallableStatement

CallableStatement cs=con.prepareCall("{call myprocedure(?,?)}");

//set the Values using setxxx methods
 cs.setInt(1,901);
 cs.setDouble(2,9000);

//now execute the Procedure
  cs.execute();

System.out.println("procedure executed successfully");
  }//main
}//class
