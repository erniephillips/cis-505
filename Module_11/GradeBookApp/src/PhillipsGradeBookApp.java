
/*
    Author: Ernest Phillips III
    Date: 10/06/2021
    Purpose: Main application file that interfaces between user and excel file for adding/retrieving grades
*/
import java.beans.EventHandler;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Parameter;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

public class PhillipsGradeBookApp extends Application {
  // set variables for the GUI box (scene) size
  final double initialSceneWidth = 520;
  final double initialSceneHeight = 700;

  // create an observable to stream to table
  // table will subscribe to observable listener
  ObservableList<Student> obsListStudents = null;

  // declare all needed variables
  // Tab Pane
  TabPane tabPane = new TabPane();
  Tab tab1 = new Tab();
  Tab tab2 = new Tab();
  Tab tab3 = new Tab();
  Tab tab4 = new Tab();

  // Alert box for save
  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
  Alert alertError = new Alert(Alert.AlertType.CONFIRMATION);

  // labels to appear in form (informational)
  private Label lblFirstName = new Label("First Name:");
  private Label lblFirstNameError = new Label("*");
  private Label lblLastName = new Label("Last Name:");
  private Label lblLastNameError = new Label("*");
  private Label lblCourse = new Label("Course:");
  private Label lblCourseError = new Label("*");
  private Label lblGrade = new Label("Grade:");
  private Label lblGradeError = new Label("*");
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
  private Button btnDownload = new Button("Download CSV");

  // testing table view
  private TableView table = new TableView();

