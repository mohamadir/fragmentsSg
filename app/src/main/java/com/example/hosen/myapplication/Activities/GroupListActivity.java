package com.example.hosen.myapplication.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.hosen.myapplication.Adapters.CustomGroupList;
import com.example.hosen.myapplication.Classes.DayPLan;
import com.example.hosen.myapplication.Classes.MySingleton;
import com.example.hosen.myapplication.Models.GroupInList;
import com.example.hosen.myapplication.R;
import com.twotoasters.jazzylistview.JazzyHelper;
import com.twotoasters.jazzylistview.JazzyListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.List;

public class GroupListActivity extends AppCompatActivity {
    // Array of strings...

    public List<GroupInList> groupList;

    String[] title = {"Saint Zohar GroupInList", "West School", "University college of London", "Famillies and Children","Saint Zohar GroupInList", "Famillies and Children","Saint Zohar GroupInList","Saint Zohar GroupInList", "Famillies and Children","Saint Zohar GroupInList","Saint Zohar GroupInList", "Famillies and Children","Saint Zohar GroupInList"};
    Button signBt;
    String[] reviews = { "78", "89","98", "89","98", "89","98", "89","98", "89","98", "89","98"};
    String[] rating = {"4", "5", "2", "3","4", "3","4", "3","4", "3","4", "3","4"};
    Integer[] imageId = {R.drawable.img, R.drawable.img, R.drawable.img, R.drawable.img, R.drawable.img, R.drawable.img, R.drawable.img, R.drawable.img, R.drawable.img, R.drawable.img, R.drawable.img, R.drawable.img, R.drawable.img};
    String[] destination = {"London to Israel", "London to Israel", "London to Israel", "London to Israel","London to Israel", "London to Israel","London to Israel", "London to Israel","London to Israel", "London to Israel","London to Israel","London to Israel","London to Israel"};
    String[] description = {"Visit the new land", "Visit the new land", "Visit the new land", "Visit the new land","Visit the new land", "Visit the new land","Visit the new land", "Visit the new land","Visit the new land", "Visit the new land","Visit the new land","Visit the new land","Visit the new land"};
    String[] strating_date = {"02/09", "07/08", "02/10", "06/02", "06/02", "06/02", "06/02", "06/02", "06/02", "06/02", "06/02", "06/02", "06/02"};
    String[] trip_duration = {"5", "6", "7", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10"};

    String[] member_count = {"80", "70" ,"60", "95", "95", "95", "95", "95", "95", "95", "95", "95", "95"};

    String[][] services = {{"true", "true", "true", "false", "false", "false"},
                           {"true", "true", "true", "false", "false", "false"},
            {"true", "true", "true", "fale", "false"},
            {"true", "true", "true", "false", "false", "false"},
            {"true", "true", "true", "fale", "false"},
            {"true", "true", "true", "false", "false", "false"},
            {"true", "true", "true", "fale", "false"},
            {"true", "true", "true", "false", "false", "false"},
            {"true", "true", "true", "fale", "false"},
            {"true", "true", "true", "false", "false", "false"}};

    String[] time_left = {"12 Days 08:14", "12 Days 08:14",
                          "12 Days 08:14", "12 Days 08:14",
            "12 Days 08:14", "12 Days 08:14",
            "12 Days 08:14", "12 Days 08:14",
            "12 Days 08:14", "12 Days 08:14",
            "12 Days 08:14", "12 Days 08:14",
            "12 Days 08:14"};

    String[] price = {"500", "250", "190", "560", "560", "560", "560", "560", "560", "560", "560", "560", "560"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_list);
        signBt=(Button )findViewById(R.id.groupLIstSignInBt);
        groupList=new ArrayList<GroupInList>();

        getGroupsRequests();
        signBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent (GroupListActivity.this,SignInActivity.class);
                startActivity(i);
            }
        });
        // Create the GroupInList List Adapter
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
        JazzyListView listView = (JazzyListView) findViewById(R.id.grouoLv);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(GroupListActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        // Set the adapter to the list
        listView.setAdapter(adapter);
        /*listView.setTransitionEffect(JazzyHelper.TWIRL);
        listView.setTranscriptMode(JazzyHelper.CARDS);*/
        listView.setTransitionEffect(JazzyHelper.ZIPPER);
        listView.setScrollBarFadeDuration(100);
        //overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);

    }

    @Override
    public void onBackPressed() {
        Intent i= new Intent(GroupListActivity.this,ServicesActivity.class);
        startActivity(i);
    }
    public void getGroupsRequests(){
        String url = "http://172.104.150.56/api/groups";


        JsonArrayRequest jsObjRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
    //int _id, String title, String descritption, String image, String origin, String destination, String start_date, String end_date
                    Log.i("groupsGet","kkkkkkkkkkkkk"+response+"");
                    JSONArray groupsArray = response;
                    for(int i=0;i<groupsArray.length();i++)
                    {
                        try {
                            groupList.add(i, new GroupInList(Integer.parseInt(
                                    groupsArray.getJSONObject(i).getString("id").toString()),// group ID
                                    groupsArray.getJSONObject(i).getString("title").toString(),// Group Title
                                    groupsArray.getJSONObject(i).getString("description").toString(), // Group description
                                    groupsArray.getJSONObject(i).getString("image").toString(),// Group image
                                    groupsArray.getJSONObject(i).getString("origin").toString(),// Group origin
                                    groupsArray.getJSONObject(i).getString("destination").toString(),// Group destination
                                    groupsArray.getJSONObject(i).getString("start_date").toString(),// Group start date
                                    groupsArray.getJSONObject(i).getString("end_date").toString()));// Group end date

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    Log.i("Length",groupList.get(3).getOrigin().toString());


                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("groupsGet",error.getMessage().toString()+" ");
                    }

                });

        MySingleton.getInstance(GroupListActivity.this).addToRequestQueue(jsObjRequest);

    }
}
