<?php
class PersistenceFoodTruck {
	private $filename;
	function __construct($filename = 'data.txt')
	{
		$this->filename = $filename;
	}	
	function loadDataFromStore()
	{
		if (file_exists($this->filename))
		{
			$str = file_get_contents($this->filename);
			$ftm = unserialize($str);
		} else {
			$ftm = FoodTruckManager::getInstance();
		}
		return $ftm;
	}
	function writeDataToStore($ftm)
	{
		$str=serialize($ftm);
		file_put_contents($this->filename,$str);
	}
// 	public function addMenuItem($aMenuItem)
// 	{
		
// 	}
}
?>