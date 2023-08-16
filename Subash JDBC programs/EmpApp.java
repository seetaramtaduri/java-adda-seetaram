* Employee Application

// Main flie of  applicartion

import java.awt.*;
import java.awt.event.*;

public class EmpApp extends Frame implements ActionListener{
	MenuBar mbar;
	Menu file,appData,subemp,subdept;
	MenuItem empins,empdel,empupdate,fexit,depins,depdel,depupdate;
	String tab="";
	public EmpApp()
	{
		super("Employee Information System");
		setLayout(null);
		setSize(300,300);
		mbar=new MenuBar();
		file=new Menu("File");
		fexit = new MenuItem("Exit");	
		fexit.addActionListener(this);
		file.add(fexit);	
		mbar.add(file);
		appData=new Menu("AppData");
		subemp = new Menu("Emp");
		empins = new MenuItem("Insert");
		empins.addActionListener(this);
		empdel= new MenuItem("Delete");
		empdel.addActionListener(this);
		empupdate= new MenuItem("Update");
		empupdate.addActionListener(this);
		subemp.add(empins);
		subemp.add(empdel);
		subemp.add(empupdate);
		appDat	a.add(subemp);
		subdept = new Menu("Dept");
		depins = new MenuItem("Insert");
		depins.addActionListener(this);
		depdel= new MenuItem("Delete");
		depdel.addActionListener(this);
		depupdate= new MenuItem("Update");
		depupdate.addActionListener(this);
		subdept.add(depins);
		subdept.add(depdel);
		subdept.add(depupdate);
		appData.add(subdept);  
		mbar.add(appData);
		setMenuBar(mbar);
		setVisible(true);
		addWindowListener(new WindowAdapter(){
		public void windowClosing(WindowEvent we)
		{
			System.exit(0);
		}
		});
	}
	public void actionPerformed(ActionEvent e)
	{	
		if(e.getSource()==fexit)
		{
			System.exit(0);
		}
		if(e.getActionCommand()=="Insert")
		{	
			if(((MenuItem)e.getSource()).getParent()==subemp)                
			{	
				tab="Employee";
				new formins(tab,this);
				this.hide();
			}
			if(((MenuItem)e.getSource()).getParent()==subdept)				
			{       
				tab="Department";
				new dformins(tab,this);
				this.hide();
			}
		}
		if(e.getActionCommand()=="Delete")
		{
			if(((MenuItem)e.getSource()).getParent()==subemp)                
			{
				tab="Employee";
				new formdel(tab,this);
				this.hide();
			}
			if(((MenuItem)e.getSource()).getParent()==subdept)	
			{                           
				tab="Department";
				new dformdel(tab,this);
				this.hide();	
			}
		}
		if(e.getActionCommand()=="Update")
		{
			if(((MenuItem)e.getSource()).getParent()==subemp)					
			{
				tab="Employee";
				new formupd(tab,this);
				this.hide();
			}
			if(((MenuItem)e.getSource()).getParent()==subdept)                            
			{
				tab="Department";
				new dformupd(tab,this);
				this.hide();
			}
		}	
	}
	public static void main(String[] args)
	{
		new EmpApp();
	}
}
	
* Message box file

// Program to print general (successfully insertion, deletion and updation of data) and error //messages 

import java.awt.*;
import java.awt.event.*;

class Message extends Dialog implements ActionListener
{
	Message(Frame dw, String s)
	{
		super(dw,"Message",false);
		setResizable(false);
		setLayout(null);
		setSize(280,120);
		addNotify();
		setLocation(150,150);
		label1=new Label(s);
		add(label1);
		label1.setBounds(getInsets().left+30,getInsets().top+20,220,20);	
		bnOk=new Button("OK");
		add(bnOk);
		bnOk.setBounds(getInsets().left+80,getInsets().top+60,60,20);
		bnOk.addActionListener(this);
		setVisible(true);
		pack();
	}
	public synchronized void setVisible(boolean flag)
	{
		Rectangle bounds=getParent().getBounds();
		Rectangle abounds=getBounds();
		super.setVisible(flag);
	}	
	public void actionPerformed(ActionEvent event)
	{
		Object source=event.getSource();
		setVisible(false);
		dispose();	
	}
	Label label1;
	Button bnOk;
}

