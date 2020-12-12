/*
  The controller class for all three tabs, which include: Product Line, Produce, and Production
  Line

  @author Lexxi Kiner
 * @date 12/11/2020
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

@SuppressWarnings("unused")
public class Controller {

  // all FXML attributes
  @FXML
  private Button addProduct;

  @FXML
  private TextField tfproductName;

  @FXML
  private TextField tfmanufacturer;

  @FXML
  private ChoiceBox<ItemType> cbItemType;

  @FXML
  private TableView<Product> tvProductLine;

  @FXML
  private ListView<String> lvChooseProduct;

  @FXML
  private ArrayList<Product> productList;

  @FXML
  private ComboBox<String> chooseQuantity;

  @FXML
  private TextArea taProductionLog;

  @FXML
  private TextField tfName;

  @FXML
  private TextField tfPassword;

  @FXML
  private TextArea taEmployeeInfo;

  @FXML
  private Button employeeButton;

  int id;
  Connection conn;

  // observable list of all products produced
  private final ObservableList<Product> products = FXCollections.observableArrayList();
  final ArrayList<ProductionRecord> productionRun = new ArrayList<>();

  // saving item number for Serial Number
  int AUitemCount = 0;
  int VIitemCount = 0;

  /**
   * adds a product to a database
   *
   * @return void
   */
  @FXML
  public void addProduct() {
    // Get data from UI fields
    String productName = tfproductName.getText();
    String manufacturer = tfmanufacturer.getText();
    ItemType itemType = cbItemType.getValue();

    Product product = finalProduct(productName, manufacturer, itemType);

    // try-catch block to avoid errors while inserting into the database
    try {
      // SQL to insert a product into the DB
      String sql = "INSERT INTO Product(type, manufacturer, name) VALUES ( ?, ?, ? )";

      // Create a prepared statement from connection and set values to UI field values
      PreparedStatement ps = conn.prepareStatement(sql);
      // This is the only way to remove the FindBugs magic number bug
      final int itemTypeIndex = 1;
      final int manufacturerIndex = 2;
      final int productNameIndex = 3;
      ps.setString(itemTypeIndex, itemType.name());
      ps.setString(manufacturerIndex, manufacturer);
      ps.setString(productNameIndex, productName);

      // Execute and close the statement
      ps.execute();
      ps.close();
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
    loadProductList();
  }

  /**
   * in charge of running all info for the GUI and databases
   *
   * @param - none
   * @return void
   */
  public void initialize() {

    // testMultimedia();
    setupProductLineTable();
    connectToDB();
    loadProductList();
    loadProductionLog();
    //addEmployee();

    // populate the combo box
    for (int count = 1; count <= 10; count++) {
      chooseQuantity.getItems().add(String.valueOf(count));
    }

    // the user can enter values into the box
    chooseQuantity.setEditable(true);
    chooseQuantity.getSelectionModel().selectFirst();

    // populate the choice box
    for (ItemType values : ItemType.values()) {
      cbItemType.getItems().add(values);
    }

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

    conn = null;

    Statement stmt = null;

    try {

      // STEP 1: Register JDBC driver

      Class.forName(JDBC_DRIVER);

      //STEP 2: Open a connection

      //identified as a bug, but i know that the password is empty for now
      conn = DriverManager.getConnection(DB_URL, USER, PASS);

    } catch (ClassNotFoundException e) {

      e.printStackTrace();


    } catch (SQLException e) {

      e.printStackTrace();

    }

  }

  /**
   * disconnects the user from the database when they are finished
   *
   * @param - none
   * @return void
   */
  public void disconnect() {
    try {
      conn.close();

    } catch (SQLException e) {

      e.printStackTrace();

    }

  }

  /*
    tests the Movie Player and associated classes

    @param - none
   * @return void
   */
  /* public static void testMultimedia() {

    // hardcoded data to run the methods
    AudioPlayer newAudioProduct = new AudioPlayer("DP-X1A", "Onkyo",

        "DSD/FLAC/ALAC/WAV/AIFF/MQA/Ogg-Vorbis/MP3/AAC", "M3U/PLS/WPL");

    Screen newScreen = new Screen("720x480", 40, 22);

    MoviePlayer newMovieProduct = new MoviePlayer("DBPOWER MK101", "OracleProduction", newScreen,

        MonitorType.LCD);

    ArrayList<MultimediaControl> productList = new ArrayList<MultimediaControl>();

    productList.add(newAudioProduct);

    productList.add(newMovieProduct);

    for (MultimediaControl p : productList) {

      System.out.println(p);

      p.play();

      p.stop();

      p.next();

      p.previous();

    }

  } */

  /**
   * creates and sets up the table on the production line tab
   *
   * @param - none
   * @return void
   */
  public void setupProductLineTable() {

    // create the product line table
    TableColumn<Product, Integer> columnId = new TableColumn<>("ID");
    columnId.setCellValueFactory(new PropertyValueFactory<Product, Integer>("id"));

    TableColumn<Product, ItemType> columnType = new TableColumn<>("Type");
    columnType.setCellValueFactory(new PropertyValueFactory<Product, ItemType>("type"));

    TableColumn<Product, String> columnManufacturer = new TableColumn<>("Manufacturer");
    columnManufacturer.setCellValueFactory(new PropertyValueFactory<Product, String>
        ("manufacturer"));

    TableColumn<Product, String> columnName = new TableColumn<>("Name");
    columnName.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));

    // populate the table and column headers
    tvProductLine.getColumns().add(columnId);
    tvProductLine.getColumns().add(columnType);
    tvProductLine.getColumns().add(columnManufacturer);
    tvProductLine.getColumns().add(columnName);
    tvProductLine.setItems(products);
  }

  /**
   * read from the Product database, create an object, add them to the productLine ObservableList,
   * which auto populates the Product Line List View
   *
   * @param - none
   * @return void
   */
  public void loadProductList() {
    // clear the list and list view from previous runs
    products.clear();
    lvChooseProduct.getItems().clear();

    // try-catch block while communicating with the database
    try {
      String sql = "SELECT * FROM PRODUCT";

      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(sql);

      while (rs.next()) {
        id = rs.getInt("ID");
        ItemType type = ItemType.valueOf(rs.getString("Type"));
        String name = rs.getString("Name");
        String manufacturer = rs.getString("Manufacturer");

        // fill in missing information about the product
        Product finalProduct = finalProduct(name, manufacturer, type);
        finalProduct.setId(id);

        // add to the list and list view
        products.add(finalProduct);
        lvChooseProduct.getItems().add(finalProduct.toString());
      }

      stmt.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  /**
   * Set up the information to make the production log visual
   *
   * @param - none
   * @return void
   */
  public void loadProductionLog() {
    taProductionLog.clear();
    try {
      // communicate with the ProductionRecord database
      String sql = "SELECT * FROM PRODUCTIONRECORD";
      String sql2 = "SELECT * FROM PRODUCT";

      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(sql);
      ResultSet rs2 = stmt.executeQuery(sql2);

      while (rs.next()) {
        // create objects from the database
        int productionNumber = rs.getInt("PRODUCTION_NUM");
        int productId = rs.getInt("PRODUCT_ID");
        String serialNumber = rs.getString("SERIAL_NUM");
        Date dateProduced = rs.getDate("DATE_PRODUCED");
        id = rs2.getInt("ID");
        String type = rs2.getString("TYPE").substring(0, 2);

        int numProduced = Integer.valueOf(chooseQuantity.getValue());
        ProductionRecord pr = new ProductionRecord(productList.get(id), 0);
        for (int productionRunProduct = 0; productionRunProduct < numProduced;
            productionRunProduct++) {
          if (type.equalsIgnoreCase("AU")) {
            AUitemCount++;
            pr = new ProductionRecord(productList.get(id), AUitemCount);
            taProductionLog.appendText(pr.toString() + "\n");
          } else if (type.equalsIgnoreCase("VI")) {
            VIitemCount++;
            pr = new ProductionRecord(productList.get(id), VIitemCount);
            taProductionLog.appendText(pr.toString() + "\n");
          }
        }
      }

      stmt.close();
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
    showProduction();
  }

  /**
   * gets the information from the produce tab
   *
   * @param mouseEvent - the program prints to the console once the recordProduction is clicked
   * @return void
   */
  @FXML
  public void recordProduction(MouseEvent mouseEvent) {
    int numberToProduce = Integer.valueOf(chooseQuantity.getValue());
    for (int i = 0; i < numberToProduce; i++) {
      Product product = products.get(lvChooseProduct.getSelectionModel().getSelectedIndex());
      ProductionRecord productionRecord = new ProductionRecord(product, numberToProduce);
      productionRecord.setProductionNum(productionRun.size());
      System.out.println(productionRecord.toString());
      productionRun.add(productionRecord);
    }

    addToProductionDB(productionRun);

    loadProductionLog();
    showProduction();
  }

  /**
   * add to the productionRecord database
   *
   * @param productionRun - brings an array list of the recorded products
   * @return void
   */
  private void addToProductionDB(ArrayList<ProductionRecord> productionRun) {
    try {
      // SQL to insert a product into the DB
      String sql =
          "INSERT INTO productionrecord(production_num, product_id, serial_num, date_produced) "
              + "VALUES ( ?, ?, ?, ? )";

      final int prodNumberIndex = 1;
      final int prodIdIndex = 2;
      final int serialNumIndex = 3;
      final int prodDateIndex = 4;
      for (int i = 0; i < productionRun.size(); i++) {
        ProductionRecord productionRecord = productionRun.get(i);

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(prodNumberIndex, productionRecord.getProductionNum());
        ps.setInt(prodIdIndex, productionRecord.getProductID());
        ps.setString(serialNumIndex, productionRecord.getSerialNumber());
        ps.setDate(prodDateIndex,
            new java.sql.Date(productionRecord.getDateProduced().getTime()));

        ps.execute();
        ps.close();
      }
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
  }

  /**
   * inputs the missing informaiton from the product
   *
   * @param name         - the name of the product
   * @param manufacturer - the manufacturer of the product
   * @param type         - the type of the product
   * @return void
   */
  public Product finalProduct(String name, String manufacturer, ItemType type) {
    Product product = null;
    if (type == ItemType.AUDIO || type == ItemType.AUDIO_MOBILE) {
      product = new AudioPlayer(name, manufacturer,
          "DSD/FLAC/ALAC/WAV/AIFF/MQA/Ogg-Vorbis/MP3/AAC",
          "M3U/PLS/WPL");
    } else if (type == ItemType.VISUAL || type == ItemType.VISUAL_MOBILE) {
      int refreshRate = 40;
      int responseTime = 22;
      product = new MoviePlayer(name, manufacturer,
          new Screen("720x400", refreshRate, responseTime), MonitorType.LCD);
    }
    return product;
  }

  /**
   * shows the product info on the production log
   */
  public void showProduction() {
    taProductionLog.clear();
    for (ProductionRecord products : productionRun) {
      taProductionLog.appendText(products.toString() + "\n");
    }
  }

  /**
   * if there is a password, it reverses it
   *
   * @param password - the employee password
   * @return String - the new reversed password
   */
  public String reverseString(String password) {
    if (password.isEmpty()) {
      return password;
    } else {
      return reverseString(password.substring(1)) + password.charAt(0);
    }
  }

  /**
   * adds all the information of the employee and creates the passwords
   */
  public void addEmployee() {
    // Get data from UI fields
    String name = tfName.getText();
    String username;
    String password = tfPassword.getText();
    String email;
    String encryptedPass;

    Employee employee = new Employee(name, password);
    username = employee.username;
    email = employee.email;
    encryptedPass = reverseString(password);

    // try-catch block to avoid errors while inserting into the database
    try {
      // SQL to insert a product into the DB
      String sql = "INSERT INTO Employee(NAME, PASSWORD, USERNAME, EMAIL) VALUES ( ?, ?, ?, ?)";

      // Create a prepared statement from connection and set values to UI field values
      PreparedStatement ps = conn.prepareStatement(sql);
      // This is the only way to remove the FindBugs magic number bug
      final int nameIndex = 1;
      final int passwordIndex = 2;
      final int usernameIndex = 3;
      final int emailIndex = 4;
      ps.setString(nameIndex, name);
      ps.setString(passwordIndex, encryptedPass);
      ps.setString(usernameIndex, username);
      ps.setString(emailIndex, email);

      // Execute and close the statement
      ps.execute();
      ps.close();
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
    taEmployeeInfo.appendText(employee.toString());
  }

}