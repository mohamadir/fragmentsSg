package com.example.hosen.myapplication.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import  android.app.Fragment;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.hosen.myapplication.Fragments.BlankFragment;
import com.example.hosen.myapplication.Fragments.ChatFragment;
import com.example.hosen.myapplication.Fragments.DetailsFragment;
import com.example.hosen.myapplication.Fragments.DocsFragment;
import com.example.hosen.myapplication.Fragments.MembersFragment;
import com.example.hosen.myapplication.Fragments.PaymentsCreditFragemnt;
import com.example.hosen.myapplication.Fragments.PlanFragment;
import com.example.hosen.myapplication.Fragments.VotesFragment;
import com.example.hosen.myapplication.Models.GroupInList;
import com.example.hosen.myapplication.R;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    Fragment frt;
    TextView mapTv,planTv;
    Intent intent;
    Bundle bundle;
    Fragment blankFt;
    GroupInList group;
    Button payBt,bt2,bt3,flyBt,mapp,docsBt,memberBt,votesBt,chatBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapTv=(TextView)findViewById(R.id.mapTv);
        planTv=(TextView)findViewById(R.id.planTv);
        mapp =(Button)findViewById(R.id.mButton);
        group= (GroupInList) getIntent().getSerializableExtra("group");






        getFragmentManager().beginTransaction().replace(R.id.fragment1,new DetailsFragment()).addToBackStack(null).commit();
        payBt =(Button)findViewById(R.id.payBt);
        bt2 =(Button)findViewById(R.id.button15);
        bt3 =(Button)findViewById(R.id.docsBt);
        docsBt=(Button)findViewById(R.id.docsBt);
        votesBt=(Button)findViewById(R.id.votesBt);
        chatBt=(Button)findViewById(R.id.chatBt);
        //overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);


        intent = getIntent();
        mapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  i = new Intent(MainActivity.this,MapActivity.class);
                startActivity(i);
            }

        });
        chatBt.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  getFragmentManager().beginTransaction().replace(R.id.fragment1,new ChatFragment()).addToBackStack(null).commit();

              }
          }
        );
        votesBt.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               getFragmentManager().beginTransaction().replace(R.id.fragment1,new VotesFragment()).addToBackStack(null).commit();

           }
       }
        );
        memberBt=(Button)findViewById(R.id.memberBt);
        memberBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.fragment1,new MembersFragment()).addToBackStack(null).commit();

            }
        });






        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Fragment-i","im in listener1");
               /* frt=new BlankFragment();
                FragmentManager fm=getFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                ft.replace(R.id.fragment1,frt);
                ft.commit();*/
               /* String f1="f1";
                android.support.v4.app.Fragment fragment = getSupportFragmentManager().findFragmentByTag(f1);
                getSupportFragmentManager().beginTransaction().remove(fragment).commit();
             */
                getFragmentManager().beginTransaction().replace(R.id.fragment1,new DocsFragment()).addToBackStack(null).commit();

            }
        });
        payBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Fragment-i","im in listener2");
               /* frt=new BlankFragment();
                FragmentManager fm=getFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                ft.replace(R.id.fragment1,frt);
                ft.commit();*/
               /* String f1="f1";
                android.support.v4.app.Fragment fragment = getSupportFragmentManager().findFragmentByTag(f1);
                getSupportFragmentManager().beginTransaction().remove(fragment).commit();
             */
                getFragmentManager().beginTransaction().replace(R.id.fragment1,new BlankFragment()).addToBackStack(null).commit();

            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Log.i("Fragment-i","im in listener3");

             /* frt=new DetailsFragment();
              FragmentManager fm=getFragmentManager();
              FragmentTransaction ft=fm.beginTransaction();
              ft.replace(R.id.fragment1,frt);
              ft.commit();*/
              getFragmentManager().beginTransaction().replace(R.id.fragment1,new PlanFragment()).addToBackStack(null).commit();

          }
      }
        );

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
    public void flyBtClick(){
        Log.i("fly","im pressed");

    }

    @Override
    public void onBackPressed() {
        Intent i=new Intent(MainActivity.this, GroupListActivity.class);
        startActivity(i);
    }
}
