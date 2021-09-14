/*
    Author: Ernest Phillips III
    Date: 09/14/2021
    Purpose: Ball class inherits from superclass Product with property color
*/
public class Ball extends Product {
  private String color = "";

  public Ball() {
    super();
  }

  public String getColor() {
    return this.color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  @Override
  public String toString() {
    return 
      super.toString() + 
      "Color: " + getColor() + "\n\n";
  }
}
