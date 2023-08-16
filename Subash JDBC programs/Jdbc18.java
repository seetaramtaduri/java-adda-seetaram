//Now retrive the image from db

import java.sql.*;
import java.io.*;

public class Jdbc18{
      public static void main(String []s)throws Exception{

Class.forName("oracle.jdbc.driver.OracleDriver");

Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:server","scott","tiger");

Statement st=con.createStatement();

ResultSet rs=st.executeQuery("select stdimage from adj_java where no=201");

while(rs.next()){
  InputStream is=rs.getBinaryStream(1);
FileOutputStream fos=new FileOutputStream("anand.jpg");
int i=is.read();
while(i!=-1){
   fos.write(i);
   i=is.read();
   }//while
 }//if
System.out.println("image retrived");
 }//main
}//class 
