<!DOCTYPE html>
<html>
	<head> 
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<!-- Import Bootstrap -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
		<link rel="stylesheet" href="stylesheets/stylesheet.css"/>
		<title>grubbr.</title>
	</head>
	<body>
		<?php 
			require_once "model/MenuItem.php";
			require_once "model/Equipment.php";
			require_once "model/Supply.php";
			require_once "model/Employee.php";
			require_once "model/FoodTruckManager.php";
			require_once "persistence/PersistenceFoodTruck.php";
			session_start();
		?>
		<div class="container-fluid">
			<div class="jumbotron text-center">
				<h1>grubbr.</h1>
			</div>
		</div>
		<div class="container-fluid text-center">
			<div class="row">
				<div class="col-xs-12 col-sm-3">
					<h2> Add Item </h2>
					<form action = "additem.php" method="post">
						<fieldset>
							<br>
							<p>Item Name: <input type="text" name="newitem_name" placeholder="Enter Name"/>
							<p>Item Price: <input type="text" name="newitem_price" placeholder="Enter Price"/>
							<span class="error">
							<?php
							if (isset($_SESSION['errorItem']) && !empty($_SESSION['errorItem'])){
								echo " * " . $_SESSION["errorItem"];
							}
							?>
							</span></p>
							<p><input type="submit" value="Add Item"/></p>
							<br>
						</fieldset>
					</form>
				</div>
			<div class="col-xs-12 col-sm-3">
				<h2> Add Equipment</h2>
				<form action = "addinventory.php" method="post">
					<fieldset>
						<br>
						<p>Equipment Name: <input type="text" name="newequipment_name" placeHolder="Enter Name"/>
						<p>Amount: <input type="text" name="newequipment_amount" placeholder="Enter Count"/>
						<span class="error">
						</span></p>	
						<p><input type="submit" value="Add To Inventory"/></p>
					<br>
					</fieldset>
				</form>
			</div>
			
			<div class="col-xs-12 col-sm-3">
				<h2> Add Supply</h2>
				<form action = "addsupply.php" method="post">
					<fieldset>
						<br>
						<p>Supply Name: <input type="text" name="newsupply_name" placeHolder="Enter Name"/>
						<p>Amount: <input type="text" name="newesupply_amount" placeholder="Enter Count"/>
						<p><input type="submit" value="Add To Inventory"/></p>
					<br>
					</fieldset>
				</form>
			</div>
			<div class="col-xs-12 col-sm-3">
				<h2> Add Employee</h2>
				<form action='addemployee.php' method="post">
					<fieldset>
						<br>
						<p>Name: <input type="text" name="newEmployee_Name" placeHolder="Enter Name"/>
						<p>Role: <input type="text" name="newEmployee_Role" placeholder="Enter Role"/>
						<p>Salary: <input type="text" name="newEmployee_Salary" placeholder="Enter Salary"/>
						
						<p><input type="submit" value="Add Employee"/></p>
					<br>
					</fieldset>
				</form>
			</div>
		
		<br>
		<span>
		<p>
			<?php 	
			if (file_exists('data.txt'))
			{
				$str = file_get_contents('data.txt');
				$ftm = unserialize($str);
			} else {
				$ftm = FoodTruckManager::getInstance();
			}
// 			echo "number of menu items: ";
// 			echo sizeof($ftm->getMenuItems());
// 			echo "<b>Menu:</b>";
// 			for($i=0;$i<sizeof($ftm->getMenuItems());$i++){
// 				echo " </br>";
// 				echo $ftm->getMenuItem_index($i)->getName()." : ";
// 				echo $ftm->getMenuItem_index($i)->getPrice()."$ ";
// 			}
			//echo sizeof($ftm->getEquipment());
			
			//MENU LISTBOX
			$y = null;
