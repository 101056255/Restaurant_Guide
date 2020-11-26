package ca.gbc.comp3074.restaurantguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class RestaurantGuideActivity extends AppCompatActivity {

    ListView listView;
    Button addRes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_guide);

        listView=(ListView)findViewById(R.id.listview);
        addRes=(Button) findViewById(R.id.addRest);

        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add("Restaurant 1");
        arrayList.add("Restaurant 2");

        ArrayAdapter arrayAdapter= new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);

        listView.setAdapter(arrayAdapter);





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





    


}