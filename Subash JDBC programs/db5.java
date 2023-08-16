import java.sql.*;

class db5
{
 public static void main(String arg[]) throws Exception
 {
  try
  {
       Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
  Connection con = DriverManager.getConnection("jdbc:odbc:oracledsn","scott","tiger");

  PreparedStatement ps = con.prepareStatement("update dept set dname=?
                                               where loc=?" where deptno=?);
 ps.setString(1,"elec");
 ps.setString(2,"delhi");
 ps.setString(3,10);

  cs.executeUpdate();
  con.commit();
  Sytstem.out.println("Updated");
  }catch(Exception e)
  {
   System.out.println(e);
  }

 }
}
