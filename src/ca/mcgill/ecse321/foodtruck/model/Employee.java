/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.20.1.4071 modeling language!*/

package ca.mcgill.ecse321.foodtruck.model;
import java.util.*;
import java.sql.Time;

// line 3 "../../../../../FoodTruck.ump"
// line 55 "../../../../../FoodTruck.ump"
public class Employee
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static int nextId = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Employee Attributes
  private String name;
  private String role;
  private double salaryPerHour;

  //Autounique Attributes
  private int id;

  //Employee Associations
  private List<Shift> shifts;
  private FoodTruckManager foodTruckManager;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Employee(String aName, String aRole, double aSalaryPerHour, FoodTruckManager aFoodTruckManager)
  {
    name = aName;
    role = aRole;
    salaryPerHour = aSalaryPerHour;
    id = nextId++;
    shifts = new ArrayList<Shift>();
    boolean didAddFoodTruckManager = setFoodTruckManager(aFoodTruckManager);
    if (!didAddFoodTruckManager)
    {
      throw new RuntimeException("Unable to create employee due to foodTruckManager");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setRole(String aRole)
  {
    boolean wasSet = false;
    role = aRole;
    wasSet = true;
    return wasSet;
  }

  public boolean setSalaryPerHour(double aSalaryPerHour)
  {
    boolean wasSet = false;
    salaryPerHour = aSalaryPerHour;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public String getRole()
  {
    return role;
  }

  public double getSalaryPerHour()
  {
    return salaryPerHour;
  }

  public int getId()
  {
    return id;
  }

  public Shift getShift(int index)
  {
    Shift aShift = shifts.get(index);
    return aShift;
  }

  public List<Shift> getShifts()
  {
    List<Shift> newShifts = Collections.unmodifiableList(shifts);
    return newShifts;
  }

  public int numberOfShifts()
  {
    int number = shifts.size();
    return number;
  }

  public boolean hasShifts()
  {
    boolean has = shifts.size() > 0;
    return has;
  }

  public int indexOfShift(Shift aShift)
  {
    int index = shifts.indexOf(aShift);
    return index;
  }

  public FoodTruckManager getFoodTruckManager()
  {
    return foodTruckManager;
  }

  public static int minimumNumberOfShifts()
  {
    return 0;
  }

  public Shift addShift(String aDayOfWeek, Time aStartTime, Time aEndTime, double aNumberOfHours)
  {
    return new Shift(aDayOfWeek, aStartTime, aEndTime, aNumberOfHours, this);
  }

  public boolean addShift(Shift aShift)
  {
    boolean wasAdded = false;
    if (shifts.contains(aShift)) { return false; }
    Employee existingEmployee = aShift.getEmployee();
    boolean isNewEmployee = existingEmployee != null && !this.equals(existingEmployee);
    if (isNewEmployee)
    {
      aShift.setEmployee(this);
    }
    else
    {
      shifts.add(aShift);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeShift(Shift aShift)
  {
    boolean wasRemoved = false;
    //Unable to remove aShift, as it must always have a employee
    if (!this.equals(aShift.getEmployee()))
    {
      shifts.remove(aShift);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addShiftAt(Shift aShift, int index)
  {  
    boolean wasAdded = false;
    if(addShift(aShift))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfShifts()) { index = numberOfShifts() - 1; }
      shifts.remove(aShift);
      shifts.add(index, aShift);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveShiftAt(Shift aShift, int index)
  {
    boolean wasAdded = false;
    if(shifts.contains(aShift))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfShifts()) { index = numberOfShifts() - 1; }
      shifts.remove(aShift);
      shifts.add(index, aShift);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addShiftAt(aShift, index);
    }
    return wasAdded;
  }

  public boolean setFoodTruckManager(FoodTruckManager aFoodTruckManager)
  {
    boolean wasSet = false;
    if (aFoodTruckManager == null)
    {
      return wasSet;
    }

    FoodTruckManager existingFoodTruckManager = foodTruckManager;
    foodTruckManager = aFoodTruckManager;
    if (existingFoodTruckManager != null && !existingFoodTruckManager.equals(aFoodTruckManager))
    {
      existingFoodTruckManager.removeEmployee(this);
    }
    foodTruckManager.addEmployee(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(int i=shifts.size(); i > 0; i--)
    {
      Shift aShift = shifts.get(i - 1);
      aShift.delete();
    }
    FoodTruckManager placeholderFoodTruckManager = foodTruckManager;
    this.foodTruckManager = null;
    placeholderFoodTruckManager.removeEmployee(this);
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "id" + ":" + getId()+ "," +
            "name" + ":" + getName()+ "," +
            "role" + ":" + getRole()+ "," +
            "salaryPerHour" + ":" + getSalaryPerHour()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "foodTruckManager = "+(getFoodTruckManager()!=null?Integer.toHexString(System.identityHashCode(getFoodTruckManager())):"null")
     + outputString;
  }
}