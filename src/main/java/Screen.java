/**
 * Class that gathers all of the information needed for the screen
 *
 * @author Lexxi Kiner
 * @date 12/11/2020
 */
public class Screen {

  final String resolution;
  final int refreshRate;
  final int responseTime;

  /**
   * the constructor to set the values of audio players
   *
   * @param resolution   - of type String, resolution of the screen
   * @param refreshRate  - of type integer, refresh rate of the screen
   * @param responseTime - of type integer, response time of the screen
   */
  public Screen(String resolution, int refreshRate, int responseTime) {
    this.resolution = resolution;
    this.refreshRate = refreshRate;
    this.responseTime = responseTime;
  }


  /*****************************************************
   * Getters and Setters
   ****************************************************/
  String getResolution() {
    return resolution;
  }

  int getRefreshRate() {
    return refreshRate;
  }

  int getResponseTime() {
    return responseTime;
  }

  /**
   * toString method that calls the super class method and prints the extra attributes of a screen
   */
  public String toString() {
    return super.toString() + "\n" + "Screen:\nResolution: " + resolution + "\n" + "Refresh rate: "
        + refreshRate + "\n" + "Response time: " + responseTime;
  }
}