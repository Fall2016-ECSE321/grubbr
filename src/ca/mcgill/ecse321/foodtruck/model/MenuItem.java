/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.20.1.4071 modeling language!*/

package ca.mcgill.ecse321.foodtruck.model;

// line 27 "../../../../../FoodTruck.ump"
// line 69 "../../../../../FoodTruck.ump"
public class MenuItem
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //MenuItem Attributes
  private String name;
  private double amountSold;
  private double price;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public MenuItem(String aName, double aAmountSold, double aPrice)
  {
    name = aName;
    amountSold = aAmountSold;
    price = aPrice;
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

  public double getAmountSold()
  {
    return amountSold;
  }

  public double getPrice()
  {
    return price;
  }

  public void delete()
  {}


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "amountSold" + ":" + getAmountSold()+ "," +
            "price" + ":" + getPrice()+ "]"
     + outputString;
  }
}