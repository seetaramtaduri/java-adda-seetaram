import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import java.awt.*;
import java.sql.*;
import java.util.*;
import java.awt.event.*;

public class Login extends JFrame
{
    public static int HEIGHT = 190;
    public static int WIDTH = 300;
    public static String TITLE = "LOGIN";
    public JLabel lbl_UserID,lbl_Pwd;
    public JTextField tf_UserID;
    public JPasswordField tf_Pwd;
    public JButton btn_Ok,btn_Cancel;
    public JPanel p_Login;
    public static Connection con;
    public Statement st;
    public ResultSet rs;
    String unm,pwd;
    Vector v;

    public Login()
    {
        super(TITLE);
        p_Login = new JPanel();
        p_Login.setLayout(null);
        p_Login.setBounds(10,10,WIDTH-25,HEIGHT-40);
        p_Login.setBorder(new TitledBorder("LOGIN"));
        buildGUI();
        Container c = getContentPane();
        c.setLayout(null);
        c.add(p_Login);
        setSize(WIDTH,HEIGHT);
        setLocation(100,100);
        setListeners();
        v = new Vector();
        databaseConnection();
    }

    public void buildGUI()
    {
        lbl_UserID = new JLabel("USER NAME");
        lbl_UserID.setFont(new Font("Times New Roman",Font.BOLD,12));
        lbl_UserID.setBounds(20,35,100,25);
        p_Login.add(lbl_UserID);
        lbl_Pwd = new JLabel("PASSWORD");
        lbl_Pwd.setFont(new Font("Times New Roman",Font.BOLD,12));
        lbl_Pwd.setBounds(20,70,100,25);
        p_Login.add(lbl_Pwd);
        tf_UserID = new JTextField();
        tf_UserID.setFont(new Font("Times New Roman",Font.BOLD,12));
        tf_UserID.setBounds(130,35,120,25);
        p_Login.add(tf_UserID);
        tf_Pwd = new JPasswordField();
        tf_Pwd.setEchoChar('*');
        tf_Pwd.setFont(new Font("Times New Roman",Font.BOLD,12));
        tf_Pwd.setBounds(130,70,120,25);
        p_Login.add(tf_Pwd);
        btn_Ok = new JButton("OK");
        btn_Ok.setBounds(60,105,60,25);
        p_Login.add(btn_Ok);
        btn_Cancel = new JButton("CANCEL");
        btn_Cancel.setBounds(130,105,80,25);
        p_Login.add(btn_Cancel);
    }

    public void databaseConnection()
    {
        try
        {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            con = DriverManager.getConnection("jdbc:odbc:raghu","scott","tiger");
        }
        catch(ClassNotFoundException e)
        {
            System.out.println("Class could not find");
        }
        catch(SQLException se)
        {
            System.out.println("Error occured while connectiong");
        }
        try
        {
            st=con.createStatement();
            rs=st.executeQuery("select * from login");
            while(rs.next())
            {
                
                String s1=rs.getString(1);
                String s2=rs.getString(2);
                v.addElement(new myVector(s1,s2));
            }

        }
        catch(SQLException s)
        {
            System.out.println(s.getMessage());
        }
    }
    public static void main(String args[])
    {
        Login myLogin = new Login();
        myLogin.show();
    }
    public void setListeners()
    {
        btn_Ok.addActionListener(new ButtonListener());
        btn_Cancel.addActionListener(new ButtonListener());
        addWindowListener(new WindowHandler());
    }

    public void checkUserName()
    {
        int count = 0;
        int count1 = 0;
        
        unm=tf_UserID.getText();
        char[] passValue = tf_Pwd.getPassword(); 
        pwd = new String(passValue);
        for(int i = 0;i < v.size(); i++)
        {
                myVector mv = (myVector)(v.elementAt(i));
                if( unm.compareTo(mv.uname)==0 && pwd.compareTo(mv.pwd)==0 )
                {
                    JOptionPane.showMessageDialog(null,"Successfully Connected","message",JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                    JdbcApp mainForm = new JdbcApp();
                    mainForm.show();
                    break;
                }
                else
                {
                    if(i == (v.size()-1))
                    {
                        JOptionPane.showMessageDialog(null,"Successfully Not Connected","message",JOptionPane.INFORMATION_MESSAGE);
                    }
                }
        }
    }

    public class ButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            if(ae.getSource().equals(btn_Ok))
            {
            	checkUserName();
            }
            if(ae.getSource().equals(btn_Cancel))
            {
                System.exit(0);
            }
        }
    }

    public class WindowHandler extends WindowAdapter
    {
        public void windowClosing(WindowEvent we)
        {
            System.exit(0);
        }
    }
}

class myVector
{
    public String uname;
    public String pwd;
    public myVector()
    {
        this.uname = "";
        this.pwd = "";
    }
    public myVector(String uname,String pwd)
    {
        this.uname = uname;
        this.pwd = pwd;
    }
}
