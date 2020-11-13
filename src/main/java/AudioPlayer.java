public class AudioPlayer extends Product implements MultimediaControl {

  String supportedAudio;
  String supportedPlaylist;

  public AudioPlayer(String name, String manufacturer, String supportedAudio,
      String supportedPlaylist) {
    super(name, manufacturer, ItemType.AUDIO);
    this.supportedAudio = supportedAudio;
    this.supportedPlaylist = supportedPlaylist;
  }

  public void play() {
    System.out.println("Playing");
  }

  public void stop() {
    System.out.println("Stopping");
  }

  public void previous() {
    System.out.println("Previous");
  }

  public void next() {
    System.out.println("Next");
  }

  public String getsupportedAudio() {
    return supportedAudio;
  }

  public void setsupportedAudio(String supportedAudio) {
    this.supportedAudio = supportedAudio;
  }

  public String getsupportedPlaylist() {
    return supportedPlaylist;
  }

  public void setsupportedPlaylist(String supportedPlaylist) {
    this.supportedPlaylist = supportedPlaylist;
  }

  public String toString() {
    return super.toString() + "\n" + "Supported Audio Formats: " + supportedAudio + "\n"
        + "Supported Playlist Formats: " + supportedPlaylist;
  }
}