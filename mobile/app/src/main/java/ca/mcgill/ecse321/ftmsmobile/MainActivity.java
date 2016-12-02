package ca.mcgill.ecse321.ftmsmobile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import ca.mcgill.ecse321.foodtruck.controller.FoodTruckController;
import ca.mcgill.ecse321.foodtruck.controller.InvalidInputException;
import ca.mcgill.ecse321.foodtruck.model.Employee;
import ca.mcgill.ecse321.foodtruck.model.Equipment;
import ca.mcgill.ecse321.foodtruck.model.FoodItem;
import ca.mcgill.ecse321.foodtruck.model.FoodTruckManager;
import ca.mcgill.ecse321.foodtruck.model.Shift;
import ca.mcgill.ecse321.foodtruck.model.Supply;
import ca.mcgill.ecse321.foodtruck.persistence.PersistenceFoodTruck;

public class MainActivity extends AppCompatActivity {

    private String errorItem;
    private String errorSupply;
    private String errorEquip;
    private String errorSCount;
    private String errorECount;
    private String errorAddStaff;
    private String errorAddShift;
    private String errorRemoveStaff;
    private String errorRemoveShift;
    private String errorOrder;

    private HashMap<Integer, Equipment> equipments;
    private HashMap<Integer, Supply> supplies;
    private HashMap<Integer, Employee> employees;
    private HashMap<Integer, Shift> shifts;
    private HashMap<Integer, FoodItem> foodMenu;
    private String week[] = new String[7];

    //Refers to the last employee for whom the user chose to see the schedule.
    private Employee lastSelectedEmployee;

    private static boolean firstRun=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Setup persistence layer if it's the first run
        if (firstRun) {
            PersistenceFoodTruck.setFilename(getFilesDir().getAbsolutePath() + File.separator + "foodtruck.xml");
            PersistenceFoodTruck.loadFoodTruckModel();

            firstRun=false;
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Initialize weekday spinner
        week[0] = "Monday";
        week[1] = "Tuesday";
        week[2] = "Wednesday";
        week[3] = "Thursday";
        week[4] = "Friday";
        week[5] = "Saturday";
        week[6] = "Sunday";
        ArrayAdapter<String> weekAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,week);
        Spinner weekspin = (Spinner) findViewById(R.id.weekdays);
        weekspin.setAdapter(weekAdapter);

