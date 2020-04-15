import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
   * file: AVLTree.java
   * author: Andrew DiBella
   * course: CMPT 435
   * assignment: project 4
   * due date: April 15, 2020
   * version: 1.0
   * 
   * This file contains the declaration of the 
   * AVLNode, AVLTree, and Encryption Tree
   */


/**
   * AVLNode
   * 
   * This class is the definition of 
   * A AVLNode that can be inserted into a 
   * AVL tree. This implentation has 
   * a Sting data type and pointers to the nodes left 
   * and right children
   */
class AVLNode {
  AVLNode(AVLNode t) { assert(false); }

  AVLNode(String d, AVLNode l, AVLNode r, int h) {
    data = d; left = l; right = r; height = h; 
  }
  
  protected String data;
  protected AVLNode left, right;
  protected int height;
  
  protected AVLNode singleRotateLeft() {
    //- single rotation of node to left
    AVLNode r = this.right; 
    this.right = r.left;
    r.left = this;
    r.updateHeight();
    this.updateHeight();

    return r;
  }

  protected AVLNode singleRotateRight() {
    //- single rotation of node to right
    AVLNode r = this.left;
    this.left = r.right;
    r.right = this;
    r.updateHeight();
    this.updateHeight();

    return r;
  }

  protected AVLNode doubleRotateLeftRight() {
    //- double rotation left then right
    this.left = left.singleRotateLeft();
    return this.singleRotateRight();
  }

  protected AVLNode doubleRotateRightLeft() {
    //- double rotation right then left
    this.right = right.singleRotateRight();
    return this.singleRotateLeft();
  }
  
  protected static int getHeight(AVLNode n) { 
    return n != null ? n.height : -1; 
  }

  protected void updateHeight() {
    int lh = getHeight(left);
    int rh = getHeight(right);
    height = (lh > rh ? lh : rh) + 1;
  }


  public AVLNode getLeft()  { return left;  }
  public AVLNode getRight() { return right; }
  public String getData()   { return data;  }
  public int getHeight()    { return height;}

  public void printPreorder() {
    String indent = "";
    //-  Prints the preorder of the tree 
    if (data == null) 
      return; 
    //print
    System.out.println(indent + data);
    
    // left
    if (left == null){
      System.out.println("  NULL");
    }
    else{
      left.printPreorder("  ");
    }
    // right
    if (right == null){
      System.out.println("  NULL");
    }
    else{
      right.printPreorder("  ");
    }
  }
  
