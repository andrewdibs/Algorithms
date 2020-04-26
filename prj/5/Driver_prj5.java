import java.util.Scanner;
class Driver_prj5{
  public static void main(String [] args){
    Scanner input = new Scanner(System.in);
    
    ArrayHeap heap = new ArrayHeap();
    int numProcesses = input.nextInt();
    int systemClock = input.nextInt();
    int nextTime = 0;
    int runs = 0;
    int skips = 0;
    
    
    for (int i = 0; i < numProcesses;i++){
      // create process 
      Process pro = new Process(i);
      pro.streamIn(input);
      // set next time
      if(input.hasNextInt()) nextTime = input.nextInt();
      System.out.println("nexttime " + nextTime);
      // insert and run if ready 
      if(systemClock < nextTime || !input.hasNext()){
        heap.insert(pro);
        // run or skip
        while(heap.getNumItems() > 0){
          Process min = heap.getMinItem();
          if (min.canComplete(systemClock)){
            System.out.println("running process id " + min.getId() + " at " +
              systemClock);
            systemClock = min.run(systemClock);
            heap.removeMinItem();
            runs++;
          }else {
            System.out.println("skipping process id " + min.getId() + " at " +
              systemClock);
            systemClock++;
            heap.removeMinItem();
            skips++;
          }
        }
        if(systemClock < nextTime) systemClock = nextTime;
      // insert if waiting for other process to finish
      }else {
        System.out.println("insert without run");
        heap.insert(pro);
      } 
      System.out.println("clock " + systemClock);

    }
    // print results
    System.out.println("final clock is                 " + systemClock);
    System.out.println("number of processes run is     " + runs);
    System.out.println("number of processes skipped is " + skips);
  }
}