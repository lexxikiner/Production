/**
 * Preset enums for item types to be used throughout the program
 *
 * @author Lexxi Kiner
 * @date 12/11/2020
 */
public enum ItemType {
  AUDIO("AU"),
  VISUAL("VI"),
  AUDIO_MOBILE("AM"),
  VISUAL_MOBILE("VM");

  private final String code;

  /*****************************************************
   * Getters and Setters
   ****************************************************/
  ItemType(String code) {
    this.code = code;
  }

  private String code() {
    return code;
  }
}