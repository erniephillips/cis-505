/*
    Author: Ernest Phillips III
    Date: 09/13/2021
    Purpose: Class represents an account object for customer accessing their account
*/

import java.text.SimpleDateFormat;
import java.util.Date;

public class Account {
  private double balance = 200.00; //set a default customer balance

  public void deposit(double amt){ //customer makes a deposit
    balance += amt; //increment the balance when called
  }

  public void withdraw(double amt){ //customer makes a withdraw
    if(balance > amt) //check customer has enough to withdraw
      balance -= amt; //decrement the balance when called
    //really should return an error to customer here (bad logic to do nothing)
  }

  public void displayMenu(){ //feature a prompt to the user
    System.out.println("Account Menu\n" +
      "Enter <D/d> for deposit\n" +
      "Enter <W/w> for withdrawl\n" +
      "Enter <B/b> for balance\n" +
      "\tEnter option>:");
  }

  public String getTransactionDate(){ //post the current time when this method is called
    return new SimpleDateFormat("MM-dd-yyyy").format(new Date());
  }

  public double getBalance() {
    return this.balance;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

}
