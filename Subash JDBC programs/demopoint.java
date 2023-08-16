class test
{
  int a;

  test(int i)
  {
   a=i;
  }

  test incrbyten()
  {
    test temp=new test(a+10);
    return temp;
    }
  }


  class demopoint
  {
    public static void main(String args[])
    {
    test t1=new test(2);
    test t2;
    t2=t1.incrbyten();
    System.out.println(t1.a);
    System.out.println(t2.a);
    t2=t2.incrbyten();
    System.out.println(t2.a);
    }
 }


