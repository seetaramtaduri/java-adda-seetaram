import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class CommitApp extends Frame {
 TextField sql = new TextField(60);
 Button commit = new Button("Commit");
 Button execute = new Button("Execute");
 TextArea resultArea = new TextArea(10,60);
 Connection connection;
 public static void main(String args[]){
  CommitApp app = new CommitApp();
 }
 public CommitApp() {
  super("CommitApp");
  setup();
  setupDB();
  pack();
  addWindowListener(new WindowEventHandler());
  show();
 }
 void setup() {
  setupMenuBar();
  setLayout(new GridLayout(2,1));
  Panel topPanel = new Panel();
  topPanel.setLayout(new GridLayout(2,1));
  Panel panels[]=new Panel[2];
  for(int i=0;i<panels.length;++i){
   panels[i]=new Panel();
   panels[i].setLayout(new FlowLayout(FlowLayout.LEFT));
  }
  panels[0].add(new Label("SQL: "));
  panels[0].add(sql);
  commit.addActionListener(new ButtonHandler());
  execute.addActionListener(new ButtonHandler());
  panels[1].add(commit);
  panels[1].add(execute);
  for(int i=0;i<panels.length;++i)
   topPanel.add(panels[i]);
  add(topPanel);
  add(resultArea);
 }
 void setupMenuBar() {
  MenuBar menuBar = new MenuBar();
  Menu fileMenu = new Menu("File");
  MenuItem fileExit = new MenuItem("Exit");
  fileExit.addActionListener(new MenuItemHandler()); 
  fileMenu.add(fileExit);
  menuBar.add(fileMenu);
  setMenuBar(menuBar);
 }
 void setupDB() {
  try{
   Class.forName("ids.sql.IDSDriver");
   String url = "jdbc:ids://cx122974-a.cv1.sdca.home.com:80/";
   url+="conn?dbtype=odbc&dsn='Actions'";
   connection=DriverManager.getConnection(url);
   DatabaseMetaData meta=connection.getMetaData();
   if(meta.supportsTransactions())
    connection.setAutoCommit(false);
   else{
    String err="Your database server/driver does not support transactions.";
    System.out.println(err);
    System.exit(0);
   }
  }catch(Exception ex){
   resultArea.setText(ex.toString());
  }
 }
 void commitTransactions() {
  try{
   connection.commit();
  }catch(Exception ex){
   resultArea.setText(ex.toString());
  }
 }
 void executeTransaction() {
  try{
   Statement statement = connection.createStatement();
   boolean hasResults = statement.execute(sql.getText());
   if(hasResults){
    ResultSet result = statement.getResultSet();
    if(result!=null) displayResults(result);
   }else resultArea.setText("");
  }catch(Exception ex){
   resultArea.setText(ex.toString());
  }
 }
 void displayResults(ResultSet r) throws SQLException {
  ResultSetMetaData rmeta = r.getMetaData();
  int numColumns=rmeta.getColumnCount();
  String text="";
  for(int i=1;i<=numColumns;++i) {
   if(i<numColumns)
    text+=rmeta.getColumnName(i)+" | ";
   else
    text+=rmeta.getColumnName(i);
  }
  text+="\n";
  while(r.next()){
   for(int i=1;i<=numColumns;++i) {
    if(i<numColumns)
     text+=r.getString(i)+" | ";
    else
     text+=r.getString(i).trim();
   }
   text+="\n";
  }
  resultArea.setText(text);
 }
 void closeConnection(){
  try {
   connection.close();
  }catch(Exception ex){
  }
 }
 class ButtonHandler implements ActionListener {
  public void actionPerformed(ActionEvent ev){
   String s=ev.getActionCommand();
   if(s=="Commit") commitTransactions();
   else if(s=="Execute") executeTransaction();
  }
 }
 class MenuItemHandler implements ActionListener {
  public void actionPerformed(ActionEvent ev){
   String s=ev.getActionCommand();
   if(s=="Exit"){
    closeConnection();
    System.exit(0);
   }
  }
 }
 class WindowEventHandler extends WindowAdapter {
  public void windowClosing(WindowEvent e){
   closeConnection();
   System.exit(0);
  }
 }
}
