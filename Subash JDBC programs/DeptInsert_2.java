import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

public class DeptInsert extends JFrame implements ActionListener
{
    Container cntr;
    JLabel lbl_dno,lbl_dnm,lbl_dloc,lbl_header;
    JTextField tf_dno,tf_dnm,tf_dloc;
    JButton btn_ok,btn_cancel,btn_home,btn_clear;
    public PreparedStatement psmt;

    public DeptInsert()
    {
        super("Insert");
        setSize(400,320);
        cntr = getContentPane();
        cntr.setLayout(null);

        Font f = new Font("Times Roman",Font.BOLD,16);

        lbl_header = new JLabel("DEPARTMENT ADDITION");
        lbl_header.setFont(f);
        lbl_header.setBounds(50,50,250,25);
        cntr.add(lbl_header);

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

        btn_ok = new JButton("SAVE");
        btn_ok.setBounds(30,250,70,25);
        btn_ok.addActionListener(this);
        cntr.add(btn_ok);
        btn_cancel = new JButton("EXIT");
        btn_cancel.setBounds(110,250,60,30);
        cntr.add(btn_cancel);
        btn_cancel.addActionListener(this);
        btn_clear = new JButton("CLEAR");
        btn_clear.setBounds(180,250,80,30);
        cntr.add(btn_clear);
        btn_clear.addActionListener(this);
        btn_home = new JButton("HOME");
        btn_home.setBounds(270,250,80,30);
        cntr.add(btn_home);
        btn_home.addActionListener(this);
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent we)
            {
                dispose();
            }
        });
    }

   public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource().equals(btn_ok))
        {
            addData();
        }
        if(ae.getSource().equals(btn_cancel))
        {
            this.dispose();
            System.exit(0);
        }
        if(ae.getSource().equals(btn_clear))
        {
            tf_dno.setText("");
            tf_dnm.setText("");
            tf_dloc.setText("");
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
            psmt = Login.con.prepareStatement("insert into gen_dept values(?,?,?)");
            psmt.setInt(1,new Integer(tf_dno.getText()).intValue());
            psmt.setString(2,tf_dnm.getText());
            psmt.setString(3,tf_dloc.getText());
            psmt.executeUpdate();
            JOptionPane.showMessageDialog(null,"Record got Inserted","message",JOptionPane.INFORMATION_MESSAGE);
        }
        catch(SQLException se)
        {
            JOptionPane.showMessageDialog(null,"Error occured while inserting","message",JOptionPane.INFORMATION_MESSAGE);
        }

    }
}
