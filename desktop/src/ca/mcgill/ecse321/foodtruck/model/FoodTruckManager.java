/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.20.1.4071 modeling language!*/

package ca.mcgill.ecse321.foodtruck.model;
import java.util.*;

// line 11 "../../../../../FoodTruck.ump"
// line 48 "../../../../../FoodTruck.ump"
public class FoodTruckManager
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static FoodTruckManager theInstance = null;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //FoodTruckManager Associations
  private List<Employee> employees;
  private List<Supply> supplies;
  private List<Equipment> equipment;
  private List<MenuItem> menuItems;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  private FoodTruckManager()
  {
    employees = new ArrayList<Employee>();
    supplies = new ArrayList<Supply>();
    equipment = new ArrayList<Equipment>();
    menuItems = new ArrayList<MenuItem>();
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

  public static int minimumNumberOfEmployees()
  {
    return 0;
  }

  public boolean addEmployee(Employee aEmployee)
  {
    boolean wasAdded = false;
    if (employees.contains(aEmployee)) { return false; }
    employees.add(aEmployee);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeEmployee(Employee aEmployee)
  {
    boolean wasRemoved = false;
    if (employees.contains(aEmployee))
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

  public static int minimumNumberOfSupplies()
  {
    return 0;
  }

  public boolean addSupply(Supply aSupply)
  {
    boolean wasAdded = false;
    if (supplies.contains(aSupply)) { return false; }
    supplies.add(aSupply);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSupply(Supply aSupply)
  {
    boolean wasRemoved = false;
    if (supplies.contains(aSupply))
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

  public boolean addEquipment(Equipment aEquipment)
  {
    boolean wasAdded = false;
    if (equipment.contains(aEquipment)) { return false; }
    equipment.add(aEquipment);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeEquipment(Equipment aEquipment)
  {
    boolean wasRemoved = false;
    if (equipment.contains(aEquipment))
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

  public static int minimumNumberOfMenuItems()
  {
    return 0;
  }

  public boolean addMenuItem(MenuItem aMenuItem)
  {
    boolean wasAdded = false;
    if (menuItems.contains(aMenuItem)) { return false; }
    menuItems.add(aMenuItem);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeMenuItem(MenuItem aMenuItem)
  {
    boolean wasRemoved = false;
    if (menuItems.contains(aMenuItem))
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

  public void delete()
  {
    employees.clear();
    supplies.clear();
    equipment.clear();
    menuItems.clear();
  }

}