import java.io.*;
import java.sql.*;
class CallProcTwo
{
	public static void main(String args[])
	{
		boolean repeat=true;
		while(repeat)
		{
			try
			{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				Connection con=DriverManager.getConnection("jdbc:odbc:oradsn","scott","tiger");
				CallableStatement cstmt=con.prepareCall("{call proctwo(?,?)}");		
				cstmt.setInt(1,300);
				cstmt.registerOutParameter(2,TYPES.FLOAT);
				cstmt.execute();
				System.out.println("the balance salary of the employee no 30 is "+cstmt.getFloat(2));
				System.out.println("bye bye");
				repeat=false;
			}
			catch(Exception e)
			{
				System.out.println("exception raised try again");
			}

		}//while
	}//main
}//class