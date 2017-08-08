package com.snapgroup.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.snapgroup.R;

import java.util.Map;

public class NewGroupHotelsServiceActivity extends AppCompatActivity {

    TextView nextBT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_group_hotels_service);
        nextBT=(TextView)findViewById(R.id.hotelsActivity_nextBt);
//        printSharedPreferences();
        nextBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(NewGroupHotelsServiceActivity.this
                        ,NewGroupTransportationActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);

    }
    private void printSharedPreferences() {
        SharedPreferences NewGroupSp=this.getSharedPreferences("NewGroup",MODE_PRIVATE);
        Map<String, ?> allEntries = NewGroupSp.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            Log.i("MAPP", entry.getKey() + ": " + entry.getValue().toString());
        }
    }
}
