import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class formdel extends Frame implements ActionListener
{
	Label dno;
	Button del,back;
	Choice dnum;
	Connection con;
	PreparedStatement psmt;
	ResultSet rs;
	Statement st;
	private String tnm="";
	Frame mainFr;
	public formdel(String tName,Frame frm)
	{
		super("Employee Deletion Form");
            	setBackground(Color.lightGray);
		mainFr=frm;
		tnm=tName;
		setLayout(null);
		setSize(300,300);
        dno=new Label("ENO :");
        dno.setBounds(70,100,75,20);
		add(dno);		
		dnum=new Choice();
        dnum.setBounds(180,100,50,20);
		add(dnum);
		
		del=new Button("Delete");
        del.setBounds(75,150,75,20);
		del.addActionListener(this);
            	add(del);

        back=new Button("Home");
        back.setBounds(180,150,75,20);
		back.addActionListener(this);
            	add(back);
		setVisible(true);
            	addWindowListener(new WindowAdapter(){
            		public void WindowClosing(WindowEvent w)
            		{
                	System.exit(0);
            		}
            		});
        try
        {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        }            
		catch(ClassNotFoundException e)
		{
            new Message(this,"Class not found");
        }
		connection();
	}
	public void connection()
	{
		try
		{
                        con=DriverManager.getConnection("jdbc:odbc:users","scott","tiger");
			st=con.createStatement();
			String str="select * from "+tnm;
			rs=st.executeQuery(str);
			while(rs.next())
			{
				dnum.add(Integer.toString(rs.getInt(1)));
			}
                                String ins="delete from "+tnm +"  where eno=?";
                                  psmt=con.prepareStatement(ins);
			
		}
		catch(SQLException se)
		{
            new Message(this,"Error occured in executing Query");
		}
	}
	public void actionPerformed(ActionEvent a)
	{
		if(a.getSource()==back)
		{
			mainFr.show();
			this.dispose();
		}
		if(a.getSource()==del)
		{
                        boolean flag = true;
			String ed="";
			int enum=Integer.parseInt(dnum.getSelectedItem());
			try
			{
                        //        String ins="delete from "+tnm +"  where eno=?";
                        //          psmt=con.prepareStatement(ins);
				psmt.setInt(1,enum);
				psmt.executeUpdate();
                                con.close();
                                dnum.removeAll();
                                connection();
                                flag = true;
			}
			catch(SQLException cb)
			{
                                new Message(this,"Error in executing Query");
                                flag=false;
			}
                        if(flag)
                        {
                                new Message(this,"Record deleted");
                        }
		}

	}
}
