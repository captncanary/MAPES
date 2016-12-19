package albuminfoprogram;

/**
 * Applications Programming Assignment 1 - Track.java
 * @author mre16utu - Stephen Whiddett
 * 
 * Class representing a music Track
 * A track has a title and duration
 */
public class Track implements Comparable<Track> {

   // Private Member Variables
   private final String trackTitle;
   private final Duration trackDuration;

   // Begin Constructors
   public Track(String title, Duration duration)
   {
      trackTitle = title;
      trackDuration = duration;
   }
   // End Constructors
   
   // Begin getters
   public String getTrackTitle()
   {
      return trackTitle;
   }

   public Duration getTrackDuration()
   {
      return trackDuration;
   }
   // End getters
   
   @Override
   public String toString()
   {
      // returns track in 'Duration - track_title' string format
      return trackDuration + " - " + trackTitle;
   }

   // implement comparable interface - compareTo method to compare tracks 
   // by duration utilising the Duration class compareTo method
   @Override
   public int compareTo(Track compareTrack)
   {
      return this.trackDuration.compareTo(compareTrack.trackDuration);
   }
   
   /**
    * @param args the command line arguments
    * test harness code for testing of class methods
    */
   public static void main(String[] args)
   {
      // test toString
      Track track = new Track("my title", new Duration("0:01:33"));
      System.out.println(track.toString());
   }
}
