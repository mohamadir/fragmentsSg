package com.snapgroup.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.snapgroup.R;

import java.util.Map;

public class NewGroupProfile1Activity extends AppCompatActivity {
    ProgressBar pb;
    TextView profileNextBt;
    EditText groupNameEt,groupDescriptionEt;

    public ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_group_profile1);
        pb=(ProgressBar)findViewById(R.id.progressBar5);
        groupNameEt=(EditText)findViewById(R.id.NewGroupProfile1_nameEt);
        groupDescriptionEt=(EditText)findViewById(R.id.NewGroupProfile1_descriptionEt);
       // pb.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#019a01")));
        //printSharedPreferences();
        profileNextBt = (TextView)findViewById(R.id.profileNextTv);
        profileNextBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               onNextClick();
            }
        });
    }

    private void onNextClick() {
        SharedPreferences.Editor editor=getSharedPreferences("NewGroup",MODE_PRIVATE).edit();
        String groupName=groupNameEt.getText().toString();
        String groupDescription=groupDescriptionEt.getText().toString();
        if(!groupName.equals(""))
            editor.putString("title",groupName);
        else
            editor.putString("title","");
        if(!groupDescription.equals(""))
            editor.putString("description",groupDescription);
        else
            editor.putString("description","");
        editor.commit();
        Intent i = new Intent(NewGroupProfile1Activity.this,NewGroupProfile2Activity.class);
        startActivity(i);
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
    }

    private void printSharedPreferences() {
        SharedPreferences NewGroupSp=this.getSharedPreferences("NewGroup",MODE_PRIVATE);
        Map<String, ?> allEntries = NewGroupSp.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            Log.i("MAPP", entry.getKey() + ": " + entry.getValue().toString());
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);

    }
}
