class ADTs{
// ADTS 
// USED IN PRJECT 2 
    // linear : queue , stack , array , linked list ....
    // non-linear : trees, graphs ,maps , heaps ....

    // QUEUES add to the back remove from the front
      //FIFO - first inout first output
      // 'line' 
      //      operations     runtime 
      //      add()           O(1)
      //      remove()        O(1)
      //      getLength()     O(1)
      //      getFront()      O(1)
      
      //  0 1 2 3 4 5 6 7 8 9
      //  845867873 
        int front = 0;
        int[] arr; 
        int capacity = 10 ;
        int length = 10; 

    // CIRCULAR array queue 
    // REMOVE :
      // avoid shifting data 
      // the queue wraps around the end of the array to the beginning 
      front = (front+1) % capacity;
      back = (front +length) % capacity;

      //to double the capacity you must double after it is already full 
      // when the array is full you want to make sure the front is at position 0
      // and length is == to capacity

      // convert ifix to posfix 
      // - operators + , *, () 
      // scan expression from left to right 
      // when you see an operand - print it 
      // keep pending operators on the stack 
      // order of precendence 
          // () -->   *   -->  + 
      // if you see an operator: if it is of higher 
      //precendence than what is in the stack
      // -- push it to the stack 

      // if it is of equal or lower precedence then print 
      // and pop until you find a lower precedence operator
      // *exception : dont do this for parenthesis you can only
      // pop when you find the corresponding closig parenthesis

      // example : 
        a + b * c --> abc*+
        a+b*c*d ---> abc*d*+
    }
// PROJECT 2 
// the moment you push something in the queue you have found the shortet path for
// that item 
    (to) (from)
// key | val              queue 
// ----------         
// cat   cat              | cat | hat |  
// hat   cat 

PROJECT 2 
//pop from the queue and check neighbors find key and next neightbor then push to stack
