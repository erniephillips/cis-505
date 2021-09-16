/*
    Author: Ernest Phillips III
    Date: 09/13/2021
    Purpose: Product class of type Super that will be consumed by child classes
*/
public class Product {
  private String code = "";
  private String description = "";
  private double price = 0;

  public Product() { } //default constructor

  public Product(String code, String description, double price) { //parameterized constructor
    this.code = code;
    this.description = description;
    this.price = price;
  }

  //getter / setter methods
  public String getCode() {
    return this.code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public double getPrice() {
    return this.price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public Product code(String code) {
    setCode(code);
    return this;
  }
 

  @Override
  public String toString() {
    return 
      "Product Code:  " + getCode() + "\n" +
      "Description:  " + getDescription() + "\n" +
      "Price:  $%,6.2f\n";
  }
  
}