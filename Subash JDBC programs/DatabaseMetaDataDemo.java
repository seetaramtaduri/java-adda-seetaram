import java.sql.*;
public class  DatabaseMetaDataDemo{

public static void main(String[] args) 
	throws Exception
{
	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	Connection con =
	DriverManager.getConnection("jdbc:odbc:khanDSN",
				"scott","tiger");
	DatabaseMetaData db=con.getMetaData();
	String[] types={"TABLE"};
	ResultSet rs=db.getTables(null,"scott",args[0],types);
	System.out.print("Catalog\t");
	System.out.print("Table_Schema\t");
	System.out.print("Table_Name\t");
	System.out.print("Remarks\t\t"); 
	System.out.println("Types_Catalog");
	System.out.println("------------------------------------------");
	while(rs.next())
	{
		System.out.print(rs.getString(1)+"\t");
		System.out.print(rs.getString(2)+"\t\t");
		System.out.print(rs.getString(3)+"\t\t");
		System.out.print(rs.getString(4)+"\t\t"); 
		System.out.println(rs.getString(5));
	}//while
	rs.close();
	con.close();
	}//main
}//class
