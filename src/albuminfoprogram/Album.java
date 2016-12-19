package albuminfoprogram;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Applications Programming Assignment 1 - Album.java
 * @author mre16utu - Stephen Whiddett
 * 
 * Class representing a music Album
 * An album has a title, artist and a list of tracks
 */
public class Album implements Comparable<Album> {

   // Private Member Variables
   private final String artist;
   private final String title;
   private final List<Track> tracks;

   // begin Constructors
   public Album(String artist, String title, List<Track> tracks)
   {
      this.artist = artist;
      this.title  = title;
      this.tracks = tracks;
   }
   // end Constructors

   // Begin Getters
   public String getAlbumTitle()
   {
      return title;
   }

   public String getAlbumArtist()
   {
      return artist;
   }
   
   public int getTrackCount()
   {
      return tracks.size();
   }
   
   public List<Track> getTracks()
   {
      return tracks; // TODO breaks encapsulation
   }
   
   // Getter to return track with longest Duration. If multiple tracks have 
   // same longest duration the first one in the track list will be returned
   public Track getLongestTrack()
   {
      // create dummy track with zero duration for initial comparison
      Track longestTrack = new Track("new track", new Duration());
      Duration maxDuration = new Duration();
      
      // Create Iterator to iterate over the album collection
      // Netbeans warns 'use of iterator for simple loop' but Iterator is used
      // here to demonstrate knowledge of using Iterators rather than using for
      // loop and List.get() method
      Iterator iterator = tracks.iterator();
      while(iterator.hasNext())
      {
         Track currentTrack = (Track)iterator.next();
         Duration currentDuration = currentTrack.getTrackDuration();
         
         // if track's duration exceeds duration of longest track 
         // found so far, store the track and it's duration
         if(currentDuration.compareTo(maxDuration) > 0)
         {
            longestTrack = currentTrack;
            maxDuration = currentDuration;
         }
      }
      return longestTrack;
   }
   
   // Method to return Track from Album given supplied trackTitle string
   // Returns null if Track title not present in Album
   public Track getTrackByTitle(String trackTitle)
   {
      for(Track track : tracks)
      {
         if(track.getTrackTitle().equals(trackTitle))
         {
            return track;
         }
      }
      return null;
   }

   // Method to get album duration, being the sum of track durations
   public Duration getAlbumDuration()
   {
      // Initialise a zero duration variable to store album duration
      Duration albumDuration = new Duration();

      // for each track get the duration and add it to album duration
      for (Track track : tracks)
      {
         albumDuration = albumDuration.add(track.getTrackDuration());
      }
      return albumDuration;
   }
   // End Getters

   @Override
   public String toString()
   {
      // returns Album artist, title and list of tracks
      String strAlbumTracks = "";
      
      // create a string containing all album tracks 1 per line
      for (Track track : tracks)
      {
         strAlbumTracks += track.toString() + "\r\n";
      }

      return artist + " : " + title + "\r\n" + strAlbumTracks;
   }

   // implement comparable interface - compareTo method to sort albums by 
   // artist and then title
   @Override
   public int compareTo(Album compareAlbum)
   {
      // concatenate artist and title for comparison
      String strThisAlbum = this.artist + this.title;
      String strCompareAlbum = compareAlbum.artist 
                             + compareAlbum.title;
      return strThisAlbum.compareTo(strCompareAlbum);
   }

    /**
    * @param args the command line arguments
    * test harness code for testing of class methods
    */
   public static void main(String[] args)
   {
      // test toString() (no tracks)
      Album album = new Album("artist", "title", new ArrayList<>());
      System.out.println(album);
   }
}
