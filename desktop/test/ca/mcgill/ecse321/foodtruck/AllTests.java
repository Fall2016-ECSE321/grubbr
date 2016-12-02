package ca.mcgill.ecse321.foodtruck;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ca.mcgill.ecse321.foodtruck.controller.TestFoodTruckEdit_Inventory;
import ca.mcgill.ecse321.foodtruck.controller.TestFoodTruckMenuItems;
import ca.mcgill.ecse321.foodtruck.controller.TestFoodTruckStaff;
import ca.mcgill.ecse321.foodtruck.persistence.TestPersistenceXStream;



@RunWith(Suite.class)
@SuiteClasses({TestFoodTruckMenuItems.class, TestFoodTruckEdit_Inventory.class, TestFoodTruckStaff.class, TestPersistenceXStream.class})
public class AllTests {

}
