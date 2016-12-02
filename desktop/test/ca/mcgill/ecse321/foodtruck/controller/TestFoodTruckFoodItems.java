package ca.mcgill.ecse321.foodtruck.controller;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.mcgill.ecse321.foodtruck.model.FoodTruckManager;
import ca.mcgill.ecse321.foodtruck.model.FoodItem;
import ca.mcgill.ecse321.foodtruck.persistence.PersistenceXStream;

public class TestFoodTruckFoodItems {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		ftms.delete();
	}

	@Test
	public void testCreateFoodItem() {
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		assertEquals(0, ftms.getFoodItems().size());
		
		String itemName = "Burger";
		String itemPrice = "10.20";
		
		FoodTruckController ftc = new FoodTruckController();
		
		try {
			ftc.createFoodItem(itemName, itemPrice);
		} catch(InvalidInputException e) {
			fail();
		}
		checkResultFoodItem(itemName, Double.parseDouble(itemPrice), ftms);
		
		FoodTruckManager ftms2 = (FoodTruckManager)PersistenceXStream.loadFromXMLwithXStream();
		
		checkResultFoodItem(itemName, Double.parseDouble(itemPrice), ftms2);
		
	}
	
	@Test
	public void testCreateFoodItemDuplicate() {
		
		String errorMessage="";
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		assertEquals(0, ftms.getFoodItems().size());
		
		String itemName = "Burger";
		String itemPrice = "10.20";
		
		String itemName2 = "burger";
		
		FoodTruckController ftc = new FoodTruckController();
		
		try {
			ftc.createFoodItem(itemName, itemPrice);
		} catch (InvalidInputException e) {
			fail();
		}
		
		try {
			ftc.createFoodItem(itemName2, itemPrice);
		} catch (InvalidInputException e) {
			errorMessage+=e.getMessage();
		}
		
		assertEquals("This item is already on the menu! Please enter a new one.",errorMessage);
		
		//check that there is only still one item on menu
		checkResultFoodItem(itemName, Double.parseDouble(itemPrice), ftms);
		
		FoodTruckManager ftms2 = (FoodTruckManager)PersistenceXStream.loadFromXMLwithXStream();
		
		checkResultFoodItem(itemName, Double.parseDouble(itemPrice), ftms2);
		
	}
	
	@Test
	public void testCreateFoodItemNullName() {
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		assertEquals(0, ftms.getFoodItems().size());
		
		String itemName = null;
		String itemPrice = "4.50";
		
		String errorMessage = null;
		
		FoodTruckController ftc = new FoodTruckController();
		
		try {
			ftc.createFoodItem(itemName, itemPrice);
		} catch (InvalidInputException e) {
			errorMessage = e.getMessage();
		}
		
		//check error
		assertEquals("Menu item name cannot be empty!", errorMessage);
		
		//no change in memory
		assertEquals(0, ftms.getFoodItems().size());
	}
	
	@Test
	public void testCreateFoodItemEmptyName() {
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		assertEquals(0, ftms.getFoodItems().size());
		
		String itemName = "";
		String itemPrice = "4.50";
		
		String errorMessage = null;
		
		FoodTruckController ftc = new FoodTruckController();
		
		try {
			ftc.createFoodItem(itemName, itemPrice);
		} catch (InvalidInputException e) {
			errorMessage = e.getMessage();
		}
		
		//check error
		assertEquals("Menu item name cannot be empty!", errorMessage);
		
		//no change in memory
		assertEquals(0, ftms.getFoodItems().size());
	}
	
	@Test
	public void testCreateFoodItemSpacesName() {
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		assertEquals(0, ftms.getFoodItems().size());
		
		String itemName = " ";
		String itemPrice = "4.50";
		
		String errorMessage = null;
		
		FoodTruckController ftc = new FoodTruckController();
		
		try {
			ftc.createFoodItem(itemName, itemPrice);
		} catch (InvalidInputException e) {
			errorMessage = e.getMessage();
		}
		
		//check error
		assertEquals("Menu item name cannot be empty!", errorMessage);
		
		//no change in memory
		assertEquals(0, ftms.getFoodItems().size());
	}
	
	@Test
	public void testCreateFoodItemAlphanumericPrice() {
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		assertEquals(0, ftms.getFoodItems().size());
		
		String itemName = "Burger";
		String itemPrice = "asasd23 123";
		
		String errorMessage = null;
		FoodTruckController ftc = new FoodTruckController();
		
		try {
			ftc.createFoodItem(itemName, itemPrice);
		} catch (InvalidInputException e) {
			errorMessage = e.getMessage();
		}
		
		//check error
		assertEquals("Menu item price must be a number!", errorMessage);
		
		//no change in error
		assertEquals(0, ftms.getFoodItems().size());
	}
	
	@Test
	public void testCreateFoodItemNullPrice() {
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		assertEquals(0, ftms.getFoodItems().size());
		
		String itemName = "Burger";
		String itemPrice = null;
		
		String errorMessage = null;
		FoodTruckController ftc = new FoodTruckController();
		
		try {
			ftc.createFoodItem(itemName, itemPrice);
		} catch (InvalidInputException e) {
			errorMessage = e.getMessage();
		}
		
		//check error
		assertEquals("Menu item price must be a number!", errorMessage);
		
		//no change in error
		assertEquals(0, ftms.getFoodItems().size());
	}
	
	@Test
	public void testCreateFoodItemEmptyPrice() {
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		assertEquals(0, ftms.getFoodItems().size());
		
		String itemName = "Burger";
		String itemPrice = "";
		
		String errorMessage = null;
		FoodTruckController ftc = new FoodTruckController();
		
		try {
			ftc.createFoodItem(itemName, itemPrice);
		} catch (InvalidInputException e) {
			errorMessage = e.getMessage();
		}
		
		//check error
		assertEquals("Menu item price must be a number!", errorMessage);
		
		//no change in error
		assertEquals(0, ftms.getFoodItems().size());
	}
	
	@Test
	public void testCreateFoodItemNegativePrice() {
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		assertEquals(0, ftms.getFoodItems().size());
		
		String itemName = "Burger";
		String itemPrice = "-5";
		
		String errorMessage = null;
		FoodTruckController ftc = new FoodTruckController();
		
		try {
			ftc.createFoodItem(itemName, itemPrice);
		} catch (InvalidInputException e) {
			errorMessage = e.getMessage();
		}
		
		//check error
		assertEquals("Menu item price must be greater than 0!", errorMessage);
		
		//no change in error
		assertEquals(0, ftms.getFoodItems().size());
	}
	
	@Test
	public void testCreateFoodItemZeroPrice() {
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		assertEquals(0, ftms.getFoodItems().size());
		
		String itemName = "Burger";
		String itemPrice = "0";
		
		String errorMessage = null;
		FoodTruckController ftc = new FoodTruckController();
		
		try {
			ftc.createFoodItem(itemName, itemPrice);
		} catch (InvalidInputException e) {
			errorMessage = e.getMessage();
		}
		
		//check error
		assertEquals("Menu item price must be greater than 0!", errorMessage);
		
		//no change in error
		assertEquals(0, ftms.getFoodItems().size());
	}
	
	@Test
	public void testCreateFoodItemTooManyDecimalsPrice() {
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		assertEquals(0, ftms.getFoodItems().size());
		
		String itemName = "Burger";
		String itemPrice = "4.124";
		
		String errorMessage = null;
		FoodTruckController ftc = new FoodTruckController();
		
		try {
			ftc.createFoodItem(itemName, itemPrice);
		} catch (InvalidInputException e) {
			errorMessage = e.getMessage();
		}
		
		//check error
		assertEquals("Menu item price cannot contain fractions of cents!", errorMessage);
		
		//no change in error
		assertEquals(0, ftms.getFoodItems().size());
	}
	
	@Test
	public void testOrderFood() {
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		
		FoodItem item = new FoodItem("Burger", 5.00, 0);
		
		ftms.addFoodItem(item);
		
		String amount = "3";
		
		FoodTruckController ftc = new FoodTruckController();
		
		try {
			ftc.orderFood(ftms.getFoodItem(0),amount);
		} catch (InvalidInputException e) {
			fail();
		}
		//check result in memory
		checkResultOrder(ftms,amount);
		FoodTruckManager ftms2 = (FoodTruckManager)PersistenceXStream.loadFromXMLwithXStream();
		checkResultOrder(ftms2, amount);
	}
	
	@Test
	public void testOrderFoodNullFoodAndQuantity() {
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		
		FoodItem item = new FoodItem("Burger", 5.00, 0);
		ftms.addFoodItem(item);
		
		FoodTruckController ftc = new FoodTruckController();
		String errorMessage="";
		try {
			ftc.orderFood(null,null);
		} catch (InvalidInputException e) {
			errorMessage = e.getMessage();
		}
		
		assertEquals(errorMessage,"Please choose an item to order! Please enter an order quantity!");
		checkResultOrderNoChange(ftms);
	}
	
	@Test
	public void testOrderFoodNonIntegerQuantity() {
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		
		FoodItem item = new FoodItem("Burger", 5.00, 0);
		ftms.addFoodItem(item);
		
		FoodTruckController ftc = new FoodTruckController();
		String errorMessage="";
		try {
			ftc.orderFood(ftms.getFoodItem(0),"3.12312");
		} catch (InvalidInputException e) {
			errorMessage = e.getMessage();
		}
		
		assertEquals(errorMessage,"Menu item price must be a positive integer!");
		checkResultOrderNoChange(ftms);
	}
	
	@Test
	public void testOrderFoodNegativeQuantity() {
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		
		FoodItem item = new FoodItem("Burger", 5.00, 0);
		ftms.addFoodItem(item);
		
		FoodTruckController ftc = new FoodTruckController();
		String errorMessage="";
		try {
			ftc.orderFood(ftms.getFoodItem(0),"-5");
		} catch (InvalidInputException e) {
			errorMessage = e.getMessage();
		}
		
		assertEquals(errorMessage,"Amount of orders must be greater than 0!");
		checkResultOrderNoChange(ftms);
	}
	
	@Test
	public void testOrderFoodEmptyQuantity() {
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		
		FoodItem item = new FoodItem("Burger", 5.00, 0);
		ftms.addFoodItem(item);
		
		FoodTruckController ftc = new FoodTruckController();
		String errorMessage="";
		try {
			ftc.orderFood(ftms.getFoodItem(0),"");
		} catch (InvalidInputException e) {
			errorMessage = e.getMessage();
		}
		
		assertEquals(errorMessage,"Please enter an order quantity!");
		checkResultOrderNoChange(ftms);
	}
	
	@Test
	public void testOrderFoodSpacesQuantity() {
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		
		FoodItem item = new FoodItem("Burger", 5.00, 0);
		ftms.addFoodItem(item);
		
		FoodTruckController ftc = new FoodTruckController();
		String errorMessage="";
		try {
			ftc.orderFood(ftms.getFoodItem(0),"  ");
		} catch (InvalidInputException e) {
			errorMessage = e.getMessage();
		}
		
		assertEquals(errorMessage,"Please enter an order quantity!");
		checkResultOrderNoChange(ftms);
	}
	
	private void checkResultFoodItem(String itemName, double itemPrice, FoodTruckManager ftms) {
		assertEquals(1,ftms.getFoodItems().size());
		assertEquals(itemName,ftms.getFoodItem(0).getName());
		assertEquals(itemPrice,ftms.getFoodItem(0).getPrice(),0.004);
		assertEquals(0,ftms.getFoodItem(0).getAmountSold());
	}
	
	private void checkResultOrder(FoodTruckManager ftms, String amount) {
		assertEquals(1,ftms.numberOfFoodItems());
		assertEquals(Integer.parseInt(amount),ftms.getFoodItem(0).getAmountSold());
	}
	private void checkResultOrderNoChange(FoodTruckManager ftms) {
		assertEquals(0,ftms.getFoodItem(0).getAmountSold());
	}

}
