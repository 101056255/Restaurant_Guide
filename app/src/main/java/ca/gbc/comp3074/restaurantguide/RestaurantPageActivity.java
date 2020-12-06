package ca.gbc.comp3074.restaurantguide;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import static ca.gbc.comp3074.restaurantguide.Constants.MAPVIEW_BUNDLE_KEY;

import java.util.List;
import java.util.Objects;

public class RestaurantPageActivity extends AppCompatActivity implements OnMapReadyCallback {

    private DatabaseHelper myDb;
    private TextView restName, restAddress, restRating, restDesc, restPhone, restTags;
    private String nameRest;
    private MapView mapView;
    private Button emailBtn, shareBtn;

    @SuppressLint("SetTextI18n")
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
        restPhone = findViewById(R.id.txt_rest_phone);
        emailBtn = findViewById(R.id.btn_sendEmail);
        shareBtn = findViewById(R.id.btn_share);
        restTags = findViewById(R.id.txt_tags);

        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY);
        }

        mapView = findViewById(R.id.mapView);
        mapView.onCreate(mapViewBundle);
        mapView.getMapAsync(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            nameRest = extras.getString("restName");
            System.out.println(nameRest);
        } else {
            System.out.println("Failed");
        }

        restName.setText(myDb.getName(nameRest));
        restAddress.setText(myDb.getAddress(nameRest));
        restRating.setText(myDb.getRating(nameRest));
        restDesc.setText(myDb.getDescription(nameRest));
        restPhone.setText(myDb.getPhone(nameRest));

        if(myDb.getTags(nameRest) != null)
        {
            restTags.setText(myDb.getTags(nameRest));
        }
        else
        {
            restTags.setText("No tags provided");
        }

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        emailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_SUBJECT, nameRest);
                email.putExtra(Intent.EXTRA_TEXT, "Check out this restaurant! \n" +
                        "Name: " + myDb.getName(nameRest) + "\n" +
                        "Address: " + myDb.getAddress(nameRest) + "\n" +
                        "Phone Number: " + myDb.getPhone(nameRest));
                email.setType("message/rfc822");
                startActivity(email);
            }
        });

        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(Intent.EXTRA_TEXT, "Check out this place! \n" +
                        myDb.getName(nameRest));
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle mapViewBundle = outState.getBundle(MAPVIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAPVIEW_BUNDLE_KEY, mapViewBundle);
        }

        mapView.onSaveInstanceState(mapViewBundle);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        googleMap.getUiSettings().setAllGesturesEnabled(true);

        LatLng address = getLocationFromAddress(this, myDb.getAddress(nameRest));

        googleMap.addMarker(new MarkerOptions()
                .position(address)
                .title(nameRest));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(address, 15));

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        googleMap.setMyLocationEnabled(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onPause() {
        mapView.onPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    public LatLng getLocationFromAddress(Context context, String strAddress)
    {
        Geocoder coder= new Geocoder(context);
        List<Address> address;
        LatLng p1 = null;

        try
        {
            address = coder.getFromLocationName(strAddress, 5);
            if(address==null)
            {
                return null;
            }
            Address location = address.get(0);
            location.getLatitude();
            location.getLongitude();

            p1 = new LatLng(location.getLatitude(), location.getLongitude());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return p1;

    }
}
