/* Driver
*
*  Author: Andrew DiBella
*  PRJ2 
*  -Word Melt solver finding the shortest path from start word
*   to end word by implementing a Circular Array using an ArrayQueue 
*  -We search for the shortest path to each word by: 
*    1) replacing a letter
*    2) inserting a letter 
*    3) deleting a letter 
*
*/
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Map;
import java.util.Stack;
class Driver_prj2{

  public static void main(String [] args){
    
    Scanner input = new Scanner(System.in);
    Maze m = new Maze();
    m.streamIn(input);
    Location start = m.getStartLocation();
    ArrayQueue qu = new ArrayQueue();
    Map<Location,Location> ml = new TreeMap<>();
    Location endLocation = new Location();

    // checks start location = end 
    if (m.isEndLocation(start)){
      System.out.println("Solution found:");
      start.streamOut();
      System.exit(0);
    }
    // initialize start 
    qu.add(start);
    ml.put(start,start);
    
    // checks if reached end location 
    while (qu.getLength() > 0 && !m.isEndLocation(qu.getFront())){
      
      Location front = new Location();
      front = qu.getFront();
      front.start();
      qu.remove();
      while(!front.isDone()){
        Location next = front.nextNeighbor();
        // if next is valid location add to qu
        if(m.isValidLocation(next) && !ml.containsKey(next) && !front.isEqual(next)){
            qu.add(next);
            ml.put(next,front);
          
          if(m.isEndLocation(next)){
            endLocation = next;
            break;
          } 
        }
      }
    }
    if (qu.getLength() == 0){
      System.out.println("No solution found");
    }
    // else prints out the shortest path from start to finish
    else{
      System.out.println("Solution found:");
      
      Location head = ml.get(endLocation);
      Stack<Location> s = new Stack<Location>();
      // reverse queue with a stack 
      while (head != start && head != null ){
        s.push(head);
        if(ml.get(head)!= null)
          head = ml.get(head);
      }
      // print 
      start.streamOut();
      while(!s.isEmpty()){
        s.pop().streamOut();
      }
      endLocation.streamOut();
    }
  }
}
