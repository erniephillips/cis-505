/*
    Author: Ernest Phillips III
    Date: 09/04/2021
    Purpose: Create a Team object class allowing addition of teams with a max of 20
*/
public class Team{
  private String teamName = "";
  private String[] players = new String[20];
  private int playerCount = 0;

  public Team(String teamName) {
    this.teamName = teamName;
  }

  public void addPlayer(String player){
    players[playerCount] = player;
    playerCount++;
  }

  public String[] getPlayers(){
    return this.players;
  }

  public int getPlayerCount(){
    return this.playerCount;
  }

  public String getTeamName(){
    return this.teamName;
  }  
}