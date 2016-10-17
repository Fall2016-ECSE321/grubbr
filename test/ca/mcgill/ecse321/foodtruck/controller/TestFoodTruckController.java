package ca.mcgill.ecse321.foodtruck.controller;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.mcgill.ecse321.foodtruck.model.FoodTruckManager;
import ca.mcgill.ecse321.foodtruck.persistence.PersistenceXStream;

public class TestFoodTruckController {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		ftms.delete();
	}

	@Test
	public void testCreateMenuItem() {
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		assertEquals(0, ftms.getMenuItems().size());
		
		String itemName = "Burger";
		String itemPrice = "4.50";
		
		FoodTruckController ftc = new FoodTruckController();
		
		try {
			ftc.createMenuItem(itemName, itemPrice);
		} catch(InvalidInputException e) {
			fail();
		}
		checkResultMenuItem(itemName, Double.parseDouble(itemPrice), ftms);
		
		FoodTruckManager ftms2 = (FoodTruckManager)PersistenceXStream.loadFromXMLwithXStream();
		
		checkResultMenuItem(itemName, Double.parseDouble(itemPrice), ftms2);
		
	}
	
	@Test
	public void testCreateMenuItemNullName() {
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		assertEquals(0, ftms.getMenuItems().size());
		
		String itemName = null;
		String itemPrice = "4.50";
		
		String errorMessage = null;
		
		FoodTruckController ftc = new FoodTruckController();
		
		try {
			ftc.createMenuItem(itemName, itemPrice);
		} catch (InvalidInputException e) {
			errorMessage = e.getMessage();
		}
		
		//check error
		assertEquals("Menu item name cannot be empty!", errorMessage);
		
		//no change in memory
		assertEquals(0, ftms.getMenuItems().size());
	}
	
	@Test
	public void testCreateMenuItemEmptyName() {
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		assertEquals(0, ftms.getMenuItems().size());
		
		String itemName = "";
		String itemPrice = "4.50";
		
		String errorMessage = null;
		
		FoodTruckController ftc = new FoodTruckController();
		
		try {
			ftc.createMenuItem(itemName, itemPrice);
		} catch (InvalidInputException e) {
			errorMessage = e.getMessage();
		}
		
		//check error
		assertEquals("Menu item name cannot be empty!", errorMessage);
		
		//no change in memory
		assertEquals(0, ftms.getMenuItems().size());
	}
	
	@Test
	public void testCreateMenuItemSpacesName() {
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		assertEquals(0, ftms.getMenuItems().size());
		
		String itemName = " ";
		String itemPrice = "4.50";
		
		String errorMessage = null;
		
		FoodTruckController ftc = new FoodTruckController();
		
		try {
			ftc.createMenuItem(itemName, itemPrice);
		} catch (InvalidInputException e) {
			errorMessage = e.getMessage();
		}
		
		//check error
		assertEquals("Menu item name cannot be empty!", errorMessage);
		
		//no change in memory
		assertEquals(0, ftms.getMenuItems().size());
	}
	
	@Test
	
	public void testCreateMenuItemAlphanumericPrice() {
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		assertEquals(0, ftms.getMenuItems().size());
		
		String itemName = "Burger";
		String itemPrice = "asasd23 123";
		
		String errorMessage = null;
		FoodTruckController ftc = new FoodTruckController();
		
		try {
			ftc.createMenuItem(itemName, itemPrice);
		} catch (InvalidInputException e) {
			errorMessage = e.getMessage();
		}
		
		//check error
		assertEquals("Menu item price is invalid!", errorMessage);
		
		//no change in error
		assertEquals(0, ftms.getMenuItems().size());
	}
	
	public void testCreateMenuItemNullPrice() {
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		assertEquals(0, ftms.getMenuItems().size());
		
		String itemName = "Burger";
		String itemPrice = null;
		
		String errorMessage = null;
		FoodTruckController ftc = new FoodTruckController();
		
		try {
			ftc.createMenuItem(itemName, itemPrice);
		} catch (InvalidInputException e) {
			errorMessage = e.getMessage();
		}
		
		//check error
		assertEquals("Menu item price is invalid!", errorMessage);
		
		//no change in error
		assertEquals(0, ftms.getMenuItems().size());
	}
	
	public void testCreateMenuItemEmptyPrice() {
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		assertEquals(0, ftms.getMenuItems().size());
		
		String itemName = "Burger";
		String itemPrice = "";
		
		String errorMessage = null;
		FoodTruckController ftc = new FoodTruckController();
		
		try {
			ftc.createMenuItem(itemName, itemPrice);
		} catch (InvalidInputException e) {
			errorMessage = e.getMessage();
		}
		
		//check error
		assertEquals("Menu item price is empty!", errorMessage);
		
		//no change in error
		assertEquals(0, ftms.getMenuItems().size());
	}
	
	public void testCreateMenuItemNegativePrice() {
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		assertEquals(0, ftms.getMenuItems().size());
		
		String itemName = "Burger";
		String itemPrice = "-5";
		
		String errorMessage = null;
		FoodTruckController ftc = new FoodTruckController();
		
		try {
			ftc.createMenuItem(itemName, itemPrice);
		} catch (InvalidInputException e) {
			errorMessage = e.getMessage();
		}
		
		//check error
		assertEquals("Menu item price is invalid!", errorMessage);
		
		//no change in error
		assertEquals(0, ftms.getMenuItems().size());
	}
	
	public void testCreateMenuItemZeroPrice() {
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		assertEquals(0, ftms.getMenuItems().size());
		
		String itemName = "Burger";
		String itemPrice = "0";
		
		String errorMessage = null;
		FoodTruckController ftc = new FoodTruckController();
		
		try {
			ftc.createMenuItem(itemName, itemPrice);
		} catch (InvalidInputException e) {
			errorMessage = e.getMessage();
		}
		
		//check error
		assertEquals("Menu item price is invalid!", errorMessage);
		
		//no change in error
		assertEquals(0, ftms.getMenuItems().size());
	}
	
	public void testCreateMenuItemTooManyDecimalsPrice() {
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		assertEquals(0, ftms.getMenuItems().size());
		
		String itemName = "Burger";
		String itemPrice = "4.124";
		
		String errorMessage = null;
		FoodTruckController ftc = new FoodTruckController();
		
		try {
			ftc.createMenuItem(itemName, itemPrice);
		} catch (InvalidInputException e) {
			errorMessage = e.getMessage();
		}
		
		//check error
		assertEquals("Menu item price is invalid!", errorMessage);
		
		//no change in error
		assertEquals(0, ftms.getMenuItems().size());
	}
	
	private void checkResultMenuItem(String itemName, double itemPrice, FoodTruckManager ftms) {
		assertEquals(1,ftms.getMenuItems().size());
		assertEquals(itemName,ftms.getMenuItem(0).getName());
		assertEquals(itemPrice,ftms.getMenuItem(0).getPrice(),0.004);
		assertEquals(0,ftms.getMenuItem(0).getAmountSold());
	}

}
