/*
  The class that is responsible for interacting with the production record database

  @author Lexxi Kiner
 */

import java.util.Date;

@SuppressWarnings({"ALL", "unused"})
public class ProductionRecord {

  int productionNumber;
  int productID;
  String serialNumber;
  Date dateProduced;

  /**
   * the constructor to set the values of audio players
   *
   * @param productID - of type integer and is the ID of the product
   */
  ProductionRecord(int productID) {
    this.productID = productID;
    this.productionNumber = 0;
    this.serialNumber = "0";
    this.dateProduced = new Date();
  }

  /**
   * the constructor to set the values of audio players
   *
   * @param productProduced - of type Product and contains the information of the product created
   * @param itemCount       - of type integer and is the number of items produced
   */
  ProductionRecord(Product productProduced, int itemCount) {
    String extendedCount = String.format("%05d", itemCount);
    serialNumber = productProduced.manufacturer.substring(0, 3) +
        productProduced.type.toString().substring(0, 2) + extendedCount;
    this.dateProduced = new Date();
  }

  /**
   * the constructor to set the values of audio players
   *
   * @param productionNumber - of type integer and is the the production set number
   * @param productID        - of type integer and is the ID of the product
   * @param serialNumber     - of type String and is the specific number for the product
   * @param dateProduced     - of type Date and sets the day and time of the product produced
   */
  ProductionRecord(int productionNumber, int productID, String serialNumber, Date dateProduced) {
    this.productionNumber = productionNumber;
    this.productID = productID;
    this.serialNumber = serialNumber;
    this.dateProduced = dateProduced;
  }

  /**
   * an override of the toString method, which displays the production record information
   *
   */
  @Override
  public String toString() {
    return "Prod. Num: " + productionNumber + " Product ID: " + productID + " Serial Num: "
        + serialNumber + " Date: " + dateProduced;
  }

  /*****************************************************
   * Getters and Setters
   ****************************************************/
  public void setProductionNum(int productionNumber) {
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

  @SuppressWarnings({"SillyAssignment", "unused"})
  public void setDateProduced() {
    //noinspection ConstantConditions
    this.dateProduced = dateProduced;
  }

  public Date getDateProduced() {
    return dateProduced;
  }

}