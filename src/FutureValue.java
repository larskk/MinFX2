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

  private final TextField investmentField = new TextField();
  private final Label investmentLabel = new Label("Monthly Investment");

  private final TextField interestRateField = new TextField();
  private final Label interestLabel = new Label("Yearly Interest Rate");

  private final TextField yearsField = new TextField();
  private final Label yearsLabel = new Label("Years");

  private TextField futureValueField = new TextField();
  private final Label futureValueLabel = new Label("Future Value");

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    futureValueField.setEditable(false);
    primaryStage.setTitle("Future Value Calculator");

    GridPane grid = new GridPane();
    grid.setAlignment(Pos.TOP_LEFT);
    grid.setPadding(new Insets(20, 10, 20, 10));
    grid.setHgap(5);
    grid.setVgap(5);
    Scene scene = new Scene(grid, 500, 300);

    //labels & text fields
    grid.addColumn(0, investmentLabel, interestLabel, yearsLabel, futureValueLabel);
    grid.addColumn(1, investmentField, interestRateField, yearsField, futureValueField);

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
    Validation v = new Validation();
    String errorMessage = "";
    errorMessage += v.isDouble(investmentField.getText(), investmentLabel.getText());
    errorMessage += v.isDouble(interestRateField.getText(), interestLabel.getText());
    errorMessage += v.isInteger(yearsField.getText(), yearsLabel.getText());

    if (errorMessage.isEmpty()) {
      double investment = Double.parseDouble(investmentField.getText());
      double rate = Double.parseDouble(interestRateField.getText());
      int years = Integer.parseInt(yearsField.getText());
      double futureValue = FinancialCalculations.calculateFutureValue(investment, rate, years);
      NumberFormat currency = NumberFormat.getCurrencyInstance();
      futureValueField.setText(currency.format(futureValue));
    } else {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setHeaderText("Invalid Entry!");
      alert.setContentText(errorMessage);
      alert.showAndWait();
    }
  }
}
