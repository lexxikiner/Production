/**
 * The main class for the Production Project
 *
 * @author Lexxi Kiner
 * @date 9/19/2020
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
    Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
    primaryStage.setTitle("Lexxi Kiner - Production Project");
    Scene scene = new Scene(root);
    scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  /**
   * the main class of the Production Project
   *
   * @param args
   * @return void
   */
  public static void main(String[] args) {
    launch(args);
  }
}