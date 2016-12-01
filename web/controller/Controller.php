<?php
require_once __DIR__.'/InputValidator.php';
require_once __DIR__.'/../model/FoodTruckManager.php';
require_once __DIR__.'/../model/MenuItem.php';
require_once __DIR__.'/../model/Equipment.php';
require_once __DIR__.'/../model/Supply.php';
require_once __DIR__.'/../model/Employee.php';
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

	public function editEmployee($employee_role, $employee_salary, $employee_index){
        $role = InputValidator::validate_input($employee_role);
        $salary = InputValidator::validate_input($employee_salary);
        if($role==null || strlen($role)==0){
            throw new Exception("Item name cannot be empty");
        } else if(!is_numeric($salary)){
            throw new Exception("Salary must be a number");
        }else{
            $pft = new PersistenceFoodTruck();
            $ftm = $pft->loadDataFromStore();
            $ftm->getEmployee_index($employee_index)->setRole($role);
            $ftm->getEmployee_index($employee_index)->setSalaryPerHour($salary);
            $pft->writeDataToStore($ftm);
        }

    }

	public function createEmployee($employee_name,$employee_role, $employee_salary){
		$name = InputValidator::validate_input($employee_name);
		$role = InputValidator::validate_input($employee_role);
		$salary = InputValidator::validate_input($employee_salary);
		if($name==null || strlen($name)==0){
			throw new Exception("Employee name cannot be empty");
		} else if ($role == null || strlen($role)==0){
			throw new Exception("Employee role cannot be empty");
		} 
		// 		else if(!is_numeric($count)){
		// 			throw new Exception("Item count must be a number");
		// 		}
		else if(!is_numeric($salary)){
			throw new Exception("Salary must be a number");
		}else{
			$pft = new PersistenceFoodTruck();
			$ftm = $pft->loadDataFromStore();
			$employee = new Employee($name, $role, $salary);
			$ftm->addEmployee($employee);
			$pft->writeDataToStore($ftm);
		}
	}
	
	public function removeMenuItem($aMenuItem){
			$pft = new PersistenceFoodTruck();
			$ftm = $pft->loadDataFromStore();
			$ftm->removeMenuItemByName($aMenuItem);
			$pft->writeDataToStore($ftm);
	}
	
	public function removeSupply($aSupplyName){
		$pft = new PersistenceFoodTruck();
		$ftm = $pft->loadDataFromStore();
		$ftm->removeSupplyByName($aSupplyName);
		$pft->writeDataToStore($ftm);
	}

	public function removeEquipment($aEquipmentName){
		$pft = new PersistenceFoodTruck();
		$ftm = $pft->loadDataFromStore();
		$ftm->removeEquipmentByName($aEquipmentName);
		$pft->writeDataToStore($ftm);
	}

    public function removeEmployee($aEmployee){
        $pft = new PersistenceFoodTruck();
        $ftm = $pft->loadDataFromStore();
        $ftm->removeEmployeeByName($aEmployee);
        $pft->writeDataToStore($ftm);
    }

	public function editMenuItemPrice($aMenuItem, $aPrice){
		$price = InputValidator::validate_input($aPrice);
		if(!is_numeric($price)){
			throw new Exception("Item price must be a number");
		} else  {
			echo $price;
			echo $aMenuItem;
			$pft = new PersistenceFoodTruck();
			$ftm = $pft->loadDataFromStore();
			$ftm->editMenuItemPrice($aMenuItem, $price);
			$pft->writeDataToStore($ftm);
		}
	}
	
	public function editSupplyCount($aSupplyItem, $aCount){
		$count = InputValidator::validate_input($aCount);
		$supplyitem = InputValidator::validate_input($aSupplyItem);
		if(!is_numeric($count)){
			throw new Exception("Item count must be a number");
		} else  {
			$pft = new PersistenceFoodTruck();
			$ftm = $pft->loadDataFromStore();
			$ftm->editSupplyCount($supplyitem, $count);
			$pft->writeDataToStore($ftm);
		}
	}
	
	public function editEquipmentCount($aEquipmentItem, $aCount){
		$count = InputValidator::validate_input($aCount);
		$equipmentItem = InputValidator::validate_input($aEquipmentItem);
		if(!is_numeric($count)){
			throw new Exception("Item count must be a number");
		} else  {
			$pft = new PersistenceFoodTruck();
			$ftm = $pft->loadDataFromStore();
			$ftm->editEquipmentCount($equipmentItem, $count);
			$pft->writeDataToStore($ftm);
		}
	}
	
	
}