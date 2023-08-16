// Program to Print Big No
import java.lang.*;
import java.io.*;
class Demo7
{
public static void main(String args[])
throws IOException
{
int a,b;
BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
System.out.print("Enter 2 Values : ");
a=Integer.parseInt(br.readLine());
b=Integer.parseInt(br.readLine());
if(a>b)
System.out.println("Big Value is "+a);
else
System.out.println("Big Value is "+b);
}
}