  // gridpane to include all above controls in a concise layout
  GridPane gridPane = new GridPane();

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) { // stage is the setting of the scene, now lets tell the actors what to do
    // how we will setup the grid pane
    gridPane.setAlignment(Pos.CENTER); // center it
    gridPane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5)); // apply a padding (clockwise) top | right | bottom | left
    gridPane.setHgap(5.5); // set the top gap
    gridPane.setVgap(5.5); // set the bottom gap

    // set error css
    lblFirstNameError.getStyleClass().add("label-red");
    lblLastNameError.getStyleClass().add("label-red");
    lblCourseError.getStyleClass().add("label-red");
    lblGradeError.getStyleClass().add("label-red");

    // set labels to hidden until error validaiton error occurrs
    lblFirstNameError.setVisible(false);
    lblLastNameError.setVisible(false);
    lblCourseError.setVisible(false);
    lblGradeError.setVisible(false);

    // set alert
    alert.setTitle("Record Info");
    alert.setHeaderText(null);
    alert.setContentText("Your record has successfully saved.");
    // I noticed that the default alert has ok/cancel. To remove cancel, must
    // explicitly declare buttons wanted
    ButtonType buttonTypeOk = new ButtonType("Ok", ButtonData.OK_DONE);
    alert.getButtonTypes().setAll(buttonTypeOk);

    // set error alert
    alertError.setTitle("ERROR");
    alertError.setHeaderText(null);
    alertError.setContentText("Error: Please fill out all fields indicated by an (*) astric");
    alertError.getButtonTypes().setAll(buttonTypeOk);

    // add controls to gridpane when adding to the gridpane.add
    // (Node, columnIndex, rowIndex, colspan, rowspan);

    // First Name
    gridPane.add(lblFirstName, 1, 1); // add first name label with a col and row index
    txtFirstName.setMaxWidth(350); // set max widths, otherwise very large
    gridPane.add(lblFirstNameError, 2, 1);
    gridPane.add(txtFirstName, 3, 1); // add the textbox
    GridPane.setHalignment(txtFirstName, HPos.RIGHT); // set the alignment of the textbox to the right

    // Last Name
    gridPane.add(lblLastName, 1, 2); // same as above comments but for last name
    txtLastName.setMaxWidth(350);
    gridPane.add(lblLastNameError, 2, 2);
    gridPane.add(txtLastName, 3, 2);
    GridPane.setHalignment(txtLastName, HPos.RIGHT);

    // Course
    gridPane.add(lblCourse, 1, 3); // same as above comments but for course
    txtCourse.setMaxWidth(350);
    gridPane.add(lblCourseError, 2, 3);
    gridPane.add(txtCourse, 3, 3);
    GridPane.setHalignment(txtCourse, HPos.RIGHT);

    // Grade
    gridPane.add(lblGrade, 1, 4); // add the label for the grades drop down
    // create a default list for drop // down
    List<String> listGrades = new ArrayList<String>(Arrays.asList("A", "B", "C", "D", "F"));
    cbGrades.getItems().addAll(listGrades); // add the list of grades to the dropdown
    gridPane.add(lblGradeError, 2, 4);
    gridPane.add(cbGrades, 3, 4); // add drop down to grid
    GridPane.setHalignment(cbGrades, HPos.RIGHT);

    HBox actionBtnContainer = new HBox(); // used to lay out children in a single horizontal row
    actionBtnContainer.setPadding(new Insets(15, 0, 15, 30)); // set the padding
    actionBtnContainer.setSpacing(10); // set the spacing between controls
    actionBtnContainer.getChildren().add(btnClear); // add the clear button
    actionBtnContainer.getChildren().add(btnView); // add the view grades button
    actionBtnContainer.getChildren().add(btnSave); // add the save grade entry button
    actionBtnContainer.getChildren().add(btnDownload); // add the download CSV button

    btnClear.setOnAction(e -> { // anonymous Lambda function to clear all fields
      txtFirstName.clear();
      txtLastName.clear();
      txtCourse.clear();
      txtResults.setText("");
      cbGrades.setValue("");
    });
    btnSave.setOnAction(e -> {
      // reset the validation and recheck
      lblFirstNameError.setVisible(false);
      lblLastNameError.setVisible(false);
      lblCourseError.setVisible(false);
      lblGradeError.setVisible(false);

      // store the textbox/combobox values in a student object and pass to insert
      // method
      try {
        if (isNullOrEmpty(txtFirstName.getText()) || isNullOrEmpty(txtLastName.getText())
            || isNullOrEmpty(txtCourse.getText()) || isNullOrEmpty(cbGrades.getValue())) {
          if (isNullOrEmpty(txtFirstName.getText()))
            lblFirstNameError.setVisible(true);
          if (isNullOrEmpty(txtLastName.getText()))
            lblLastNameError.setVisible(true);
          if (isNullOrEmpty(txtCourse.getText()))
            lblCourseError.setVisible(true);
          if (isNullOrEmpty(cbGrades.getValue()))
            lblGradeError.setVisible(true);
          alertError.showAndWait();
        } else {
          // insert the student record
          RecordsIO.InsertRecord(new Student() {
            {
              setFirstName(txtFirstName.getText());
              setLastName(txtLastName.getText());
              setCourse(txtCourse.getText());
              setGrade(cbGrades.getValue());
            }
          });
          alert.showAndWait();
        }
      } catch (IOException e1) {
        e1.printStackTrace();
      }
    });
    btnView.setOnAction(this::handleViewBtnClick); // call the view method defined below
    btnDownload.setOnAction(e -> {
      File file = new File("grades.csv"); // locate the project file
      FileChooser fileChooser = new FileChooser(); // instantiate the file chooser for user to choose save location
      fileChooser.setInitialFileName("grades.csv"); // set default file name
      // set the filter to csv only
      FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
      fileChooser.getExtensionFilters().add(extFilter); // add the filter to the file chooser
      File dest = fileChooser.showSaveDialog(primaryStage); // set dialog to show in primary stage
      try {
        // copy the csv file to the proposed location
        Files.copy(file.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
      } catch (IOException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
    });
    gridPane.add(actionBtnContainer, 2, 5, 2, 1); // add the container to the grid pane
    GridPane.setHalignment(actionBtnContainer, HPos.RIGHT);

    tab1.setText("Grading Form");
    tab1.setContent(gridPane);
    tabPane.getTabs().add(tab1);

    // create a scene and place it in the stage
    Scene scene = new Scene(tabPane, initialSceneWidth, initialSceneHeight);
    scene.getStylesheets().add("/css/stylesheet.css"); // add the custom stylesheet
    primaryStage.setTitle("Phillips Grade Book App");// set the stage title
    primaryStage.setScene(scene);// place the scene in the stage
    primaryStage.show(); // display the stage

  }

  private void handleViewBtnClick(ActionEvent event) {
    try {
      // retrieve list of student records and output to text area
      List<Student> students = RecordsIO.DisplayRecords();
      String records = "";

      // reset the columns in case user reclicks view button
      table.getColumns().clear();

      if (students != null) {
        table.setEditable(true);
        Callback<TableColumn, TableCell> cellFactory = new Callback<TableColumn, TableCell>() {
          public TableCell call(TableColumn p) {
            return new EditingCell();
          }
        };
        // set the column headers
        TableColumn firstNameCol = new TableColumn<Student, String>("First Name");
        TableColumn lastNameCol = new TableColumn("Last Name");
        TableColumn courseCol = new TableColumn("Course");
        TableColumn gradeCol = new TableColumn("Grade");
        TableColumn<Student, Student> remove = new TableColumn<>("Actions");
        TableColumn<Student, Student> edit = new TableColumn<>("Actions");

        // set the student object property mappings for observable list
        // firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        // firstNameCol.setOnEditCommit((CellEditEvent<Student, String> t) -> {
        // ((Student)
        // t.getTableView().getItems().get(t.getTablePosition().getRow())).setFirstName(t.getNewValue());
        // });
        firstNameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("firstName"));
        firstNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        firstNameCol.setOnEditCommit(new EventHandler<CellEditEvent<Student, String>>() {
          @Override
          public void handle(CellEditEvent<Student, String> t) {
            ((Student) t.getTableView().getItems().get(t.getTablePosition().getRow())).setFirstName(t.getNewValue());
          }
        });
        // firstNameCol.setOnEditCommit(new
        // EventHandler<TableColumn.CellEditEvent<Student, String>>() {
        // @Override
        // public void handle(TableColumn.CellEditEvent<SaleItem, Double> event) {
        // SaleItem saleItem =
        // event.getTableView().getItems().get(event.getTablePosition().getRow());
        // saleItem.setQuantity(event.getNewValue());
        // saleItem.updatePrice();
        // tv.refresh();
        // }
        // }
        // );

        lastNameCol.setCellValueFactory(new PropertyValueFactory("lastName"));
        courseCol.setCellValueFactory(new PropertyValueFactory("course"));
        gradeCol.setCellValueFactory(new PropertyValueFactory("grade"));

        remove.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        remove.setCellFactory(param -> new TableCell<Student, Student>() {
          private final Button deleteButton = new Button("X");

          @Override
          protected void updateItem(Student student, boolean empty) {
            super.updateItem(student, empty);

            if (student == null) {
              setGraphic(null);
              return;
            }

            setGraphic(deleteButton);
            deleteButton.setOnAction(event -> {
              getTableView().getItems().remove(student); // remove from the table
              // in theory this would hook into db and remove record. Not sure about an excel
              // conventional method to delete by indexed id so this is easy work around
              // try remove from stream and file bulk rewrite
              obsListStudents.remove(student);
              ArrayList<Student> students = new ArrayList<Student>(obsListStudents);
              try {
                RecordsIO.BulkInsertRewrite(students);
              } catch (IOException e) {
                e.printStackTrace();
              }
            });
          }
        });

        // create an observable list from the array list
        obsListStudents = FXCollections.observableArrayList(students);
        table.setItems(obsListStudents); // set the item population from list

        table.getColumns().addAll(firstNameCol, lastNameCol, courseCol, gradeCol, remove); // add the columns

        int a = 0, b = 0, c = 0, d = 0, f = 0;
        // loop through student records and increment grade counters accordingly
        // append student.toString to records string for output of textarea
        for (Student student : students) {
          switch (student.getGrade().toLowerCase()) {
            case "a":
              a++;
              break;
            case "b":
              b++;
              break;
            case "c":
              c++;
              break;
            case "d":
              d++;
              break;
            case "f":
              f++;
              break;
          }
          records += student.toString() + "\n"; // append string of results
        }
        txtResults.setText(records); // set the textbox

        // set tab 2 content
        tab2.setText("Table Results View");
        tab2.setContent(table);
        tabPane.getTabs().add(tab2);

        // set tab 3 content
        tab3.setText("Textarea Results View");
        VBox ctr = new VBox();
        ctr.getChildren().addAll(lblResults, txtResults);
        tab3.setContent(ctr);
        tabPane.getTabs().add(tab3);

        // creation of bar graph
        CategoryAxis xAxis = new CategoryAxis(); // set x the axis
        xAxis.setLabel("Grade");

        NumberAxis yAxis = new NumberAxis(); // set the y axis
        yAxis.setLabel("Number of Students");

        BarChart barChart = new BarChart(xAxis, yAxis); // new bar chart with axis properties
        XYChart.Series dataSeries1 = new XYChart.Series();
        dataSeries1.setName("Student Grades");

        // create the plots
        dataSeries1.getData().add(new XYChart.Data("A", a));
        dataSeries1.getData().add(new XYChart.Data("B", b));
        dataSeries1.getData().add(new XYChart.Data("C", c));
        dataSeries1.getData().add(new XYChart.Data("D", d));
        dataSeries1.getData().add(new XYChart.Data("F", f));

        barChart.getData().add(dataSeries1);

        VBox vbox = new VBox(barChart);

        // set tab 4 content
        tab4.setText("Bar Chart Results View");

        tab4.setContent(vbox);
        tabPane.getTabs().add(tab4);
      }
    } catch (IOException e1) {
      e1.printStackTrace();
    }
  }

  // method to check my textbox strings
  boolean isNullOrEmpty(String string) {
    return string == null || string.isEmpty();
  }

  public static void main(String[] args) { // main method to launch application
    Application.launch(args);
  }
}