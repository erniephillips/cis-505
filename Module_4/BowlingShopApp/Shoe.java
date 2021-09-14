/*
    Author: Ernest Phillips III
    Date: 09/14/2021
    Purpose: Shoe class inherits from superclass Product with property size
*/
public class Shoe extends Product {
  private double size = 0; // specifies the size of the shoes

  public Shoe() {
  }

  public double getSize() {
    return this.size;
  }

  public void setSize(double size) {
    this.size = size;
  }


  @Override
  public String toString() {
    return 
      super.toString() +
      "Size:  " + getSize() + "\n\n";
  }

}
