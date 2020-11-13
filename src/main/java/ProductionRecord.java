import java.util.Date;

public class ProductionRecord {

  int productionNumber;
  int productID;
  String serialNumber;
  Date dateProduced;
  Product productProduced;
  int itemCount;

  ProductionRecord(int productID) {
    this.productID = productID;
    this.productionNumber = 0;
    this.serialNumber = "0";
    this.dateProduced = new Date();
  }

  ProductionRecord(Product productProduced, int itemCount) {
    String extendedCount = String.format("%05d",itemCount);
    serialNumber = productProduced.manufacturer.substring(0,3) +
        productProduced.type.toString().substring(0,2) + extendedCount;
    this.dateProduced = new Date();
  }

  ProductionRecord(int productionNumber, int productID, String serialNumber, Date dateProduced) {
    this.productionNumber = productionNumber;
    this.productID = productID;
    this.serialNumber = serialNumber;
    this.dateProduced = dateProduced;
  }

  public String toString() {
    return "Prod. Num: " + productionNumber + " Product ID: " + productID + " Serial Num: "
        + serialNumber + " Date: " + dateProduced;
  }

  public void setProductionNum(int productionNum) {
    this.productionNumber = productionNumber;
  }

  public int getProductionNum() {
    return productionNumber;
  }

  public void setProductID(int productID) {
    this.productID = productID;
  }

  public int getProductID() {
    return productID;
  }

  public void setSerialNumber(String serialNumber) {
    this.serialNumber = serialNumber;
  }

  public String getSerialNumber() {
    return serialNumber;
  }

  public void setDateProduced(Date date) {
    this.dateProduced = dateProduced;
  }

  public Date getDateProduced() {
    return dateProduced;
  }

}