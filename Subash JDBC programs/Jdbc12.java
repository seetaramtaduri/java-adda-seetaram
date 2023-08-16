import java.sql.*;

public class Jdbc12{
     public static void main(String []s)throws Exception{

Class.forName("oracle.jdbc.driver.OracleDriver");
Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:server","scott","tiger");

Statement st=con.createStatement();

st.addBatch("create table myta_tab(col number,col2 number(8,2))");
st.addBatch("insert into adj_tab values(801,5000)");
st.addBatch("update adj_tab set sal=3000 where accno=101");

//execute the Batch

int count[]=st.executeBatch();
for(int i=0;i<count.length;i++){
   System.out.println("Query::" +(i+1)+ "::has executed::" +count[i]+ "::number of rows affected");
    }//for
 }//main
}//class
