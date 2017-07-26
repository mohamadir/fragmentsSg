package com.example.hosen.myapplication.Activities;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.hosen.myapplication.R;

public class NewGroupSettingsPageAtivity extends AppCompatActivity {
    TextView nextTv;
    ProgressBar pb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_group_settings_page_ativity);
        pb=(ProgressBar)findViewById(R.id.progressBar4);
        //pb.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#019a01")));
        nextTv =(TextView)findViewById(R.id.SettingsNextTv);

        nextTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(NewGroupSettingsPageAtivity.this,NewGroupProfile1Activity.class);
                startActivity(i);
                //overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);

            }
        });

    }
}
