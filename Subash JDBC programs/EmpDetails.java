import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

public class EmpDetails extends JFrame implements ActionListener
{
    public JLabel lbl_header,lbl_eno,lbl_ename,lbl_esal,lbl_egender,lbl_edno;
    public JTextField tf_eno,tf_ename,tf_esal;
    public JButton btn_ok,btn_cancel,btn_home;
    public ButtonGroup jrg_gender;
    public JRadioButton jrb_mgender,jrb_fgender;
    public JList jl_edno;
    public Connection con;
    public PreparedStatement psmt;
    public Statement st,st1;
    public ResultSet rs,rs1;
    public Vector departments;
    public Hashtable ht,ht1;
    public String str[];
    public int tempdno,tdno;


    public EmpDetails()
    {
        super("Insert");
        setSize(360,400);
        setLocation(35,35);
        Container c = getContentPane();
        c.setLayout(null);
        Font f1 = new Font("Times New Roman",Font.BOLD,18);
        lbl_header = new JLabel();
        if(EmpUpdate.opType.equals("update"))
        {
            lbl_header.setText("EMPLOYEE MODIFUCATION");
        }
        if(EmpUpdate.opType.equals("delete"))
        {
            lbl_header.setText("EMPLOYEE DELETION");
        }
        lbl_header.setFont(f1);
        lbl_header.setBounds(50,15,300,30);
        c.add(lbl_header);
        
        Font f2 = new Font("Times New Roman",Font.BOLD,12);

        lbl_eno = new JLabel("ENO     :");
        lbl_eno.setFont(f2);
        lbl_eno.setBounds(40,70,100,25);
        c.add(lbl_eno);

        tf_eno = new JTextField();
        tf_eno.setFont(f2);
        tf_eno.setBounds(140,70,100,25);
        c.add(tf_eno);

        lbl_ename = new JLabel("ENAME   :");
        lbl_ename.setFont(f2);
        lbl_ename.setBounds(40,110,100,25);
        c.add(lbl_ename);

        tf_ename = new JTextField();
        tf_ename.setFont(f2);
        tf_ename.setBounds(140,110,150,25);
        c.add(tf_ename);

        lbl_esal = new JLabel("SALARY  :");
        lbl_esal.setFont(f2);
        lbl_esal.setBounds(40,150,100,25);
        c.add(lbl_esal);

        tf_esal = new JTextField();
        tf_esal.setFont(f2);
        tf_esal.setBounds(140,150,100,25);
        c.add(tf_esal);

        jrg_gender = new ButtonGroup();

        lbl_egender = new JLabel("GENDER  :");
        lbl_egender.setFont(f2);
        lbl_egender.setBounds(40,190,100,25);
        c.add(lbl_egender);

        jrb_mgender = new JRadioButton("MALE",true);
        jrb_mgender.setBounds(140,190,60,25);
        jrg_gender.add(jrb_mgender);
        c.add(jrb_mgender);
        jrb_fgender = new JRadioButton("FEMALE",false);
        jrb_fgender.setBounds(200,190,80,25);
        jrg_gender.add(jrb_fgender);
        c.add(jrb_fgender);

        lbl_edno = new JLabel("DNAME   :");
        lbl_edno.setFont(f2);
        lbl_edno.setBounds(40,230,100,25);
        c.add(lbl_edno);
        ht = new Hashtable();
        ht1 = new Hashtable();
        departments = new Vector();
        jl_edno = new JList();
        jl_edno.addListSelectionListener(new ListHandler());
        JScrollPane pane = new JScrollPane();
        pane.setBorder(BorderFactory.createLoweredBevelBorder());
        pane.getViewport().add(jl_edno);
        pane.setBounds(140,230,100,80);
        c.add(pane);
        btn_ok = new JButton();
        if(EmpUpdate.opType.equals("update"))
        {
            btn_ok.setText("Modify");
        }
        if(EmpUpdate.opType.equals("delete"))
        {
            btn_ok.setText("Delete");
        }
        btn_ok.setBounds(50,330,80,30);
        c.add(btn_ok);
        btn_ok.addActionListener(this);
        btn_cancel = new JButton("EXIT");
        btn_cancel.setBounds(140,330,70,30);
        c.add(btn_cancel);
        btn_cancel.addActionListener(this);
        btn_home = new JButton("HOME");
        btn_home.setBounds(230,330,70,30);
        c.add(btn_home);
        btn_home.addActionListener(this);
        setResizable(false);
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent we)
            {
                dispose();
            }
        });
        fillList();
        fillDetails();
    }

    public void addData()
    {
        try
        {
            if(EmpUpdate.opType.equals("update"))
            {
		getDeptno();
        	psmt = Login.con.prepareStatement("update gen_emp set ename = ?,esal = ?,sex = ?, dno = ? where eno = ?");
                psmt.setString(1,tf_ename.getText());
                psmt.setFloat(2,new Float(tf_esal.getText()).floatValue());
                if(jrb_mgender.isSelected())
                  psmt.setString(3,"M");
                else
                  psmt.setString(3,"F");
		psmt.setInt(4,tempdno);
                psmt.setInt(5,new Integer(tf_eno.getText()).intValue());
                psmt.execute();
                JOptionPane.showMessageDialog(null,"Record got modified","message",JOptionPane.INFORMATION_MESSAGE);
           }
           if(EmpUpdate.opType.equals("delete"))
           {
        	psmt = Login.con.prepareStatement("delete gen_emp where eno = ?");
                psmt.setInt(1,new Integer(tf_eno.getText()).intValue());
		psmt.execute();
                JOptionPane.showMessageDialog(null,"Record got deleted","message",JOptionPane.INFORMATION_MESSAGE);
	   }	
        }
        catch(SQLException se)
        {
            System.out.println("Error occured in database operation " + se.getMessage());
            JOptionPane.showMessageDialog(null,"Error occured in database","message",JOptionPane.INFORMATION_MESSAGE);

        }
    }

    public void fillList()
    {
        try
        {
            st = Login.con.createStatement();
            rs = st.executeQuery("select * from gen_dept");
            while(rs.next())
            {
                int i = rs.getInt(1);
                String temp = rs.getString(2);
               
                ht.put(temp,new Integer(i));
                ht1.put(new Integer(i),temp);
                departments.addElement(temp);
            }
            str = new String[departments.size()];
            for(int i = 0;i < departments.size();i++)
            {
                str[i] = (String)departments.elementAt(i);
            }
            jl_edno.setListData(str);
        }
        catch(SQLException se)
        {
            System.out.println("Error occured in database operation " + se.getMessage());
        }

    }
    public void fillDetails()
    {
        try
        {
            st = Login.con.createStatement();
            rs = st.executeQuery("select * from gen_emp where eno = " + EmpUpdate.empNo);
            while(rs.next())
            {
                int i = rs.getInt(1);
                tf_eno.setText(new Integer(i).toString());
                tf_eno.setEnabled(false);
                String temp = rs.getString(2);
                tf_ename.setText(temp);
                float tflaot = rs.getFloat(3);
                tf_esal.setText(new Float(tflaot).toString());
                String tsex = rs.getString(4);
                if(tsex.compareTo("M") == 0)
                {
                    jrb_mgender.setSelected(true);
                }
                else
                {
                    jrb_fgender.setSelected(true);
                }
                tdno = rs.getInt(5);
            }
            String strtemp =(String)ht1.get(new Integer(tdno));
            for(int j = 0; j < departments.size();j++)
            {
                String deptname = (String)departments.elementAt(j);
                if(deptname.compareTo(strtemp) == 0)
                {
                    jl_edno.setSelectedIndex(j);
                    break;
                }
            }
        }
        catch(SQLException se)
        {
            System.out.println("Error occured in database operation " + se.getMessage());
        }
                
    }
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource().equals(btn_ok))
        {
		addData();
		dispose();
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
    public class ListHandler implements ListSelectionListener
    {
        public void valueChanged(ListSelectionEvent lse)
        {
		getDeptno();
        }
    }
    private void getDeptno()
    {
            String tempDept = jl_edno.getSelectedValue().toString();
            Integer integer = (Integer)ht.get(tempDept);
            tempdno = integer.intValue();
    }
}
