<?php
define('__ROOT__', dirname(dirname(__FILE__)));
require_once '/Applications/XAMPP/xamppfiles/htdocs/persistence/PersistenceFoodTruck.php';
require_once '/Applications/XAMPP/xamppfiles/htdocs/model/MenuItem.php';
require_once '/Applications/XAMPP/xamppfiles/htdocs/model/FoodTruckManager.php';
require_once '/Applications/XAMPP/xamppfiles/htdocs/controller/controller.php';
//require_once __DIR__.'/../model/FoodTruckManager.php';

class ControllerTest extends PHPUnit_Framework_TestCase
{
    protected $c;
    protected $pft;
    protected $ftm;

    protected function setUp()
    {
        $this->c = new Controller();
        $this->pft = new PersistenceFoodTruck();
        $this->ftm = $this->pft->loadDataFromStore();
        $this->ftm->delete();
        $this->pft->writeDataToStore($this->ftm);
    }

    protected function tearDown()
    {
    }

    public function testCreateItem() {
        $this->assertEquals(0, count($this->ftm->getMenuItems()));
    
    	$name = "Burger";
    	$price= 10.0;
    	try {
    		$this->c->createMenuItem($name,$price);
    	} catch (Exception $e) {
    		// check that no error occurred
    		$this->fail();
    	}
    
    	// check file contents
    	$this->ftm = $this->pft->loadDataFromStore();
    	$this->assertEquals(1, count($this->ftm->getMenuItems()));
    	$this->assertEquals($name, $this->ftm->getMenuItem_index(0)->getName());
    	$this->assertEquals($price, $this->ftm->getMenuItem_index(0)->getPrice());
    }
    
    public function testCreateItemNameNull() {
        $this->assertEquals(0, count($this->ftm->getMenuItems()));
    
    	$name = null;
    	$price=10.0;
    	$error = "";
    	try {
    		$this->c->createMenuItem($name,$price);
  		} catch (Exception $e) {
			$error = $e->getMessage();
    	}
    
    	// check error
    	$this->assertEquals("Menu item name cannot be empty!", $error);
        // check file contents
    	$this->ftm = $this->pft->loadDataFromStore();
    	$this->assertEquals(0, count($this->ftm->getMenuItems()));
    }
    
    public function testCreateItemEmpty() {
    	$this->assertEquals(0, count($this->ftm->getMenuItems()));
    	$name = "";
    	$price = 10.0;
    
    	$error = "";
    	try {
    		$this->c->createMenuItem($name, $price);
    	} catch (Exception $e) {
    		$error = $e->getMessage();
    	}
    
    	// check error
    	$this->assertEquals("Menu item name cannot be empty!", $error);
    	// check file contents
    	$this->ftm = $this->pft->loadDataFromStore();
    	$this->assertEquals(0, count($this->ftm->getMenuItems()));
    }
    
    public function testCreateItemNoPrice() {
    	$this->assertEquals(0, count($this->ftm->getMenuItems()));
    
    	$name = "Burger";
    	$price=null;
    	$error = "";
    	try {
    		$this->c->createMenuItem($name, $price);
    	} catch (Exception $e) {
    		$error = $e->getMessage();
    	}
    
    	// check error
    	$this->assertEquals("Menu item price cannot be empty!", $error);
    	// check file contents
    	$this->ftm = $this->pft->loadDataFromStore();
    	$this->assertEquals(0, count($this->ftm->getMenuItems()));
    }
    public function testCreateItemNegativePrice() {
    	$this->assertEquals(0, count($this->ftm->getMenuItems()));
    
    	$name = "Burger";
    	$price=-10;
    	$error = "";
    	try {
    		$this->c->createMenuItem($name, $price);
    	} catch (Exception $e) {
    		$error = $e->getMessage();
    	}
    
    	// check error
    	$this->assertEquals("Menu item price cannot be negative!", $error);
    	// check file contents
    	$this->ftm = $this->pft->loadDataFromStore();
    	$this->assertEquals(0, count($this->ftm->getMenuItems()));
    }
    
    //NaN = Not a Number
    public function testCreateItemPriceNaN() {
    	$this->assertEquals(0, count($this->ftm->getMenuItems()));
    
    	$name = "Burger";
    	$price="ABC";
    	$error = "";
    	try {
    		$this->c->createMenuItem($name, $price);
    	} catch (Exception $e) {
    		$error = $e->getMessage();
    	}
    
    	// check error
    	$this->assertEquals("Menu item price has to be a positive number!", $error);
    	// check file contents
    	$this->ftm = $this->pft->loadDataFromStore();
    	$this->assertEquals(0, count($this->ftm->getMenuItems()));
    }

