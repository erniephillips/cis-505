/*
    Author: Ernest Phillips III
    Date: 09/21/2021
    Purpose: Main class for application execution
*/
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class TestExpenseTracker {
  public static void main(String[] args) throws IOException {
    Scanner scanner = new Scanner(System.in); // create new scanner object
    String continueProgram = "y";

    while (continueProgram.equalsIgnoreCase("y")) {
      // pass the user's input and display menu to the user;
      int input = ValidatorIO.getInt(scanner, displayMenu());

      if (input == 1) {// View Transactions
        ArrayList<Transaction> transactions = TransactionIO.findAll(); // find all transactions stored in txt file
        System.out.println("MONTHLY EXPENSES");
        for (Transaction tran : transactions) { // iterate through each transaction
          System.out.printf(tran.toString(), tran.getAmount()); // output the overridden toString method of the Transaction class
        }
      } else if (input == 2) { // Add Transactions
        String addAnotherTransaction = "y"; // set user prompt for iterating over adding actions
        ArrayList<Transaction> transactions = new ArrayList<>(); // create new array list

        while (addAnotherTransaction.equalsIgnoreCase("y")) { // loop until not 'y'
          String description = ValidatorIO.getString(scanner, "\n  Enter the description: "); // get the trans desc
          double amount = ValidatorIO.getDouble(scanner, "  Enter the amount: "); // get the trans amount

          // create a new transaction with the desc, amount, and current date
          Transaction transaction = new Transaction(description, amount,
              new SimpleDateFormat("MM-dd-yyyy").format(Calendar.getInstance().getTime()));

          // add to trans list
          transactions.add(transaction);

          // prompt user again
          addAnotherTransaction = ValidatorIO.getString(scanner, "\n  Add another transaction (y/n): ");
        }
        try {// once user is finished with adding transactions, input to file
          TransactionIO.bulkInsert(transactions);
        } catch (IOException ex) {
          System.out.println("\n  Exception: " + ex.getMessage());
        }
      } else if (input == 3) {// View Expense
        double totalAmount = 0;
        ArrayList<Transaction> transactions = TransactionIO.findAll(); // find all transactions stored in txt file
        for (Transaction tran : transactions) { // iterate through each transaction
          totalAmount += tran.getAmount(); // output the overridden toString method of the Transaction class
        }
        System.out.printf("\nYour total monthly expense is $%,6.2f\n", totalAmount);
      }

      continueProgram = ValidatorIO.getString(scanner, "\nContinue (y/n):  ");
    }
    scanner.close();
  }

  public static String displayMenu() {
    return "MENU OPTIONS\n" + "\t1.  View Transactions" + "\n\t2.  Add Transactions" + "\n\t3.  View Expense\n\n"
        + "Please choose an option:  ";
  }
}
