package com.example.hosen.myapplication.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.example.hosen.myapplication.R;

public class ServicesActivity extends AppCompatActivity {
    Button serviceFly,serviceTrans,serviceGuide,serviceHotels,servicePlaces,serviceRests,createNewGroupBt,signInBt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        serviceFly=(Button)findViewById(R.id.serviceFlightBt);
        serviceTrans=(Button)findViewById(R.id.serviceTransportBt);
        serviceGuide=(Button)findViewById(R.id.serviceGUideBt);
        serviceHotels=(Button)findViewById(R.id.serviceHotelsBt);
        SharedPreferences.Editor editor=getSharedPreferences("NewGroup",MODE_PRIVATE).edit();
        editor.clear().commit();
        servicePlaces=(Button)findViewById(R.id.servicePlacesBt);
        signInBt=(Button)findViewById(R.id.ServicesActivity_SignInBt);
        if(isSigned())
            signInBt.setText("SIGN OUT");
        else {
            Log.i("signed","im here");
            signInBt.setText("SIGN IN");

        }
        signInBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(signInBt.getText().equals("SIGN IN")) {
                    Intent i = new Intent(ServicesActivity.this, SignInActivity.class);
                    startActivity(i);
                }
                else
                    signOut();
            }
        });
        serviceRests=(Button)findViewById(R.id.serviceRestaurantBt);
        createNewGroupBt=(Button)findViewById(R.id.createNewGroupBt);
        createNewGroupBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(ServicesActivity.this,NewGroupSettingsPageAtivity.class);
                startActivity(i);
            }
        });
        serviceFly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goIntent(GroupListActivity.class);
            }
        });
        serviceTrans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goIntent(GroupListActivity.class);
            }
        });
        servicePlaces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goIntent(GroupListActivity.class);
            }
        });
        serviceRests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goIntent(GroupListActivity.class);
            }
        });
        serviceHotels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(ServicesActivity.this,HotelServiceFilterActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_up,R.anim.slide_down);

            }
        });
        serviceGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goIntent(GroupListActivity.class);
            }
        });



    }

    // method to intent transport
    public void goIntent(Class c)
    {
        Intent i=new Intent(ServicesActivity.this,c);
        startActivity(i);
    }

    // check from the sharedPreferences if someone is signed now
    public boolean isSigned(){
        SharedPreferences settings=this.getSharedPreferences("UserLog",MODE_PRIVATE);
        String signed = settings.getString("isSigned","false");
        if(signed.equals("false"))
            return false;
        else
            return true;
    }

    // sign Out method ( changing the text of the button to sign in and update the sharedPreferences)
    public void signOut(){
        signInBt.setText("SIGN IN");
        SharedPreferences.Editor editor=getSharedPreferences("UserLog",MODE_PRIVATE).edit();
        editor.putString("isSigned","false");
        editor.commit();
    }

    // ovveride the onResume method in order to refresh the data in SharedPreferences
    @Override
    protected void onResume() {
        if(isSigned())
            signInBt.setText("SIGN OUT");
        else {
            Log.i("signed","im here");
            signInBt.setText("SIGN IN");
        }
        super.onResume();
    }
}
