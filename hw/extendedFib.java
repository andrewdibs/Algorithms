
class extendedFib{

  public static int fib(int n){
    if (n <4 ) return 1;
    else return fib(n-1) + fib(n-2) + fib(n-3);
  }
  public static void main(String [] args ){
    System.out.println(fib(-5));
  }
}