    public function testChangePriceValid(){
        $this->assertEquals(0, count($this->ftm->getMenuItems()));

        $name = "Burger";
        $price="10";
        $newPrice=15;

        try {
            $this->c->createMenuItem($name, $price);
            $this->c->editMenuItemPrice($name, $newPrice);
        } catch (Exception $e) {
            $error = $e->getMessage();
        }

        // check error
        $this->ftm = $this->pft->loadDataFromStore();
        $this->assertEquals(1, count($this->ftm->getMenuItems()));
        $this->assertEquals($name, $this->ftm->getMenuItem_index(0)->getName());
        $this->assertEquals($newPrice, $this->ftm->getMenuItem_index(0)->getPrice());
        // check file contents

    }

    public function testChangePriceNull(){
        $this->assertEquals(0, count($this->ftm->getMenuItems()));

        $name = "Burger";
        $price="10";
        $newPrice=null;

        try {
            $this->c->createMenuItem($name, $price);
            $this->c->editMenuItemPrice($name, $newPrice);
        } catch (Exception $e) {
            $error = $e->getMessage();
        }

        // check error
        $this->assertEquals("Item price must be a number", $error);
        // check file contents
        $this->ftm = $this->pft->loadDataFromStore();

        $this->assertEquals(1, count($this->ftm->getMenuItems()));
        // check file contents

    }

    public function testChangePriceNaN(){
        $this->assertEquals(0, count($this->ftm->getMenuItems()));

        $name = "Burger";
        $price="10";
        $newPrice="";

        try {
            $this->c->createMenuItem($name, $price);
            $this->c->editMenuItemPrice($name, $newPrice);
        } catch (Exception $e) {
            $error = $e->getMessage();
        }

        // check error
        $this->assertEquals("Item price must be a number", $error);
        // check file contents
        $this->ftm = $this->pft->loadDataFromStore();

        $this->assertEquals(1, count($this->ftm->getMenuItems()));
        // check file contents

    }
    public function testChangePriceString(){
        $this->assertEquals(0, count($this->ftm->getMenuItems()));

        $name = "Burger";
        $price="10";
        $newPrice="hello world!";

        try {
            $this->c->createMenuItem($name, $price);
            $this->c->editMenuItemPrice($name, $newPrice);
        } catch (Exception $e) {
            $error = $e->getMessage();
        }

        // check error
        $this->assertEquals("Item price must be a number", $error);
        // check file contents
        $this->ftm = $this->pft->loadDataFromStore();

        $this->assertEquals(1, count($this->ftm->getMenuItems()));
        // check file contents

    }
    public function testChangePriceEmptyItem(){
        $this->assertEquals(0, count($this->ftm->getMenuItems()));

        $name = "Burger";
        $price="10";
        $newPrice=15;

        try {
            $this->c->createMenuItem($name, $price);
            $this->c->editMenuItemPrice("", $newPrice);
        } catch (Exception $e) {
            $error = $e->getMessage();
        }

        // check error
        $this->assertEquals("Item cannot be empty", $error);
        // check file contents
        $this->ftm = $this->pft->loadDataFromStore();

        $this->assertEquals(1, count($this->ftm->getMenuItems()));
        // check file contents

    }

    public function testChangePriceNullItem(){
        $this->assertEquals(0, count($this->ftm->getMenuItems()));

        $name = "Burger";
        $price="10";
        $newPrice=15;

        try {
            $this->c->createMenuItem($name, $price);
            $this->c->editMenuItemPrice(null, $newPrice);
        } catch (Exception $e) {
            $error = $e->getMessage();
        }

        // check error
        $this->assertEquals("Item cannot be empty", $error);
        // check file contents
        $this->ftm = $this->pft->loadDataFromStore();

        $this->assertEquals(1, count($this->ftm->getMenuItems()));
        // check file contents
    }

    public function testCreateSupply() {
        $this->assertEquals(0, count($this->ftm->getSupplies()));

        $name = "Spatula";
        $count = 5;
        try {
            $this->c->createSupplyItem($name,$count);
        } catch (Exception $e) {
            // check that no error occurred
            $this->fail();
        }

        // check file contents
        $this->ftm = $this->pft->loadDataFromStore();
        $this->assertEquals(1, count($this->ftm->getSupplies()));
        $this->assertEquals($name, $this->ftm->getSupply_index(0)->getName());
        $this->assertEquals($count, $this->ftm->getSupply_index(0)->getCount());
    }