//			echo "<form action='removeitem.php' method ='post'>";
//				echo "<p>Menu Items<br>";
//				echo "<select name='selectedMenuItem' size="; echo sizeof($ftm->getMenuItems()); ">";
//				for($i=0;$i<sizeof($ftm->getMenuItems());$i++){
//					echo " </br>";
//					$x = $ftm->getMenuItem_index($i)->getName();
//					echo "<option value = '$x'>";
//							echo $ftm->getMenuItem_index($i)->getName()." : ";
//							echo $ftm->getMenuItem_index($i)->getPrice()."$ ";
//							"</option>";
//
//				}
//				echo "</select>";
//				//REMOVE MENU ITEM
//				echo "<br>";
//			echo "<input type='submit' value='Remove selected Item'/>";
//			echo "</p>";
//			echo "</form>";
// 			if(isset($_POST['selectedMenuItem'])){
// 				$selected = $_POST['selectedMenuItem'];
// 			}

			echo "<h4>Edit Items: Enter a value less than 0 to remove</h4>";

			echo "<div class='row'>";
				echo "<div class='col-xs-12 col-sm-3''>";
			echo "<p>Menu Items<br>";
			echo "<form action='editprice.php' method ='post'>";
			echo "<select name='selectedMenuItem' size="; echo sizeof($ftm->getMenuItems()); ">";
			for($i=0;$i<sizeof($ftm->getMenuItems());$i++){
				echo " </br>";
				$x = $ftm->getMenuItem_index($i)->getName();
				echo "<option value = '$x'>";
				echo $ftm->getMenuItem_index($i)->getName()." : ";
				echo $ftm->getMenuItem_index($i)->getPrice()."$ ";
				"</option>";
					
			}
			echo "</select>";
			//REMOVE MENU ITEM
			echo "<br>";
					echo "<p>Edit Selected Price: <input type='text' name='editMenuPrice' placeholder='New Price'/>";

				echo "<input type='submit' value='OK'/>";
			echo "</form>";
			
			echo "</div>";




			echo "<div class='col-xs-12 col-sm-3''>";
			echo "<p>Equipment Items<br>";
			echo "<form action='editequipmentcount.php' method ='post'>";
			echo "<select name='selectedEquipment' size="; echo sizeof($ftm->getEquipment()); ">";
			for($i=0;$i<sizeof($ftm->getEquipment());$i++){
					
				echo " </br>";
				$x=$ftm->getEquipment_index($i)->getName();
				echo "<option value='$x' selected>";
				echo $ftm->getEquipment_index($i)->getName()." : ";
				echo $ftm->getEquipment_index($i)->getCount();
				"</option>";
			}
			echo "</select>";
			//REMOVE MENU ITEM
			echo "<br>";
			echo "<p>Edit Selected Amount: <input type='text' name='editEquipmentCount' placeholder='New Amount'/>";
				
			echo "<input type='submit' value='OK'/>";
			echo "</form>";

			echo "</div>";



			echo "<div class='col-xs-12 col-sm-3''>";
			echo "<p>Supply Items<br>";
			echo "<form action='editsupplycount.php' method ='post'>";
			echo "<select name='selectedSupply' size="; echo sizeof($ftm->getSupplies()); ">";
			for($i=0;$i<sizeof($ftm->getSupplies());$i++){
					
				echo " </br>";
				$x=$ftm->getSupply_index($i)->getName();
				echo "<option value='$x' selected>";
				echo $ftm->getSupply_index($i)->getName()." : ";
				echo $ftm->getSupply_index($i)->getCount();
				"</option>";
			}
			echo "</select>";
			//REMOVE MENU ITEM
			echo "<br>";
			echo "<p>Edit Selected Amount: <input type='text' name='editSupplyCount' placeholder='New Amount'/>";
			
			echo "<input type='submit' value='OK'/>";
			echo "</form>";
			echo "</div>";



			echo "<div class='col-xs-12 col-sm-3''>";


			echo "<p>Employees<br>";
			echo "<form action='editemployee.php' method='post'>";
			echo "<select name='selectedEmployee' size="; echo sizeof($ftm->getEmployees()); ">";
			for($i=0;$i<sizeof($ftm->getEmployees());$i++){
					
				echo " </br>";

				//$x=$ftm->getEmployee_index($i)->getName();
				$x=$ftm->getEmployee_index($i)->getName();

				echo "<option value='$x' selected>";
				//echo $i;
					//echo $ftm->getSupply_index($i)->getName()." : ";
					echo $ftm->getEmployee_index($i)->getName().": ";
					echo $ftm->getEmployee_index($i)->getRole()." -- ";
					echo $ftm->getEmployee_index($i)->getSalaryPerHour()."  $/hour";


				"</option>";
			}
			echo "</select>";
			//REMOVE MENU ITEM
			echo "<br>";
			//echo "<p>Edit Selected Amount: <input type='time' name='shift_date' placeholder='New Amount'/>";
				
			echo "<input type='submit' value='Edit Selected Employee'/>";
			echo "</form>";
			echo "</div>";
			echo "</div>";
			?>
			
			</p>
		</span>
		</div>
		</div>

		<!--Scripts-->
		<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

	</body>
</html>

