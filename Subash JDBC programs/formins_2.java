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
        PreparedStatement psmt, psmt2;
	ResultSet rs;
	Statement st;
	private String tnm="";
    Hashtable ht;
    Frame fr;
	
    public formins(String tName,Frame f)
	{
      
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
		tfsal.setBounds(140,150,75,20);
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
                        con=DriverManager.getConnection("jdbc:odbc:users","scott","tiger");
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
            		int enum=Integer.valueOf(tfeno.getText()).intValue();
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