    public function testCreateSupplyNull() {
        $this->assertEquals(0, count($this->ftm->getSupplies()));

        $name = null;
        $count = 5;
        try {
            $this->c->createSupplyItem($name,$count);
        } catch (Exception $e) {
            // check that no error occurred
            $error = $e->getMessage();
        }


        $this->assertEquals("Item name cannot be empty", $error);
        $this->ftm = $this->pft->loadDataFromStore();
        $this->assertEquals(0, count($this->ftm->getSupplies()));

    }

    public function testCreateSupplyNullCount() {
        $this->assertEquals(0, count($this->ftm->getSupplies()));

        $name = "Spatula";
        $count = null;
        try {
            $this->c->createSupplyItem($name,$count);
        } catch (Exception $e) {
            // check that no error occurred
            $error = $e->getMessage();
        }


        $this->assertEquals("Item count cannot be empty", $error);
        $this->ftm = $this->pft->loadDataFromStore();
        $this->assertEquals(0, count($this->ftm->getSupplies()));
    }
    public function testCreateSupplyStringCount() {
        $this->assertEquals(0, count($this->ftm->getSupplies()));

        $name = "Spatula";
        $count = "Hello World!";
        try {
            $this->c->createSupplyItem($name,$count);
        } catch (Exception $e) {
            // check that no error occurred
            $error = $e->getMessage();
        }


        $this->assertEquals("Item count must be an integer", $error);
        $this->ftm = $this->pft->loadDataFromStore();
        $this->assertEquals(0, count($this->ftm->getSupplies()));
    }

    public function testCreateSupplyDoubleCount() {
        $this->assertEquals(0, count($this->ftm->getSupplies()));

        $name = "Spatula";
        $count = 3.14159;
        try {
            $this->c->createSupplyItem($name,$count);
        } catch (Exception $e) {
            // check that no error occurred
            $error = $e->getMessage();
        }


        $this->assertEquals("Item count must be an integer", $error);
        $this->ftm = $this->pft->loadDataFromStore();
        $this->assertEquals(0, count($this->ftm->getSupplies()));
    }

    public function testCreateEquipment() {
        $this->assertEquals(0, count($this->ftm->getEquipment()));

        $name = "Grill";
        $count = 7;
        try {
            $this->c->createEquipmentItem($name,$count);
        } catch (Exception $e) {
            // check that no error occurred
            $this->fail();
        }

        // check file contents
        $this->ftm = $this->pft->loadDataFromStore();
        $this->assertEquals(1, count($this->ftm->getEquipment()));
        $this->assertEquals($name, $this->ftm->getEquipment_index(0)->getName());
        $this->assertEquals($count, $this->ftm->getEquipment_index(0)->getCount());
    }

    public function testCreateEquipmentNull() {
        $this->assertEquals(0, count($this->ftm->getEquipment()));

        $name = null;
        $count = 5;
        try {
            $this->c->createEquipmentItem($name,$count);
        } catch (Exception $e) {

            $error = $e->getMessage();
        }

        $this->assertEquals("Item name cannot be empty", $error);
        $this->ftm = $this->pft->loadDataFromStore();
        $this->assertEquals(0, count($this->ftm->getEquipment()));

    }

    public function testCreateEquipmentNullCount() {
        $this->assertEquals(0, count($this->ftm->getEquipment()));

        $name = "Spatula";
        $count = null;
        try {
            $this->c->createEquipmentItem($name,$count);
        } catch (Exception $e) {
            // check that no error occurred
            $error = $e->getMessage();
        }


        $this->assertEquals("Item count cannot be empty", $error);
        $this->ftm = $this->pft->loadDataFromStore();
        $this->assertEquals(0, count($this->ftm->getEquipment()));
    }
    public function testCreateEquipmentStringCount() {
        $this->assertEquals(0, count($this->ftm->getEquipment()));

        $name = "Spatula";
        $count = "Hello World!";
        try {
            $this->c->createEquipmentItem($name,$count);
        } catch (Exception $e) {
            // check that no error occurred
            $error = $e->getMessage();
        }


        $this->assertEquals("Item count must be an integer", $error);
        $this->ftm = $this->pft->loadDataFromStore();
        $this->assertEquals(0, count($this->ftm->getEquipment()));
    }

