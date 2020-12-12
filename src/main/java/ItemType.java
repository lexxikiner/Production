/**
 * Preset enums for item types to be used throughout the program
 *
 * @author Lexxi Kiner
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
  /**
   * @param code - uses code to be visible to the other classes
   */
  ItemType(String code) {
    this.code = code;
  }

  @SuppressWarnings("unused")
  private String code() {
    return code;
  }
}