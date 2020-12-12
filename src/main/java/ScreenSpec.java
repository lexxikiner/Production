/**
 * Defines methods that will get the screen specifications
 *
 * @author Lexxi Kiner
 */
@SuppressWarnings("unused")
public interface ScreenSpec {

  /*****************************************************
   * Getters
   ****************************************************/
  String getResolution();

  int getRefreshRate();

  int getResponseTime();
}