* Employee insertion file

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

class formins extends Frame implements ActionListener
{
	Label eno,ename,esal,edno;
	Choice edept;
	TextField tfeno,tfenm,tfsal;
	Button insert;
	Button back;
	Connection con;
	PreparedStatement psmt;
	ResultSet rs;
	Statement st;
	private String tnm="";
	Hashtable ht;
	Frame fr;
	
	public formins(String tName,Frame f){
      	super("EMPLOYEE DATA INSERTION FORM");
		tnm=tName;
		fr=f;
		setLayout(null);
		setSize(300,300);
		setBackground(Color.lightGray);
		ht = new Hashtable();

		eno=new Label("ENUM :");
		eno.setBounds(50,50,75,20);	
		add(eno);

		tfeno=new TextField();
		tfeno.setBounds(140,50,50,20);
		add(tfeno);
		
		ename=new Label("ENAME :");
		ename.setBounds(50,100,75,20);
		add(ename);
		tfenm=new TextField();
		tfenm.setBounds(140,100,100,20);	
		add(tfenm);	

		esal=new Label("ESAL :");
		esal.setBounds(50,150,75,20);
		add(esal);

		tfsal=new TextField();
		tfsal.se	tBounds(140,150,75,20);
		add(tfsal);		

		edno=new Label("DNAME :");
		edno.setBounds(50,200,75,20);
		add(edno);		
	
		edept=new Choice();
		edept.setBounds(140,200,75,20);
		add(edept);
		
		insert=new Button("Save");
		insert.setBounds(50,250,75,20);
		insert.addActionListener(this);
		add(insert);
	
		back=new Button("Home");
		back.setBounds(150,250,75,20);
		back.addActionListener(this);
		add(back);
		setVisible(true);
		addWindowListener(new WindowAdapter(){

		public void WindowClosing(WindowEvent w)
		{
			System.exit(0);
			fr.show();

		}
		});
		connection();
	}
	public void connection()
	{
		try
		{
			int count = 0;
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con=DriverManager.getConnection("jdbc:odbc:users","users","database");
			st=con.createStatement();
			rs=st.executeQuery("select * from "+" Department");
			while(rs.next())
			{
				count++;
				int s1 = rs.getInt(1);
				String s2 = rs.getString(2);
				edept.add(s2);
				ht.put(new String(s2),new Integer(s1));
			}
			if(count == 0)
			{
				Message m1 = new Message(this,"No records existed");
				m1.show();
			}
		} 
		catch(ClassNotFoundException e)
		{
			new Message(this,"Class not found");
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
			this.dispose();
			fr.show();
		}
		if(a.getSource()==insert)
		{
			String en;
			boolean flag = true;
			int enum=nteger.valueOf(tfeno.getText()).intValue();
			en=tfenm.getText();
			float esal=Float.valueOf(tfsal.getText()).floatValue();
			Integer ed1 = (Integer)(ht.get(edept.getSelectedItem()));
			int ed = ed1.intValue();
			try
			{
				String ins="insert into Employee (eno,enm,esal,edno) values(?,?,?,?)";
				psmt=con.prepareStatement(ins);
				psmt.setInt(1,enum);
				psmt.setString(2,en);
				psmt.setFloat(3,esal);
				psmt.setInt(4,ed);
				psmt.executeUpdate();
				flag = true;	
			}
			catch(SQLException cb)
			{
				Message msg=new Message(this,"Problem occured in insertion");
				msg.show();
				flag = false;
			}
			if(flag)
			{
				Message msg=new Message(this,"Record Inserted Successfully");
				msg.show();
			}	
		}
	}
}

