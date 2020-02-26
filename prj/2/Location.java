import java.util.Scanner;

class Location implements Comparable<Location>{
  final int CHANGE_LETTER = 0;
  final int INSERT_LETTER = 1;
  final int DELETE_LETTER = 2;
  final int DONE          = 3;

  String word;
  int iterationMode;
  int indexToChange;
  char nextLetter;

  Location() {
    // - blank location 
    word = "";
    iterationMode = CHANGE_LETTER;
    indexToChange = 0;
    nextLetter = 'a';

  }

  void start() {  
    // - start iteration process
    iterationMode = CHANGE_LETTER;
    indexToChange = 0;
    nextLetter = 'a';
  }

  Location nextNeighbor() {  
    // -
    StringBuilder sb = new StringBuilder();
    sb.append(word);
    
    
    if (iterationMode == CHANGE_LETTER){
      sb.setCharAt(indexToChange, nextLetter);
      nextLetter++;
      if(nextLetter == '{'){
        nextLetter = 'a';
        indexToChange++;
        if (indexToChange>word.length()-1){
          indexToChange = 0;
          iterationMode++;
        }
      }
    }
    else if (iterationMode == INSERT_LETTER){
      sb.insert(indexToChange, nextLetter);
      nextLetter++;
      if(nextLetter == '{'){
        nextLetter = 'a';
        indexToChange++;
        if (indexToChange>word.length()){
          indexToChange = 0;
          iterationMode++;
        }
      }
    } 
    else if (iterationMode == DELETE_LETTER){
      sb.deleteCharAt(indexToChange);
      indexToChange++;
      if (indexToChange>word.length()-1){
        iterationMode++;
      }
    }
    //System.out.println(iterationMode);
    String next = sb.toString();
    //System.out.println(next);
    
    Location neighbor = new Location();
    neighbor.word = next;


    return neighbor; 
  }

  boolean isDone() {  
    // - checks if done with iteration
    return iterationMode == DONE;
  }

  boolean isEqual(Location loc) {  
    // - checks if the lcoation word is equal 
    return loc.word.equals(this.word);
  }

  void streamOut() {
    // -  print out the location word 
    System.out.println(word);
  }

  void streamIn(Scanner input) {
    // -  input location word 
    this.word = input.nextLine();

  }

  boolean isLess(Location loc) {
    // -
    int c = word.compareTo(loc.word);
    return c<0;
  }

  public int compareTo(Location loc){
    return word.compareTo(loc.word);
  }
}
