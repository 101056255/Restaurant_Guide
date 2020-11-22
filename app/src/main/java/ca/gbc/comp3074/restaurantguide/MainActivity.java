package ca.gbc.comp3074.restaurantguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button buttonGuide;
    Button buttonAbout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonAbout=(Button) findViewById(R.id.aboutActivity);
        buttonGuide=(Button) findViewById(R.id.restaurantGuide);

       buttonAbout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent aboutActivity= new Intent(MainActivity.this,AboutActivity.class);
               startActivity(aboutActivity);
           }
       });

        buttonGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent restaurantGuide= new Intent(MainActivity.this,RestaurantGuideActivity.class);
                startActivity(restaurantGuide);
            }
        });

    }


}