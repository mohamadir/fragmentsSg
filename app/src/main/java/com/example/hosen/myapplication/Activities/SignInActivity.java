package com.example.hosen.myapplication.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.provider.SyncStateContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.hosen.myapplication.Classes.MySingleton;
import com.example.hosen.myapplication.R;

import org.json.JSONObject;

import java.util.HashMap;

public class SignInActivity extends AppCompatActivity {
    Button signInBt,signInVolleyBt;
    EditText usernameEt,passwordEt;
    String token;
    TextView signupTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        signupTv=(TextView) findViewById(R.id.signupTv);
        usernameEt=(EditText)findViewById(R.id.signInUsernameEt);
        passwordEt=(EditText)findViewById(R.id.signInPasswordEt);

        signInVolleyBt=(Button) findViewById(R.id.signInVolleyBt);
        signInVolleyBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                volleyRequest(usernameEt.getText().toString(),passwordEt.getText().toString());
            }
        });
        signupTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(SignInActivity.this,RegisterActivity.class);
                startActivity(i);
            }
        });
    }
    public void volleyRequest(final String username,final String password){
        StringRequest sr = new StringRequest(Request.Method.POST, "http://172.104.150.56/api/login", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("responsy",response);
                AlertDialog.Builder builder = new AlertDialog.Builder(SignInActivity.this);
                builder.setMessage("Login Successfully")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("myError",error.getMessage().toString());
                AlertDialog.Builder builder = new AlertDialog.Builder(SignInActivity.this);
                builder.setMessage("Login Faild ! :-(")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        }) {
            @Override
            public byte[] getBody() throws AuthFailureError {
                HashMap<String, String> params2 = new HashMap<String, String>();
                params2.put("username", username);
                params2.put("password", password);
                return new JSONObject(params2).toString().getBytes();
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };

        MySingleton.getInstance(SignInActivity.this).addToRequestQueue(sr);

    }
}
