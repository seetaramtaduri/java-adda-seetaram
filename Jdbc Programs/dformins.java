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
		mainFr=frm;
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
			con=DriverManager.getConnection("jdbc:odbc:users","scott","tiger");
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
