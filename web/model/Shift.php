<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

class Shift
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Shift Attributes
  private $dayOfWeek;
  private $startTime;
  private $endTime;
  private $numberOfHours;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aDayOfWeek, $aStartTime, $aEndTime, $aNumberOfHours)
  {
    $this->dayOfWeek = $aDayOfWeek;
    $this->startTime = $aStartTime;
    $this->endTime = $aEndTime;
    $this->numberOfHours = $aNumberOfHours;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setDayOfWeek($aDayOfWeek)
  {
    $wasSet = false;
    $this->dayOfWeek = $aDayOfWeek;
    $wasSet = true;
    return $wasSet;
  }

  public function setStartTime($aStartTime)
  {
    $wasSet = false;
    $this->startTime = $aStartTime;
    $wasSet = true;
    return $wasSet;
  }

  public function setEndTime($aEndTime)
  {
    $wasSet = false;
    $this->endTime = $aEndTime;
    $wasSet = true;
    return $wasSet;
  }

  public function setNumberOfHours($aNumberOfHours)
  {
    $wasSet = false;
    $this->numberOfHours = $aNumberOfHours;
    $wasSet = true;
    return $wasSet;
  }

  public function getDayOfWeek()
  {
    return $this->dayOfWeek;
  }

  public function getStartTime()
  {
    return $this->startTime;
  }

  public function getEndTime()
  {
    return $this->endTime;
  }

  public function getNumberOfHours()
  {
    return $this->numberOfHours;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {}

}
?>