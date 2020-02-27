
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
    data = temp.clone();
    
  }

  ArrayQueue() {
    // - Creates a blank queue
    front = 0;
    length = 0;
    capacity = 500;
    data = new Location[capacity];
  }

  ArrayQueue(ArrayQueue q) {
    // - creates new arrray queue from contents q
    this.length = q.length;
    this.capacity = q.capacity;
    this.front = q.front;
    for (int i = 0;i < capacity;i++){
      this.data[i] = q.data[i];
    }
  }

  void add(Location loc) {
    // - finds the correct index to add loc to circular array
    int index = (length + front) % capacity;
    data[index] = loc;
    length++;
    if (length == capacity){
      doubleCapacity(); 
    }
    System.out.println(front);
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
}

