<?php
/**
 * Created by PhpStorm.
 * User: patricklai
 * Date: 2016-11-21
 * Time: 3:36 PM
 */
// require_once 'persistence/PersistenceFoodTruck.php';
// require_once 'model/FoodTruckManager.php';
require_once 'controller/Controller.php';

session_start();

$c = new Controller();
$c->removeEmployee($_POST['fire_name']);



?>
<!DOCTYPE html>
<html>
    <head>
        <!--url=/FoodTruck/-->
        <meta http-equiv="refresh" content="0; url=index.php" />
    </head>
</html>