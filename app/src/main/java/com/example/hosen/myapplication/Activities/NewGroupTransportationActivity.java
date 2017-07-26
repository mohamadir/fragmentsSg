package com.example.hosen.myapplication.Activities;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.hosen.myapplication.R;

public class NewGroupTransportationActivity extends AppCompatActivity {

    TextView vipairport,airCOndtion,wifiTv,accessDisabled,tvTv,onlyAthTv,driverTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_group_transportation);
        vipairport = (TextView)findViewById(R.id.vipAirportTv);
        airCOndtion = (TextView)findViewById(R.id.air_conditionTv);
        wifiTv = (TextView)findViewById(R.id.wifiTV);
        accessDisabled = (TextView)findViewById(R.id.authCOmpanyTv);
        tvTv = (TextView)findViewById(R.id.tvTv);
        onlyAthTv = (TextView)findViewById(R.id.onlyAthTv);
        driverTv = (TextView)findViewById(R.id.driverTV);
        vipairport.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(vipairport.getCurrentTextColor()!= Color.parseColor("#fc8130")){
                    vipairport.setTextColor(Color.parseColor("#fc8130"));
                }
                else
                    vipairport.setTextColor(Color.parseColor("#009900"));
            }
        });
        vipairport.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(vipairport.getCurrentTextColor()!= Color.parseColor("#fc8130")){
                    vipairport.setTextColor(Color.parseColor("#fc8130"));
                }
                else
                    vipairport.setTextColor(Color.parseColor("#009900"));
            }
        });
        wifiTv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(wifiTv.getCurrentTextColor()!= Color.parseColor("#fc8130")){
                    wifiTv.setTextColor(Color.parseColor("#fc8130"));
                }
                else
                    wifiTv.setTextColor(Color.parseColor("#009900"));
            }
        });
        airCOndtion.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(airCOndtion.getCurrentTextColor()!= Color.parseColor("#fc8130")){
                    airCOndtion.setTextColor(Color.parseColor("#fc8130"));
                }
                else
                    airCOndtion.setTextColor(Color.parseColor("#009900"));
            }
        });
        accessDisabled.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(accessDisabled.getCurrentTextColor()!= Color.parseColor("#fc8130")){
                    accessDisabled.setTextColor(Color.parseColor("#fc8130"));
                }
                else
                    accessDisabled.setTextColor(Color.parseColor("#009900"));
            }
        });
        tvTv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(tvTv.getCurrentTextColor()!= Color.parseColor("#fc8130")){
                    tvTv.setTextColor(Color.parseColor("#fc8130"));
                }
                else
                    tvTv.setTextColor(Color.parseColor("#009900"));
            }
        });
        onlyAthTv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(onlyAthTv.getCurrentTextColor()!= Color.parseColor("#fc8130")){
                    onlyAthTv.setTextColor(Color.parseColor("#fc8130"));
                }
                else
                    onlyAthTv.setTextColor(Color.parseColor("#009900"));
            }
        });
        driverTv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(driverTv.getCurrentTextColor()!= Color.parseColor("#fc8130")){
                    driverTv.setTextColor(Color.parseColor("#fc8130"));
                }
                else
                    driverTv.setTextColor(Color.parseColor("#009900"));
            }
        });



    }
}