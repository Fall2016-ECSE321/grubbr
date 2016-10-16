/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.20.1.4071 modeling language!*/

package ca.mcgill.ecse321.foodtruck.model;
import java.util.*;

// line 28 "../../../../../FoodTruck.ump"
// line 76 "../../../../../FoodTruck.ump"
public class Menu
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Menu Associations
  private List<MenuItem> menuItems;
  private FoodTruckManager foodTruckManager;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Menu(FoodTruckManager aFoodTruckManager)
  {
    menuItems = new ArrayList<MenuItem>();
    boolean didAddFoodTruckManager = setFoodTruckManager(aFoodTruckManager);
    if (!didAddFoodTruckManager)
    {
      throw new RuntimeException("Unable to create menu due to foodTruckManager");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

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

  public MenuItem addMenuItem(String aName, String aDescription, double aAmountSold, double aPrice)
  {
    return new MenuItem(aName, aDescription, aAmountSold, aPrice, this);
  }

  public boolean addMenuItem(MenuItem aMenuItem)
  {
    boolean wasAdded = false;
    if (menuItems.contains(aMenuItem)) { return false; }
    Menu existingMenu = aMenuItem.getMenu();
    boolean isNewMenu = existingMenu != null && !this.equals(existingMenu);
    if (isNewMenu)
    {
      aMenuItem.setMenu(this);
    }
    else
    {
      menuItems.add(aMenuItem);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeMenuItem(MenuItem aMenuItem)
  {
    boolean wasRemoved = false;
    //Unable to remove aMenuItem, as it must always have a menu
    if (!this.equals(aMenuItem.getMenu()))
    {
      menuItems.remove(aMenuItem);
      wasRemoved = true;
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

  public boolean setFoodTruckManager(FoodTruckManager aNewFoodTruckManager)
  {
    boolean wasSet = false;
    if (aNewFoodTruckManager == null)
    {
      //Unable to setFoodTruckManager to null, as menu must always be associated to a foodTruckManager
      return wasSet;
    }
    
    Menu existingMenu = aNewFoodTruckManager.getMenu();
    if (existingMenu != null && !equals(existingMenu))
    {
      //Unable to setFoodTruckManager, the current foodTruckManager already has a menu, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    FoodTruckManager anOldFoodTruckManager = foodTruckManager;
    foodTruckManager = aNewFoodTruckManager;
    foodTruckManager.setMenu(this);

    if (anOldFoodTruckManager != null)
    {
      anOldFoodTruckManager.setMenu(null);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(int i=menuItems.size(); i > 0; i--)
    {
      MenuItem aMenuItem = menuItems.get(i - 1);
      aMenuItem.delete();
    }
    FoodTruckManager existingFoodTruckManager = foodTruckManager;
    foodTruckManager = null;
    if (existingFoodTruckManager != null)
    {
      existingFoodTruckManager.setMenu(null);
    }
  }

}