    public function testCreateEquipmentDoubleCount() {
        $this->assertEquals(0, count($this->ftm->getEquipment()));

        $name = "Spatula";
        $count = 3.14159;
        try {
            $this->c->createEquipmentItem($name,$count);
        } catch (Exception $e) {
            // check that no error occurred
            $error = $e->getMessage();
        }
        $this->assertEquals("Item count must be an integer", $error);
        $this->ftm = $this->pft->loadDataFromStore();
        $this->assertEquals(0, count($this->ftm->getEquipment()));
    }

    public function testEditEquipmentCount() {
        $this->assertEquals(0, count($this->ftm->getEquipment()));

        $name = "Grill";
        $count = 7;
        $newCount = 10;
        try {
            $this->c->createEquipmentItem($name,$count);
            $this->c->editEquipmentCount($name,$newCount);
        } catch (Exception $e) {
            // check that no error occurred
            $this->fail();
        }

        // check file contents
        $this->ftm = $this->pft->loadDataFromStore();
        $this->assertEquals(1, count($this->ftm->getEquipment()));
        $this->assertEquals($name, $this->ftm->getEquipment_index(0)->getName());
        $this->assertEquals($newCount, $this->ftm->getEquipment_index(0)->getCount());
    }

    public function testEditEquipmentCountNull() {
        $this->assertEquals(0, count($this->ftm->getEquipment()));

        $name = "Grill";
        $count = 7;
        $newCount = null;
        try {
            $this->c->createEquipmentItem($name,$count);
            $this->c->editEquipmentCount($name,$newCount);
        } catch (Exception $e) {
            // check that no error occurred
            $error = $e->getMessage();
        }

        $this->assertEquals("Item count must be an integer", $error);
        $this->ftm = $this->pft->loadDataFromStore();
        $this->assertEquals(1, count($this->ftm->getEquipment()));
        $this->assertEquals($name, $this->ftm->getEquipment_index(0)->getName());
        $this->assertEquals($count, $this->ftm->getEquipment_index(0)->getCount());
    }

    public function testEditEquipmentCountNonInt() {
        $this->assertEquals(0, count($this->ftm->getEquipment()));

        $name = "Grill";
        $count = 7;
        $newCount = 14.5;
        try {
            $this->c->createEquipmentItem($name,$count);
            $this->c->editEquipmentCount($name,$newCount);
        } catch (Exception $e) {
            // check that no error occurred
            $error = $e->getMessage();
        }

        $this->assertEquals("Item count must be an integer", $error);
        $this->ftm = $this->pft->loadDataFromStore();
        $this->assertEquals(1, count($this->ftm->getEquipment()));
        $this->assertEquals($name, $this->ftm->getEquipment_index(0)->getName());
        $this->assertEquals($count, $this->ftm->getEquipment_index(0)->getCount());
    }

    public function testEditEquipmentCountString() {
        $this->assertEquals(0, count($this->ftm->getEquipment()));

        $name = "Grill";
        $count = 7;
        $newCount = "good stuff";
        try {
            $this->c->createEquipmentItem($name,$count);
            $this->c->editEquipmentCount($name,$newCount);
        } catch (Exception $e) {
            // check that no error occurred
            $error = $e->getMessage();
        }

        $this->assertEquals("Item count must be an integer", $error);
        $this->ftm = $this->pft->loadDataFromStore();
        $this->assertEquals(1, count($this->ftm->getEquipment()));
        $this->assertEquals($name, $this->ftm->getEquipment_index(0)->getName());
        $this->assertEquals($count, $this->ftm->getEquipment_index(0)->getCount());
    }

    public function testCreateEmployee() {
        $this->assertEquals(0, count($this->ftm->getEmployees()));

        $name = "Bob";
        $role = "cook";
        $salary = 10.00;
        try {
            $this->c->createEmployee($name,$role,$salary);
        } catch (Exception $e) {
            // check that no error occurred
            $this->fail();
        }

        // check file contents
        $this->ftm = $this->pft->loadDataFromStore();
        $this->assertEquals(1, count($this->ftm->getEmployees()));
        $this->assertEquals($name, $this->ftm->getEmployee_index(0)->getName());
        $this->assertEquals($role, $this->ftm->getEmployee_index(0)->getRole());
        $this->assertEquals($salary, $this->ftm->getEmployee_index(0)->getSalaryPerHour());
    }

