//Driver
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map;
class Driver_prj2{

  public static void main(String [] args){
    
    Scanner input = new Scanner(System.in);
    Maze m = new Maze();
    m.streamIn(input);
    Location start = m.getStartLocation();
    ArrayQueue qu = new ArrayQueue();
    Map<Location,Location> ml = new TreeMap<>();

    // initialize start 
    qu.add(start);
    ml.get(start).equals(start);
    

    // checks if reached end location 
    while (!m.isEndLocation(qu.getFront())){
      while(true){
        Location front = new Location();
        front = qu.getFront();

        Location next = front.nextNeighbor();
        // if next is valid location add to qu
        if (m.isValidLocation(next)){
          qu.add(next);
          //if next is not already a key add shortest path to map
          if(!ml.containsKey(next))
            ml.get(next).equals(front);

        }
        // if next is done checking neighbors break and restart loop
        if (next.isDone()){
          qu.remove();
          break;
        }
      }
      // if qu is empty then no solution 
      if (qu.getLength() == 0) break;
    
    }
    if (m.isEndLocation(qu.getFront())){
      System.out.println("Solution found:");
    }
    else{
      System.out.println("No solution");
    }

  }
}
