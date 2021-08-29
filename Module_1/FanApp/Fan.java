/*
    Author: Ernest Phillips III
    Date: 08/28/2021
    Purpose: Create a fan class that defines the properties of the fan object and possible actions
*/
public class Fan {
    //declare all variables
    //set as static so all like objects can access the fan's speeds
    /*****(better set as a strongly-typed object) or for memory purposes as bitmasks */
    public static final int STOPPED = 0, SLOW = 1, MEDIUM = 2, FAST = 3; //set constants for fan speed 
    private int speed = STOPPED; //set current speed to stopped
    private boolean isOn = false; //var for check of fan status
    private double radius = 6; //var for fan size
    private String color = "white"; //var for fan color


    /*set parameterless constructor that default values for each fan object*/
    public Fan(){ //isn't this redundant since the assignment specified setting the vars as they were declared??
        this.speed = STOPPED;
        this.isOn = false;
        this.radius = 6;
        this.color = "white";
    }

    /* constructor allowing custom fan object settings when instantiated. NOTE: must 
    declare all four during object creation to hit this constructor */
    public Fan(int speed, boolean isOn, double radius, String color){
        this.speed = speed;
        this.isOn = isOn;
        this.radius = radius;
        this.color = color;
    }

    /*getter setter methods - this operator refers to object's instance variables above*/
    public int getSpeed(){//get the fan speed
        return this.speed;
    }
    public void setSpeed(int speed){ 
        //set the fan speed if within range as dictated by ternary operator
        this.speed = speed < 0 || speed > 4 ? 0 : speed;
    }

    public boolean getStatus(){ //get the fan's status
        return this.isOn;
    }
    public void setStatus(boolean status){ //set the fan's status
        this.isOn = status; //could also be auto resolved with a ternary on/off switch and remove the status param
    }

    public double getRadius(){ //get the radius
        return this.radius;
    }
    public void setRadius(double radius){ //set the radius
        this.radius = radius;
    }

    public String getColor(){
        return this.color;
    }
    public void setColor(String color){
        this.color = color;
    }

    @Override //creation of override toString method that can be called from another class
    public String toString(){
        String fanStatus = this.isOn ? "on" : "off"; //set boolean to custom text
        if(this.isOn)
            return "The fan speed is set to " + this.speed + " with a color of " + this.color + " and a radius of " + this.radius;
        else
            return "The fan is " + this.color + " with a radius of " + this.radius + " and the fan is " + fanStatus;
    }
}
