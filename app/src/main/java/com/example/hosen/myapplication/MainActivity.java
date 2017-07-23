package com.example.hosen.myapplication;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import  android.app.Fragment;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    Fragment frt;
    TextView mapTv,planTv;
    Fragment blankFt;
    Button bt,bt2,bt3,flyBt,docsBt,memberBt
            ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapTv=(TextView)findViewById(R.id.mapTv);
        planTv=(TextView)findViewById(R.id.planTv);
        getFragmentManager().beginTransaction().replace(R.id.fragment1,new PlanFragment()).addToBackStack(null).commit();
         bt =(Button)findViewById(R.id.mButton);
         bt2 =(Button)findViewById(R.id.button15);
         bt3 =(Button)findViewById(R.id.docsBt);
         docsBt=(Button)findViewById(R.id.docsBt);
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
        bt.setOnClickListener(new View.OnClickListener() {
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

             /* frt=new DetailsFragmetn();
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

    }
}
