//package my.MP3Player;
package mapes;

/**
 * A sample java program for playing mp3 file Description: It uses JFilechooser
 * to allow user to choose an MP3 file from disk and then creates a thread to
 * play the chosen mp3 file in background.
 *
 * @author wjw
 *
 * modified from the original source code made by Kevin Wayne source:
 * http://introcs.cs.princeton.edu/java/faq/mp3/mp3.html
 *
 * Note: 1. Use it in Netbeans project: (1) add this java class file into the
 * package in your project (2) you also need to add jLayer.jar, available from
 * MA23's blackboard, onto the Libraries of your Java Project.
 *
 *
 * 2. If you want to run it standalone, use command below in DOS (jLayer.jar
 * should be in the same folder as this java program.) To compile: javac
 * -classpath .;jLayer.jar MP3Player0.java To run: java -classpath .;jLayer.jar
 * MP3Player0
 *
 * Hint: you can use the methods: play() and close() in this java class by (1)
 * adding it onto your package (2) creating instance of it and (3) then use the
 * instance to access its methods
 *
 * date: 22/12/2014 version: v0
 *
 *
 */
import java.awt.Component;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import javax.swing.JFileChooser;
import javazoom.jl.player.Player;

public class MP3Player0 {

   private String filename;
   private static Player player;

   // a default constructor
   public MP3Player0()
   {
   }

   // Constructor takes a given file name 
   public MP3Player0(String filename)
   {
	this.filename = filename;
   }

   public void close()
   {
	if (player != null)
	{
	   player.close();
	}
   }

   // play the JLayerMP3 file to the sound card
   public void play()
   {
	try
	{
	   FileInputStream fis = new FileInputStream(filename);
	   BufferedInputStream bis = new BufferedInputStream(fis);
	   player = new Player(bis);
	} 
	catch (Exception e)
	{
	   System.out.println("\n Problem in playing: " + filename);
	   System.out.println(e);
	}
   }

   public void play(String mp3filename)
   {
	try
	{
	   FileInputStream fis = new FileInputStream(mp3filename);
	   BufferedInputStream bis = new BufferedInputStream(fis);
	   player = new Player(bis);
	} 
	catch (Exception e)
	{
	   System.out.println("Problem in playing: " + mp3filename);
	   System.out.println(e);
	}

	// creata a thread to play music in background
	new Thread() {
	   public void run()
	   {
		try
		{
		   player.play();
		} catch (Exception e)
		{
		   System.out.println(e);
		}
	   }
	}.start();
   }

   // test MP3Player
   public static void main(String[] args)
   {
	//String filename = args[0];
	String filename = "SleepAway.mp3";
	// String filepath ="C:\\awork\\teaching\\ma23\\lab6"; 
	String fileType;

	// use JFileChooser to open an MP3 file
	try
	{
	   //use a preset file path 
	   //JFileChooser openFC = new JFileChooser(filepath);

	   JFileChooser openFC = new JFileChooser();
	   Component c1 = null;
	   openFC.showOpenDialog(c1);
	   File mp3file = openFC.getSelectedFile();
	   filename = mp3file.getAbsolutePath();

	   //check the file format 
	   //fileType= openFC.getTypeDescription(mp3file);
	   //System.out.print("file type= "+ fileType);
	} catch (Exception e)
	{
	}

	//MP3Player0 mp3 = new MP3Player0(filename); 
	// create an instance of MP3Player0 using default constructor
	MP3Player0 mp3 = new MP3Player0();
	mp3.play(filename);
	System.out.println("Playing mp3 file:" + filename);
	// the programn will stop when the track ends. 
	//mp3.close();
   }
}
