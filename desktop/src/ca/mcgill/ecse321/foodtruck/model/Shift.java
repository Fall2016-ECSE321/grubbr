/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.20.1.4071 modeling language!*/

package ca.mcgill.ecse321.foodtruck.model;
import java.sql.Time;

// line 19 "../../../../../FoodTruck.ump"
// line 57 "../../../../../FoodTruck.ump"
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

  //Shift Associations
  private Employee employee;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Shift(String aDayOfWeek, Time aStartTime, Time aEndTime, double aNumberOfHours, Employee aEmployee)
  {
    dayOfWeek = aDayOfWeek;
    startTime = aStartTime;
    endTime = aEndTime;
    numberOfHours = aNumberOfHours;
    if (!setEmployee(aEmployee))
    {
      throw new RuntimeException("Unable to create Shift due to aEmployee");
    }
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

  public Employee getEmployee()
  {
    return employee;
  }

  public boolean setEmployee(Employee aNewEmployee)
  {
    boolean wasSet = false;
    if (aNewEmployee != null)
    {
      employee = aNewEmployee;
      wasSet = true;
    }
    return wasSet;
  }

  public void delete()
  {
    employee = null;
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "dayOfWeek" + ":" + getDayOfWeek()+ "," +
            "numberOfHours" + ":" + getNumberOfHours()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "startTime" + "=" + (getStartTime() != null ? !getStartTime().equals(this)  ? getStartTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "endTime" + "=" + (getEndTime() != null ? !getEndTime().equals(this)  ? getEndTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "employee = "+(getEmployee()!=null?Integer.toHexString(System.identityHashCode(getEmployee())):"null")
     + outputString;
  }
}