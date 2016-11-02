package ca.mcgill.ecse321.ftmsmobile;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.File;

import ca.mcgill.ecse321.foodtruck.controller.FoodTruckController;
import ca.mcgill.ecse321.foodtruck.controller.InvalidInputException;
import ca.mcgill.ecse321.foodtruck.model.FoodTruckManager;
import ca.mcgill.ecse321.foodtruck.persistence.PersistenceFoodTruck;

public class MainActivity extends AppCompatActivity {

    private String errorItem;
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
    private void refreshData(){
        FoodTruckManager ftm = FoodTruckManager.getInstance();
        TextView itemNameView = (TextView) findViewById(R.id.newitem_name);
        TextView itemPriceView= (TextView) findViewById(R.id.newitem_price);

        TextView supplyNameView = (TextView) findViewById(R.id.newsupply_name);
        TextView supplyCountView = (TextView) findViewById(R.id.newsupply_count);

        TextView equipmentNameView = (TextView) findViewById(R.id.newequipment_name);
        TextView equipmentCountView = (TextView) findViewById(R.id.newequipment_count);

        //Sets the error message next to the "name" field regardless if the error
        //is in the name or in the price
        // I should change this to display the error message(s) somewhere like at the top
        if (errorItem != null) {
            itemNameView.setError(errorItem);
        } else {
            itemNameView.setText("");
            itemPriceView.setText("");
        }
        displayItems();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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

        errorItem=null;
        try{
            ftc.createSupply(supplyNameView.getText().toString());
        } catch (InvalidInputException e){
            errorItem=e.getMessage();
        }
        refreshData();
    }

    public void addEquipment(View v){
        TextView equipmentNameView = (TextView) findViewById(R.id.newequipment_name);
        FoodTruckController ftc = new FoodTruckController();

        errorItem=null;
        try{
            ftc.createEquipment(equipmentNameView.getText().toString());
        } catch (InvalidInputException e){
            errorItem=e.getMessage();
        }
        refreshData();
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
