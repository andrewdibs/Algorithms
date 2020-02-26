import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/* Changes from Project 1:
 *
 * Data member validLocations was changed from a Location array to a
 * Set<Location>, but it still represents the group of locations that may be
 * visited in the maze.
 *
 * Data member validLocationCount was eliminated (because validLocations is no
 * longer an array).
 *
 * We let the compiler deal with the assignment operator, copy constructor, and 
 * for this version of the Maze, you should use the default constructor for the
 * _usual_ and to initally allocate memory for the Set of validLocations using
 * the TreeSet implementation of a Set.
 */

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
