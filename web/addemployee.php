<?php
require_once 'controller/Controller.php';
session_start();
$c = new Controller();
try {
	$c->createEmployee($_POST['newEmployee_Name'],$_POST['newEmployee_Role'],$_POST['newEmployee_Salary']);

	//We will only display one error at a time if multiple errors exist
	$_SESSION["errorItem"] = "";
} catch (Exception $e) {
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