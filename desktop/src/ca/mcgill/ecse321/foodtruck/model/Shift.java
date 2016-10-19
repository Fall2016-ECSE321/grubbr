/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.20.1.4071 modeling language!*/

package ca.mcgill.ecse321.foodtruck.model;
import java.sql.Time;

// line 20 "../../../../../FoodTruck.ump"
// line 64 "../../../../../FoodTruck.ump"
public class Shift
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Shift Attributes
  private String dayOfWeek;
  private Time startTime;
  private Time endTime;
  private double numberOfHours;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Shift(String aDayOfWeek, Time aStartTime, Time aEndTime, double aNumberOfHours)
  {
    dayOfWeek = aDayOfWeek;
    startTime = aStartTime;
    endTime = aEndTime;
    numberOfHours = aNumberOfHours;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setDayOfWeek(String aDayOfWeek)
  {
    boolean wasSet = false;
    dayOfWeek = aDayOfWeek;
    wasSet = true;
    return wasSet;
  }

  public boolean setStartTime(Time aStartTime)
  {
    boolean wasSet = false;
    startTime = aStartTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setEndTime(Time aEndTime)
  {
    boolean wasSet = false;
    endTime = aEndTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setNumberOfHours(double aNumberOfHours)
  {
    boolean wasSet = false;
    numberOfHours = aNumberOfHours;
    wasSet = true;
    return wasSet;
  }

  public String getDayOfWeek()
  {
    return dayOfWeek;
  }

  public Time getStartTime()
  {
    return startTime;
  }

  public Time getEndTime()
  {
    return endTime;
  }

  public double getNumberOfHours()
  {
    return numberOfHours;
  }

  public void delete()
  {}


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "dayOfWeek" + ":" + getDayOfWeek()+ "," +
            "numberOfHours" + ":" + getNumberOfHours()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "startTime" + "=" + (getStartTime() != null ? !getStartTime().equals(this)  ? getStartTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "endTime" + "=" + (getEndTime() != null ? !getEndTime().equals(this)  ? getEndTime().toString().replaceAll("  ","    ") : "this" : "null")
     + outputString;
  }
}