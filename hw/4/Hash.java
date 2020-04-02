class Hash{
  public static void main(String [] args){
    hash_string_1(key, tableSize);
    



  }
  static int hash_string_1(String key, int tableSize) {
    return (key.length() * key.length() * 4) % tableSize;
  }
  
  static int hash_string_2(String key, int tableSize) {
    return   (key.charAt(0) + 
         27 * key.charAt(1) + 
        729 * key.charAt(2)) % tableSize;
  }
  
  //static int hash_string_3(String key, int tableSize) {
    // come up with your own hash function and see if you 
    // can do better on any of the test cases
  //}

}