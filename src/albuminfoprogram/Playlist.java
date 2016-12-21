package albuminfoprogram;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Applications Programming Assignment 1 - Playlist.java
 * @author mre16utu - Stephen Whiddett
 * 
 * Class representing a Playlist of Tracks from an AlbumCollection
 * Contains a reference to the Album Collection containing the tracks and a
 * list of PlaylistTrack objects representing the tracks
 */
public class Playlist {

   // Private Member Variables
   private final AlbumCollection albumCollection;
   private final List<PlaylistTrack> playlistTracks;
   
   // Begin Constructors
   public Playlist(AlbumCollection albumCollection)
   {
      // Creates empty playlist associated to given albumCollection
      this.albumCollection = albumCollection;
      playlistTracks = new ArrayList<>();
   }
   
   public Playlist(AlbumCollection albumCollection, 
                                         List<PlaylistTrack> playlistTrackList)
   {
      // Creates playlist populated with playlistTracks and associated to 
      // given albumCollection
      this.albumCollection = albumCollection;
      playlistTracks = playlistTrackList;
   }
   // End Constructors

   // Begin Getters
   public List<PlaylistTrack> getPlaylistTracks()
   {
      return playlistTracks;
   }
   
   public Duration getPlaylistDuration()
   {
      // iterate over all playlist tracks and sum the durations
      Duration playlistDuration = new Duration();
      for(PlaylistTrack playlistTrack : playlistTracks)
      {
         playlistDuration = playlistDuration.add(
                                             playlistTrack.getTrackDuration());
      }
      return playlistDuration;
   }
   
   // Returns reference to album collection upon which the playlist is based
   public AlbumCollection getPlayListAlbumCollection()
   {
      return albumCollection;
   }
   
   //
   public PlaylistTrack getTrackByNameAndDuration(String title, String duration)
   {
	//PlaylistTrack track;
	for(PlaylistTrack plTrack : playlistTracks)
	{
	   if(plTrack.getTrackTitle().equals(title) && plTrack.getTrackDuration().toString().equals(duration))
	   {
		return plTrack;
	   }
	}
	
      return null;
   }
   // End Getters
   
   // Begin Setters
   public void clearPlaylist()
   {
	playlistTracks.clear();
   }
   // End Setters
   
   // Method allowing playlist tracks to be added to playlist
   public void addPlaylistTrack(PlaylistTrack playlistTrack)
   {
      playlistTracks.add(playlistTrack);
   }
   
   // imports a playlist from file
   public String loadPlaylist(String fileName)
   {
      // Initialise variables to store Playlist and PlaylistTrack details from
      // import file
      String trackTitle;
      String albumTitle;
      String albumArtist; // Not used but extracted from data for reference
      
      try
      {
         // Read in the playlist data file line by line
         BufferedReader buffer;
         buffer = new BufferedReader(new FileReader(fileName));
         String currentLine;
         
         while ((currentLine = buffer.readLine()) != null)
         {
            // For each line in the file, get Album title, artist and track name
            // from the string. 
            
            // Split first at opening bracket '('
            String[] split = currentLine.split("\\(");
            trackTitle = split[0].trim();
            String albumArtistAndTitle = split[1].trim();
            
            // Split second part of string at ':'
            String[] split1 = albumArtistAndTitle.split(":");
            albumArtist = split1[0].trim(); 
            albumTitle = split1[1].trim();
            // Remove trailing ')' from album title string
            albumTitle = albumTitle.substring(0,albumTitle.length()-1);
                    
            // Now find the track in the album collection
            // Begin with retrieving the album containing the track
            Album currentAlbum = albumCollection.getAlbumByTitle(albumTitle);
            
            // if the album was retrieved (not null), try to get the track from
            // the album
            Track currentTrack = null;
            if(currentAlbum != null)
            {
               currentTrack = currentAlbum.getTrackByTitle(trackTitle);
            }
            
            // If track was retrieved from album (not null), create a new 
            // playlist track to be added to the playlist
            if(currentTrack != null)
            {
               PlaylistTrack playlistTrack = new PlaylistTrack(trackTitle, 
                                          currentTrack.getTrackDuration(), 
                                          currentAlbum);
               this.addPlaylistTrack(playlistTrack);
            }
         }
         // Get and print the playlist duration
         String outputString = "Total play time of Imported PlayList:\r\n" 
                            + this.getPlaylistDuration();
         System.out.println();
         System.out.println(outputString);
         // Write output to bufferedWriter
	   return outputString;
      }
      catch (IOException e)
      {
         // if error occurs, print the IOException
         System.out.println(e);
      }
	return "";
   }
   
   // method to print playlist duration
   public String printPlaylistDuration(String fileName)
   {
      // Get and print the playlist duration
      String outputString = "Total play time of Imported PlayList:\r\n" 
                         + this.getPlaylistDuration();
      System.out.println();
      System.out.println(outputString);
      // Write output to bufferedWriter
      return outputString;
   }
   
   // override to accept buffered writer to write output
   public void printPlaylistDuration(String fileName, BufferedWriter bw) 
										 throws IOException
   {
	String outputString = printPlaylistDuration(fileName);

      // Write output to bufferedWriter
      bw.write(outputString + "\r\n");
   }
   
   @Override
   public String toString()
   {
      // create a string containing all playlist tracks 1 per line
      String strPlaylistTracks = "";
      for (PlaylistTrack track : playlistTracks)
      {
         strPlaylistTracks += track.toString() + "\r\n";
      }
      return strPlaylistTracks;
   }
   
   /**
    * @param args the command line arguments
    * test harness code for testing of class methods
    */
   public static void main(String[] args)
   {
      // test toString - Can be called after creation of a playlist:
      // System.out.println(playlist);
   }
}
