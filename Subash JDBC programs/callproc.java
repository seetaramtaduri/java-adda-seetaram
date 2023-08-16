import java.sql.*;
class callproc 
{
 public static void main(String str[]) throws Exception
 {
  try
  {
   Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
   Connection con = DriverManager.getConnection("jdbc:odbc:javadsn","scott","tiger");
   System.out.println("connected to Database");


   CallableStatement cs = con.prepareCall(" {call  maxsal(?) } ");
   //.....................CALL UPDATESAL(?,?)
   //CS.registerInParameter(1,Types.INTEGER);
   //CS.registerInParameter(2,Types.INTEGER);
   //CS.setInt(1,101);
   //cs.setInt(2,1000);

   cs.registerOutParameter(1,Types.INTEGER);
   cs.executeUpdate();
   System.out.println(cs.getInt(1));
   }catch (Exception e)
   {
    System.out.println(e);
   }
 }
}
