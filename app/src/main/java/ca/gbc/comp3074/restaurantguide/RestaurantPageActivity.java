package ca.gbc.comp3074.restaurantguide;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Objects;

public class RestaurantPageActivity extends AppCompatActivity {

    private DatabaseHelper myDb;
    private TextView restName;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restuarant_page);

        myDb = new DatabaseHelper(this);

        restName = findViewById(R.id.txt_restaurant_title);

        restName.setText(myDb.getName());

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }
}