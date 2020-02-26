import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

class Maze {
  private Set<Location> validLocations;

  private Location startLocation;
  private Location endLocation;

  Maze() {
    // -  constructs empty maze
    validLocations = new TreeSet<Location>();
    startLocation = new Location();
    endLocation = new Location();
  }

  Location getStartLocation() {
    // -  returns start location
    return startLocation;
  }
  boolean isValidLocation(Location loc) {
    // -  checks if location is a member of the location set
    return validLocations.contains(loc);

  }
  boolean isEndLocation(Location loc) {
    // -  checks if reached end location
    return endLocation.isEqual(loc);
  }

  void streamIn(Scanner input) {
    // -  streams input to maze 
    int count = input.nextInt();
    for (int i=0; i < count+1; i++){
      Location loc = new Location();
      loc.word = input.nextLine();
      validLocations.add(loc);
    }    
    startLocation.word = input.next();
    endLocation.word = input.next();;
  }
}
