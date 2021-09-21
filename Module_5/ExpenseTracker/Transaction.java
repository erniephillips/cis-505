package ExpenseTracker;
/*
    Author: Ernest Phillips III
    Date: 09/20/2021
    Purpose: Transaction object class representing each transaction
*/
public class Transaction{ //declare vars
  private String date = "MM-dd-yyyy";
  private String description = "";
  private double amount = 0;


  //default and overloaded constructors
  public Transaction() {
  }

  public Transaction(String date, double amount, String description) {
    this.date = date;
    this.description = description;
    this.amount = amount;
  }

  //getter/setters
  public String getDate() {
    return this.date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public double getAmount() {
    return this.amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  @Override
  public String toString() {
    return
      "Date:  " + getDate() + "\n" +
      "Description:  " + getDescription() + "\n" +
      "Amount:  " + getAmount() + "\n";
  }

}