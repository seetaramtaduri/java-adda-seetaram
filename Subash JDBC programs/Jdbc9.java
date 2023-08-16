//using functions

import java.sql.*;
import java.io.*;
import java.util.*;

public class Jdbc9{
     public static void main(String []s)throws Exception{

Driver d=(Driver)Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();

Properties p=new Properties();

p.load(new FileInputStream("mypro.properties"));

Connection con=d.connect(p.getProperty("url"),p);

CallableStatement cs=con.prepareCall("{call ?:=myfun(?)}");

cs.registerOutParameter(1,Types.DOUBLE);

cs.setInt(2,Integer.parseInt(s[0]));

cs.execute();

double amt=cs.getDouble(1);

System.out.println("The retrive sal is::"+amt);
 
     }//main
}//class