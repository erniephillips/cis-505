/*
    Author: Ernest Phillips III
    Date: 09/21/2021
    Purpose: Composer object class representing each composer
*/

public class Composer{
  private int id = 0;
  private String name = "";
  private String genre = "";

  public Composer(){}

  public Composer(int id, String name, String genre) {
    this.id = id;
    this.name = name;
    this.genre = genre;
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getGenre() {
    return this.genre;
  }

  public void setGenre(String genre) {
    this.genre = genre;
  }

  @Override
  public String toString() {
    return "\nId:  " + getId() + "\n" +
      "Name:  " + getName() + "\n" +
      "Genre:  " + getGenre() + "\n";
  }


}