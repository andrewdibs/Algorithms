/**
   * file: Driver_prj3.java
   * author: Andrew DiBella
   * course: CMPT 435
   * assignment: project 3
   * due date: April 4, 2020
   * version: 1.0
   * 
   * This file contains the main driver for 
   * my implementation of the Binary Search 
   * Encryption tree.
   */



import java.util.Scanner;
class Driver_prj3{
  public static void main(String [] args){
    Scanner input = new Scanner(System.in);
    EncryptionTree tree = new EncryptionTree();

    while (input.hasNext()){
      String line = input.nextLine();
      char command = line.charAt(0);

      if (command == 'i'){
        tree.insert(line.substring(2));
      }
      else if(command == 'r'){
        tree.remove(line.substring(2));
      }
      else if(command == 'e'){
        String[] encrypted = line.split("'");
        String[] paths = encrypted[1].split(" ");
        for (String path: paths)
          System.out.print(tree.encrypt(path)+ " ");
        System.out.println();
      }
      else if(command == 'd'){
        String[] decrypted = line.split("'");
        String[] paths = decrypted[1].split(" ");
        for (String path: paths)
          System.out.print(tree.decrypt(path) + " ");
        System.out.println();
      }
      else if(command == 'p'){
        tree.printPreorder();
      }
      else if(command == 'q'){
        System.exit(0);
      }
    }
    input.close();
  }
}