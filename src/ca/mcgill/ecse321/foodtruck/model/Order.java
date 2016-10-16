/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.20.1.4071 modeling language!*/

package ca.mcgill.ecse321.foodtruck.model;
import java.util.*;

// line 45 "../../../../../FoodTruck.ump"
// line 87 "../../../../../FoodTruck.ump"
public class Order
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Order Attributes
  private double price;

  //Order Associations
  private List<MenuItem> menuItems;
  private FoodTruckManager foodTruckManager;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Order(double aPrice, FoodTruckManager aFoodTruckManager)
  {
    price = aPrice;
    menuItems = new ArrayList<MenuItem>();
    boolean didAddFoodTruckManager = setFoodTruckManager(aFoodTruckManager);
    if (!didAddFoodTruckManager)
    {
      throw new RuntimeException("Unable to create order due to foodTruckManager");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPrice(double aPrice)
  {
    boolean wasSet = false;
    price = aPrice;
    wasSet = true;
    return wasSet;
  }

  public double getPrice()
  {
    return price;
  }

  public MenuItem getMenuItem(int index)
  {
    MenuItem aMenuItem = menuItems.get(index);
    return aMenuItem;
  }

  public List<MenuItem> getMenuItems()
  {
    List<MenuItem> newMenuItems = Collections.unmodifiableList(menuItems);
    return newMenuItems;
  }

  public int numberOfMenuItems()
  {
    int number = menuItems.size();
    return number;
  }

  public boolean hasMenuItems()
  {
    boolean has = menuItems.size() > 0;
    return has;
  }

  public int indexOfMenuItem(MenuItem aMenuItem)
  {
    int index = menuItems.indexOf(aMenuItem);
    return index;
  }

  public FoodTruckManager getFoodTruckManager()
  {
    return foodTruckManager;
  }

  public static int minimumNumberOfMenuItems()
  {
    return 0;
  }

  public boolean addMenuItem(MenuItem aMenuItem)
  {
    boolean wasAdded = false;
    if (menuItems.contains(aMenuItem)) { return false; }
    menuItems.add(aMenuItem);
    if (aMenuItem.indexOfOrder(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aMenuItem.addOrder(this);
      if (!wasAdded)
      {
        menuItems.remove(aMenuItem);
      }
    }
    return wasAdded;
  }

  public boolean removeMenuItem(MenuItem aMenuItem)
  {
    boolean wasRemoved = false;
    if (!menuItems.contains(aMenuItem))
    {
      return wasRemoved;
    }

    int oldIndex = menuItems.indexOf(aMenuItem);
    menuItems.remove(oldIndex);
    if (aMenuItem.indexOfOrder(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aMenuItem.removeOrder(this);
      if (!wasRemoved)
      {
        menuItems.add(oldIndex,aMenuItem);
      }
    }
    return wasRemoved;
  }

  public boolean addMenuItemAt(MenuItem aMenuItem, int index)
  {  
    boolean wasAdded = false;
    if(addMenuItem(aMenuItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMenuItems()) { index = numberOfMenuItems() - 1; }
      menuItems.remove(aMenuItem);
      menuItems.add(index, aMenuItem);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveMenuItemAt(MenuItem aMenuItem, int index)
  {
    boolean wasAdded = false;
    if(menuItems.contains(aMenuItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMenuItems()) { index = numberOfMenuItems() - 1; }
      menuItems.remove(aMenuItem);
      menuItems.add(index, aMenuItem);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addMenuItemAt(aMenuItem, index);
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
      existingFoodTruckManager.removeOrder(this);
    }
    foodTruckManager.addOrder(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ArrayList<MenuItem> copyOfMenuItems = new ArrayList<MenuItem>(menuItems);
    menuItems.clear();
    for(MenuItem aMenuItem : copyOfMenuItems)
    {
      aMenuItem.removeOrder(this);
    }
    FoodTruckManager placeholderFoodTruckManager = foodTruckManager;
    this.foodTruckManager = null;
    placeholderFoodTruckManager.removeOrder(this);
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "price" + ":" + getPrice()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "foodTruckManager = "+(getFoodTruckManager()!=null?Integer.toHexString(System.identityHashCode(getFoodTruckManager())):"null")
     + outputString;
  }
}