import java.sql.*;
public class CreateTable
{
public static void main(String args[])
	throws SQLException,ClassNotFoundException
{
//Get Connection
	Connection con=prepareConnection(); 
	Statement st=con.createStatement();
	String query = "create table employee"+
			 "(emp_name varchar2(20),"+ 
			 "emp_id number, emp_sal number(10,2))";
	//Execute the Query
	st.executeUpdate(query);
	System.out.println("Table Created Successfully");
	con.close(); // closing the connection
}//main

public static Connection prepareConnection() 
	throws SQLException,ClassNotFoundException
{
	String driverClassName="oracle.jdbc.driver.OracleDriver";
	String url="jdbc:oracle:thin:@mysys:1521:khan";
	String username="scott";
	String password="tiger";
	//Load driver class
	Class.forName(driverClassName);
	// Obtain a connection
	Connection con=DriverManager.getConnection(url,username,password);
	return con;
}//prepareConnection
}//class