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
		<!--?php 
			require_once "model/Participant.php";
			require_once "model/Registration.php";
			require_once "model/RegistrationManager.php";
			require_once "model/Event.php";
			require_once "persistence/PersistenceEventRegistration.php";
			session_start();
			
			//Retreive data from the model
			$pm = new PersistenceEventRegistration();
			$rm = $pm->loadDataFromStore();
			
			echo "<h1>Register</h1>";
			echo "<form action='register.php' method='post'>";
			echo "<fieldset>";
			echo "<p>Name? <select name='participantspinner'>";
			foreach ($rm->getParticipants() as $participant){
				echo "<option>" . $participant->getName() . "</option>";
			}
			echo "</select><span class='error'>";
			if (isset($_SESSION['errorRegisterParticipant']) && !empty($_SESSION['errorRegisterParticipant'])){
				echo " * " . $_SESSION["errorRegisterParticipant"];
			}
			echo "</span></p>";
			echo "<p>Event? <select name='eventspinner'>";
			foreach ($rm->getEvents() as $event){
				echo "<option>" . $event->getName() . "</option>";
			}
			echo "</select><span class='error'>";
			if (isset($_SESSION['errorRegisterEvent']) && !empty($_SESSION['errorRegisterEvent'])){
				echo " * " . $_SESSION["errorRegisterEvent"];
			}
			echo "</span></p>";
			echo "<p><input type='submit' value='Register'/></p>";
			echo "</fieldset>";
			echo "</form>";
		?-->
		<h1> Add Item </h1>
		<form action = "additem.php" method="post">
			<fieldset>
			<br>
			<p>Item Name: <input type="text" name="newitem_name" placeholder="Enter Name"/>
			<p>Item Price: <input type="text" name="newitem_price" placeholder="Enter Price"/>
			<span class="error">
			<?php
			if (isset($_SESSION['errorItemName']) && !empty($_SESSION['errorItemName'])){
				echo " * " . $_SESSION["errorItemName"];
			}
			if (isset($_SESSION['errorItemPrice']) && !empty($_SESSION['errorItemPrice'])){
				echo " * " . $_SESSION["errorItemPrice"];
			}
			?>
			</span></p>	
			<p><input type="submit" value="Add Item"/></p>
			<br>
			</fieldset>
		</form>
	</body>
</html>

