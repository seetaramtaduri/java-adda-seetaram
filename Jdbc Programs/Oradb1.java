import java.sql.*;
class Oradb1
{
public static void main(String args[])throws Exception
{
DriverManager.registerDriver(new sun.jdbc.odbc.JdbcOdbcDriver());
Connection con=DriverManager.getConnection("jdbc:odbc:oradsn","scott","tiger");
Statement stmt=con.createStatement();
String vsql="create table bhagu(bid number(5),bname varchar(5))";
stmt.executeUpdate(vsql);
stmt.close();
con.close();
}
}
