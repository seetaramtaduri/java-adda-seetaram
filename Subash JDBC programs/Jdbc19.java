import java.sql.*;

public class Jdbc19{
    public static void main(String []s)throws Exception
{
  Class.forName("oracle.jdbc.driver.OracleDriver");

Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:server","scott","tiger");

Statement st=con.createStatement();

int i=st.executeUpdate("insert into markstable values(1,'sabitha',sabmarks(80,80,80,80,90))");
System.out.println("record inserted");
  }
}