    public function testCreateEmployeeNoName() {
        $this->assertEquals(0, count($this->ftm->getEmployees()));

        $name = null;
        $role = "cook";
        $salary = 10.00;
        try {
            $this->c->createEmployee($name,$role,$salary);
        } catch (Exception $e) {
            // check that no error occurred
            $error = $e->getMessage();
        }

        // check file contents
        $this->assertEquals("Employee name cannot be empty", $error);
        $this->ftm = $this->pft->loadDataFromStore();
        $this->assertEquals(0, count($this->ftm->getEmployees()));
    }

    public function testCreateEmployeeNoRole() {
        $this->assertEquals(0, count($this->ftm->getEmployees()));

        $name = "bob";
        $role = null;
        $salary = 10.00;
        try {
            $this->c->createEmployee($name,$role,$salary);
        } catch (Exception $e) {
            // check that no error occurred
            $error = $e->getMessage();
        }

        // check file contents
        $this->assertEquals("Employee role cannot be empty", $error);
        $this->ftm = $this->pft->loadDataFromStore();
        $this->assertEquals(0, count($this->ftm->getEmployees()));
    }

    public function testCreateEmployeeNoSalary() {
        $this->assertEquals(0, count($this->ftm->getEmployees()));

        $name = "bob";
        $role = "cook";
        $salary = null;
        try {
            $this->c->createEmployee($name,$role,$salary);
        } catch (Exception $e) {
            // check that no error occurred
            $error = $e->getMessage();
        }

        // check file contents
        $this->assertEquals("Salary must be a number", $error);
        $this->ftm = $this->pft->loadDataFromStore();
        $this->assertEquals(0, count($this->ftm->getEmployees()));
    }

    public function testCreateEmployeeNegativeSalary() {
        $this->assertEquals(0, count($this->ftm->getEmployees()));

        $name = "bob";
        $role = "cook";
        $salary = -10.5;
        try {
            $this->c->createEmployee($name,$role,$salary);
        } catch (Exception $e) {
            // check that no error occurred
            $error = $e->getMessage();
        }

        // check file contents
        $this->assertEquals("Salary must be positive", $error);
        $this->ftm = $this->pft->loadDataFromStore();
        $this->assertEquals(0, count($this->ftm->getEmployees()));
    }

    public function testCreateEmployeeInvalidSalary() {
        $this->assertEquals(0, count($this->ftm->getEmployees()));

        $name = "bob";
        $role = "cook";
        $salary = "five dollars";
        try {
            $this->c->createEmployee($name,$role,$salary);
        } catch (Exception $e) {
            // check that no error occurred
            $error = $e->getMessage();
        }

        // check file contents
        $this->assertEquals("Salary must be a number", $error);
        $this->ftm = $this->pft->loadDataFromStore();
        $this->assertEquals(0, count($this->ftm->getEmployees()));
    }

    public function testEditEmployeeSalary() {
        $this->assertEquals(0, count($this->ftm->getEmployees()));

        $name = "Bob";
        $role = "cook";
        $salary = 10.00;
        $newSalary = 15.00;
        try {
            $this->c->createEmployee($name,$role,$salary);
            $this->c->editEmployee($role, $newSalary, 0);
        } catch (Exception $e) {
            // check that no error occurred
            $this->fail();
        }

        // check file contents
        $this->ftm = $this->pft->loadDataFromStore();
        $this->assertEquals(1, count($this->ftm->getEmployees()));
        $this->assertEquals($name, $this->ftm->getEmployee_index(0)->getName());
        $this->assertEquals($role, $this->ftm->getEmployee_index(0)->getRole());
        $this->assertEquals($newSalary, $this->ftm->getEmployee_index(0)->getSalaryPerHour());
    }

    public function testEditEmployeeRole() {
        $this->assertEquals(0, count($this->ftm->getEmployees()));

        $name = "Bob";
        $role = "cook";
        $salary = 10.00;
        $newRole = "head chef";
        try {
            $this->c->createEmployee($name,$role,$salary);
            $this->c->editEmployee($newRole, $salary, 0);
        } catch (Exception $e) {
            // check that no error occurred
            $this->fail();
        }

        // check file contents
        $this->ftm = $this->pft->loadDataFromStore();
        $this->assertEquals(1, count($this->ftm->getEmployees()));
        $this->assertEquals($name, $this->ftm->getEmployee_index(0)->getName());
        $this->assertEquals($newRole, $this->ftm->getEmployee_index(0)->getRole());
        $this->assertEquals($salary, $this->ftm->getEmployee_index(0)->getSalaryPerHour());
    }

