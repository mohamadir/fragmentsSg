package com.example.hosen.myapplication;

import android.app.Service;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ServicesActivity extends AppCompatActivity {
    Button serviceFly,serviceTrans,serviceGuide,serviceHotels,servicePlaces,serviceRests;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        serviceFly=(Button)findViewById(R.id.serviceFlightBt);
        serviceTrans=(Button)findViewById(R.id.serviceTransportBt);
        serviceGuide=(Button)findViewById(R.id.serviceGUideBt);
        serviceHotels=(Button)findViewById(R.id.serviceHotelsBt);
        servicePlaces=(Button)findViewById(R.id.servicePlacesBt);
        serviceRests=(Button)findViewById(R.id.serviceRestaurantBt);
        serviceFly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goIntent(GroupListActivity.class);
            }
        });
        serviceTrans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goIntent(GroupListActivity.class);
            }
        });
        servicePlaces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goIntent(GroupListActivity.class);
            }
        });
        serviceRests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goIntent(GroupListActivity.class);
            }
        }); serviceHotels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goIntent(GroupListActivity.class);
            }
        });
        serviceGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goIntent(GroupListActivity.class);
            }
        });



    }

    public void goIntent(Class c)
    {
        Intent i=new Intent(ServicesActivity.this,c);
        startActivity(i);
    }
}
