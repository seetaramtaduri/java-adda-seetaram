import java.sql.*;
public class CallableStatementEx1 {
public static void main(String s[]) throws Exception {
	Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
	Connection con=DriverManager.getConnection(
		"jdbc:oracle:thin:@mysys:1521:khan","scott","tiger");
	//Step1: Get CallableStatement
	CallableStatement cs= con.prepareCall(
		"{call create_account (?,?,?,?)}");
	//Step2: set IN parameters
	cs.setInt(1, 101);
	cs.setString(2, "Tousif Khan");
	cs.setDouble(3, 24000);
	cs.setDouble(4, 10000);
	//Step3 : register OUT parameters
	//In this procedure example we dont have OUT parameters 
	//Step4
	cs.execute();
	System.out.println("Account Created");
	con.close();
	}//main
}//class
