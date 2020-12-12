/**
 * Creates the possible values of a movie player's monitor type
 *
 * @author Lexxi Kiner
 */
@SuppressWarnings("unused")
public enum MonitorType {

  LCD("LCD"),
  LED("LED");

  public final String code;

  /*****************************************************
   * Getters and Setters
   ****************************************************/
  MonitorType(String code) {
    this.code = code;
  }

  public String code() {
    return code;
  }

}
