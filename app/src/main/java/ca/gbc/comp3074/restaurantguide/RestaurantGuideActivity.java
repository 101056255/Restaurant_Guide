package ca.gbc.comp3074.restaurantguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class RestaurantGuideActivity extends AppCompatActivity{


    ListView listView;
    DatabaseHelper db;


    private ArrayAdapter arrayAdapter;
    private final ArrayList<String> arrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_guide);

        db = new DatabaseHelper(this);

        listView=(ListView)findViewById(R.id.listview);

        arrayList.add("Restaurant 1");
        arrayList.add("Restaurant 2");

        viewData();

        //arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);

        listView.setAdapter(arrayAdapter);

        Button addNewRest = findViewById(R.id.addRest);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent restOne = new Intent(RestaurantGuideActivity.this,
                        RestaurantPageActivity.class);
                startActivity(restOne);
            }
        });

        addNewRest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addRest = new Intent(RestaurantGuideActivity.this,
                        AddRestaurant.class);
                startActivity(addRest);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.restaurant_guide) {
            Intent intent = new Intent(RestaurantGuideActivity.this, RestaurantGuideActivity.class);
            startActivity(intent);
            return true;
        }

        if (item.getItemId() == R.id.about) {
            Intent intent = new Intent(RestaurantGuideActivity.this, AboutActivity.class);
            startActivity(intent);
            return true;
        }
        if (item.getItemId() == R.id.contactus) {
            Intent intent = new Intent(RestaurantGuideActivity.this, ContactUsActivity.class);
            startActivity(intent);
            return true;
        }

        if (item.getItemId() == R.id.share) {
            Intent intent = new Intent(RestaurantGuideActivity.this, ShareActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    /*public void addToList(String name)
    {
        arrayList.add(name);
        arrayAdapter.notifyDataSetChanged();
    }*/

    public void viewData(){
        Cursor cursor = db.viewData();

        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data to show", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                arrayList.add(cursor.getString(cursor.getColumnIndex("name")));
            }

            arrayAdapter =new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);
            listView.setAdapter(arrayAdapter);


        }
    }
}