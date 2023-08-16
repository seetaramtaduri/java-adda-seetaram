import java.lang.*;
import java.io.*;
class Demo6
{
public static void main(String args[])
throws IOException
{
int eno;
String name;
char gen;
float sal;
BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
System.out.print("Enter Employee No : ");
eno=Integer.parseInt(br.readLine());
System.out.print("Enter Employee Name : ");
name=br.readLine();
System.out.print("Enter Employee gen: ");
gen=(char)br.read();
br.skip(2);
//gen=(char)br.readLine().charAt(0);
System.out.print("Enter Employee Salary : ");
sal=Float.parseFloat(br.readLine());
System.out.println("\n Employee Details");
System.out.println("Employee No : "+eno);
System.out.println("Employee Name : "+name);
System.out.println("Employee Gen: "+gen);

System.out.println("Employee Salary : "+sal);
}
}
