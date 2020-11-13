public class Screen {

  String resolution;
  int refreshRate;
  int responseTime;

  public Screen(String resolution, int refreshRate, int responseTime) {
    this.resolution = resolution;
    this.refreshRate = refreshRate;
    this.responseTime = responseTime;
  }

  String getResolution() {
    return resolution;
  }

  int getRefreshRate() {
    return refreshRate;
  }

  int getResponseTime() {
    return responseTime;
  }

  public String toString() {
    return super.toString() + "\n" + "Screen:\nResolution: " + resolution + "\n" + "Refresh rate: "
        + refreshRate + "\n" + "Response time: " + responseTime;
  }
}