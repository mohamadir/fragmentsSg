package com.example.hosen.myapplication.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.hosen.myapplication.Adapters.GroupLIstAdapter;
import com.example.hosen.myapplication.Adapters.GroupLIstAdapter2;
import com.example.hosen.myapplication.Classes.MySingleton;
import com.example.hosen.myapplication.Models.GroupInList;
import com.example.hosen.myapplication.R;
import com.google.gson.Gson;
import com.twotoasters.jazzylistview.JazzyHelper;
import com.twotoasters.jazzylistview.JazzyListView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class GroupListActivity extends AppCompatActivity {
    // Array of strings...

    public ArrayList<GroupInList> groupList;
    JazzyListView listView;
    Button signBt;
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_list);
        signBt=(Button )findViewById(R.id.groupLIstSignInBt);
        groupList=new ArrayList<GroupInList>();

       signBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent (GroupListActivity.this,SignInActivity.class);
                startActivity(i);
            }
        });
        // Create the GroupInList List Adapter

        listView = (JazzyListView) findViewById(R.id.grouoLv);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                SharedPreferences.Editor editor=getSharedPreferences("SnapGroup",MODE_PRIVATE).edit();
                Log.i("GROOPY2",groupList.get(i).getImage());
                editor.putString("gImage", groupList.get(i).getImage());
                editor.putString("gDescription", groupList.get(i).getDescritption());
                editor.putString("gDestination", groupList.get(i).getDestination());
                editor.putString("gId", String.valueOf(groupList.get(i).get_id()));
                editor.putString("gOrigin", groupList.get(i).getOrigin());
                editor.putString("gStart", groupList.get(i).getStart_date());
                editor.putString("gEnd", groupList.get(i).getEnd_date());
                editor.putString("gTitle", groupList.get(i).getTitle());
                editor.commit();



                Intent intent=new Intent(GroupListActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        String type=getIntent().getStringExtra("date");
        String dateFrom=getIntent().getStringExtra("dateFrom");
        String dateTo=getIntent().getStringExtra("dateTo");

       getGroupsByHotelsRequest(dateFrom,dateTo);
        if(!(type==null || type.equals(""))) {
            if(type.equals("ok")) {
                getGroupsByHotelsRequest(dateFrom, dateTo);
            }
            else
                getGroupsRequests();
        }


        /*
        *
        * Copy here
        *
        * */
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);

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
                        GroupLIstAdapter2 glAdapter= new GroupLIstAdapter2(GroupListActivity.this,groupList);
                        // Set the adapter to the list
                        listView.setAdapter(glAdapter);
        /*listView.setTransitionEffect(JazzyHelper.TWIRL);
        listView.setTranscriptMode(JazzyHelper.CARDS);*/
                     // a7la    listView.setTransitionEffect(JazzyHelper.ZIPPER);
                     listView.setTransitionEffect(JazzyHelper.WAVE);

                        listView.setScrollBarFadeDuration(100);

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("groupsGet",error.getMessage().toString()+" ");
                    }

                });

        MySingleton.getInstance(GroupListActivity.this).addToRequestQueue(jsObjRequest);

    }
    public void getGroupsByHotelsRequest(String dateFrom,String dateTo){

        String url = "http://172.104.150.56/api/hotels"+"/"+dateFrom+"/"+dateTo;


        JsonArrayRequest jsObjRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        //int _id, String title, String descritption, String image, String origin, String destination, String start_date, String end_date
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
//                        Log.i("Length",groupList.get(3).getOrigin().toString());
                        GroupLIstAdapter2 glAdapter= new GroupLIstAdapter2(GroupListActivity.this,groupList);
                        // Set the adapter to the list
                        listView.setAdapter(glAdapter);
        /*listView.setTransitionEffect(JazzyHelper.TWIRL);
        listView.setTranscriptMode(JazzyHelper.CARDS);*/
                        // a7la    listView.setTransitionEffect(JazzyHelper.ZIPPER);
                        listView.setTransitionEffect(JazzyHelper.WAVE);

                        listView.setScrollBarFadeDuration(100);

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }

                });

        MySingleton.getInstance(GroupListActivity.this).addToRequestQueue(jsObjRequest);

    }


}
