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
      
      System.out.print("Next: ");
      next.streamOut();
      System.out.print(" Top: ");
      ls.getTop().streamOut();
      System.out.println();
      
      
        //  if location checked all possible directions - pop
      if (ls.getTop().nextDirection == 4){
        ls.pop();
        System.out.println("pop");
        // if stack is empty after pop - break - no solution
        if (ls.isEmpty()) break;
      }
      //  if next neighbor is a valid location - push to top
      else if(m.isValidLocation(next) && !ls.isOn(next)){
        ls.push(next);
        System.out.println("push");
      }
      //  checks if reached end location 
      if (m.isEndLocation(ls.getTop())){
        break;
      }
      // else{
      //   ls.getTop().nextDirection++;
      // }
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