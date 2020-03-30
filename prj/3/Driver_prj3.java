
import java.util.Scanner;
class Driver_prj3{
  public static void main(String [] args){
    Scanner input = new Scanner(System.in);
    EncryptionTree tree = new EncryptionTree();

    while (input.hasNext()){
      String line = input.nextLine();
      char command = line.charAt(0);

      if (command == 'i'){
        System.out.println("insert");

        tree.insert(line.substring(2));
      }
      else if(command == 'r'){
        tree.remove(line.substring(2));
      }
      else if(command == 'e'){
        tree.encrypt(line.substring(3, line.length()-1));
      }
      else if(command == 'd'){
        tree.decrypt(line.substring(3, line.length()-1));
      }
      else if(command == 'p'){
        System.out.println("print");
        //tree.printPreorder();
      }
      else if(command == 'q'){
        System.exit(0);
      }
    }
  }
}