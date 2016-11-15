<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

class FoodTruckManager
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static $theInstance = null;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //FoodTruckManager Associations
  private $employees;
  private $order;
  private $supplies;
  private $equipment;
  private $menuItems;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  private function __construct()
  {
    $this->employees = array();
    $this->order = array();
    $this->supplies = array();
    $this->equipment = array();
    $this->menuItems = array();
  }

  public static function getInstance()
  {
    if(self::$theInstance == null)
    {
      self::$theInstance = new FoodTruckManager();
    }
    return self::$theInstance;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function getEmployee_index($index)
  {
    $aEmployee = $this->employees[$index];
    return $aEmployee;
  }

  public function getEmployees()
  {
    $newEmployees = $this->employees;
    return $newEmployees;
  }

  public function numberOfEmployees()
  {
    $number = count($this->employees);
    return $number;
  }

  public function hasEmployees()
  {
    $has = $this->numberOfEmployees() > 0;
    return $has;
  }

  public function indexOfEmployee($aEmployee)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->employees as $employee)
    {
      if ($employee->equals($aEmployee))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function getOrder_index($index)
  {
    $aOrder = $this->order[$index];
    return $aOrder;
  }

  public function getOrder()
  {
    $newOrder = $this->order;
    return $newOrder;
  }

  public function numberOfOrder()
  {
    $number = count($this->order);
    return $number;
  }

  public function hasOrder()
  {
    $has = $this->numberOfOrder() > 0;
    return $has;
  }

  public function indexOfOrder($aOrder)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->order as $order)
    {
      if ($order->equals($aOrder))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function getSupply_index($index)
  {
    $aSupply = $this->supplies[$index];
    return $aSupply;
  }

  public function getSupplies()
  {
    $newSupplies = $this->supplies;
    return $newSupplies;
  }

  public function numberOfSupplies()
  {
    $number = count($this->supplies);
    return $number;
  }

  public function hasSupplies()
  {
    $has = $this->numberOfSupplies() > 0;
    return $has;
  }

  public function indexOfSupply($aSupply)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->supplies as $supply)
    {
      if ($supply->equals($aSupply))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function getEquipment_index($index)
  {
    $aEquipment = $this->equipment[$index];
    return $aEquipment;
  }

  public function getEquipment()
  {
    $newEquipment = $this->equipment;
    return $newEquipment;
  }

  public function numberOfEquipment()
  {
    $number = count($this->equipment);
    return $number;
  }

  public function hasEquipment()
  {
    $has = $this->numberOfEquipment() > 0;
    return $has;
  }

  public function indexOfEquipment($aEquipment)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->equipment as $equipment)
    {
      if ($equipment->equals($aEquipment))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function getMenuItem_index($index)
  {
    $aMenuItem = $this->menuItems[$index];
    return $aMenuItem;
  }

  public function getMenuItems()
  {
    $newMenuItems = $this->menuItems;
    return $newMenuItems;
  }

  public function numberOfMenuItems()
  {
    $number = count($this->menuItems);
    return $number;
  }

  public function hasMenuItems()
  {
    $has = $this->numberOfMenuItems() > 0;
    return $has;
  }

  public function indexOfMenuItem($aMenuItem)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->menuItems as $menuItem)
    {
      if ($menuItem->equals($aMenuItem))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }
  
  public function indexOfMenuItemByName($aMenuItem)
  {
  	$wasFound = false;
  	$index = 0;
  	foreach($this->menuItems as $menuItem)
  	{
  		//if ($menuItem->getName()->equals($aMenuItem))
  		if(strcmp($menuItem->getName(),$aMenuItem)==0)
  		{
  			$wasFound = true;
  			break;
  		}
  		$index += 1;
  	}
  	$index = $wasFound ? $index : -1;
  	//echo $index;
  	return $index;
  }
  
  public function indexOfSupplyByName($aSupplyItem)
  {
  	$wasFound = false;
  	$index = 0;
  	foreach($this->supplies as $supply)
  	{
  		//if ($menuItem->getName()->equals($aMenuItem))
  		if(strcmp($supply->getName(),$aSupplyItem)==0)
  		{
  			$wasFound = true;
  			break;
  		}
  		$index += 1;
  	}
  	$index = $wasFound ? $index : -1;
  	//echo $index;
  	return $index;
  }
  
  public function indexOfEquipmentByName($aEquipmentItem)
  {
  	$wasFound = false;
  	$index = 0;
  	foreach($this->equipment as $equipment)
  	{
  		//if ($menuItem->getName()->equals($aMenuItem))
  		if(strcmp($equipment->getName(),$aEquipmentItem)==0)
  		{
  			$wasFound = true;
  			break;
  		}
  		$index += 1;
  	}
  	$index = $wasFound ? $index : -1;
  	//echo $index;
  	return $index;
  }

  public static function minimumNumberOfEmployees()
  {
    return 0;
  }

  public function addEmployee($aEmployee)
  {
    $wasAdded = false;
    if ($this->indexOfEmployee($aEmployee) !== -1) { return false; }
    $this->employees[] = $aEmployee;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeEmployee($aEmployee)
  {
    $wasRemoved = false;
    if ($this->indexOfEmployee($aEmployee) != -1)
    {
      unset($this->employees[$this->indexOfEmployee($aEmployee)]);
      $this->employees = array_values($this->employees);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addEmployeeAt($aEmployee, $index)
  {  
    $wasAdded = false;
    if($this->addEmployee($aEmployee))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfEmployees()) { $index = $this->numberOfEmployees() - 1; }
      array_splice($this->employees, $this->indexOfEmployee($aEmployee), 1);
      array_splice($this->employees, $index, 0, array($aEmployee));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveEmployeeAt($aEmployee, $index)
  {
    $wasAdded = false;
    if($this->indexOfEmployee($aEmployee) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfEmployees()) { $index = $this->numberOfEmployees() - 1; }
      array_splice($this->employees, $this->indexOfEmployee($aEmployee), 1);
      array_splice($this->employees, $index, 0, array($aEmployee));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addEmployeeAt($aEmployee, $index);
    }
    return $wasAdded;
  }

  public static function minimumNumberOfOrder()
  {
    return 0;
  }

  public function addOrder($aOrder)
  {
    $wasAdded = false;
    if ($this->indexOfOrder($aOrder) !== -1) { return false; }
    $this->order[] = $aOrder;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeOrder($aOrder)
  {
    $wasRemoved = false;
    if ($this->indexOfOrder($aOrder) != -1)
    {
      unset($this->order[$this->indexOfOrder($aOrder)]);
      $this->order = array_values($this->order);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addOrderAt($aOrder, $index)
  {  
    $wasAdded = false;
    if($this->addOrder($aOrder))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfOrder()) { $index = $this->numberOfOrder() - 1; }
      array_splice($this->order, $this->indexOfOrder($aOrder), 1);
      array_splice($this->order, $index, 0, array($aOrder));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveOrderAt($aOrder, $index)
  {
    $wasAdded = false;
    if($this->indexOfOrder($aOrder) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfOrder()) { $index = $this->numberOfOrder() - 1; }
      array_splice($this->order, $this->indexOfOrder($aOrder), 1);
      array_splice($this->order, $index, 0, array($aOrder));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addOrderAt($aOrder, $index);
    }
    return $wasAdded;
  }

  public static function minimumNumberOfSupplies()
  {
    return 0;
  }

  public function addSupply($aSupply)
  {
    $wasAdded = false;
    if ($this->indexOfSupply($aSupply) !== -1) { return false; }
    $this->supplies[] = $aSupply;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeSupply($aSupply)
  {
    $wasRemoved = false;
    if ($this->indexOfSupply($aSupply) != -1)
    {
      unset($this->supplies[$this->indexOfSupply($aSupply)]);
      $this->supplies = array_values($this->supplies);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addSupplyAt($aSupply, $index)
  {  
    $wasAdded = false;
    if($this->addSupply($aSupply))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfSupplies()) { $index = $this->numberOfSupplies() - 1; }
      array_splice($this->supplies, $this->indexOfSupply($aSupply), 1);
      array_splice($this->supplies, $index, 0, array($aSupply));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveSupplyAt($aSupply, $index)
  {
    $wasAdded = false;
    if($this->indexOfSupply($aSupply) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfSupplies()) { $index = $this->numberOfSupplies() - 1; }
      array_splice($this->supplies, $this->indexOfSupply($aSupply), 1);
      array_splice($this->supplies, $index, 0, array($aSupply));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addSupplyAt($aSupply, $index);
    }
    return $wasAdded;
  }

  public static function minimumNumberOfEquipment()
  {
    return 0;
  }

  public function addEquipment($aEquipment)
  {
    $wasAdded = false;
    if ($this->indexOfEquipment($aEquipment) !== -1) { return false; }
    $this->equipment[] = $aEquipment;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeEquipment($aEquipment)
  {
    $wasRemoved = false;
    if ($this->indexOfEquipment($aEquipment) != -1)
    {
      unset($this->equipment[$this->indexOfEquipment($aEquipment)]);
      $this->equipment = array_values($this->equipment);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addEquipmentAt($aEquipment, $index)
  {  
    $wasAdded = false;
    if($this->addEquipment($aEquipment))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfEquipment()) { $index = $this->numberOfEquipment() - 1; }
      array_splice($this->equipment, $this->indexOfEquipment($aEquipment), 1);
      array_splice($this->equipment, $index, 0, array($aEquipment));
      $wasAdded = true;
    }
    return $wasAdded;
  }
  


  public function addOrMoveEquipmentAt($aEquipment, $index)
  {
    $wasAdded = false;
    if($this->indexOfEquipment($aEquipment) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfEquipment()) { $index = $this->numberOfEquipment() - 1; }
      array_splice($this->equipment, $this->indexOfEquipment($aEquipment), 1);
      array_splice($this->equipment, $index, 0, array($aEquipment));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addEquipmentAt($aEquipment, $index);
    }
    return $wasAdded;
  }

  public static function minimumNumberOfMenuItems()
  {
    return 0;
  }

  public function addMenuItem($aMenuItem)
  {
    $wasAdded = false;
    if ($this->indexOfMenuItem($aMenuItem) !== -1) { return false; }
    $this->menuItems[] = $aMenuItem;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeMenuItem($aMenuItem)
  {
    $wasRemoved = false;
    if ($this->indexOfMenuItem($aMenuItem) != -1)
    {
      unset($this->menuItems[$this->indexOfMenuItem($aMenuItem)]);
      $this->menuItems = array_values($this->menuItems);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }
  
  public function removeMenuItemByName($aMenuItem)
  {
  	$wasRemoved = false;
  	if ($this->indexOfMenuItemByName($aMenuItem) != -1)
  	{
  		echo $this->indexOfMenuItemByName($aMenuItem);
  		unset($this->menuItems[$this->indexOfMenuItemByName($aMenuItem)]);
  		
  		$this->menuItems = array_values($this->menuItems);
  		$wasRemoved = true;
  	}
  	return $wasRemoved;
  }
  
  public function removeSupplyByName($aSupplyItem)
  {
  	$wasRemoved = false;
  	if ($this->indexOfSupplyByName($aSupplyItem) != -1)
  	{
  		echo $this->indexOfSupplyByName($aSupplyItem);
  		unset($this->supplies[$this->indexOfSupplyByName($aSupplyItem)]);
  
  		$this->supplies = array_values($this->supplies);
  		$wasRemoved = true;
  	}
  	return $wasRemoved;
  }
  
  public function removeEquipmentByName($aEquipmentItem)
  {
  	$wasRemoved = false;
  	if ($this->indexOfEquipmentByName($aEquipmentItem) != -1)
  	{
  		unset($this->equipment[$this->indexOfEquipmentByName($aEquipmentItem)]);
  
  		$this->equipment = array_values($this->equipment);
  		$wasRemoved = true;
  	}
  	return $wasRemoved;
  }
  
  public function editMenuItemPrice($aMenuItem, $aPrice)
  {
  	$priceEdited = false;
  	if ($this->indexOfMenuItemByName($aMenuItem) != -1)
  	{
  		echo $this->indexOfMenuItemByName($aMenuItem);
  		$this->menuItems[$this->indexOfMenuItemByName($aMenuItem)]->setPrice($aPrice);
  		$this->menuItems = array_values($this->menuItems);
  		$priceEdited = true;
  	}
  	return $priceEdited;
  }
  
  public function editSupplyCount($aSupplyItem, $aCount){
  	$countEdited = false;
  	if($this->indexOfSupplyByName($aSupplyItem) != -1)
  	{
  		$this->supplies[$this->indexOfSupplyByName($aSupplyItem)]->setCount($aCount);
  		$this->supplies = array_values($this->supplies);
  		$countEdited = true;
  	}
  }
  
  public function editEquipmentCount($aEquipmentItem, $aCount){
  	$countEdited = false;
  	if($this->indexOfEquipmentByName($aEquipmentItem) != -1)
  	{
  		$this->equipment[$this->indexOfEquipmentByName($aEquipmentItem)]->setCount($aCount);
  		$this->equipment = array_values($this->equipment);
  		$countEdited = true;
  	}
  }

  public function addMenuItemAt($aMenuItem, $index)
  {  
    $wasAdded = false;
    if($this->addMenuItem($aMenuItem))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfMenuItems()) { $index = $this->numberOfMenuItems() - 1; }
      array_splice($this->menuItems, $this->indexOfMenuItem($aMenuItem), 1);
      array_splice($this->menuItems, $index, 0, array($aMenuItem));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveMenuItemAt($aMenuItem, $index)
  {
    $wasAdded = false;
    if($this->indexOfMenuItem($aMenuItem) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfMenuItems()) { $index = $this->numberOfMenuItems() - 1; }
      array_splice($this->menuItems, $this->indexOfMenuItem($aMenuItem), 1);
      array_splice($this->menuItems, $index, 0, array($aMenuItem));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addMenuItemAt($aMenuItem, $index);
    }
    return $wasAdded;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    $this->employees = array();
    $this->order = array();
    $this->supplies = array();
    $this->equipment = array();
    $this->menuItems = array();
  }

}
?>