    public function testEditEmployeeRoleAndSalary() {
        $this->assertEquals(0, count($this->ftm->getEmployees()));

        $name = "Bob";
        $role = "cook";
        $salary = 10.00;
        $newSalary = 15;
        $newRole = "head chef";
        try {
            $this->c->createEmployee($name,$role,$salary);
            $this->c->editEmployee($newRole, $newSalary, 0);
        } catch (Exception $e) {
            $this->fail();
        }

        // check file contents
        $this->ftm = $this->pft->loadDataFromStore();
        $this->assertEquals(1, count($this->ftm->getEmployees()));
        $this->assertEquals($name, $this->ftm->getEmployee_index(0)->getName());
        $this->assertEquals($newRole, $this->ftm->getEmployee_index(0)->getRole());
        $this->assertEquals($newSalary, $this->ftm->getEmployee_index(0)->getSalaryPerHour());
    }

    public function testEditEmployeeInvalidIndex() {
        $this->assertEquals(0, count($this->ftm->getEmployees()));

        $name = "Bob";
        $role = "cook";
        $salary = 10.00;
        $newSalary = 15;
        $newRole = "head chef";
        try {
            $this->c->createEmployee($name,$role,$salary);
            $this->c->editEmployee($newRole, $newSalary, -1);
        } catch (Exception $e) {
            $error = $e->getMessage();
        }

        $this->assertEquals("No Employee Selected", $error);
        $this->ftm = $this->pft->loadDataFromStore();
        $this->assertEquals(1, count($this->ftm->getEmployees()));
        $this->assertEquals($name, $this->ftm->getEmployee_index(0)->getName());
        $this->assertEquals($role, $this->ftm->getEmployee_index(0)->getRole());
        $this->assertEquals($salary, $this->ftm->getEmployee_index(0)->getSalaryPerHour());
    }

    public function testEditEmployeeInvalidRole() {
        $this->assertEquals(0, count($this->ftm->getEmployees()));

        $name = "Bob";
        $role = "cook";
        $salary = 10.00;
        $newSalary = 15;
        $newRole = null;
        try {
            $this->c->createEmployee($name,$role,$salary);
            $this->c->editEmployee($newRole, $newSalary, 0);
        } catch (Exception $e) {
            $error = $e->getMessage();
        }

        $this->assertEquals("Role cannot be empty", $error);
        $this->ftm = $this->pft->loadDataFromStore();
        $this->assertEquals(1, count($this->ftm->getEmployees()));
        $this->assertEquals($name, $this->ftm->getEmployee_index(0)->getName());
        $this->assertEquals($role, $this->ftm->getEmployee_index(0)->getRole());
        $this->assertEquals($salary, $this->ftm->getEmployee_index(0)->getSalaryPerHour());
    }

    public function testEditEmployeeInvalidSalary() {
        $this->assertEquals(0, count($this->ftm->getEmployees()));

        $name = "Bob";
        $role = "cook";
        $salary = 10.00;
        $newSalary = null;
        $newRole = "boss";
        try {
            $this->c->createEmployee($name,$role,$salary);
            $this->c->editEmployee($newRole, $newSalary, 0);
        } catch (Exception $e) {
            $error = $e->getMessage();
        }

        $this->assertEquals("Salary must be a number", $error);
        $this->ftm = $this->pft->loadDataFromStore();
        $this->assertEquals(1, count($this->ftm->getEmployees()));
        $this->assertEquals($name, $this->ftm->getEmployee_index(0)->getName());
        $this->assertEquals($role, $this->ftm->getEmployee_index(0)->getRole());
        $this->assertEquals($salary, $this->ftm->getEmployee_index(0)->getSalaryPerHour());
    }

    public function testEditEmployeeNegativeSalary() {
        $this->assertEquals(0, count($this->ftm->getEmployees()));

        $name = "Bob";
        $role = "cook";
        $salary = 10.00;
        $newSalary = -5.00;
        $newRole = "boss";
        try {
            $this->c->createEmployee($name,$role,$salary);
            $this->c->editEmployee($newRole, $newSalary, 0);
        } catch (Exception $e) {
            $error = $e->getMessage();
        }
        $this->assertEquals("Salary must be positive", $error);
        $this->ftm = $this->pft->loadDataFromStore();
        $this->assertEquals(1, count($this->ftm->getEmployees()));
        $this->assertEquals($name, $this->ftm->getEmployee_index(0)->getName());
        $this->assertEquals($role, $this->ftm->getEmployee_index(0)->getRole());
        $this->assertEquals($salary, $this->ftm->getEmployee_index(0)->getSalaryPerHour());
    }

