/* CMPT 435
 * Project 0 -- Program trace verification
 * Filename: Driver_prj0.java
 * Student name: Andrew DiBella
 *
 * This Program uses a Stack to trace the calls and returns of a txt file input
 * If trace is valid output is confirmation, otherwise error
 * Some duplicate code, but did not see the need to create seperate functions
 */

import java.util.Scanner;
import java.util.Stack;

public class Driver_prj0 {
  /* main
   *  parameters:
   *      args -- the array of command line argument values
   *  return value: nothing
   *  Programs main function is used for the entirity 
   */
  public static void main(String[] args) {
    // Here we initialize the scaner variable to read lines of input
    Scanner input = new Scanner(System.in);
    String line;
    String top = "";
    String curFunc = "";

    // the callStack is used for storing the names of functions that have been
    // called and not yet returned
    Stack<String> callStack = new Stack<String>();

    // Each time we go through this while loop, we read a line of input.
    // The function hasNext() returns a boolean, which is checked by the while 
    // condition. If System.in has reached the end of the file, it will return 
    // false and the loop will exit.  Otherwise, it will return true and the 
    // loop will continue.
    int lineNumber = 0;
    int maximum_depth = 0;
    int depth = 0;
    if (input.hasNext()){
      while (input.hasNext()) {
        line = input.nextLine();
        lineNumber++;
        if (line.charAt(0) == 'c'){
          callStack.push(line);
          depth++;
          //sets max depth if current depth exceeds previous max
          if (depth > maximum_depth) 
            maximum_depth = depth;
          if (depth>0) 
            top = callStack.peek();
        }
        else if (line.charAt(0) == 'r'){
          curFunc = line.substring(7);
          //  If stack is empty output error and break loop
          if (callStack.empty()){
            System.out.println("Invalid trace at line " + lineNumber);
            System.out.println("Returning from " + curFunc + " which was not called");
            System.out.println("Stack trace:");
            break;          
          }
          //  If current return function matches the top of call stack - POP
          else if (curFunc.equals(top.substring(5))){ 
            callStack.pop();
            depth--;
            if (depth > 0)
              top = callStack.peek();
            //  If stack trace is valid output confirmation  
            if (!input.hasNext() && callStack.empty()){
              System.out.println("Valid trace");
              System.out.println("Maximum call depth was " + maximum_depth);
              break;
            }      
          }
          else{
            // Error for wrong function call
            System.out.println("Invalid trace at line " + lineNumber);
            System.out.println("Returning from "+ curFunc + " instead of " + top.substring(5));
            System.out.println("Stack trace:");
            //  Prints stack trace 
            for (int i=0; i < depth; i++){
              System.out.println(callStack.pop().substring(5));
            }
            break;
          }
        }
      }
    }
    //  If input started with nothing 
    else{
      System.out.println("Valid trace");
      System.out.println("Maximum call depth was 0");
    }
    //  Error if Stack has unreturned call
    if (!callStack.empty() && !input.hasNext()){
      System.out.println("Invalid trace at line " + lineNumber);
      System.out.println("Not all functions returned");
      System.out.println("Stack trace:");
      for (int i=0; i < depth; i++){
        System.out.println(callStack.pop().substring(5));
      }
    }
  }
}

