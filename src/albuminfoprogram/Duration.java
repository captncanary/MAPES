package albuminfoprogram;

import java.text.DecimalFormat;

/**
 * Applications Programming Assignment 1 - Duration.java
 * @author mre16utu - Stephen Whiddett
 * 
 * Class representing time duration in hours, minutes and seconds
 * Negative time durations cannot be constructed
 */
public class Duration implements Comparable<Duration> {

   // Private Member Variables
   private final int seconds;
   private final int minutes;
   private final int hours;
   private static final DecimalFormat decFormat = new DecimalFormat("00");

   // Begin Constructors
   public Duration()
   {  // default constructor
      seconds = 0;
      minutes = 0;
      hours   = 0;
   }

   public Duration(int hours, int minutes, int seconds)
   {
      // Check for valid int values and throw exception if invalid value found
      // i.e. mins/secs > 59 or hours/mins/secs < 0 (negative durations not 
      // allowed)
      checkValidArgs(hours, minutes, seconds);
      
      this.seconds = seconds;
      this.minutes = minutes;
      this.hours   = hours;
   }

   // Constructor that accepts a time in the form hh:mm:ss
   public Duration(String hhmmss)
   {
      // split hhmmss string into hours, minutes and seconds components
      String[] split = hhmmss.split(":");
      
      // attempt to parse the split duration string to an int array where value
      // at index 0 is hours, 1 is minutes and 2 is seconds - throws exception
      // if 3 ints are returned
      int[] tempDuration = attemptParseDuration(split);
      
      // duration string parsed correctly so check for valid int values and 
      // throw exception if invalid value found, i.e. mins/secs > 59 or 
      // hours/mins/secs < 0 (negative durations not allowed)
      checkValidArgs(tempDuration[0], tempDuration[1], tempDuration[2]);
      
      // no exceptions thrown so initialise object variables to submitted 
      // values
      hours   = tempDuration[0];
      minutes = tempDuration[1];
      seconds = tempDuration[2];
   }
   // End Constructors
   
   // Begin Getters
   public int getHours()
   {
      return hours;
   }

   public int getMinutes()
   {
      return minutes;
   }

   public int getSeconds()
   {
      return seconds;
   }
   
   public int getDurationInSeconds()
   {
      return durationInSeconds();
   }
   // End Getters

   /**
    * method that returns sum of 2 durations
    * @param addDuration
    * @return Duration returns the sum of this.Duration and addDuration
    */
   public Duration add(Duration addDuration)
   {
      // add up seconds in this duration + addDuration
      int totalSeconds = seconds + addDuration.getSeconds();
      int extraMinutes = totalSeconds / 60; // count extra minutes
      totalSeconds = totalSeconds % 60; // remove extra minutes from seconds

      // add up minutes in this duration + addDuration
      int totalMinutes = minutes + addDuration.getMinutes() + extraMinutes;
      int extraHours = totalMinutes / 60; // count extra hours
      totalMinutes = totalMinutes % 60; // remove extra hours from minutes

      // add up hours in this duration + addDuration
      int totalHours = hours + addDuration.getHours() + extraHours;

      return new Duration(totalHours, totalMinutes, totalSeconds);
   }

   // method that returns subtraction of 2 durations
   public Duration subtract(Duration subtractDuration)
   {
      // subtract subtractDuration seconds from this duration seconds
      int totalSeconds = seconds - subtractDuration.getSeconds();
      // variable to track minutes moved to seconds
      int extraMinutes = 0;
      // check seconds is positive and if not add a minute to seconds
      if (totalSeconds < 0)
      {
         totalSeconds += 60;
         extraMinutes--;
      }

      // subtract subtractDuration minutes from this duration minutes
      int totalMinutes = minutes - subtractDuration.getMinutes() + extraMinutes;
      // variable to track hours moved to minutes
      int extraHours = 0;
      // check minutes is positive and if not add an hour to minutes
      if (totalMinutes < 0)
      {
         totalMinutes += 60;
         extraHours--;
      }

      // subtract subtractDuration hours from this duration hours
      int totalHours = hours - subtractDuration.getHours() + extraHours;

      return new Duration(totalHours, totalMinutes, totalSeconds);
   }

   @Override
   public String toString()
   {
      // returns duration in hh:mm:ss string format
      return decFormat.format(hours) + ":" 
           + decFormat.format(minutes) + ":" 
           + decFormat.format(seconds);
   }
   
   // implement comparable interface - compareTo method to compare durations
   @Override
   public int compareTo(Duration compareDuration)
   {
      return this.durationInSeconds().compareTo(
                                          compareDuration.durationInSeconds());
   }
   
   // method to return duration in seconds as an Integer
   // helper method for compareTo method
   private Integer durationInSeconds()
   {
      return (hours * 60 * 60) 
           + (minutes * 60) 
           +  seconds;
   }
   
   // helper method to check for valid arguments in constructor
   private void checkValidArgs(int hours, int minutes, int seconds)
   {
      if (seconds > 59 || seconds < 0)
      {
         throwIllegalArgException("Invalid seconds value exception");
      }
      else if (minutes > 59 || minutes < 0)
      {
         throwIllegalArgException("invalid minutes value exception");
      }
      else if (hours < 0)
      {
         throwIllegalArgException("invalid hours value exception");
      }
   }
   
   // helper method to throw illegal arg exception with custom message
   private void throwIllegalArgException(String message)
   {
      throw new IllegalArgumentException(message);
   }
   
   // helper method to check split duration string parses correctly
   private int[] attemptParseDuration (String[] split)
   {
      // Initialise temporary variables to allow error checking on submitted 
      // duration string hhmmss
      int[] tempDuration = {0,0,0};
      
      // Try to parse duration string to 3 component ints. If parse unsuccessful
      // throw invalid duration string exception message
      try
      {
         tempDuration[0] = Integer.parseInt(split[0]);// hours
         tempDuration[1] = Integer.parseInt(split[1]);// minutes
         tempDuration[2] = Integer.parseInt(split[2]);// seconds
      }
      catch(IllegalArgumentException e)
      {
         throwIllegalArgException("invalid duration string");
      }
      return tempDuration;
   }
   
   /**
    * @param args the command line arguments
    * test harness code for testing of class methods
    */
   public static void main(String[] args)
   {
      // test toString
      Duration d1 = new Duration(1, 33, 33);
      System.out.println("D1: " + d1);
      Duration d2 = new Duration(60, 30, 30);
      System.out.println("D2: " + d2);

      // test add method
      System.out.println("D1 + D2: " + d1.add(d2));
      
      // test subtract method
      System.out.println("D2 - D1: " + d2.subtract(d1));
      //System.out.println("D1 - D2: " + d1.subtract(d2));
      
      // test compareTo
      System.out.println("Compare: ");
      System.out.println("> " + new Duration(2,2,2).compareTo(
                                                         new Duration(1,1,1)));
      System.out.println("= " + new Duration(1,1,1).compareTo(
                                                         new Duration(1,1,1)));
      System.out.println("< " + new Duration(1,1,1).compareTo(
                                                         new Duration(2,2,2)));
      
      // test constructor error catching
      Duration duration = new Duration(1,2,59);
      System.out.println(duration);
      Duration duration2 = new Duration("21:59:01");
      System.out.println(duration2);
   }
}
