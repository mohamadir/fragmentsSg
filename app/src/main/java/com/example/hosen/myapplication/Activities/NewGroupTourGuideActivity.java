package com.example.hosen.myapplication.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.hosen.myapplication.R;

public class NewGroupTourGuideActivity extends AppCompatActivity {

    TextView nextBT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_group_tour_guide);
        nextBT=(TextView)findViewById(R.id.tour_guide_next_tv);
        nextBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(NewGroupTourGuideActivity.this,NewGroupServicesActivity.class);
                startActivity(i);
            }
        });

    }
}
