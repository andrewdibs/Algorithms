
class ArrayQueue {
  private Location[] data;
  private int length, capacity, front;
  
  private void doubleCapacity() {
    // - doubles the capacity of queue 
    Location[] temp = new Location[capacity*2];
    for (int i=0; i<length; i++){
      temp[i] = data[(front + i) % length];
    }
    data = temp;
    front = 0;
    capacity = capacity * 2;
  }

  ArrayQueue() {
    // - Creates a blank queue
    front = 0;
    length = 0;
    capacity = 1;
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
    
    if (length == capacity){
      doubleCapacity(); 
    }
    int index = (length + front) % capacity;
    data[index] = loc;
    length++;
  }

  void remove() {
    // - removes the front item in the queue 
    front= (front +1)% capacity ;
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

