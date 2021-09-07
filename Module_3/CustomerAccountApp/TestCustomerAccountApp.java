
/*
    Regex expression found at: https://stackoverflow.com/questions/3681242/java-how-to-parse-double-from-regex posted by Brian
    Author: Ernest Phillips III
    Date: 09/13/2021
    Purpose: Main class to run the three object classes we created
*/
import java.util.Scanner;

public class TestCustomerAccountApp {
  public static void main(String args[]) {
    Scanner scanner = new Scanner(System.in); // create new input reader object
    Account account = new Account(); // instantiate the account object which will be used 'x' times during loop

    String promptEntry = ""; // store string entry for user decision on proceeding with program

    // display welcome message with a bolded text
    System.out.println("\033[1mWelcome to the Customer Account App\033[0m");

    // prompt the user for a customer id between 1007 - 1009
    System.out.println("Enter a customer ID between 1007 - 1009:");
    String customerID = scanner.nextLine(); // read input
    while (!customerID.matches("[1-9][0-9][0-9][0-9]")) { // check that input is number between 1 and 9999 using regex
      System.out.println("Error: Invalid Option.");
      System.out.println("Enter a customer ID between 1007 - 1009:");
      customerID = scanner.nextLine();
    }

    // get the customer info from the static mock database file
    Customer customer = CustomerDB.getCustomer(Integer.parseInt(customerID)); // store in a customer obj

    do {
      // let the user know whose account they are working with
      System.out.println("Account information for \033[1m" + customer.getName() + "\033[0m");
      // display the account obj prompt
      account.displayMenu();

      // store indexed string character for user's decision on banking account
      // transactions
      // grab the first character and set to lowercase for switch below
      char accountOptionSelected = scanner.nextLine().toLowerCase().charAt(0);
      String amount; // store the withdrawl and deposit amounts as a string

      switch (accountOptionSelected) { // run a switch on the char entered
        case 'd': // user deposit
          System.out.println("Enter deposit amount:  ");
          amount = scanner.nextLine(); // get amount
          while (!amount.matches("[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?")) { // check numeric and double
            System.out.println("Error: Invalid Value.");
            System.out.println("Enter deposit amount:  ");
            amount = scanner.nextLine();
          }
          account.deposit(Double.parseDouble(amount));
          break;
        case 'w': // user withdrawl
          System.out.println("Enter withdrawl amount:  ");
          amount = scanner.nextLine();
          while (!amount.matches("[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?")) {
            System.out.println("Error: Invalid Value.");
            System.out.println("Enter withdrawl amount:  ");
            amount = scanner.nextLine();
          }
          account.withdraw(Double.parseDouble(amount));
          break;
        case 'b': // output the balance
          System.out.printf("\nBalance as of " + account.getTransactionDate() + " is $%,6.2f", account.getBalance());
          break;
        default:
          System.out.println("Error: Invalid Option.");
          break;
      }

      System.out.println("\nContinue (y/n):"); // output text
      promptEntry = scanner.nextLine(); // read if user wishes to continue
    } while (promptEntry.toLowerCase().equals("y"));
    // output the customer's bank info
    System.out.println("--Customer Details--");
    System.out.println(customer.toString());// output the user's personal information
    //output the user's bank balance and date
    System.out.printf("\nBalance as of " + account.getTransactionDate() + " is $%,6.2f", account.getBalance());
    scanner.close(); // tell GC to dispose of the object for memory management
  }
}
