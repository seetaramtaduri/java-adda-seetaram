import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class formupd extends Frame implements ActionListener
{
	Label eno,enm,esal,edep;
	Choice edept,jf1;
	TextField jf2,jf3;
	Button upd,back,sel;
	Connection con1,con2;
	PreparedStatement psmt;
	ResultSet rs1,rs2;
	Statement st1,st2;
	private String tnm="";
	Frame mainFr;
	public formupd(String tName,Frame frm)
	{
		super("Employee Updation Form");
            	setBackground(Color.lightGray);
		mainFr=frm;
		tnm=tName;
		setLayout(null);
		setSize(300,300);
		eno=new Label("ENUM :");
		eno.setBounds(50,50,75,20);
		add(eno);		
		jf1=new Choice();
		jf1.setBounds(140,50,50,20);
		add(jf1);
		
		enm=new Label("ENAME :");
		enm.setBounds(50,100,75,20);
		add(enm);		
		jf2=new TextField();
		jf2.setBounds(140,100,100,20);
		add(jf2);

		esal=new Label("ESAL :");
		esal.setBounds(50,150,75,20);
		add(esal);		
		jf3=new TextField();
		jf3.setBounds(140,150,75,20);
		add(jf3);

		edep=new Label("DEPNO :");
		edep.setBounds(50,200,75,20);
		add(edep);		

		edept=new Choice();
		edept.setBounds(140,200,75,20);
		add(edept);
		
		sel=new Button("SELECT");
        sel.setBounds(35,250,80,20);
		sel.addActionListener(this);
            	add(sel);
		
		upd=new Button("Update");
        upd.setBounds(125,250,75,20);
		upd.addActionListener(this);
            	add(upd);

        back=new Button("Home");
        back.setBounds(210,250,65,20);
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
                        con1=DriverManager.getConnection("jdbc:odbc:users","scott","tiger");
                        con2=DriverManager.getConnection("jdbc:odbc:users","scott","tiger");
			st1=con1.createStatement();
			st2=con2.createStatement();
			String str1="select * from "+tnm;
			String str2="select * from "+"Department";
			rs1=st1.executeQuery(str1);
			while(rs1.next())
			{
				String s1=Integer.toString(rs1.getInt(1));
				jf1.add(s1);
				
			}
			rs1.close();
			con1.close();
			rs2=st2.executeQuery(str2);
			while(rs2.next())
			{
			
				String s2=Integer.toString(rs2.getInt(1));
				edept.add(s2);
			}
			rs2.close();
			con2.close();
		}
		catch(ClassNotFoundException e)
		{
            new Message(this,"Class not found");
		}
		catch(SQLException se)
		{
            new Message(this,"Error in executing in query");
		}
	}
	public void actionPerformed(ActionEvent a)
	{
		if(a.getSource()==back)
		{
			mainFr.show();
			this.dispose();
		}
		if(a.getSource()==sel)
		{
			int en=Integer.parseInt(jf1.getSelectedItem());
            jf2.setText("");
            jf3.setText("");
			try
			{
                                con1=DriverManager.getConnection("jdbc:odbc:users","scott","tiger"); 
				st1=con1.createStatement();
				String sel="select * from " +tnm + " where eno=" +en;
				rs1=st1.executeQuery(sel);
				while(rs1.next())
				{
					jf2.setText(rs1.getString(2));
                    Float f1 = new Float(rs1.getFloat(3));
                    edept.select(Integer.toString(rs1.getInt(4)));
                    jf3.setText(f1.toString());
				}				
				
			}
			catch(SQLException de)
			{
                new Message(this,"Error occured in executing selection");
			}
			
		}
		if(a.getSource()==upd)
		{
            boolean flag = true;         
			int enum=Integer.parseInt(jf1.getSelectedItem());
			String ename=jf2.getText();
			float esal=Float.valueOf(jf3.getText()).floatValue();
			int ed=Integer.parseInt(edept.getSelectedItem());
			try
			{
				String ins="update "+tnm+" set eno=?,enm=?,esal=?, edno=? where eno=?"; 
				psmt=con1.prepareStatement(ins);
				psmt.setInt(1,enum);
				psmt.setString(2,ename);
				psmt.setFloat(3,esal);
				psmt.setInt(4,ed);
				psmt.setInt(5,enum);
				psmt.executeUpdate();
                flag = true;
			}
			catch(SQLException cb)
			{
                new Message(this,"Error in updating statement");
                flag = false;
			}
            if(flag)
            {
                new Message(this,"Record updated successfully");
            }
		}
	}
}
