import java.sql.*;

public class Jdbc16{
     public static void main(String []s)throws Exception{

Class.forName("oracle.jdbc.driver.OracleDriver");

Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:server","scott","tiger");

Statement st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);

st.execute("select accno,sal from adj_tab");

ResultSet rs=st.getResultSet();

rs.absolute(Integer.parseInt(s[0]));

rs.deleteRow();
System.out.println("row deleted");
  }//main
}//class