        refreshData();
    }

    //Recycled code from Event Registration App
    public void showTimePickerDialog(View v) {
        TextView tf = (TextView) v;
        Bundle args = getTimeFromLabel(tf.getText());
        args.putInt("id", v.getId());
        TimePickerFragment newFragment = new TimePickerFragment();
        newFragment.setArguments(args);
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }
    private Bundle getTimeFromLabel(CharSequence text) {
        Bundle rtn = new Bundle();
        String comps[] = text.toString().split(":");
        int hour = 12;
        int minute = 0;
        if (comps.length == 2) {
            hour = Integer.parseInt(comps[0]); minute = Integer.parseInt(comps[1]);
        }
        rtn.putInt("hour", hour); rtn.putInt("minute", minute);
        return rtn;
    }
    public void setTime(int id, int h, int m) {
        TextView tv = (TextView) findViewById(id);
        tv.setText(String.format("%02d:%02d", h, m));
    }
    // End recycled code

    public void changeTab(View v){
        LinearLayout mainmenu = (LinearLayout) findViewById(R.id.Main);
        LinearLayout menu = (LinearLayout) findViewById(R.id.Menu);
        LinearLayout equip = (LinearLayout) findViewById(R.id.Equipment);
        LinearLayout supply = (LinearLayout) findViewById(R.id.Supply);
        LinearLayout employee = (LinearLayout) findViewById(R.id.Employee);

        String id = getResources().getResourceEntryName(v.getId());

        menu.setVisibility(View.GONE);
        equip.setVisibility(View.GONE);
        supply.setVisibility(View.GONE);
        employee.setVisibility(View.GONE);

        switch(id){
            case "menutab":
                if (mainmenu.getVisibility()==View.VISIBLE){
                    mainmenu.setVisibility(View.GONE);
                    menu.setVisibility(View.VISIBLE);
                } else {
                    mainmenu.setVisibility(View.VISIBLE);
                }
                break;
            case "equipmenttab":
                if (mainmenu.getVisibility()==View.VISIBLE) {
                    mainmenu.setVisibility(View.GONE);
                    equip.setVisibility(View.VISIBLE);
                } else {
                    mainmenu.setVisibility(View.VISIBLE);
                }
                break;
            case "supplytab":
                if (mainmenu.getVisibility()==View.VISIBLE) {
                    mainmenu.setVisibility(View.GONE);
                    supply.setVisibility(View.VISIBLE);
                } else {
                    mainmenu.setVisibility(View.VISIBLE);
                }
                break;
            case "employeetab":
                if (mainmenu.getVisibility()==View.VISIBLE) {
                    mainmenu.setVisibility(View.GONE);
                    employee.setVisibility(View.VISIBLE);
                } else {
                    mainmenu.setVisibility(View.VISIBLE);
                }
                break;
        }

    }

    private void refreshData() {
        FoodTruckManager ftm = FoodTruckManager.getInstance();
        TextView itemNameView = (TextView) findViewById(R.id.newitem_name);
        TextView itemPriceView = (TextView) findViewById(R.id.newitem_price);

        TextView supplyNameView = (TextView) findViewById(R.id.newsupply_name);
        TextView supplyCountView = (TextView) findViewById(R.id.newsupply_count);

        TextView equipmentNameView = (TextView) findViewById(R.id.newequipment_name);
        TextView equipmentCountView = (TextView) findViewById(R.id.newequipment_count);

        TextView employeeName = (TextView) findViewById(R.id.employee_name);
        TextView employeePay = (TextView) findViewById(R.id.employee_salary);
        TextView employeeRole = (TextView) findViewById(R.id.employee_function);

        TextView employeeShiftTitleView = (TextView) findViewById(R.id.employeeShiftTitle);
        TextView removeShiftTitleView = (TextView) findViewById(R.id.removeShiftTitle);

        TextView orderTitleView = (TextView) findViewById(R.id.orderTitle);
        TextView amountSoldView = (TextView) findViewById(R.id.amountOrdered);

        TextView startTime = (TextView) findViewById(R.id.employee_starttime);
        TextView endTime = (TextView) findViewById(R.id.employee_endtime);
        Spinner selectedEmployee = (Spinner) findViewById(R.id.employeespinner);

        //Sets the error message next to the "name" field regardless if the error
        //is in the name or in the price
        // I should change this to display the error message(s) somewhere like at the top
        itemNameView.setError(errorItem);
        supplyNameView.setError(errorSupply);
        equipmentNameView.setError(errorEquip);
        supplyCountView.setError(errorSCount);
        employeeName.setError(errorAddStaff);
        employeeShiftTitleView.setError(errorAddShift);
        removeShiftTitleView.setError(errorRemoveShift);
        orderTitleView.setError(errorOrder);


        if (errorItem == null) {
            itemNameView.setText("");
            itemPriceView.setText("");
        }

        if (errorSupply == null) {
            supplyNameView.setText("");
            supplyCountView.setText("");

            //Initialize spinner data
            Spinner supplySpinner = (Spinner) findViewById(R.id.supplyspinner);
            ArrayAdapter<CharSequence> supplyAdapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item);
            supplyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            this.supplies = new HashMap<Integer, Supply>();
            int i = 0;
            for (Iterator<Supply> supplies = ftm.getSupplies().iterator(); supplies.hasNext(); i++) {
                Supply s = supplies.next();
                supplyAdapter.add(s.getName());
                this.supplies.put(i, s);
            }
            supplySpinner.setAdapter(supplyAdapter);

        }

        if (errorEquip == null) {
            equipmentNameView.setText("");
            equipmentCountView.setText("");

            //Initialize spinner data
            Spinner equipmentSpinner = (Spinner) findViewById(R.id.equipmentspinner);
            ArrayAdapter<CharSequence> equipmentAdapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item);
            equipmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            this.equipments = new HashMap<Integer, Equipment>();
            int i = 0;
            for (Iterator<Equipment> equipments = ftm.getEquipment().iterator(); equipments.hasNext(); i++) {
                Equipment e = equipments.next();
                equipmentAdapter.add(e.getName());
                this.equipments.put(i, e);
            }
            equipmentSpinner.setAdapter(equipmentAdapter);
        }

        if (errorSCount == null) {
            supplyCountView.setText("");
        }

        if (errorAddShift == null) {
            startTime.setText("");
            endTime.setText("");
        }


        if (selectedEmployee.getSelectedItemPosition() >= 0) {
            displayShifts(employees.get(selectedEmployee.getSelectedItemPosition()));
        }

        if (errorAddStaff == null) {
            employeeName.setText("");
            employeePay.setText("");
            employeeRole.setText("");

            //Initialize spinner data
            Spinner employeeSpinner = (Spinner) findViewById(R.id.employeespinner);
            ArrayAdapter<CharSequence> employeeAdapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item);
            employeeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            this.employees = new HashMap<Integer, Employee>();
            int i = 0;
            for (Iterator<Employee> employees = ftm.getEmployees().iterator(); employees.hasNext(); i++) {
                Employee emp = employees.next();
                employeeAdapter.add(emp.getName() + " - " + emp.getRole() + " - " + emp.getSalaryPerHour() + "$/hr");
                this.employees.put(i, emp);
            }
            employeeSpinner.setAdapter(employeeAdapter);
        }

        if(errorOrder==null){
            amountSoldView.setText("");
        }


        errorItem = null;
        errorEquip = null;
        errorSupply = null;
        errorSCount = null;
        errorECount = null;
        errorAddStaff = null;
        errorRemoveStaff = null;
        errorOrder = null;

        //Display changes to user
        displayItems();
        displayEquip();
        displaySupplies();
        displayOrders();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    //  MENU USE CASE
    public void addItem(View v){
        TextView itemNameView = (TextView) findViewById(R.id.newitem_name);
        TextView itemPriceView = (TextView) findViewById(R.id.newitem_price);
        FoodTruckController ftc = new FoodTruckController();

        errorItem=null;
        try{
            ftc.createFoodItem(itemNameView.getText().toString(),itemPriceView.getText().toString());
        } catch (InvalidInputException e){
            errorItem=e.getMessage();
        }
        refreshData();
    }

    //  INVENTORY USE CASE
    public void addSupply(View v){
        TextView supplyNameView = (TextView) findViewById(R.id.newsupply_name);
        FoodTruckController ftc = new FoodTruckController();

        errorSupply=null;
        try{
            ftc.createSupply(supplyNameView.getText().toString());
        } catch (InvalidInputException e){
            errorSupply=e.getMessage();
        }
        refreshData();
    }

    public void addEquipment(View v){
        TextView equipmentNameView = (TextView) findViewById(R.id.newequipment_name);
        FoodTruckController ftc = new FoodTruckController();

        errorEquip=null;
        try{
            ftc.createEquipment(equipmentNameView.getText().toString());
        } catch (InvalidInputException e){
            errorEquip=e.getMessage();
        }
        refreshData();
    }

    public void changeSupplyCount(View v){

        FoodTruckController ftc = new FoodTruckController();

        TextView supplyCount = (TextView) findViewById(R.id.newsupply_count);
        Spinner supplySpinner = (Spinner) findViewById(R.id.supplyspinner);

        Supply selectedSupply = supplies.get(supplySpinner.getSelectedItemPosition());
        String count;
        int rmvId = R.id.remove_supply;
        if(v.getId() == rmvId){
            count = "-1";
        } else {
            count = supplyCount.getText().toString();
        }
        try {
            ftc.editSupplyQuantity(selectedSupply, count);
        } catch (InvalidInputException e){
            //Records the error
            errorSCount = e.getMessage();
        }
        refreshData();
    }

    public void changeEquipmentCount(View v){
        FoodTruckController ftc = new FoodTruckController();

        TextView equipmentCount = (TextView) findViewById(R.id.newequipment_count);
        Spinner equipmentSpinner = (Spinner) findViewById(R.id.equipmentspinner);

        Equipment selectedEquipment = equipments.get(equipmentSpinner.getSelectedItemPosition());

        String count;
        int rmvId = R.id.remove_equip;
        if(v.getId() == rmvId){
            count = "-1";
        } else {
            count = equipmentCount.getText().toString();
        }

        try{
            ftc.editEquipmentQuantity(selectedEquipment,count);
        } catch (InvalidInputException e){
            //Records the error
            errorECount = e.getMessage();
        }
        refreshData();
    }
    //  EMPLOYEE USE CASE
    public void addEmployee(View v){
        TextView employeeName = (TextView) findViewById(R.id.employee_name);
        TextView employeeFunction = (TextView) findViewById(R.id.employee_function);
        TextView employeeSalary = (TextView) findViewById(R.id.employee_salary);

        FoodTruckController ftc = new FoodTruckController();

        try{
            ftc.createEmployee(employeeName.getText().toString(),employeeFunction.getText().toString(),employeeSalary.getText().toString());
        } catch (InvalidInputException e){
            //Records the error
            errorAddStaff = e.getMessage();
        }
        refreshData();
    }

    public void addShift(View v){
        FoodTruckController ftc = new FoodTruckController();

        //View elements
        Spinner employeeView = (Spinner) findViewById(R.id.employeespinner);
        Spinner weekDayView = (Spinner) findViewById(R.id.weekdays);
        TextView startTimeView = (TextView) findViewById(R.id.employee_starttime);
        TextView endTimeView = (TextView) findViewById(R.id.employee_endtime);

        //Model elements
        Employee selectedEmp = employees.get(employeeView.getSelectedItemPosition());
        java.sql.Time startTime = getSqlTimeFromLabel(startTimeView.getText());
        java.sql.Time endTime = getSqlTimeFromLabel(endTimeView.getText());
        String weekDay = week[weekDayView.getSelectedItemPosition()];

        try{
            ftc.createShift(selectedEmp,weekDay,startTime,endTime);
        } catch (InvalidInputException e){
            errorAddShift=e.getMessage();
        }

        refreshData();
    }
    public void removeShift(View v){
        FoodTruckController ftc = new FoodTruckController();

        Spinner scheduleSpinner = (Spinner) findViewById(R.id.shiftSpinner);
        Shift selectedShift = this.shifts.get(scheduleSpinner.getSelectedItemPosition());

        try{
            ftc.cancelShift(lastSelectedEmployee,selectedShift);
        } catch (InvalidInputException e){
            //error handling
            errorRemoveShift = e.getMessage();
        }
        refreshData();
    }

    public void removeEmployee(View v){
        FoodTruckController ftc = new FoodTruckController();
        Spinner selectedEmployee = (Spinner) findViewById(R.id.employeespinner);
        TextView shifts = (TextView) findViewById(R.id.employee_display);
        Employee emp = this.employees.get(selectedEmployee.getSelectedItemPosition());
        try {
            ftc.removeEmployee(emp);
        } catch (InvalidInputException e){
            errorRemoveStaff = e.getMessage();
        }
        refreshData();
        shifts.setText("You have fired "+emp.getName()+".");
    }

    public void addOrder(View v){
        FoodTruckController ftc = new FoodTruckController();
        Spinner orderSpinner = (Spinner) findViewById(R.id.selectMenuItem);
        TextView amountOrderedView = (TextView) findViewById(R.id.amountOrdered);

        try{
            ftc.orderFood(foodMenu.get(orderSpinner.getSelectedItemPosition()),amountOrderedView.getText().toString());
        } catch (InvalidInputException e){
            //Error handling
            errorOrder = e.getMessage();
        }
        refreshData();
    }

    //Helper Method -- Recycled from Event Registration
    private java.sql.Time getSqlTimeFromLabel(CharSequence text) {
        String timeString = text.toString()+":00";
        return java.sql.Time.valueOf(timeString);
    }
    //  DISPLAY
    public void displaySupplies(){
        TextView supplyList = (TextView) findViewById(R.id.supply_display);
        FoodTruckManager ftm = FoodTruckManager.getInstance();
        supplyList.setText("");
        for(int i=0;i<ftm.getSupplies().size();i++){
            supplyList.setText(supplyList.getText() + ftm.getSupply(i).getName() + " : " + ftm.getSupply(i).getCount() + "\n");
        }
    }

    public void displayEquip (){
        TextView equipList = (TextView) findViewById(R.id.equipment_display);
        FoodTruckManager ftm = FoodTruckManager.getInstance();
        equipList.setText("");
        for(int i=0;i<ftm.getEquipment().size();i++){
            equipList.setText(equipList.getText() + ftm.getEquipment(i).getName() + " : " + ftm.getEquipment(i).getCount() + "\n");
        }
    }

    public void displayItems(){
        TextView itemList = (TextView) findViewById(R.id.menu_display);
        FoodTruckManager ftm = FoodTruckManager.getInstance();
        itemList.setText("");
        for (int i=0;i<ftm.getFoodItems().size();i++) {
            itemList.setText(itemList.getText() + ftm.getFoodItem(i).getName() +" : " + ftm.getFoodItem(i).getPrice() + "$" + "\n");
        }

        Spinner foodItemSpinner = (Spinner) findViewById(R.id.selectMenuItem);
        ArrayAdapter<CharSequence> foodAdapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item);
        foodAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.foodMenu = new HashMap<Integer, FoodItem>();
        int i=0;

        for(Iterator<FoodItem> foodMenu = ftm.getFoodItems().iterator(); foodMenu.hasNext();i++){
            FoodItem currentFood = foodMenu.next();
            foodAdapter.add(currentFood.getName()+" : "+currentFood.getPrice()+"$");
            this.foodMenu.put(i,currentFood);
        }
        foodItemSpinner.setAdapter(foodAdapter);
    }

    public void showShifts(View v) {
        Spinner selectedEmployee = (Spinner) findViewById(R.id.employeespinner);
        if (selectedEmployee.getSelectedItemPosition() >= 0) {
            displayShifts(this.employees.get(selectedEmployee.getSelectedItemPosition()));
        }
    }

    public void displayShifts(Employee emp){
        TextView shiftList = (TextView) findViewById(R.id.employee_display);
        shiftList.setText("Employee: "+emp.getName()+" - "+emp.getRole()+"\nSalary: "+emp.getSalaryPerHour()+"$/hr\n");
        for (int i=0;i<emp.getShifts().size();i++){
            shiftList.setText(shiftList.getText()+emp.getShift(i).getDayOfWeek()+" : "+emp.getShift(i).getStartTime()+" to "+emp.getShift(i).getEndTime()+"\n");
        }

        //Also initialize dropdown menu to remove shifts
        Spinner scheduleSpinner = (Spinner) findViewById(R.id.shiftSpinner);
        ArrayAdapter<CharSequence> shiftAdapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item);
        shiftAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.shifts = new HashMap<Integer, Shift>();
        int i=0;
        for(Iterator<Shift> shifts = emp.getShifts().iterator(); shifts.hasNext(); i++){
            Shift currentShift = shifts.next();
            shiftAdapter.add(currentShift.getDayOfWeek()+" : "+currentShift.getStartTime()+" to "+currentShift.getEndTime());
            this.shifts.put(i,currentShift);
        }
        scheduleSpinner.setAdapter(shiftAdapter);

        lastSelectedEmployee = emp;
    }
    public void displayOrders(){
        FoodTruckController ftc = new FoodTruckController();
        TextView displayOrders = (TextView) findViewById(R.id.topFiveView);
        ArrayList<FoodItem> food = (ArrayList) ftc.getPopularItems();
        displayOrders.setText("");
        for(int i=0;i<food.size();i++){
            displayOrders.setText(displayOrders.getText()+"\n"+(i+1)+". "+food.get(i).getName()+" | Amount Sold:"+food.get(i).getAmountSold());
        }
    }
}
