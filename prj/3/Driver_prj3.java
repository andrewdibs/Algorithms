
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
        System.out.println(tree.encrypt(line.substring(3, line.length()-1)));
      }
      else if(command == 'd'){
        System.out.println(tree.decrypt(line.substring(3, line.length()-1)));
      }
      else if(command == 'p'){
        tree.printPreorder();
      }
      else if(command == 'q'){
        System.exit(0);
      }
    }
  }
}