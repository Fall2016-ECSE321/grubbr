package ca.mcgill.ecse321.foodtruck.controller;

import org.apache.commons.lang3.text.WordUtils;

import ca.mcgill.ecse321.foodtruck.model.Equipment;
import ca.mcgill.ecse321.foodtruck.model.FoodTruckManager;
import ca.mcgill.ecse321.foodtruck.model.MenuItem;
import ca.mcgill.ecse321.foodtruck.model.Supply;
import ca.mcgill.ecse321.foodtruck.persistence.PersistenceXStream;

/**
 * Controller for the Food Truck Management System.
 * 
 * @author erick
 * @version 0.2
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
	 * @param itemName	the name of the item.
	 * @param itemPrice	the price of the item.
	 */
	
	public void createMenuItem(String itemName,String itemPrice) throws InvalidInputException{
		
		String error = "";
		
		if (isEmpty(itemName)) {
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
	 * Adds a new supply to the supply list.
	 * 
	 * Each supply has a user-defined name and a count. 
	 * By default, the count of a supply is 0, and it is up to the
	 * user to instantiate its initial quantity.
	 * @param supplyName	Name of the food supply in question.
	 */
	
	public void createSupply(String supplyName) {
		
		supplyName = WordUtils.capitalizeFully(supplyName);
		Supply supply = new Supply(supplyName,0);
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		ftms.addSupply(supply);
		PersistenceXStream.saveToXMLwithXStream(ftms);
	}
	
	/**
	 * Sets the quantity of an existing food supply in the inventory.
	 * 
	 * A value under 0 will give the command to delete the item entirely.
	 * @param supply	Food supply whose quantity is to be edited.
	 * @param count		Quantity to be set.
	 */
	
	public void editSupplyQuantity(Supply supply,int count) {
		
		if (count < 0) {
			supply.delete();
		} else {
			supply.setCount(count);
		}
		
	}
	
	/**
	 * Adds a new equipment to the equipment list.
	 * 
	 * Each piece of equipment has a user-defined name and a count. 
	 * By default, the count of an equipment is 0, and it is up to the
	 * user to instantiate its initial quantity.
	 * @param equipmentName
	 */
	
	public void createEquipment(String equipmentName) {
		
		equipmentName = WordUtils.capitalizeFully(equipmentName);
		Equipment equipment = new Equipment(equipmentName,0);
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		ftms.addEquipment(equipment);
		PersistenceXStream.saveToXMLwithXStream(ftms);
	}
	
	/**
	 * Sets the quantity of an existing equipment in the inventory.
	 * 
	 * A value under 0 will give the command to delete the item entirely.
	 * @param equipment	Equipment whose quantity is to be edited.
	 * @param count		Quantity to be set.
	 */
	
	public void editEquipmentQuantity(Equipment equipment,int count) {
		
		if (count < 0) {
			equipment.delete();
		} else {
			equipment.setCount(count);
		}
		
	}
	
	/**
	 * Determines whether or not the text in the label is a valid dollar value.
	 * @param price The string fetched from the itemPrice text field.
	 * @return A boolean value that indicates whether or not the input is correct.
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
	
	/**
	 * Determines whether or not the text in the label is empty.
	 * @param text	The string that you want to evaluate.
	 * @return A boolean value that indicates whether or not the string is empty or not.
	 */
	
	private boolean isEmpty(String text) {
		
		if (text == null || text.trim().length() == 0) {
			return true;
		}
		return false;
	}
}
