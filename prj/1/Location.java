
import java.util.Scanner;
class Location {
  final int RIGHT = 0;
  final int DOWN  = 1;
  final int LEFT  = 2;
  final int UP    = 3;
  final int DONE  = 4;

  private int row;
  private int col;
  int nextDirection;   // mutable

  Location() {
    // -This constructor initilizes the Row and Column for a location
    row = 0;
    col = 0;
    nextDirection = 0;
  }

  void start() {  // const
    // - Starts the next Direction phase for the new loc
    nextDirection = RIGHT;
  }

  Location nextNeighbor() {  // const
    // -Returns a location object for next location 
    Location temp = new Location();
    temp.row = this.row;
    temp.col = this.col;
    if (nextDirection == RIGHT)
      temp.col++;
    else if (nextDirection == DOWN) 
      temp.row++;
    else if (nextDirection == LEFT)
      temp.col--;
    else if (nextDirection == UP)
      temp.row--;
    
    nextDirection++;
    return temp; 
  }
  boolean isDone() {  // const
    // - checks if location has checked each of the possible directions
    return nextDirection == DONE;
  }

  boolean isEqual(Location loc) {  // const
    // -Checks if this loation is equal to the passed location
    return (row == loc.row && col == loc.col);
  }

  void streamOut() {
    // -Prints the row and column for this location
    System.out.print(row + " " + col);
  }

  void streamIn(Scanner input) {
    // -Reads from plain text input to initialize loc
    row = input.nextInt();
    col = input.nextInt();
  }
}
