/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.20.1.4071 modeling language!*/

package ca.mcgill.ecse321.foodtruck.model;

// line 39 "../../../../../FoodTruck.ump"
// line 86 "../../../../../FoodTruck.ump"
public class Supply
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Supply Attributes
  private String name;
  private int count;

  //Supply Associations
  private FoodTruckManager foodTruckManager;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Supply(String aName, int aCount, FoodTruckManager aFoodTruckManager)
  {
    name = aName;
    count = aCount;
    boolean didAddFoodTruckManager = setFoodTruckManager(aFoodTruckManager);
    if (!didAddFoodTruckManager)
    {
      throw new RuntimeException("Unable to create supply due to foodTruckManager");
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

  public boolean setCount(int aCount)
  {
    boolean wasSet = false;
    count = aCount;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public int getCount()
  {
    return count;
  }

  public FoodTruckManager getFoodTruckManager()
  {
    return foodTruckManager;
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
      existingFoodTruckManager.removeSupply(this);
    }
    foodTruckManager.addSupply(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    FoodTruckManager placeholderFoodTruckManager = foodTruckManager;
    this.foodTruckManager = null;
    placeholderFoodTruckManager.removeSupply(this);
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "count" + ":" + getCount()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "foodTruckManager = "+(getFoodTruckManager()!=null?Integer.toHexString(System.identityHashCode(getFoodTruckManager())):"null")
     + outputString;
  }
}