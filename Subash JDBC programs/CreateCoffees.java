import java.sql.*;

public class CreateCoffees {
    public static void main(String args[]) {
        String url = "jdbc:odbc:oradsn";
        Connection con;
        String createString;
        createString = "create table COFFEE " +
                            "(COF_NAME VARCHAR(32), " +
                            "SUP_ID INTEGER, " +
                            "PRICE FLOAT, " +
                            "SALES INTEGER, " +
                            "TOTAL INTEGER)";
        Statement stmt;

        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        } catch(java.lang.ClassNotFoundException e) {
            System.err.print("ClassNotFoundException: ");
            System.err.println(e.getMessage());
        }

        try {
            con = DriverManager.getConnection(url, "scott", "tiger");
            stmt = con.createStatement();
            stmt.executeUpdate(createString);
            stmt.close();
            con.close();

        } catch(SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
    }
}
