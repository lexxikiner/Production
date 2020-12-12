/**
 * Methods to be implemented throughout all classes to identify all attributes of the items
 *
 * @author Lexxi Kiner
 */
@SuppressWarnings("unused")
public interface Item {

  /*****************************************************
   * Getters and Setters
   ****************************************************/

  /**
   * @return int - returns the ID
   */
  int getId();

  /**
   * @param name - the name of the product
   */
  void setName(String name);

  /**
   * @return String - returns the product name
   */
  String getName();

  /**
   * @param manufacturer - brings in the manufacturer of the product
   */
  void setManufacturer(String manufacturer);

  /**
   * @return String - returns the manufacturer
   */
  String getManufacturer();

}
