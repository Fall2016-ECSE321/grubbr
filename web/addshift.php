<?php
require_once 'controller/Controller.php';
session_start();
$c = new Controller();
//echo $_POST['shift_start'];
//echo $_POST['shift_end'];
//echo $_POST['selectedDay'];
//$_POST['shiftEmployee_Index'];


$daysOfWeek = array("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");
try {
    $index = $_POST['selectedDay'];
    $c->assignShift($_POST['shift_start'], $_POST['shift_end'], $daysOfWeek[$index] ,$_POST['shiftEmployee_Index'], $index);

    //We will only display one error at a time if multiple errors exist
    $_SESSION["employeeError"] = "";
} catch (Exception $e) {
    $_SESSION["employeeError"] = $e->getMessage();
}
//try {
//    $c->editEmployee($_POST['editEmployee_Role'],$_POST['editEmployee_Salary'],$_POST['editEmployee_Index']);
//
//    //We will only display one error at a time if multiple errors exist
//    $_SESSION["errorItem"] = "";
//} catch (Exception $e) {
//    $_SESSION["errorItem"] = $e->getMessage();
//}
?>
<!DOCTYPE html>
<html>
<head>
    <!--url=/FoodTruck/-->
    <meta http-equiv="refresh" content="0; url=editemployee.php" />
</head>
</html>