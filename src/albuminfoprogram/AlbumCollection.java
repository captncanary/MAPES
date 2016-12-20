package albuminfoprogram;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Applications Programming Assignment 1 - AlbumCollection.java
 *
 * @author mre16utu - Stephen Whiddett
 *
 * Class representing a music Album Collection An album collection contains a
 * list of albums
 */
public class AlbumCollection {

   // Private Member Variables
   private final List<Album> albumCollection;

   // Begin Constructors
   public AlbumCollection()
   {  // default constructor
	albumCollection = new ArrayList<>();
   }

   public AlbumCollection(List<Album> albumList)
   {  // additional constructor for use with existing list of albums
	albumCollection = albumList;
   }
   // End Constructors

   // Begin Getters
   public List<Album> getAlbums()
   {
	return albumCollection; // TODO breaks encapsulation
   }

   // Get method to return an album if its title matches given string parameter
   public Album getAlbumByTitle(String albumTitle)
   {
	// Loop over albums until album title found and then return the album
	for (Album album : albumCollection)
	{
	   if (album.getAlbumTitle().equals(albumTitle))
	   {
		return album;
	   }
	}
	// If album not found return null
	return null;
   }

   // Method to return a list of albums by given albumArtist string parameter
   public List<Album> getAlbumsByArtist(String albumArtist)
   {
	// iterate over each album in my album collection and where the album 
	// artist is equal to albumArtist, add the album to a list of albums
	List<Album> albumsByArtist = new ArrayList<>();
	for (Album album : albumCollection)
	{
	   if (album.getAlbumArtist().equals(albumArtist))
	   {
		albumsByArtist.add(album);
	   }
	}
	return albumsByArtist;
   }

   // Method to get album with most tracks from the collection. If multiple 
   // albums have the same max track count, this method will return the first
   // of those albums occurring in the album collection list
   public Album getAlbumWithMostTracks()
   {
	// Initialise variables to store max track count and album with the most 
	// tracks
	int maxTrackCount = 0;
	Album maxTrackAlbum = null;

	// Create Iterator to iterate over the album collection
	// Netbeans warns 'use of iterator for simple loop' but Iterator is used
	// here to demonstrate knowledge of using Iterators rather than using for
	// loop and List.get() method
	Iterator iterator = albumCollection.iterator();
	while (iterator.hasNext())
	{
	   // get current album and number of tracks in current album
	   Album currentAlbum = (Album) iterator.next();
	   int trackCount = currentAlbum.getTrackCount();

	   // if track count exceeds max found so far, update max track count and
	   // store the album in maxTrackAlbum
	   if (trackCount > maxTrackCount)
	   {
		maxTrackCount = trackCount;
		maxTrackAlbum = currentAlbum;
	   }
	}
	return maxTrackAlbum;
   }
   // End Getters

   // Method to add album to the collection
   public void addAlbum(Album album)
   {
	albumCollection.add(album);
   }

   // Method to remove album from the collection
   public void removeAlbum(Album album)
   {
	albumCollection.remove(album);
   }

   // Assignment Problem 1 method - reads album collection from text file and 
   // populates album collection
   public void readAlbumCollectionFromFile(Object fileName)
   {
	// Initialise variables to hold album title, artist and tracks
	List<Track> albumTracks = new ArrayList<>();
	String albumTitle = "";
	String albumArtist = "";
	String file = (String) fileName;
	try
	{
	   // Read in the album data file line by line
	   BufferedReader buffer;
	   buffer = new BufferedReader(new FileReader(file));
	   String currentLine;
	   Album album = null;

	   while ((currentLine = buffer.readLine()) != null)
	   {
		// Lines in the album data file begin with a letter when the line 
		// contains an album title and artist, or an integer when it 
		// contains track info
		

		// For each line, check whether first character is a letter. If so,
		// assume we are at a new Album title/artist
		if (Character.isLetter(currentLine.charAt(0)))
		{
		   // Album title found
		   
		   // Get Album title and artist from the current line
		   String[] split = currentLine.split(":");
		   albumArtist = split[0].trim();
		   albumTitle = split[1].trim();


		   // check that current album does not exist in collection
		   // try to get album by title
		   Album albumByTitle = this.getAlbumByTitle(albumTitle);
		   //only add album if albumByTitle returned null
		   //TODO checking by artist will not work here as only first album by title found is returned. Need method to check for more albums of same name.
		   if (albumByTitle == null || !albumByTitle.getAlbumArtist().equals(albumArtist))
		   {
			// We are at the end of the current album. Therefore, create
			// the album and add it to the album collection
			album = new Album(albumArtist, albumTitle);
			this.addAlbum(album);
		   }
		}
		else // If first char is not a letter assume the line is a new track
		{
		   // Track found - get its title and duration
		   String[] split = currentLine.split("-", 2); // ', 2' prevents 
		   //splitting where '-' character occurs in title of the track
		   String strTrackDuration = split[0].trim();
		   String trackTitle = split[1].trim();

		   // create duration object from string
		   Duration trackDuration = new Duration(strTrackDuration);
		   // create track object and add to album track list
		   Track track = new Track(trackTitle, trackDuration);
		   album.addTrack(track);
		}
	   }

	} catch (IOException e)
	{
	   // if error occurs, print the IOException
	   System.out.println(e);
	}
   }

