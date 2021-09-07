/*
    Author: Ernest Phillips III
    Date: 09/13/2021
    Purpose: Class for object: Customer. Accessor methods for private instance variables
*/
public class Customer{
  //declare customer attributes
  private String name;
  private String address;
  private String city;
  private String zip;

  //declare an empty constructor (no defaults were stated in assingment???)
  public Customer() {
  }

  //delcare constructor for user to intantiate with four values
  public Customer(String name, String address, String city, String zip) {
    this.name = name;
    this.address = address;
    this.city = city;
    this.zip = zip;
  }

  //create accessor/mutator methods for above attributes
  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return this.address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getCity() {
    return this.city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getZip() {
    return this.zip;
  }

  public void setZip(String zip) {
    this.zip = zip;
  }

  @Override
  public String toString() {
    return "Name:  " + getName() + "\n" +
      "Address:  " + getAddress() + "\n" +
      "City:  " + getCity() + "\n" +
      "Zip:  " + getZip() + "\n";
  }

  
}