  // Method Overload
  public void printPreorder(String indent){
    //- overloads printPreorder with string param
    
    //-  Prints the preorder of the tree 
    if (data == null) {
      System.out.println(indent + "  NULL");
    }
    else{
      System.out.println(indent + data);
    }
    
    // left
    if (left == null){
      System.out.println(indent + "  NULL");
    }
    else{
      left.printPreorder(indent + "  ");
    }
    // right
    if (right == null){
      System.out.println(indent + "  NULL");
    }
    else{
      right.printPreorder(indent + "  ");
    }

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

  public void verifyBalance() {
    int heightDiff = Math.abs(getHeight(left) - getHeight(right));
    assert(heightDiff <= 1); 
    if (left  != null) left.verifyBalance();
    if (right != null) right.verifyBalance();
  }

  public AVLNode minNode() {
    //- returns the minimum node in tree
    AVLNode min = this;
    while (min.left !=null){
      min = min.left;
    }
    return min;
  }

  public AVLNode maxNode() {
    //- returns the maximum node in tree 
    AVLNode max = this;
    while(max.right != null){
      max = max.right;
    }
    return max;
  }
}


/* An AVLTree is a String-based class that represents an AVL-balanced binary 
 * search tree. It has one data member, "root", which is a reference to the 
 * root of the tree.
 *
 * Many of the methods in this class are virtually identical to methods in the
 * BST from the previous project (#3), including the constructor,
 * printPreorder(), verifySearchOrder(), and copy constructor.
 *
 * The insert() and remove() methods behave as in the plain BST, but both
 * methods should rebalance the tree as necessary. This is best done by creating
 * an array of references to AVLNode objects as the insert/remove methods search
 * for the place to do their work.  This array of references represents the path
 * taken to get from the root to the place where a change occurs in the tree.
 * Note that for remove(), this path might go deeper than the node removed, in
 * the case of removing a node with two children (think carefully about this).
 * After insert/remove finish updating the tree, they can pass the path to
 * rebalancePathToRoot() which actually does the rebalancing. Think about how
 * large the array of references needs to be, at its largest. An AVL tree with
 * height 30 must have at least 3,524,577 nodes, and if it has height 50, it
 * must have at least 53,316,291,172 nodes -- probably more than we care to put
 * in the tree. These results come from the minimum size of an AVL tree of
 * height h, which is described in your book as: S(h) = S(h - 1) + S(h - 2) + 1
 * (and base cases S(0) = 1, S(1) = 2).
 *
 * The printLevelOrder() method prints out all the nodes in the tree in
 * level-order (root, then the root's children, then their children, etc.). This
 * is like performing a breadth-first search of the tree. The method should put
 * up to 20 nodes on each line, and use multiple lines as necessary. This method
 * should use a Java queue, and it is iterative (not recursive). This method is
 * useful if we want to transmit the information for building exactly the same
 * tree to our correspondent. If we were to take all the non-NULL nodes and
 * insert them in the order printed by this method, we would get the exact same
 * tree. We would not always be able to construct the exact same tree if we were
 * to use printPreorder() instead.
 *
 * The rebalancePathToRoot() method takes an array of references to AVLNode
 * objects, and the number of references that are on the array. This array should
 * represent the path that needs rebalancing after an insert or remove. It's
 * probably best to have the root at the start of the array. This method should
 * walk from the bottom of the path to the root, checking for imbalances, and
 * correcting any it finds by calling rotation methods as necessary to correct
 * imbalances.
 */

 /**
   * AVLTree
   * 
   * This class implements a AVL tree 
   * with strings as its data member
   * root is the AVLNode that has links to its 
   * children 
   */
class AVLTree {
  protected AVLNode root;
  
  AVLTree(AVLTree t) { assert(false); }

  AVLTree() { root = null; }

  protected void rebalancePathToRoot(AVLNode[] path, int numOnPath) {
    //-
    AVLNode rotated = null;
    numOnPath--;
    while (numOnPath > -1){
      System.out.println(path[numOnPath].data);
      AVLNode cur = path[numOnPath];
      cur.updateHeight();
      int left = AVLNode.getHeight(cur.left);
      int right = AVLNode.getHeight(cur.right);

      if (left + 1 < right){
        int rightLeft = AVLNode.getHeight(cur.right.left);
        int rightRight = AVLNode.getHeight(cur.right.right);
        if (rightLeft > rightRight){
          rotated = cur.doubleRotateRightLeft();
        }else{
          rotated = cur.singleRotateLeft();
        }
      }else if (right + 1 < left){
        int leftRight = AVLNode.getHeight(cur.left.right);
        int leftLeft = AVLNode.getHeight(cur.left.left);
        if (leftRight > leftLeft){
          rotated = cur.doubleRotateLeftRight();
        }else{
          rotated = cur.singleRotateRight();
        }
      }

      if (rotated != null){
        if (numOnPath == 0){ root = rotated; }
        else if(cur == path[numOnPath - 1].left){
          path[numOnPath - 1].left = rotated;
        }else{
          path[numOnPath - 1].right = rotated;
        } 
      }
      numOnPath--;
    }
  }

  
  public void insert(String item) {
    //- insert new node with data item

    if (root == null) root = new AVLNode(item, null, null, 0);
    else{
      AVLNode[] path = new AVLNode[32];
      int numOnPath = 0;
      AVLNode cur = root;

      while (cur != null){
        path[numOnPath++] = cur;
        int compare = cur.data.compareTo(item);
        // right
        if (compare < 0){
          if (cur.right != null){
            cur = cur.right;
          }else{
            cur.right = new AVLNode(item, null, null, 0);
            path[numOnPath++] = cur.right;
            rebalancePathToRoot(path, numOnPath);
            return;
          }
        } // left
        else if (compare > 0){
          if (cur.left != null){
            cur = cur.left;
          }else{ 
            cur.left = new AVLNode(item, null, null, 0);
            path[numOnPath++] = cur.left;
            rebalancePathToRoot(path, numOnPath);
            return;
          }
        }
        else{
          return;
        }
      }
    }
  }

