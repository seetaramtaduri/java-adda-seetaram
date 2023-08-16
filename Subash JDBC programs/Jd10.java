//using DatabaseMetaData
/* we use a different mechanism to load the Driver */

import java.sql.*;
import java.util.*;
import java.io.*;

public class Jd10{
    public static void main(String []s)throws Exception{
 
 Driver d=(Driver)Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();

Properties p=new Properties();

p.load(new FileInputStream("myproperties.properties"));

Connection con=d.connect("jdbc:oracle:thin:@localhost:1521:server",p);

DatabaseMetaData dbmd=con.getMetaData();

System.out.println("DB ProductName::"+ dbmd.getDatabaseProductName());

System.out.println("DB ProductVersion::"+ dbmd.getDatabaseProductVersion());

System.out.println("DriverName::"+ dbmd.getDriverName());

System.out.println("Driver Version::"+ dbmd.getDriverVersion());
 }
}

