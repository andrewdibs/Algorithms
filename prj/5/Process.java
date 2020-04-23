import java.util.Scanner;

/* A Process object represents a batch-executed computer process, which has a
 * hard real-time deadline before which it must finish. It also has an amount of
 * time that it requires to execute.
 *
 * A Process object has a (unique) id, which starts at 0 and should increment
 * whenever a new process is created. It is up to the operating system to assign
 * it an ID.
 *
 * Finally, a Process object has a data member "information", which is printed
 * when the run() method executes.
 *
 * The default constructor for the Process initializes all the data members to
 * reasonable defaults, and the id to the given ID (default is 0).
 *
 *
 * The canComplete method returns true if the method would be able to complete
 * if it starts now, or false if it could not finish by its deadline.
 *
 *
 * The streamIn() method is used to read in the deadline, requiredTime, and
 * information from a Scanner input stream.
 */
class Process {
  private int id;
  private int deadline;
  private int requiredTime;
  private String information;

  public Process(int theId) {
    // - default constuctor for a new process
    this.id = theId;
    this.deadline = 0;
    this.requiredTime = 0;
    this.information = "";
  }

  public int run(int currentSystemTime) {
    // - prints proccess information and updates systemTime
    System.out.println(information);
    return currentSystemTime + requiredTime;
  }

  public boolean canComplete(int currentSystemTime) {
    // - returns true if the process is able to complete by the deadline
    return currentSystemTime + requiredTime <= deadline; 
  }

  public int getId() {
    // - returns this id attribute
    return this.id;
  }

  public boolean isLess(Process p) {
    // - is less checks if this deadline, requiredTime, or id is less than p's
    if (this.deadline < p.deadline) return true;
    else if (this.deadline == p.deadline){
      if (this.requiredTime < p.requiredTime) return true;
      else if(this.requiredTime == p.requiredTime){
        if (this.id < p.id) return true;
      } 
    }
    return false; 
  }

  public void streamIn(Scanner input) {
    // - streams in a new process object
    this.deadline = input.nextInt();
    this.requiredTime = input.nextInt();
    this.information = input.nextLine().substring(1);

  }
}

