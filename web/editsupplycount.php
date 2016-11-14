<?php
require_once 'controller/Controller.php';
session_start();
$c = new Controller();
$c->editSupplyCount($_POST['selectedSupply'], $_POST['editSupplyCount']);
?>
<!DOCTYPE html>
<html>
	<head>
	<!--url=/FoodTruck/-->
		<meta http-equiv="refresh" content="0; url=index.php" />
	</head>
</html>