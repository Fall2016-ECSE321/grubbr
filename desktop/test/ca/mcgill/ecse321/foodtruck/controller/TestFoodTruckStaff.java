package ca.mcgill.ecse321.foodtruck.controller;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Time;

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
		checkResultAddStaff(ftms,staffName,staffRole,staffSalary);
		
		FoodTruckManager ftms2 = (FoodTruckManager)PersistenceXStream.loadFromXMLwithXStream();
		
		checkResultAddStaff(ftms2,staffName,staffRole,staffSalary);		
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
		assertEquals("Employee name cannot be empty!", errorMessage);
		
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
		assertEquals("Employee name cannot be empty!", errorMessage);
		
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
		assertEquals("Employee name cannot be empty!", errorMessage);
		
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
		assertEquals("Role cannot be empty!", errorMessage);
		
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
		assertEquals("Role cannot be empty!", errorMessage);
		
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
		assertEquals("Role cannot be empty!", errorMessage);
		
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
		assertEquals("Salary cannot contain fractions of cents!", errorMessage);
		
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
		assertEquals("Minimum wage is 10.75! Treat your workers fairly!", errorMessage);
		
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
		assertEquals("Salary must be a number!", errorMessage);
		
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
		assertEquals("Salary must be a number!", errorMessage);
		
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
		
		Employee employee=null;
		String staffName = "Bruh";
		String staffRole = "chef";
		String staffSalary = "12.75";
		
		String day = "Monday";
		Time startTime = new Time(9000);
		Time endTime = new Time(10000);
		
		FoodTruckController ftc = new FoodTruckController();
		
		try {
			ftc.createEmployee(staffName, staffRole, staffSalary);
			employee=ftms.getEmployee(0);
			ftc.createShift(employee, day, startTime, endTime);
		} catch(InvalidInputException e) {
			fail();
		}
		checkResultAddShift(ftms,employee,day,startTime,endTime);
		
		FoodTruckManager ftms2 = (FoodTruckManager)PersistenceXStream.loadFromXMLwithXStream();
		
		checkResultAddShift(ftms2,employee,day,startTime,endTime);		
	}
	

	@Test
	public void testCreateShiftNullEmployee() {
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		assertEquals(false, ftms.hasEmployees());
		assertEquals(0, ftms.numberOfEmployees());

		Employee Biceps=null;
		
		String day = "Monday";
		Time startTime = new Time(9000);
		Time endTime = new Time(10000);
		
		String errorMessage = null;
		
		FoodTruckController ftc = new FoodTruckController();

		try {
			ftc.createShift(Biceps, day, startTime, endTime);
		} catch (InvalidInputException e) {
			errorMessage = e.getMessage();
		}
			
		//check error
		assertEquals("Please fill out the entire form before adding a shift in!", errorMessage);
		
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
		Time startTime = new Time(9000);
		Time endTime = new Time(10000);
		
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
		assertEquals("Please fill out the entire form before adding a shift in! "
				+ "Please select a day of the week!", errorMessage);
		
		//check no change in memory
		checkResultRemoveShift(ftms);		
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
		Time startTime = new Time(9000);
		Time endTime = new Time(10000);
		
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
		assertEquals("Please select a day of the week!", errorMessage);
		
		//check no change in memory
		checkResultRemoveShift(ftms);		
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
		Time startTime = new Time(9000);
		Time endTime = new Time(10000);
		
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
		assertEquals("Please select a day of the week!", errorMessage);
		
		//check no change in memory
		checkResultRemoveShift(ftms);		
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
		Time endTime = new Time(10000);
		
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
		assertEquals("Please fill out the entire form before adding a shift in!", errorMessage);
		
		checkResultRemoveShift(ftms);		
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
		Time startTime = new Time(9000);		
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
		assertEquals("Please fill out the entire form before adding a shift in!", errorMessage);
		
		checkResultRemoveShift(ftms);		
	}

	@Test
	public void testCreateShiftEndTimeBeforeStartTime() {
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		assertEquals(false, ftms.hasEmployees());
		assertEquals(0, ftms.numberOfEmployees());

		String staffName = "Bruh";
		String staffRole = "chef";
		String staffSalary = "12.75";
		
		String day = "Monday";
		Time startTime = new Time(10000);
		Time endTime = new Time(9000);
		
		String errorMessage = null;
		
		FoodTruckController ftc = new FoodTruckController();

		try {
			ftc.createEmployee(staffName, staffRole, staffSalary);
			Employee biceps = ftms.getEmployee(0);
			ftc.createShift(biceps, day, startTime, endTime);
		} catch (InvalidInputException e) {
			errorMessage = e.getMessage();
		}
			
		//check error
		assertEquals("Shift end time cannot be before shift start time!", errorMessage);
		
		checkResultRemoveShift(ftms);	
	}

	
	@Test
	public void testRemoveShift() {
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		assertEquals(false, ftms.hasEmployees());
		assertEquals(0, ftms.numberOfEmployees());
		
		Employee biceps=null;
		String staffName = "Bruh";
		String staffRole = "chef";
		String staffSalary = "12.75";
		
		String day = "Monday";
		Time startTime = new Time(9000);
		Time endTime = new Time(10000);
		
		FoodTruckController ftc = new FoodTruckController();
		
		try {
			ftc.createEmployee(staffName, staffRole, staffSalary);
			biceps = ftms.getEmployee(0);
			ftc.createShift(biceps, day, startTime, endTime);
			ftc.cancelShift(ftms.getShift(0));
		} catch(InvalidInputException e) {
			fail();
		}
		checkResultRemoveShift(ftms);
		
		FoodTruckManager ftms2 = (FoodTruckManager)PersistenceXStream.loadFromXMLwithXStream();
		
		checkResultRemoveShift(ftms2);		
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
		Time startTime = new Time(9000);
		Time endTime = new Time(10000);
		
		FoodTruckController ftc = new FoodTruckController();
		
		String errorMessage=null;
		
		try {
			ftc.createEmployee(staffName, staffRole, staffSalary);
			Biceps=ftms.getEmployee(0);
			ftc.createShift(Biceps, day, startTime, endTime);
			ftc.cancelShift(noShift);
		} catch(InvalidInputException e) {
			errorMessage = e.getMessage();
		}
			
		//check error
		assertEquals("Please select a shift to be removed!", errorMessage);
		
		//check no change in memory
		assertEquals(1, ftms.numberOfShifts());
	}
	
	
	//Checking methods for Staff
	private void checkResultAddStaff(FoodTruckManager ftms, String employeeName, String role, String salary) {
		assertEquals(employeeName,ftms.getEmployee(0).getName());
		assertEquals(role,ftms.getEmployee(0).getRole());
		assertEquals(Double.parseDouble(salary),ftms.getEmployee(0).getSalaryPerHour(),0.005);
		assertEquals(1,ftms.getEmployees().size());
		assertEquals(true,ftms.hasEmployees());		
	}
	
	private void checkResultRemoveStaff(FoodTruckManager ftms) {
		assertEquals(0,ftms.getEmployees().size());
		assertEquals(false,ftms.hasEmployees());				
	}	
	
	//Checking methods for Shift
	private void checkResultAddShift(FoodTruckManager ftms, Employee employee, String day, Time startTime, Time endTime) {
		assertEquals(1,ftms.numberOfShifts());
	}
	
	private void checkResultRemoveShift(FoodTruckManager ftms) {
		assertEquals(false, ftms.hasShifts());
	}
	
	
}
