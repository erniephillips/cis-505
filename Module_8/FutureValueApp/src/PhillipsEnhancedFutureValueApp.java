/*
    Author: Ernest Phillips III
    Date: 09/24/2021
    Purpose: Demonstration of JavaFX program that outlines a shell of a GUI for interest calculations.
*/
//JavaFX CSS: https://docs.oracle.com/javafx/2/api/javafx/scene/doc-files/cssref.html#label
//https://www.youtube.com/watch?v=H67COH9F718
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class PhillipsEnhancedFutureValueApp extends Application {
  //set variables for the GUI box (scene) size 
  final double initialSceneWidth = 320;
  final double initialSceneHeight = 470;

  //declare all needed variables

  //labels to appear in form (informational)
  private Label lblMonthlyPayment = new Label("Monthly Payment:");
  private Label lblInterestRate = new Label("Interest Rate:");
  private Label lblInstructional = new Label("Enter 11.1% as 11.1");
  private Label lblYears = new Label("Years: ");
  private Label lblFutureValueDate = new Label("");
  
  //textfields (identify the textboxes)
  private TextField txtMonthlyPayment = new TextField();
  private TextField txtInterestRate = new TextField();
  
  //textarea
  private TextArea txtResults = new TextArea();

  //buttons
  private Button btnCalculate = new Button("Calculate");
  private Button btnClear = new Button("Clear");
  
  //dropdown
  private ComboBox<Integer> cbYears = new ComboBox<Integer>();
  
  //gridpane to include all above controls in a concise layout
  GridPane gridPane = new GridPane();

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) { //stage is the setting of the scene, now lets tell the actors what to do
    //how we will setup the grid pane
    gridPane.setAlignment(Pos.CENTER); //center it
    gridPane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5)); //apply a padding (clockwise) top | right | bottom | left
    gridPane.setHgap(5.5); //set the top gap
    gridPane.setVgap(5.5); //set the bottom gap

    // add controls to gridpane
    //When adding to the gridpane.add 
    //(Node, columnIndex, rowIndex, colspan, rowspan);
    gridPane.add(lblMonthlyPayment, 1, 1); //add monthly payment label with a col and row index
    txtMonthlyPayment.setMaxWidth(150); //set max widths, otherwise very large
    gridPane.add(txtMonthlyPayment, 2, 1); //add the textbox
    GridPane.setHalignment(txtMonthlyPayment, HPos.RIGHT);
    gridPane.add(lblInterestRate, 1, 2); //same as above comments but for interest
    txtInterestRate.setMaxWidth(150);
    gridPane.add(txtInterestRate, 2, 2);
    GridPane.setHalignment(txtInterestRate, HPos.RIGHT);

    // lblInterestRate.setTextFill(Color.RED); NOT WORKING
    lblInstructional.getStyleClass().add("label-red"); //set the color from my custom css class
    gridPane.add(lblInstructional, 2, 3); //add the label to the pane
    GridPane.setHalignment(lblInstructional, HPos.RIGHT); //set the label to float right

    gridPane.add(lblYears, 1, 4); //add the label for the years drop down
    List<Integer> listYears = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5)); //create a default list for drop down
    cbYears.getItems().addAll(listYears); //add the list of years to the dropdown
    gridPane.add(cbYears, 2, 4); //add drop down to grid
    gridPane.setHalignment(cbYears, HPos.RIGHT);

    HBox actionBtnContainer = new HBox(); //used to lay out children in a single horizontal row
    actionBtnContainer.setPadding(new Insets(15, 0, 15, 30)); //set the padding
    actionBtnContainer.setSpacing(10); //set the spacing between controls
    actionBtnContainer.getChildren().add(btnClear); //add the clear button
    actionBtnContainer.getChildren().add(btnCalculate); //add the calculate button
    btnClear.setOnAction(e -> clearFormFields());
    btnCalculate.setOnAction(e -> calculateResults());
    gridPane.add(actionBtnContainer, 2, 5); //add the container to the grid pane
    GridPane.setHalignment(actionBtnContainer, HPos.RIGHT);

    gridPane.add(lblFutureValueDate, 1, 6, 2, 1); //add the output which will be used in module 8 to output text
    
    gridPane.add(txtResults, 1, 7, 2, 1); //add the textarea which will output results 

    // create a scene and place it in the stage
    Scene scene = new Scene(gridPane, initialSceneWidth, initialSceneHeight);
    scene.getStylesheets().add("/css/stylesheet.css"); //add the custom stylesheet
    primaryStage.setTitle("Phillips Future Value App");// set the stage title
    primaryStage.setScene(scene);// place the scene in the stage
    primaryStage.show(); // display the stage
  }

  private void clearFormFields(){
    txtMonthlyPayment.clear();
    txtInterestRate.clear();
    txtResults.clear();
    lblFutureValueDate.setText("");
    cbYears.setValue(0);
  }

  private void calculateResults(){
    double futureValue = FinanceCalculator.calculateFutureValue(
      Double.parseDouble(txtMonthlyPayment.getText()), 
      Double.parseDouble(txtInterestRate.getText()), 
      cbYears.getValue());
    lblFutureValueDate.setText("Calcuation as of " + todaysDate("MM/dd/yyyy"));
    txtResults.setText("The future value is $" + String.format("%.2f", futureValue));
  }

  private String todaysDate(String pattern){
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    return simpleDateFormat.format(new Date());
  }

  public static void main(String[] args) { //main method to launch application
    Application.launch(args);
  }
}