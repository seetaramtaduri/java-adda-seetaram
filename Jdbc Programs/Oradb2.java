import java.sql.*;
import java.io.*;
class Oradb2
{
public static void main(String args[])throws Exception
{
//int i;
char ch='y';
BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
DriverManager.registerDriver(new sun.jdbc.odbc.JdbcOdbcDriver());
Connection con=DriverManager.getConnection("jdbc:odbc:oradsn","scott","tiger");
PreparedStatement pstmt=con.prepareStatement("insert into bhagu values(?,?)");
for(int i=1;i<=5;i++)
{
System.out.println("enter bid");
int n=Integer.parseInt(br.readLine());

System.out.println("enter name");
String str=br.readLine();
pstmt.setInt(1,n);
pstmt.setString(2,str);



pstmt.executeUpdate();
}
pstmt.close();
con.close();


}
}
