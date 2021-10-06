
/*
    Author: Ernest Phillips III
    Date: 10/06/2021
    Purpose: Main application file that interfaces between user and excel file for adding/retrieving grades
*/
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class PhillipsGradeBookApp extends Application {
  // set variables for the GUI box (scene) size
  final double initialSceneWidth = 520;
  final double initialSceneHeight = 470;

  // declare all needed variables
  // Alert box for save
  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

  // labels to appear in form (informational)
  private Label lblFirstName = new Label("First Name:");
  private Label lblLastName = new Label("Last Name:");
  private Label lblCourse = new Label("Course:");
  private Label lblGrade = new Label("Grade:");
  private Label lblResults = new Label("View Results:");

  // textfields (identify the textboxes)
  private TextField txtFirstName = new TextField();
  private TextField txtLastName = new TextField();
  private TextField txtCourse = new TextField();

  // textarea
  private TextArea txtResults = new TextArea();

  // dropdown
  private ComboBox<String> cbGrades = new ComboBox<String>();

  // buttons
  private Button btnClear = new Button("Clear");
  private Button btnView = new Button("View Grades");
  private Button btnSave = new Button("Save Entry");

  // gridpane to include all above controls in a concise layout
  GridPane gridPane = new GridPane();

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) { // stage is the setting of the scene, now lets tell the actors what to do
    // how we will setup the grid pane
    gridPane.setAlignment(Pos.CENTER); // center it
    gridPane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5)); // apply a padding (clockwise) top | right | bottom | left
    gridPane.setHgap(5.5); // set the top gap
    gridPane.setVgap(5.5); // set the bottom gap

    // set alert
    alert.setTitle("Record Info");
    alert.setHeaderText(null);
    alert.setContentText("Your record has successfully saved.");
    // I noticed that the default alert has ok/cancel. To remove cancel, must
    // explicitly declare buttons wanted
    ButtonType buttonTypeOk = new ButtonType("Ok", ButtonData.OK_DONE);
    alert.getButtonTypes().setAll(buttonTypeOk);

    // add controls to gridpane when adding to the gridpane.add
    // (Node, columnIndex, rowIndex, colspan, rowspan);

    // First Name
    gridPane.add(lblFirstName, 1, 1); // add first name label with a col and row index
    txtFirstName.setMaxWidth(350); // set max widths, otherwise very large
    gridPane.add(txtFirstName, 2, 1); // add the textbox
    GridPane.setHalignment(txtFirstName, HPos.RIGHT); // set the alignment of the textbox to the right

    // Last Name
    gridPane.add(lblLastName, 1, 2); // same as above comments but for last name
    txtLastName.setMaxWidth(350);
    gridPane.add(txtLastName, 2, 2);
    GridPane.setHalignment(txtLastName, HPos.RIGHT);

    // Course
    gridPane.add(lblCourse, 1, 3); // same as above comments but for course
    txtCourse.setMaxWidth(350);
    gridPane.add(txtCourse, 2, 3);
    GridPane.setHalignment(txtCourse, HPos.RIGHT);

    // Grade
    gridPane.add(lblGrade, 1, 4); // add the label for the grades drop down
    // create a default list for drop // down
    List<String> listGrades = new ArrayList<String>(Arrays.asList("A", "B", "C", "D", "F"));
    cbGrades.getItems().addAll(listGrades); // add the list of grades to the dropdown
    gridPane.add(cbGrades, 2, 4); // add drop down to grid
    gridPane.setHalignment(cbGrades, HPos.RIGHT);

    HBox actionBtnContainer = new HBox(); // used to lay out children in a single horizontal row
    actionBtnContainer.setPadding(new Insets(15, 0, 15, 30)); // set the padding
    actionBtnContainer.setSpacing(10); // set the spacing between controls
    actionBtnContainer.getChildren().add(btnClear); // add the clear button
    actionBtnContainer.getChildren().add(btnView); // add the view grades button
    actionBtnContainer.getChildren().add(btnSave); // add the save grade entry button
    btnClear.setOnAction(e -> { // anonymous Lambda function to clear all fields
      txtFirstName.clear();
      txtLastName.clear();
      txtCourse.clear();
      txtResults.setText("");
      cbGrades.setValue("");
    });
    btnSave.setOnAction(e -> {
      try {// store the textbox/combobox values in a student object and pass to insert
           // method
        RecordsIO.InsertRecord(new Student() {
          {
            setFirstName(txtFirstName.getText());
            setLastName(txtLastName.getText());
            setCourse(txtCourse.getText());
            setGrade(cbGrades.getValue());
          }
        });
        alert.showAndWait();
      } catch (IOException e1) {
        e1.printStackTrace();
      }
    });
    btnView.setOnAction(e -> {
      try { // retrieve list of student records and output to text area
        List<Student> students = RecordsIO.DisplayRecords();
        String records = "";
        if (students != null) {
          for (Student student : students) {
            records += student.toString() + "\n"; // append string of results
          }
          txtResults.setText(records); // set the textbox
        }
      } catch (IOException e1) {
        e1.printStackTrace();
      }
    });
    gridPane.add(actionBtnContainer, 2, 5, 2, 1); // add the container to the grid pane
    GridPane.setHalignment(actionBtnContainer, HPos.RIGHT);

    gridPane.add(lblResults, 1, 6, 2, 1); // add the output which will be used in module 8 to output text
    gridPane.add(txtResults, 1, 7, 2, 1); // add the textarea which will output results

    // create a scene and place it in the stage
    Scene scene = new Scene(gridPane, initialSceneWidth, initialSceneHeight);
    scene.getStylesheets().add("/css/stylesheet.css"); // add the custom stylesheet
    primaryStage.setTitle("Phillips Grade Book App");// set the stage title
    primaryStage.setScene(scene);// place the scene in the stage
    primaryStage.show(); // display the stage
  }

  public static void main(String[] args) { // main method to launch application
    Application.launch(args);
  }
}