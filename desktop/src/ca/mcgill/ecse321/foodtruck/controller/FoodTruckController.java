package ca.mcgill.ecse321.foodtruck.controller;

import java.sql.Time;

import org.apache.commons.lang3.text.WordUtils;

import ca.mcgill.ecse321.foodtruck.controller.InvalidInputException;
import ca.mcgill.ecse321.foodtruck.model.Employee;
import ca.mcgill.ecse321.foodtruck.model.Equipment;
import ca.mcgill.ecse321.foodtruck.model.FoodTruckManager;
import ca.mcgill.ecse321.foodtruck.model.MenuItem;
import ca.mcgill.ecse321.foodtruck.model.Shift;
import ca.mcgill.ecse321.foodtruck.model.Supply;
import ca.mcgill.ecse321.foodtruck.persistence.PersistenceXStream;

/**
 * Controller for the Food Truck Management System.
 * 
 * @author erick
 * @version 0.3
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
		} catch (NullPointerException e) {
			error += "Menu item price must be a number! ";
		}
		
		error = error.trim();
		
		if (error.length()>0) {
			throw new InvalidInputException(error);
		}
		
		itemName = WordUtils.capitalizeFully(itemName);
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
	
	public void createSupply(String supplyName) throws InvalidInputException {
		
		String error = "";
		
		if (isEmpty(supplyName)) {
			error += "Supply name cannot be empty! ";
		}
		
		error = error.trim();
		
		if (error.length()>0) {
			throw new InvalidInputException(error);
		}
		
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
	
	public void editSupplyQuantity(Supply supply,String count) throws InvalidInputException {

		FoodTruckManager ftms = FoodTruckManager.getInstance();
		String error = "";
		
		int quantity = 0;
		try {
			quantity = Integer.parseInt(count);
		} catch (NumberFormatException e) {
			error += "Supply count must be a number! ";
		} catch (NullPointerException e) {
			error += "Supply count must be a number! ";
		}
		
		error = error.trim();
		
		if (error.length()>0) {
			throw new InvalidInputException(error);
		}
		
		if (quantity < 0) {
			ftms.removeSupply(supply);
		} else {
			supply.setCount(quantity);
		}
		PersistenceXStream.saveToXMLwithXStream(ftms);
	}
	
	/**
	 * Adds a new equipment to the equipment list.
	 * 
	 * Each piece of equipment has a user-defined name and a count. 
	 * By default, the count of an equipment is 0, and it is up to the
	 * user to instantiate its initial quantity.
	 * @param equipmentName
	 */
	
	public void createEquipment(String equipmentName) throws InvalidInputException {
		
		String error = "";
		
		if (isEmpty(equipmentName)) {
			error += "Equipment name cannot be empty! ";
		}
		
		error = error.trim();
		
		if (error.length()>0) {
			throw new InvalidInputException(error);
		}
		
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
	
	public void editEquipmentQuantity(Equipment equipment,String count) throws InvalidInputException {

		FoodTruckManager ftms = FoodTruckManager.getInstance();
		String error="";
		int quantity = 0;
		try {
			quantity = Integer.parseInt(count);
		} catch (NumberFormatException e) {
			error += "Equipment count must be a number! ";
		} catch (NullPointerException e) {
			error += "Equipment count must be a number! ";
		}
		
		error = error.trim();
		
		if (error.length()>0) {
			throw new InvalidInputException(error);
		}
		
		if (quantity < 0) {
			ftms.removeEquipment(equipment);
		} else {
			equipment.setCount(quantity);
		}
		PersistenceXStream.saveToXMLwithXStream(ftms);
		
	}
	
	/**
	 * Adds an employee to the database.
	 * @param employeeName		name of the employee we want to add
	 * @param role				their role within the company
	 * @param salary			how much money they earn per hour
	 */
	public void createEmployee(String employeeName, String role, String salary) throws InvalidInputException {
		
		String error = "";
		
		if (isEmpty(employeeName)) {
			error += "Employee name cannot be empty! ";
		}
		if (isEmpty(role)) {
			error += "Role cannot be empty! ";
		}
		
		double salaryNumber=0;
		try {
			salaryNumber = Double.parseDouble(salary);
			
			if (!hasCorrectAmountOfDecimalPlaces(salary)) {
				error += "Salary cannot contain fractions of cents! ";
			} else if (salaryNumber < 10.75) {
				error += "Minimum wage is 10.75! Treat your workers fairly! ";
			}
			
		} catch (NumberFormatException e) {
			error += "Salary must be a number! ";
		} catch (NullPointerException e) {
			error += "Salary must be a number! ";
		}
		
		error = error.trim();
		
		if (error.length()>0) {
			throw new InvalidInputException(error);
		}
		
		employeeName = WordUtils.capitalizeFully(employeeName);
		Employee employee = new Employee(employeeName,role,salaryNumber);
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		
		ftms.addEmployee(employee);
		PersistenceXStream.saveToXMLwithXStream(ftms);
	}
	
	public void removeEmployee(Employee employee) {
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		ftms.removeEmployee(employee);
		PersistenceXStream.saveToXMLwithXStream(ftms);
	}
	
	/**
	 * Schedules a shift for an employee.
	 * @param employee	the employee that we are scheduling
	 * @param day		the day of the week of the shift
	 * @param startTime	the start time of the shift
	 * @param endTime	the end time of the shift
	 */
	public void createShift(Employee employee, String day, Time startTime, Time endTime) throws InvalidInputException {
		
		String error = "";
		if (endTime != null && startTime != null && endTime.getTime() < startTime.getTime()) {
			error = error + "Shift end time cannot be before shift start time! ";
		}
		
		if (isEmpty(day)) {
			error += "Please select a day of the week! ";
		}
		error = error.trim();
		
		if (error.length()>0) {
			throw new InvalidInputException(error);
		}
		
		long duration = endTime.getTime()-startTime.getTime();
		int numHours = (int) (duration/1000/60/60);
		
		Shift shift = new Shift(day, startTime, endTime, numHours);
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		
		employee.addShift(shift);
		PersistenceXStream.saveToXMLwithXStream(ftms);
	}
	
	/**
	 * Removes a shift for an employee.
	 * @param employee	the employee for whom we are removing the shift
	 * @param shift		the shift to be removed
	 */
	public void removeShift(Employee employee, Shift shift) {
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		
		employee.removeShift(shift);
		PersistenceXStream.saveToXMLwithXStream(ftms);
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
