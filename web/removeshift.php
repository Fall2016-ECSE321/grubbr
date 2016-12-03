<?php
require_once 'controller/Controller.php';
session_start();
$c = new Controller();



$shiftIndex = $_POST['selectedShiftIndex'];
$employeeIndex = $_POST['selectedEmployeeIndex'];
$removeShiftEmployeeName = $_POST['selectedEmployeeName'];

try {

    $c->removeShift($employeeIndex, $shiftIndex);

    //We will only display one error at a time if multiple errors exist
    $_SESSION["employeeError"] = "";
} catch (Exception $e) {
    $_SESSION["employeeError"] = $e->getMessage();
}

?>
<!DOCTYPE html>
<html>
<head>
    <!--url=/FoodTruck/-->
    <meta http-equiv="refresh" content="0; url=editemployee.php" />
</head>
</html>