package ca.mcgill.ecse321.foodtruck.controller;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import ca.mcgill.ecse321.foodtruck.model.Employee;
import ca.mcgill.ecse321.foodtruck.model.FoodTruckManager;
import ca.mcgill.ecse321.foodtruck.persistence.PersistenceXStream;

public class TestFoodTruckStaff {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		ftms.delete();
	}

	@Test
	public void testCreateEmployee() {
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		assertEquals(false, ftms.hasEmployees());
		assertEquals(0, ftms.numberOfEmployees());
		
		String staffName = "Bruh";
		String staffRole = "chef";
		String staffSalary = "12.75";
		
		FoodTruckController ftc = new FoodTruckController();
		
		try {
			ftc.createEmployee(staffName, staffRole, staffSalary);
		} catch(InvalidInputException e) {
			fail();
		}
		checkResultStaff(staffName, ftms);
		
		FoodTruckManager ftms2 = (FoodTruckManager)PersistenceXStream.loadFromXMLwithXStream();
		
		checkResultStaff(staffName, ftms2);		
	}
	

	@Test
	public void testCreateEmployeeNameNull() {
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		assertEquals(false, ftms.hasEmployees());
		assertEquals(0, ftms.numberOfEmployees());
		
		String staffName = null;
		String staffRole = "chef";
		String staffSalary = "12.75";
		
		String errorMessage = null;
		
		FoodTruckController ftc = new FoodTruckController();
		
		try {
			ftc.createEmployee(staffName, staffRole, staffSalary);
		} catch (InvalidInputException e) {
			errorMessage = e.getMessage();
		}
		
		//check error
		assertEquals("Employee name cannot be empty! ", errorMessage);
		
		//check no change in memory
		assertEquals(false, ftms.hasEmployees());
		assertEquals(0, ftms.numberOfEmployees());		
	}
	
	@Test
	public void testCreateEmployeeNameEmpty() {
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		assertEquals(false, ftms.hasEmployees());
		assertEquals(0, ftms.numberOfEmployees());
		
		String staffName = "";
		String staffRole = "chef";
		String staffSalary = "12.75";
		
		String errorMessage = null;
		
		FoodTruckController ftc = new FoodTruckController();
		
		try {
			ftc.createEmployee(staffName, staffRole, staffSalary);
		} catch (InvalidInputException e) {
			errorMessage = e.getMessage();
		}
		
		//check error
		assertEquals("Employee name cannot be empty! ", errorMessage);
		
		//check no change in memory
		assertEquals(false, ftms.hasEmployees());
		assertEquals(0, ftms.numberOfEmployees());		
	}
	
	@Test
	public void testCreateEmployeeNameSpace() {
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		assertEquals(false, ftms.hasEmployees());
		assertEquals(0, ftms.numberOfEmployees());
		
		String staffName = " ";
		String staffRole = "chef";
		String staffSalary = "12.75";
		
		String errorMessage = null;
		
		FoodTruckController ftc = new FoodTruckController();
		
		try {
			ftc.createEmployee(staffName, staffRole, staffSalary);
		} catch (InvalidInputException e) {
			errorMessage = e.getMessage();
		}
		
		//check error
		assertEquals("Employee name cannot be empty! ", errorMessage);
		
		//check no change in memory
		assertEquals(false, ftms.hasEmployees());
		assertEquals(0, ftms.numberOfEmployees());		
	}
	
	@Test
	public void testCreateEmployeeRoleNull() {
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		assertEquals(false, ftms.hasEmployees());
		assertEquals(0, ftms.numberOfEmployees());
		
		String staffName = "bruh";
		String staffRole = null;
		String staffSalary = "12.75";
		
		String errorMessage = null;
		
		FoodTruckController ftc = new FoodTruckController();
		
		try {
			ftc.createEmployee(staffName, staffRole, staffSalary);
		} catch (InvalidInputException e) {
			errorMessage = e.getMessage();
		}
		
		//check error
		assertEquals("Role cannot be empty! ", errorMessage);
		
		//check no change in memory
		assertEquals(false, ftms.hasEmployees());
		assertEquals(0, ftms.numberOfEmployees());		
	}
	
	@Test
	public void testCreateEmployeeRoleEmpty() {
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		assertEquals(false, ftms.hasEmployees());
		assertEquals(0, ftms.numberOfEmployees());
		
		String staffName = "bruh";
		String staffRole = "";
		String staffSalary = "12.75";
		
		String errorMessage = null;
		
		FoodTruckController ftc = new FoodTruckController();
		
		try {
			ftc.createEmployee(staffName, staffRole, staffSalary);
		} catch (InvalidInputException e) {
			errorMessage = e.getMessage();
		}
		
		//check error
		assertEquals("Role cannot be empty! ", errorMessage);
		
		//check no change in memory
		assertEquals(false, ftms.hasEmployees());
		assertEquals(0, ftms.numberOfEmployees());		
	}
	
	@Test
	public void testCreateEmployeeRoleSpace() {
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		assertEquals(false, ftms.hasEmployees());
		assertEquals(0, ftms.numberOfEmployees());
		
		String staffName = "bruh";
		String staffRole = " ";
		String staffSalary = "12.75";
		
		String errorMessage = null;
		
		FoodTruckController ftc = new FoodTruckController();
		
		try {
			ftc.createEmployee(staffName, staffRole, staffSalary);
		} catch (InvalidInputException e) {
			errorMessage = e.getMessage();
		}
		
		//check error
		assertEquals("Role cannot be empty! ", errorMessage);
		
		//check no change in memory
		assertEquals(false, ftms.hasEmployees());
		assertEquals(0, ftms.numberOfEmployees());		
	}
	
	@Test
	public void testCreateEmployeeSalaryTooManyDecimals() {
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		assertEquals(false, ftms.hasEmployees());
		assertEquals(0, ftms.numberOfEmployees());
		
		String staffName = "bruh";
		String staffRole = "chef";
		String staffSalary = "12.755555";
		
		String errorMessage = null;
		
		FoodTruckController ftc = new FoodTruckController();
		
		try {
			ftc.createEmployee(staffName, staffRole, staffSalary);
		} catch (InvalidInputException e) {
			errorMessage = e.getMessage();
		}
		
		//check error
		assertEquals("Salary cannot contain fractions of cents! ", errorMessage);
		
		//check no change in memory
		assertEquals(false, ftms.hasEmployees());
		assertEquals(0, ftms.numberOfEmployees());		
	}
	
	@Test
	public void testCreateEmployeeSalaryTooLow() {
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		assertEquals(false, ftms.hasEmployees());
		assertEquals(0, ftms.numberOfEmployees());
		
		String staffName = "bruh";
		String staffRole = "chef";
		String staffSalary = "0.75";
		
		String errorMessage = null;
		
		FoodTruckController ftc = new FoodTruckController();
		
		try {
			ftc.createEmployee(staffName, staffRole, staffSalary);
		} catch (InvalidInputException e) {
			errorMessage = e.getMessage();
		}
		
		//check error
		assertEquals("Minimum wage is 10.75! Treat your workers fairly! ", errorMessage);
		
		//check no change in memory
		assertEquals(false, ftms.hasEmployees());
		assertEquals(0, ftms.numberOfEmployees());		
	}
	
	@Test
	public void testCreateEmployeeSalaryNotNumber() {
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		assertEquals(false, ftms.hasEmployees());
		assertEquals(0, ftms.numberOfEmployees());
		
		String staffName = "bruh";
		String staffRole = "chef";
		String staffSalary = "blaze";
		
		String errorMessage = null;
		
		FoodTruckController ftc = new FoodTruckController();
		
		try {
			ftc.createEmployee(staffName, staffRole, staffSalary);
		} catch (InvalidInputException e) {
			errorMessage = e.getMessage();
		}
		
		//check error
		assertEquals("Salary must be a number! ", errorMessage);
		
		//check no change in memory
		assertEquals(false, ftms.hasEmployees());
		assertEquals(0, ftms.numberOfEmployees());		
	}
	
	@Test
	public void testCreateEmployeeSalaryNull() {
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		assertEquals(false, ftms.hasEmployees());
		assertEquals(0, ftms.numberOfEmployees());
		
		String staffName = "bruh";
		String staffRole = "chef";
		String staffSalary = null;
		
		String errorMessage = null;
		
		FoodTruckController ftc = new FoodTruckController();
		
		try {
			ftc.createEmployee(staffName, staffRole, staffSalary);
		} catch (InvalidInputException e) {
			errorMessage = e.getMessage();
		}
		
		//check error
		assertEquals("Salary must be a number! ", errorMessage);
		
		//check no change in memory
		assertEquals(false, ftms.hasEmployees());
		assertEquals(0, ftms.numberOfEmployees());		
	}

	@Test
	public void testRemoveEmployee() {
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		assertEquals(false, ftms.hasEmployees());
		assertEquals(0, ftms.numberOfEmployees());
		
		String staffName = "Bruh";
		String staffRole = "chef";
		String staffSalary = "12.75";
		Employee moyase=null;
		
		FoodTruckController ftc = new FoodTruckController();
		
		try {
			ftc.createEmployee(staffName, staffRole, staffSalary);
			Iterator<Employee> employeeIterator = ftms.getEmployees().iterator();
				while (employeeIterator.hasNext()) {
					if(staffName.equals(employeeIterator.next())){
						moyase=employeeIterator.next();
					}
				}
			ftc.removeEmployee(moyase);
		} catch(InvalidInputException e) {
			fail();
		}
		checkResultStaffRemoved(ftms);
		
		FoodTruckManager ftms2 = (FoodTruckManager)PersistenceXStream.loadFromXMLwithXStream();
		
		checkResultStaffRemoved(ftms2);		
	}

	//Checking methods for Staff
	private void checkResultStaff(String staffName, FoodTruckManager ftms) {
		assertEquals(staffName,ftms.getEmployee(0));
		assertEquals(1,ftms.getEmployees().size());
		assertEquals(true,ftms.hasEmployees());		
	}
	
	private void checkResultStaffRemoved(FoodTruckManager ftms) {
		assertEquals(0,ftms.getEmployees().size());
		assertEquals(false,ftms.hasEmployees());				
	}	
	
	
}
