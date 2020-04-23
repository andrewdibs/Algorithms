
import java.util.Scanner;
class Driver_prj5{
  public static void main(String [] args){
    Scanner input = new Scanner(System.in);
    
    ArrayHeap heap = new ArrayHeap();
    int numProcesses = input.nextInt();
    int systemClock = input.nextInt();
    int proTime = systemClock;
    int runs = 0;
    int skips = 0;    
    
    for (int i = 0; i < numProcesses; i++){
      // run current processes in real time
      while(proTime <= systemClock){
      
        Process pro = new Process(i);
        //proTime = input.nextInt();
        pro.streamIn(input);
        heap.insert(pro);
      }
      // Run or skip
      if (heap.getNumItems() > 0){
        if(heap.getMinItem().canComplete(systemClock)){
          systemClock = heap.getMinItem().run(systemClock);
          heap.removeMinItem();
          runs++;
        }else{
          System.out.println("skipping process id " + heap.getMinItem().getId()
            +  " at " + systemClock);
          skips++;
        }

      }else{
        systemClock = proTime;
      }
    }
    // finish heap's remaining processes 
    while(heap.getNumItems() > 0){
      if(heap.getMinItem().canComplete(systemClock)){
        systemClock = heap.getMinItem().run(systemClock);
        heap.removeMinItem();
        runs++;
      }else{
        System.out.println("skipping process id " + heap.getMinItem().getId() +
          " at " + systemClock);
        skips++;
      }
    }
    // print results 
    System.out.println("final clock is                 " + systemClock);
    System.out.println("number of processes run        " + runs);
    System.out.println("number of processes skipped is " + skips);
    
  }
}