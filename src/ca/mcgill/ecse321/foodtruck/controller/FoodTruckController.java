package ca.mcgill.ecse321.foodtruck.controller;

import ca.mcgill.ecse321.foodtruck.model.FoodTruckManager;
import ca.mcgill.ecse321.foodtruck.model.MenuItem;
import ca.mcgill.ecse321.foodtruck.persistence.PersistenceXStream;

/**
 * Controller for the Food Truck Management System.
 * 
 * @author erick
 * @version 0.1
 *
 */

public class FoodTruckController {

	/**
	 * Constructor.
	 */
	public FoodTruckController() {
	}
	
	/**
	 * Adds a new item to the menu.
	 * @param itemName	the name of the item
	 * @param itemPrice	the price of the item
	 */
	
	public void createMenuItem(String itemName, double itemPrice) {

		MenuItem item = new MenuItem(itemName, itemPrice, 0);
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		ftms.addMenuItem(item);
		PersistenceXStream.saveToXMLwithXStream(ftms);
	}
}
