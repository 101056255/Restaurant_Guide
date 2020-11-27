package ca.gbc.comp3074.restaurantguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddRestaurant extends AppCompatActivity{

    private DatabaseHelper myDb;
    private EditText name, address, rating, description;
    private Button addButton;

    RestaurantGuideActivity restaurantGuideActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_restaurant);

        myDb = new DatabaseHelper(this);
        restaurantGuideActivity = new RestaurantGuideActivity();

        name = findViewById(R.id.txt_name);
        address = findViewById(R.id.txt_address);
        rating = findViewById(R.id.txt_rating);
        description = findViewById(R.id.txt_desc);
        addButton = findViewById(R.id.btn_add);

        final String nameText = name.getText().toString();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = myDb.insertData(nameText,
                        address.getText().toString(),
                        rating.getText().toString(),
                        description.getText().toString());

                if (isInserted) {

                    Toast.makeText(AddRestaurant.this, "Restaurant Added",
                            Toast.LENGTH_SHORT).show();
                    restaurantGuideActivity.viewData();



                    Intent backToPage = new Intent(AddRestaurant.this,
                            RestaurantGuideActivity.class);
                    restaurantGuideActivity.viewData();
                    startActivity(backToPage);
                }
                else
                {
                    Toast.makeText(AddRestaurant.this, "Restaurant Failed to Add",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}