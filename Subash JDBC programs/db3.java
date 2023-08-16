//program to retrieve an emp record
import java.sql.*;
import java.util.Scanner;
/* Connecting to oracle ..  */
class db3
{
 public static void main(String str[]) throws Exception
 {
    try
    {
         Scanner  s=new Scanner(System.in);
		 Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
         Connection con=DriverManager.getConnection("jdbc:odbc:oradsn","system","tiger");
         System.out.println("connected to Oracle Database");

         PreparedStatement ps = con.prepareStatement("Select * from emp where empno=?");
         System.out.println("Enter emp no to retrieve Record");
		 int n=s.nextInt();
		 ps.setInt(1,n);
  
         ResultSet rs = ps.executeQuery();
         System.out.println("empno       empname     \n");
          while(rs.next())
          {
                int empno = rs.getInt(1);
                String ename= rs.getString(2);
                
                System.out.print(empno+"\t");
                System.out.print(ename);
     
           }
           System.out.println("\n");
   }
   catch (Exception e)
   {
   System.out.println(e);
   }


 }
}
