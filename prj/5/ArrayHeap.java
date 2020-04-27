
/**
   * file: ArrayHeap.java
   * author: Andrew DiBella
   * course: CMPT 435
   * assignment: project 5
   * due date: April 27, 2020
   * version: 3.0
   * 
   * This file contains the abstract data 
   * type of an array heap to organize process 
   * objects and retreiving them in constant time
   */
class ArrayHeap {
  private Process[] data;
  private int[] heapAndFreeStack;

  private int numItems;
  private int capacity;

  private void doubleCapacity() {
    // - doubles the capacity of the arrays and copys to new sized arrays
    this.capacity = capacity * 2;
    Process[] dataTmp = new Process[capacity];
    int[] heapTmp = new int[capacity];

    for (int i = 0; i < data.length; i++){
      dataTmp[i] = data[i];
      heapTmp[i] = heapAndFreeStack[i];
    }
    this.data = dataTmp;
    this.heapAndFreeStack = heapTmp;
    for (int j = numItems; j < capacity ; j++){
      heapAndFreeStack[j] = j;
    }
  }

  private void bubbleUp(int ndx) {
    // - bubbles up newly inserted item if necessary
    if (ndx == 0) return;

    int parent = ( ndx - 1 )/ 2; 
    if (data[heapAndFreeStack[ndx]].isLess(data[heapAndFreeStack[parent]])){

      int tmp = heapAndFreeStack[ndx];
      heapAndFreeStack[ndx] = heapAndFreeStack[parent];
      heapAndFreeStack[parent] = tmp;
    }
    //recursion
    bubbleUp(parent);
    
  }

  private void bubbleDown(int ndx) {
    // - bubbles down data member at index with children
    int child1 = (ndx * 2) + 1;
    int child2 = child1 + 1;
    // check if ndx has children to bubble down
    if (child1 < numItems){
      int lesserChild = child1;
      // find correct lesser child
      if (child2 < numItems && 
        data[heapAndFreeStack[child2]].isLess(data[heapAndFreeStack[child1]])){
        lesserChild = child2;
      }
      // compare 
      if(data[heapAndFreeStack[lesserChild]].isLess(data[heapAndFreeStack[ndx]])){
        // swap
        int tmp = heapAndFreeStack[ndx];
        heapAndFreeStack[ndx] = heapAndFreeStack[lesserChild];
        heapAndFreeStack[lesserChild] = tmp;
      }
      // recursion
      bubbleDown(lesserChild);
    }
  }


  public ArrayHeap() {
    // - creates a new default ArrayHeap
    this.numItems = 0;
    this.capacity = 5;
    this.data = new Process[capacity];
    this.heapAndFreeStack = new int[capacity];
    for (int i = 0; i < capacity; i++){
      heapAndFreeStack[i] = i;
    }
  }

  public ArrayHeap(ArrayHeap h) {
    // - creates a copy of the arrayHeap h
    this.numItems = h.numItems;
    this.capacity = h.capacity;
    this.data = h.data;
    this.heapAndFreeStack = h.heapAndFreeStack;
  }

  public void insert(Process item) {
    // - inserts item and doubles cap if necessa
    //  double capacity 
    if (numItems == capacity) doubleCapacity();

    // add to last open position
    data[heapAndFreeStack[numItems]] = item;
    // bubble up
    bubbleUp(numItems++);
    
  }

  public void removeMinItem() {
    // - removes min from heap and puts it in free stack 
    int tmp = heapAndFreeStack[numItems-1];
    heapAndFreeStack[numItems-1] = heapAndFreeStack[0];
    heapAndFreeStack[0] = tmp;
    
    numItems--;
    bubbleDown(0);
  }

  public Process getMinItem() {
    // - returns minItem in data processes 
    return data[heapAndFreeStack[0]];
  }

  public int getNumItems() {
    // - returns heaps number of items
    return this.numItems;
  }
}
