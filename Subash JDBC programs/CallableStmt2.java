import java.io.*;
import java.sql.*;
import javax.sql.*;
public class CallableStmt2
{
public static void main(String arg[])throws Exception
{

Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
Connection con=DriverManager.getConnection("jdbc:odbc:oradsn","scott","tiger");
CallableStatement stmt=con.prepareCall("{call myproc2(?,?)}");

stmt.setInt(1,3);
//stmt.setFloat(2,500);
//stmt.setFloat(2,Type.FLOAT);


stmt.registerOutParameter(2,Types.FLOAT);
stmt.executeUpdate();
float incsal=stmt.getFloat(2);
System.out.println("incremented sal="+incsal);

con.close();
}
}
