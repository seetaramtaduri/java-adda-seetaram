
import java.sql.*;
import java.io.*;

public class Jdbc3{
     public static void main(String []s)throws Exception{

Class.forName("oracle.jdbc.driver.OracleDriver");

Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:server","scott","tiger");

Statement st=con.createStatement();

DataInputStream dis=new DataInputStream(System.in);

System.out.println("enter query");
String query=dis.readLine();
boolean b=st.execute(query);

if(b){
 ResultSet rs=st.getResultSet();
 while(rs.next()){
  System.out.println(rs.getInt(1));
  }
 }//if 
else{
 int i=st.getUpdateCount();
 System.out.println("rows affected are::"+i);
   }//else
 }//main
}//class