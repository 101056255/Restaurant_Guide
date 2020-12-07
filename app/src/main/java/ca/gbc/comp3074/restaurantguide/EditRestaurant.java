package ca.gbc.comp3074.restaurantguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.PhoneNumberUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditRestaurant extends AppCompatActivity {

    private DatabaseHelper myDb;
    private TextView name, phone, address, rating, desc, tags;
    private EditText nameEdit, phoneEdit, addressEdit, ratingEdit, descEdit, tagsEdit;
    private Button update;
    private String nameRest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_restaurant);

        myDb = new DatabaseHelper(this);

        name = findViewById(R.id.txt_name_header);
        address = findViewById(R.id.txt_address_header);
        phone = findViewById(R.id.txt_phone_header);
        rating = findViewById(R.id.txt_rating_header);
        desc = findViewById(R.id.txt_desc_header);
        tags = findViewById(R.id.txt_tags_header);

        nameEdit = findViewById(R.id.txt_edit_name);
        addressEdit = findViewById(R.id.txt_edit_address);
        phoneEdit = findViewById(R.id.txt_edit_phone);
        ratingEdit = findViewById(R.id.txt_edit_rating);
        descEdit = findViewById(R.id.txt_edit_desc);
        tagsEdit = findViewById(R.id.txt_edit_tags);

        update = findViewById(R.id.btn_update);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            nameRest = extras.getString("restaurantName");
            System.out.println(nameRest);
        } else {
            System.out.println("Failed");
        }

        name.setText(myDb.getName(nameRest));
        address.setText(myDb.getAddress(nameRest));
        phone.setText(myDb.getPhone(nameRest));
        rating.setText(myDb.getRating(nameRest));
        desc.setText(myDb.getDescription(nameRest));
        tags.setText(myDb.getTags(nameRest));

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNum = PhoneNumberUtils.formatNumber(phoneEdit.getText().toString().trim(),
                        "CA");

                final String name = nameEdit.getText().toString().trim();

                myDb.updateData(name,
                        phoneNum,
                        addressEdit.getText().toString().trim(),
                        ratingEdit.getText().toString().trim(), descEdit.getText().toString().trim(),
                        tagsEdit.getText().toString().trim(), myDb.getName(nameRest));

                Toast.makeText(getApplicationContext(), "Loading Changes",
                        Toast.LENGTH_SHORT).show();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent refresh = new Intent(EditRestaurant.this, RestaurantPageActivity.class);
                        refresh.putExtra("restName", name);
                        startActivity(refresh);
                    }
                }, 2000);
            }
        });
    }
}