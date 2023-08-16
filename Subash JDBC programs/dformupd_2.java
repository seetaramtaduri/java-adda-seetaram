import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class dformupd extends Frame implements ActionListener
{
	Label dno,dnm,dloc;
	Choice dnum;
	TextField tfdnm,tfdloc;
	Button update,back,sel;
	Connection con;
	PreparedStatement psmt;
	ResultSet rs;
	Statement st;
	private String tnm="";
	Frame mainFr;
	public dformupd(String tName,Frame frm)
	{
		super("Department Updation Form");
	        setBackground(Color.lightGray);
		mainFr=frm;
		tnm=tName;
		setLayout(null);
		setSize(300,300);

		dno=new Label("DNUM :");
		dno.setBounds(50,50,75,20);
		add(dno);		
		dnum=new Choice();
		dnum.setBounds(140,50,50,20);
		add(dnum);
		
		dnm=new Label("DNAME :");
		dnm.setBounds(50,100,75,20);
		add(dnm);		
		tfdnm=new TextField();
		tfdnm.setBounds(140,100,100,20);
		add(tfdnm);

		dloc=new Label("DLOC :");
        dloc.setBounds(50,150,80,20);
		add(dloc);		

		tfdloc=new TextField();
        tfdloc.setBounds(140,150,125,20);
		add(tfdloc);
		
		sel=new Button("SELECT");
        sel.setBounds(35,220,80,20);
		sel.addActionListener(this);
            	add(sel);
		
		update=new Button("Update");
        update.setBounds(125,220,75,20);
		update.addActionListener(this);
            	add(update);

        back=new Button("Home");
        back.setBounds(210,220,65,20);
		back.addActionListener(this);
            	add(back);
        setVisible(true);
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
			String str="select * from "+tnm;
			rs=st.executeQuery(str);
			while(rs.next())
			{
				String s1=Integer.toString(rs.getInt(1));
				dnum.add(s1);
			}
			
			
		}
		catch(ClassNotFoundException e)
		{
            new Message(this,"Class not found");
		}
		catch(SQLException se)
		{
            new Message(this,"Error occured in executing quwry");
		}
	}
	public void actionPerformed(ActionEvent a)
	{
		
		if(a.getSource()==sel)
		{
			int en=Integer.parseInt(dnum.getSelectedItem());
			try
			{
				st=con.createStatement();
				String sel="select * from " +tnm + " where dno=" +en;
				rs=st.executeQuery(sel);
				while(rs.next())
				{
					tfdnm.setText(rs.getString(2));
					tfdloc.setText(rs.getString(3));
				}
			}
			catch(SQLException de)
			{
                new Message(this,"Error occured in executing code");
			}
			
		}
		if(a.getSource()==update)
		{
            boolean flag = true;         
			int enum=Integer.parseInt(dnum.getSelectedItem());
			String ename=tfdnm.getText();
			String ed=tfdloc.getText();
			
			try
			{
				String ins="update "+tnm+" set dno=?,dnm=?,dloc=? where dno=?"; 
				psmt=con.prepareStatement(ins);
				psmt.setInt(1,enum);
				psmt.setString(2,ename);
				psmt.setString(3,ed);
				psmt.setInt(4,enum);
				psmt.executeUpdate();
                flag = true;
			}
			catch(SQLException cb)
			{
                new Message(this,"Error occured in updating");
                flag = false;
			}
            if(flag)
            {
                new Message(this,"Record Updated Successfully");
            }
		}
		if(a.getSource()==back)
		{
			mainFr.show();
			this.dispose();
		}
	}
}
