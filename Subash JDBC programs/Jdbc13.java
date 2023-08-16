import java.sql.*;

public class Jdbc13{
   public static void main(String []s)throws Exception{

Class.forName("oracle.jdbc.driver.OracleDriver");

Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:server","scott","tiger");

Statement st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);

ResultSet rs=st.executeQuery("select * from adj_tab");

while(rs.next()){
 System.out.println(rs.getInt(1) +"\t"+ rs.getDouble(2));
 }
System.out.println(rs.isAfterLast());
System.out.println("***************");

while(rs.previous()){
 System.out.println(rs.getInt(1) +"\t"+ rs.getDouble(2));
 }
System.out.println("***************");
if(rs.first()){
 System.out.println(rs.getInt(1) +"\t"+ rs.getDouble(2));
}

System.out.println("***************");
if(rs.absolute(Integer.parseInt(s[0]))){
 System.out.println(rs.getInt(1) +"\t"+ rs.getDouble(2));
 }
}//main
}//class