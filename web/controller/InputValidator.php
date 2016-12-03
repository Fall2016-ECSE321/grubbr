<?php
class InputValidator{
	public static function validate_input($data){
		$data = trim($data);
		$data = stripslashes($data);
		$data = htmlspecialchars($data);
		return $data;
	}

	public static function validate_time($str){
        if(strtotime($str)) {
           return true;
        } else {
            return false;
        }
    }
}
?>