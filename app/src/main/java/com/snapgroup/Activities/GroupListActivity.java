package com.snapgroup.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.snapgroup.Adapters.GroupLIstAdapter;
import com.snapgroup.Adapters.GroupLIstAdapter2;
import com.snapgroup.Classes.MySingleton;
import com.snapgroup.Models.GroupInList;
import com.snapgroup.R;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.twotoasters.jazzylistview.JazzyHelper;
import com.twotoasters.jazzylistview.JazzyListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupListActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    // Array of strings...

    public ArrayList<GroupInList> groupList;
    JazzyListView listView;
    Button groupsButton,invitationsButton;
    Button inviteBt;
    TextView groupListActivity_myGroupsTv,groupListActivity_invitesFilterTv;
    Toolbar mToolBar;
    String token;
    Button menuBt;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    NavigationView navigationView;
    Button signInBt;
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_list);
        groupList=new ArrayList<GroupInList>();
        Log.i("onCreateMethod","hi");

        // Create the GroupInList List Adapter
       groupsButton = (Button) findViewById(R.id.groupsButton);
       invitationsButton=(Button)findViewById(R.id.invitationsButton);
       inviteBt = (Button) findViewById(R.id.button27);
       inviteBt.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Log.i("clicked","im here");
               scrollToBottom();
           }
       });
       groupsButton.setVisibility(View.INVISIBLE);
       invitationsButton.setVisibility(View.INVISIBLE);
       signInBt=(Button)findViewById(R.id.GroupListActivity_SignInBt);
       if(isSigned()) {
           SharedPreferences userLogInfo=this.getSharedPreferences("UserLog",MODE_PRIVATE);
           token= userLogInfo.getString("token","");
           Log.i("signedIn","ok");
           requsetidByToken();
       }
       menuBt=(Button)findViewById(R.id.menuBt);
       groupListActivity_myGroupsTv = (TextView)findViewById(R.id.groupListActivity_myGroupsTv);
       groupListActivity_invitesFilterTv = (TextView)findViewById(R.id.groupListActivity_invitesFilterTv);
       mDrawerLayout=(DrawerLayout)findViewById(R.id.drawerLayout);
       mToggle=new ActionBarDrawerToggle(GroupListActivity.this,mDrawerLayout,R.string.open,R.string.close);
       mDrawerLayout.addDrawerListener(mToggle);
       mToggle.syncState();
       navigationView= (NavigationView) findViewById(R.id.nav_view);
       navigationView.setNavigationItemSelectedListener(this);
       groupListActivity_myGroupsTv.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               groupsButton.setVisibility(View.INVISIBLE);
               invitationsButton.setVisibility(View.VISIBLE);
           }
       });
       groupListActivity_invitesFilterTv.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               groupsButton.setVisibility(View.VISIBLE);
               invitationsButton.setVisibility(View.INVISIBLE);
           }
       });
       signInBt.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if(signInBt.getText().equals("SIGN IN")) {
                   Intent i = new Intent(GroupListActivity.this, SignInActivity.class);
                   startActivity(i);
               }
               else{
                   setNavigateViewSignOut();
                   signOut();}
           }
       });
       if(isSigned())
           signInBt.setText("SIGN OUT");
       else {
           Log.i("signed","im here");
           signInBt.setText("SIGN IN");

       }
        listView = (JazzyListView) findViewById(R.id.grouoLv1);
       //listView.setItemClick
       listView.setDivider(null);
       listView.setDividerHeight(0);
        listView.setOnItemClickListener(new  AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("group_func_onclick", "clickedd");
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
       menuBt.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               mDrawerLayout.openDrawer(Gravity.LEFT);
           }
       });
      /* getGroupsByHotelsRequest(dateFrom,dateTo);
        if(!(type==null || type.equals(""))) {
            if(type.equals("ok")) {
                getGroupsByHotelsRequest(dateFrom, dateTo);
            }
            else

        }*/
       getGroupsRequests();
       listView.smoothScrollToPosition(5);
       if(getIntent()!=null)
           if((getIntent().getExtras()!=null) &&getIntent().getExtras().getString("Intent").equals("Finish"))
           {
               scrollToBottom();
           }



        /*
        *
        * Copy here
        *
        * */

          overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);

    }

    private void scrollToBottom() {
        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(3000);
                    listView.smoothScrollToPosition(listView.getScrollBarSize());

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }


    private void setNavigateViewSignOut() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View viewy=navigationView.getHeaderView(0);
        TextView name=(TextView)viewy.findViewById(R.id.nav_header_nameTv);
        TextView email=(TextView)viewy.findViewById(R.id.nav_header_email);
        TextView signOut=(TextView)viewy.findViewById(R.id.nav_header_SignOut);
        ImageView profileIv=(ImageView)viewy.findViewById(R.id.nav_header_profileImg);
        name.setText("No User Loged");
        email.setText("");
        signOut.setVisibility(View.GONE);
        Picasso.with(getBaseContext()).load("http://science.cs.upc.edu/public/web/img/avatar/no_user_logo.png").into(profileIv);
        /*TextView tv=(TextView)viewy.findViewById(R.id.textTest);
        tv.setText("hi");*/
    }
    public boolean isSigned(){
        SharedPreferences settings=this.getSharedPreferences("UserLog",MODE_PRIVATE);
        String signed = settings.getString("isSigned","false");
        if(signed.equals("false"))
            return false;
        else
            return true;
    }
    @Override
    public void onBackPressed() {

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
                     listView.setTransitionEffect(JazzyHelper.TILT);

                        listView.setScrollBarFadeDuration(100);
                        listView.smoothScrollToPosition(11);



                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                     //   Log.i("groupsGet",error.getMessage().toString()+" ");
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

    public boolean onOptionsItemSelected(MenuItem item) {

        if(mToggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.my_profile_drawer_item)
        {
            Log.i("itemy","im clicked");
            Intent i=new Intent(GroupListActivity.this,ProfileMemberActivity.class);
            startActivity(i);
        }
        if(item.getItemId()==R.id.my_groups_drawer_item)
        {
            Log.i("itemy","im clicked");
            Intent i=new Intent(GroupListActivity.this,NewGroupProfile1Activity.class);
            startActivity(i);
        }
        return false;
    }

    public  void requsetidByToken() {
        String tokenUrl = "http://172.104.150.56/api/get_current_member";
        SharedPreferences settings2 =this.getSharedPreferences("UserLog", MODE_PRIVATE);
        final String token2 = settings2.getString("token", "");
        Log.i("requestByToken","hi");
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                tokenUrl,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("exceptiony",response.toString());

                        SharedPreferences.Editor editor=getSharedPreferences("UserLog",MODE_PRIVATE).edit();
                        try {
                            JSONObject profile=response.getJSONObject("profile");
                            editor.putString("id",response.getString("id").toString());
                            editor.putString("email",response.getString("email"));
                            editor.putString("first_name",profile.getString("first_name").toString());
                            editor.putString("last_name",profile.getString("last_name"));
                            editor.putString("profile_image",profile.getString("profile_image"));
                            editor.commit();
                            setNavigateViewSignIn();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.i("exceptiony",e.getMessage().toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("error_listener","error");
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "Bearer "+token2);


                return params;
            }


        };
        MySingleton.getInstance(GroupListActivity.this).addToRequestQueue(jsonObjectRequest);
        setNavigateViewSignIn();


    }
    private void setNavigateViewSignIn() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View viewy=navigationView.getHeaderView(0);
        SharedPreferences settings=this.getSharedPreferences("UserLog",MODE_PRIVATE);
        String firstNameString = settings.getString("first_name","No name");
        String lastNameString = settings.getString("last_name","No name");
        String emailString = settings.getString("email","No name");
        String profileImage= settings.getString("profile_image","No name");
        Log.i("detailsy",firstNameString+","+lastNameString+","+profileImage);
        TextView name=(TextView)viewy.findViewById(R.id.nav_header_nameTv);
        TextView email=(TextView)viewy.findViewById(R.id.nav_header_email);
        TextView signOut=(TextView)viewy.findViewById(R.id.nav_header_SignOut);
        ImageView profileIv=(ImageView)viewy.findViewById(R.id.nav_header_profileImg);
        name.setText(firstNameString+" "+lastNameString);
        email.setText(emailString);
        Picasso.with(getBaseContext()).load(profileImage).into(profileIv);
        signOut.setVisibility(View.VISIBLE);

        /*TextView tv=(TextView)viewy.findViewById(R.id.textTest);
        tv.setText("hi");*/
    }
    public void signOut(){
        signInBt.setText("SIGN IN");
        SharedPreferences.Editor editor=getSharedPreferences("UserLog",MODE_PRIVATE).edit();
        editor.clear();
        editor.putString("isSigned","false");
        editor.commit();
        Intent i=new Intent(GroupListActivity.this,SignInActivity.class);
        startActivity(i);
    }


}
