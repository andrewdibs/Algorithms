
import java.util.Scanner;
class Maze {
  private Maze(Maze m) { assert(false); }

  private int validLocationCount;
  private Location[] validLocations;

  private Location startLocation;
  private Location endLocation;

  Maze() {
    // -
    startLocation = null;
    endLocation = null;
    validLocationCount = 0;
    
  }

  Location getStartLocation() {
    // - returns location start
    return startLocation;
  }
  boolean isValidLocation(Location loc) {
    // - checks each location in valid location array
    for(int i =0; i <validLocationCount;i++){
      Location valLoc = validLocations[i];
      if(valLoc.isEqual(loc))return true;
    }
    return false;
    
  }
  boolean isEndLocation(Location loc) {
    // - checks if it has reached end location 
    return loc.isEqual(endLocation);
  }

  void streamIn(Scanner input) {
    // - creates the maze object from input
    this.validLocationCount = input.nextInt();
    this.validLocations = new Location[validLocationCount];
    for(int i = 0;i<validLocationCount;i++){
      Location temp = new Location();
      temp.streamIn(input);
      validLocations[i] = temp;
    }
    this.startLocation= new Location();
    startLocation.streamIn(input);
    this.endLocation = new Location();
    endLocation.streamIn(input);

  }
}
