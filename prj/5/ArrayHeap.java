
/* An ArrayHeap is a binary heap that is implemented using arrays. The heap can
 * grow dynamically if it gets full (it should double in capacity each time it
 * gets full).
 *
 * The default constructor constructs an empty ArrayHeap.
 *
 * The copy constructor creates a logical copy of the given ArrayHeap,
 * storing the values in its own copy. It should resize the heap as necessary 
 * so that the two have the same capacity.
 *
 * The insert method inserts the given item into the heap (at the bottom),
 * and then restores the heap order property by bubbling the item up.
 *
 * The removeMinItem method removes the minimum item at the root of the
 * heap, places the last element in the root's position, and bubbles it
 * down to restore the heap property.
 *
 * The getMinItem method returns the minimum item at the top of the heap.
 *
 * The getNumItems method returns the number of items that are on the heap.
 *
 * The doubleCapacity method doubles the capacity of the heap (the data and
 * heapAndFreeStack data members).
 *
 * The bubbleUp method can be implemented iteratively or recursively. It
 * starts at the item given by ndx, and moves it up the heap towards the
 * root in order to preserve the heap order property.
 *
 * The bubbleDown method can be implemented iteratively or recursively. It
 * starts at the item given by ndx, and moves it down the heap towards the
 * leaves in order to preserve the heap order property.
 *
 * The data member "data" contains the items that are stored in the heap.
 * Once an item is placed in data, it should not move.
 *
 * The data member "heapAndFreeStack" describes the structure of the heap (in
 * heapAndFreeStack[0] through heapAndFreeStack[numItems-1]), and it has a stack
 * of the indexes in data that are not being used (in heapAndFreeStack[numItems]
 * through heapAndFreeStack[capacity-1]).  Every value in this array (whether in
 * the heap or stack region) is an index into the array "data".
 *
 * The data member "numItems" contains the number of items on the heap, and
 * serves as the dividing line between the heap structure and the stack of free
 * items.
 *
 * The data member "capacity" represents the number of items that the heap can
 * contain before requiring extra space. The data and heapAndFreeStack arrays
 * both are capacity long.
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

    dataTmp = null;
    heapTmp = null;
    for (int j = numItems; numItems < capacity; j++){
      heapAndFreeStack[j] = j;
    }
    // maybe print out indexxes for each to test 
  }

  private void bubbleUp(int ndx) {
    // - bubbles up newly inserted item if necessary
    if (ndx == 0) return;

    int parent = ndx - 1 / 2; 
    if (data[heapAndFreeStack[ndx]].isLess(data[heapAndFreeStack[parent]])){

      int tmp = heapAndFreeStack[ndx];
      heapAndFreeStack[ndx] = heapAndFreeStack[parent];
      heapAndFreeStack[parent] = tmp;

      bubbleUp(parent);
    }

  }

  private void bubbleDown(int ndx) {
    // - bubbles down data member at index with children
    int child1 = 2 * (ndx + 1) - 1;
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
        // recursion
        bubbleDown(lesserChild);
      }
    }
  }


  public ArrayHeap() {
    // - creates a new default ArrayHeap
    this.numItems = 0;
    this.capacity = 2;
    this.data = new Process[2];
    this.heapAndFreeStack = new int[2];
    heapAndFreeStack[0] = 0;
    heapAndFreeStack[1] = 1;

  }
  public ArrayHeap(ArrayHeap h) {
    // - creates a copy of the arrayHeap h
    this.numItems = h.numItems;
    this.capacity = h.capacity;
    this.data = h.data;
    this.heapAndFreeStack = h.heapAndFreeStack;
  }

  public void insert(Process item) {
    // -
    //  double capacity 
    if (numItems == capacity) doubleCapacity();

    // add to last open position
    data[numItems] = item;
    numItems++;
    // bubble up
    bubbleUp(numItems - 1);
  }

  public void removeMinItem() {
    // -
    int tmp = heapAndFreeStack[numItems-1];
    heapAndFreeStack[0] = tmp;
    heapAndFreeStack[numItems-1] = tmp;
    numItems--;
    bubbleDown(heapAndFreeStack[0]);

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
