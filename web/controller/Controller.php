<?php
require_once __DIR__.'/InputValidator.php';
require_once __DIR__.'/../model/FoodTruckManager.php';
require_once __DIR__.'/../model/MenuItem.php';
require_once __DIR__.'/../model/Equipment.php';
require_once __DIR__.'/../model/Supply.php';
require_once __DIR__.'/../persistence/PersistenceFoodTruck.php';

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
			$item = new MenuItem($name, $price, 0); //0 for the moment since we will implement "amountSold" in a later iteration
			$ftm->addMenuItem($item);
			//Write data
			$pft->writeDataToStore($ftm);
		}
	}
	public function createEquipmentItem($item_name,$item_count){
		$name = InputValidator::validate_input($item_name);
		$count = InputValidator::validate_input($item_count);
		if($name==null || strlen($name)==0){
			throw new Exception("Item name cannot be empty");
		} else if ($count == null || strlen($count)==0){
			throw new Exception("Item count cannot be empty");
		} 
// 		else if(!is_numeric($count)){
// 			throw new Exception("Item count must be a number");
// 		} 
		else if(!is_numeric($count)){
			throw new Exception("Item count must be an integer");
		}else{
			$pft = new PersistenceFoodTruck();
			$ftm = $pft->loadDataFromStore();
			$item = new Equipment($name, $count);
			$ftm->addEquipment($item);
			$pft->writeDataToStore($ftm);
		}
	}
	
	public function createSupplyItem($item_name,$item_count){
		$name = InputValidator::validate_input($item_name);
		$count = InputValidator::validate_input($item_count);
		if($name==null || strlen($name)==0){
			throw new Exception("Item name cannot be empty");
		} else if ($count == null || strlen($count)==0){
			throw new Exception("Item count cannot be empty");
		}
		// 		else if(!is_numeric($count)){
		// 			throw new Exception("Item count must be a number");
		// 		}
		else if(!is_numeric($count)){
			throw new Exception("Item count must be an integer");
		}else{
			$pft = new PersistenceFoodTruck();
			$ftm = $pft->loadDataFromStore();
			$item = new Supply($name, $count);
			$ftm->addSupply($item);
			$pft->writeDataToStore($ftm);
		}
	}
}