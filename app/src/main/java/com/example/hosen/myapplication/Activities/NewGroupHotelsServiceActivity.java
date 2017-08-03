package com.example.hosen.myapplication.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.hosen.myapplication.R;

import org.w3c.dom.Text;

public class NewGroupHotelsServiceActivity extends AppCompatActivity {

    TextView nextBT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_group_hotels_service);
        nextBT=(TextView)findViewById(R.id.hotelsActivity_nextBt);
        nextBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(NewGroupHotelsServiceActivity.this
                        ,NewGroupTransportationActivity.class);
                startActivity(i);
            }
        });
    }
}
