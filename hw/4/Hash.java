import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Hash {
  public static void main(String [] args){
    
    Scanner input = new Scanner(System.in);
    int tableSize = 8192;
    int probes = 0;
    Map<Integer, String> map = new HashMap<>();
    
    while (input.hasNext()){
      String next = input.nextLine();
      

      int index = hash_string_3(next, tableSize);
      while (map.get(index) != null){
        index++;
        probes++;
      }
      map.put(index, next);
      probes++;


      // QUADRATIC Probing ; choose one commment other

      int index = hash_string_3(next,tableSize);
        int start = index;
        int count = 1;
        do{
          if (map.get(index) == null){
              map.put(index, next);
              probes++;
              break;
            }
            probes++;        
            index = (index + count * count++) % tableSize;            
        } while (index != start);  



    }
    System.out.println(probes); 
  }

  static int hash_string_1(String key, int tableSize) {
    return (key.length() * key.length() * 4) % tableSize;
  }
  
  static int hash_string_2(String key, int tableSize) {
    return   (key.charAt(0) + 
         27 * key.charAt(1) + 
        729 * key.charAt(2)) % tableSize;
  }
  
  static int hash_string_3(String key, int tableSize) {
    int hash = 0;
    int prime = 31;
    for (int i = 0; i < key.length();i++){
      hash = (prime * hash + key.charAt(i));
    }
    return hash % tableSize;
  }

}