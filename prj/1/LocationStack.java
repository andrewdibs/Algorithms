/* 
 *
 * streamOut() streams out the stack from bottom to top. This method
 * should NOT make a copy of the stack. Instead, it should require two
 * passes over the stack to print the stack. The first pass will
 * traverse the stack to top->bottom, reversing the links of the nodes
 * as it goes. The second pass will traverse from bottom->top,
 * printing each Location as it is visited, and undoing the reversing
 * of the node links.
 */
import java.util.Stack;
class LocationStack {
  private LocationStack(LocationStack s) { assert(false); }
  private LocationStackNode top;
  private int depth = 0;
  LocationStack() {
    // - Constuctor for Location stack
    depth = 0;
    top = null;
  }

  void push(Location loc) {
    // - creates a new LocationStack node and makes it top
    LocationStackNode newLoc = new LocationStackNode(loc, top); 
    newLoc.setNextNode(top);
    top = newLoc;
    depth++;
    
  }
  void pop() {
    // - pops the top and makes next top
    if (depth > 0){ 
      top = top.getNextNode();
      depth--;
    }
  }
  Location getTop() {
    // - peeks top
    return top.getLocation();
  }

  boolean isEmpty() {
    // -  Retruns true if depth of stack is 0
    return depth == 0;
  }
  boolean isOn(Location loc) {
    // - traverses the stack and checks if location isEqual
    LocationStackNode curLoc = top; 
    while(curLoc != null){
      if(loc.isEqual(curLoc.getLocation())) return true; 
      curLoc = curLoc.getNextNode();
    }
    return false;
  }
  LocationStackNode getLastNode(){
    if (top.getNextNode() != null) return this.getLastNode();

    return top;
  }

  void streamOut(LocationStack s) {
    // - reverses the nodes 2x and prints the locations
    Stack <Location> temp = new Stack<Location>();
    
    while(s.isEmpty()){
      temp.push(top.getLocation());
      s.pop();
    }
    while(!temp.empty()){
      Location curLoc = new Location();
      curLoc = temp.peek();
      curLoc.streamOut();
      temp.pop();
    }

  }
    
}


class LocationStackNode {
  private LocationStackNode() { assert(false); }
  private LocationStackNode(LocationStackNode s) { assert(false); }

  private Location location;
  private LocationStackNode nextNode;

  LocationStackNode(Location loc, LocationStackNode next) {
    // -Constructor
    this.location = loc;
    this.nextNode = next;
  }
  Location getLocation() {
    // - returns the location object for current node
    return this.location;
  }
  LocationStackNode getNextNode() {
    // - returns the linked next node for current node
    return this.nextNode;
  }
  void setNextNode(LocationStackNode next) {
    // - sets the linked next node for current node
    this.nextNode = next;
  }
}
