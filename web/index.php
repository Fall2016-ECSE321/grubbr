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
			$y = null;
			echo "<form action='removeitem.php' method ='post'>";
				echo "<p>Menu Items<br>";
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
			echo "<input type='submit' value='Remove selected Item'/>";
			echo "</p>";
			echo "</form>";
// 			if(isset($_POST['selectedMenuItem'])){
// 				$selected = $_POST['selectedMenuItem'];
// 			}
			
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
			
// 			if(isset($_POST['selectedMenuItem'])){
// 				echo $_POST['selectedMenuItem'];
// 				$pft = new PersistenceFoodTruck();
// 				$ftm = $pft->loadDataFromStore();
// 				$ftm->removeMenuItemByName($_POST['selectedMenuItem']);
// 				$pft->writeDataToStore($ftm);
// 				echo "<meta http-equiv='refresh' content='0; url=index.php' />";
// 			}
			
			//EQUIPMENT LISTBOX
			echo "<form action='removeequipment.php' method ='post'>";
			echo "<p>Equipment Items<br>";
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
			echo "<br>";
			echo "<input type='submit' value='Remove selected Equipment'/>";
			echo "</p>";
			echo "</form>";
			
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
			
			
			//SUPPLY LISTBOX
			echo "<form action='removesupply.php' method ='post'>";
			echo "<p>Supply Items<br>";
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
			echo "<br>";
			echo "<input type='submit' value='Remove selected Supply'/>";
			echo "</p>";
			echo "</form>";
			
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
			
			
			?>
			
			</p>
		</span>
	</body>
</html>

