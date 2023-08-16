import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AccessApp extends Frame {
 TextField driver = new TextField("sun.jdbc.odbc.JdbcOdbcDriver",60);

 TextField url = new TextField("jdbc:odbc:accessdsn",60);
 TextField sql = new TextField(60);
 Button doIt = new Button("Do it!");
 TextArea resultArea = new TextArea(10,60);

 public static void main(String args[]){
  AccessApp app = new AccessApp();
 }

 public AccessApp() {
  super("AccessApp");
  setup();
  addWindowListener(new WindowEventHandler());
 }

 void setup() {
  setupMenuBar();

  setLayout(new GridLayout(2,1));

  Panel topPanel = new Panel();
  topPanel.setLayout(new GridLayout(4,1));
  Panel panels[]=new Panel[4];
  for(int i=0;i<panels.length;++i){
   panels[i]=new Panel();
   panels[i].setLayout(new FlowLayout(FlowLayout.LEFT));
  }
  panels[0].add(new Label("Driver:"));
  panels[0].add(driver);
  panels[1].add(new Label("URL: "));
  panels[1].add(url);
  panels[2].add(new Label("SQL: "));
  panels[2].add(sql);
  doIt.addActionListener(new ButtonHandler());
  panels[3].add(doIt);
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

 void accessDB() {
  try{
   Class.forName(driver.getText());
   Connection connection=DriverManager.getConnection(url.getText(),);
   Statement statement = connection.createStatement();

   boolean hasResults = statement.execute(sql.getText());

   if(hasResults){
    ResultSet result = statement.getResultSet();
    if(result!=null) displayResults(result);
   }else resultArea.setText("");
   connection.close();
  }catch(Exception ex){
   resultArea.setText(ex.toString());
  }
 }

 void displayResults(ResultSet r) throws SQLException {
  ResultSetMetaData rmeta = r.getMetaData();
  int numColumns=rmeta.getColumnCount();
  String text="";
  for(int i=1;i<=numColumns;++i) {
    text+=rmeta.getColumnName(i);
    text+=" |  "
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


 class ButtonHandler implements ActionListener {

  public void actionPerformed(ActionEvent ev){
   String s=ev.getActionCommand();
   if(s=="Do it!") accessDB();
  }
 }
 class MenuItemHandler implements ActionListener {
  public void actionPerformed(ActionEvent ev){
   String s=ev.getActionCommand();
   if(s=="Exit"){
    System.exit(0);
   }
  }
 }
 class WindowEventHandler extends WindowAdapter {
  public void windowClosing(WindowEvent e){
   System.exit(0);
  }
 }
}
