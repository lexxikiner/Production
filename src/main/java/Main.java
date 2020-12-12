/*
  The main class for the Production Project
  This renders the GUI and runs the program.

  @author Lexxi Kiner
 * @date 12/11/2020
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

  /*
    this is where the sample.fxml and style.css are used to create the visual of the tabs and
    tab pane window
    @param primaryStage - creates the window
   * @return void
   */
  @Override
  public void start(Stage primaryStage) throws Exception {
    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("sample.fxml"));
    Parent root = loader.load();
    final Controller controller = loader.getController();
    primaryStage.setTitle("Lexxi Kiner - Production Project");
    Scene scene = new Scene(root);
    scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
    primaryStage.setScene(scene);
    primaryStage.show();
    primaryStage.setOnCloseRequest(e -> controller.disconnect());
  }

  /**
   * the main class of the Production Project.
   *
   * @param args - array of string arguments
   */
  public static void main(String[] args) {
    launch(args);
  }
}