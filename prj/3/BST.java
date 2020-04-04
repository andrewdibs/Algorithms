

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

  public BSTNode minNode() { 
    //-  Locates the most left node
    BSTNode cur = this;
    while (cur.left != null){
      cur = cur.left;
    }
    return cur;
  }

  public BSTNode maxNode() { 
    //-  locates the most right node 
    BSTNode cur = this;
    while (cur.right != null){
      cur = cur.right;
    }
    return cur;
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
        }else{
          break;
        }
      }
    }
  }
  public void remove(String item) { 
    BSTNode parent = null;
    BSTNode cur = root;

    //- find node, determine case, remove node
    if (root == null) return;

    while(cur != null){
      if (cur.getData().compareTo(item) > 0){
        parent = cur;
        cur = cur.left;
      }
      else if (cur.getData().compareTo(item) < 0){
        parent = cur;
        cur = cur.right;
      }else{
        break;
      }
    }

    if (cur == null) return;
    
    // removing a leaf node 
    if (cur.left == null && cur.right == null){
      if (parent == null) {root = null;}
      else if (parent.left == cur){
          parent.left = null;
        }else{
          parent.right = null;
        }
        cur = null; 
    }
    
    // removing a node with one child 
    else if ((cur.left == null && cur.right != null) || 
              (cur.left != null && cur.right == null )){
              
      BSTNode grandChild = null;
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
      cur = null;
    }
    // removing a node with two children
    else {
      // need to keep track of min parent as well
      // BSTNode min = rightChild.minNode();

      BSTNode min = cur.right;
      BSTNode minParent = cur;
      
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
      }else{
        // change pointers of the parent node 
        if (parent.left == cur) {
          parent.left = min;
        }
        else{
          parent.right = min;

        }
      }
      cur.left = null;
      cur.right = null;
      cur = null;
    }
  }
  
 

  public void printPreorder() { if (root != null) root.printPreorder(); }
  public void verifySearchOrder() { if (root != null) root.verifySearchOrder(); }

}


 
class EncryptionTree extends BST {
  public EncryptionTree() {}
  
  public String encrypt(String item) {
    //- encrypts using the location in the tree from the root
    if (root == null) return "?";
    BSTNode cur = root;
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
    //-
    if (root == null) return "?";
    BSTNode cur = root;
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

