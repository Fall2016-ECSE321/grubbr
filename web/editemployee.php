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
if(isset($_POST['selectedEmployee'])){
    $selectedEmployeeName = $_POST['selectedEmployee'];
    $_SESSION['employeeName'] = $selectedEmployeeName;
} else {
    $selectedEmployeeName = $_SESSION['employeeName'];
}


$pft = new PersistenceFoodTruck();
$ftm = $pft->loadDataFromStore();
$selectedEmployeeIndex = $ftm->indexOfEmployeeByName($selectedEmployeeName);
//echo $selectedEmployeeIndex;
$selectedEmployee = $ftm->getEmployee_index($selectedEmployeeIndex);

$daysOfWeek = array("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");

?>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Import Bootstrap -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="stylesheets/stylesheet.css"/>
        <link rel="stylesheet" href="stylesheets/sticky-footer-navbar.css"/>
        <title>edit employee</title>


    </head>
    <body style="padding:10;">
        <div class="container-fluid">
            <div class="jumbotron text-center">
                <h1>grubbr.</h1>
                <h4>simple food truck management</h4>
            </div>
        </div>
        <div class="container-fluid">
            <span class="error">
                <?php
                if (isset($_SESSION['employeeError']) && !empty($_SESSION['employeeError'])){
                    echo " * " . $_SESSION["employeeError"];
                }
                ?>
            </span>
        <h1>Employee Edit Page</h1>
        <a href="index.php"><h4>back</h4></a>
        <div>
            <form action="removeshift.php" method="post">
                <?php
                echo "<br>";
                echo "<b>Name: </b>" . $selectedEmployee->getName();
                echo "<br>";
                echo "<b>Role: </b>" . $selectedEmployee->getRole();
                echo "<br>";
                echo "<b>Salary: </b>$" . $selectedEmployee->getSalaryPerHour()." / hour";
                echo "<br>";
                echo "<h3>Shifts:</h3>";
                echo "<select name='selectedShiftIndex' size=7>";
                for($i=0;$i<sizeof($daysOfWeek);$i++){
                    $shiftString = "no shift assigned";
                    if(isset($selectedEmployee->getShifts()[$i])){
                        $shiftString =  $selectedEmployee->getShift_index($i)->getStartTime() . " - " . $selectedEmployee->getShift_index($i)->getEndTime();
                    }
                    echo "<option value='$i' selected>";
                        echo $daysOfWeek[$i] . ": " . $shiftString;
                    echo "</option>";
                }
                echo "</select>";
                ?>
                <input name="selectedEmployeeIndex" type="hidden" value="<?php echo $selectedEmployeeIndex ?>"/>
                <input name="selectedEmployeeName" type="hidden" value="<?php echo $selectedEmployeeName ?>"/>
                <input type='submit' value='Remove Shift'/>
            </form>
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
        <h3>Add Employee Shift:</h3>
        <form action="addshift.php" method="post"/>
        <?php
        echo "<select name='selectedDay' size="; echo 1; ">";
        for($i=0;$i<sizeof($daysOfWeek);$i++){

            echo " </br>";

            echo "<option value='$i' selected>";
            echo $daysOfWeek[$i];
            "</option>";
        }
        echo "</select>";
        ?>
            <fieldset>
                <p>
                    <P>Start time: <input type="time" name="shift_start" placeholder="Enter Shift Start"/></p>
                    <p>End time: <input type="time" name="shift_end" placeholder="Enter Shift End"/></p>
                    <input type="hidden" name="shiftEmployee_Index" value="<?php echo $selectedEmployeeIndex ?>">

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
    </div>
        <br>
        <footer class="footer">
            <div class="container">
                <p class="text-muted">Copyright &copy; 2016 Grubbr. All rights reserved (not really)</p>
            </div>
        </footer>

        <!--Scripts-->
        <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </body>
</html>