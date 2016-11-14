package ca.mcgill.ecse321.foodtruck.controller;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.mcgill.ecse321.foodtruck.model.Equipment;
import ca.mcgill.ecse321.foodtruck.model.FoodTruckManager;
import ca.mcgill.ecse321.foodtruck.model.Supply;
import ca.mcgill.ecse321.foodtruck.persistence.PersistenceXStream;

public class TestFoodTruckEdit_Inventory {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		ftms.delete();
	}

	@Test
	public void testCreateSupply() {
		
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		assertEquals(0, ftms.getSupplies().size());
		
		String supplyName = "Patty";
		
		FoodTruckController ftc = new FoodTruckController();
		
		try{
			ftc.createSupply(supplyName);
		}catch(InvalidInputException e){
			fail();
		}
		checkResultSupply(supplyName, ftms);
		
		FoodTruckManager ftms2 = (FoodTruckManager)PersistenceXStream.loadFromXMLwithXStream();
		
		checkResultSupply(supplyName, ftms2);
	}
	
	@Test
	public void testCreateSupplyNull() {
		
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		assertEquals(0, ftms.getSupplies().size());
		
		String supplyName = null;
		
		String errorMessage = null;
		
		FoodTruckController ftc = new FoodTruckController();
		
		try {
			ftc.createSupply(supplyName);
		} catch (InvalidInputException e) {
			errorMessage = e.getMessage();
		}
		
		//check error
		assertEquals("Supply name cannot be empty!", errorMessage);
		
		//check no change in memory
		assertEquals(0, ftms.getSupplies().size());
	}
	
	@Test
	public void testCreateSupplyEmpty() {
		
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		assertEquals(0, ftms.getSupplies().size());
		
		String supplyName = "";
		
		String errorMessage = null;
		
		FoodTruckController ftc = new FoodTruckController();
		
		try {
			ftc.createSupply(supplyName);
		} catch (InvalidInputException e) {
			errorMessage = e.getMessage();
		}
		
		//check error
		assertEquals("Supply name cannot be empty!", errorMessage);
		
		//check no change in memory
		assertEquals(0, ftms.getSupplies().size());
	}
	
	@Test
	public void testCreateSupplySpaces() {
		
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		assertEquals(0, ftms.getSupplies().size());
		
		String supplyName = " ";
		
		String errorMessage = null;
		
		FoodTruckController ftc = new FoodTruckController();
		
		try {
			ftc.createSupply(supplyName);
		} catch (InvalidInputException e) {
			errorMessage = e.getMessage();
		}
		
		//check error
		assertEquals("Supply name cannot be empty!", errorMessage);
		
		//check no change in memory
		assertEquals(0, ftms.getSupplies().size());
	}
	
	@Test
	public void testEditSupplyQuantityAdd() {
		
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		assertEquals(0, ftms.getSupplies().size());
		
		String supplyName = "Patty";
		String quantity = "10";
		
		FoodTruckController ftc = new FoodTruckController();
		
		try {
			ftc.createSupply(supplyName);
		} catch (InvalidInputException e) {
			fail();
		}
		
		Supply supply = ftms.getSupply(0);
		
		try{
			ftc.editSupplyQuantity(supply, quantity);
		}catch(InvalidInputException e){
			fail();
		}
		checkResultSupplyAdd(supplyName, quantity, ftms);
		
		FoodTruckManager ftms2 = (FoodTruckManager)PersistenceXStream.loadFromXMLwithXStream();
		
		checkResultSupplyAdd(supplyName, quantity, ftms2);
	}
	
	@Test
	public void testDeleteSupply() {
		
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		assertEquals(0, ftms.getSupplies().size());
		
		String supplyName = "Patty";
		String quantity = "-1";
		
		FoodTruckController ftc = new FoodTruckController();
		
		try {
			ftc.createSupply(supplyName);
		} catch (InvalidInputException e) {
			fail();
		}
		
		Supply supply = ftms.getSupply(0);
		
		try{
			ftc.editSupplyQuantity(supply, quantity);
		}catch(InvalidInputException e){
			fail();
		}
		checkResultSupplyDelete(supplyName, quantity, ftms);
		
		FoodTruckManager ftms2 = (FoodTruckManager)PersistenceXStream.loadFromXMLwithXStream();
		
		checkResultSupplyDelete(supplyName, quantity, ftms2);
	}
	
	@Test
	public void testEditSupplyQuantityEmpty() {
	
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		assertEquals(0, ftms.getSupplies().size());
		
		FoodTruckController ftc = new FoodTruckController();
		String supplyName = "Spatula";
		String quantity = "";
		String errorMessage = "";
		
		try {
			ftc.createSupply(supplyName);
		} catch (InvalidInputException e) {
			fail();
		}
		
		Supply supply = ftms.getSupply(0);
		
		try {
			ftc.editSupplyQuantity(supply, quantity);
		} catch (InvalidInputException e) {
			errorMessage = e.getMessage();
		}
		
		//check error
		assertEquals("Supply count must be a number!", errorMessage);
		
		//check no change in memory
		assertEquals(0, ftms.getSupply(0).getCount());
	}
	
	@Test
	public void testEditSupplyQuantityNaN() {
		
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		assertEquals(0, ftms.getSupplies().size());
		
		FoodTruckController ftc = new FoodTruckController();
		String supplyName = "Spatula";
		String quantity = "I am not a number";
		String errorMessage = "";
		
		try {
			ftc.createSupply(supplyName);
		} catch (InvalidInputException e) {
			fail();
		}
		
		Supply supply = ftms.getSupply(0);
		
		try {
			ftc.editSupplyQuantity(supply, quantity);
		} catch (InvalidInputException e) {
			errorMessage = e.getMessage();
		}
		
		//check error
		assertEquals("Supply count must be a number!", errorMessage);
		
		//check no change in memory
		assertEquals(0, ftms.getSupply(0).getCount());
	}

	@Test
	public void testCreateEquipment() {
		
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		assertEquals(0, ftms.getEquipment().size());
		
		String equipName = "Spatula";
		
		FoodTruckController ftc = new FoodTruckController();
		
		try{
			ftc.createEquipment(equipName);
		}catch(InvalidInputException e){
			fail();
		}
		checkResultEquipment(equipName, ftms);
		
		FoodTruckManager ftms2 = (FoodTruckManager)PersistenceXStream.loadFromXMLwithXStream();
		
		checkResultEquipment(equipName, ftms2);
	}
	
	@Test
	public void testCreateEquipmentNull() {
		
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		assertEquals(0, ftms.getEquipment().size());
		
		String equipName = null;
		
		String errorMessage = null;
		
		FoodTruckController ftc = new FoodTruckController();
		
		try {
			ftc.createEquipment(equipName);
		} catch (InvalidInputException e) {
			errorMessage = e.getMessage();
		}
		
		//check error
		assertEquals("Equipment name cannot be empty!", errorMessage);
		
		//check no change in memory
		assertEquals(0, ftms.getEquipment().size());
	}
	
	@Test
	public void testCreateEquipmentEmpty() {
		
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		assertEquals(0, ftms.getEquipment().size());
		
		String equipName = "";
		
		String errorMessage = null;
		
		FoodTruckController ftc = new FoodTruckController();
		
		try {
			ftc.createEquipment(equipName);
		} catch (InvalidInputException e) {
			errorMessage = e.getMessage();
		}
		
		//check error
		assertEquals("Equipment name cannot be empty!", errorMessage);
		
		//check no change in memory
		assertEquals(0, ftms.getEquipment().size());
	}
	
	@Test
	public void testCreateEquipmentSpaces() {
		
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		assertEquals(0, ftms.getEquipment().size());
		
		String equipName = " ";
		
		String errorMessage = null;
		
		FoodTruckController ftc = new FoodTruckController();
		
		try {
			ftc.createEquipment(equipName);
		} catch (InvalidInputException e) {
			errorMessage = e.getMessage();
		}
		
		//check error
		assertEquals("Equipment name cannot be empty!", errorMessage);
		
		//check no change in memory
		assertEquals(0, ftms.getEquipment().size());
	}
	
	@Test
	public void testEditEquipmentQuantity() {
		
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		assertEquals(0, ftms.getEquipment().size());
		
		String equipName = "Spatula";
		String quantity = "2";
		
		FoodTruckController ftc = new FoodTruckController();
		
		try {
			ftc.createEquipment(equipName);
		} catch (InvalidInputException e) {
			fail();
		}
		
		Equipment equip = ftms.getEquipment(0);
		
		try {
			ftc.editEquipmentQuantity(equip, quantity);
		} catch (InvalidInputException e) {
			fail();
		}
		checkResultEquipAdd(equipName, quantity, ftms);
		
		FoodTruckManager ftms2 = (FoodTruckManager)PersistenceXStream.loadFromXMLwithXStream();
		
		checkResultEquipAdd(equipName, quantity, ftms2);
	}
	
	@Test
	public void testDeleteEquipment() {
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		assertEquals(0, ftms.getEquipment().size());
		
		String equipName = "Spatula";
		String quantity = "-1";
		
		FoodTruckController ftc = new FoodTruckController();
		
		try {
			ftc.createEquipment(equipName);
		} catch (InvalidInputException e) {
			fail();
		}
		
		Equipment equip = ftms.getEquipment(0);
		
		try {
			ftc.editEquipmentQuantity(equip, quantity);
		} catch (InvalidInputException e) {
			fail();
		}
		
		checkResultEquipDelete(ftms);
		
		FoodTruckManager ftms2 = (FoodTruckManager)PersistenceXStream.loadFromXMLwithXStream();
		
		checkResultEquipDelete(ftms2);
	}
	
	@Test
	public void testEditEquipmentQuantityEmpty() {
	
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		assertEquals(0, ftms.getEquipment().size());
		
		FoodTruckController ftc = new FoodTruckController();
		String equipName = "Spatula";
		String quantity = "";
		String errorMessage = "";
		
		try {
			ftc.createEquipment(equipName);
		} catch (InvalidInputException e) {
			fail();
		}
		
		Equipment equip = ftms.getEquipment(0);
		
		try {
			ftc.editEquipmentQuantity(equip, quantity);
		} catch (InvalidInputException e) {
			errorMessage = e.getMessage();
		}
		
		//check error
		assertEquals("Equipment count must be a number!", errorMessage);
		
		//check no change in memory
		assertEquals(0, ftms.getEquipment(0).getCount());
	}
	
	@Test
	public void testEditEquipmentQuantityNaN() {
		
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		assertEquals(0, ftms.getEquipment().size());
		
		FoodTruckController ftc = new FoodTruckController();
		String equipName = "Spatula";
		String quantity = "I am not a number";
		String errorMessage = "";
		
		try {
			ftc.createEquipment(equipName);
		} catch (InvalidInputException e) {
			fail();
		}
		
		Equipment equip = ftms.getEquipment(0);
		
		try {
			ftc.editEquipmentQuantity(equip, quantity);
		} catch (InvalidInputException e) {
			errorMessage = e.getMessage();
		}
		
		//check error
		assertEquals("Equipment count must be a number!", errorMessage);
		
		//check no change in memory
		assertEquals(0, ftms.getEquipment(0).getCount());
	}
	
	
	
	//Checking methods for Equipment
	private void checkResultEquipDelete(FoodTruckManager ftms) {
			assertEquals(0,ftms.getEquipment().size());
	}

	private void checkResultEquipAdd(String equipName, String quantity,
			FoodTruckManager ftms) {
		assertEquals(1,ftms.getEquipment().size());
		assertEquals(equipName,ftms.getEquipment(0).getName());
		assertEquals(quantity, String.valueOf(ftms.getEquipment(0).getCount()));
	}
	
	private void checkResultEquipment(String equipName, FoodTruckManager ftms) {
		assertEquals(1,ftms.getEquipment().size());
		assertEquals(equipName,ftms.getEquipment(0).getName());
	}	
	
	//Checking methods for Supply
	private void checkResultSupplyDelete(String supplyName, String quantity,
			FoodTruckManager ftms) {
		assertEquals(0,ftms.getSupplies().size());
	}

	private void checkResultSupplyAdd(String supplyName, String quantity,
			FoodTruckManager ftms) {
		assertEquals(1,ftms.getSupplies().size());
		assertEquals(supplyName,ftms.getSupply(0).getName());
		assertEquals(quantity, String.valueOf(ftms.getSupply(0).getCount()));
	}

	private void checkResultSupply(String supplyName, FoodTruckManager ftms) {
		assertEquals(1,ftms.getSupplies().size());
		assertEquals(supplyName,ftms.getSupply(0).getName());
	}
}
