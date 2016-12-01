package ca.mcgill.ecse321.foodtruck.controller;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Time;
import java.util.*;

import ca.mcgill.ecse321.foodtruck.model.Employee;
import ca.mcgill.ecse321.foodtruck.model.FoodTruckManager;
import ca.mcgill.ecse321.foodtruck.model.Shift;
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

	
	//Test Employees
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
		checkResultAddStaff(staffName, ftms);
		
		FoodTruckManager ftms2 = (FoodTruckManager)PersistenceXStream.loadFromXMLwithXStream();
		
		checkResultAddStaff(staffName, ftms2);		
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
		
		FoodTruckController ftc = new FoodTruckController();
		
		try {
			ftc.createEmployee(staffName, staffRole, staffSalary);
			ftc.removeEmployee(ftms.getEmployee(0));
		} catch(InvalidInputException e) {
			fail();
		}
		checkResultRemoveStaff(ftms);
		
		FoodTruckManager ftms2 = (FoodTruckManager)PersistenceXStream.loadFromXMLwithXStream();
		
		checkResultRemoveStaff(ftms2);		
	}

	
	//Test Shifts
	@Test
	public void testCreateShift() {
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		assertEquals(false, ftms.hasEmployees());
		assertEquals(0, ftms.numberOfEmployees());
		
		Employee Biceps=null;
		String staffName = "Bruh";
		String staffRole = "chef";
		String staffSalary = "12.75";
		
		String day = "Monday";
		Time startTime = Time.valueOf("09:00");
		Time endTime = Time.valueOf("12:00");
		
		FoodTruckController ftc = new FoodTruckController();
		
		try {
			ftc.createEmployee(staffName, staffRole, staffSalary);
			Biceps=ftms.getEmployee(0);
			ftc.createShift(Biceps, day, startTime, endTime);
		} catch(InvalidInputException e) {
			fail();
		}
		checkResultAddShift(Biceps, ftms);
		
		FoodTruckManager ftms2 = (FoodTruckManager)PersistenceXStream.loadFromXMLwithXStream();
		
		checkResultAddShift(Biceps, ftms2);		
	}
	

	@Test
	public void testCreateShiftNullEmployee() {
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		assertEquals(false, ftms.hasEmployees());
		assertEquals(0, ftms.numberOfEmployees());

		Employee Biceps=null;
		
		String day = "Monday";
		Time startTime = Time.valueOf("09:00");
		Time endTime = Time.valueOf("12:00");
		
		String errorMessage = null;
		
		FoodTruckController ftc = new FoodTruckController();

		try {
			ftc.createShift(Biceps, day, startTime, endTime);
		} catch (InvalidInputException e) {
			errorMessage = e.getMessage();
		}
			
		//check error
		assertEquals("Please fill out the entire form before adding a shift in! ", errorMessage);
		
		//check no change in memory
		assertEquals(false, ftms.hasEmployees());
		assertEquals(0, ftms.numberOfEmployees());		
	}

	@Test
	public void testCreateShiftNullDay() {
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		assertEquals(false, ftms.hasEmployees());
		assertEquals(0, ftms.numberOfEmployees());

		Employee Biceps=null;
		String staffName = "Bruh";
		String staffRole = "chef";
		String staffSalary = "12.75";
		
		String day = null;
		Time startTime = Time.valueOf("09:00");
		Time endTime = Time.valueOf("12:00");
		
		String errorMessage = null;
		
		FoodTruckController ftc = new FoodTruckController();

		try {
			ftc.createEmployee(staffName, staffRole, staffSalary);
			Biceps=ftms.getEmployee(0);
			ftc.createShift(Biceps, day, startTime, endTime);
		} catch (InvalidInputException e) {
			errorMessage = e.getMessage();
		}
			
		//check error
		assertEquals("Please fill out the entire form before adding a shift in! ", errorMessage);
		
		//check no change in memory
		assertEquals(false, ftms.getEmployee(0).hasShifts());
		assertEquals(false, Biceps.hasShifts());		
	}

	@Test
	public void testCreateShiftEmptyDay() {
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		assertEquals(false, ftms.hasEmployees());
		assertEquals(0, ftms.numberOfEmployees());

		Employee Biceps=null;
		String staffName = "Bruh";
		String staffRole = "chef";
		String staffSalary = "12.75";
		
		String day = "";
		Time startTime = Time.valueOf("09:00");
		Time endTime = Time.valueOf("12:00");
		
		String errorMessage = null;
		
		FoodTruckController ftc = new FoodTruckController();

		try {
			ftc.createEmployee(staffName, staffRole, staffSalary);
			Biceps=ftms.getEmployee(0);
			ftc.createShift(Biceps, day, startTime, endTime);
		} catch (InvalidInputException e) {
			errorMessage = e.getMessage();
		}
			
		//check error
		assertEquals("Please select a day of the week! ", errorMessage);
		
		//check no change in memory
		assertEquals(false, ftms.getEmployee(0).hasShifts());
		assertEquals(false, Biceps.hasShifts());		
	}

	@Test
	public void testCreateShiftSpaceDay() {
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		assertEquals(false, ftms.hasEmployees());
		assertEquals(0, ftms.numberOfEmployees());

		Employee Biceps=null;
		String staffName = "Bruh";
		String staffRole = "chef";
		String staffSalary = "12.75";
		
		String day = " ";
		Time startTime = Time.valueOf("09:00");
		Time endTime = Time.valueOf("12:00");
		
		String errorMessage = null;
		
		FoodTruckController ftc = new FoodTruckController();

		try {
			ftc.createEmployee(staffName, staffRole, staffSalary);
			Biceps=ftms.getEmployee(0);
			ftc.createShift(Biceps, day, startTime, endTime);
		} catch (InvalidInputException e) {
			errorMessage = e.getMessage();
		}
			
		//check error
		assertEquals("Please select a day of the week! ", errorMessage);
		
		//check no change in memory
		assertEquals(false, ftms.getEmployee(0).hasShifts());
		assertEquals(false, Biceps.hasShifts());		
	}

	@Test
	public void testCreateShiftNullStartTime() {
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		assertEquals(false, ftms.hasEmployees());
		assertEquals(0, ftms.numberOfEmployees());

		Employee Biceps=null;
		String staffName = "Bruh";
		String staffRole = "chef";
		String staffSalary = "12.75";
		
		String day = "Monday";
		Time startTime = null;
		Time endTime = Time.valueOf("12:00");
		
		String errorMessage = null;
		
		FoodTruckController ftc = new FoodTruckController();

		try {
			ftc.createEmployee(staffName, staffRole, staffSalary);
			Biceps=ftms.getEmployee(0);
			ftc.createShift(Biceps, day, startTime, endTime);
		} catch (InvalidInputException e) {
			errorMessage = e.getMessage();
		}
			
		//check error
		assertEquals("Please fill out the entire form before adding a shift in! ", errorMessage);
		
		//check no change in memory
		assertEquals(false, ftms.getEmployee(0).hasShifts());
		assertEquals(false, Biceps.hasShifts());		
	}

	@Test
	public void testCreateShiftNullEndTime() {
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		assertEquals(false, ftms.hasEmployees());
		assertEquals(0, ftms.numberOfEmployees());

		Employee Biceps=null;
		String staffName = "Bruh";
		String staffRole = "chef";
		String staffSalary = "12.75";
		
		String day = "Monday";
		Time startTime = Time.valueOf("09:00");
		Time endTime = null;
		
		String errorMessage = null;
		
		FoodTruckController ftc = new FoodTruckController();

		try {
			ftc.createEmployee(staffName, staffRole, staffSalary);
			Biceps=ftms.getEmployee(0);
			ftc.createShift(Biceps, day, startTime, endTime);
		} catch (InvalidInputException e) {
			errorMessage = e.getMessage();
		}
			
		//check error
		assertEquals("Please fill out the entire form before adding a shift in! ", errorMessage);
		
		//check no change in memory
		assertEquals(false, ftms.getEmployee(0).hasShifts());
		assertEquals(false, Biceps.hasShifts());		
	}

	@Test
	public void testCreateShiftEndTimeBeforeStartTime() {
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		assertEquals(false, ftms.hasEmployees());
		assertEquals(0, ftms.numberOfEmployees());

		Employee Biceps=null;
		String staffName = "Bruh";
		String staffRole = "chef";
		String staffSalary = "12.75";
		
		String day = "Monday";
		Time startTime = Time.valueOf("09:00");
		Time endTime = Time.valueOf("08:00");
		
		String errorMessage = null;
		
		FoodTruckController ftc = new FoodTruckController();

		try {
			ftc.createEmployee(staffName, staffRole, staffSalary);
			Biceps=ftms.getEmployee(0);
			ftc.createShift(Biceps, day, startTime, endTime);
		} catch (InvalidInputException e) {
			errorMessage = e.getMessage();
		}
			
		//check error
		assertEquals("Shift end time cannot be before shift start time! ", errorMessage);
		
		//check no change in memory
		assertEquals(false, ftms.getEmployee(0).hasShifts());
		assertEquals(false, Biceps.hasShifts());		
	}

	
	@Test
	public void testRemoveShift() {
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		assertEquals(false, ftms.hasEmployees());
		assertEquals(0, ftms.numberOfEmployees());
		
		Employee Biceps=null;
		String staffName = "Bruh";
		String staffRole = "chef";
		String staffSalary = "12.75";
		
		String day = "Monday";
		Time startTime = Time.valueOf("09:00");
		Time endTime = Time.valueOf("12:00");
		
		FoodTruckController ftc = new FoodTruckController();
		
		try {
			ftc.createEmployee(staffName, staffRole, staffSalary);
			Biceps=ftms.getEmployee(0);
			ftc.createShift(Biceps, day, startTime, endTime);
			ftc.removeShift(Biceps, Biceps.getShift(0));
		} catch(InvalidInputException e) {
			fail();
		}
		checkResultRemoveShift(Biceps, ftms);
		
		FoodTruckManager ftms2 = (FoodTruckManager)PersistenceXStream.loadFromXMLwithXStream();
		
		checkResultRemoveShift(Biceps, ftms2);		
	}
	
	@Test
	public void testRemoveShiftNullEmployee() {
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		assertEquals(false, ftms.hasEmployees());
		assertEquals(0, ftms.numberOfEmployees());
		
		Employee Biceps=null;
		Employee noName=null;
		String staffName = "Bruh";
		String staffRole = "chef";
		String staffSalary = "12.75";
		
		String day = "Monday";
		Time startTime = Time.valueOf("09:00");
		Time endTime = Time.valueOf("12:00");
		
		FoodTruckController ftc = new FoodTruckController();
		
		String errorMessage=null;
		
		try {
			ftc.createEmployee(staffName, staffRole, staffSalary);
			Biceps=ftms.getEmployee(0);
			ftc.createShift(Biceps, day, startTime, endTime);
			ftc.removeShift(noName, Biceps.getShift(0));
		} catch(InvalidInputException e) {
			errorMessage = e.getMessage();
		}
			
		//check error
		assertEquals("Please fill out the form before removing shift! ", errorMessage);
		
		//check no change in memory
		assertEquals(true, ftms.getEmployee(0).hasShifts());
		assertEquals(true, Biceps.hasShifts());		
	}
	
	@Test
	public void testRemoveShiftNullShift() {
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		assertEquals(false, ftms.hasEmployees());
		assertEquals(0, ftms.numberOfEmployees());
		
		Employee Biceps=null;
		Shift noShift = null;
		String staffName = "Bruh";
		String staffRole = "chef";
		String staffSalary = "12.75";
		
		String day = "Monday";
		Time startTime = Time.valueOf("09:00");
		Time endTime = Time.valueOf("12:00");
		
		FoodTruckController ftc = new FoodTruckController();
		
		String errorMessage=null;
		
		try {
			ftc.createEmployee(staffName, staffRole, staffSalary);
			Biceps=ftms.getEmployee(0);
			ftc.createShift(Biceps, day, startTime, endTime);
			ftc.removeShift(Biceps, noShift);
		} catch(InvalidInputException e) {
			errorMessage = e.getMessage();
		}
			
		//check error
		assertEquals("Please fill out the form before removing shift! ", errorMessage);
		
		//check no change in memory
		assertEquals(true, ftms.getEmployee(0).hasShifts());
		assertEquals(true, Biceps.hasShifts());		
	}
	
	
	//Checking methods for Staff
	private void checkResultAddStaff(String staffName, FoodTruckManager ftms) {
		assertEquals(staffName,ftms.getEmployee(0));
		assertEquals(1,ftms.getEmployees().size());
		assertEquals(true,ftms.hasEmployees());		
	}
	
	private void checkResultRemoveStaff(FoodTruckManager ftms) {
		assertEquals(0,ftms.getEmployees().size());
		assertEquals(false,ftms.hasEmployees());				
	}	
	
	//Checking methods for Shift
	private void checkResultAddShift(Employee biceps, FoodTruckManager ftms) {
		assertEquals(1,biceps.numberOfShifts(),ftms.getEmployee(0).numberOfShifts());
		assertEquals(biceps.getShift(0),ftms.getEmployee(0).getShift(0));
		assertEquals(true,biceps.hasShifts());
		assertEquals(true,ftms.getEmployee(0).hasShifts());
	}
	
	private void checkResultRemoveShift(Employee biceps, FoodTruckManager ftms) {
		assertEquals(0,biceps.numberOfShifts(),ftms.getEmployee(0).numberOfShifts());
		assertEquals(biceps.getShift(0),ftms.getEmployee(0).getShift(0));
		assertEquals(false,biceps.hasShifts());
		assertEquals(false,ftms.getEmployee(0).hasShifts());
	}
	
	
}
