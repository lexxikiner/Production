/**
 * The class that captures the details of an audio player
 *
 * @author Lexxi Kiner
 * @date 12/11/2020
 */
@SuppressWarnings("ALL")
public class AudioPlayer extends Product implements MultimediaControl {

  final String supportedAudioFormats;
  final String supportedPlaylistFormats;

  /**
   * the constructor to set the values of audio players
   *
   * @param name                     - of type String name of the product
   * @param manufacturer             - of type String manufacturer of the product
   * @param supportedAudioFormats    - of type String and identifies the audio types supported
   * @param supportedPlaylistFormats - of type String and identifies the playlist types supported
   * @return void
   */
  public AudioPlayer(String name, String manufacturer, String supportedAudioFormats,
      String supportedPlaylistFormats) {
    super(name, manufacturer, ItemType.AUDIO);
    this.supportedAudioFormats = supportedAudioFormats;
    this.supportedPlaylistFormats = supportedPlaylistFormats;
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

// --Commented out by Inspection START (12/11/2020 1:31 PM):
//  /*****************************************************
//   * Getters and Setters
//   ****************************************************/
//  public String getsupportedAudioFormats() {
//    return supportedAudioFormats;
//  }
// --Commented out by Inspection STOP (12/11/2020 1:31 PM)

// --Commented out by Inspection START (12/11/2020 1:31 PM):
//  public void setsupportedAudioFormats(String supportedAudioFormats) {
//    this.supportedAudioFormats = supportedAudioFormats;
//  }
// --Commented out by Inspection STOP (12/11/2020 1:31 PM)

// --Commented out by Inspection START (12/11/2020 1:31 PM):
//  public String getsupportedPlaylistFormats() {
//    return supportedPlaylistFormats;
//  }
// --Commented out by Inspection STOP (12/11/2020 1:31 PM)

// --Commented out by Inspection START (12/11/2020 1:31 PM):
//  public void setsupportedPlaylistFormats(String supportedPlaylistFormats) {
//    this.supportedPlaylistFormats = supportedPlaylistFormats;
//  }
// --Commented out by Inspection STOP (12/11/2020 1:31 PM)

  /**
   * toString method that calls the super class method and prints the extra attributes of an audio
   * player
   *
   * @return void
   */
  public String toString() {
    return super.toString() + "\n" + "Supported Audio Formats: " + supportedAudioFormats + "\n"
        + "Supported Playlist Formats: " + supportedPlaylistFormats;
  }
}