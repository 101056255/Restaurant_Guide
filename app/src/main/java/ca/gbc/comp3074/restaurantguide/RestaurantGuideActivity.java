package ca.gbc.comp3074.restaurantguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class RestaurantGuideActivity extends AppCompatActivity {

    private Button addNewRest, restOne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_guide);

        addNewRest = findViewById(R.id.btn_addRest);
        restOne = findViewById(R.id.btn_one);

        restOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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


}