package com.example.hosen.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.security.acl.Group;

public class GroupListActivity extends AppCompatActivity {
    // Array of strings...
    String[] title = {"Saint Zohar Group", "West School", "University college of London", "Famillies and Children","Saint Zohar Group"};
    Button signBt;
    String[] reviews = {"12", "56", "78", "89","98"};
    String[] rating = {"4", "5", "2", "3","4"};
    Integer[] imageId = {R.drawable.img, R.drawable.img, R.drawable.img, R.drawable.img, R.drawable.img};
    String[] destination = {"London to Israel", "London to Israel", "London to Israel", "London to Israel","London to Israel",};
    String[] description = {"Visit the new land", "Visit the new land", "Visit the new land", "Visit the new land","Visit the new land"};
    String[] strating_date = {"02/09", "07/08", "02/10", "06/02", "06/02"};
    String[] trip_duration = {"5", "6", "7", "10", "10"};
    String[] member_count = {"80", "70" ,"60", "95", "95"};
    String[][] services = {{"true", "true", "true", "false", "false", "false"}, {"true", "true", "true", "false", "false", "false"}, {"true", "true", "true", "false", "false"}, {"true", "true", "true", "false", "false", "false"}};
    String[] time_left = {"12 Days 08:14", "12 Days 08:14", "12 Days 08:14", "12 Days 08:14", "12 Days 08:14"};
    String[] price = {"500", "250", "190", "560", "560"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_list);
        signBt=(Button )findViewById(R.id.groupLIstSignInBt);
        signBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent (GroupListActivity.this,SignInActivity.class);
                startActivity(i);
            }
        });
        // Create the Group List Adapter
        CustomGroupList adapter = new CustomGroupList(this,
                title,
                reviews,
                rating,
                imageId,
                destination,
                description,
                strating_date,
                trip_duration,
                member_count,
                services,
                time_left,
                price
        );
        // Get the List View form the layout
        ListView listView = (ListView) findViewById(R.id.grouoLv);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(GroupListActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        // Set the adapter to the list
        listView.setAdapter(adapter);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);

    }


}
