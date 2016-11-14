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
    	$this->assertEquals("Menu item price has to be a number!", $error);
    	// check file contents
    	$this->ftm = $this->pft->loadDataFromStore();
    	$this->assertEquals(0, count($this->ftm->getMenuItems()));
    }
}
?>