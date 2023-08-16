import java.sql.*;
class Oradb
{
public static void main(String args[])throws Exception
{
DriverManager.registerDriver(new sun.jdbc.odbc.JdbcOdbcDriver());
Connection con=DriverManager.getConnection("jdbc:odbc:oradsn","scott","tiger");
Statement stmt=con.createStatement();
ResultSet rs=stmt.executeQuery("select * from stud1");
while(rs.next())
{
System.out.println(rs.getInt(1));
System.out.println(rs.getString(2));
System.out.println(rs.getString(3));
System.out.println("................");
}
con.close();
}
}
