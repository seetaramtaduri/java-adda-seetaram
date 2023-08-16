import java.sql.*;

class DBApp
{

        public static void main(String str[])
        {

        try{
                Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        }
        catch(ClassNotFoundException sql)
        {
                        System.out.println(sql.getMessage());
        }
        try{
        Connection con=DriverManager.getConnection("jdbc:odbc:padma","scott","tiger");
        Statement stm=con.createStatement();
        ResultSet rs=stm.executeQuery("select * from emp");
        ResultSetMetaData rsmd=rs.getMetaData();
        int colcnt=rsmd.getColumnCount();
        for(int i=1;i<=colcnt;i++)
                System.out.print(rsmd.getColumnName(i)+" ");
        System.out.println("");

        while(rs.next())
        {
                for(int i=1;i<=colcnt;i++)
                        System.out.print(rs.getString(i)+" ");
                System.out.println("");
        }
        rs.close();
        stm.close();
        con.close();
        
        }catch(SQLException sql)
        {
                        System.out.println(sql.getMessage());
        }

        }
}

