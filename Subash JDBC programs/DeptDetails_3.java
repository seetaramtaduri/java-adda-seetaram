import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

public class DeptDetails extends JFrame implements ActionListener
{
    Container cntr;
    JLabel lbl_dno,lbl_dnm,lbl_dloc,lbl_header;
    JTextField tf_dno,tf_dnm,tf_dloc;
    JButton btn_ok,btn_cancel,btn_home;
    public PreparedStatement psmt;
    public Statement st;
    public ResultSet rs;


    public DeptDetails()
    {
        super("Insert");
        setSize(300,320);
        cntr = getContentPane();
        cntr.setLayout(null);

        Font f = new Font("Times Roman",Font.BOLD,16);

        lbl_header = new JLabel();
        lbl_header.setFont(f);
        lbl_header.setBounds(50,50,250,25);
        cntr.add(lbl_header);

        if(DeptUpdate.opType.equals("update"))
        {
            lbl_header.setText("DEPT MODIFICATION");
        }
        if(DeptUpdate.opType.equals("delete"))
        {
            lbl_header.setText("DEPT DELETION");
        }
        lbl_dno = new JLabel("DNO      :");
        lbl_dno.setBounds(50,100,60,25);
        cntr.add(lbl_dno);

        lbl_dnm = new JLabel("DNAME :");
        lbl_dnm.setBounds(50,150,60,25);
        cntr.add(lbl_dnm);

        lbl_dloc = new JLabel("DLOC    :");
        lbl_dloc.setBounds(50,200,60,25);
        cntr.add(lbl_dloc);
        
        tf_dno = new JTextField();
        tf_dno.setBounds(115,100,100,25);
        cntr.add(tf_dno);

        tf_dnm = new JTextField();
        tf_dnm.setBounds(115,150,100,25);
        cntr.add(tf_dnm);

        tf_dloc = new JTextField();
        tf_dloc.setBounds(115,200,100,25);
        cntr.add(tf_dloc);

        btn_ok = new JButton();
        if(DeptUpdate.opType.equals("update"))
        {
            btn_ok.setText("Modify");
        }
        if(DeptUpdate.opType.equals("delete"))
        {
            btn_ok.setText("Delete");
        }
        btn_ok.setBounds(30,250,70,25);
        btn_ok.addActionListener(this);
        cntr.add(btn_ok);
        btn_cancel = new JButton("EXIT");
        btn_cancel.setBounds(110,250,60,30);
        cntr.add(btn_cancel);
        btn_cancel.addActionListener(this);
        btn_home = new JButton("HOME");
        btn_home.setBounds(180,250,80,30);
        cntr.add(btn_home);
        btn_home.addActionListener(this);
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent we)
            {
                dispose();
            }
        });
	fillDetails();
    }

   public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource().equals(btn_ok))
        {
            addData();
            this.dispose();
            JdbcApp ja = new JdbcApp();
            ja.show();
        }
        if(ae.getSource().equals(btn_cancel))
        {
            this.dispose();
            System.exit(0);
        }

        if(ae.getSource().equals(btn_home))
        {
            this.dispose();
            JdbcApp ja = new JdbcApp();
            ja.show();
        }
    }
    
    public void addData()
    {
         try
        {
            if(DeptUpdate.opType.equals("update"))
            {
        	psmt = Login.con.prepareStatement("update gen_dept set dname = ?,dloc = ? where dno = ?");
                psmt.setString(1,tf_dnm.getText());
		psmt.setString(2,tf_dloc.getText());
                psmt.setInt(3,new Integer(tf_dno.getText()).intValue());
                psmt.executeUpdate();
                JOptionPane.showMessageDialog(null,"Record got modified","message",JOptionPane.INFORMATION_MESSAGE);
           }
           if(DeptUpdate.opType.equals("delete"))
           {
        	psmt = Login.con.prepareStatement("delete gen_dept where dno = ?");
                psmt.setInt(1,new Integer(tf_dno.getText()).intValue());
		psmt.executeUpdate();
                JOptionPane.showMessageDialog(null,"Record got deleted","message",JOptionPane.INFORMATION_MESSAGE);
	   }	
        }
        catch(SQLException se)
        {
            System.out.println("Error occured in database operation " + se.getMessage());
            JOptionPane.showMessageDialog(null,"Error occured in database","message",JOptionPane.INFORMATION_MESSAGE);

        }
    }

    public void fillDetails()
    {
        try
        {
            st = Login.con.createStatement();
            rs = st.executeQuery("select * from gen_dept where dno = " + DeptUpdate.deptNo);
            while(rs.next())
            {
                int i = rs.getInt(1);
                tf_dno.setText(new Integer(i).toString());
                tf_dno.setEnabled(false);
                String temp = rs.getString(2);
                tf_dnm.setText(temp);
                temp = rs.getString(3);
                tf_dloc.setText(temp);
            }
        }
        catch(SQLException se)
        {
            System.out.println("Error occured in database operation " + se.getMessage());
        }
                
   }
}
