import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.text.NumberFormat;

public class FutureValue extends Application {

  private TextField investmentField;
  private TextField interestRateField;
  private TextField yearsField;
  private TextField futureValueField;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    primaryStage.setTitle("Future Value Calculator");

    GridPane grid = new GridPane();
    grid.setAlignment(Pos.TOP_LEFT);
    grid.setPadding(new Insets(20, 10, 20, 10));
    grid.setHgap(5);
    grid.setVgap(5);
    Scene scene = new Scene(grid, 500, 300);


    //labels & text fields
    grid.add(new Label("Monthly Investment:"), 0, 0);
    investmentField = new TextField();
    grid.add(investmentField, 1, 0);

    grid.add(new Label("Yearly Interest Rates:"), 0, 1);
    interestRateField = new TextField();
    grid.add(interestRateField, 1, 1);

    grid.add(new Label("Years:"), 0, 2);
    yearsField = new TextField();
    grid.add(yearsField, 1, 2);

    grid.add(new Label("Future Value:"), 0, 3);
    futureValueField = new TextField();
    futureValueField.setEditable(false);
    grid.add(futureValueField, 1, 3);

    //buttons
    Button calcButton = new Button("Calculate");
    calcButton.setDefaultButton(true);
    calcButton.setOnAction(event -> calculateButtonClicked());

    Button exitButton = new Button("Exit");
    exitButton.setCancelButton(true);
    exitButton.setOnAction(event -> exitButtonClicked());

    HBox buttonBox = new HBox(10, calcButton, exitButton);
    buttonBox.setAlignment(Pos.BOTTOM_RIGHT);
    grid.add(buttonBox, 0, 4, 2, 1);

    primaryStage.setScene(scene);
    primaryStage.show();

    //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
  }

  private void exitButtonClicked() {
    System.exit(0);
  }

  private void calculateButtonClicked() {

    double investment;
    double rate = Double.parseDouble(interestRateField.getText());
    int years = Integer.parseInt(yearsField.getText());

    //get data from text fields
    if (investmentField.getText().isEmpty()) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setHeaderText("Invalid Entry!");
      alert.setContentText("Monthly investment is a required field.");
      alert.showAndWait();
      return;
    }
    try {
      investment = Double.parseDouble(investmentField.getText());

    } catch (NumberFormatException e) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setHeaderText("Invalid Entry!");
      alert.setContentText("Monthly investment must be a valid number.");
      alert.showAndWait();
      return;
    }

//  calculate future value
    double futureValue = FinancialCalculations.calculateFutureValue(investment, rate, years);

//  set data in read only text field
    NumberFormat currency = NumberFormat.getCurrencyInstance();
    futureValueField.setText(currency.format(futureValue));
  }
}
