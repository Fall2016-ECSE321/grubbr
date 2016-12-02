<?php
require_once 'controller/Controller.php';
session_start();
$c = new Controller();



$c = new Controller();
try{
	if($_POST['editMenuPrice'] < 0){
		$c->removeMenuItem($_POST['selectedMenuItem']);
	} else {
		$c->editMenuItemPrice($_POST['selectedMenuItem'], $_POST['editMenuPrice']);
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