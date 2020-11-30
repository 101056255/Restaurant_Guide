package ca.gbc.comp3074.restaurantguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;
import java.util.Locale;

public class AddRestaurant extends AppCompatActivity{

    private DatabaseHelper myDb;
    private EditText name, address, rating, description, phone;
    private Button addButton;

    RestaurantGuideActivity restaurantGuideActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_restaurant);

        myDb = new DatabaseHelper(this);
        restaurantGuideActivity = new RestaurantGuideActivity();

        name = findViewById(R.id.txt_name);
        phone = findViewById(R.id.txt_phone);
        address = findViewById(R.id.txt_address);
        rating = findViewById(R.id.txt_rating);
        description = findViewById(R.id.txt_desc);
        addButton = findViewById(R.id.btn_add);

        name.addTextChangedListener(textWatcher);
        phone.addTextChangedListener(textWatcher);
        address.addTextChangedListener(textWatcher);
        rating.addTextChangedListener(textWatcher);
        description.addTextChangedListener(textWatcher);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String phoneNum = PhoneNumberUtils.formatNumber(phone.getText().toString().trim(),
                        "CA");

                boolean isInserted = myDb.insertData(name.getText().toString().trim(),
                        phoneNum,
                        address.getText().toString().trim(),
                        rating.getText().toString().trim(), description.getText().toString().trim());

                if (isInserted) {

                    Toast.makeText(AddRestaurant.this, "Restaurant Added",
                            Toast.LENGTH_SHORT).show();
                    restaurantGuideActivity.viewData();
                }
                else
                {
                    Toast.makeText(AddRestaurant.this, "Restaurant Failed to Add",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public final TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            String inputName = name.getText().toString().trim();
            String inputNum = phone.getText().toString().trim();
            String inputAddress = address.getText().toString().trim();
            String inputDesc = description.getText().toString().trim();
            String inputRating = rating.getText().toString().trim();

            addButton.setEnabled(!inputName.isEmpty() && !inputAddress.isEmpty() &&
                    !inputNum.isEmpty() && !inputDesc.isEmpty() && !inputRating.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };


}