   // Assignment Problem 2 method - sort album collection alphabetically and
   // print the entire collection
   public String printAlbumsAlphabetical() throws IOException
   {
	// Sort the albums alphabetically. Iterate over the list and
	// print details of each album
	Collections.sort(albumCollection);

	// Print each of the sorted albums
	String outputString = "Printing Albums ordered by artist and title:\r\n";
	System.out.println(outputString);

	for (Album album : albumCollection)
	{
	   System.out.println(album);
	}
	return outputString;
   }

   // override to accept buffered writer to write output
   public void printAlbumsAlphabetical(BufferedWriter bw) throws IOException
   {
	String outputString = printAlbumsAlphabetical();
	// Write output to bufferedWriter
	bw.write(outputString);
	for (Album album : albumCollection)
	{
	   // Write output to bufferedWriter
	   bw.write(album.toString());
	   bw.newLine();
	}
   }

   // Assignment Problem 3 method - prints total album playtime for the given 
   // albumArtist
   public String printArtistsTotalAlbumPlaytime(String albumArtist)
	     throws IOException
   {
	// Initialise a zero time duration to use for summing the albumArtist's
	// Album Durations
	Duration artistsTotalAlbumDuration = new Duration();

	// Get list of Albums by given artist
	List<Album> artistsAlbums = this.getAlbumsByArtist(albumArtist);

	// for each album by the albumArtist, get the album duration and add it
	// to the total
	for (Album album : artistsAlbums)
	{
	   Duration albumDuration = album.getAlbumDuration();
	   artistsTotalAlbumDuration
		     = artistsTotalAlbumDuration.add(albumDuration);
	}
	// Output total duration of albumArtist's albums
	String outputString = albumArtist + "'s albums total duration: "
		  + artistsTotalAlbumDuration + "\r\n";
	System.out.println(outputString);
	return outputString;
   }

   // override to accept buffered writer to write output
   public void printArtistsTotalAlbumPlaytime(String albumArtist,
	     BufferedWriter bw) throws IOException
   {
	String outputString = printArtistsTotalAlbumPlaytime(albumArtist);
	// Write output to bufferedWriter
	bw.write(outputString);
   }

   // Assignment Problem 4 method - prints the album in the album collection 
   // that contains the most tracks
   public Album printAlbumMostTracks() throws IOException
   {
	// Get album containing most tracks in the collection
	Album albumWithMostTracks = this.getAlbumWithMostTracks();

	// Display the album with max track count
	String outputString = "Album with the most tracks in the collection ("
		  + albumWithMostTracks.getTrackCount() + "):\r\n";
	System.out.println(outputString);
	System.out.println(albumWithMostTracks);
	return albumWithMostTracks;
   }

   // override to accept buffered writer to write output
   public void printAlbumMostTracks(BufferedWriter bw) throws IOException
   {
	Album albumWithMostTracks = printAlbumMostTracks();
	// Display the album with max track count
	String outputString = "Album with the most tracks in the collection ("
		  + albumWithMostTracks.getTrackCount() + "):\r\n";
	// Write output to bufferedWriter
	bw.write(outputString);
	bw.write(albumWithMostTracks.toString());
   }

   // Assignment Problem 5 method - prints the track in the collection with
   // the longest duration
   public Track printLongestTrack()
   {
	// Initialise variables to store longest Track and its Duration
	Track longestTrack = null;
	Duration maxDuration = new Duration();

	// Iterate over albums in the collection getting the longest track in
	// each
	for (Album album : this.getAlbums())
	{
	   Track currentTrack = album.getLongestTrack();
	   Duration currentDuration = currentTrack.getTrackDuration();

	   // Compare duration of longest track in this album with max duration 
	   // found so far. If it is longer, store the track and its duration.
	   if (currentDuration.compareTo(maxDuration) > 0)
	   {
		longestTrack = currentTrack;
		maxDuration = currentDuration;
	   }
	}

	// Display the album with max track count
	String outputString = "Track in collection with longest duration:\r\n";
	System.out.print(outputString);
	// Print details of last (longest) track in the sorted track list
	System.out.println(longestTrack);
	return longestTrack;
   }

   // override to accept buffered writer to write output
   public void printLongestTrack(BufferedWriter bw) throws IOException
   {
	Track longestTrack = printLongestTrack();
	String outputString = "Track in collection with longest duration:\r\n";
	// Write output to bufferedWriter
	bw.write(outputString);
	bw.write(longestTrack.toString() + "\r\n");
   }

   @Override
   public String toString()
   {
	// returns list of Albums by artist, title and list of tracks
	String strAlbums = "";

	// create a string containing all albums
	for (Album album : albumCollection)
	{
	   strAlbums += album.toString() + "\r\n";
	}

	return strAlbums;
   }

   /**
    * @param args the command line arguments test harness code for testing of
    * class methods
    */
   public static void main(String[] args)
   {
	//System.out.println();
	//System.out.println("PRINTING ALBUM COLLECTION:");
	//System.out.println(myAlbumCollection.toString());
   }
}
