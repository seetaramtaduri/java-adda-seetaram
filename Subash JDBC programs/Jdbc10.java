import java.sql.*;
import java.util.*;
import java.io.*;

public class Jdbc10{
       public static void  main(String []s)throws  Exception{

Driver d=(Driver)Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();

Properties p=new Properties();

p.load(new FileInputStream("mypro.properties"));

Connection con=d.connect(p.getProperty("url"),p);

DatabaseMetaData dmd=con.getMetaData();

System.out.println(dmd.getDatabaseProductName());
System.out.println(dmd.getDatabaseProductVersion());
System.out.println(dmd.getDriverName());
System.out.println(dmd.getDriverVersion());
  }//main
}//class