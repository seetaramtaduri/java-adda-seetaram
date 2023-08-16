import java.sql.*;

/*
 create procedure minsal(no out number)
  is
  begin
    select min(sal) into no from emp ;
  end;
*/

class db4proc
{
 public static void main(String arg[]) throws Exception
 {
  try
  {
  Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
  Connection con = DriverManager.getConnection("jdbc:odbc:oracledsn","scott","tiger");

  CallableStatement cs = con.prepareCall("{call minsal(?,?)}");
                                                updatesal(?,?)

  cs.registerInParameter(1,10);
  cs.registerOutParameter(2,Types.INTEGER);

  cs.executeUpdate();
  int j = cs.getInt(1);
  System.out.println("min. salary is " +j);
  }catch (Exception e)
   {
    System.out.println(e);
   }


  //con.commit()
  //con.rollback();
  //con.setAutoCommit(false);
 }
}
