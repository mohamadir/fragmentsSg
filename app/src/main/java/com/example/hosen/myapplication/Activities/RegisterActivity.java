package com.example.hosen.myapplication.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.hosen.myapplication.Classes.MySingleton;
import com.example.hosen.myapplication.R;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    String username,firstName,lastName,password;
    EditText userNameEt,firstNameEt,lastNameEt,passwordEt;
    Button registerBt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerBt=(Button)findViewById(R.id.signUpVolleyBt);

        userNameEt=(EditText) findViewById(R.id.email_Et);
        firstNameEt=(EditText) findViewById(R.id.firstName_Et);
        lastNameEt=(EditText) findViewById(R.id.lastName_Et);
        passwordEt=(EditText) findViewById(R.id.password_Et);


        // get the texts from the edit texts

        registerBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                registerRequest(userNameEt.getText().toString(),passwordEt.getText().toString(),firstNameEt.getText().toString(),lastNameEt.getText().toString());

            }
        });




    }

    public void registerRequest(final String username, final String password, final String firstname, final String lastname){
        StringRequest sr = new StringRequest(Request.Method.POST, "http://172.104.150.56/api/register", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("responsy",response);
                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                builder.setMessage("Register Successfully")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //do things
                                Intent i = new Intent(RegisterActivity.this,SignInActivity.class);
                                startActivity(i);
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("myError",error.getMessage()+" ");
            }
        }) {
            @Override
            public byte[] getBody() throws AuthFailureError {
                HashMap<String, String> params2 = new HashMap<String, String>();
                if(username==null)
                    Log.i("imnull","imnull");
                else {
                    params2.put("email", username);
                    params2.put("password", password);
                    params2.put("first_name", firstname);
                    params2.put("last_name", lastname);
                    params2.put("profile_image", "aaaa");
                }
                return new JSONObject(params2).toString().getBytes();
            }

         /*   @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6ImU2MjFhZTY0OTY5ODBhMmI0OTdiYzVjMzE2Y2FjYTgyOGU3ZTRiNTM1NTUzZDA2ZGZlZjg5ZWIwOGEyNDM0YmEyZTZlNzNmOGIwZGJhMmQwIn0.eyJhdWQiOiIyIiwianRpIjoiZTYyMWFlNjQ5Njk4MGEyYjQ5N2JjNWMzMTZjYWNhODI4ZTdlNGI1MzU1NTNkMDZkZmVmODllYjA4YTI0MzRiYTJlNmU3M2Y4YjBkYmEyZDAiLCJpYXQiOjE1MDA5OTA3NzYsIm5iZiI6MTUwMDk5MDc3NiwiZXhwIjoxNTAwOTkyNTc2LCJzdWIiOiI0Iiwic2NvcGVzIjpbIioiXX0.HpcPxoJUmfuYfTD-AEfYoTsiPwkPd39ZrOTRTsTll7Y-938BEKVVO2y2f7QQi4MheM2qcRLlWD4blSLJvGW_0OX2Xq-endqvny5sweGvFffO1MxDPX32WvpFhrvE52ZHWVgvCCSLw1aJjLLM89sqTKVoITDXHprI0_uFLfCvSk7TmTtvpE7PpJvKa-ZW7j763MeE-ywUGfNaxa214OlfS_UHl5UGWTQ5fhG3blnzOSmkS8u0Fy0c1-1hmkY2Ivmxk_7s5yHTYa-p9NtU0Ati1XLCOlonQdHNCkUSjWTWSwFEaiBV17M5PskR_hwc2jq79v6eHWGy0yJ-NOlrE38WiEwkT4SPTgKgvplz6bqVXFsNGWl-ZUxcz_VMSV23CGD0oImSleZdgp7sMBSFDvmasV0d3wXjXau0Ft8Q2LYtHtC75dYXkUOUkSET3yQg07GG4JFbOK_LWldUIn9GezYtQbvTQe_jxHm42go2yK1meLXxSr0YxpR8sRArkPIrr0kTOpluREVAL0WmHyo8nZEEwtZeIjbi862KTmAdYGaGC709duuasLspsxyJKUifR6Rab8V_Pb2RgNtKy-RJsIpcoabewZSatVM0xzKmpiZ15A0qgY6FUugBEhFJ-V9OeT8wQWLMVkWEx13zvY8Lc0hgrOkFoSFwGooqYGTBeSbzOc4");
                return params;
            }*/

            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };

        MySingleton.getInstance(RegisterActivity.this).addToRequestQueue(sr);

    }

}
