import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

public class DeptUpdate extends JFrame implements ActionListener
{
    public JLabel mainLabel,jlbl_dno;
    public JComboBox jcb_dno;
    public JButton jbtn_ok,jbtn_exit,jbtn_home;
    public Statement st;
    public ResultSet rs;
    public Vector dnoVector;
    public static String opType;
    public static int deptNo;

    public DeptUpdate(String str)
    {
        super(str);
        setSize(255,170);
        setLocation(35,35);
        Container c = getContentPane();
        c.setLayout(null);
        if(JdbcApp.empString.compareTo("update") == 0)
        {
            mainLabel = new JLabel("DEPT UPDATION");
        }
        else
        {
            mainLabel = new JLabel("DEPT DELETION");
        }
        mainLabel.setBounds(30,30,200,25);
        c.add(mainLabel);
        jlbl_dno = new JLabel("DNO :");
        jlbl_dno.setBounds(30,70,60,25);
        c.add(jlbl_dno);
        dnoVector = new Vector();
        getList();
        jcb_dno = new JComboBox(dnoVector);
        jcb_dno.setBounds(100,70,100,25);
        c.add(jcb_dno);
        jbtn_ok =  new JButton();
        jbtn_ok.setText(str);
        jbtn_ok.setBounds(10,110,80,25);
        c.add(jbtn_ok);
        jbtn_ok.addActionListener(this);
        jbtn_exit = new JButton("EXIT");
        jbtn_exit.setBounds(100,110,70,25);
        c.add(jbtn_exit);
        jbtn_exit.addActionListener(this);
        jbtn_home = new JButton("HOME");
        jbtn_home.setBounds(180,110,70,25);
        c.add(jbtn_home);
        jbtn_home.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource().equals(jbtn_home))
        {
            dispose();
            JdbcApp ja = new JdbcApp();
            ja.show();
        }
        if(ae.getSource().equals(jbtn_exit))
        {
            System.exit(0);
        }
        if(ae.getSource().equals(jbtn_ok))
        {
            if(jbtn_ok.getText().equals("update"))
            {
                opType = "update";
            }
            else
            {
                opType = "delete";
            }
            Integer temp = (Integer)jcb_dno.getSelectedItem();
            deptNo = temp.intValue();
            dispose();
            DeptDetails ed = new DeptDetails();
            ed.show();
        }
    }

    public void getList()
    {
        try
        {
            Statement st = Login.con.createStatement();
            ResultSet rs = st.executeQuery("select dno from gen_dept");
            while(rs.next())
            {
                int i = rs.getInt(1);
                dnoVector.addElement(new Integer(i));
            }
        }
        catch(SQLException se)
        {
            System.out.println("Error in database Query");
        }
    }

}
