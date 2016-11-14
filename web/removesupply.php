<?php
// require_once 'persistence/PersistenceFoodTruck.php';
// require_once 'model/FoodTruckManager.php';
require_once 'controller/Controller.php';

session_start();

$c = new Controller();
$c->removeSupply($_POST['selectedSupply']);

?>
<!DOCTYPE html>
<html>
	<head>
	<!--url=/FoodTruck/-->
		<meta http-equiv="refresh" content="0; url=index.php" />
	</head>
</html>