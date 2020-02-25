
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
    
    LocationStackNode head = s.top;
    LocationStackNode prev = null;
    LocationStackNode next; 
    
    while (head != null){
      next = head.getNextNode();
      head.setNextNode(prev);
      prev = head;
      head = next;
    }
    head = s.top;
    
    while (head != null){
      head.getLocation().streamOut();
      LocationStackNode loc = head.getNextNode();
      head.setNextNode(prev);
      prev = head;
      head = loc;
    }

    // Stack <Location> locals = new Stack<Location>();
    // while(!s.isEmpty()){
    //   locals.push(top.getLocation());
    //   s.pop();
    // }
    // while(!locals.empty()){
    //   Location loc = new Location();
    //   loc = locals.peek();
    //   loc.streamOut();
    //   locals.pop();
    // }
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
