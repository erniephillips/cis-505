/*
    Author: Ernest Phillips III
    Date: 09/14/2021
    Purpose: Bag class inherits from superclass Product with property type
*/
public class Bag extends Product {
  private String type = ""; // specify the number of bowling balls the bag can hold

  public Bag() {
    super();
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return 
      super.toString() +
      "Type:  " + getType() + "\n\n";
  }

}
