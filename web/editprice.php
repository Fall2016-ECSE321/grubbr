<?php
require_once 'controller/Controller.php';
session_start();
$c = new Controller();

$name = $_POST['selectedMenuItem'];
$price = $_POST['editMenuPrice'];

$c = new Controller();
if($price < 0){
	$c->removeMenuItem($name);
} else {
	$c->editMenuItemPrice($name, $price);
}


?>
<!DOCTYPE html>
<html>
	<head>
	<!--url=/FoodTruck/-->
		<meta http-equiv="refresh" content="0; url=index.php" />
	</head>
</html>