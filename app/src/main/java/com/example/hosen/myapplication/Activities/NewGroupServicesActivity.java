package com.example.hosen.myapplication.Activities;


import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.hosen.myapplication.R;


public class NewGroupServicesActivity extends AppCompatActivity {

    TextView selectAll,resturant,hotel,transport,tour_guide,site_places,activity,flights,yesText,noText,nextBt;
    boolean [] array = new boolean[7];

    public void trueFunc(boolean [] array)
    {
        for(int i =0 ; i<7 ; i++)
        {
            array[i] = true ;
        }

    }
    public void falseFunc(boolean [] array)
    {
        for(int i =0 ; i<7 ; i++)
        {
            array[i] = false ;
        }

    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_group_services);
        yesText = (TextView) findViewById(R.id.yesTextView);
        flights = (TextView) findViewById(R.id.flightsTextView) ;
        noText = (TextView) findViewById(R.id.noTextView);
        selectAll=(TextView)findViewById(R.id.selectAllTv);
        resturant = (TextView)findViewById(R.id.restarurantsTextview);
        hotel = (TextView)findViewById(R.id.hotelTextView);
        transport = (TextView)findViewById(R.id.transportTextView);
        tour_guide = (TextView)findViewById(R.id.tour_GuideTextView);
        site_places = (TextView)findViewById(R.id.sites_placesTextView);
        activity = (TextView)findViewById(R.id.activtyTextView);
        nextBt = (TextView)findViewById(R.id.services_next_tv);
        nextBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i   = new Intent(NewGroupServicesActivity.this,NewGroupTransportationActivity.class);
                startActivity(i);
            }
        });
        falseFunc(array);
        selectAll.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View view) {
                if(selectAll.getCurrentTextColor()!=Color.parseColor("#fc8130")) {
                    trueFunc(array);
                    selectAll.setTextColor(Color.parseColor("#fc8130"));
                    flights.setTextColor(Color.parseColor("#fc8130"));
                    hotel.setTextColor(Color.parseColor("#fc8130"));
                    resturant.setTextColor(Color.parseColor("#fc8130"));
                    tour_guide.setTextColor(Color.parseColor("#fc8130"));
                    transport.setTextColor(Color.parseColor("#fc8130"));
                    site_places.setTextColor(Color.parseColor("#fc8130"));
                    activity.setTextColor(Color.parseColor("#fc8130"));
                }

                else{
                    falseFunc(array);
                    selectAll.setTextColor(Color.parseColor("#009900"));
                    flights.setTextColor(Color.parseColor("#009900"));
                    hotel.setTextColor(Color.parseColor("#009900"));
                    resturant.setTextColor(Color.parseColor("#009900"));
                    tour_guide.setTextColor(Color.parseColor("#009900"));
                    transport.setTextColor(Color.parseColor("#009900"));
                    site_places.setTextColor(Color.parseColor("#009900"));
                    activity.setTextColor(Color.parseColor("#009900"));

                }

            }
        });
        //selectAll.setOnClickListener(new View.OnClickListener() {

        flights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flights.getCurrentTextColor() != Color.parseColor("#fc8130")) {
                    array[0] = true;
                    flights.setTextColor(Color.parseColor("#fc8130"));
                } else {
                    flights.setTextColor(Color.parseColor("#009900"));
                    array[0]=false;
                }
            }
        });
        resturant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(resturant.getCurrentTextColor()!=Color.parseColor("#fc8130")) {
                    resturant.setTextColor(Color.parseColor("#fc8130"));
                    array[5] = true;
                }
                else{
                    resturant.setTextColor(Color.parseColor("#009900"));
                    array[5] = false;}

            }
        });
        hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(hotel.getCurrentTextColor()!=Color.parseColor("#fc8130")) {
                    hotel.setTextColor(Color.parseColor("#fc8130"));
                    array[1] = true;
                }
                else{
                    hotel.setTextColor(Color.parseColor("#009900"));
                    array[1] = false;}
            }
        });
        transport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(transport.getCurrentTextColor()!=Color.parseColor("#fc8130")) {
                    transport.setTextColor(Color.parseColor("#fc8130"));
                    array[2] = true;
                }
                else{
                    transport.setTextColor(Color.parseColor("#009900"));
                    array[2] = false;}
            }
        });
        tour_guide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tour_guide.getCurrentTextColor()!=Color.parseColor("#fc8130")) {
                    tour_guide.setTextColor(Color.parseColor("#fc8130"));
                    array[3] = true;
                }
                else{
                    tour_guide.setTextColor(Color.parseColor("#009900"));
                    array[3] = false;}
            }
        });
        activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(activity.getCurrentTextColor()!=Color.parseColor("#fc8130")) {
                    activity.setTextColor(Color.parseColor("#fc8130"));
                    array[6] = true;
                }
                else{
                    activity.setTextColor(Color.parseColor("#009900"));
                    array[6] = false;}

            }
        });
        site_places.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(site_places.getCurrentTextColor()!=Color.parseColor("#fc8130")) {
                    site_places.setTextColor(Color.parseColor("#fc8130"));
                    array[4] = true;

                }
                else{
                    site_places.setTextColor(Color.parseColor("#009900"));
                    array[4] = false;

                }
            }
        });
        yesText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(yesText.getCurrentTextColor()!=Color.parseColor("#fc8130")) {
                    yesText.setTextColor(Color.parseColor("#fc8130"));
                    noText.setTextColor(Color.parseColor("#009900"));
                }
                else{
                    yesText.setTextColor(Color.parseColor("#009900"));

                }
            }
        });
        noText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(noText.getCurrentTextColor()!=Color.parseColor("#fc8130")) {
                    noText.setTextColor(Color.parseColor("#fc8130"));
                    yesText.setTextColor(Color.parseColor("#009900"));
                }
                else{
                    noText.setTextColor(Color.parseColor("#009900"));

                }
            }
        });


    }
}