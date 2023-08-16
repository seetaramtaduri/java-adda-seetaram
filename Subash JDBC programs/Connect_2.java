// connection program that connects to oracle
// returns Connection object and Statement object
//-----------------------------------------------

import java.sql.*;
public class Connect 
{ 
	 static Connection con=null;
     static boolean flag=false;
     static Statement s=null;
     public static void main(String a[])
     {
              Connection con = Connect.getConnection();
              Statement s = Connect.getStatement();
     }
     public static java.sql.Connection getConnection()
    {
          try
          { 
                 Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                 con=DriverManager.getConnection("jdbc:odbc:oradsn","system","tiger");
                 System.out.println("connected to Database");
          }catch(Exception e){System.out.println(e);}
         flag=true;
    return con;
   }
   public static java.sql.Statement getStatement()
   {
      if (flag==true)
       {  try{
          s=con.createStatement();
		  System.out.println("steatement  is created:");
          }catch(Exception e)
          {System.out.println(e);}
         return s;
       }
         
      else
      {
        con= getConnection();
        try{
        s=con.createStatement();
        }catch(Exception e)
        {System.out.println(e); }
        return s;
      }
   }

}
  

