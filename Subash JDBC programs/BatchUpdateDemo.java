import java.sql.*;
import java.util.*;
import java.io.*;

public class BatchUpdateDemo {

public static void main(String s[]) throws Exception {
Driver d= (Driver) ( Class.forName( 
	"oracle.jdbc.driver.OracleDriver").newInstance());
Properties p=new Properties ();
p.put("user","scott");
p.put("password","tiger");
Connection con=d.connect("jdbc:oracle:thin:@mysys:1521:khan",p);
Statement st=con.createStatement();
//statement1
st.addBatch("insert into emp(empno,sal,deptno)"+
	" values("+s[0]+",1000,10)");
//statement2
st.addBatch("update emp set sal=2000 where empno="+s[0]);
//statement3
st.addBatch("insert into emp(empno,sal,deptno) values(202,1000,10)");
//statement4
st.addBatch("insert into emp(empno,sal,deptno) values(203,1000,10)");
try {
	int[] counts=st.executeBatch();
	System.out.println("Batch Executed Successfully");
	for (int i=0;i< counts.length;i++){
	System.out.println("Records effected by statement"+(i+1)+
		": "+counts[i]);
	}//for
}//try
catch(BatchUpdateException e){
	System.out.println("Batch terminated with an abnormal condition");
	int[] counts=e.getUpdateCounts();
	System.out.println("Batch terminated at statement"
	+(counts.length+1));	
	for (int i=0;i< counts.length;i++) {
	System.out.println("Records effected by the statement"+ (i+1)+
	": "+counts[i]);
	}//for
}//catch
con.close();
}//main
}//class
