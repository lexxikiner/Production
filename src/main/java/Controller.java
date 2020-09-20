/**
 * The controller class for all three tabs, which include: Product Line, Produce, and Production
 * Line
 *
 * @author Lexxi Kiner
 * @date 9/19/2020
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class Controller {

  @FXML
  private Button addProduct;

  @FXML
  private TextField productName;

  @FXML
  private TextField manufacturer;

  @FXML
  private ChoiceBox<String> itemType;

  /**
   * @param mouseEvent - the program connects to the database once the button is pressed
   * @return void
   */
  @FXML
  public void addProduct(MouseEvent mouseEvent) {
    connectToDB();
  }

  /**
   * @param mouseEvent - the program prints to the console once the recordProduction is clicked
   * @return void
   */
  @FXML
  public void recordProduction(MouseEvent mouseEvent) {
    System.out.println("clicked record production");
  }

  /**
   * populates the choice box in the Production Line tab
   *
   * @param - none
   * @return void
   */
  public void initialize() {
    for (int count = 1; count <= 10; count++) {
      itemType.getItems().add(String.valueOf(count));
    }
    itemType.getSelectionModel().selectFirst();
    // itemType.setEditable(true); -- compiler does not like this
  }

  /**
   * allows the user to interact with the database
   *
   * @param - none
   * @return void
   */
  public void connectToDB() {
    final String JDBC_DRIVER = "org.h2.Driver";

    final String DB_URL = "jdbc:h2:./res/HR";

    //  Database credentials

    final String USER = "";

    final String PASS = "";

    Connection conn = null;

    Statement stmt = null;

    try {

      // STEP 1: Register JDBC driver

      Class.forName(JDBC_DRIVER);

      //STEP 2: Open a connection

      //identified as a bug, but i know that the password is empty for now
      conn = DriverManager.getConnection(DB_URL, USER, PASS);

      //STEP 3: Execute a query

      stmt = conn.createStatement(); // identified as a bug

      String prodName = productName.getText();
      String manufacturerText = manufacturer.getText();
      System.out.println(prodName + " " + manufacturerText + " " + itemType);

      String sql = "INSERT INTO Product(type, manufacturer, name)"
          + "VALUES ( 'AUDIO', 'Apple', 'iPod' )";

      stmt.executeUpdate(sql);

      String sql2 = "SELECT name, manufacturer, type "
          + "FROM product ";

      ResultSet rs = stmt.executeQuery(sql2); //identified as a bug

      // print out the 3 columns of the table
      while (rs.next()) {
        System.out.print(rs.getString(1) + " ");
        System.out.print(rs.getString(2) + " ");
        System.out.println(rs.getString(3));
      }

      // STEP 4: Clean-up environment

      stmt.close();

      conn.close();

    } catch (ClassNotFoundException e) {

      e.printStackTrace();


    } catch (SQLException e) {

      e.printStackTrace();

    }

  }

}