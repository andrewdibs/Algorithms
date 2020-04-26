import java.util.Scanner;


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

