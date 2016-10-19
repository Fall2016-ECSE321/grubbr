package ca.mcgill.ecse321.foodtruck.persistence;

import java.util.Iterator;

import ca.mcgill.ecse321.foodtruck.model.FoodTruckManager;
import ca.mcgill.ecse321.foodtruck.model.MenuItem;

/**
 * Persistence layer for the Food Truck Management System.
 * Uses XStream to store data in XML files.
 * 
 * @author erick
 * @version 0.1
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
		}
	}

}