    public function testFireEmployee() {
        $this->assertEquals(0, count($this->ftm->getEmployees()));

        $name = "Bob";
        $role = "cook";
        $salary = 10.00;

        try {
            $this->c->createEmployee($name,$role,$salary);
            $this->c->removeEmployee($name);
        } catch (Exception $e) {
            $this->fail();
        }
        $this->ftm = $this->pft->loadDataFromStore();
        $this->assertEquals(0, count($this->ftm->getEmployees()));
    }

    public function testFireEmployeeNotSelected() {
        $this->assertEquals(0, count($this->ftm->getEmployees()));

        $name = "Bob";
        $role = "cook";
        $salary = 10.00;

        try {
            $this->c->createEmployee($name,$role,$salary);
            $this->c->removeEmployee(null);
        } catch (Exception $e) {
            $error = $e->getMessage();
        }
        $this->assertEquals("No employee selected", $error);
        $this->ftm = $this->pft->loadDataFromStore();
        $this->assertEquals(1, count($this->ftm->getEmployees()));
    }

    public function testAddShift() {
        date_default_timezone_set('America/New_York');
        $this->assertEquals(0, count($this->ftm->getEmployees()));

        //because the user selects the day from a list the input cannot be invalid unless nothing is selected
        $name = "Bob";
        $role = "cook";
        $salary = 10.00;
        $dayOfWeek = "Friday";
        $startTime = "09:00";
        $endTime = "17:00";

        try {

            $this->c->createEmployee($name,$role,$salary);
            $this->c->assignShift($startTime, $endTime, $dayOfWeek, 0, 5);
        } catch (Exception $e) {
            echo $e;
            $this->fail();
        }
        $this->ftm = $this->pft->loadDataFromStore();
        $this->assertEquals(1, count($this->ftm->getEmployees()));
        $this->assertEquals($name, $this->ftm->getEmployee_index(0)->getName());
        $this->assertEquals($role, $this->ftm->getEmployee_index(0)->getRole());
        $this->assertEquals($salary, $this->ftm->getEmployee_index(0)->getSalaryPerHour());
        $this->assertEquals(true, $this->ftm->getEmployee_index(0)->hasShifts());
    }

    public function testAddShiftInvalidTime() {
        date_default_timezone_set('America/New_York');
        $this->assertEquals(0, count($this->ftm->getEmployees()));

        //because the user selects the day from a list the input cannot be invalid unless nothing is selected
        $name = "Bob";
        $role = "cook";
        $salary = 10.00;
        $dayOfWeek = "Friday";
        $startTime = "hello world";
        $endTime = null;

        try {

            $this->c->createEmployee($name,$role,$salary);
            $this->c->assignShift($startTime, $endTime, $dayOfWeek, 0, 5);
        } catch (Exception $e) {
            $error = $e->getMessage();
        }
        $this->assertEquals("Time format invalid.", $error);
        $this->ftm = $this->pft->loadDataFromStore();
        $this->assertEquals(1, count($this->ftm->getEmployees()));
        $this->assertEquals($name, $this->ftm->getEmployee_index(0)->getName());
        $this->assertEquals($role, $this->ftm->getEmployee_index(0)->getRole());
        $this->assertEquals($salary, $this->ftm->getEmployee_index(0)->getSalaryPerHour());
        $this->assertEquals(false, $this->ftm->getEmployee_index(0)->hasShifts());
    }

