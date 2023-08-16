import java.sql.*;
import java.util.*;


public class Jdbc8{
       public static void main(String []s)throws Exception{

// get the Driver type

Driver d=(Driver)Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();

Properties p=new Properties();
p.put("user","scott");
p.put("password","tiger");

Connection con=d.connect("jdbc:oracle:thin:@localhost:1521:server",p);

CallableStatement cs=con.prepareCall("{call myprocedure3(?,?)}");

cs.setInt(1,Integer.parseInt(s[0]));
cs.setDouble(2,Double.parseDouble(s[1]));

cs.registerOutParameter(2,Types.DOUBLE);

cs.execute();

double amt=cs.getDouble(2);

System.out.println("Sal retrived is::"+amt);
    }//main
}//class
