/* An ArrayQueue is a queue based on an array. The array is a circular array.
 * The queue should grow dynamically if it gets full (it should double in
 * capacity each time it gets full).
 *
 * The default constructor creates an ArrayQueue that is empty but has some
 * capacity > 0. The copy constructor creates a deep copy of the given
 * ArrayQueue object. This means that it gets its own deep copy of the data.
 *
 * The add method adds an element to the back of the queue. The remove method
 * removes one item from the front of the queue. These methods should not move
 * any data already in the queue. The getFront method returns the item at the
 * front of the queue.
 *
 * The getLength function returns the length of the queue. If the length is 0,
 * the queue is considered to be empty.
 *
 * The copyFrom method first checks to see if the queue we are assigning to is
 * the same as this, and if not, makes a deep copy of the given queue.
 *
 * The doubleCapacity method doubles the capacity of the ArrayQueue, and updates
 * the data members so they are now valid for the newly allocated array.
 *
 * Note that even if some methods are not used in your project, you still need
 * to implement them all correctly!
 */

class ArrayQueue {
  private Location[] data;
  private int length, capacity, front;
  
  private void doubleCapacity() {
    // -
  }

  ArrayQueue() {
    // -
  }
  ArrayQueue(ArrayQueue q) {
    // -
  }

  void add(Location loc) {
    // -
  }

  void remove() {
    // -
  }

  Location getFront() {
    // -
  }

  int getLength()  {
    // -
  }

  ArrayQueue copyFrom(ArrayQueue q) {
  // -
  }
}