* Employee Deletion file

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
	public formdel(String tName,Frame frm){
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
		try{
			con=DriverManager.getConnection("jdbc:odbc:users","users","database");
			st=con.createStatement();
			String str="select * from "+tnm;
			rs=st.executeQuery(str);
			while(rs.next())
			{
				dnum.add(Integer.toString(rs.getInt(1)));
			}
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
				String ins="delete from "+tnm +"  where eno=?";
				psmt=con.prepareStatement(ins);
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

* Employee Updation file

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
				con1=DriverManager.getConnection("jdbc:odbc:users","users","database");
				con2=DriverManager.getConnection("jdbc:odbc:users","users","database");
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
			if(a.getSource()==sel){
				int en=Integer.parseInt(jf1.getSelectedItem());
				jf2.setText("");
				jf3.setText("");
				try
				{
					con1=DriverManager.getConnection("jdbc:odbc:users","users","database");	
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
* Dept Insertion File

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class dformins extends Frame implements ActionListener
{
	Label dno,dname,dloc;
	TextField tfdno,tfdnm,tfloc;
	Button insert;
	Button back;
	Connection con;
	PreparedStatement psmt;
	ResultSet rs;
	Statement st;
	private String tnm="";
	Frame mainFr;
	public dformins(String tName,Frame frm)
	{
		super("DEPARTMENT DATA INSERTION FORM");
		mai	nFr=frm;
		setBackground(Color.lightGray);
		tnm=tName;
		setLayout(null);
		setSize(300,300);

		dno=new Label("DNUM :");
		dno.setBounds(50,50,75,20);
		add(dno);
		
		tfdno=new TextField();
		tfdno.setBounds(140,50,50,20);
		add(tfdno);
		
		dname=new Label("DNAME :");
		dname.setBounds(50,100,75,20);
		add(dname);

		tfdnm=new TextField();
		tfdnm.setBounds(140,100,100,20);
		add(tfdnm);
        
		dloc=new Label("DLOC :");
		dloc.setBounds(50,150,75,20);
		add(dloc);

		tfloc=new TextField();
		tfloc.setBounds(140,150,100,20);
		add(tfloc);
		insert=new Button("Insert");
		insert.setBounds(80,200,75,20);
		insert.addActionListener(this);
		add(insert);

		back=new Button("Home");
		back.setBounds(180,200,75,20);
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
			con=DriverManager.getConnection("jdbc:odbc:users","users","database");
		}
		catch(ClassNotFoundException e)
		{
			new Message(this,"Class not found");
		}
		catch(SQLException se)
		{
			new Message(this,"Error in connecting to SQL");
		}
	}

	public void actionPerformed(ActionEvent a)
	{
		if(a.getSource()==back)
		{
			mainFr.show();
			this.dispose();
		}
		if(a.getSource()==insert)
		{
			boolean flag = true;     
			int dnum=Integer.parseInt(tfdno.getText());
			String dnm=tfdnm.getText();
			String dlc=tfloc.getText();
			try
			{
				String ins="insert into Department (dno,dnm,dloc) values(?,?,?)";
				psmt=con.prepareStatement(ins);
				psmt.setInt(1,dnum);
				psmt.setString(2,dnm);
				psmt.setString(3,dlc);
				psmt.executeUpdate();
				flag = true;
			}
			catch(SQLException cb)
			{
				new Message(this,"Error occured in insertion");
				flag = false;	
			}
			if(flag)
			{
				new Message(this,"Record inserted successfully");
			}
		}
	}
}

* Dept Deletion File

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
			con=DriverManager.getConnection("jdbc:odbc:users","users","database");
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

* Dept Updation File

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
			con=DriverManager.getConnection("jdbc:odbc:users","users","database");
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
			Int en=Integer.parseInt(dnum.getSelectedItem());
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
		If(a.getSource()==update)
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





		
JDBC - Database Application			20

		Genesis Confidential

