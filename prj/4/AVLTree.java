import java.util.LinkedList;
import java.util.Queue;
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
    this.updateHeight();
    r.updateHeight();
    
    return r;
  }

  protected AVLNode singleRotateRight() {
    //- single rotation of node to right
    AVLNode r = this.left;
    this.left = r.right;
    r.right = this;
    this.updateHeight();
    r.updateHeight();
  
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
  

  public void printPreorder() {
    String indent = "";
    //-  Prints the preorder of the tree 
    if (data == null) 
      return; 
    //print
    System.out.println(indent + data + height);
    
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
      System.out.println(indent + data + height);
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
    numOnPath--;
    while (numOnPath > -1){
      AVLNode rotated = null;
      AVLNode cur = path[numOnPath];
      cur.updateHeight();
      int left = AVLNode.getHeight(cur.left);
      int right = AVLNode.getHeight(cur.right);

      if (right - left > 1){
        int rightLeft = AVLNode.getHeight(cur.right.left);
        int rightRight = AVLNode.getHeight(cur.right.right);
        if (rightLeft > rightRight){
          rotated = cur.doubleRotateRightLeft();
        }else{
          rotated = cur.singleRotateLeft();
        }
      }else if (left - right > 1){
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
    // tree is empty nothing to remove 
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
    // node not in tree 
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
    // one child
    else if ((cur.left == null && cur.right != null) || 
              (cur.left != null && cur.right == null )){
              
      AVLNode grandChild = null;
      // make left grandchild parents left child
      if (cur.left != null){
        grandChild = cur.left;
        cur.left = null;
      }else{
        grandChild = cur.right;
        cur.right = null;
      }

      if (parent == null){
        root = grandChild;
      }
      else if (parent.left == cur){
        parent.left = grandChild;
      }else{
        parent.right = grandChild;
      }
      path[numOnPath++] = grandChild;
      cur = null;
    }
    // removing a node with two children
    else {
      // need to keep track of min parent as well
      AVLNode min = cur.right;
      AVLNode minParent = cur;
      
      if (min.left != null){
        while(min.left != null){
          minParent = min;
          min = min.left;
        }
        // relocate the min right node 
        minParent.left = min.right;
        min.right = cur.right;
      }
      // change pointers of the leftmost node
      min.left = cur.left;

      // if the node to remove is the root
      if (parent == null){ 
        root = min;
        path[0] = min;
      }else{
        // change pointers of the parent node 
        if (parent.left == cur) {
          parent.left = min;
        }
        else{
          parent.right = min;

        }
        path[numOnPath++] = min;
      }
      cur.left = null;
      cur.right = null;
      cur = null;
    }
    rebalancePathToRoot(path, numOnPath);

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
      numItems++;
      // left
      if (front.left != null){
        q.add(front.left);
      }else if (!front.data.equals("NULL")){
        q.add(new AVLNode("NULL", null, null, -1));
      }
      // right
      if (front.right != null){
        q.add(front.right);
      }else if (!front.data.equals("NULL")){ 
        q.add(new AVLNode("NULL", null, null, -1));
      }
      // new line
      if (numItems == 20){
        System.out.println(q.remove().getData());
        numItems = 0;  
      }else{
        System.out.print(q.remove().getData() + " ");
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
