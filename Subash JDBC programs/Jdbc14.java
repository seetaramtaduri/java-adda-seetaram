import java.sql.*;

public class Jdbc14{
      public static void main(String []s)throws Exception{
     Class.forName("oracle.jdbc.driver.OracleDriver");
Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:server","scott","tiger");
Statement st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
ResultSet rs=st.executeQuery("select accno,sal from adj_tab");
rs.absolute(Integer.parseInt(s[0]));
rs.updateDouble(2,Double.parseDouble(s[1]));
rs.updateRow();
System.out.println("row updated");
  }//main
}//class