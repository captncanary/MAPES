package albuminfoprogram;

import java.util.ArrayList;
import java.util.List;

/**
 * Applications Programming Assignment 1 - PlaylistTrack.java
 * @author mre16utu - Stephen Whiddett
 * 
 * Class representing a Playlist Track
 * A Playlist Track extends the Track class by adding a reference to the Album
 * on which the track occurs
 */
public class PlaylistTrack extends Track {

   // Private Member Variables
   private Album album;

   // Begin Constructors
   public PlaylistTrack(String title, Duration duration, Album album)
   {
      // calls Track super class and initialises album
      super(title, duration);
      this.album = album;
   }
   // End Constructors
   
   // Begin Getters
   public Album getPlaylistTrackAlbum()
   {
      return album;
   }
   // End Getters
   
   @Override
   public String toString()
   {
      // returns track in 'Duration - track_title : album_title, album_artist'
      // string format
      return this.getTrackDuration() + " - " 
           + this.getTrackTitle() + " : " 
           + album.getAlbumTitle() + ", "
           + album.getAlbumArtist();
   }
   
   /**
    * @param args the command line arguments
    * test harness code for testing of class methods
    */
   public static void main(String[] args)
   {
      // test toString
      List<Track> albumTracks = new ArrayList<>();
      albumTracks.add(new Track("trackTitle", new Duration("0:05:33")));
      Album testAlbum = new Album("albumArtist", "albumTitle", albumTracks);
      PlaylistTrack playlistTrack = new PlaylistTrack(
                                         albumTracks.get(0).getTrackTitle(), 
                                         albumTracks.get(0).getTrackDuration(), 
                                         testAlbum);
      System.out.println(playlistTrack);
   }
}
