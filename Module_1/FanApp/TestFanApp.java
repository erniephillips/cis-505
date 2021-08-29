/*
    Author: Ernest Phillips III
    Date: 08/29/2021
    Purpose: Create fan objects and output the results 
*/
public class TestFanApp {
    // main method
    public static void main(String[] args) {
        //instantiate two instances of the fan object in the fan class
        Fan fan1 = new Fan();//use default constructor
        Fan fan2 = new Fan(Fan.MEDIUM, true, 8.0, "blue"); //use overloaded constructor

        //output custom overridden toString method
        System.out.println(fan1.toString());
        System.out.println(fan2.toString());
    }
}
