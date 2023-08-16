//executed correctly
import java.sql.*;
/* Connecting to oracle .. creating/ inserting, deleting, updating table */
class db2
{
 public static void main(String str[]) throws Exception
 {
  try
  {
   Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
   Connection con = DriverManager.getConnection("jdbc:odbc:raghu","scott","tiger");
   System.out.println("connected to Database");

   //Connect con=Connect.getConnection();
   // Statement s = con.getStatment();

   Statement s = con.createStatement();

   s.executeUpdate("create table emp2(empno number(5), ename char(20) )");
   System.out.println("table emp is created:");

   s.executeUpdate("insert into emp1 values(111,'murthy') ");
   System.out.println("row inserted");

   s.executeUpdate("update emp1 set ename='raghu' where empno=111");

   System.out.println("1 row Updated");



   int x = s.executeUpdate("delete from emp1 where empno>222");
   System.out.println("Rows deleted are "+x);
  }catch (Exception e)
   {
   System.out.println(e);
   }


 }
}
