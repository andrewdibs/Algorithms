import java.util.Scanner;

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
          if (cur == null) break;
          if (cur.data.equals(item)){
            break;
          }
        }
        // to the right
        else if (cur.data.compareTo(item) < 0){
          parent = cur;
          cur = parent.right;
          if (cur == null) break;
          if (cur.data.compareTo(item) == 0){
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
        if (cur == root) root = null;
        else{
          if (parent.data.compareTo(cur.data) >= 0){
            parent.left = null;
          }else{
            parent.right = null;
          }
          cur = null; 
        }
      }
      // removing a node with one child 
      else if (leftChild == null && rightChild != null || 
                leftChild != null && rightChild == null ){
        
        // make left grandchild parents left child
        if (rightChild == null){
          if(cur == root) root = cur.left;
          else parent.left = leftChild;
        }
        // make right grandchild parents right child
        else{
          if(cur == root) root = cur.right;
          else parent.right = rightChild;
        }
        cur = null;
        
      }
      // removing a node with two children
      else{
        // need to keep track of min parent as well
        // BSTNode min = rightChild.minNode();

        BSTNode min = cur.right;
        BSTNode minParent = cur;
        while(min.left != null){
          minParent = min;
          min = min.left;
        }
        // relocate the min right node 
        minParent.left = min.right;
        
        // change pointers of the leftmost node
        min.left = cur.left;
        min.right = cur.right;

        // if the node to remove is the root
        if (cur == root){ 
          root = min;
        }else{
          // change pointers of the parent node 
          if (parent.left == cur) parent.right = min;
          else if (parent.right == cur) parent.right = min;
        }
        cur = null;
      }
    }
  }
 

  public void printPreorder() { if (root != null) root.printPreorder(); }
  public void verifySearchOrder() { if (root != null) root.verifySearchOrder(); }

}


 
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

