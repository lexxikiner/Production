public abstract class Product implements Item {

  private int id;
  private String type;
  private String manufacturer;
  private String name;



  Product(String name, String manufacturer, String type) {
    this.name = name;
    this.manufacturer = manufacturer;
    this.type = type;
  }

  public String toString() {
    return "Name: " + name + "\n" + "Manufacturer: " + manufacturer + "\n" + "Type: "
        + type;
  }

  public int getId() {
    return id;
  }

  public String getManufacturer() {
    return manufacturer;
  }

  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}

class Widget {}
