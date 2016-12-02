<?php
require_once 'controller/Controller.php';
session_start();

$name = $_POST['selectedEquipment'];
$count = $_POST['editEquipmentCount'];

$c = new Controller();
try{
	if($count < 0){
		$c->removeEquipment($name);
	} else {
		$c->editEquipmentCount($name, $count);
	}
	$_SESSION["errorItem"] = "";
} catch(Exception $e){
	$_SESSION["errorItem"] = $e->getMessage();
}




?>
<!DOCTYPE html>
<html>
	<head>
	<!--url=/FoodTruck/-->
		<meta http-equiv="refresh" content="0; url=index.php" />
	</head>
</html>