/**
 * The controller class for all three tabs, which include: Product Line, Produce, and Production
 * Line
 *
 * @author Lexxi Kiner
 * @date 11/12/2020
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

public class Controller {

  @FXML
  private Button addProduct;

  @FXML
  private TextField tfproductName;

  @FXML
  private TextField tfmanufacturer;

  @FXML
  private ChoiceBox<String> cbItemType;

  @FXML
  private TableView<Product> tvProductLine;

  @FXML
  private ListView<String> lvChooseProduct;

  private ArrayList<Product> productList;

  @FXML
  private ComboBox<String> chooseQuantity;

  @FXML
  private TextArea taProductionLog;

  int id;


  /**
   * @param mouseEvent - the program connects to the database once the button is pressed
   * @return void
   */
  @FXML
  public void addProduct(MouseEvent mouseEvent) {
    // Get data from UI fields
    String productName = tfproductName.getText();
    String manufacturer = tfmanufacturer.getText();
    ItemType itemType = ItemType.valueOf(cbItemType.getValue());

    Product product = findProduct(productName, manufacturer, itemType);

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
  }

  /**
   * @param mouseEvent - the program prints to the console once the recordProduction is clicked
   * @return void
   */
  @FXML
  public void recordProduction(MouseEvent mouseEvent) {
    int numberToProduce = Integer.valueOf(chooseQuantity.getValue());
    ArrayList<ProductionRecord> productionRecords = new ArrayList<>();
    for (int i = 0; i < numberToProduce; i++) {
      Product product = products.get(lvChooseProduct.getSelectionModel().getSelectedIndex());
      ProductionRecord productionRecord = new ProductionRecord(product, numberToProduce);
      productionRecord.setProductionNum(productionRun.size());
      System.out.println(productionRecord.toString());
      productionRun.add(productionRecord);
      productionRecords.add(productionRecord);
    }

    addToProductionDB(productionRecords);

    loadProductionLog();
  }

  private void addToProductionDB(ArrayList<ProductionRecord> productionRecords) {
    try {
      // SQL to insert a product into the DB
      String sql =
          "INSERT INTO productionrecord(production_num, product_id, serial_num, date_produced) "
              + "VALUES ( ?, ?, ?, ? )";

      final int prodNumberIndex = 1;
      final int prodIdIndex = 2;
      final int serialNumIndex = 3;
      final int prodDateIndex = 4;
      for (int i = 0; i < productionRecords.size(); i++) {
        ProductionRecord productionRecord = productionRecords.get(i);

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

  private ObservableList<Product> products = FXCollections.observableArrayList();
  private ArrayList<ProductionRecord> productionRun = new ArrayList<>();

  private Connection conn;

  private Statement stmt;

  /* public static void testMultimedia() {

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

  public Product findProduct(String name, String manufacturer, ItemType type) {
    Product product = null;
    if (type == ItemType.AUDIO || type == ItemType.AUDIO_MOBILE) {
      product = new AudioPlayer(name, manufacturer,
          "DSD/FLAC/ALAC/WAV/AIFF/MQA/Ogg-Vorbis/MP3/AAC", "M3U/PLS/WPL");
    } else if (type == ItemType.VISUAL || type == ItemType.VISUAL_MOBILE) {
      int refreshRate = 40;
      int responseTime = 22;
      product = new MoviePlayer(name, manufacturer,
          new Screen("720x400", refreshRate, responseTime), MonitorType.LCD);
    }
    return product;
  }

  public void setupProductLineTable() {

    TableColumn<Product, Integer> columnId = new TableColumn<>("ID");
    columnId.setCellValueFactory(new PropertyValueFactory<Product, Integer>("id"));

    TableColumn<Product, ItemType> columnType = new TableColumn<>("Type");
    columnType.setCellValueFactory(new PropertyValueFactory<Product, ItemType>("type"));

    TableColumn<Product, String> columnManufacturer = new TableColumn<>("Manufacturer");
    columnManufacturer.setCellValueFactory(new PropertyValueFactory<Product, String>
        ("manufacturer"));

    TableColumn<Product, String> columnName = new TableColumn<>("Name");
    columnName.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));

    tvProductLine.getColumns().add(columnId);
    tvProductLine.getColumns().add(columnType);
    tvProductLine.getColumns().add(columnManufacturer);
    tvProductLine.getColumns().add(columnName);
    tvProductLine.setItems(products);
  }

  public void loadProductList() {
    products.clear();
    lvChooseProduct.getItems().clear();

    try {

      String sql = "SELECT * FROM PRODUCT";

      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(sql);

      while (rs.next()) {
        id = rs.getInt("ID");
        ItemType type = ItemType.valueOf(rs.getString("Type"));
        String name = rs.getString("Name");
        String manufacturer = rs.getString("Manufacturer");

        Product product = findProduct(name, manufacturer, type);
        product.setId(id);

        products.add(product);
        lvChooseProduct.getItems().add(product.getName());
      }

      stmt.close();
    } catch (SQLException e) {
      e.printStackTrace();

    }
  }

  public void loadProductionLog() {
    taProductionLog.clear();
    try {
      String sql = "SELECT * FROM PRODUCTIONRECORD";

      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(sql);

      while (rs.next()) {
        int productionNumber = rs.getInt("PRODUCTION_NUM");
        int productId = rs.getInt("PRODUCT_ID");
        String serialNumber = rs.getString("SERIAL_NUM");
        Date dateProduced = rs.getDate("DATE_PRODUCED");
        id = rs.getInt("ID");

        //productionRun.add(productionRecord);
        //taProductionLog.appendText(productionRecord.toString() + "\n");

        int numProduced = Integer.valueOf(chooseQuantity.getValue());
        int itemCount = 0;
        for (int productionRunProduct = 0; productionRunProduct < numProduced; productionRunProduct++) {
          ProductionRecord pr = new ProductionRecord(productList.get(id), itemCount++);
          taProductionLog.appendText(pr.toString() + "\n");
          // System.out.println(pr.toString());
        }
      }

      stmt.close();
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
    showProduction();
  }

  public void showProduction() {
    taProductionLog.clear();
    for (ProductionRecord products : productionRun) {
      taProductionLog.appendText(products.toString() + "\n");
    }
  }

  /**
   * populates the choice box in the Production Line tab
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

    for (int count = 1; count <= 10; count++) {
      chooseQuantity.getItems().add(String.valueOf(count));
    }
    chooseQuantity.getSelectionModel().selectFirst();
    chooseQuantity.setEditable(true);
    cbItemType.getItems().add("AUDIO");
    cbItemType.getItems().add("VISUAL");
    cbItemType.getItems().add("AUDIO_MOBILE");
    cbItemType.getItems().add("VISUAL_MOBILE`");

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

    stmt = null;

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

  public void disconnect() {
    try {
      conn.close();

    } catch (SQLException e) {

      e.printStackTrace();

    }

  }

}