import sun.management.resources.agent;

class Trees{
    public static void main(String [] args){
        Trees h = new Trees();
        System.out.println(h.g(10));

    }
    int g(int n) {
        int x = g(n - 1);
        if (x > 0) {
          return x + 1;
        } else {
          return 1;
        }
    }


    //  TREES 
      // TRIE TREE 
      //  ReTRIEval Tree 
    // fast bad memory 
      // B-Tree
      //good optimization   
      
      //Generic trees 
      class TreeNode<T> {
        T data;
        //TreeNode[] ch = new TreeNode[n];
        TreeNode child, sibling;

    //    a
     //   |
  //      b---c---d---e
   //     |       |
     //   f--g    h 

      }

      //Binary Tree             a--b---c---d---e is considered a binary and binary search tree
      a
     /  \
    b     c   ---f
   /  \    |
  d    z   g
    //post order : d,z,b,a,g,f,c,a visit children first bottom work way up // root always last
    //preorder: a,b,d,z,c,g,f   root first 
    //BST :  inorder:  d,b,z,a,g,c,f
    //Binary search trees have no duplicates   
}