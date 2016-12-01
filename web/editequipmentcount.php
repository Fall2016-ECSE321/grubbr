<?php
require_once 'controller/Controller.php';
session_start();

$name = $_POST['selectedEquipment'];
$count = $_POST['editEquipmentCount'];

$c = new Controller();
if($count < 0){
	$c->removeEquipment($name);
} else {
	$c->editEquipmentCount($name, $count);
}



?>
<!DOCTYPE html>
<html>
	<head>
	<!--url=/FoodTruck/-->
		<meta http-equiv="refresh" content="0; url=index.php" />
	</head>
</html>