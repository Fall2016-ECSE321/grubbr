<!DOCTYPE html>
<html>
	<head> 
		<meta charset="UTF-8">
		<title> Food Truck Manager </title>
		<style>
			.error {color:#FF0000;}
		</style>
	</head>
	<body>
		<?php 
			require_once "model/MenuItem.php";
			require_once "model/Equipment.php";
			require_once "model/Supply.php";
			require_once "model/FoodTruckManager.php";
			require_once "persistence/PersistenceFoodTruck.php";
			session_start();
		?>
		<h1> Add Item </h1>
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
			echo "<p>Menu Items<br>";
			echo "<select name='Menu' size="; echo sizeof($ftm->getMenuItems()); ">";
			for($i=0;$i<sizeof($ftm->getMenuItems());$i++){
				echo " </br>";
				echo "<option value='Option 1' selected>";
						echo $ftm->getMenuItem_index($i)->getName()." : ";
						echo $ftm->getMenuItem_index($i)->getPrice()."$ ";
						"</option>";
						
			}
			echo "</select>";
			echo "</p>";
			
			//EQUIPMENT LISTBOX
			echo "<p>Equipment Items<br>";
			echo "<select name='Equipment' size="; echo sizeof($ftm->getEquipment()); ">";
			for($i=0;$i<sizeof($ftm->getEquipment());$i++){
					
				echo " </br>";
				echo "<option value='Option 1' selected>";
				echo $ftm->getEquipment_index($i)->getName()." : ";
				echo $ftm->getEquipment_index($i)->getCount();
 				"</option>";
			}
			echo "</select>";
			echo "</p>";
			
			//EQUIPMENT LISTBOX
			echo "<p>Supply Items<br>";
			echo "<select name='Supply' size="; echo sizeof($ftm->getSupplies()); ">";
			for($i=0;$i<sizeof($ftm->getSupplies());$i++){
					
				echo " </br>";
				echo "<option value='Option 1' selected>";
				echo $ftm->getSupply_index($i)->getName()." : ";
				echo $ftm->getSupply_index($i)->getCount();
				"</option>";
			}
			echo "</select>";
			echo "</p>";
			
			
			?>
			
			</p>
		</span>
	</body>
</html>

