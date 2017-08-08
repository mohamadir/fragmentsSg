package com.snapgroup.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.snapgroup.Adapters.GroupLIstAdapter2;
import com.snapgroup.Adapters.HLVAdapter;
import com.snapgroup.Classes.MySingleton;
import com.snapgroup.Models.GroupInList;
import com.snapgroup.R;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;
import com.twotoasters.jazzylistview.JazzyHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;

public class ProfileMemberActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;
    public ArrayList<String> alName;
    public ArrayList<String> alImage;
    TextView profile_member_firstname,profile_member_lastname,profile_member_email,profile_member_role;
    ImageView profile_image_member;

    public String memberId="33";
    TextView signout;
    public ArrayList<GroupInList> groupList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_member);

        //alName = new ArrayList<>(Arrays.asList("Group Name", "Group Name", "Group Name", "Group Name", "Group Name", "Group Name", "Group Name", "Group Name"));
       // alImage = new ArrayList<>(Arrays.asList(R.drawable.person2, R.drawable.person2, R.drawable.person2, R.drawable.person2, R.drawable.person2, R.drawable.person2, R.drawable.person2, R.drawable.person2));
        alName = new ArrayList<String>();
        alImage = new ArrayList<String>();
        groupList=new ArrayList<GroupInList>();
        // Calling the RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);


        profile_member_email = (TextView) findViewById(R.id.profile_member_email);
        profile_member_firstname = (TextView) findViewById(R.id.profile_member_firstname);
        profile_member_lastname = (TextView) findViewById(R.id.profile_member_lastname);
        profile_image_member = (ImageView) findViewById(R.id.profile_image_member) ;
        profile_member_role = (TextView) findViewById(R.id.profile_member_role) ;


        SharedPreferences settings=getSharedPreferences("UserLog",MODE_PRIVATE);
        String image = settings.getString("profile_image","");
        profile_member_role.setText(settings.getString("role",""));
        profile_member_firstname.setText(settings.getString("first_name","noName"));
        profile_member_lastname.setText(settings.getString("last_name","noName"));
        profile_member_email.setText(settings.getString("email","noEmail"));
        Picasso.with(ProfileMemberActivity.this).load(settings.getString("profile_image","https://uploads.toptal.io/user/photo/15919/small_profile_photo.jpg")).into(profile_image_member);
        getGroupsRequests();

        mRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });




        // The number of Columns


    }


    public void getGroupsRequests() {


        SharedPreferences settings=getSharedPreferences("UserLog",MODE_PRIVATE);
        String id = settings.getString("id","-1");
        memberId = id;
        String url = "http://172.104.150.56/api/groups";
        String url2 = "http://172.104.150.56/api/members/"+memberId;
        Log.i("asd",url2);
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url2, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        //int _id, String title, String descritption, String image, String origin, String destination, String start_date, String end_date
                       // JSONArray groupsArray = response;
                        try {
                            JSONObject groupsObject = response;
                            JSONArray groupsArray = groupsObject.getJSONArray("groups");
                            JSONObject profileINfoaa = groupsObject.getJSONObject("profile");
//                            profile_member_lastname.setText(profileINfoaa.getString("last_name").toString());
//                            profile_member_firstname.setText(profileINfoaa.getString("first_name").toString());
//                            profile_member_email.setText(groupsObject.getString("email"));
                            for (int i = 0; i < groupsArray.length(); i++) {

                                //Log.i("Asd","Asd");
                                alImage.add(i,groupsArray.getJSONObject(i).getString("image").toString());
                                alName.add(i,groupsArray.getJSONObject(i).getString("title").toString());
                                Log.i("Asd",groupsArray.getJSONObject(i).getString("id").toString());
                                Log.i("Asd",groupsArray.getJSONObject(i).getString("image").toString());


                                groupList.add(i, new GroupInList(Integer.parseInt(
                                        groupsArray.getJSONObject(i).getString("id").toString()),// group ID
                                        groupsArray.getJSONObject(i).getString("title").toString(),// Group Title
                                        groupsArray.getJSONObject(i).getString("description").toString(), // Group description
                                        groupsArray.getJSONObject(i).getString("image").toString(),// Group image
                                        groupsArray.getJSONObject(i).getString("origin").toString(),// Group origin
                                        groupsArray.getJSONObject(i).getString("destination").toString(),// Group destination
                                        groupsArray.getJSONObject(i).getString("start_date").toString(),// Group start date
                                        groupsArray.getJSONObject(i).getString("end_date").toString()));// Group end date





                            }
                        } catch (JSONException e)
                        {
                            e.printStackTrace();
                        }


                        mLayoutManager = new LinearLayoutManager(ProfileMemberActivity.this, LinearLayoutManager.HORIZONTAL, false);
                        mRecyclerView.setLayoutManager(mLayoutManager);
                        mAdapter = new HLVAdapter(ProfileMemberActivity.this, alName, alImage,groupList);
                        mRecyclerView.setAdapter(mAdapter);

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Log.i("groupsGet", error.getMessage().toString() + " ");
                    }

                });

        MySingleton.getInstance(ProfileMemberActivity.this).addToRequestQueue(jsObjRequest);

    }
}
