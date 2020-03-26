
/*
Heap order properties 

  Min Heap: 
  key(p) <=  key(c1) && key (p) <= key(c2)
  c1 , c2 are chideren and p is a parent 

  add -1,-3,-2, 9,7,1,2,3
  ________________________________
  | -1 | -2 | 
  ----------------------------------

  bubble up takes log(n) operations 

  parent = ceil(child/2  - 1 );

  child1 = 2*parent + 1;
  child2 = 2*parent +2;

  you are able to recieve the min of a tree in constant time
  add log(n)
  remove log(n)

  */
