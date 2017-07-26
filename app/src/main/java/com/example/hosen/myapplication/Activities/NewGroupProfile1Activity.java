package com.example.hosen.myapplication.Activities;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.hosen.myapplication.R;

public class NewGroupProfile1Activity extends AppCompatActivity {
    ProgressBar pb;
    TextView profileNextBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_group_profile1);
        pb=(ProgressBar)findViewById(R.id.progressBar5);
       // pb.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#019a01")));
        profileNextBt = (TextView)findViewById(R.id.profileNextTv);
        profileNextBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(NewGroupProfile1Activity.this,NewGroupFlightServiceActivity.class);
                startActivity(i);
            }
        });
    }
}
