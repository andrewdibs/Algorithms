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
    // - doubles the capacity of queue 
    capacity = capacity * 2;
    Location[] temp = new Location[capacity];
    for (int i=0; i<length; i++){
      temp[i] = data[(front + i) % length];
    }

    front = 0;
    this.data = temp;
  }

  ArrayQueue() {
    // - Creates a blank queue
    front = 0;
    length = 0;
    capacity = 10;
    data = new Location[capacity];
  }
  ArrayQueue(ArrayQueue q) {
    // - creates new arrray queue from contents q
    this.length = q.length;
    this.capacity = q.capacity;
    this.front = q.front;
    for (int i= 0;i< capacity;i++){
      this.data[i]= q.data[i];
    }
  }

  void add(Location loc) {
    // - finds the correct index to add loc to circular array
    if (length == capacity){
      doubleCapacity();
    }
    int index = (length + front) % capacity;
    data[index] = loc;
    length++;
  }

  void remove() {
    // - removes the front item in the queue 
    front++;
    length--;
  }

  Location getFront() {
    // - returns the location of the front 
    return data[front];
  }

  int getLength()  {
    // - current length of the queue
    return length;
  }

  ArrayQueue copyFrom(ArrayQueue q) {
  // - returns copy of q 
  ArrayQueue copy = new ArrayQueue(q);
  return copy;
  }

  boolean isOn(Location loc){
    for (Location cur: data){
      if (cur.isEqual(loc))return true; 
    }
    return false;

  }
}

