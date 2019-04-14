package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class FutureValue extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    GridPane grid = new GridPane();
    grid.setAlignment(Pos.TOP_LEFT);
    grid.setPadding(new Insets(20, 20, 20, 20));
    grid.setHgap(5);
    grid.setVgap(5);
    Scene scene = new Scene(grid, 500, 300);

    //labels
    Label monthlyInv = new Label();
    monthlyInv.setText("Monthly investment:");
    grid.add(monthlyInv, 0, 0);
    grid.add(new Label("Yearly Interest Rates:"), 0, 1);
    grid.add(new Label("Years:"), 0, 2);
    grid.add(new Label("Future Value:"), 0, 3);

    //text fields
    TextField investmentField = new TextField();
    grid.add(investmentField, 1, 0);

    TextField interestRateField = new TextField();
    interestRateField.setDisable(true);
    grid.add(interestRateField, 1, 1);

    TextField yearsField = new TextField();
    grid.add(yearsField, 1, 2);

    TextField futureValueField = new TextField();
    futureValueField.setEditable(false);
    grid.add(futureValueField, 1, 3);

    //Set column width
//    for (int i = 0; i < 2; i++) {
//      ColumnConstraints col = new ColumnConstraints(100);
//      //col.setPercentWidth(33);
//      grid.getColumnConstraints().add(col);
//    }
//    ColumnConstraints a = new ColumnConstraints(50);
//    ColumnConstraints b = new ColumnConstraints(200);
//    ColumnConstraints c = new ColumnConstraints(300);
//    grid.getColumnConstraints().addAll(a, b, c);

    //buttons
    Button calcButton = new Button("Calculate");
    calcButton.setOnAction(event -> {primaryStage.setTitle("Click");});
    Button exitButton = new Button("Exit");
    HBox buttonBox = new HBox(10, calcButton, exitButton);
    buttonBox.setAlignment(Pos.BOTTOM_RIGHT);
    grid.add(buttonBox, 0, 4, 2, 1);


    //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

    primaryStage.setTitle("Future Value Calculator");
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
