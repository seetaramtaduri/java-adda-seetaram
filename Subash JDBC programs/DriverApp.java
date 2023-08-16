import java.sql.*;
import java.util.*;

class DriverApp {
 public static void main(String args[]) {
  try{
    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
    Enumeration drivers = DriverManager.getDrivers();
   System.out.println("Available drivers:");
    while(drivers.hasMoreElements()){
    Driver driver=(Driver)drivers.nextElement();
    System.out.println("  Driver: "+driver.getClass().getName());
    System.out.println("  Major version: "+driver.getMajorVersion());
    System.out.println("  Minor version: "+driver.getMinorVersion());
    System.out.println("  JDBC compliant: "+driver.jdbcCompliant());
    DriverPropertyInfo props[] = driver.getPropertyInfo("",null);
    if(props!=null){
     System.out.println("  Properties: ");
     for(int i=0;i<props.length;++i){
      System.out.println("    Name: "+props[i].name);
      System.out.println("      Description: "+props[i].description);
      System.out.println("      Value: "+props[i].value);
      if(props[i].choices!=null){
       System.out.println("      Choices: ");
       for(int j=0;j<props[i].choices.length;++j)
        System.out.println("        "+props[i].choices[j]);
      }
      System.out.println("      Required: "+props[i].required);
     }
    }
   }
  }catch(Exception ex){
   System.out.println(ex);
   System.exit(0);
  }
 }
}
