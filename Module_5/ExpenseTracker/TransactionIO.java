/*
    Author: Ernest Phillips III
    Date: 09/20/2021
    Purpose: Class to output transactions to a dedicated file in the same location as code execution
*/

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class TransactionIO {
  // variables were not listed as static in the assignment but had to be converted
  // to work with the static method bulkInsert assignment calls for
  private static final String FILE_NAME = "expenses.txt"; // unchanging string value
  private static File file = new File(FILE_NAME); // create a new file using const name

  public static void bulkInsert(ArrayList<Transaction> transactions) throws IOException {
    PrintWriter output = null; // create new object that will write to file
    if (file.exists()) {
      // if no file, create one using file_name. True states that exception for the
      // FileNotFoundException or SecurityException
      output = new PrintWriter(new FileOutputStream(new File(FILE_NAME), true));
    } else {
      output = new PrintWriter(FILE_NAME); // call existing file
    }

    for (Transaction tran : transactions) { // write to the file for each transaction
      output.print(tran.getDate() + " ");
      output.print(tran.getDescription() + " ");
      output.println(tran.getAmount());
    }

    output.close(); // close the FileWriter to save on memory
  }

  public static ArrayList<Transaction> findAll() throws IOException{
    //Create the List to store read transactions
    ArrayList<Transaction> transactions = null;
    if(file.exists()){
      Scanner input = new Scanner(new File(FILE_NAME));
      transactions = new ArrayList<Transaction>();
      while (input.hasNext()){
        transactions.add(new Transaction(){{setDescription(input.next()); setDate(input.next()); setAmount(Double.parseDouble(input.nextLine()));}});
      }
    }else{
      return new ArrayList<>();
    } 
    return transactions;
  }
}
