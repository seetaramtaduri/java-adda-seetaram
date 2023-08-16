import java.io.*;
import java.sql.*;
class DeleteQueryEx
{
	public static void main(String args[]) throws Exception 
	{
		boolean repeat=true;
		while(repeat)
		{
			try
			{
			class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("jdbc:odbc:oradsn","scott","tiger");
			Statement stmt=con.createStatement();
			DataInputStream dis=new DataInputStream(system.in);
			System.out.println("enter employee no to be deleted");
			String s=dis.readLine();
			int no=Integer.parseInt(s);
			int count=stmt.executeUpdate("delete from myemp where eno="+eno);
			if(count>0)			
			System.out.println("Record deleted  successfully");
			else
			System.out.println("Record Updation failed");
			repeat=flase;
			}//try
			catch(Exception e)
			{
				System.out.println("exception raised trya again ");
			}//catch

		}//while
	}//main
}//class