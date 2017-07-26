package com.example.hosen.myapplication.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.hosen.myapplication.R;

public class NewGroupFlightServiceActivity extends AppCompatActivity {
    TextView nextFlightBt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_group_flight_service);
        nextFlightBt=(TextView)findViewById(R.id.flightsNextTv);
        nextFlightBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(NewGroupFlightServiceActivity.this,NewGroupTourGuideActivity.class);
                startActivity(i);

            }
        });
    }
}
