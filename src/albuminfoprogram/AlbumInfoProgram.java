package albuminfoprogram;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Applications Programming Assignment 1 - AlbumInfoProgram.java
 * @author mre16utu - Stephen Whiddett
 * 
 * Class representing an Album Info Program designed to access and test a 
 * collection of classes implementing a collection of music albums
 */
public class AlbumInfoProgram {

   /**
    * @param args the command line arguments
    * @throws java.io.IOException
    */
   public static void main(String[] args) throws IOException
   {
      // Initialise new album collection
      AlbumCollection myAlbumCollection = new AlbumCollection();
      
      // Initialise a buffered writer for results output to text file
      // Not required but added for easy printing of results and to demonstrate
      // writing output to a file
      BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
      
      // Create string to separate each assignment task in results output file
      String taskSeparation = 
              "-----------------------------------------------------------\r\n";

      // 1. Read in an AlbumCollection from the file albums.txt provided
      myAlbumCollection.readAlbumCollectionFromFile("albums.txt");

      // 2. Display the entire album collection, arranged in alphabetical 
      // order of the album artist. If more than one album exists for a given
      // artist, they should be displayed in alphabetical order of the album 
      // title.
      myAlbumCollection.printAlbumsAlphabetical(bw);
      bw.write(taskSeparation);

      // 3. Display the total play time of all Pink Floyd albums in the 
      // collection.
      myAlbumCollection.printArtistsTotalAlbumPlaytime("Pink Floyd", bw);
      bw.write(taskSeparation);
      
      // 4. Display the album with the largest number of tracks.
      myAlbumCollection.printAlbumMostTracks(bw);
      bw.write(taskSeparation);
      
      // 5. Display the details of the longest track in the album collection.
      myAlbumCollection.printLongestTrack(bw);
      bw.write(taskSeparation);
      
      // 6. Display the total play time of the PlayList in the file playlist.txt
	Playlist playlist = new Playlist(myAlbumCollection);
      playlist.printPlaylistDuration("playlist.txt", bw);
      bw.write(taskSeparation);
      
      // flush bufferedwriter text to file containing results
      bw.flush();
   }
}
