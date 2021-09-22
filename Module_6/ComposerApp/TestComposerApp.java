import java.util.List;
import java.util.Scanner;

/*
    Author: Ernest Phillips III
    Date: 09/21/2021
    Purpose: Main class to run functionality of other classes
*/

public class TestComposerApp {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in); // create a scanner object to read user input in console
    int optionSelection = ValidatorIO.getInt(scanner, displayMenu());
    MemComposerDao mcd = new MemComposerDao();
    while (optionSelection == 1 || optionSelection == 2 || optionSelection == 3) { // user wishes to do something
      switch (optionSelection) {
        case 1:
          List<Composer> composers = mcd.findAll(); // get current list of users in memory
          System.out.println("--DISPLAYING COMPOSER--");
          for (Composer composer : composers) { //loop through list
            System.out.println(composer.toString()); // output the current composer
          }
          break;
        case 2:
          int composerId = ValidatorIO.getInt(scanner, "\nEnter the composer id to be searched:  ");
          Composer composer = mcd.findBy(composerId); //find the composer
          System.out.println("--DISPLAYING COMPOSER--"); //out
          System.out.println(composer.toString());
          break;
        case 3:
          Composer comp = new Composer(); //create new composer instance
          comp.setId(ValidatorIO.getInt(scanner, "\nEnter an id:  ")); //get the id through validator class
          comp.setName(ValidatorIO.getString(scanner, "Enter a name:  ")); //get name
          comp.setGenre(ValidatorIO.getString(scanner, "Enter a genre:  ")); //get genre
          mcd.insert(comp); //insert the composer
          break;
      }
      optionSelection = ValidatorIO.getInt(scanner, displayMenu());
    }
    scanner.close(); // close object, save on memory
  }

  public static String displayMenu() {
    return "\nMENU OPTIONS\n" + "\t1.  View Composers\n" + "\t2.  Find Composer\n" + "\t3.  Add Composer\n"
        + "\t4.  Exit\n" + "\nPlease choose an option:  ";
  }
}
