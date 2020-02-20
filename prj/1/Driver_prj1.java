/*
*Author: Andrew DiBella
*MAZE depth first search algorithm
*2/19/20
*
*/

import java.util.Scanner;
class Driver_prj1{
  public static void main(String [] args){
    Scanner input = new Scanner(System.in);
        
    Maze m = new Maze();
    m.streamIn(input);

    LocationStack ls = new LocationStack();

    // push the start location into location stack and start nextDirection
    ls.push(m.getStartLocation());
    ls.getTop().start();
        
    //  begin main loop 
    while (true){
      //  Finds the next neighor 
      Location next = ls.getTop().nextNeighbor();
       
      //  checks if reached end location 
      if (m.isEndLocation(ls.getTop()))
        break;

      //  if next neighbor is a valid location - push to top
      if (m.isValidLocation(next) && !ls.isOn(next)){
        ls.push(next);
      }
      
      //  if location checked all possible directions - pop
      else if (ls.getTop().isDone()){
        while(ls.getTop().isDone()){
          ls.pop();
          // if stack is empty after pop - break - no solution
          if (ls.isEmpty()) break;
        }
        if(ls.isEmpty()) break;
      } 
     
    }
    // No solution found
    if (ls.isEmpty()){
      System.out.println("No solution found");
    }
    //  Solution Found 
    else{
      System.out.println("Solution found:");
      ls.streamOut(ls);
    }
  }
}