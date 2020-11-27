package ca.gbc.comp3074.restaurantguide;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Objects;

public class RestaurantPageActivity extends AppCompatActivity {

    private DatabaseHelper myDb;
    private TextView restName, restAddress, restRating, restDesc;
    private String nameRest;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restuarant_page);

        myDb = new DatabaseHelper(this);

        restName = findViewById(R.id.txt_restaurant_title);
        restAddress = findViewById(R.id.txt_rest_address);
        restRating = findViewById(R.id.txt_rest_rating);
        restDesc = findViewById(R.id.txt_rest_desc);

        Bundle extras = getIntent().getExtras();
        if(extras != null)
        {
            nameRest = extras.getString("restName");
            System.out.println(nameRest);
        }
        else
        {
            System.out.println("Failed");
        }

        restName.setText(myDb.getName(nameRest));
        restAddress.setText(myDb.getAddress(nameRest));
        restRating.setText(myDb.getRating(nameRest));
        restDesc.setText(myDb.getDescription(nameRest));

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }
}