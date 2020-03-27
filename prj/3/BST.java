import java.util.Scanner;

/* A BSTNode represents a node in a binary search tree. Each BSTNode object
 * 
 *
 * The printPreorder() traverses this node and its children recursively in
 * pre-order and prints each node it visits to standard output (i.e.
 * System.in). It presumes that "data" can be printed; that is, 
 * "System.out.print(this.data)" is a statement that makes sense. At each 
 * level of the tree it adds two spaces of indentation to show the structure 
 * of the tree. The run-time of printPreorder() is O(n). Can you figure out 
 * why?  Could it be made more efficient?
 */
class BSTNode {
  protected  BSTNode(BSTNode t) { assert(false); }

  protected  String data;
  protected  BSTNode left;
  protected  BSTNode right;

  public BSTNode(String d, BSTNode l, BSTNode r) {
    data = d; left = l; right = r;
  }

  public BSTNode getLeft()  { return left;  }
  public BSTNode getRight()  { return right; }
  public String getData()    { return data;  }
  
  public void printPreorder() {
    String indent = "  ";
    //-  Prints the preorder of the tree 
    if (this.data == null) 
      return; 
    // first print data of node
    System.out.print(this.data + indent); 
    //then recur on left sutree 
    left.printPreorder();  
    // now recur on right subtree 
    right.printPreorder(); 
  }
   
  public BSTNode minNode() { 
    //-  Locates the most left node
    BSTNode cur = this;
    while (cur != null){
      cur = cur.left;
    }
    return cur;
  }
  public BSTNode maxNode() { 
    //-  locates the most right node 
    BSTNode cur = this;
    while (cur != null){
      cur = cur.right;
    }
    return cur;
  }
  public BSTNode duplicate(){
    return this; 
  }

  public void verifySearchOrder() {
    if (left != null) {
      assert(left.maxNode().data.compareTo(data) == -1);
      left.verifySearchOrder();
    }
    if (right != null) {
      assert(data.compareTo(right.minNode().data) == -1);
      right.verifySearchOrder();
    }
  }
}

/* A BST is a String-based class, but could easily be coded as a generic-type 
 * type class (e.g. with T), that represents a binary search tree. It has one
 * data member, "root", which is a pointer to the root of the tree.
 *
 * The constructor is provided for you.
 *
 * The insert() method places the given item in the tree, unless the item is
 * already in the tree. The insertion should follow the algorithm we discuss in
 * class.
 *
 * The remove() method removes the given item from the tree, if it is in the
 * tree. The remove should follow the algorithm we discuss in class.
 *
 * The printPreorder() and verifySearchOrder() methods simply calls the relevant
 * per-node methods on the root.
 *
 * No one may call the copy constructor on a BST, it is hereby forbidden, so
 * it is protected and will crash the program if called.
 */
class BST {
  protected BST(BST t) { assert(false); }
  protected BST isEqual(BST t) { assert(false); return this; }
  
  protected BSTNode root;

  public BST() {
    root = null; 
  }

  public void insert(String item) { 
    //- insertion of new node not already in tree 

    if (root == null) root = new BSTNode(item,null,null);
    else{
      BSTNode cur = root;

      while (true){
        if (cur.data.compareTo(item) > 0 ){
          if (cur.left == null){
            cur.left = new BSTNode(item,null,null);
            break;
          }else{
            cur = cur.left;
          }
        }
        else if (cur.data.compareTo(item) < 0){
          if (cur.right == null){
            cur.right = new BSTNode(item,null,null);
            break;
          }else{
            cur = cur.right;
          }
        }
      }
    }
  }
  public void remove(String item) { 
    //- find node, determine case, remove node
    if (root != null){
      BSTNode parent = null;
      BSTNode cur = root;
      // locate the node to remove
      while(cur != null){
        // left is less than item
        if (cur.data.compareTo(item) > 0) {
          parent = cur;
          cur = parent.left;
          if (cur.data.equals(item)){
            break;
          }
        }
        // to the right
        else if (cur.data.compareTo(item) < 0){
          parent = cur;
          cur = parent.right;
          if (cur.data.equals(item)){
            break;
          }
        }
      }
      // if to remove is not in tree do nothing
      if (cur == null) return;

      // cur node is node to remove: store grandchildren of cur parent
      BSTNode leftChild = cur.left;
      BSTNode rightChild = cur.right; 
      // removing a leaf node 
      if (leftChild == null && rightChild == null){
        if (parent.data.compareTo(cur.data) >= 0){
          parent.left = null;
        }else{
          parent.right = null;
        }
        cur = null; 
      }
      // removing a node with one child 
      else if (leftChild == null && rightChild != null || 
                leftChild != null && rightChild == null ){
        
        // make left grandchild parents left child
        if (rightChild == null){
          parent.left = leftChild;
        }
        // make right grandchild parents right child
        else{
          parent.right = rightChild;
        }
        cur = null;
        
      }
      // removing a node with two children
      else{
        BSTNode min = rightChild.minNode();

        min.left = cur.left;
        min.right = cur.right;
        if (cur == root) root = min;
        
        cur = null;

      }


      


    }


  }
 

  public void printPreorder() { if (root != null) root.printPreorder(); }
  public void verifySearchOrder() { if (root != null) root.verifySearchOrder(); }

}

/* An EncryptionTree is a special type of BST which knows how to encrypt a
 * String object (e.g. word) into a string that represents the path to the 
 * object in the tree, and decrypt a path into the String object (e.g. word) 
 * it leads to.
 *
 * The constructor method is provided for you.
 *
 * The encrypt() method takes a String object and returns a string of the form 
 * "rX" where "r" is a literal letter r, and X is a sequence of 0 and 1 
 * characters (which may be empty). The r stands for "root", and each 0 and 1 
 * represent the left/right path from the root to the given object, with 0 
 * indicating a left-branch and 1 indicating a right-branch. If the object is 
 * not in the dictionary, an empty string (or the string "?") should be 
 * returned.
 *
 * The decrypt() method takes an encrypted string (or path through the tree) in
 * the form provided by encrypt(). It should return a pointer to the associated
 * string for the given path (or NULL if the path is invalid).
 */
class EncryptionTree extends BST {
  public EncryptionTree() {}
  
  public String encrypt(String item) {
    //- encrypts using the location in the tree from the root
    if (root == null) return "";
    BSTNode cur = root;
    String path = "r";
    // while the current node isnt the item 
    while(!(cur.data.compareTo(item) == 0)){
      // to the left 
      if (cur.data.compareTo(item) > 0){
        path = path + "0";
        cur = cur.left;
      }
      // to the right
      else if (cur.data.compareTo(item) < 0){
        path = path + "1";
        cur = cur.right;
      }
    }

    return path;

  }
  public String decrypt(String path) { 
    //-
    if (root == null) return "";
    BSTNode cur = root;
    for (int i = 0; i< path.length();i++){
      char next = path.charAt(i);
      if (next == '0'){
        cur = cur.left;
      }
      else if (next == '1'){
        cur = cur.right;
      }
    }
    // return final node data in path
    if (!(cur == null))
      return cur.data;
    //else return empty string 
    return "";
  } 
}

