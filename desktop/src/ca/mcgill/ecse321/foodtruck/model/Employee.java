/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.20.1.4071 modeling language!*/

package ca.mcgill.ecse321.foodtruck.model;

// line 3 "../../../../../FoodTruck.ump"
// line 43 "../../../../../FoodTruck.ump"
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

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Employee(String aName, String aRole, double aSalaryPerHour)
  {
    name = aName;
    role = aRole;
    salaryPerHour = aSalaryPerHour;
    id = nextId++;
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

  public void delete()
  {}


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "id" + ":" + getId()+ "," +
            "name" + ":" + getName()+ "," +
            "role" + ":" + getRole()+ "," +
            "salaryPerHour" + ":" + getSalaryPerHour()+ "]"
     + outputString;
  }
}