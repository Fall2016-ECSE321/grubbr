<?php
require_once 'controller/Controller.php';
session_start();
$c = new Controller();
$c->editMenuItemPrice($_POST['selectedMenuItem'], $_POST['editMenuPrice']);
?>
<!DOCTYPE html>
<html>
	<head>
	<!--url=/FoodTruck/-->
		<meta http-equiv="refresh" content="0; url=index.php" />
	</head>
</html>