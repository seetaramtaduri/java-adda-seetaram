import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class dformdel extends Frame implements ActionListener
{
	Label dno;
	Button del,back;
	Choice dnum;
	Connection con;
	PreparedStatement psmt;
	ResultSet rs;
	Statement st;
	Frame mainFr;
	private String tnm="";
	public dformdel(String tName,Frame dfr)
	{
		super("DEPARTMENT DELETION FORM");
            	setBackground(Color.lightGray);
		tnm=tName;
		mainFr=dfr;
		setLayout(null);
		
        dno=new Label("DNAME :");
        dno.setBounds(70,100,75,20);
		add(dno);		
		dnum=new Choice();
        dnum.setBounds(180,100,100,20);
		add(dnum);
		
		del=new Button("Delete");
        del.setBounds(75,160,75,20);
		del.addActionListener(this);
            	add(del);

        back=new Button("Home");
        back.setBounds(180,160,75,20);
		back.addActionListener(this);
            	add(back);
 		setVisible(true);
		setSize(300,300);
            	addWindowListener(new WindowAdapter(){
            		public void WindowClosing(WindowEvent w)
            		{
               			System.exit(0);
            		}
            		});
		connection();
		
	}
	public void connection()
	{
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con=DriverManager.getConnection("jdbc:odbc:users","scott","tiger");
			st=con.createStatement();
            String str="select dnm from "+tnm;
			rs=st.executeQuery(str);
			while(rs.next())
			{
                dnum.add(rs.getString(1));
			}
			
		}
		catch(ClassNotFoundException e)
		{
            new Message(this,"Class not found");
		}
		catch(SQLException se)
		{
            new Message(this,"Error in executing statement");
		}
	}
	public void actionPerformed(ActionEvent a)
	{


		if(a.getSource()==del)
		{
            boolean flag = true;
			String ed="";
			try
			{
                String ins="delete from Department where dnm=?";
				psmt=con.prepareStatement(ins);
                psmt.setString(1,dnum.getSelectedItem());
				psmt.executeUpdate();
                flag = true;
                dnum.removeAll();
                st = con.createStatement();
                rs = st.executeQuery("select dnm from department");
                while(rs.next())
                {
                    dnum.addItem(rs.getString(1));
                }
			}
			catch(SQLException cb)
			{
                new Message(this,"You can't delete ! Employees exists");
                flag = false;
			}
            if(flag)
            {
                new Message(this,"Record deleted");
            }
		}
		if(a.getSource()==back)
		{
			this.dispose();
			mainFr.show();
		}		
	}
}

