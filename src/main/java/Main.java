/**
 * The main class for the Production Project
 *
 * @author Lexxi Kiner
 * @date 11/10/2020
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {


  @Override
  /**
   * this is where the sample.fxml and style.css are used to create the visual of the tabs and
   * tab pane window
   * @param primaryStage - creates the window
   * @return void
   */
  public void start(Stage primaryStage) throws Exception {
    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("sample.fxml"));
    Parent root = loader.load();
    Controller controller = loader.getController();
    primaryStage.setTitle("Lexxi Kiner - Production Project");
    Scene scene = new Scene(root);
    scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
    primaryStage.setScene(scene);
    primaryStage.show();
    primaryStage.setOnCloseRequest(e -> controller.disconnect());
  }

  /**
   * the main class of the Production Project
   *
   * @param args
   * @return void
   */
  public static void main(String[] args) {

    launch(args);

    Product productProduced = new Widget("iPod", "Apple", ItemType.AUDIO);

    // test constructor used when creating production records from user interface
    int numProduced = 3;  // this will come from the combobox in the UI
    int itemCount = 0;

    for (int productionRunProduct = 0; productionRunProduct < numProduced; productionRunProduct++) {
      ProductionRecord pr = new ProductionRecord(productProduced, itemCount++);
      // using the iterator as the product id for testing
      System.out.println(pr.toString());
    }
  }
}