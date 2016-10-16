/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.20.1.4071 modeling language!*/

package ca.mcgill.ecse321.foodtruck.model;
import java.util.*;

// line 32 "../../../../../FoodTruck.ump"
// line 81 "../../../../../FoodTruck.ump"
public class MenuItem
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //MenuItem Attributes
  private String name;
  private String description;
  private double amountSold;
  private double price;

  //MenuItem Associations
  private Menu menu;
  private List<Order> orders;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public MenuItem(String aName, String aDescription, double aAmountSold, double aPrice, Menu aMenu)
  {
    name = aName;
    description = aDescription;
    amountSold = aAmountSold;
    price = aPrice;
    boolean didAddMenu = setMenu(aMenu);
    if (!didAddMenu)
    {
      throw new RuntimeException("Unable to create menuItem due to menu");
    }
    orders = new ArrayList<Order>();
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

  public boolean setDescription(String aDescription)
  {
    boolean wasSet = false;
    description = aDescription;
    wasSet = true;
    return wasSet;
  }

  public boolean setAmountSold(double aAmountSold)
  {
    boolean wasSet = false;
    amountSold = aAmountSold;
    wasSet = true;
    return wasSet;
  }

  public boolean setPrice(double aPrice)
  {
    boolean wasSet = false;
    price = aPrice;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public String getDescription()
  {
    return description;
  }

  public double getAmountSold()
  {
    return amountSold;
  }

  public double getPrice()
  {
    return price;
  }

  public Menu getMenu()
  {
    return menu;
  }

  public Order getOrder(int index)
  {
    Order aOrder = orders.get(index);
    return aOrder;
  }

  public List<Order> getOrders()
  {
    List<Order> newOrders = Collections.unmodifiableList(orders);
    return newOrders;
  }

  public int numberOfOrders()
  {
    int number = orders.size();
    return number;
  }

  public boolean hasOrders()
  {
    boolean has = orders.size() > 0;
    return has;
  }

  public int indexOfOrder(Order aOrder)
  {
    int index = orders.indexOf(aOrder);
    return index;
  }

  public boolean setMenu(Menu aMenu)
  {
    boolean wasSet = false;
    if (aMenu == null)
    {
      return wasSet;
    }

    Menu existingMenu = menu;
    menu = aMenu;
    if (existingMenu != null && !existingMenu.equals(aMenu))
    {
      existingMenu.removeMenuItem(this);
    }
    menu.addMenuItem(this);
    wasSet = true;
    return wasSet;
  }

  public static int minimumNumberOfOrders()
  {
    return 0;
  }

  public boolean addOrder(Order aOrder)
  {
    boolean wasAdded = false;
    if (orders.contains(aOrder)) { return false; }
    orders.add(aOrder);
    if (aOrder.indexOfMenuItem(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aOrder.addMenuItem(this);
      if (!wasAdded)
      {
        orders.remove(aOrder);
      }
    }
    return wasAdded;
  }

  public boolean removeOrder(Order aOrder)
  {
    boolean wasRemoved = false;
    if (!orders.contains(aOrder))
    {
      return wasRemoved;
    }

    int oldIndex = orders.indexOf(aOrder);
    orders.remove(oldIndex);
    if (aOrder.indexOfMenuItem(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aOrder.removeMenuItem(this);
      if (!wasRemoved)
      {
        orders.add(oldIndex,aOrder);
      }
    }
    return wasRemoved;
  }

  public boolean addOrderAt(Order aOrder, int index)
  {  
    boolean wasAdded = false;
    if(addOrder(aOrder))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOrders()) { index = numberOfOrders() - 1; }
      orders.remove(aOrder);
      orders.add(index, aOrder);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveOrderAt(Order aOrder, int index)
  {
    boolean wasAdded = false;
    if(orders.contains(aOrder))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOrders()) { index = numberOfOrders() - 1; }
      orders.remove(aOrder);
      orders.add(index, aOrder);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addOrderAt(aOrder, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    Menu placeholderMenu = menu;
    this.menu = null;
    placeholderMenu.removeMenuItem(this);
    ArrayList<Order> copyOfOrders = new ArrayList<Order>(orders);
    orders.clear();
    for(Order aOrder : copyOfOrders)
    {
      aOrder.removeMenuItem(this);
    }
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "description" + ":" + getDescription()+ "," +
            "amountSold" + ":" + getAmountSold()+ "," +
            "price" + ":" + getPrice()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "menu = "+(getMenu()!=null?Integer.toHexString(System.identityHashCode(getMenu())):"null")
     + outputString;
  }
}