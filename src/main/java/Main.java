import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
    primaryStage.setTitle("TabPane Example");
    primaryStage.setScene(new Scene(root, 300, 275));
    Scene scene = new Scene(new Group(), 500, 400);
    scene.getStylesheets().add("style.css");
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}