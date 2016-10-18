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
	
	public void createMenuItem(String itemName,String itemPrice) throws InvalidInputException{
		
		String error = "";
		
		if (itemName == null || itemName.trim().length() == 0) {
			error += "Menu item name cannot be empty! ";
		}
		
		try {
			double price = Double.parseDouble(itemPrice);
			
			if (!hasCorrectAmountOfDecimalPlaces(itemPrice)) {
				error += "Menu item price cannot contain fractions of cents! ";
			} else if (price <= 0) {
				error += "Menu item price must be greater than 0! ";
			}
		} catch (NumberFormatException e) {
			error += "Menu item price must be a number! ";
		}
		
		error = error.trim();
		
		if (error.length()>0) {
			throw new InvalidInputException(error);
		}
		
		MenuItem item = new MenuItem(itemName, Double.parseDouble(itemPrice), 0);
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		ftms.addMenuItem(item);
		PersistenceXStream.saveToXMLwithXStream(ftms);
	}
	
	/**
	 * Determines whether or not the text in the label is a valid price.
	 * @param price The string fetched from the itemPrice text field
	 * @return A boolean value that indicates whether or not the input is correct
	 */
	
	private boolean hasCorrectAmountOfDecimalPlaces(String price) {
		
		//return true if there are no decimal places
		if (price.indexOf('.')==-1) {
			return true;
		}
		
		//return true if there are one or two decimal places
		if (price.length()-1-price.indexOf('.')<=2) {
			return true;
		}
		
		return false;
		
	}
}
