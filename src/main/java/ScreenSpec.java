/**
 * Defines methods that will get the screen specifications
 *
 * @author Lexxi Kiner
 * @date 12/11/2020
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