package ca.gbc.comp3074.restaurantguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class ContactUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.restaurant_guide) {
            Intent intent = new Intent(ContactUsActivity.this, RestaurantGuideActivity.class);
            startActivity(intent);
            return true;
        }
        if (item.getItemId() == R.id.about) {
            Intent intent = new Intent(ContactUsActivity.this, AboutActivity.class);
            startActivity(intent);
            return true;
        }
        if (item.getItemId() == R.id.contactus) {
            Intent intent = new Intent(ContactUsActivity.this, ContactUsActivity.class);
            startActivity(intent);
            return true;
        }

        if (item.getItemId() == R.id.share) {
            Intent intent = new Intent(ContactUsActivity.this, ShareActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);

    }
}