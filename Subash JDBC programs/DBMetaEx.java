import java.io.*;
import java.sql.*;
class DBMetaEx
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
				DatabaseMetaData dbmd=con.getMetaData();
				System.out.println("Database Name:"+dbmd.getDatabaseProductName());
				System.out.println("Database Version:"+dbmd.getDatabaseProductVersion());
				System.out.println("JDBC DriverVersion:"+dbmd.getDriverVersion());
				System.out.println("SQL KeyWords:"+dbmd.getSQLKeywords());
				System.out.println("Numeric Functions:"+dbmd.getNumericFunctions());
				System.out.println("String FUnctions:"+dbmd.getStringFunctions());
				//System.out.println("Suport of stored procedure"+dbmd.supportsStoredProcedure());
				repeat=false;
				}
			catch(Exception e)
			{
				System.out.println("exception raised try again");
			}

		}//while
	}//main
}//class