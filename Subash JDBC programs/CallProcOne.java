import java.io.*;
import java.sql.*;
class CallProcOne
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
				CallableStatement cstmt=con.prepareCall("{call procone}");		
				cstmt.executeQuery();
				System.out.println("procedure called");
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