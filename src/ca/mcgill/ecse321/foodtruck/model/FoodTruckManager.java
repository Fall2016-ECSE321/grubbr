/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.20.1.4071 modeling language!*/

package ca.mcgill.ecse321.foodtruck.model;
import java.util.*;

// line 11 "../../../../../FoodTruck.ump"
// line 61 "../../../../../FoodTruck.ump"
public class FoodTruckManager
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static FoodTruckManager theInstance = null;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //FoodTruckManager Attributes
  private String name;

  //FoodTruckManager Associations
  private List<Employee> employees;
  private List<Order> orders;
  private List<Supply> supplies;
  private List<Equipment> equipment;
  private Menu menu;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  private FoodTruckManager()
  {
    name = null;
    employees = new ArrayList<Employee>();
    orders = new ArrayList<Order>();
    supplies = new ArrayList<Supply>();
    equipment = new ArrayList<Equipment>();
  }

  public static FoodTruckManager getInstance()
  {
    if(theInstance == null)
    {
      theInstance = new FoodTruckManager();
    }
    return theInstance;
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

  public String getName()
  {
    return name;
  }

  public Employee getEmployee(int index)
  {
    Employee aEmployee = employees.get(index);
    return aEmployee;
  }

  public List<Employee> getEmployees()
  {
    List<Employee> newEmployees = Collections.unmodifiableList(employees);
    return newEmployees;
  }

  public int numberOfEmployees()
  {
    int number = employees.size();
    return number;
  }

  public boolean hasEmployees()
  {
    boolean has = employees.size() > 0;
    return has;
  }

  public int indexOfEmployee(Employee aEmployee)
  {
    int index = employees.indexOf(aEmployee);
    return index;
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

  public Supply getSupply(int index)
  {
    Supply aSupply = supplies.get(index);
    return aSupply;
  }

  public List<Supply> getSupplies()
  {
    List<Supply> newSupplies = Collections.unmodifiableList(supplies);
    return newSupplies;
  }

  public int numberOfSupplies()
  {
    int number = supplies.size();
    return number;
  }

  public boolean hasSupplies()
  {
    boolean has = supplies.size() > 0;
    return has;
  }

  public int indexOfSupply(Supply aSupply)
  {
    int index = supplies.indexOf(aSupply);
    return index;
  }

  public Equipment getEquipment(int index)
  {
    Equipment aEquipment = equipment.get(index);
    return aEquipment;
  }

  public List<Equipment> getEquipment()
  {
    List<Equipment> newEquipment = Collections.unmodifiableList(equipment);
    return newEquipment;
  }

  public int numberOfEquipment()
  {
    int number = equipment.size();
    return number;
  }

  public boolean hasEquipment()
  {
    boolean has = equipment.size() > 0;
    return has;
  }

  public int indexOfEquipment(Equipment aEquipment)
  {
    int index = equipment.indexOf(aEquipment);
    return index;
  }

  public Menu getMenu()
  {
    return menu;
  }

  public boolean hasMenu()
  {
    boolean has = menu != null;
    return has;
  }

  public static int minimumNumberOfEmployees()
  {
    return 0;
  }

  public Employee addEmployee(String aName, String aRole, double aSalaryPerHour)
  {
    return new Employee(aName, aRole, aSalaryPerHour, this);
  }

  public boolean addEmployee(Employee aEmployee)
  {
    boolean wasAdded = false;
    if (employees.contains(aEmployee)) { return false; }
    FoodTruckManager existingFoodTruckManager = aEmployee.getFoodTruckManager();
    boolean isNewFoodTruckManager = existingFoodTruckManager != null && !this.equals(existingFoodTruckManager);
    if (isNewFoodTruckManager)
    {
      aEmployee.setFoodTruckManager(this);
    }
    else
    {
      employees.add(aEmployee);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeEmployee(Employee aEmployee)
  {
    boolean wasRemoved = false;
    //Unable to remove aEmployee, as it must always have a foodTruckManager
    if (!this.equals(aEmployee.getFoodTruckManager()))
    {
      employees.remove(aEmployee);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addEmployeeAt(Employee aEmployee, int index)
  {  
    boolean wasAdded = false;
    if(addEmployee(aEmployee))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEmployees()) { index = numberOfEmployees() - 1; }
      employees.remove(aEmployee);
      employees.add(index, aEmployee);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveEmployeeAt(Employee aEmployee, int index)
  {
    boolean wasAdded = false;
    if(employees.contains(aEmployee))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEmployees()) { index = numberOfEmployees() - 1; }
      employees.remove(aEmployee);
      employees.add(index, aEmployee);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addEmployeeAt(aEmployee, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfOrders()
  {
    return 0;
  }

  public Order addOrder(double aPrice)
  {
    return new Order(aPrice, this);
  }

  public boolean addOrder(Order aOrder)
  {
    boolean wasAdded = false;
    if (orders.contains(aOrder)) { return false; }
    FoodTruckManager existingFoodTruckManager = aOrder.getFoodTruckManager();
    boolean isNewFoodTruckManager = existingFoodTruckManager != null && !this.equals(existingFoodTruckManager);
    if (isNewFoodTruckManager)
    {
      aOrder.setFoodTruckManager(this);
    }
    else
    {
      orders.add(aOrder);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeOrder(Order aOrder)
  {
    boolean wasRemoved = false;
    //Unable to remove aOrder, as it must always have a foodTruckManager
    if (!this.equals(aOrder.getFoodTruckManager()))
    {
      orders.remove(aOrder);
      wasRemoved = true;
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

  public static int minimumNumberOfSupplies()
  {
    return 0;
  }

  public Supply addSupply(String aName, int aCount)
  {
    return new Supply(aName, aCount, this);
  }

  public boolean addSupply(Supply aSupply)
  {
    boolean wasAdded = false;
    if (supplies.contains(aSupply)) { return false; }
    FoodTruckManager existingFoodTruckManager = aSupply.getFoodTruckManager();
    boolean isNewFoodTruckManager = existingFoodTruckManager != null && !this.equals(existingFoodTruckManager);
    if (isNewFoodTruckManager)
    {
      aSupply.setFoodTruckManager(this);
    }
    else
    {
      supplies.add(aSupply);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSupply(Supply aSupply)
  {
    boolean wasRemoved = false;
    //Unable to remove aSupply, as it must always have a foodTruckManager
    if (!this.equals(aSupply.getFoodTruckManager()))
    {
      supplies.remove(aSupply);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addSupplyAt(Supply aSupply, int index)
  {  
    boolean wasAdded = false;
    if(addSupply(aSupply))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSupplies()) { index = numberOfSupplies() - 1; }
      supplies.remove(aSupply);
      supplies.add(index, aSupply);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSupplyAt(Supply aSupply, int index)
  {
    boolean wasAdded = false;
    if(supplies.contains(aSupply))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSupplies()) { index = numberOfSupplies() - 1; }
      supplies.remove(aSupply);
      supplies.add(index, aSupply);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSupplyAt(aSupply, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfEquipment()
  {
    return 0;
  }

  public Equipment addEquipment(String aName, int aCount)
  {
    return new Equipment(aName, aCount, this);
  }

  public boolean addEquipment(Equipment aEquipment)
  {
    boolean wasAdded = false;
    if (equipment.contains(aEquipment)) { return false; }
    FoodTruckManager existingFoodTruckManager = aEquipment.getFoodTruckManager();
    boolean isNewFoodTruckManager = existingFoodTruckManager != null && !this.equals(existingFoodTruckManager);
    if (isNewFoodTruckManager)
    {
      aEquipment.setFoodTruckManager(this);
    }
    else
    {
      equipment.add(aEquipment);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeEquipment(Equipment aEquipment)
  {
    boolean wasRemoved = false;
    //Unable to remove aEquipment, as it must always have a foodTruckManager
    if (!this.equals(aEquipment.getFoodTruckManager()))
    {
      equipment.remove(aEquipment);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addEquipmentAt(Equipment aEquipment, int index)
  {  
    boolean wasAdded = false;
    if(addEquipment(aEquipment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEquipment()) { index = numberOfEquipment() - 1; }
      equipment.remove(aEquipment);
      equipment.add(index, aEquipment);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveEquipmentAt(Equipment aEquipment, int index)
  {
    boolean wasAdded = false;
    if(equipment.contains(aEquipment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEquipment()) { index = numberOfEquipment() - 1; }
      equipment.remove(aEquipment);
      equipment.add(index, aEquipment);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addEquipmentAt(aEquipment, index);
    }
    return wasAdded;
  }

  public boolean setMenu(Menu aNewMenu)
  {
    boolean wasSet = false;
    if (menu != null && !menu.equals(aNewMenu) && equals(menu.getFoodTruckManager()))
    {
      //Unable to setMenu, as existing menu would become an orphan
      return wasSet;
    }

    menu = aNewMenu;
    FoodTruckManager anOldFoodTruckManager = aNewMenu != null ? aNewMenu.getFoodTruckManager() : null;

    if (!this.equals(anOldFoodTruckManager))
    {
      if (anOldFoodTruckManager != null)
      {
        anOldFoodTruckManager.menu = null;
      }
      if (menu != null)
      {
        menu.setFoodTruckManager(this);
      }
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(int i=employees.size(); i > 0; i--)
    {
      Employee aEmployee = employees.get(i - 1);
      aEmployee.delete();
    }
    for(int i=orders.size(); i > 0; i--)
    {
      Order aOrder = orders.get(i - 1);
      aOrder.delete();
    }
    for(int i=supplies.size(); i > 0; i--)
    {
      Supply aSupply = supplies.get(i - 1);
      aSupply.delete();
    }
    for(int i=equipment.size(); i > 0; i--)
    {
      Equipment aEquipment = equipment.get(i - 1);
      aEquipment.delete();
    }
    Menu existingMenu = menu;
    menu = null;
    if (existingMenu != null)
    {
      existingMenu.delete();
    }
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "name" + ":" + getName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "menu = "+(getMenu()!=null?Integer.toHexString(System.identityHashCode(getMenu())):"null")
     + outputString;
  }
}