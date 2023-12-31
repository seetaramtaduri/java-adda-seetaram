import java.io.*;
import java.sql.*;

public class CountRows{
	public static void main(String[] args) {
		System.out.println("Count number of rows in a specific table!");
		Connection con = null;
		int count = 0;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbctutorial","root","root");
			try{
				Statement st = con.createStatement();
				BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
				System.out.println("Enter table name:");
				String table = bf.readLine();
				ResultSet res = st.executeQuery("SELECT COUNT(*) FROM "+table);
				while (res.next()){
					count = res.getInt(1);
				}
				System.out.println("Number of column:"+count);
			}
			catch (SQLException s){
				System.out.println("SQL statement is not executed!");
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
}