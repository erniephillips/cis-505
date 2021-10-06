/*
    Author: Ernest Phillips III
    Date: 10/06/2021
    Purpose: Student object for storing user input and file results
*/
public class Student {
  private String firstName = "";
  private String lastName = "";
  private String course = "";
  private String grade = "";

  public Student() {
  }

  public Student(String firstName, String lastName, String course, String grade) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.course = course;
    this.grade = grade;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getCourse() {
    return this.course;
  }

  public void setCourse(String course) {
    this.course = course;
  }

  public String getGrade() {
    return this.grade;
  }

  public void setGrade(String grade) {
    this.grade = grade;
  }

  @Override
  public String toString() {
    return "{" + " firstName='" + getFirstName() + "'" + ", lastName='" + getLastName() + "'" + ", course='"
        + getCourse() + "'" + ", grade='" + getGrade() + "'" + "}";
  }
}
