import java.util.Scanner;
class Driver_prj4{
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
      else if (command == 'l'){
        tree.printLevelOrder();
      }
      else if(command == 'q'){
        System.exit(0);
      }
    }
  }
}