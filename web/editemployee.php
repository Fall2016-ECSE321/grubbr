<?php
/**
 * Created by PhpStorm.
 * User: patricklai
 * Date: 2016-11-21
 * Time: 9:52 PM
 */


require_once 'controller/Controller.php';
require_once 'model/Employee.php';
require_once 'model/FoodTruckManager.php';

session_start();

$c = new Controller();
$selectedEmployeeName = $_POST['selectedEmployee'];

$pft = new PersistenceFoodTruck();
$ftm = $pft->loadDataFromStore();
$selectedEmployeeIndex = $ftm->indexOfEmployeeByName($selectedEmployeeName);
//echo $selectedEmployeeIndex;
$selectedEmployee = $ftm->getEmployee_index($selectedEmployeeIndex);

?>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Import Bootstrap -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="stylesheets/stylesheet.css"/>



    </head>
    <body>
        <div class="container-fluid">
            <div class="jumbotron text-center">
                <h1>grubbr.</h1>
            </div>
        </div>

        <h1>Employee Edit Page</h1>
        <a href="index.php">back</a>
        <br>

        <div>
            <?php
            echo "<br>";
            echo "Name: " . $selectedEmployee->getName();
            echo "<br>";
            echo "Role: " . $selectedEmployee->getRole();
            echo "<br>";
            echo "Salary: $" . $selectedEmployee->getSalaryPerHour()." / hour";
            echo "<br>";
            ?>
        </div>

        <form action="saveEmployeeEdits.php" method="post">
            <fieldset>
                <br>
                <p>Employee Role: <input type="text" name="editEmployee_Role" placeholder="Enter New Role" value="<?php echo $selectedEmployee->getRole() ?>"/> </p>
                <p>Employee Salary: <input type="text" name="editEmployee_Salary" placeholder="Enter New Salary" value="<?php echo $selectedEmployee->getSalaryPerHour() ?>"/> </p>
                <p> <input type="hidden" name="editEmployee_Index" value="<?php echo $selectedEmployeeIndex ?>"> </p>
                <p><input type="submit" value="Save edits"/></p>
            </fieldset>

        </form>
        <br>
        <form method="post"/>
            <fieldset>
                <p>Add Employee Shift:
                    <input type="time" name="shift_time" placeholder="Enter Shift Time"/>
                    <input type="date" name="shift_date" placeholder="Enter Shift Date"/>
                </p>

                <p><input type="submit" value="Add shift"/></p>
            </fieldset>
        </form>
        <br>

        Fire Employee
        <form action="removeemployee.php" method="post"/>
            <input type = "hidden" name="fire_name" value="<?php echo $selectedEmployee->getName()?>"/>
            <input type="submit" value="Fire Employee"/>
        </form>


    </form>
    </body>
</html>