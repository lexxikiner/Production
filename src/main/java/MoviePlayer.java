/**
 * The class that captures the details of a movie player
 *
 * @author Lexxi Kiner
 * @date 12/11/2020
 */
@SuppressWarnings("unused")
public class MoviePlayer extends Product implements MultimediaControl {

  Screen screen;
  MonitorType monitorType;

  /**
   * the constructor to set the values of audio players
   *
   * @param name         - of type String name of the product
   * @param manufacturer - of type String manufacturer of the product
   * @param screen       - of type Screen and pulls in all the values of a screen
   * @param monitorType  - of type MonitorType and pulls in all the values of monitor
   */
  public MoviePlayer(String name, String manufacturer, Screen screen, MonitorType monitorType) {
    super(name, manufacturer, ItemType.VISUAL);
    this.screen = screen;
    this.monitorType = monitorType;
  }

  /*****************************************************
   * Getters and Setters
   ****************************************************/
  public Screen getScreen() {
    return screen;
  }

  public void setScreen(Screen screen) {
    this.screen = screen;
  }

  public MonitorType getMonitorType() {
    return monitorType;
  }

  public void setMonitorType(MonitorType monitorType) {
    this.monitorType = monitorType;
  }

  public void play() {
    System.out.println("Playing movie");
  }

  public void stop() {
    System.out.println("Stopping movie");
  }

  public void previous() {
    System.out.println("Previous movie");
  }

  public void next() {
    System.out.println("Next movie");
  }


  /**
   * toString method that prints the attributes of a movie player
   */
  public String toString() {
    return super.toString() + "\nScreen:" + "\nResolution: " + screen.resolution
        + "\nRefresh rate: " + screen.refreshRate + "\nResponse time: " + screen.responseTime
        + "\nMonitor Type: " + monitorType;
  }
}