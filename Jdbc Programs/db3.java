import java.sql.*;
/* Connecting to oracle ..  */
class db3
{
 public static void main(String str[]) throws Exception
 {
  try
  {
   Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
   Connection con = DriverManager.getConnection("jdbc:odbc:pavandsn","scott","tiger");
   System.out.println("connected to Oracle Database");

   PreparedStatement ps = con.prepareStatement("Select * from emp where empno>?");
   ps.setInt(1,2000);
  // ps.setString (2,"manager");
 //  ps.setString(t1.getText());
   ResultSet rs = ps.executeQuery();
      System.out.println("empno       empname     job\n");
      for(int i=0;i<78;i++)
      System.out.print("=");

   while(rs.next())
     {
      int empno = rs.getInt(1);
      String ename= rs.getString(2);
      String job  = rs.getString(3);
      System.out.println(empno+"----");
      System.out.print(ename+"-----");
      System.out.print(job);
     }
     System.out.println("\n");
      for(int i=0;i<78;i++)
      System.out.print("=");


  }catch (Exception e)
   {
   System.out.println(e);
   }


 }
}