    public function testAddShiftNoDaySelected() {
        date_default_timezone_set('America/New_York');
        $this->assertEquals(0, count($this->ftm->getEmployees()));

        //because the user selects the day from a list the input cannot be invalid unless nothing is selected
        $name = "Bob";
        $role = "cook";
        $salary = 10.00;
        $dayOfWeek = null;
        $startTime = "09:00";
        $endTime = "17:00";

        try {

            $this->c->createEmployee($name,$role,$salary);
            $this->c->assignShift($startTime, $endTime, $dayOfWeek, 0, null);
        } catch (Exception $e) {
            $error = $e->getMessage();
        }
        $this->assertEquals("No day selected", $error);
        $this->ftm = $this->pft->loadDataFromStore();
        $this->assertEquals(1, count($this->ftm->getEmployees()));
        $this->assertEquals($name, $this->ftm->getEmployee_index(0)->getName());
        $this->assertEquals($role, $this->ftm->getEmployee_index(0)->getRole());
        $this->assertEquals($salary, $this->ftm->getEmployee_index(0)->getSalaryPerHour());
        $this->assertEquals(false, $this->ftm->getEmployee_index(0)->hasShifts());
    }

    public function testAddShiftStartTimeLater() {
        date_default_timezone_set('America/New_York');
        $this->assertEquals(0, count($this->ftm->getEmployees()));

        //because the user selects the day from a list the input cannot be invalid unless nothing is selected
        $name = "Bob";
        $role = "cook";
        $salary = 10.00;
        $dayOfWeek = "Friday";
        $startTime = "21:00";
        $endTime = "17:00";

        try {

            $this->c->createEmployee($name,$role,$salary);
            $this->c->assignShift($startTime, $endTime, $dayOfWeek, 0, 5);
        } catch (Exception $e) {
           $error = $e->getMessage();
        }
        $this->assertEquals("End time must be later than start time", $error);
        $this->ftm = $this->pft->loadDataFromStore();
        $this->assertEquals(1, count($this->ftm->getEmployees()));
        $this->assertEquals($name, $this->ftm->getEmployee_index(0)->getName());
        $this->assertEquals($role, $this->ftm->getEmployee_index(0)->getRole());
        $this->assertEquals($salary, $this->ftm->getEmployee_index(0)->getSalaryPerHour());
        $this->assertEquals(false, $this->ftm->getEmployee_index(0)->hasShifts());
    }

    public function testTransaction() {
        $this->assertEquals(0, count($this->ftm->getMenuItems()));

        $name = "Burger";
        $price= 10.0;
        $amountSold = 20;
        try {
            $this->c->createMenuItem($name,$price);
            $this->c->updateMenuItemSold($name, $amountSold);
        } catch (Exception $e) {
            // check that no error occurred
            $this->fail();
        }

        // check file contents
        $this->ftm = $this->pft->loadDataFromStore();
        $this->assertEquals(1, count($this->ftm->getMenuItems()));
        $this->assertEquals($name, $this->ftm->getMenuItem_index(0)->getName());
        $this->assertEquals($price, $this->ftm->getMenuItem_index(0)->getPrice());
        $this->assertEquals($amountSold, $this->ftm->getMenuItem_index(0)->getAmountSold());
    }

    public function testTransactionInvalidCount() {
        $this->assertEquals(0, count($this->ftm->getMenuItems()));

        $name = "Burger";
        $price= 10.0;
        $amountSold = null;
        try {
            $this->c->createMenuItem($name,$price);
            $this->c->updateMenuItemSold($name, $amountSold);
        } catch (Exception $e) {
            // check that no error occurred
           $error = $e->getMessage();
        }

        $this->assertEquals("the amount sold must be an integer", $error);
        $this->ftm = $this->pft->loadDataFromStore();
        $this->assertEquals(1, count($this->ftm->getMenuItems()));
        $this->assertEquals($name, $this->ftm->getMenuItem_index(0)->getName());
        $this->assertEquals($price, $this->ftm->getMenuItem_index(0)->getPrice());
        $this->assertEquals(0, $this->ftm->getMenuItem_index(0)->getAmountSold());
    }

    public function testTransactionNoItemSelected() {
        $this->assertEquals(0, count($this->ftm->getMenuItems()));

        $name = "Burger";
        $price= 10.0;
        $amountSold = 15;
        try {
            $this->c->createMenuItem($name,$price);
            $this->c->updateMenuItemSold(null, $amountSold);
        } catch (Exception $e) {
            $error = $e->getMessage();
        }

        $this->assertEquals("No item selected", $error);
        $this->ftm = $this->pft->loadDataFromStore();
        $this->assertEquals(1, count($this->ftm->getMenuItems()));
        $this->assertEquals($name, $this->ftm->getMenuItem_index(0)->getName());
        $this->assertEquals($price, $this->ftm->getMenuItem_index(0)->getPrice());
        $this->assertEquals(0, $this->ftm->getMenuItem_index(0)->getAmountSold());
    }


}
?>