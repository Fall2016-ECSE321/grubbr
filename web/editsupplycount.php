<?php
require_once 'controller/Controller.php';
session_start();

$c = new Controller();
try{
	$count = $_POST['editSupplyCount'];
	$name = $_POST['selectedSupply'];
	if($count < 0){
		$c->removeSupply($name);
	} else {
		$c->editSupplyCount($name, $count);
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