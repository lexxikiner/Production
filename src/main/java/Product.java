/**
 * The super class for all products produced by the company
 *
 * @author Lexxi Kiner
 * @date 12/11/2020
 */
public abstract class Product implements Item {

  int id;
  ItemType type;
  String manufacturer;
  String name;

  /**
   * the constructor to set standard values of all products
   *
   * @param name         - of type String name of the product
   * @param manufacturer - of type String manufacturer of the product
   * @param type         - of type ItemType and is the type of product
   */
  Product(String name, String manufacturer, ItemType type) {
    this.name = name;
    this.manufacturer = manufacturer;
    this.type = type;
  }

  /**
   * original toString method that prints the values of the Products
   */
  public String toString() {
    return "Name: " + name + "\n" + "Manufacturer: " + manufacturer + "\n" + "Type: "
        + type;
  }

  /*****************************************************
   * Getters and Setters
   ****************************************************/
  public int getId() {
    return id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  public String getManufacturer() {
    return manufacturer;
  }

  public void setId(int id) {
    this.id = id;
  }

  public ItemType getType() {
    return type;
  }

  public void setType(ItemType type) {
    this.type = type;
  }
}