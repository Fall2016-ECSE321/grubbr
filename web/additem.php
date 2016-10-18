<?php
	require_once 'controller/Controller.php';
	session_start();
	$c = new Controller();
	try {
		$c->createMenuItem($_POST['newitem_name'],$_POST['newitem_price']);
		
		//We will only display one error at a time if multiple errors exist
		$_SESSION["errorItem"] = "";
	} catch (Exception $e) {
		$_SESSION["errorItem"] = $e->getMessage();
	}
?>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="refresh" content="0; url=/FoodTruck/" />
	</head>
</html>