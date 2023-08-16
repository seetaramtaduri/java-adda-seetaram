import java.io.*;
import java.sql.*;
class BatchUpdateEx
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
				Statement stmt=con.createStatement();
				stmt.addBatch("update myemp set sal=5000 where no=7");
				stmt.addBatch("update myemp set sal=15000 where no=2");
				int r[]=stmt.executeBatch();
				for(int i=0;i<r.length;i++)
				{
					System.out.println("NO of records updated"+r[i]);
				}
					repeat=false;
				}
			catch(Exception e)
			{
				System.out.println("exception raised try again");
			}

		}//while
	}//main
}//class