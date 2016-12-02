package ca.mcgill.ecse321.foodtruck.persistence;

import java.util.Iterator;

import ca.mcgill.ecse321.foodtruck.model.Employee;
import ca.mcgill.ecse321.foodtruck.model.Equipment;
import ca.mcgill.ecse321.foodtruck.model.FoodTruckManager;
import ca.mcgill.ecse321.foodtruck.model.MenuItem;
import ca.mcgill.ecse321.foodtruck.model.Shift;
import ca.mcgill.ecse321.foodtruck.model.Supply;

/**
 * Persistence layer for the Food Truck Management System.
 * Uses XStream to store data in XML files.
 * 
 * @author erick
 * @version 0.2
 */

public class PersistenceFoodTruck {
	
	private static String filename = "foodtruck.xml";
	
	public static void setFilename(String name) {
		filename = name;
	}
	
	/**
	 * Initializes variables so that XStream can work.
	 */
	
	private static void initializeXStream() {
		PersistenceXStream.setFilename(filename);
		PersistenceXStream.setAlias("item", MenuItem.class);
		PersistenceXStream.setAlias("manager", FoodTruckManager.class);
		PersistenceXStream.setAlias("supply", Supply.class);
		PersistenceXStream.setAlias("equipment", Equipment.class);
		PersistenceXStream.setAlias("employee", Employee.class);
		PersistenceXStream.setAlias("shift", Shift.class);
	}
	
	public static void loadFoodTruckModel() {
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		PersistenceFoodTruck.initializeXStream();
		FoodTruckManager ftms2 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		
		if (ftms2 != null) {
			//copy loaded model into singleton instance of FTMS
			Iterator<MenuItem> menuIterator = ftms2.getMenuItems().iterator();
			while (menuIterator.hasNext()) {
				ftms.addMenuItem(menuIterator.next());
			}
			Iterator<Supply> supplyIterator = ftms2.getSupplies().iterator();
			while (supplyIterator.hasNext()) {
				ftms.addSupply(supplyIterator.next());
			}
			Iterator<Equipment> equipmentIterator = ftms2.getEquipment().iterator();
			while (equipmentIterator.hasNext()) {
				ftms.addEquipment(equipmentIterator.next());
			}
			Iterator<Employee> employeeIterator = ftms2.getEmployees().iterator();
			while (employeeIterator.hasNext()) {
				ftms.addEmployee(employeeIterator.next());
			}
			Iterator<Shift> shiftIterator = ftms2.getShifts().iterator();
			while (shiftIterator.hasNext()) {
				ftms.addShift(shiftIterator.next());
			}
		}
	}

}
