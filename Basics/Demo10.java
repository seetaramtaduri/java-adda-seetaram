// Program to Demo Switch-case
import java.lang.*;
class Demo10
{
public static void main(String args[])
 {
int a=20,b=10;
char op='+';
switch(op)
   {
		case '+':System.out.println("Addition = "+(a+b));
					  break;
		case '-':System.out.println("Subtraction = "+(a-b));
					  break;
		case '*':System.out.println("Multiplication = "+(a*b));
					  break;
		case '/':System.out.println("Division = "+(a/b));
					  break;
		default:System.out.println("Wrong Choice");
					  break;
    }
  }
}
