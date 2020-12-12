/**
 * Methods to be implemented throughout all classes to identify all attributes of the items
 *
 * @author Lexxi Kiner
 * @date 12/11/2020
 */
@SuppressWarnings("unused")
public interface Item {

  /*****************************************************
   * Getters and Setters
   ****************************************************/
  int getId();

  void setName(String name);

  String getName();

  void setManufacturer(String manufacturer);

  String getManufacturer();

}
