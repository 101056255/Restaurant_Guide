package ca.gbc.comp3074.restaurantguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class RestaurantGuideActivity extends AppCompatActivity {

    EditText resSearch;
    ListView listView;
    Button addRes;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_guide);

        resSearch=(EditText)findViewById(R.id.resSearch);
        listView=(ListView)findViewById(R.id.listview);
        addRes=(Button) findViewById(R.id.addRest);

        arrayList = new ArrayList<String>();

        arrayList.add("Restaurant 1");
        arrayList.add("Restaurant 2");

        arrayAdapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);

        listView.setAdapter(arrayAdapter);
        onBtnClick();



    }

    public void onBtnClick(){
        addRes.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String result= resSearch.getText().toString();
                arrayList.add(result);
                arrayAdapter.notifyDataSetChanged();



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





    


}