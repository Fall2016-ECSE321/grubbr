package ca.mcgill.ecse321.ftmsmobile;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;

import ca.mcgill.ecse321.foodtruck.controller.FoodTruckController;
import ca.mcgill.ecse321.foodtruck.controller.InvalidInputException;
import ca.mcgill.ecse321.foodtruck.model.Equipment;
import ca.mcgill.ecse321.foodtruck.model.FoodTruckManager;
import ca.mcgill.ecse321.foodtruck.model.Supply;
import ca.mcgill.ecse321.foodtruck.persistence.PersistenceFoodTruck;

public class MainActivity extends AppCompatActivity {

    private String errorItem;
    private String errorSupply;
    private String errorEquip;
    private String errorSCount;
    private String errorECount;

    private HashMap<Integer, Equipment> equipments;
    private HashMap<Integer, Supply> supplies;

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
        refreshData();
    }

    public void menuTab(View v){

    }

    private void refreshData(){
        FoodTruckManager ftm = FoodTruckManager.getInstance();
        TextView itemNameView = (TextView) findViewById(R.id.newitem_name);
        TextView itemPriceView = (TextView) findViewById(R.id.newitem_price);

        TextView supplyNameView = (TextView) findViewById(R.id.newsupply_name);
        TextView supplyCountView = (TextView) findViewById(R.id.newsupply_count);

        TextView equipmentNameView = (TextView) findViewById(R.id.newequipment_name);
        TextView equipmentCountView = (TextView) findViewById(R.id.newequipment_count);

        //Sets the error message next to the "name" field regardless if the error
        //is in the name or in the price
        // I should change this to display the error message(s) somewhere like at the top
        itemNameView.setError(errorItem);
        supplyNameView.setError(errorSupply);
        equipmentNameView.setError(errorEquip);
        supplyCountView.setError(errorSCount);

        if (errorItem == null)
        {
            itemNameView.setText("");
            itemPriceView.setText("");
        }

        if (errorSupply == null)
        {
            supplyNameView.setText("");

            //Initialize spinner data
            Spinner supplySpinner = (Spinner) findViewById(R.id.supplyspinner);
            ArrayAdapter<CharSequence> supplyAdapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item);
            supplyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            this.supplies = new HashMap<Integer,Supply>();
            int i=0;
            for (Iterator<Supply> supplies = ftm.getSupplies().iterator(); supplies.hasNext(); i++){
                Supply s = supplies.next();
                supplyAdapter.add(s.getName());
                this.supplies.put(i,s);
            }
            supplySpinner.setAdapter(supplyAdapter);

        }

        if (errorEquip == null)
        {
            equipmentNameView.setText("");
            //Initialize spinner data
            Spinner equipmentSpinner = (Spinner) findViewById(R.id.equipmentspinner);
            ArrayAdapter<CharSequence> equipmentAdapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item);
            equipmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            this.equipments = new HashMap<Integer,Equipment>();
            int i=0;
            for (Iterator<Equipment> equipments = ftm.getEquipment().iterator(); equipments.hasNext(); i++){
                Equipment e = equipments.next();
                equipmentAdapter.add(e.getName());
                this.equipments.put(i,e);
            }
            equipmentSpinner.setAdapter(equipmentAdapter);
        }

        if (errorSCount == null) {
            supplyCountView.setText("");
        }

        errorItem=null;
        errorEquip=null;
        errorSupply=null;
        errorSCount=null;
        errorECount=null;
        displayItems();
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
    public void addItem(View v){
        TextView itemNameView = (TextView) findViewById(R.id.newitem_name);
        TextView itemPriceView = (TextView) findViewById(R.id.newitem_price);
        FoodTruckController ftc = new FoodTruckController();

        errorItem=null;
        try{
            ftc.createMenuItem(itemNameView.getText().toString(),itemPriceView.getText().toString());
        } catch (InvalidInputException e){
            errorItem=e.getMessage();
        }
        refreshData();
    }

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

        try {
            ftc.editSupplyQuantity(selectedSupply, supplyCount.getText().toString());
        } catch (InvalidInputException e){
            //Records the error
            errorSCount = e.getMessage();
        }
    }

    public void changeEquipmentCount(View v){
        FoodTruckController ftc = new FoodTruckController();

        TextView equipmentCount = (TextView) findViewById(R.id.newequipment_count);
        Spinner equipmentSpinner = (Spinner) findViewById(R.id.equipmentspinner);

        Equipment selectedEquipment = equipments.get(equipmentSpinner.getSelectedItemPosition());

        try{
            ftc.editEquipmentQuantity(selectedEquipment,equipmentCount.getText().toString());
        } catch (InvalidInputException e){
            //Records the error
            errorECount = e.getMessage();
        }
    }

    public void changeSupplyQuantity(View v){
        TextView supplyCountView = (TextView) findViewById(R.id.newsupply_count);

    }

    public void displayItems(){
        TextView itemList = (TextView) findViewById(R.id.menu_display);
        FoodTruckManager ftm = FoodTruckManager.getInstance();
        itemList.setText("");
        for (int i=0;i<ftm.getMenuItems().size();i++) {
            itemList.setText(itemList.getText() + ftm.getMenuItem(i).getName() +" : " + ftm.getMenuItem(i).getPrice() + "$" + "\n");
        }
    }
}
