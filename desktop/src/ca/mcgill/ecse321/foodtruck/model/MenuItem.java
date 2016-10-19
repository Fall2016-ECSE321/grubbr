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
  private double price;
  private int amountSold;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public MenuItem(String aName, double aPrice, int aAmountSold)
  {
    name = aName;
    price = aPrice;
    amountSold = aAmountSold;
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

  public boolean setPrice(double aPrice)
  {
    boolean wasSet = false;
    price = aPrice;
    wasSet = true;
    return wasSet;
  }

  public boolean setAmountSold(int aAmountSold)
  {
    boolean wasSet = false;
    amountSold = aAmountSold;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public double getPrice()
  {
    return price;
  }

  public int getAmountSold()
  {
    return amountSold;
  }

  public void delete()
  {}


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "price" + ":" + getPrice()+ "," +
            "amountSold" + ":" + getAmountSold()+ "]"
     + outputString;
  }
}