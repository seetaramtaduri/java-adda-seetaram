import java.io.*;
import java.sql.*;
class CommitEx
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
				con.setAutoCommit(false);
				Statement stmt=con.createStatement();
				stmt.executeUpdate("insert into myemp values(25,'rama',20000,'app')");
				stmt.executeUpdate("insert into myemp values(20,'amar',20000,'aps')");
				con.commit();
				stmt.executeUpdate("update myemp set sal=15000 where no=7");
				con.rollback();
				stmt.executeUpdate("update myemp set sal=25000 where no=2");
				con.commit();
				repeat=false;
			}
			catch(Exception e)
			{
				System.out.println("exception raised try again");
			}

		}//while
	}//main
}//class