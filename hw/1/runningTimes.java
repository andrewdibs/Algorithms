class runningTimes{
    public static void main(String[] args){
      int n = 100000;

      long start = System.nanoTime();
                                    // COST   TIMES
      int sum = 0;                  // c1     1
      for (int i = 1;               // c2     1
                     i < n;         // c3     n+1
                            i++) {  // c4     n
        for (int j = 1;             // c5     n
                    j<i*i;          // c6     (not required)
                        j++){       // c7  
          if(j%i == 0)   
          for (int k = 0;           // c8
                        k<j;        // c9
                            k++ ){  // c10
            sum++;                  // c11           

          }
        }
      }
      System.out.println(System.nanoTime()- start);
    }
}