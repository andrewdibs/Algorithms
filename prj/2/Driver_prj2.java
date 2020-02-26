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

    qu.add(start);
    
    while (!m.isEndLocation(qu.getFront())){
      while(true){
        Location front = new Location();
        front = qu.getFront();

        Location next = front.nextNeighbor();
        
        if (m.isValidLocation(next)){
          qu.add(next);
          ml.get(next).equals(front);

        }

        if (next.isDone()){
          qu.remove();
          break;
        }
      }
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
