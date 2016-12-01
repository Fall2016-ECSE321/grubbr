<?php
require_once 'controller/Controller.php';
session_start();
$count = $_POST['editSupplyCount'];
$name = $_POST['selectedSupply'];
$c = new Controller();
if($count < 0){
	$c->removeSupply($name);
} else {
	$c->editSupplyCount($name, $count);
}

?>
<!DOCTYPE html>
<html>
	<head>
	<!--url=/FoodTruck/-->
		<meta http-equiv="refresh" content="0; url=index.php" />
	</head>
</html>