<?php
require_once __DIR__.'\..\Controller\InputValidator.php';
require_once __DIR__.'\..\Model\FoodTruckManager.php';
require_once __DIR__.'\..\Model\MenuItem.php';
require_once __DIR__.'\..\Persistence\PersistenceFoodTruck.php';

class Controller
{
	public function __construct()
	{
	}
	
	public function createMenuItem($item_name,$item_price){
		//Validate input
		$name = InputValidator::validate_input($item_name);
		$price= InputValidator::validate_input($item_price);
		
		//Error handling
		if ($name==null || strlen($name)==0){
			throw new Exception("Menu item name cannot be empty!");
		} else if ($price==null || strlen($price)==0){
			throw new Exception("Menu item price cannot be empty!");
		} else if ($price<0){
			throw new Exception("Menu item price cannot be negative!");
		} else if(!is_numeric($price)){
			throw new Exception("Menu item price has to be a number!");
		} else {
			//Load Data
			$pft = new PersistenceFoodTruck();
			$ftm = $pft->loadDataFromStore();
			//Add item
			$item = new MenuItem($name, $price, 0); //"0" Because how do we increment the current count ??
			$ftm->addMenuItem($item);
			//Write data
			$pft->writeDataToStore($pft);
		}
	}
}