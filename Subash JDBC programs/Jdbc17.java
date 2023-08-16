import java.sql.*;
import java.io.*;

public class Jdbc17{
    public static void main(String []s)throws Exception{

Class.forName("oracle.jdbc.driver.OracleDriver");
Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:server","scott","tiger");

PreparedStatement ps=con.prepareStatement("insert into adj_java values(?,?)");

ps.setInt(1,201);

File f=new File("anand.jpg");

FileInputStream fis=new FileInputStream(f);

//use setBinaryStream

ps.setBinaryStream(2,fis,(int)f.length());

int count=ps.executeUpdate();

System.out.println("image inserted superly");
  }//main
}//class