<?php
require_once 'controller/Controller.php';
session_start();
$c = new Controller();
try {
    $c->editEmployee($_POST['editEmployee_Role'],$_POST['editEmployee_Salary'],$_POST['editEmployee_Index']);

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