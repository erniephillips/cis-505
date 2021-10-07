
/*
    Author: Ernest Phillips III
    Date: 10/06/2021
    Purpose: file writing class. Only inserts a record and retrieves a list of all records
*/
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class RecordsIO {
  private static final String FILE_NAME = "grades.csv"; // unchanging string value
  private static File file = new File(FILE_NAME); // create a new file using const name

  public static void InsertRecord(Student student) throws IOException {
    PrintWriter output = null; // create new object that will write to file
    StringBuffer csvHeader = new StringBuffer(""); // output to the header
    StringBuffer csvData = new StringBuffer(""); // output to the body
    if (file.exists()) {
      // if no file, create one using file_name. True states that exception for the
      // FileNotFoundException or SecurityException
      output = new PrintWriter(new FileOutputStream(new File(FILE_NAME), true));
    } else {
      output = new PrintWriter(FILE_NAME); // call existing file
      csvHeader.append("First Name,Last Name,Course,Grade"); // write the header
      output.write(csvHeader.toString());
    }

    // write the body
    output.append("\n" + student.getFirstName() + "," + student.getLastName() + "," + student.getCourse() + ","
        + student.getGrade());
    output.write(csvData.toString());

    output.close(); // close the FileWriter to save on memory
  }

  public static void BulkInsertRewrite(ArrayList<Student> students) throws IOException {
    PrintWriter output = null; // create new object that will write to file
    StringBuffer csvHeader = new StringBuffer(""); // output to the header
    StringBuffer csvData = new StringBuffer(""); // output to the body

    output = new PrintWriter(new FileOutputStream(new File(FILE_NAME), false)); // set to false so file is overwritten

    csvHeader.append("First Name,Last Name,Course,Grade"); // write the header
    output.write(csvHeader.toString());
    for (Student student : students) {
      // write the body
      output.append("\n" + student.getFirstName() + "," + student.getLastName() + "," + student.getCourse() + ","
          + student.getGrade());
    }

    output.write(csvData.toString()); // write to file
    output.close(); // close the FileWriter to save on memory
  }

  public static ArrayList<Student> DisplayRecords() throws IOException {
    // open file input stream
    BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));

    // read file line by line
    String line = null;
    Scanner scanner = null;
    int index = 0; // set delimiter index
    ArrayList<Student> studentList = new ArrayList<>(); // net list

    while ((line = reader.readLine()) != null) { // read each line
      Student student = new Student();
      scanner = new Scanner(line);
      scanner.useDelimiter(","); // set the delimiter char
      while (scanner.hasNext()) { // read each column value
        String data = scanner.next(); // store data sequentially
        if (index == 0)
          student.setFirstName(data);
        else if (index == 1)
          student.setLastName(data);
        else if (index == 2)
          student.setCourse(data);
        else if (index == 3)
          student.setGrade(data);
        index++;
      }
      index = 0;
      studentList.add(student);
    }

    // close reader
    reader.close();
    studentList.remove(0); // remove the first row as it is the csv headers
    return studentList;
  }
}