  public void remove(String item) {
    //-
    if (root == null) return;
  
    AVLNode[] path = new AVLNode[32];
    int numOnPath = 0;
    AVLNode parent = null;
    AVLNode cur = root;

    while (cur != null){
      path[numOnPath++] = cur;
      int compare = cur.data.compareTo(item);

      if (compare > 0){
        parent = cur; 
        cur = cur.left;
      }
      else if (compare < 0){
        parent = cur;
        cur = cur.right;
      }
      else{
        break;
      }
    }

    if (cur == null) return;

    // leaf node
    if (cur.left == null && cur.right == null){
      if(parent == null){ root = null; }
      else if(parent.left == cur){
        parent.left = null;
      }
      else{
        parent.right = null;
      }
      cur = null;
    }

  }

  public void printLevelOrder() {
    //- prints each level of tree starting with root

    if (root == null) {
      System.out.println("NULL");
      return;
    }
    Queue<AVLNode> q = new LinkedList<AVLNode>();
    int numItems = 0;

    q.add(root);
    AVLNode front = q.peek();

    while (front != null){
      // left
      if (front.left != null){
        q.add(front.left);
      }
      // right
      if (front.right != null){
        q.add(front.right);
      }
      // new line
      if (numItems == 20){
        System.out.println(q.remove().getData());
        numItems = 0;  
      }else{
        System.out.print(q.remove().getData() + "  ");
      }
      front = q.peek();
    }
    System.out.println();

  }

  public void printPreorder() { 
    if (root != null) root.printPreorder(); 
  }

  public void verifySearchOrder() { 
    if (root != null) root.verifySearchOrder(); 
  }

  public void verifyBalance() { 
    if (root != null) root.verifyBalance(); 
  }

}


/**
   * Encryption Tree
   * 
   * This class extends a AVL Tree
   * Uses the data in the tree to encypt based on a 
   * specific string or decrpyt based on a given path
   */

class EncryptionTree extends AVLTree {
  EncryptionTree() {}

  public String encrypt(String item) {
    //- encrypts using the location in the tree from the root
    if (root == null) return "?";
    AVLNode cur = root;
    String path = "r";
    // while the current node isnt the item 
    while(cur != null && !(cur.data.compareTo(item) == 0)){
      // to the left 
      if (cur.data.compareTo(item) > 0){
        path = path + "0";
        cur = cur.left;
        if (cur == null) return "?";
      }
      // to the right
      else if (cur.data.compareTo(item) < 0){
        path = path + "1";
        cur = cur.right;
        if (cur == null) return "?";
      }
    }
    return path;
  }

  public String decrypt(String path) { 
    //- decrypts the path of single node
    if (root == null) return "?";
    AVLNode cur = root;
    for (int i = 0; i< path.length();i++){
      char next = path.charAt(i);
      if (next == '0'){
        cur = cur.left;
        if (cur == null) break;
      }
      else if (next == '1'){
        cur = cur.right;
        if (cur == null) break;
      }
    }
    // return final node data in path
    if (cur != null)
      return cur.data;
    //else return empty string 
    return "?";
  } 
}
