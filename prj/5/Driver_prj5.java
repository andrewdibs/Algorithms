import java.util.Scanner;
/**
   * file: Driver_prj5.java
   * author: Andrew DiBella
   * course: CMPT 435
   * assignment: project 5
   * due date: April 27, 2020
   * version: 3.0
   * 
   * This file contains the logic to create 
   * a real time batch operating system that 
   * utilizes an array heap data structure containing
   * process objects
   */

public class Driver_prj5 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    ArrayHeap heap = new ArrayHeap();
    int numProcesses  = input.nextInt();
    int id = 0;
    int runs = 0;
    int skips = 0;
    int systemClock = 0;

    if(numProcesses == 0){
      // print results
      System.out.println("final clock is                 " + systemClock);
      System.out.println("number of processes run is     " + runs);
      System.out.println("number of processes skipped is " + skips);
      System.exit(0);
    }

    int nextTime = input.nextInt();

    // main program loop
    while (id < numProcesses || heap.getNumItems() > 0 ) {
      if (heap.getNumItems() == 0) systemClock = nextTime;
      // run or skip 
      else if (heap.getMinItem().canComplete(systemClock)) {
        System.out.println("running process id " + heap.getMinItem().getId() + 
            " at " + systemClock);
        systemClock = heap.getMinItem().run(systemClock);
        heap.removeMinItem();
        runs++;
      }else{
        System.out.println("skipping process id " + heap.getMinItem().getId() +
            " at " + systemClock);
        heap.removeMinItem();
        systemClock++;
        skips++;
      }
      // insert processes if ready       
      while (nextTime <= systemClock) {
        Process pro = new Process(id++);
        pro.streamIn(input);
        heap.insert(pro);

        if (input.hasNextInt()) nextTime = input.nextInt();
        else nextTime = Integer.MAX_VALUE;
      }
    }
    // print results
    System.out.println("final clock is                 " + systemClock);
    System.out.println("number of processes run is     " + runs);
    System.out.println("number of processes skipped is " + skips);
  }
}