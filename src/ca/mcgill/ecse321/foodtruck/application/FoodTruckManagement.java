package ca.mcgill.ecse321.foodtruck.application;

import ca.mcgill.ecse321.foodtruck.persistence.PersistenceFoodTruck;
import ca.mcgill.ecse321.foodtruck.view.FoodTruckManagementPage;

public class FoodTruckManagement {
	
	public static void main(String[] args) {
		
		//load model
		PersistenceFoodTruck.loadFoodTruckModel();
		
		//initialize view
		FoodTruckManagementPage.main(args);
		
	}

}