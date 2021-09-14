
/*
    Author: Ernest Phillips III
    Date: 09/14/2021
    Purpose: Main class: test super and sub classes along with static item creation class and generic manipulation class
*/
import java.util.Scanner;

public class TestBowlingShopApp {
  public static void main(String[] args) {
    String menuResponse = ""; // set the menu response for do/while
    Scanner input = new Scanner(System.in); // create new input reader object
    do {
      System.out.print(displayMenu());// output the menu to the user
      menuResponse = input.next(); // read user's input
      GenericQueue<Product> products = ProductDB.getProducts(menuResponse); // assign the results to the generic queue object
      System.out.println("--Product Listing--");
      while (products.size() > 0) {// output the list of objects
        Product prod = products.dequeue(); //had to move dequeue to store returned product into varible for dual access in printf
        System.out.printf(prod.toString(), prod.getPrice()); //display the product's to string and it's super's tostring, use formatted money
      }
    } while (!menuResponse.equals("x")); //run loop until user specifies 'x'
    input.close(); // close the scanner object
  }

  public static String displayMenu() { // method must be static in order to be referenced from main method
    return 
      "MENU OPTIONS\n" + 
      "\t1.  <b>  Bowling Balls\n" + 
      "\t2.  <a>  Bowling Bags\n" + 
      "\t3.  <s>  Bowling Balls\n" + 
      "\t4.  <x>  To exit" + 
      "\n\nPlease choose an option:  ";
  }
}
