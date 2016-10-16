package ca.mcgill.ecse321.foodtruck.controller;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.mcgill.ecse321.foodtruck.model.FoodTruckManager;

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
		double itemPrice = 4.50;
		
		FoodTruckController ftc = new FoodTruckController();
		
		ftc.createMenuItem(itemName, itemPrice);
		checkResultMenuItem(itemName, itemPrice, ftms);
		
	}
	
	private void checkResultMenuItem(String itemName, double itemPrice, FoodTruckManager ftms) {
		assertEquals(1,ftms.getMenuItems().size());
		assertEquals(itemName,ftms.getMenuItem(0).getName());
		assertEquals(itemPrice,ftms.getMenuItem(0).getPrice(),0.004);
		assertEquals(0,ftms.getMenuItem(0).getAmountSold());
	}

}
