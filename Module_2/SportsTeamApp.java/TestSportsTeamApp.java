import java.util.Scanner;

/*
    Author: Ernest Phillips III
    Date: 09/04/2021
    Purpose: Main class to instantiate the team object and manipulate data as needed for this course
*/
public class TestSportsTeamApp {
  public static void main(String args[]) {
    Scanner scannerObj = new Scanner(System.in); //create input object for user entry
    System.out.println("Enter a team name"); //output text 
    String teamName = scannerObj.nextLine(); //receive user's entry for a team name and store in var. 
    String promptEntry = ""; //set instance variable for whether the user should continue adding more users
    Team team = new Team(teamName); //Define team obj outside scope of loop. Team class should only be instantiated once. Call constructor, creating new team with user's input
    do { //do while loop is most suitable here. Ensures block of code is ran at least once
      String playersReformatted = ""; //temp var for storing comma delimited users that will be output to user
      
      System.out.println("Enter the player names: \n\tHint: <use comma for multiple players; no spaces> i.e.: Aaron,Allen,Smith"); //output text
      String playerNames = scannerObj.nextLine(); //read players name entries

      String[] players = playerNames.split(","); //split the comma delimited entries and store in an array for looping
      for (String player : players) { //loop through newly created array
        //TODO: proper methodology would check array size != 20 before attempting to store to avoid breaking application
        team.addPlayer(player); //store each player on the team via the addPlayer method
      }

      System.out.println("\n\n--Team Summary--"); //output text
      System.out.println("Team Name:  " + team.getTeamName()); //out the team name
      System.out.println("Number of players in team:  " + team.getPlayerCount()); //output the total players on team
      for (String player : team.getPlayers()) { //loop back through array of players
        if (player != null) //only grab non-null array entries
          playersReformatted += player + ","; //concatenate inside string
      }

      System.out.println("Players on the team " + playersReformatted.replaceAll(",$", "")); //output the concatenated string of comma delimited players, removing trailing comma
      
      System.out.println("\n\nContinue? (y/n)"); //output text
      promptEntry = scannerObj.nextLine(); //prompt user if they wish to continue loop logic
    } while (promptEntry.toLowerCase().equals("y")); //if user wishes to continue, iterate through code under do {}
    scannerObj.close(); //close the scanner object
  }
}